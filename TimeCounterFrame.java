import javax.swing.*;
import java.awt.*;

public class TimeCounterFrame extends JFrame {

    private JLabel timeLabel;
    private TimeCounter timeCounter;

    public TimeCounterFrame() {
        setTitle("Time Counter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(200, 100));

        timeLabel = new JLabel("00:00");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(timeLabel, BorderLayout.CENTER);

        timeCounter = new TimeCounter();

        pack();
        setLocationRelativeTo(null);
    }

    public void start() {
        timeCounter.start();
    }

    public void stop() {
        timeCounter.stop();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TimeCounterFrame frame = new TimeCounterFrame();
                frame.setVisible(true);
                frame.start(); // Start the time counter
            }
        });
    }
}