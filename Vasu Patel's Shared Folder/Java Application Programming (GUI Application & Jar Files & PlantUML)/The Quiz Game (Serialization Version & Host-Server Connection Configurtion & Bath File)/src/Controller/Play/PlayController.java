package Controller.Play;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controller.Design.PlayerController;
import MainView.SettingMode;
import MainView.Play.PlayMode;
import Model.Design.Player;
import Model.Exception.GameException;

/**
 * The PlayController class manages the flow of the Play Mode.
 * It handles player selection, validation, and navigation
 * between Play Mode and Setting Mode.
 */
public class PlayController {

	 /**
     * Default constructor for PlayController.
     */
    public PlayController() {
        super();
    } 
    
	/**
     * Controller used to manage player-related operations.
     */
	private PlayerController playerController = new PlayerController();

	/**
     * Starts the Play Mode by allowing the user to select a player.
     * Displays a dialog with available players and initializes
     * the game for the selected player.
     *
     * @throws GameException if no players are available
     */
	public void startPlayMode() throws GameException {

        
			List<Player> players = playerController.getPlayers();

        
			if (players == null || players.isEmpty()) {
				throw new GameException("No players found. Please add a player first.");
			}

        
			String[] options = players.stream().map(p -> p.getId() 
						+ " - " 
						+ p.getName() + " (" 
                        + p.getCurrentPoint() + " / " 
                        + p.getHistoricPoint() + ")").toArray(String[]::new);

        
			String selected = (String) JOptionPane.showInputDialog(
                null,
                "Select player to start:",
                "Select Player",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
			

        if (selected == null) { return; }

        int id;

        try {
            id = Integer.parseInt(selected.split(" - ")[0].trim());
        } 
        catch (Exception ex) {
            return;
        }

        Player player = players.stream().filter(p -> p.getId() == id).findFirst().orElse(null);

        if (player != null) {
            PlayMode.startWithPlayer(player);
        }
    
	}
	
	/**
     * Opens the Setting Mode window from Play Mode.
     *
     * @param parentFrame the current frame (expected to be PlayMode)
     * @param settingsButton the button that triggered the settings action
     */
    public void openSettingMode(JFrame parentFrame, JButton settingsButton) {

        if (parentFrame instanceof PlayMode) {
            SettingMode settings = new SettingMode((PlayMode) parentFrame);
            settings.openSettingMode(settingsButton);
        }

    }

}