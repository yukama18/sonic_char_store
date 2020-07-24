package model;

import model.Character;

import java.util.ArrayList;

public class CharactersList {
    static final int MAX_SIZE = Character.numOfChar;
    private static final String ALL_CHAR_AVAILABLE = "all characters available";
    Character sonic = new Character("sonic");
    // fields: all the available characters (only diff is name ==> don't need separate Character classes)
    //          static variables

    ArrayList<Character> charList;
    private String listName;

    // EFFECTS: creates a list of Characters
    public CharactersList(String listName) {
        charList = new ArrayList<Character>();
        this.listName = listName;
        if (listName == ALL_CHAR_AVAILABLE) {
            loadAllChar();
        }
    }

    // REQUIRES: c not already in list
    // MODIFIES: this
    // EFFECTS: adds new character to CharacterList
    public void addChar(Character c) {}

    // REQUIRES: c already in list, list isFull (not parameter req)
    // MODIFIES: this
    // EFFECTS: remove given character from CharacterList
    public void removeChar(Character c) {}

    // REQUIRES: charName is real;
    public Character getCharacter(String charName) {
        return new Character("Ghost");
    }

    // EFFECTS: returns true if CharacterList is empty, otherwise return false
    public boolean isEmpty() {
        return false;
    }

    // EFFECTS: returns true if CharacterList is at MAX_SIZE, otherwise return false
    public boolean isFull() {
        return false;
    }

    // REQUIRES: this is empty, stringName = "all characters available" (not parameter req)
    // MODIFIES: this
    // EFFECTS: loads all available characters
    public void loadAllChar() {}
}
