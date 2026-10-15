package Model.Design;

import java.util.Collections;
import java.util.List;

public class DataStore {

    private static final List<Player> players;
    private static int idCounter = 1;

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

    public static synchronized int getNextId() { return idCounter++; }

    public static synchronized void addPlayer(Player p) {
        players.add(p);
        PlayerDataStore.savePlayers(players);
    }

    public static synchronized void removePlayer(int id) {
        players.removeIf(pl -> pl.getId() == id);
        PlayerDataStore.savePlayers(players);
    }

    public static synchronized void updatePlayer(Player p) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == p.getId()) {
                players.set(i, p);
                PlayerDataStore.savePlayers(players);
                return;
            }
        }
    }

    public static synchronized List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public static synchronized void clearAll() {
        players.clear();
        idCounter = 1;
        PlayerDataStore.savePlayers(players);
    }
}