package MainView.Play;

import javax.swing.*;
import java.awt.*;
import util.LanguageManager;

/**
 * The LifelinePanel class represents a panel containing life line buttons
 * used in Play Mode. These life lines provide special actions that the player
 * can use during game play.
 */
public class LifelinePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    
    /**
     * Button for the Superposition lifeline.
     */
    private JButton superpositionBtn;
    
    /**
     * Button for the Entanglement lifeline.
     */
    private JButton entanglementBtn;
    
    /**
     * Button for the Interference lifeline.
     */
    private JButton interferenceBtn;
    
    /**
     * Constructs the LifelinePanel and initializes lifeline buttons,
     * layout, and event handling.
     *
     * @param play the PlayMode instance used to trigger lifeline actions
     */
    public LifelinePanel(PlayMode play) {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 8, 8)); 

        superpositionBtn = new JButton(LanguageManager.t("SUPERPOSITION"));
        entanglementBtn = new JButton(LanguageManager.t("ENTANGLEMENT"));
        interferenceBtn = new JButton(LanguageManager.t("INTERFERENCE"));

        applyNirmalaFont(superpositionBtn, 14, Font.BOLD);  
        applyNirmalaFont(entanglementBtn, 14, Font.BOLD);
        applyNirmalaFont(interferenceBtn, 14, Font.BOLD);

        superpositionBtn.setPreferredSize(new Dimension(160, 36));
        entanglementBtn.setPreferredSize(new Dimension(160, 36));
        interferenceBtn.setPreferredSize(new Dimension(160, 36));

        superpositionBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        entanglementBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        interferenceBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        /**
         * Action listener for Superposition lifeline.
         */
        superpositionBtn.addActionListener(e -> {
            if (superpositionBtn.isEnabled()) {
                play.useSuperposition();
                superpositionBtn.setEnabled(false);
            }
        });

        /**
         * Action listener for Entanglement lifeline.
         */
        entanglementBtn.addActionListener(e -> {
            if (entanglementBtn.isEnabled()) {
                play.useEntanglement();
                entanglementBtn.setEnabled(false);
            }
        });

        /**
         * Action listener for Interference lifeline.
         */
        interferenceBtn.addActionListener(e -> {
            if (interferenceBtn.isEnabled()) {
                play.useInterference();
                interferenceBtn.setEnabled(false);
            }
        });

        add(superpositionBtn);
        add(entanglementBtn);
        add(interferenceBtn);
    }

    /**
     * Applies the "Nirmala UI" font to a component.
     * Falls back to "Dialog" font if not available.
     *
     * @param c the component to style
     * @param size the font size
     * @param style the font style (e.g., Font.BOLD)
     */
    private void applyNirmalaFont(JComponent c, int size, int style) {
        Font f = new Font("Nirmala UI", style, size);
        if (!f.getFamily().equals("Nirmala UI")) f = new Font("Dialog", style, size);
        c.setFont(f);
    }

    /**
     * Enables or disables all lifeline buttons.
     *
     * @param v true to enable all buttons, false to disable
     */
    public void enableAll(boolean v) {
        superpositionBtn.setEnabled(v);
        entanglementBtn.setEnabled(v);
        interferenceBtn.setEnabled(v);
    }

    /**
     * Reloads button texts based on the current language settings
     * and reapplies the font styling.
     */
    public void reloadTexts() {
        superpositionBtn.setText(LanguageManager.t("SUPERPOSITION"));
        entanglementBtn.setText(LanguageManager.t("ENTANGLEMENT"));
        interferenceBtn.setText(LanguageManager.t("INTERFERENCE"));
        applyNirmalaFont(superpositionBtn, 14, Font.BOLD);
        applyNirmalaFont(entanglementBtn, 14, Font.BOLD);
        applyNirmalaFont(interferenceBtn, 14, Font.BOLD);
    }
    
}