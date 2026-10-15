package MainView.Play;

import javax.swing.*;
import java.awt.*;

/**
 * The BackgroundPanel class is a custom JPanel that displays
 * a background image stretched to fit the panel size.
 */
public class BackgroundPanel extends JPanel {
   
	private static final long serialVersionUID = 1L;
	
	/**
     * Image used as the background.
     */
	private Image img;

	/**
     * Constructs a BackgroundPanel with the specified image path.
     *
     * @param path the file path of the background image
     */
    public BackgroundPanel(String path) {
        img = new ImageIcon(path).getImage();
    }

    /**
     * Paints the background image onto the panel.
     *
     * @param g the Graphics object used for drawing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}