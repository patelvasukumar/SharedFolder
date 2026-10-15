package util;

import java.awt.Color;

/**
 * Utility class responsible for managing application themes.
 *
 * Supports LIGHT and DARK themes and provides color configurations
 * for different UI components such as option buttons and question areas.
 * 
 */
public class ThemeManager {
	
	/**
	 * Private constructor to prevent instantiation.
	 */
	private ThemeManager() {}
	
	/**
     * Represents available UI themes.
     */
    public enum Theme {

        /** Light theme with bright UI colors */
        LIGHT,

        /** Dark theme with darker UI colors */
        DARK
    }
    
    /** Currently active theme */
    private static Theme current = Theme.LIGHT;
    
    /**
     * Toggles between LIGHT and DARK themes.
     */
    public static void toggleTheme() {
        current = (current == Theme.LIGHT) ? Theme.DARK : Theme.LIGHT;
    }
    
    /**
     * Gets the currently active theme.
     *
     * @return current theme
     */
    public static Theme getTheme() {
        return current;
    }
    
    /**
     * Returns background color for option buttons based on theme.
     *
     * @return option background color
     */
    public static Color getOptionBackground() {
        if (current == Theme.LIGHT) return new Color(255, 230, 180);
        return new Color(70, 70, 70);
    }
    
    /**
     * Returns text color for option buttons based on theme.
     *
     * @return option foreground color
     */
    public static Color getOptionForeground() {
        if (current == Theme.LIGHT) return java.awt.Color.BLACK;
        return java.awt.Color.WHITE;
    }
    
    /**
     * Returns background color for question display area based on theme.
     *
     * @return question area background color
     */
    public static Color getQuestionAreaBackground() {
        if (current == Theme.LIGHT) return new Color(25, 118, 210);
        return new Color(20, 20, 20);
    }
    
    /**
     * Returns text color for question display area based on theme.
     *
     * @return question area foreground color
     */
    public static Color getQuestionAreaForeground() {
        if (current == Theme.LIGHT) return java.awt.Color.WHITE;
        return new Color(220, 220, 220);
    }
}