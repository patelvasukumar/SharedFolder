package MainView;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import CommonComponents.CommonComponents;
import Controller.Play.PlayController;
import MainView.Design.DesignMode;
import Model.Design.Player;
import Controller.Design.PlayerController;
import Model.Exception.GameException;

public class SplashScreen extends JFrame {
    
	private static final long serialVersionUID = 1L;
    private CommonComponents components = new CommonComponents();
    private PlayController controller = new PlayController();
    private PlayerController playerController = new PlayerController();

    public SplashScreen() {
       
    	setTitle("Splash Screen");
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon bg = new ImageIcon("images/splash.jpeg");
        JLabel background = new JLabel(bg);
        background.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        JLabel creators = components.createLabel("Created by Vasu and Kavya");
        creators.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(creators);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton playButton = components.createButton("PLAY MODE");
        JButton designButton = components.createButton("DESIGN MODE");
        JButton compareButton = components.createButton("PLAYERS COMPARISON"); 

        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        designButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        compareButton.setAlignmentX(Component.CENTER_ALIGNMENT);

       
        playButton.setVisible(false);
        designButton.setVisible(false);
        compareButton.setVisible(false);

        bottomPanel.add(playButton);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomPanel.add(designButton);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomPanel.add(compareButton);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        background.add(bottomPanel, BorderLayout.SOUTH);
        add(background);
        setVisible(true);
        
        new Timer(3000, e -> {
            creators.setVisible(false);
            playButton.setVisible(true);
            designButton.setVisible(true);
            compareButton.setVisible(true);
            ((Timer) e.getSource()).stop();
        }).start();

        playButton.addActionListener(e -> {
            try {
                controller.startPlayMode();
                dispose();
            } catch (GameException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "No Players",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        });

        designButton.addActionListener(e -> {
            dispose();
            new DesignMode();
        });

        
        compareButton.addActionListener(e -> {
        	
            List<Player> players = new ArrayList<>(playerController.getPlayers());
            players.sort((a, b) -> Integer.compare(b.getCurrentPoint(), a.getCurrentPoint()));
            if (players.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No players available.", "Players Comparison", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (Player p : players) {
                sb.append(p.getName()).append(" : ").append(p.getCurrentPoint()).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Players Comparison (by current points)", JOptionPane.INFORMATION_MESSAGE);
        });
    }
    
}