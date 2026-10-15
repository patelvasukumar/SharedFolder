package Network;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog window used to configure and host a multiplayer game session.
 * Allows the user to enter player name, port number, and select number of players.
 */
public class NetworkHostDialog extends JDialog {
	
	/** 
	 * Text field for entering player name 
	 */
    public final JTextField txtName = new JTextField();

    /** 
     * Text field for entering port number 
     */
    public final JTextField txtPort = new JTextField();

    /** 
     * Button group for selecting number of players 
     */
    public final ButtonGroup group = new ButtonGroup();

    /** 
     * Radio button for 2 players 
     */
    public final JRadioButton rb2 = new JRadioButton("2");

    /** 
     * Radio button for 4 players
     */
    public final JRadioButton rb4 = new JRadioButton("4");

    /** 
     * Radio button for 8 players 
     */
    public final JRadioButton rb8 = new JRadioButton("8");

    /** 
     * Label to display connection status 
     */
    public final JLabel status = new JLabel("Status : Network is not set up");

    /** 
     * Button to apply configuration 
     */
    public final JButton btnApply = new JButton("Apply");

    /** 
     * Button to close dialog 
     */
    public final JButton btnClose = new JButton("Close");

    /** 
     * Button to cancel operation 
     */
    public final JButton btnCancel = new JButton("Cancel");

    /**
     * Constructs the NetworkHostDialog window.
     *
     * @param parent the parent JFrame for this dialog
     */
    public NetworkHostDialog(JFrame parent) {
        super(parent, "Host Configuration", true);
        setLayout(null);
        setSize(380, 270);
        setResizable(false);

        JLabel l1 = new JLabel("Player Name :");
        l1.setBounds(20, 20, 120, 25);
        txtName.setBounds(140, 20, 190, 25);

        JLabel l2 = new JLabel("Port Number :");
        l2.setBounds(20, 55, 120, 25);
        txtPort.setBounds(140, 55, 190, 25);

        JLabel l3 = new JLabel("Player :");
        l3.setBounds(20, 90, 120, 25);

        rb2.setBounds(140, 90, 40, 25);
        rb4.setBounds(190, 90, 40, 25);
        rb8.setBounds(240, 90, 40, 25);

        group.add(rb2);
        group.add(rb4);
        group.add(rb8);
        rb2.setSelected(true);

        status.setBounds(20, 125, 320, 25);

        btnApply.setBounds(30, 175, 100, 35);
        btnClose.setBounds(140, 175, 100, 35);
        btnCancel.setBounds(250, 175, 100, 35);

        add(l1);
        add(txtName);
        add(l2);
        add(txtPort);
        add(l3);
        add(rb2);
        add(rb4);
        add(rb8);
        add(status);
        add(btnApply);
        add(btnClose);
        add(btnCancel);
    }

    /**
     * Returns the entered player name.
     *
     * @return player name as trimmed string
     */
    public String getPlayerName() {
        return txtName.getText().trim();
    }

    /**
     * Returns the entered port number.
     *
     * @return port number as integer
     */
    public int getPort() {
        return Integer.parseInt(txtPort.getText().trim());
    }

    /**
     * Returns the selected number of players.
     *
     * @return number of players (2, 4, or 8)
     */
    public int getSelectedPlayers() {
        if (rb4.isSelected()) return 4;
        if (rb8.isSelected()) return 8;
        return 2;
    }

    /**
     * Updates the status label text.
     *
     * @param text status message to display
     */
    public void setStatus(String text) {
        status.setText(text);
    }
}