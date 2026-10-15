package Network;

import Controller.Design.PlayerController;
import Controller.Design.QuestionController;
import MainView.Play.PlayMode;
import Model.Design.Player;
import Model.Design.Question;
import util.LanguageManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Controls multiplayer game logic including hosting, joining,
 * question distribution, answer processing, scoring, and network events.
 */
public class MultiplayerGameController {

	/**
     * Represents current multiplayer mode.
     */
	public enum Mode {

	    /** 
	     * Offline single-player mode 
	     */
	    OFFLINE,

	    /**
	     *  Host server mode (controls game flow) 
	     */
	    HOST,

	    /** 
	     * Client mode (connected to host) 
	     */
	    CLIENT
	}

    private final PlayMode view;
    private final QuestionController questionController;
    private final PlayerController playerController;
    private final Player player;
    private final NetworkManager network = new NetworkManager();
    private final Random rnd = new Random();

    private Mode mode = Mode.OFFLINE;

    private int localId = 0;
    private String localName = "";

    private int currentLevel = 1;
    private final int totalLevels = 15;

    private String currentQuestionText = "";
    private String currentCorrect = "";
    private String[] currentOptions = new String[4];

    private boolean spectator = false;
    private boolean questionLocked = false;
    private boolean freezeTimeEnabled = false;

    private final Map<Integer, Integer> scores = new LinkedHashMap<>();
    private final Map<Integer, String> names = new LinkedHashMap<>();
    private final Set<Integer> activePlayers = new LinkedHashSet<>();
    private final Set<Integer> answeredThisRound = new LinkedHashSet<>();
    private final Set<Integer> spectators = new LinkedHashSet<>();

    /**
     * Creates a multiplayer controller and registers network listeners.
     *
     * @param view UI view (PlayMode)
     * @param questionController controller for questions
     * @param playerController controller for player updates
     * @param player current player model
     */
    public MultiplayerGameController(PlayMode view,QuestionController questionController,PlayerController playerController,Player player) {
        this.view = view;
        this.questionController = questionController;
        this.playerController = playerController;
        this.player = player;

        network.addListener(new NetworkManager.NetworkListener() {
            @Override
            public void onMessage(String[] msg) {
                handleMessage(msg);
            }

            @Override
            public void onClosed(String reason) {
                SwingUtilities.invokeLater(() -> view.showNetworkStatus(reason));
            }
        });
    }

    /**
     * Checks if current user is host.
     *
     * @return true if host
     */
    public boolean isHost() {
        return mode == Mode.HOST;
    }

    /**
     * Checks if current user is client.
     *
     * @return true if client
     */
    public boolean isClient() {
        return mode == Mode.CLIENT;
    }

    /**
     * Checks if game is in online mode.
     *
     * @return true if online
     */
    public boolean isOnline() {
        return mode != Mode.OFFLINE;
    }

    /**
     * Checks if freeze time feature is enabled.
     *
     * @return true if enabled
     */
    public boolean isFreezeTimeEnabled() {
        return freezeTimeEnabled;
    }

    /**
     * Enables or disables freeze time feature.
     *
     * @param enabled true to enable
     */
    public void setFreezeTime(boolean enabled) {
        freezeTimeEnabled = enabled;
        view.setFreezeTimeEnabled(enabled);
        view.showNetworkStatus(enabled ? "Freeze Time ON" : "Freeze Time OFF");

        if (mode == Mode.HOST) {
            network.broadcastFreezeTime(enabled);
        } else if (mode == Mode.CLIENT) {
            network.sendToServer(NetworkMessage.freezeTimeMsg(enabled));
        }
    }

    /**
     * Starts hosting a multiplayer game.
     *
     * @param name host name
     * @param port server port
     * @param maxPlayers maximum players allowed
     * @throws Exception if server fails to start
     */
    public void startHost(String name, int port, int maxPlayers) throws Exception {
        resetState();

        localName = (name == null || name.isBlank()) ? "Host" : name.trim();
        mode = Mode.HOST;
        localId = 0;
        // Store the host's own name so lookups via names.get(0) resolve correctly.
        names.put(0, localName);

        network.startHost(port, maxPlayers, localName);

        view.setNetworkStateLabel("HOST");
        view.setHostDisplayOnly(true);
        view.showNetworkStatus("Hosting on port " + port);
        view.enableMultiplayerUI(true);
        view.setOptionsEnabled(false);
    }
    
