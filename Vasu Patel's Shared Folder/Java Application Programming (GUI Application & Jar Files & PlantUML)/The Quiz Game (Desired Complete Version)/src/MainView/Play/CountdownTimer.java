package MainView.Play;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountdownTimer {

    private final int totalSeconds = 180;
    private int remainingSeconds;

    private Timer swingTimer;
    private JLabel timerLabel;
    private Runnable onTimeUp;

    public CountdownTimer(JLabel displayLabel, Runnable onTimeUpCallback) {

        this.timerLabel = displayLabel;
        this.onTimeUp = onTimeUpCallback;

        remainingSeconds = totalSeconds;
        timerLabel.setText(formatTime(remainingSeconds));

        swingTimer = new Timer(1000, new ActionListener() {
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

    public void start() {

        remainingSeconds = totalSeconds;
        timerLabel.setText(formatTime(remainingSeconds));
        swingTimer.start();
    }

    public void stop() {

        if (swingTimer.isRunning()) {
            swingTimer.stop();
        }
    }

    public void reset() {

        stop();
        remainingSeconds = totalSeconds;
        timerLabel.setText(formatTime(remainingSeconds));
    }

    public void restart() {

        reset();
        swingTimer.start();
    }

    private String formatTime(int seconds) {

        int min = seconds / 60;
        int sec = seconds % 60;

        return String.format("%02d:%02d", min, sec);
    }
}