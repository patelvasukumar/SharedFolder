package CommonComponents;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Abstract class that defines methods for creating
 * reusable UI components such as buttons, labels, and dialogs.
 */
public abstract class CommonComponentsAbstract {

	/**
	 * Default constructor for CommonComponentsAbstract.
	 */
	public CommonComponentsAbstract() {
	    super();
	}
	/**
     * Creates a button with given text.
     * @param text the button label
     * @return JButton instance
     */
    public abstract JButton createButton(String text);

    /**
     * Creates a label with given text.
     * @param text the label text
     * @return JLabel instance
     */
    public abstract JLabel createLabel(String text);   

    /**
     * Creates a button with an icon.
     * @param imageName path of the image
     * @return JButton with icon
     */
    public abstract JButton iconButton(String imageName);

    /**
     * Creates a themed option button.
     * @param text button text
     * @param theme theme identifier
     * @return JButton instance
     */
    public abstract JButton optionButton(String text, int theme);

    /**
     * Creates a styled button.
     * @param text button text
     * @return JButton instance
     */
    public abstract JButton styledButton(String text);

    /**
     * Displays a dialog with text.
     * @param parent parent frame
     * @param title dialog title
     * @param text dialog content
     */
    public abstract void showTextDialog(JFrame parent, String title, String text);

}