    /**
     * Joins an existing multiplayer host.
     *
     * @param name player name
     * @param ip host IP address
     * @param port host port
     * @throws Exception if connection fails
     */
    public void joinHost(String name, String ip, int port) throws Exception {
        resetState();

        localName = (name == null || name.isBlank()) ? "Player" : name.trim();
        mode = Mode.CLIENT;
        localId = -1;

        network.connectToServer(ip, port, localName);

        view.setNetworkStateLabel("CLIENT");
        view.setHostDisplayOnly(false);
        view.showNetworkStatus("Connecting to " + ip + ":" + port);
        view.enableMultiplayerUI(true);
    }

    /**
     * Resets game state before starting or joining a session.
     */
    private void resetState() {
        currentLevel = 1;
        spectator = false;
        questionLocked = false;
        currentQuestionText = "";
        currentCorrect = "";
        currentOptions = new String[4];

        scores.clear();
        names.clear();
        activePlayers.clear();
        answeredThisRound.clear();
        spectators.clear();
    }

    /**
     * Sends next question from host to all players.
     */
    public void hostSendQuestion() {
        if (!isHost()) return;

        Question q = questionController.getQuestion(currentLevel);
        if (q == null) {
            endGame();
            return;
        }

        answeredThisRound.clear();
        syncPlayersFromNetwork();

        boolean useFirst = rnd.nextBoolean();

        currentQuestionText = useFirst ? q.getQuestion1() : q.getQuestion2();
        currentCorrect = useFirst ? q.getQ1op1() : q.getQ2op1();

        List<String> opts = new ArrayList<>();
        if (useFirst) {
            Collections.addAll(opts, q.getQ1op1(), q.getQ1op2(), q.getQ1op3(), q.getQ1op4());
        } else {
            Collections.addAll(opts, q.getQ2op1(), q.getQ2op2(), q.getQ2op3(), q.getQ2op4());
        }

        Collections.shuffle(opts, rnd);
        currentOptions = opts.toArray(new String[0]);

        view.showQuestion(currentQuestionText, currentOptions, currentLevel, true);
        view.appendQuestionLog("Q" + currentLevel + ": " + currentQuestionText);

        network.broadcastQuestion(currentLevel, currentQuestionText, currentCorrect, currentOptions);

        questionLocked = false;
        view.setHostDisplayOnly(true);
        view.setOptionsEnabled(false);
    }

    /**
     * Submits answer from local client.
     *
     * @param selectedOption selected answer
     */
    public void submitLocalAnswer(String selectedOption) {
        if (mode == Mode.HOST) return;
        if (questionLocked || spectator) return;

        if (selectedOption == null || selectedOption.isBlank()) {
            view.showNetworkStatus("Select an option first.");
            return;
        }

        int index = Arrays.asList(currentOptions).indexOf(selectedOption);
        if (index >= 0) {
            network.sendToServer(NetworkMessage.answerMsg(localId, index));
            questionLocked = true;
            view.setOptionsEnabled(false);
            view.showNetworkStatus("Answer sent");
        }
    }

    /**
     * Handles timeout event for host player.
     */
    public void onLocalTimeout() {
        if (!isHost() || spectator) return;
        processAnswer(0, localName, null);
    }

    /**
     * Sends chat message in multiplayer session.
     *
     * @param text message text
     */
    public void sendChat(String text) {
        if (text == null || text.isBlank()) return;

        if (mode == Mode.HOST) {
            view.addChatMessage(localName + ": " + text);
            network.broadcastChatExcept(0, 0, text);
        } else if (mode == Mode.CLIENT) {
            view.addChatMessage(localName + ": " + text);
            network.sendToServer(NetworkMessage.chatMsg(localId, text));
        }
    }

    /**
     * Disconnects from multiplayer session.
     */
    public void disconnect() {
        network.close();
        resetState();
        mode = Mode.OFFLINE;
        view.setNetworkStateLabel("OFFLINE");
        view.enableMultiplayerUI(false);
        view.setOptionsEnabled(true);
        view.setFreezeTimeEnabled(false);
    }

    /**
     * Ends game and shows final ranking.
     */
    public void endGame() {
        if (mode == Mode.HOST) {
            network.broadcastShutdown();
        }
        network.close();
        mode = Mode.OFFLINE;
        view.setNetworkStateLabel("OFFLINE");
        view.openRankingWindow(getNamesSnapshot(), getScoresSnapshot());
    }

