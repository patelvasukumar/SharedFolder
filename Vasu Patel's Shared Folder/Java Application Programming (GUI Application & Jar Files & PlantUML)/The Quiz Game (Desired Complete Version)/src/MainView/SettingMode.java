package MainView;

import MainView.Play.PlayMode;
import util.LanguageManager;
import util.ThemeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingMode {

    private final PlayMode parent;

    public SettingMode(PlayMode parent) {
        this.parent = parent;
    }

    public void openSettingMode(Component invoker) {

        JDialog d = new JDialog(
                (Frame) SwingUtilities.getWindowAncestor(invoker),
                LanguageManager.t("Settings"),
                true
        );

        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setSize(420, 260);
        d.setLocationRelativeTo(invoker);
        d.setResizable(false);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel langRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 6));
        langRow.setOpaque(false);

        JLabel langLabel = new JLabel(LanguageManager.t("Language") + ":");

        String[] langs = {"English", "हिन्दी"};
        JComboBox<String> langCombo = new JComboBox<>(langs);
        langCombo.setSelectedIndex(LanguageManager.isEnglish() ? 0 : 1);

        langRow.add(langLabel);
        langRow.add(langCombo);
  
        JPanel themeRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 6));
        themeRow.setOpaque(false);

        JLabel themeLabel = new JLabel(LanguageManager.t("Theme") + ":");

        JButton themeBtn = new JButton(
                ThemeManager.getTheme() == ThemeManager.Theme.LIGHT
                        ? LanguageManager.t("Switch to Dark")
                        : LanguageManager.t("Switch to Light")
        );

        themeRow.add(themeLabel);
        themeRow.add(themeBtn);

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 6));
        btnRow.setOpaque(false);

        JButton applyBtn = new JButton(LanguageManager.t("Apply"));
        JButton closeBtn = new JButton(LanguageManager.t("Close"));

        btnRow.add(applyBtn);
        btnRow.add(closeBtn);

        content.add(langRow);
        content.add(themeRow);
        content.add(Box.createVerticalStrut(6));
        content.add(btnRow);

        themeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
            	ThemeManager.toggleTheme();
                
            	themeBtn.setText(
                        ThemeManager.getTheme() == ThemeManager.Theme.LIGHT
                                ? LanguageManager.t("Switch to Dark")
                               : LanguageManager.t("Switch to Light")
                );
                
                SwingUtilities.updateComponentTreeUI(parent);
                parent.applyTheme();
                
            }
             
        });

        applyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean setEnglish = langCombo.getSelectedIndex() == 0;
                LanguageManager.setEnglish(setEnglish);
                parent.reloadUI();
                parent.applyTheme();
                d.dispose();
            }
        });

        closeBtn.addActionListener(e -> d.dispose());

        Font nirmalaFont = new Font("Nirmala UI", Font.PLAIN, 14);
        setFontRecursive(content, nirmalaFont);

        langCombo.setRenderer(new DefaultListCellRenderer() {
            
			private static final long serialVersionUID = 1L;

			@Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component comp = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                comp.setFont(nirmalaFont);
                return comp;
            }
        });

        d.setContentPane(content);
        d.setVisible(true);
        
    }

    private void setFontRecursive(Component c, Font f) {
        
    	c.setFont(f);
    
        if (c instanceof Container) {
        
        	for (Component child : ((Container) c).getComponents()) {
                setFontRecursive(child, f);
            }
           
        }

    }

}