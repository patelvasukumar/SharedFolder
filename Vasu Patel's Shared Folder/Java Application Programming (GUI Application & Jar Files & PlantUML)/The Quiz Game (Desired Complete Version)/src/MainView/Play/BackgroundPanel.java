package MainView.Play;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
   
	private static final long serialVersionUID = 1L;
	private Image img;

    public BackgroundPanel(String path) {
        img = new ImageIcon(path).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}