package ui.panels;

import model.characterlist.CharList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharPanel extends JPanel implements ActionListener {


    // EFFECTS: displays all characters available
    public CharPanel(CharList cl) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel charDisplay = new JPanel();
        charDisplay.setBackground(Color.blue);
        charDisplay.setLayout(new GridLayout(0, 3));

        displayChar("sonic", cl, charDisplay);
        displayChar("tails", cl, charDisplay);
        displayChar("knuckles", cl, charDisplay);
        displayChar("amy", cl, charDisplay);
        displayChar("eggman", cl, charDisplay);

        add(displayInstr(cl));
        add(charDisplay);
    }

    // EFFECTS: displays availability status of character in store
    private void displayChar(String charName, CharList cl, JPanel panel) {
        if (cl.containsChar(charName)) {
            panel.add(getButton(charName));
            panel.revalidate();
        } else {
            if (cl.getListName().equals("your characters")) {
                JLabel notPurchased = new JLabel("Not yet purchased!");
                panel.add(notPurchased);
                panel.revalidate();
            } else {
                JLabel alreadyPurchased = new JLabel("Already purchased!");
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
        return new JLabel(toPrint);
    }

    // REQUIRES: valid charName existent in CharList
    // EFFECTS: returns a button with image of charName
    private JButton getButton(String charName) {
        JButton charButton = null;
        if (charName.equals("sonic")) {
            ImageIcon sonicIcon = new ImageIcon("./data/char/sonic.png");
            charButton = new JButton(sonicIcon);
        }
        if (charName.equals("tails")) {
            ImageIcon tailsIcon = new ImageIcon("./data/char/tails.png");
            charButton = new JButton(tailsIcon);
        }
        if (charName.equals("knuckles")) {
            ImageIcon knucklesIcon = new ImageIcon("./data/char/knuckles.png");
            charButton = new JButton(knucklesIcon);
        }
        if (charName.equals("amy")) {
            ImageIcon amyIcon = new ImageIcon("./data/char/amy.png");
            charButton = new JButton(amyIcon);
        }
        if (charName.equals("eggman")) {
            ImageIcon eggmanIcon = new ImageIcon("./data/char/eggman.png");
            charButton = new JButton(eggmanIcon);
        }
        return charButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
