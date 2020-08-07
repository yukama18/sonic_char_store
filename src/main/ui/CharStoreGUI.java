package ui;

import model.characterlist.AllCharList;
import model.characterlist.CharList;
import model.characterlist.UserCharList;
import persistence.Reader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CharStoreGUI extends JFrame implements ActionListener {
    public static final String CLIST_FILE = "./data/clists.txt";
    private AllCharList allChar;
    private UserCharList yourChar;
    private int coins;


    public CharStoreGUI() {
        super("Sonic Character Store");
        runStore();
    }

    // MODIFIES: this
    // EFFECTS: runs store
    public void runStore() {

        loadChar();

        displayOptions();

        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // EFFECTS: displays menu options and welcome messages
    private void displayOptions() {
        displayMenu();
        displayChoice();
    }

    private void displayChoice() {
        JPanel chosenDisplay = new JPanel();
        chosenDisplay.setBackground(Color.blue);

        getContentPane().add(BorderLayout.CENTER, chosenDisplay);
    }

    // EFFECTS: displays menu options in WEST
    private void displayMenu() {
        //main menu panel
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.add(topDisplay());
        menu.add(optionDisplay());

        //add menu to frame WEST
        getContentPane().add(BorderLayout.WEST, menu);
    }

    // EFFECTS: creates menu buttons panel
    private JPanel optionDisplay() {
        // initializes option buttons
        JButton allCharButton = new JButton("All characters available");
        JButton yourCharButton = new JButton("Your characters owned");
        JButton coinTossButton = new JButton("Generate lucky coin toss");
        //options display holding JButtons
        JPanel optionDisplay = new JPanel();
        optionDisplay.setLayout(new BoxLayout(optionDisplay, BoxLayout.Y_AXIS));
        optionDisplay.add(allCharButton);
        optionDisplay.add(yourCharButton);
        optionDisplay.add(coinTossButton);

        setEventHandling(allCharButton, yourCharButton, coinTossButton);

        return optionDisplay;
    }

    // EFFECTS: creates menu top panel
    private JPanel topDisplay() {
        //intro message + coins owned
        JLabel intro = new JLabel("Hi, welcome to the Sonic character store!");
        JLabel coinsOwned = new JLabel("Coins owned: " + coins);
        //top display holding JLabels
        JPanel topDisplay = new JPanel();
        topDisplay.setLayout(new BoxLayout(topDisplay, BoxLayout.Y_AXIS));
        topDisplay.add(intro);
        topDisplay.add(coinsOwned);

        return topDisplay;
    }

    // EFFECTS: adds event-handling to buttons
    private void setEventHandling(JButton allCharButton, JButton yourCharButton, JButton coinTossButton) {
        allCharButton.setActionCommand("all");
        allCharButton.addActionListener(this);

        yourCharButton.setActionCommand("your");
        yourCharButton.addActionListener(this);

        coinTossButton.setActionCommand("toss");
        coinTossButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: loads character lists from CLIST_FILE. if file dne, initialize char lists with default values
    private void loadChar() {
        try {
            Reader reader = new Reader(CLIST_FILE);
            yourChar = reader.readList();
            reader.close();
            allChar = new AllCharList(yourChar);
        } catch (FileNotFoundException e) {
            initChar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: initializes default list of all characters available and characters user owns
    public void initChar() {
        allChar = new AllCharList();
        yourChar = new UserCharList("your characters");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("all".equals(e.getActionCommand())) {
            displayCharacters(allChar);
        } else {
            if ("your".equals(e.getActionCommand())) {
                ;
            } else {
                if ("toss".equals(e.getActionCommand())) {
                    ;
                }
            }
        }
    }

    // EFFECTS: displays all characters available
    private void displayCharacters(CharList cl) {
        JPanel charDisplay = new JPanel();
        charDisplay.setBackground(Color.blue);
        charDisplay.setLayout(new GridLayout(0, 3));
        displayChar("sonic", cl);
        displayChar("tails", cl);
        displayChar("knuckles", cl);
        displayChar("amy", cl);
        displayChar("eggman", cl);

//        charDisplay.add(getButton("sonic"));
//        charDisplay.add(getButton("tails"));
//        charDisplay.add(getButton("knuckles"));
//        charDisplay.add(getButton("amy"));
//        charDisplay.add(getButton("eggman"));

        getContentPane().add(BorderLayout.CENTER, charDisplay);
        revalidate();
    }

    // EFFECTS: displays availability status of character in store
    private void displayChar(String sonic, CharList cl) {



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
}
