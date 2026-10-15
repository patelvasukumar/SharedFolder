package Model.Design;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles saving and loading of Player data using file serialization.
 * Data is stored in a local file and restored when needed.
 */
public class PlayerDataStore {
	
	/** 
	 * Private constructor to prevent object creation
	 */
    private PlayerDataStore() {}
    
	/** 
	 * File name used to store player data 
	 */
    private static final String FILE_NAME = "playerDataSave.ser";

    /**
     * Saves the list of players to a file.
     *
     * @param players list of players to be saved
     */
    public static void savePlayers(List<Player> players) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the list of players from the file.
     * If the file does not exist or an error occurs, an empty list is returned.
     *
     * @return list of loaded players or empty list if no data found
     */
    @SuppressWarnings("unchecked")
    public static List<Player> loadPlayers() {
        File f = new File(FILE_NAME);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Player>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}