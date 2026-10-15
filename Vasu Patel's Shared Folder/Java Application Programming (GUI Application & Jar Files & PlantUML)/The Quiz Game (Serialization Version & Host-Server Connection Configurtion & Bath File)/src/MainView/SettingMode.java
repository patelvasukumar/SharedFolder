package MainView;

import MainView.Play.PlayMode;
import util.LanguageManager;
import util.ThemeManager;

import javax.swing.*;
import java.awt.*;

/**
 * The SettingMode class represents the settings dialog of the application.
 * It allows users to configure language, theme, freeze time option,
 * and network settings during Play Mode.
 */
public class SettingMode {

	/**
     * Reference to the parent PlayMode instance.
     */
    private final PlayMode parent;

    /**
     * Constructs a SettingMode object with the given PlayMode parent.
     *
     * @param parent the PlayMode instance from which settings are accessed
     */
    public SettingMode(PlayMode parent) {
        this.parent = parent;
    }

    /**
     * Opens the settings dialog window.
     *
     * @param invoker the component that triggered the settings dialog
     */
    public void openSettingMode(Component invoker) {
        JDialog d = new JDialog(
                (Frame) SwingUtilities.getWindowAncestor(invoker),
                LanguageManager.t("Settings"),
                true
        );

        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setSize(520, 390);
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

        JPanel freezeRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 6));
        freezeRow.setOpaque(false);
        JLabel freezeLabel = new JLabel("Freeze Time:");
        JLabel freezeState = new JLabel(parent.isFreezeTimeEnabled() ? "ON" : "OFF");
        JButton freezeOn = new JButton("On");
        JButton freezeOff = new JButton("Off");
        freezeRow.add(freezeLabel);
        freezeRow.add(freezeState);
        freezeRow.add(freezeOn);
        freezeRow.add(freezeOff);

        JPanel networkRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 6));
        networkRow.setOpaque(false);
        JLabel netLabel = new JLabel(LanguageManager.t("Network") + ":");
        JButton leftBtn = new JButton();
        JButton rightBtn = new JButton();
        refreshNetworkButtons(leftBtn, rightBtn);
        networkRow.add(netLabel);
        networkRow.add(leftBtn);
        networkRow.add(rightBtn);

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 6));
        btnRow.setOpaque(false);
        JButton applyBtn = new JButton(LanguageManager.t("Apply"));
        JButton closeBtn = new JButton(LanguageManager.t("Close"));
        btnRow.add(applyBtn);
        btnRow.add(closeBtn);

        content.add(langRow);
        content.add(themeRow);
        content.add(freezeRow);
        content.add(networkRow);
        content.add(Box.createVerticalStrut(8));
        content.add(btnRow);

        themeBtn.addActionListener(e -> {
            ThemeManager.toggleTheme();
            themeBtn.setText(
                    ThemeManager.getTheme() == ThemeManager.Theme.LIGHT
                            ? LanguageManager.t("Switch to Dark")
                            : LanguageManager.t("Switch to Light")
            );
            SwingUtilities.updateComponentTreeUI(parent);
            parent.applyTheme();
        });

        freezeOn.addActionListener(e -> {
            parent.toggleFreezeTimeFromSettings(true);
            freezeState.setText("ON");
        });

        freezeOff.addActionListener(e -> {
            parent.toggleFreezeTimeFromSettings(false);
            freezeState.setText("OFF");
        });

        leftBtn.addActionListener(e -> {
            String state = parent.getNetworkState();
            if ("HOST".equals(state) || "CLIENT".equals(state)) {
                parent.disconnectNetwork();
                refreshNetworkButtons(leftBtn, rightBtn);
                return;
            }
            parent.openHostConfiguration();
            d.dispose();
        });

        rightBtn.addActionListener(e -> {
            String state = parent.getNetworkState();
            if ("HOST".equals(state)) {
                parent.openConnectConfiguration();
                d.dispose();
                return;
            }
            if ("CLIENT".equals(state)) {
                parent.openHostConfiguration();
                d.dispose();
                return;
            }
            parent.openConnectConfiguration();
            d.dispose();
        });

        applyBtn.addActionListener(e -> {
            boolean setEnglish = langCombo.getSelectedIndex() == 0;
            LanguageManager.setEnglish(setEnglish);
            parent.reloadUI();
            parent.applyTheme();
            refreshNetworkButtons(leftBtn, rightBtn);
            freezeState.setText(parent.isFreezeTimeEnabled() ? "ON" : "OFF");
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

    /**
     * Updates the labels of network buttons based on the current network state.
     *
     * @param leftBtn the left button for network actions
     * @param rightBtn the right button for network actions
     */
    private void refreshNetworkButtons(JButton leftBtn, JButton rightBtn) {
        String state = parent.getNetworkState();
        if ("HOST".equals(state)) {
            leftBtn.setText("Disconnect");
            rightBtn.setText("Connect to Server");
        } else if ("CLIENT".equals(state)) {
            leftBtn.setText("Disconnect");
            rightBtn.setText("Host");
        } else {
            leftBtn.setText("Host");
            rightBtn.setText("Connect to Server");
        }
    }

    /**
    * Recursively applies a font to a component and all its child components.
    *
    * @param c the component to apply the font to
    * @param f the font to be applied
    */
    private void setFontRecursive(Component c, Font f) {
        c.setFont(f);
        if (c instanceof Container) {
            for (Component child : ((Container) c).getComponents()) {
                setFontRecursive(child, f);
            }
        }
    }
}