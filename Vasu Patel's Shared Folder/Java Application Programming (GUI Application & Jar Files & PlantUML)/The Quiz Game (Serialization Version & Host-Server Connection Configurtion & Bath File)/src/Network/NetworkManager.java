package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Manages network communication for multiplayer game play.
 * Supports hosting and client connections, message broadcasting,
 * and handling player interactions.
 */
public class NetworkManager {

	/**
	 * Default constructor for NetworkManager.
	 */
	public NetworkManager() {}
	
	/** 
	 * Name of the host player 
	 */
	private String hostName = "Host";
	
	/**
     * Listener interface for receiving network events.
     */
    public interface NetworkListener {
    	 /**
         * Called when a message is received.
         *
         * @param msg parsed message parts
         */
        void onMessage(String[] msg);

        /**
         * Called when connection is closed.
         *
         * @param reason reason for closure
         */
        void onClosed(String reason);
    }

    /**
     * Defines the operating mode of the network.
     */
    public enum Mode {

        /** 
         * No network connection (offline mode) 
         */
        OFFLINE,

        /**
         * Running as host/server 
         */
        HOST,

        /** 
         * Connected as a client 
         */
        CLIENT
    }

    /**
     * Sends a raw message to all clients except the specified one.
     *
     * @param excludeId ID of client to exclude
     * @param raw message string
     */
    public void broadcastRawExcept(int excludeId, String raw) {
        for (ClientConnection c : clients.values()) {
            if (c.id != excludeId) {
                c.send(raw);
            }
        }
    }
    
    /**
     * Broadcasts a chat message to all clients except one.
     *
     * @param excludeId client ID to exclude
     * @param playerId sender player ID
     * @param msg chat message
     */
    public void broadcastChatExcept(int excludeId, int playerId, String msg) {
        if (mode != Mode.HOST) return;
        broadcastRawExcept(excludeId, NetworkMessage.chatMsg(playerId, msg));
    }
    
    /**
     * Represents a connected client.
     */
    private static final class ClientConnection {
    	
    	/** 
    	 * Unique client ID 
    	 */
        final int id;

        /** 
         * Client socket 
         */
        final Socket socket;

        /** 
         * Input stream 
         */
        final BufferedReader in;

        /** 
         * Output stream 
         */
        final PrintWriter out;

        /** 
         * Player name 
         */
        volatile String name = "";

        /**
         * Creates a client connection.
         *
         * @param id client ID
         * @param socket client socket
         * @throws IOException if stream creation fails
         */
        ClientConnection(int id, Socket socket) throws IOException {
            this.id = id;
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        }

        /**
         * Sends a raw message to the client.
         *
         * @param raw message string
         */
        void send(String raw) {
            out.println(raw);
        }

