package ui;

import model.characterlist.AllCharList;
import model.characterlist.UserCharList;
import persistence.Reader;
import persistence.Writer;
import ui.panels.CharPanel;
import ui.panels.CoinTossPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Store that allows transactions of characters through graphical user interaction

public class CardLayoutGUI extends JFrame implements ActionListener {
    public static final String CLIST_FILE = "./data/clists.txt";
    public AllCharList allChar;
    public UserCharList yourChar;
    public int coins;
    private JPanel cards;
    private CardLayout cl;


    private JPanel noneClicked;
    private CharPanel allCharClicked;
    private CharPanel yourCharClicked;
    private CoinTossPanel coinTossClicked;

    public CardLayoutGUI() {
        super("Sonic Character Store");
        runStore();
    }

    // MODIFIES: this
    // EFFECTS: runs store
    public void runStore() {
        initChar();
//        loadChar();
        initCards();
        displayOptions();

        setSize(1000, 570);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: initializes cards and adds to card layout panel
    public void initCards() {
        noneClicked = noButtonsPanel();

        cards = new JPanel(new CardLayout());
        cards.add(noneClicked, "base");

        cl = (CardLayout) cards.getLayout();

        getContentPane().add(BorderLayout.CENTER, cards);
    }

    private JPanel noButtonsPanel() {
        JPanel noButtonsClicked = new JPanel();
        noButtonsClicked.setBackground(Color.blue);
        return noButtonsClicked;
    }

    private void displayOptions() {
        displayMenu();
        cl.show(cards, "base");
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

    // EFFECTS: creates menu buttons panel
    private JPanel optionDisplay() {
        // initializes option buttons
        JButton allCharButton = new JButton("All characters available");
        JButton yourCharButton = new JButton("Your characters owned");
        JButton coinTossButton = new JButton("Generate lucky coin toss");
        JButton saveButton = new JButton("Save progress");
        JButton loadButton = new JButton("Load past progress");
        //options display holding JButtons
        JPanel optionDisplay = new JPanel();
        optionDisplay.setLayout(new BoxLayout(optionDisplay, BoxLayout.Y_AXIS));
        optionDisplay.add(allCharButton);
        optionDisplay.add(yourCharButton);
        optionDisplay.add(coinTossButton);
        optionDisplay.add(saveButton);
        optionDisplay.add(loadButton);
        optionDisplay.revalidate();

        setEventHandling(allCharButton, yourCharButton, coinTossButton, saveButton, loadButton);

        return optionDisplay;
    }

    // EFFECTS: adds event-handling to buttons
    private void setEventHandling(JButton allChar, JButton yourChar, JButton coinToss, JButton save, JButton load) {
        allChar.setActionCommand("all");
        allChar.addActionListener(this);

        yourChar.setActionCommand("your");
        yourChar.addActionListener(this);

        coinToss.setActionCommand("toss");
        coinToss.addActionListener(this);

        save.setActionCommand("save");
        save.addActionListener(this);

        load.setActionCommand("load");
        load.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: loads character lists from CLIST_FILE. if file dne, initialize char lists with default values
    private void loadChar() {
        try {
            Reader reader = new Reader(CLIST_FILE);
            yourChar = reader.readList();
            reader.close();
            allChar = new AllCharList(yourChar);
            JOptionPane insufficient = new JOptionPane();
            insufficient.showMessageDialog(this, "Past progress loaded!");
        } catch (FileNotFoundException e) {
            JOptionPane insufficient = new JOptionPane();
            insufficient.showMessageDialog(this,"Sorry, no past progress found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: initializes default list of all characters available and characters user owns
    public void initChar() {
        allChar = new AllCharList();
        yourChar = new UserCharList("your characters");
    }

    // EFFECTS: updates total coins owned by user by updateAmount
    public void updateCoins(int updateAmount) {
        coins += updateAmount;
        displayMenu();
    }

    // EFFECTS: action events for each button in main menu
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("all".equals(e.getActionCommand())) {
            allCharClicked = new CharPanel(allChar, this);
            cards.add(allCharClicked, "all");

            cl.show(cards, "all");
        }
        if ("your".equals(e.getActionCommand())) {
            yourCharClicked = new CharPanel(yourChar, this);
            cards.add(yourCharClicked, "your");

            cl.show(cards, "your");
//            yourCharClicked.cardLayout.show(yourCharClicked, "none");
        }
        if ("toss".equals(e.getActionCommand())) {
            coinTossClicked = new CoinTossPanel(this);
            cards.add(coinTossClicked, "coin");

            cl.show(cards, "coin");
        }
        if ("save".equals(e.getActionCommand())) {
            saveChar();

            JOptionPane insufficient = new JOptionPane();
            insufficient.showMessageDialog(this, "Progress saved!");
        }
        if ("load".equals(e.getActionCommand())) {
            loadChar();
        }
    }

    // EFFECTS: saves state of your character list to CLIST_FILE
    private void saveChar() {
        try {
            Writer writer = new Writer(CLIST_FILE);
            writer.write(yourChar);
            writer.close();
        } catch (IOException e) {
            JOptionPane insufficient = new JOptionPane();
            insufficient.showMessageDialog(this, "Unable to save file!");
        }
    }
}
