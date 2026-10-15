/*
 * Program Name : CET-CS [LEVEL-4]
 * Course Name : Java Application Programming
 * Team Members and Student Number : 1. Vasu Patel (041153940) 
 * 									 2. Kavya Vasani (041163941)
 * Lab Professor Name : PAULO SOUSA
 * 
 */

import javax.swing.*;
import java.awt.*;

public class SettingMode {

    private JFrame parentFrame;
    private JPanel slidePanel;
    private boolean isOpen = false;
    private int currentLanguage = 0;
    private int currentTheme = 0;


    public SettingMode(JFrame frame) {
        this.parentFrame = frame;
        createSlidePanel();
    }

    private void createSlidePanel() {

        slidePanel = new JPanel(new GridLayout(2, 1, 10, 10));
        slidePanel.setBackground(new Color(30, 30, 30));
        slidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        slidePanel.setSize(260,120);
        slidePanel.setVisible(false);

        JButton languageButton = createSettingButton(" 🌐 Change Language");
        JButton themeButton = createSettingButton(" 🎨 Change Look & Feel");

        languageButton.addActionListener(e -> toggleLanguage());
        themeButton.addActionListener(e -> toggleTheme());
        
        slidePanel.add(languageButton);
        slidePanel.add(themeButton);

        parentFrame.getLayeredPane().add(slidePanel, JLayeredPane.POPUP_LAYER);
    
    }
    
    private void toggleLanguage() {

    	if (currentLanguage == 0) {
    	    currentLanguage = 1;
    	} else {
    	    currentLanguage = 0;
    	}

        updateBottomPanel(currentLanguage);
    
    }
    
    private void toggleTheme() {

        if (currentTheme == 0) {
            currentTheme = 1;
        } else {
            currentTheme = 0;
        }

        updateBottomPanel(currentLanguage);
    }

    private void updateBottomPanel(int language) {

      
        Container contentPane = parentFrame.getContentPane();
        Component comp = contentPane.getComponent(0);

        JLabel bgLabel = (JLabel) comp;
        
        BorderLayout layout = (BorderLayout) bgLabel.getLayout();
        Component oldBottom = layout.getLayoutComponent(BorderLayout.SOUTH);

        if (oldBottom != null) {
            bgLabel.remove(oldBottom);
        }

        QuantumMillionaire qm = new QuantumMillionaire();

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        qm.questionDisplay("", bottomPanel, language, currentTheme);
        qm.optionButtonsGrid(bottomPanel, language, currentTheme);
        qm.addConfirmButton(bottomPanel, language);

        bgLabel.add(bottomPanel, BorderLayout.SOUTH);

        parentFrame.revalidate();
        parentFrame.repaint();
        
    }

    public void openSettingMode(JButton settingsButton) {

        Point btnLocation = SwingUtilities.convertPoint(
                settingsButton.getParent(),
                settingsButton.getLocation(),
                parentFrame.getLayeredPane()
        );

        int startX = btnLocation.x;
        int startY = btnLocation.y + settingsButton.getHeight();

        if (!isOpen) {
            slidePanel.setLocation(startX, startY - 120);
            slidePanel.setVisible(true);
            slideDown(startY);
        } else {
            slideUp(startY);
        }

        isOpen = !isOpen;
        
    }

    private void slideDown(int targetY) {

        Timer timer = new Timer(10, null);
        timer.addActionListener(e -> {
            int y = slidePanel.getY();
            if (y < targetY) {
                slidePanel.setLocation(slidePanel.getX(), y + 10);
            } else {
                slidePanel.setLocation(slidePanel.getX(), targetY);
                timer.stop();
            }
        });
        
        timer.start();
    
    }

    private void slideUp(int targetY) {

        Timer timer = new Timer(10, null);
        timer.addActionListener(e -> {
            int y = slidePanel.getY();
            if (y > targetY - 120) {
                slidePanel.setLocation(slidePanel.getX(), y - 10);
            } else {
                slidePanel.setVisible(false);
                timer.stop();
            }
        });
        
        timer.start();
    }

    private JButton createSettingButton(String text) {
        
    	JButton button = new JButton(text);
    	button.setFont(new Font("Dialog", Font.BOLD, 16));
    	button.setFocusPainted(false);
    	button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	button.setBackground(new Color(70, 70, 70));
    	button.setForeground(Color.WHITE);
    	button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        return button;
    
    }

}
