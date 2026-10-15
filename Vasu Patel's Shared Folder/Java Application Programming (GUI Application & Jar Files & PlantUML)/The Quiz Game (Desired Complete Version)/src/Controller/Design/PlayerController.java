package Controller.Design;

import java.util.List;
import Model.Design.DataStore;
import Model.Design.Player;

public class PlayerController {

    public List<Player> getPlayers() {
        return DataStore.getPlayers();
    }

    public Player addPlayer(String name, String gender, String nationality,
                            int currentPoint, int historicPoint) {

        Player p = new Player(
                DataStore.getNextId(),
                name,
                gender,
                nationality,
                currentPoint,
                historicPoint
        );

        DataStore.addPlayer(p);
        return p;
    }

    public void updatePlayer(Player p) {
        DataStore.updatePlayer(p);
    }

    public void removePlayer(int id) {
        DataStore.removePlayer(id);
    }
}