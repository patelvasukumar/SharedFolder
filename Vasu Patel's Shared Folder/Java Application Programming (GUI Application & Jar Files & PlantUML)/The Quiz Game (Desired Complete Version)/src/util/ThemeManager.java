package util;

import java.awt.Color;

public class ThemeManager {
    public enum Theme { LIGHT, DARK }
    private static Theme current = Theme.LIGHT;
    public static void toggleTheme() {
        current = (current == Theme.LIGHT) ? Theme.DARK : Theme.LIGHT;
    }
    public static Theme getTheme() {
        return current;
    }
    public static Color getOptionBackground() {
        if (current == Theme.LIGHT) return new Color(255, 230, 180);
        return new Color(70, 70, 70);
    }
    public static Color getOptionForeground() {
        if (current == Theme.LIGHT) return java.awt.Color.BLACK;
        return java.awt.Color.WHITE;
    }
    public static Color getQuestionAreaBackground() {
        if (current == Theme.LIGHT) return new Color(25, 118, 210);
        return new Color(20, 20, 20);
    }
    public static Color getQuestionAreaForeground() {
        if (current == Theme.LIGHT) return java.awt.Color.WHITE;
        return new Color(220, 220, 220);
    }
}