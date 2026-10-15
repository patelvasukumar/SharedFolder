package Network;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A Swing panel that displays player scores in a multiplayer game.
 *
 * This panel maintains a score list and dynamically updates a text area
 * to show player names and their corresponding scores.
 */
public class ScoresPanel extends JPanel {
	/** 
	 * Internal storage of player score. 
	 */
    private Map<Integer, Integer> scores = new LinkedHashMap<>();

    /** 
     * Text area used to display score information. 
     */
    private final JTextArea text = new JTextArea();

    /**
     * Constructs a ScoresPanel UI component.
     *
     * Initializes layout, styling, and score display area.
     */
    public ScoresPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(220, 180));
        setBorder(BorderFactory.createLineBorder(Color.RED, 3));

        text.setEditable(false);
        text.setFont(new Font("Dialog", Font.PLAIN, 14));
        add(new JScrollPane(text), BorderLayout.CENTER);
    }

    /**
     * Updates the score display using only score data.
     *
     * @param sc map of player IDs to scores
     */
    public void updateScores(Map<Integer, Integer> sc) {
        updateScores(sc, null);
    }

    /**
     * Updates the score display using scores and player names.
     *
     * If names are provided, they are displayed instead of default "Player X" labels.
     *
     * @param sc map of player IDs to scores
     * @param names optional map of player names
     */
    public void updateScores(Map<Integer, Integer> sc, Map<Integer, String> names) {
        this.scores = new LinkedHashMap<>(sc);
        StringBuilder sb = new StringBuilder("Player's Score\n");
        for (Map.Entry<Integer, Integer> e : scores.entrySet()) {
            if (e.getKey() == 0) continue;
            String label = (names != null && names.containsKey(e.getKey()))
                    ? names.get(e.getKey())
                    : "Player " + e.getKey();
            sb.append(label).append(" : ").append(e.getValue()).append('\n');
        }
        text.setText(sb.toString());
    }

    /**
     * Sets a custom status line in the score display area.
     *
     * Replaces the current content with the provided message.
     *
     * @param line status text to display
     */
    public void setStatusLine(String line) {
        text.setText(line == null ? "" : line);
    }
}