        /**
         * Closes connection safely.
         */
        void closeQuietly() {
            try {
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }

    private final List<NetworkListener> listeners = new CopyOnWriteArrayList<>();
    private final Map<Integer, ClientConnection> clients = new ConcurrentHashMap<>();
    private final Map<Integer, String> playerNames = new ConcurrentHashMap<>();
    private final Map<Integer, Integer> scores = new ConcurrentHashMap<>();

    private volatile Mode mode = Mode.OFFLINE;
    private volatile boolean running = false;

    private ServerSocket serverSocket;

    private Socket clientSocket;
    private BufferedReader clientIn;
    private PrintWriter clientOut;
    private int localClientId = -1;

    private final AtomicInteger nextId = new AtomicInteger(1);

    /**
     * Adds a network listener.
     *
     * @param l listener instance
     */
    public void addListener(NetworkListener l) {
        if (l != null) listeners.add(l);
    }

    /**
     * Returns current network mode.
     *
     * @return mode (OFFLINE, HOST, CLIENT)
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * Gets local client ID.
     *
     * @return client ID
     */
    public int getLocalClientId() {
        return localClientId;
    }

    /**
     * Returns all player names.
     *
     * @return unmodifiable map of player names
     */
    public Map<Integer, String> getPlayerNames() {
        return Collections.unmodifiableMap(playerNames);
    }

    /**
     * Returns all player scores.
     *
     * @return unmodifiable map of scores
     */
    public Map<Integer, Integer> getScores() {
        return Collections.unmodifiableMap(scores);
    }

    /**
     * Starts hosting a game server.
     *
     * @param port server port
     * @param maxPlayers maximum allowed players
     * @param hostName host player name
     * @throws IOException if server fails to start
     */
    public synchronized void startHost(int port, int maxPlayers, String hostName) throws IOException {
        close();
        mode = Mode.HOST;
        running = true;
        clients.clear();
        playerNames.clear();
        scores.clear();
        nextId.set(1);

        this.hostName = (hostName == null || hostName.isBlank()) ? "Host" : hostName.trim();

        playerNames.put(0, this.hostName);
        scores.put(0, 0);
        localClientId = 0;

        serverSocket = new ServerSocket(port);

        Thread acceptThread = new Thread(() -> acceptLoop(maxPlayers), "QM-AcceptLoop");
        acceptThread.setDaemon(true);
        acceptThread.start();
    }
    
    
    /**
     * Connects to a remote server.
     *
     * @param ip server IP address
     * @param port server port
     * @param playerName player name
     * @throws IOException if connection fails
     */
    public synchronized void connectToServer(String ip, int port, String playerName) throws IOException {
        close();
        mode = Mode.CLIENT;
        running = true;

        clientSocket = new Socket(ip, port);
        clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        clientOut = new PrintWriter(clientSocket.getOutputStream(), true);

        Thread listenThread = new Thread(this::clientListenLoop, "QM-ClientListen");
        listenThread.setDaemon(true);
        listenThread.start();

        sendToServer(NetworkMessage.joinMsg(playerName));
    }

    /**
     * Sends a message to server (client mode only).
     *
     * @param raw message string
     */
    public void sendToServer(String raw) {
        if (mode != Mode.CLIENT || clientOut == null) return;
        clientOut.println(raw);
    }

    /**
     * Broadcasts a message to all clients (host mode).
     *
     * @param raw message string
     */
    public void hostBroadcast(String raw) {
        if (mode != Mode.HOST) return;
        broadcastRaw(raw);
    }

    /**
     * Sends a message to a specific client.
     *
     * @param playerId target player ID
     * @param raw message string
     */
    public void hostSendTo(int playerId, String raw) {
        if (mode != Mode.HOST) return;
        ClientConnection c = clients.get(playerId);
        if (c != null) c.send(raw);
    }

    /**
     * Broadcasts game start signal to all clients.
     */
    public void broadcastStart() {
        hostBroadcast(NetworkMessage.startMsg());
    }

    /**
     * Broadcasts a question to all players.
     *
     * @param level question level
     * @param questionText question text
     * @param correctAnswer correct answer
     * @param options list of options
     */
    public void broadcastQuestion(int level, String questionText, String correctAnswer, String[] options) {
        hostBroadcast(NetworkMessage.questionMsg(level, questionText, correctAnswer, options));
    }

    /**
     * Broadcasts result of an answer.
     *
     * @param playerId player ID
     * @param correct whether answer was correct
     * @param score updated score
     * @param playerName player name
     * @param selectedAnswer selected answer
     * @param correctAnswer correct answer
     */
    public void broadcastResult(int playerId, boolean correct, int score, String playerName, String selectedAnswer, String correctAnswer) {
        hostBroadcast(NetworkMessage.resultMsg(playerId, correct, score, playerName, selectedAnswer, correctAnswer));
    }

    /**
     * Broadcasts a chat message to all clients.
     *
     * @param playerId sender ID
     * @param msg message text
     */
    public void broadcastChat(int playerId, String msg) {
        hostBroadcast(NetworkMessage.chatMsg(playerId, msg));
    }

    /**
     * Notifies clients that a player became a spectator.
     *
     * @param playerId player ID
     */
    public void broadcastSpectator(int playerId) {
        hostBroadcast(NetworkMessage.spectatorMsg(playerId));
    }

    /**
     * Broadcasts freeze time state to all clients.
     *
     * @param freeze true to freeze, false to resume
     */
    public void broadcastFreezeTime(boolean freeze) {
        hostBroadcast(NetworkMessage.freezeTimeMsg(freeze));
    }

    /**
     * Broadcasts shutdown signal to all clients.
     */
    public void broadcastShutdown() {
        hostBroadcast(NetworkMessage.shutdownMsg());
    }

    /**
     * Closes all network connections and resets the manager.
     */
    public synchronized void close() {
        running = false;

        try {
            if (clientSocket != null) clientSocket.close();
        } catch (IOException ignored) {
        }

        try {
            if (serverSocket != null) serverSocket.close();
        } catch (IOException ignored) {
        }

        for (ClientConnection c : clients.values()) {
            c.closeQuietly();
        }

        clients.clear();
        clientSocket = null;
        clientIn = null;
        clientOut = null;
        serverSocket = null;
        localClientId = -1;
        mode = Mode.OFFLINE;
    }

    private void acceptLoop(int maxPlayers) {
        while (running) {
            try {
                Socket s = serverSocket.accept();
                int id = nextId.getAndIncrement();

                ClientConnection conn = new ClientConnection(id, s);
                clients.put(id, conn);

                conn.send(NetworkMessage.assignIdMsg(id));
                conn.send(NetworkMessage.joinMsg(hostName));

                Thread t = new Thread(() -> hostClientListenLoop(conn), "QM-Client-" + id);
                t.setDaemon(true);
                t.start();

                if (clients.size() + 1 >= maxPlayers) {
                    broadcastStart();
                }
            } catch (IOException e) {
                if (running) fireClosed("Server stopped: " + e.getMessage());
                break;
            }
        }
    }

    private void hostClientListenLoop(ClientConnection conn) {
        try {
            String line;
            while (running && (line = conn.in.readLine()) != null) {
                handleHostRaw(conn, line);
            }
        } catch (IOException e) {
            disconnectHostClient(conn.id, "Client disconnected");
        } finally {
            disconnectHostClient(conn.id, "Client closed");
        }
    }

    private void handleHostRaw(ClientConnection conn, String raw) {
        String[] parts = NetworkMessage.splitMsg(raw);
        if (parts.length < 2) return;

        String type = parts[1];

        switch (type) {
            
        	case "JOIN":
                if (parts.length >= 3) {
                    conn.name = NetworkMessage.dec(parts[2]);
                    playerNames.put(conn.id, conn.name);
                    scores.putIfAbsent(conn.id, 0);
                }
                // Rewrite the sender to the actual client ID so every listener
                // (including the host's own MultiplayerGameController) sees the
                // real player ID instead of the hardcoded "0" from joinMsg().
                String correctedJoin = conn.id + NetworkMessage.SEP
                        + parts[1]
                        + (parts.length > 2 ? NetworkMessage.SEP + parts[2] : "");
                fireMessage(correctedJoin);
                broadcastRaw(correctedJoin);
                break;
                
            case "CHAT":
                fireMessage(raw);
                broadcastRawExcept(conn.id, raw);
                break;

            case "ANSWER":
                fireMessage(raw);
                break;

            case "DISCONNECT":
                fireMessage(raw);
                broadcastRaw(raw);
                disconnectHostClient(conn.id, "Disconnected");
                break;

            case "SPECTATOR":
                fireMessage(raw);
                broadcastRaw(raw);
                break;

            case "FREEZE_TIME":
                fireMessage(raw);
                broadcastRaw(raw);
                break;

            default:
                fireMessage(raw);
                break;
        }
    }

    private void clientListenLoop() {
        try {
            String line;
            while (running && (line = clientIn.readLine()) != null) {
                String[] parts = NetworkMessage.splitMsg(line);
                if (parts.length >= 2 && "ASSIGN_ID".equals(parts[1]) && parts.length >= 3) {
                    try {
                        localClientId = Integer.parseInt(parts[2]);
                    } catch (Exception ignored) {
                    }
                }
                fireMessage(line);
            }
        } catch (IOException e) {
            if (running) fireClosed("Disconnected: " + e.getMessage());
        } finally {
            running = false;
        }
    }

    private void disconnectHostClient(int id, String reason) {
        ClientConnection conn = clients.remove(id);
        if (conn != null) conn.closeQuietly();
        playerNames.remove(id);
        scores.remove(id);
        if (running) fireClosed("Player " + id + ": " + reason);
    }

    private void broadcastRaw(String raw) {
        for (ClientConnection c : clients.values()) {
            c.send(raw);
        }
    }

    private void fireMessage(String raw) {
        String[] parts = NetworkMessage.splitMsg(raw);
        for (NetworkListener l : listeners) {
            l.onMessage(parts);
        }
    }

    private void fireClosed(String reason) {
        for (NetworkListener l : listeners) {
            l.onClosed(reason);
        }
    }
}