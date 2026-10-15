package Controller.Design;

import java.util.List;
import Model.Design.DataStore;
import Model.Design.Player;

/**
 * The PlayerController class acts as a controller layer
 * between the user interface and the data storage.
 * It manages operations related to Player objects such as
 * adding, updating, retrieving, and removing players.
 */
public class PlayerController {

	
	/**
	 * Default constructor for PlayerController.
	 */
	public PlayerController() {
	    super();
	}
	
	/**
     * Retrieves the list of all players from the data store.
     *
     * @return a list of Player objects
     */
    public List<Player> getPlayers() {
        return DataStore.getPlayers();
    }

    /**
     * Creates a new Player object and adds it to the data store.
     *
     * @param name the name of the player
     * @param gender the gender of the player
     * @param nationality the nationality of the player
     * @param currentPoint the current points of the player
     * @param historicPoint the historical points of the player
     * @return the newly created Player object
     */
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

    /**
     * Updates an existing player's information in the data store.
     *
     * @param p the Player object containing updated data
     */
    public void updatePlayer(Player p) {
        DataStore.updatePlayer(p);
    }

    /**
     * Removes a player from the data store using their ID.
     *
     * @param id the unique identifier of the player to be removed
     */
    public void removePlayer(int id) {
        DataStore.removePlayer(id);
    }
}