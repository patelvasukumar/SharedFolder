package MainView.Play;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The CountdownTimer class manages a count down timer using
 * Swing's Timer. It updates a JLabel with the remaining time
 * and triggers a callback when time runs out.
 */
public class CountdownTimer {

	 /**
     * Total time in seconds 180 seconds 
     */
    private final int totalSeconds = 180;
    
    /**
     * Remaining time in seconds.
     */
    private int remainingSeconds;

    /**
     * Swing timer used to update the countdown every second.
     */
    private Timer swingTimer;
    
    /**
     * Label used to display the timer.
     */
    private JLabel timerLabel;
    
    /**
     * Callback executed when time runs out.
     */
    private Runnable onTimeUp;

    /**
     * Constructs a CountdownTimer with a display label and callback.
     *
     * @param displayLabel the JLabel used to display remaining time
     * @param onTimeUpCallback the action to perform when time reaches zero
     */
    public CountdownTimer(JLabel displayLabel, Runnable onTimeUpCallback) {

        this.timerLabel = displayLabel;
        this.onTimeUp = onTimeUpCallback;

        remainingSeconds = totalSeconds;
        timerLabel.setText(formatTime(remainingSeconds));

        swingTimer = new Timer(1000, new ActionListener() {
        	
        	/**
             * Updates the timer every second and triggers
             * the callback when time reaches zero.
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                remainingSeconds--;

                timerLabel.setText(formatTime(remainingSeconds));

                if (remainingSeconds <= 0) {

                    stop();

                    if (onTimeUp != null) {
                        onTimeUp.run();
                    }
                }
            }
        });
    }

    /**
     * Starts the count down timer from the beginning.
     */
    public void start() {

        remainingSeconds = totalSeconds;
        timerLabel.setText(formatTime(remainingSeconds));
        swingTimer.start();
    }

    /**
     * Stops the countdown timer.
     */
    public void stop() {

        if (swingTimer.isRunning()) {
            swingTimer.stop();
        }
    }

    /**
     * Resets the timer to the initial value without starting it.
     */
    public void reset() {

        stop();
        remainingSeconds = totalSeconds;
        timerLabel.setText(formatTime(remainingSeconds));
    }

    /**
     * Restarts the timer from the beginning.
     */
    public void restart() {

        reset();
        swingTimer.start();
    }

    /**
     * Formats seconds into MM:SS format.
     *
     * @param seconds the total seconds to format
     * @return formatted time string (MM:SS)
     */
    private String formatTime(int seconds) {

        int min = seconds / 60;
        int sec = seconds % 60;

        return String.format("%02d:%02d", min, sec);
    }
}