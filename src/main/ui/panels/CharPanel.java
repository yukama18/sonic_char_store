package ui.panels;

import model.Character;
import model.characterlist.CharList;
import ui.CardLayoutGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// panel for characters

public class CharPanel extends JPanel implements ActionListener {
    CardLayoutGUI frame;
    CharList cl;

    JPanel notChosen;
    JPanel charDisplay;

    public CardLayout cardLayout;

    // EFFECTS: displays all characters available
    public CharPanel(CharList cl, CardLayoutGUI frame) {
        this.frame = frame;
        this.cl = cl;

        run();
    }

    // MODIFIES: this
    // EFFECTS: initializes card layout and shows panel when no characters chosen
    private void run() {
        initCards();
        cardLayout.show(this, "none");
    }

    // MODIFIES: this
    // EFFECTS: initializes panel for no characters chosen, adds to card layout
    private void initCards() {
        notChosen = new JPanel();
        notChosen.setLayout(new BoxLayout(notChosen, BoxLayout.Y_AXIS));
        charDisplay = new JPanel();
        charDisplay.setBackground(Color.blue);
        charDisplay.setLayout(new GridLayout(0, 3));

        notChosen();

        setLayout(new CardLayout());
        add(notChosen, "none");

        cardLayout = (CardLayout) getLayout();
    }

    // MODIFIES: this
    // EFFECTS: sets up updated notChosen panel, displays available characters
    private void notChosen() {
        notChosen.removeAll();
        charDisplay.removeAll();

        displayChar("sonic", cl, charDisplay);
        displayChar("tails", cl, charDisplay);
        displayChar("knuckles", cl, charDisplay);
        displayChar("amy", cl, charDisplay);
        displayChar("eggman", cl, charDisplay);

        notChosen.add(displayInstr(cl));
        notChosen.add(charDisplay);
        notChosen.revalidate();
    }

    // EFFECTS: displays availability status of character in store
    private void displayChar(String charName, CharList cl, JPanel panel) {
        if (cl.containsChar(charName)) {
            JButton charButton = getButton(charName);
            charButton.setActionCommand(charName);
            charButton.addActionListener(this);

            panel.add(charButton);
            panel.revalidate();
        } else {
            if (cl.getListName().equals("your characters")) {
                JLabel notPurchased = new JLabel("Not yet purchased!");
                notPurchased.setForeground(Color.lightGray);
                notPurchased.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(notPurchased);
                panel.revalidate();
            } else {
                JLabel alreadyPurchased = new JLabel("Already purchased!");
                alreadyPurchased.setForeground(Color.lightGray);
                alreadyPurchased.setHorizontalAlignment(SwingConstants.CENTER);
                panel.add(alreadyPurchased);
                panel.revalidate();
            }
        }
    }

    // EFFECTS: displays instructions according to button pressed
    private JLabel displayInstr(CharList cl) {
        String toPrint = "";

        if (cl.getListName().equals("your characters")) {
            toPrint = "Here are all the characters that you currently own. Click to view or power up. ";
        } else {
            toPrint = "Here are all available characters. Each costs 1000 coins. Click to purchase.";
        }

        JLabel print = new JLabel(toPrint);
        print.setAlignmentX(CENTER_ALIGNMENT);

        return print;
    }

    // REQUIRES: valid charName existent in CharList
    // EFFECTS: returns a button with image of charName
    private JButton getButton(String charName) {
        JButton charButton = null;
        ImageIcon charIcon = new ImageIcon("./data/char/" + charName + ".png");
        charButton = new JButton(charName, charIcon);
        return charButton;
    }

    // EFFECTS: purchases character or upgrades character
    @Override
    public void actionPerformed(ActionEvent e) {
        if (cl.getListName().equals("all characters available")) {
            attemptPurchase(e);
        } else {
            viewChar(e);
        }
    }

    // EFFECTS: displays chosen character's skill and power up cost
    private void viewChar(ActionEvent e) {
        Character selected = cl.getChar(e.getActionCommand());

        JPanel chosenChar = new SkillPanel(selected, cl, frame);
        add(chosenChar, e.getActionCommand());

        cardLayout.show(this,e.getActionCommand());
    }

    // EFFECTS: attempts to purchase chosen character
    private void attemptPurchase(ActionEvent e) {
        if (frame.coins >= 1000) {
            buyChar(e.getActionCommand());
        } else {
            JOptionPane insufficient = new JOptionPane();
            insufficient.showMessageDialog(frame,"Sorry, insufficient coins!");
        }
    }

    // EFFECTS: purchases chosen character
    private void buyChar(String name) {
        if (cl.containsChar(name)) {
            Character toAdd = cl.getChar(name);
            frame.yourChar.addChar(toAdd);
            frame.updateCoins(-1000);

            notChosen();
            popUpMessage();
        }
    }

    // EFFECTS: prints pop up message when purchasing or powering up character
    private void popUpMessage() {
        if (cl.getListName().equals("all characters available")) {
            // print "You have successfully purchased this character. Power up in 'Your characters owned'.
        }
    }
}
