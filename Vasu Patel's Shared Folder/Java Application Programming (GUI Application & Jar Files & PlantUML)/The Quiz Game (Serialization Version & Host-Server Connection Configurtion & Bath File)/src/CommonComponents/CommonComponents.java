package CommonComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import util.ThemeManager;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * The CommonComponents class provides reusable UI components
 * such as buttons, labels, and dialogs with consistent styling.
 * It extends the CommonComponentsAbstract class and implements
 * its abstract methods.
 */
public class CommonComponents extends CommonComponentsAbstract {

	/**
     * Creates a standard styled button with predefined font,
     * color, border, and cursor properties.
     *
     * @param text the text to display on the button
     * @return a styled JButton instance
     */
    @Override
    public JButton createButton(String text) {

        JButton button = new JButton(text);
        button.setFont(new Font("Dialog", Font.BOLD, 24));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setBackground(new Color(255,180,0));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setMaximumSize(new Dimension(1000,600));

        return button;
    }

    /**
     * Creates a label with predefined font, color, and alignment.
     *
     * @param text the text to display on the label
     * @return a styled JLabel instance
     */
    @Override
    public JLabel createLabel(String text) {

        JLabel label = new JLabel(text);
        label.setForeground(Color.YELLOW);
        label.setFont(new Font("Dialog", Font.BOLD, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        return label;
    }

    /**
     * Creates a button with an icon instead of text.
     *
     * @param imageName the file path or name of the image icon
     * @return a JButton containing the specified icon
     */
    @Override
    public JButton iconButton(String imageName) {

        JButton button = new JButton(new ImageIcon(imageName));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    /**
     * Creates an option button styled according to the current theme.
     *
     * @param text the text to display on the button
     * @param theme the theme identifier (currently not directly used in method)
     * @return a themed JButton instance
     */
    @Override
    public JButton optionButton(String text, int theme) {

        JButton button = new JButton(text);
        button.setFont(new Font("Nirmala UI", Font.BOLD, 22));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBackground(ThemeManager.getOptionBackground());
        button.setForeground(ThemeManager.getOptionForeground());
        button.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK,2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }
    
    /**
     * Creates a styled button with bold font and custom background.
     *
     * @param text the text to display on the button
     * @return a styled JButton instance
     */
    @Override
    public JButton styledButton(String text) {

        JButton button = new JButton(text);
        button.setFont(new Font("Nirmala UI", Font.BOLD, 24));
        button.setFocusPainted(false);
        button.setBackground(new java.awt.Color(255,180,0));
        button.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK,3));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    /**
     * Displays a modal dialog containing scrollable text.
     *
     * @param parent the parent JFrame for the dialog
     * @param title the title of the dialog window
     * @param text the content to display inside the dialog
     */
    public void showTextDialog(JFrame parent, String title, String text) {

        JDialog d = new JDialog(parent, title, true);

        JTextArea area = new JTextArea(text);
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));

        d.getContentPane().add(new JScrollPane(area), BorderLayout.CENTER);

        d.setSize(600, 400);
        d.setLocationRelativeTo(parent);
        d.setVisible(true);
    }

}