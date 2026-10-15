package CommonComponents;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class CommonComponentsAbstract {

    public abstract JButton createButton(String text);

    public abstract JLabel createLabel(String text);   

    public abstract JButton iconButton(String imageName);

    public abstract JButton optionButton(String text, int theme);

    public abstract JButton styledButton(String text);

    public abstract void showTextDialog(JFrame parent, String title, String text);

}