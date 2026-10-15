package Network;

import javax.swing.*;

/**
 * A dialog window used to connect a player to a multiplayer server.
 * 
 * This dialog allows the user to enter player name, server IP,
 * and port number, and provides options to apply or cancel the connection.
 */
public class NetworkConnectDialog extends JDialog {
	/**
     * Text field for entering player name.
     */
    public final JTextField txtName = new JTextField();

    /**
     * Text field for entering server IP address.
     */
    public final JTextField txtIP = new JTextField();

    /**
     * Text field for entering port number.
     */
    public final JTextField txtPort = new JTextField();

    /**
     * Label used to display network connection status.
     */
    public final JLabel status = new JLabel("Status : Network is not set up");

    /**
     * Button to apply and initiate connection.
     */
    public final JButton btnApply = new JButton("Apply");

    /**
     * Button to close the dialog.
     */
    public final JButton btnClose = new JButton("Close");

    /**
     * Button to cancel the operation.
     */
    public final JButton btnCancel = new JButton("Cancel");

    /**
     * Constructs the NetworkConnectDialog.
     *
     * @param parent the parent frame for this dialog
     */
    public NetworkConnectDialog(JFrame parent) {
        super(parent, "Connect to Server", true);
        setLayout(null);
        setSize(380, 250);
        setResizable(false);

        JLabel l1 = new JLabel("Player Name :");
        l1.setBounds(20, 20, 120, 25);
        txtName.setBounds(140, 20, 190, 25);

        JLabel l2 = new JLabel("Server IP :");
        l2.setBounds(20, 55, 120, 25);
        txtIP.setBounds(140, 55, 190, 25);

        JLabel l3 = new JLabel("Port Number :");
        l3.setBounds(20, 90, 120, 25);
        txtPort.setBounds(140, 90, 190, 25);

        status.setBounds(20, 125, 320, 25);

        btnApply.setBounds(30, 170, 100, 35);
        btnClose.setBounds(140, 170, 100, 35);
        btnCancel.setBounds(250, 170, 100, 35);

        add(l1);
        add(txtName);
        add(l2);
        add(txtIP);
        add(l3);
        add(txtPort);
        add(status);
        add(btnApply);
        add(btnClose);
        add(btnCancel);
    }

    /**
     * Gets the player name entered by the user.
     *
     * @return the trimmed player name
     */
    public String getPlayerName() {
        return txtName.getText().trim();
    }

    /**
     * Gets the server IP entered by the user.
     *
     * @return the trimmed server IP address
     */
    public String getServerIP() {
        return txtIP.getText().trim();
    }

    /**
     * Gets the port number entered by the user.
     *
     * @return the port number as an integer
     * @throws NumberFormatException if the input is not a valid number
     */
    public int getPort() {
        return Integer.parseInt(txtPort.getText().trim());
    }

    /**
     * Updates the status label text.
     *
     * @param text the status message to display
     */
    public void setStatus(String text) {
        status.setText(text);
    }
}