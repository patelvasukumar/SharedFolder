package Model.Design;

import java.util.Collections;
import java.util.List;

/**
 * Manages player data in memory and handles persistence.
 * Provides operations to add, remove, update, and retrieve players.
 */
public class DataStore {

	/** 
	 * List of all players 
	 */
    private static final List<Player> players;

    /** 
     * Counter used to generate unique player IDs 
     */
    private static int idCounter = 1;

    /**
     * Private constructor to prevent instantiation.
     */
    private DataStore() {}

    /**
     * Static block to initialize player data.
     * Loads players from storage or creates default players if none exist.
     */
    static {
        List<Player> loadedPlayers = PlayerDataStore.loadPlayers();
        if (loadedPlayers.isEmpty()) {
            loadedPlayers.add(new Player(getNextId(), "Vasu Patel", "Male", "Indian"));
            loadedPlayers.add(new Player(getNextId(), "Kavya Vasani", "Male", "Indian"));
        } else {
            int maxId = loadedPlayers.stream().mapToInt(Player::getId).max().orElse(0);
            idCounter = maxId + 1;
        }
        players = loadedPlayers;
    }

    /**
     * Generates the next unique player ID.
     *
     * @return next player ID
     */
    public static synchronized int getNextId() { 
    	return idCounter++; 
    	}

    /**
     * Adds a new player and saves data.
     *
     * @param p player to add
     */
    public static synchronized void addPlayer(Player p) {
        players.add(p);
        PlayerDataStore.savePlayers(players);
    }

    /**
     * Removes a player by ID and saves data.
     *
     * @param id ID of the player to remove
     */
    public static synchronized void removePlayer(int id) {
        players.removeIf(pl -> pl.getId() == id);
        PlayerDataStore.savePlayers(players);
    }

    /**
     * Updates an existing player and saves data.
     *
     * @param p updated player object
     */
    public static synchronized void updatePlayer(Player p) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == p.getId()) {
                players.set(i, p);
                PlayerDataStore.savePlayers(players);
                return;
            }
        }
    }

    /**
     * Returns an unmodifiable list of all players.
     *
     * @return list of players
     */
    public static synchronized List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    /**
     * Clears all player data and resets ID counter.
     */
    public static synchronized void clearAll() {
        players.clear();
        idCounter = 1;
        PlayerDataStore.savePlayers(players);
    }
}