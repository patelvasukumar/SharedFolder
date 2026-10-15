package MainView.Play;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.Font;
import CommonComponents.CommonComponents;
import Model.Design.Player;

/**
 * Utility class used to display the player profile information.
 */
public class ProfileView {
	
	/**
	 * Default constructor for ProfileView.
	 * This class contains only static methods and is not intended to be instantiated.
	 */
	public ProfileView() {}
    private static CommonComponents components = new CommonComponents();

    /**
     * Displays the profile of the given player in a dialog window.
     *
     * @param parent the parent frame for the dialog
     * @param player the player whose profile will be displayed
     * @param currentLevel the current level of the player in the game
     */
    public static void showProfile(JFrame parent, Player player, int currentLevel) {
        if (player == null) {
            JOptionPane.showMessageDialog(
                parent,
                "No player selected.",
                "Profile",
                JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(components.createLabel("PLAYER PROFILE"));
        panel.add(Box.createRigidArea(new Dimension(0,15)));

        JTextArea info = new JTextArea(
            "ID : " + player.getId() + "\n\n" +
            "Name : " + player.getName() + "\n\n" +
            "Gender : " + player.getGender() + "\n\n" +
            "Nationality : " + player.getNationality() + "\n\n" +
            "Current Level : " + currentLevel + "\n\n" +
            "Current Points : " + player.getCurrentPoint() + "\n\n" +
            "Historic (best) Points : " + player.getHistoricPoint()
        );
        
        info.setEditable(false);
        info.setFont(new Font("Dialog", Font.BOLD, 16));
        info.setOpaque(false);
        info.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel.add(info);

        JOptionPane.showMessageDialog(parent, panel, "Profile - " + player.getName(), JOptionPane.INFORMATION_MESSAGE);

    }

}