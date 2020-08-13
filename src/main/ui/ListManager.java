package ui;

// CLASS LEVEL COMMENT: Manages saving/loading/displays of character lists

import model.characterlist.AllCharList;
import model.characterlist.UserCharList;
import persistence.Reader;
import persistence.Writer;
import ui.panels.CharPanel;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ListManager {
    public static final String CLIST_FILE = "./data/clists.txt";

    public AllCharList allChar;
    public UserCharList yourChar;

    private Writer writer;
    private Reader reader;

    public ListManager() {
        initChar();
    }

    // MODIFIES: this, cards
    // EFFECTS: initializes default list of all characters available and characters user owns
    public void initChar() {
        allChar = new AllCharList();
        yourChar = new UserCharList("your characters");
    }

    // MODIFIES: this, cards
    // EFFECTS: loads character lists from CLIST_FILE. if file dne, display fail msg
    public void loadChar(CharStoreGUI frame) {
        try {
            reader = new Reader(CLIST_FILE);
            yourChar = reader.readList();
            reader.close();

            allChar = new AllCharList(yourChar);

            JOptionPane loadSuccess = new JOptionPane();
            loadSuccess.showMessageDialog(frame, "Past progress loaded!");
        } catch (FileNotFoundException e) {
            JOptionPane loadFail = new JOptionPane();
            loadFail.showMessageDialog(frame,"Sorry, no past progress found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this, cards
    // EFFECTS: saves state of your character list to CLIST_FILE
    public void saveChar(CharStoreGUI frame) {
        try {
            writer = new Writer(CLIST_FILE);
            writer.write(yourChar);
            writer.close();
        } catch (IOException e) {
            JOptionPane saveFail = new JOptionPane();
            saveFail.showMessageDialog(frame, "Unable to save file!");
        }
    }

    // MODIFIES: cards
    // EFFECTS: generate panel for all characters available
    public CharPanel allCharPanel(CharStoreGUI frame) {
        return new CharPanel(allChar, frame);
    }

    // MODIFIES: cards
    // EFFECTS: generate panel for your characters owned
    public CharPanel yourCharPanel(CharStoreGUI frame) {
        return new CharPanel(yourChar, frame);
    }
}
