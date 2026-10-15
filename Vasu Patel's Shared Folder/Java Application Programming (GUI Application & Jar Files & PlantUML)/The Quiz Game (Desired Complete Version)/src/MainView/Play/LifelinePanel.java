package MainView.Play;

import javax.swing.*;
import java.awt.*;
import util.LanguageManager;

public class LifelinePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JButton superpositionBtn;
    private JButton entanglementBtn;
    private JButton interferenceBtn;
    
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

        superpositionBtn.addActionListener(e -> {
            if (superpositionBtn.isEnabled()) {
                play.useSuperposition();
                superpositionBtn.setEnabled(false);
            }
        });

        entanglementBtn.addActionListener(e -> {
            if (entanglementBtn.isEnabled()) {
                play.useEntanglement();
                entanglementBtn.setEnabled(false);
            }
        });

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

    private void applyNirmalaFont(JComponent c, int size, int style) {
        Font f = new Font("Nirmala UI", style, size);
        if (!f.getFamily().equals("Nirmala UI")) f = new Font("Dialog", style, size);
        c.setFont(f);
    }

    public void enableAll(boolean v) {
        superpositionBtn.setEnabled(v);
        entanglementBtn.setEnabled(v);
        interferenceBtn.setEnabled(v);
    }

    
    public void reloadTexts() {
        superpositionBtn.setText(LanguageManager.t("SUPERPOSITION"));
        entanglementBtn.setText(LanguageManager.t("ENTANGLEMENT"));
        interferenceBtn.setText(LanguageManager.t("INTERFERENCE"));
        applyNirmalaFont(superpositionBtn, 14, Font.BOLD);
        applyNirmalaFont(entanglementBtn, 14, Font.BOLD);
        applyNirmalaFont(interferenceBtn, 14, Font.BOLD);
    }
    
}