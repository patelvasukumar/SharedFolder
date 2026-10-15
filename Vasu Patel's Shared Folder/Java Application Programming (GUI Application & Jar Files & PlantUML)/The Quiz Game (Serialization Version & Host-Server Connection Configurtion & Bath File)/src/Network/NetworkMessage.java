package Network;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Utility class responsible for creating and parsing
 * network messages used for multiplayer communication.
 *
 * Messages are encoded in a structured format:
 * senderId, messageType, and optional payload data.
 *
 * Provides helper methods for encoding/decoding strings,
 * building messages, and splitting received data.
 */
public final class NetworkMessage {

	 /**
     * Represents all supported message types in the network protocol.
     */
    public enum Type {

        /** 
         * Player joins the game 
         */
        JOIN,

        /** 
         * Server assigns a unique player ID 
         */
        ASSIGN_ID,

        /** 
         * Game start signal 
         */
        START,

        /** 
         * Question broadcast to all players 
         */
        QUESTION,

        /** 
         * Player answer submission 
         */
        ANSWER,

        /** 
         * Result of an answered question 
         */
        RESULT,

        /** 
         * Chat message between players 
         */
        CHAT,

        /** 
         * Player disconnects from server 
         */
        DISCONNECT,

        /** 
         * Server shutdown notification 
         */
        SHUTDOWN,

        /** 
         * Player becomes spectator 
         */
        SPECTATOR,

        /** Freeze/Unfreeze game timer */
        FREEZE_TIME
    }

    /** 
     * Separator used between message fields 
     */
    public static final String SEP = ",";

    /** 
     * Separator used for multi-value pay loads 
     */
    public static final String MULTI = ";";

    /**
     * Private constructor to prevent instantiation.
     * This is a utility class only.
     */
    private NetworkMessage() {
    }

    /**
     * Encodes a string for safe network transmission using UTF-8 URL encoding.
     *
     * @param s input string
     * @return encoded string (safe for network use)
     */
    private static String enc(String s) {
        if (s == null) return "";
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    /**
     * Decodes a URL-encoded string back to readable format.
     *
     * @param s encoded string
     * @return decoded string
     */
    public static String dec(String s) {
        if (s == null) return "";
        return URLDecoder.decode(s, StandardCharsets.UTF_8);
    }

    /**
     * Builds a formatted network message string.
     *
     * @param type message type
     * @param senderId sender identifier
     * @param data optional payload data
     * @return formatted message string
     */
    private static String build(Type type, String senderId, String... data) {
        StringBuilder sb = new StringBuilder();
        sb.append(senderId).append(SEP).append(type.name());
        if (data != null) {
            for (String d : data) {
                sb.append(SEP).append(d == null ? "" : d);
            }
        }
        return sb.toString();
    }

    /**
     * Creates a JOIN message when a player connects.
     *
     * @param playerName name of the player
     * @return encoded JOIN message
     */
    public static String joinMsg(String playerName) {
        return build(Type.JOIN, "0", enc(playerName));
    }

    /**
     * Creates a message assigning a player ID from the server.
     *
     * @param id assigned player ID
     * @return ASSIGN_ID message
     */
    public static String assignIdMsg(int id) {
        return build(Type.ASSIGN_ID, "0", String.valueOf(id));
    }

    /**
     * Creates a game start message.
     *
     * @return START message
     */
    public static String startMsg() {
        return build(Type.START, "0");
    }

    /**
     * Creates a question message containing level, question text,
     * correct answer, and multiple options.
     *
     * @param level question difficulty level
     * @param questionText question text
     * @param correctAnswer correct answer
     * @param options array of answer options (must be size 4)
     * @return QUESTION message
     */
    public static String questionMsg(int level, String questionText, String correctAnswer, String[] options) {
        String payload = String.join(MULTI,
                String.valueOf(level),
                enc(questionText),
                enc(correctAnswer),
                enc(options[0]),
                enc(options[1]),
                enc(options[2]),
                enc(options[3]));
        return build(Type.QUESTION, "0", payload);
    }

    /**
     * Creates an answer submission message from a player.
     *
     * @param playerId player ID
     * @param optionIndex selected option index
     * @return ANSWER message
     */
    public static String answerMsg(int playerId, int optionIndex) {
        return build(Type.ANSWER, String.valueOf(playerId), String.valueOf(optionIndex));
    }

    /**
     * Creates a result message after an answer is processed.
     *
     * @param playerId player ID
     * @param correct whether the answer is correct
     * @param score updated score
     * @param playerName player name
     * @param selectedAnswer selected answer
     * @param correctAnswer correct answer
     * @return RESULT message
     */
    public static String resultMsg(int playerId, boolean correct, int score, String playerName, String selectedAnswer, String correctAnswer) {
        String payload = String.join(MULTI,
                String.valueOf(playerId),
                correct ? "Correct" : "Incorrect",
                String.valueOf(score),
                enc(playerName),
                enc(selectedAnswer),
                enc(correctAnswer));
        return build(Type.RESULT, "0", payload);
    }

    /**
     * Creates a chat message.
     *
     * @param playerId sender ID
     * @param msg chat text
     * @return CHAT message
     */
    public static String chatMsg(int playerId, String msg) {
        return build(Type.CHAT, String.valueOf(playerId), enc(msg));
    }

    /**
     * Creates a disconnect message.
     *
     * @param id player ID
     * @return DISCONNECT message
     */
    public static String disconnectMsg(int id) {
        return build(Type.DISCONNECT, String.valueOf(id));
    }

    /**
     * Creates a server shutdown message.
     *
     * @return SHUTDOWN message
     */
    public static String shutdownMsg() {
        return build(Type.SHUTDOWN, "0");
    }

    /**
     * Creates a spectator mode message.
     *
     * @param playerId player ID
     * @return SPECTATOR message
     */
    public static String spectatorMsg(int playerId) {
        return build(Type.SPECTATOR, String.valueOf(playerId), enc("Watching"));
    }

    /**
     * Creates a freeze/unfreeze timer message.
     *
     * @param on true to enable freeze, false to disable
     * @return FREEZE_TIME message
     */
    public static String freezeTimeMsg(boolean on) {
        return build(Type.FREEZE_TIME, "0", on ? "ON" : "OFF");
    }

    /**
     * Splits a raw network message into components.
     *
     * @param msg raw message string
     * @return array containing message parts
     */
    public static String[] splitMsg(String msg) {
        if (msg == null) return new String[0];
        return msg.split(SEP, 3);
    }

    /**
     * Splits a multi-value pay load and decodes each value.
     *
     * @param data encoded pay load string
     * @return decoded string array
     */
    public static String[] splitMulti(String data) {
        if (data == null || data.isEmpty()) return new String[0];
        String[] raw = data.split(MULTI, -1);
        String[] out = new String[raw.length];
        for (int i = 0; i < raw.length; i++) out[i] = dec(raw[i]);
        return out;
    }
}