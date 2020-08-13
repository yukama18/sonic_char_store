package ui.panels;

// CLASS LEVEL COMMENT: generates panel for characters

import model.Character;
import model.characterlist.CharList;
import ui.CharStoreGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharPanel extends JPanel implements ActionListener {
    private CharStoreGUI frame;
    private CharList cl;

    private JPanel notChosen;
    private JPanel charDisplay;

    private CardLayout cardLayout;

    // EFFECTS: displays all characters available
    public CharPanel(CharList cl, CharStoreGUI frame) {
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

    // MODIFIES: this
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
                charNotContained(panel,"Not yet purchased!");
            } else {
                charNotContained(panel, "Already purchased!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: displays correct message to charDisplay
    private void charNotContained(JPanel panel, String message) {
        JLabel notAvailable = new JLabel(message);
        notAvailable.setForeground(Color.lightGray);
        notAvailable.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(notAvailable);
        panel.revalidate();
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
    // EFFECTS: generates a button with image of given character
    private JButton getButton(String charName) {
        JButton charButton = null;
        ImageIcon charIcon = new ImageIcon("./data/char/" + charName + ".png");
        charButton = new JButton(charName, charIcon);
        return charButton;
    }

    // MODIFIES: allChar, yourChar
    // EFFECTS: purchases character or upgrades character
    @Override
    public void actionPerformed(ActionEvent e) {
        if (cl.getListName().equals("all characters available")) {
            attemptPurchase(e);
        } else {
            viewChar(e);
        }
    }

    // MODIFIES: this
    // EFFECTS: displays chosen character's skill and power up cost
    private void viewChar(ActionEvent e) {
        Character selected = cl.getChar(e.getActionCommand());

        JPanel chosenChar = new SkillPanel(selected, cl, frame);
        add(chosenChar, e.getActionCommand());

        cardLayout.show(this,e.getActionCommand());
    }

    // MODIFIES: this
    // EFFECTS: attempts to purchase chosen character
    private void attemptPurchase(ActionEvent e) {
        if (frame.coins >= 1000) {
            buyChar(e.getActionCommand());
        } else {
            JOptionPane insufficient = new JOptionPane();
            insufficient.showMessageDialog(frame,"Sorry, insufficient coins!");
        }
    }

    // MODIFIES: this, allChar, yourChar
    // EFFECTS: purchases chosen character
    private void buyChar(String name) {
        if (cl.containsChar(name)) {
            Character toAdd = cl.getChar(name);
            frame.listManager.yourChar.addChar(toAdd);
            frame.updateCoins(-1000);

            notChosen();

            JOptionPane purchaseSuccess = new JOptionPane();
            purchaseSuccess.showMessageDialog(frame,"You have successfully purchased this character!");
        }
    }
}
