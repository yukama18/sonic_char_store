package ui.unused;

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

public class CharStoreGUI extends JFrame implements ActionListener {
    public static final String CLIST_FILE = "./data/clists.txt";
    private AllCharList allChar;
    private UserCharList yourChar;
    int coins;

    public CharStoreGUI() {
        super("Sonic Character Store");
        runStore();
    }

    // MODIFIES: this
    // EFFECTS: runs store
    public void runStore() {
        loadChar();
        displayOptions();

        setSize(1000, 570);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: presents menu options and welcome messages
    private void displayOptions() {
        displayMenu();
        displayNoButtonsPressed();
    }

    private void displayNoButtonsPressed() {
        JPanel initDisplay = new JPanel();
        initDisplay.setBackground(Color.blue);
        getContentPane().add(BorderLayout.CENTER, initDisplay);
        revalidate();
    }

    // EFFECTS: displays main menu options in WEST
    private void displayMenu() {
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.add(topDisplay());
        menu.add(optionDisplay());
        menu.revalidate();

        getContentPane().add(BorderLayout.WEST, menu);
        revalidate();
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
        optionDisplay.revalidate();

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
    private void setEventHandling(JButton allChar, JButton yourChar, JButton coinToss) {
        allChar.setActionCommand("all");
        allChar.addActionListener(this);

        yourChar.setActionCommand("your");
        yourChar.addActionListener(this);

        coinToss.setActionCommand("toss");
        coinToss.addActionListener(this);
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

        fromMenu(e);

        if ("tossed".equals(e.getActionCommand())) {
//            new TimerEvent(this);
            int amountGenerated = randomGenerate();
            coins += amountGenerated;

            String toPrint = "You have been generated " + amountGenerated + " coins.";
            JLabel instr = new JLabel(toPrint);

            displayMenu();
            getContentPane().add(BorderLayout.CENTER, instr);
            revalidate();
        }
    }

    private void fromMenu(ActionEvent e) {
        if ("all".equals(e.getActionCommand())) {
            displayCharacters(allChar);
        } else {
            if ("your".equals(e.getActionCommand())) {
                displayCharacters(yourChar);
            } else {
                if ("toss".equals(e.getActionCommand())) {
                    generateCoins();
                }
            }
        }
    }

    private void generateCoins() {
        JPanel display = new JPanel();
        display.setLayout(new BoxLayout(display, BoxLayout.Y_AXIS));

        String toPrint = "        You will be randomly generated a number of coins between 600 and 3000 upon toss.";
        JLabel instr = new JLabel(toPrint);

//        JButton coinToss = new JButton("Toss", new ImageIcon("./data/cointoss.png"));
        JButton coinToss = new JButton("Toss");
        coinToss.setActionCommand("tossed");
        coinToss.addActionListener(this);

        display.add(instr);
        display.add(coinToss);

        getContentPane().add(BorderLayout.CENTER, display);
        revalidate();
    }

    // EFFECTS: displays all characters available
    private void displayCharacters(CharList cl) {
        JPanel display = new JPanel();
        display.setLayout(new BoxLayout(display, BoxLayout.Y_AXIS));

        JPanel charDisplay = new JPanel();
        charDisplay.setBackground(Color.blue);
        charDisplay.setLayout(new GridLayout(0, 3));

        displayChar("sonic", cl, charDisplay);
        displayChar("tails", cl, charDisplay);
        displayChar("knuckles", cl, charDisplay);
        displayChar("amy", cl, charDisplay);
        displayChar("eggman", cl, charDisplay);

        display.add(displayInstr(cl));
        display.add(charDisplay);
        display.revalidate();

        getContentPane().add(BorderLayout.CENTER, display);
        revalidate();
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

    // EFFECTS: generate a random integer between 3000 and 600
    private int randomGenerate() {
        int amountGenerated = 0;
        int maxCoins = 3000;
        int minCoins = 600;

        amountGenerated = (int) (Math.random() * (maxCoins - minCoins)) + minCoins;

        return amountGenerated;
    }
}
