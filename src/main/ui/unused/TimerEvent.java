package ui.unused;

import ui.unused.CharStoreGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerEvent implements ActionListener {
    private CharStoreGUI frame;
    private Timer timer;
    private JLabel coinTossGif;
    private JPanel display;

    public TimerEvent(CharStoreGUI frame) {
        this.frame = frame;
        timer = new Timer(3050, this);
        timer.setRepeats(false);
        run();
        timer.start();
    }

    private void run() {
        display = new JPanel();
        display.setLayout(new BoxLayout(display, BoxLayout.Y_AXIS));

        String toPrint = "You will be randomly generated a number of coins between 600 and 3000 upon toss.";
        JLabel instr = new JLabel(toPrint);

        coinTossGif = new JLabel(new ImageIcon("./data/cointoss.gif"));

        display.add(instr);
        display.add(coinTossGif);

        frame.getContentPane().add(BorderLayout.CENTER, display);
        frame.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        coinTossGif.setVisible(false);

        frame.revalidate();
    }
}
