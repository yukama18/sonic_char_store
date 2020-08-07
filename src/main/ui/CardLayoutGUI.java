package ui;

import model.characterlist.AllCharList;
import model.characterlist.UserCharList;
import persistence.Reader;
import ui.panels.CharPanel;
import ui.panels.CoinTossPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CardLayoutGUI extends JFrame implements ActionListener {
    public static final String CLIST_FILE = "./data/clists.txt";
    private AllCharList allChar;
    private UserCharList yourChar;
    int coins;
    private JPanel cards;
    private CardLayout cl;


    private JPanel noneClicked;
    private JPanel allCharClicked;
    private JPanel yourCharClicked;
    private CoinTossPanel coinTossClicked;


    public CardLayoutGUI() {
        super("Sonic Character Store");
        runStore();
    }

    // MODIFIES: this
    // EFFECTS: runs store
    public void runStore() {
        loadChar();
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
        allCharClicked = new CharPanel(allChar);
        yourCharClicked = new CharPanel(yourChar);
        coinTossClicked = new CoinTossPanel(this);

        cards = new JPanel(new CardLayout());
        cards.add(noneClicked, "base");
        cards.add(allCharClicked, "all");
        cards.add(yourCharClicked, "your");
        cards.add(coinTossClicked, "coin");

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
        cl.show(cards,"base");
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

    public void incrementCoins(int amountGenerated) {
        coins += amountGenerated;
        displayMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("all".equals(e.getActionCommand())) {
            cl.show(cards,"all");
        }
        if ("your".equals(e.getActionCommand())) {
            cl.show(cards,"your");
        }
        if ("toss".equals(e.getActionCommand())) {
            cl.show(cards,"coin");
            coinTossClicked.cl.show(coinTossClicked,"base");
        }
    }
}
