package ui;

// CLASS LEVEL COMMENT: Store that allows transactions of characters through graphical user interaction

import ui.panels.CharPanel;
import ui.panels.CoinTossPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CharStoreGUI extends JFrame implements ActionListener {

    public ListManager listManager;

    public int coins;

    private JPanel cards;
    private CardLayout cl;

    private JPanel noneClicked;
    private CharPanel allCharClicked;
    private CharPanel yourCharClicked;
    private CoinTossPanel coinTossClicked;

    // EFFECTS: runs the game store application
    public CharStoreGUI() {
        super("Sonic Character Store");
        listManager = new ListManager();
        runStore();
    }

    // MODIFIES: this
    // EFFECTS: runs store
    public void runStore() {
        initChar();
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

    // EFFECTS: initializes panel when no buttons clicked
    private JPanel noButtonsPanel() {
        JPanel noButtonsClicked = new JPanel();
        noButtonsClicked.setBackground(Color.blue);
        return noButtonsClicked;
    }

    // MODIFIES: this
    // EFFECTS: displays main menu options
    private void displayOptions() {
        displayMenu();
        cl.show(cards, "base");
    }

    // MODIFIES: this
    // EFFECTS: initializes main menu options in WEST
    private void displayMenu() {
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));

        menu.add(topDisplay());
        menu.add(optionDisplay());
        menu.revalidate();

        getContentPane().add(BorderLayout.WEST, menu);
        revalidate();
    }

    // EFFECTS: returns panel holding instr + total coins owned
    private JPanel topDisplay() {
        JPanel topDisplay = new JPanel();
        topDisplay.setLayout(new BoxLayout(topDisplay, BoxLayout.Y_AXIS));

        JLabel intro = new JLabel("Hi, welcome to the Sonic character store!");
        JLabel coinsOwned = new JLabel("Coins owned: " + coins);

        topDisplay.add(intro);
        topDisplay.add(coinsOwned);

        return topDisplay;
    }

    // EFFECTS: returns panel holding menu options
    private JPanel optionDisplay() {
        JPanel optionDisplay = new JPanel();
        optionDisplay.setLayout(new BoxLayout(optionDisplay, BoxLayout.Y_AXIS));

        JButton allCharButton = new JButton("All characters available");
        JButton yourCharButton = new JButton("Your characters owned");
        JButton coinTossButton = new JButton("Generate lucky coin toss");
        JButton saveButton = new JButton("Save progress");
        JButton loadButton = new JButton("Load past progress");

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
    // EFFECTS: loads character lists from CLIST_FILE. if file dne, display fail msg
    private void loadChar() {
        listManager.loadChar(this);
    }

    // MODIFIES: this
    // EFFECTS: saves state of your character list to CLIST_FILE
    private void saveChar() {
        listManager.saveChar(this);
    }

    // MODIFIES: this
    // EFFECTS: initializes default list of all characters available and characters user owns
    public void initChar() {
        listManager.initChar();
    }

    // MODIFIES: this
    // EFFECTS: updates total coins owned by user by updateAmount
    public void updateCoins(int updateAmount) {
        coins += updateAmount;
        displayMenu();
    }

    // MODIFIES: this
    // EFFECTS: action events for each button in main menu
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("all".equals(e.getActionCommand())) {
            allCharClicked = listManager.allCharPanel(this); //new CharPanel(allChar, this);
            cards.add(allCharClicked, "all");

            cl.show(cards, "all");
        }
        if ("your".equals(e.getActionCommand())) {
            yourCharClicked = listManager.yourCharPanel(this);  //new CharPanel(yourChar, this);
            cards.add(yourCharClicked, "your");

            cl.show(cards, "your");
        }
        if ("toss".equals(e.getActionCommand())) {
            coinTossClicked = new CoinTossPanel(this);
            cards.add(coinTossClicked, "coin");

            cl.show(cards, "coin");
        }
        if ("save".equals(e.getActionCommand())) {
            saveChar();

            JOptionPane saveSuccess = new JOptionPane();
            saveSuccess.showMessageDialog(this, "Progress saved!");
        }
        if ("load".equals(e.getActionCommand())) {
            loadChar();
        }
    }
}
