package Network;

import javax.swing.*;
import java.awt.*;

/**
 * ChatPanel provides a simple graphical chat interface
 * for sending and receiving messages in the application.
 */
public class ChatPanel extends JPanel {
	
	/** 
	 * Area where chat messages are displayed 
	 */
    private final JTextArea chatArea = new JTextArea();

    /** 
     * Input field for typing messages 
     */
    private final JTextField input = new JTextField();

    /** 
     * Button used to send a message 
     */
    private final JButton sendBtn = new JButton("Send");

    /** 
     * Callback handler for sending messages 
     */
    private SendHandler handler;

    /**
     * Interface for handling message sending events.
     * Implement this to define what happens when a user sends a message.
     */
    public interface SendHandler {

        /**
         * Called when a message is sent from the chat panel.
         *
         * @param msg the message text entered by the user
         */
        void onSend(String msg);
    }

    /**
     * Default constructor that initializes the chat panel UI,
     * including message area, input field, and send button.
     */
    public ChatPanel() {
        setLayout(new BorderLayout(6, 6));
        setPreferredSize(new Dimension(360, 180));

        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("Dialog", Font.PLAIN, 14));

        JScrollPane scr = new JScrollPane(chatArea);

        JPanel south = new JPanel(new BorderLayout(6, 6));
        south.add(input, BorderLayout.CENTER);
        south.add(sendBtn, BorderLayout.EAST);

        add(scr, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);

        sendBtn.addActionListener(e -> sendText());
        input.addActionListener(e -> sendText());
    }

    /**
     * Sets the handler that will process outgoing chat messages.
     *
     * @param h implementation of SendHandler
     */
    public void setSendHandler(SendHandler h) {
        this.handler = h;
    }

    /**
     * Adds a message to the chat display area.
     *
     * @param msg message to display
     */
    public void addMessage(String msg) {
        chatArea.append(msg + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    /**
     * Clears all messages from the chat display area.
     */
    public void clear() {
        chatArea.setText("");
    }

    /**
     * Sends the message typed in the input field.
     * If a handler is set, it forwards the message to it.
     */
    private void sendText() {
        String msg = input.getText().trim();
        if (!msg.isEmpty() && handler != null) handler.onSend(msg);
        input.setText("");
    }
}