    /**
     * Returns snapshot of player scores.
     *
     * @return map of player scores
     */
    public Map<Integer, Integer> getScoresSnapshot() {
        LinkedHashMap<Integer, Integer> out = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> e : scores.entrySet()) {
            if (e.getKey() != 0) {
                out.put(e.getKey(), e.getValue());
            }
        }
        return out;
    }

    /**
     * Returns snapshot of player names.
     *
     * @return map of player names
     */
    public Map<Integer, String> getNamesSnapshot() {
        LinkedHashMap<Integer, String> merged = new LinkedHashMap<>();
        for (Map.Entry<Integer, String> e : network.getPlayerNames().entrySet()) {
            if (e.getKey() != 0) {
                merged.put(e.getKey(), e.getValue());
            }
        }
        for (Map.Entry<Integer, String> e : names.entrySet()) {
            if (e.getKey() != 0) {
                merged.put(e.getKey(), e.getValue());
            }
        }
        return merged;
    }

    /**
     * Processes an answer submitted by a player.
     * Updates score, spectators, UI, and network state accordingly.
     *
     * @param playerId ID of the player submitting the answer
     * @param playerName name of the player
     * @param selectedOption selected answer option (null if none)
     */
    private void processAnswer(int playerId, String playerName, String selectedOption) {
        
    	if (playerId == 0) return;
    	
    	boolean correct = selectedOption != null && selectedOption.equals(currentCorrect);

        int newScore = scores.getOrDefault(playerId, 0);
        if (correct) {
            newScore++;
        }
        scores.put(playerId, newScore);
        names.putIfAbsent(playerId, playerName);

        activePlayers.add(playerId);
        answeredThisRound.add(playerId);

        if (!correct) {
            spectators.add(playerId);
            if (playerId == localId || (playerId == 0 && isHost())) {
                spectator = true;
                questionLocked = true;
                view.setOptionsEnabled(false);
            }
        }

        if (correct && playerId == localId) {
            player.setCurrentPoint(newScore);
            if (player.getHistoricPoint() < player.getCurrentPoint()) {
                player.setHistoricPoint(player.getCurrentPoint());
            }
            playerController.updatePlayer(player);
        }

        view.updateScores(getScoresSnapshot());
        view.appendQuestionLog(playerName + " selected " + (selectedOption == null ? "No Answer" : selectedOption) + " -> " + (correct ? "Correct" : "Incorrect") + " | Correct: " + currentCorrect);
        view.showAnswerResult(correct, currentCorrect, newScore);

        network.broadcastResult(playerId, correct, newScore, playerName, selectedOption == null ? "" : selectedOption, currentCorrect);

        if (!correct) {
            network.broadcastSpectator(playerId);
        }

        if (isHost() && allActivePlayersFinishedRound()) {
            currentLevel++;
            if (currentLevel > totalLevels) {
                endGame();
            } else {
                hostSendQuestion();
            }
        }
    }

    /**
     * Checks whether all active (non-spectator) players
     * have completed answering the current round.
     *
     * @return true if all active players have answered
     */
    private boolean allActivePlayersFinishedRound() {
        for (Integer id : activePlayers) {
            if (id == 0) continue;
            if (spectators.contains(id)) continue;
            if (!answeredThisRound.contains(id)) return false;
        }
        return true;
    }

    /**
     * Synchronizes local player state with network player data.
     * Adds missing players and updates active player list.
     */
    private void syncPlayersFromNetwork() {
        Map<Integer, String> remote = network.getPlayerNames();
        for (Map.Entry<Integer, String> e : remote.entrySet()) {
            int id = e.getKey();
            if (id == 0) continue;

            String name = e.getValue();
            names.putIfAbsent(id, name);
            scores.putIfAbsent(id, 0);
            activePlayers.add(id);

            if (spectators.contains(id)) {
                activePlayers.remove(id);
            }
        }
    }

    /**
     * Handles incoming network messages and routes them
     * to appropriate game logic handlers.
     * 
     * @param msg parsed message array [sender, type, data...]
     */
    private void handleMessage(String[] msg) {
        if (msg == null || msg.length < 2) return;

        String sender = msg[0];
        String type = msg[1];

        SwingUtilities.invokeLater(() -> {
            switch (type) {
                case "ASSIGN_ID":
                    if (msg.length >= 3) {
                        try {
                            localId = Integer.parseInt(msg[2]);
                            names.put(localId, localName);
                            scores.putIfAbsent(localId, 0);
                            activePlayers.add(localId);
                            view.showNetworkStatus("Assigned ID: " + localId);
                        } catch (Exception ignored) {
                        }
                    }
                    break;

                case "JOIN":
                    if (msg.length >= 3) {
                        int id = 0;
                        try {
                            id = Integer.parseInt(sender);
                        } catch (Exception ignored) {
                        }
                        String name = NetworkMessage.dec(msg[2]);
                        names.put(id, name);
                        if (isHost()) {
                            syncPlayersFromNetwork();
                            if (id != 0) {
                                view.appendQuestionLog(name + " joined the room");
                                // Re-send the current question directly to this client.
                                // Without this the client never receives the question that was
                                // broadcast before it connected, leaving it frozen with an empty
                                // currentOptions array and locked confirm button.
                                if (currentOptions != null
                                        && currentOptions.length >= 4
                                        && currentOptions[0] != null) {
                                    network.hostSendTo(id, NetworkMessage.questionMsg(
                                            currentLevel,
                                            currentQuestionText,
                                            currentCorrect,
                                            currentOptions));
                                }
                            }
                        } else {
                            if (id == 0) {
                                view.showNetworkStatus("Connected to host: " + name);
                            } else {
                                view.appendQuestionLog(name + " joined the room");
                            }
                        }
                    }
                    break;

                case "ANSWER":
                    if (isHost() && msg.length >= 3) {
                        try {
                            int pid = Integer.parseInt(sender);
                            int optionIndex = Integer.parseInt(msg[2]);
                            String pname = names.getOrDefault(pid, "Player " + pid);
                            String selected = optionIndex >= 0 && optionIndex < currentOptions.length ? currentOptions[optionIndex] : "";
                            processAnswer(pid, pname, selected);
                        } catch (Exception ignored) {
                        }
                    }
                    break;

                case "QUESTION":
                    if (msg.length >= 3) {
                        String[] data = NetworkMessage.splitMulti(msg[2]);
                        if (data.length >= 7) {
                            try {
                                currentLevel = Integer.parseInt(data[0]);
                            } catch (Exception ignored) {
                            }
                            currentQuestionText = data[1];
                            currentCorrect = data[2];
                            currentOptions = new String[]{data[3], data[4], data[5], data[6]};
                            questionLocked = false;
                            view.showQuestion(currentQuestionText, currentOptions, currentLevel, false);
                            view.setOptionsEnabled(!spectators.contains(localId));
                            view.appendQuestionLog("Q" + currentLevel + ": " + currentQuestionText);
                        }
                    }
                    break;

                case "CHAT":
                    if (msg.length >= 3) {
                        String text = NetworkMessage.dec(msg[2]);
                        int id = 0;
                        try {
                            id = Integer.parseInt(sender);
                        } catch (Exception ignored) {
                        }
                        String name = names.getOrDefault(id, id == 0 ? "Host" : "Player " + id);
                        view.addChatMessage(name + ": " + text);
                    }
                    break;

                case "RESULT":
                    if (msg.length >= 3) {
                        String[] data = NetworkMessage.splitMulti(msg[2]);
                        if (data.length >= 6) {
                            int pid = 0;
                            int score = 0;
                            try {
                                pid = Integer.parseInt(data[0]);
                                score = Integer.parseInt(data[2]);
                            } catch (Exception ignored) {
                            }
                            boolean correct = "Correct".equalsIgnoreCase(data[1]);
                            String pname = data[3];
                            String selected = data[4];
                            String correctAnswer = data[5];
                            names.putIfAbsent(pid, pname);
                            scores.put(pid, score);
                            if (!correct && pid == localId) {
                                spectator = true;
                                questionLocked = true;
                                view.setOptionsEnabled(false);
                            }
                            view.updateScores(getScoresSnapshot());
                            view.appendQuestionLog(pname + " selected " + selected + " -> " + (correct ? "Correct" : "Incorrect") + " | Correct: " + correctAnswer);
                            view.showAnswerResult(correct, correctAnswer, score);
                        }
                    }
                    break;

                case "SPECTATOR":
                    if (msg.length >= 3) {
                        int pid = 0;
                        try {
                            pid = Integer.parseInt(sender);
                        } catch (Exception ignored) {
                        }
                        spectators.add(pid);
                        activePlayers.remove(pid);
                        if (pid == localId) {
                            spectator = true;
                            questionLocked = true;
                            view.setOptionsEnabled(false);
                            view.showNetworkStatus("You are now spectator");
                        }
                    }
                    break;

                case "FREEZE_TIME":
                    if (msg.length >= 3) {
                        boolean freeze = "ON".equalsIgnoreCase(msg[2]);
                        freezeTimeEnabled = freeze;
                        view.setFreezeTimeEnabled(freeze);
                        view.showNetworkStatus(freeze ? "Freeze Time ON" : "Freeze Time OFF");
                        if (isHost() && !"0".equals(sender)) {
                            network.broadcastFreezeTime(freeze);
                        }
                    }
                    break;

                case "SHUTDOWN":
                    if (isHost() && "0".equals(sender)) return;
                    view.showNetworkStatus(LanguageManager.t("Server shutdown"));
                    view.openRankingWindow(getNamesSnapshot(), getScoresSnapshot());
                    break;
            }
        });
    }
}