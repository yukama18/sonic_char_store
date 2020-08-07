package ui.panels;

import ui.CardLayoutGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoinTossPanel extends JPanel implements ActionListener {
    private CardLayoutGUI frame;

    private JPanel unTossed;
    private JPanel tossed;

    private JLabel instr;
    private JButton coinToss;

    public CardLayout cl;

    int amountGenerated;

    public CoinTossPanel(CardLayoutGUI frame) {
        this.frame = frame;
        run();
    }

    private void run() {
        initCards();
        cl.show(this,"base");
    }

    // EFFECTS: initializes cards and adds to card layout panel
    public void initCards() {
        unTossed();
        tossed = new JPanel();

        setLayout(new CardLayout());
        add(unTossed, "base");
        add(tossed, "toss");

        cl = (CardLayout) getLayout();
    }

    // EFFECTS: sets up panel before coin toss
    public void unTossed() {
        unTossed = new JPanel();
        unTossed.setLayout(new BoxLayout(unTossed,BoxLayout.Y_AXIS));

        instr = new JLabel("  You will be randomly generated 600 to 3000 coins upon toss.");
        coinToss = new JButton("Toss");

//        coinToss.setActionCommand("tossed");
        coinToss.addActionListener(this);

        unTossed.add(instr);
        unTossed.add(coinToss);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tossed();
        cl.show(this,"toss");
    }

    private void tossed() {
        tossed.setLayout(new BoxLayout(tossed,BoxLayout.Y_AXIS));

        amountGenerated = randomGenerate();
        frame.incrementCoins(amountGenerated);

        ImageIcon coinImage = new ImageIcon("./data/cointoss.gif");
        JLabel image = new JLabel(coinImage);

        String toPrint = "You have been generated " + amountGenerated + " coins.";
        JLabel instr = new JLabel(toPrint);

        tossed.removeAll();
        tossed.add(image);
        tossed.add(instr);
        revalidate();
    }

    // EFFECTS: generate a random integer between 3000 and 600
    private int randomGenerate() {
        int amountGenerated = 0;
        int maxCoins = 3000;
        int minCoins = 600;

        amountGenerated = (int) (Math.random() * (maxCoins - minCoins)) + minCoins;

        return amountGenerated;
    }
}
