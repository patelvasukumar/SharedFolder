package MainView.Design;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import CommonComponents.CommonComponents;
import MainView.SplashScreen;

/**
 * The DesignMode class represents the UI for managing design-related features
 * of the application. It allows users to navigate to Player Editor and
 * Question Editor screens.
 */
public class DesignMode extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Common UI components helper used for consistent styling.
     */
    private CommonComponents components = new CommonComponents();

    /**
     * Constructs the DesignMode window and initializes all UI components,
     * layout, and event handlers.
     */
    public DesignMode() {

        setTitle("Design Mode");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        JPanel background = new JPanel(new BorderLayout());
        background.setBackground(new Color(25, 118, 210)); 
        background.setOpaque(true);
        add(background);

      
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false); 

        JButton home = components.createButton("HOME");
        home.setPreferredSize(new Dimension(120, 40));

        topPanel.add(home);
        background.add(topPanel, BorderLayout.NORTH);

      
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false); 
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        Dimension btnSize = new Dimension(300, 80);

        JButton playerEditor = components.styledButton("PLAYER EDITOR");
        playerEditor.setMaximumSize(btnSize);
        playerEditor.setPreferredSize(btnSize);
        playerEditor.setAlignmentX(JButton.CENTER_ALIGNMENT);

        JButton questionEditor = components.styledButton("QUESTION EDITOR");
        questionEditor.setMaximumSize(btnSize);
        questionEditor.setPreferredSize(btnSize);
        questionEditor.setAlignmentX(JButton.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(playerEditor);
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(questionEditor);
        centerPanel.add(Box.createVerticalGlue());

        background.add(centerPanel, BorderLayout.CENTER);

        /**
         * Opens the Player Editor window.
         */
        playerEditor.addActionListener(e -> new PlayerEditor(this));
        
        /**
         * Opens the Question Editor window.
         */
        questionEditor.addActionListener(e -> new QuestionEditor());

        /**
         * Navigates back to the SplashScreen.
         */
        home.addActionListener(e -> {
            dispose();
            new SplashScreen();
        });

        setVisible(true);
    }
}