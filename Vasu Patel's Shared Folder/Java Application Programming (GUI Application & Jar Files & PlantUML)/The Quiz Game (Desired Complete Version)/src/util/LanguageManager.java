package util;

import java.util.HashMap;
import java.util.Map;

public class LanguageManager {
   
    private static boolean english = true;

    private static final Map<String, String> en = new HashMap<>();
    private static final Map<String, String> hi = new HashMap<>();

    static {
        
        en.put("SUPERPOSITION", "SUPERPOSITION");
        en.put("ENTANGLEMENT", "ENTANGLEMENT");
        en.put("INTERFERENCE", "INTERFERENCE");
        en.put("CONFIRM", "CONFIRM");
        en.put("QUIT & TAKE PRIZE", "QUIT & TAKE PRIZE");
        en.put("Time's up!", "Time's up!");
        en.put("Select an option first.", "Select an option first.");
        en.put("You quit the game!", "You quit the game!");
        en.put("You take: ", "You take: ");
        en.put("Prize", "Prize");
        en.put("Wrong answer!", "Wrong answer!");
        en.put("Congratulations", "Congratulations");
        en.put("You completed all questions.", "You completed all questions.");
        en.put("Settings", "Settings");
        en.put("Language", "Language");
        en.put("Theme", "Theme");
        en.put("Switch to Dark", "Switch to Dark");
        en.put("Switch to Light", "Switch to Light");
        en.put("Apply", "Apply");
        en.put("Close", "Close");
        en.put("Entangled (this will count as correct)", "Entangled (this will count as correct)");
        en.put("Interfered suggestion", "Interfered suggestion");

        
        hi.put("SUPERPOSITION", "सुपरपोज़िशन");
        hi.put("ENTANGLEMENT", "एंटैंगलमेंट");
        hi.put("INTERFERENCE", "इंटरफेरेंस");
        hi.put("CONFIRM", "पुष्टि करें");
        hi.put("QUIT & TAKE PRIZE", "छोड़ें और पुरस्कार लें");
        hi.put("Time's up!", "समय समाप्त!");
        hi.put("Select an option first.", "पहले एक विकल्प चुनें।");
        hi.put("You quit the game!", "आपने खेल छोड़ दिया!");
        hi.put("You take: ", "आप लेते हैं: ");
        hi.put("Prize", "इनाम");
        hi.put("Wrong answer!", "गलत उत्तर!");
        hi.put("Congratulations", "बधाई");
        hi.put("You completed all questions.", "आपने सभी प्रश्न पूरे कर लिए हैं।");
        hi.put("Settings", "सेटिंग्स");
        hi.put("Language", "भाषा");
        hi.put("Theme", "थीम");
        hi.put("Switch to Dark", "डार्क पर स्विच करें");
        hi.put("Switch to Light", "लाइट पर स्विच करें");
        hi.put("Apply", "लगू करें");
        hi.put("Close", "बंद करें");
        hi.put("Entangled (this will count as correct)", "एंटैंगल्ड (यह सही माना जाएगा)");
        hi.put("Interfered suggestion", "इंटरफेरेंस संकेत");
    }

    public static String t(String key) {
        if (english) {
            return en.getOrDefault(key, key);
        } else {
            return hi.getOrDefault(key, key);
        }
    }

    public static boolean isEnglish() {
        return english;
    }

    public static void setEnglish(boolean eng) {
        english = eng;
    }
}