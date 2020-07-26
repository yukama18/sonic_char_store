package model;

import model.Character;

import java.util.ArrayList;

public class CharacterList {
    static final int MAX_SIZE = Character.numOfChar;
    private static final String ALL_CHAR_AVAILABLE = "all characters available";
    Character sonic = new Character("sonic");
    Character tails = new Character("tails");
    Character knuckles = new Character("knuckles");
    Character amy = new Character("amy");
    Character villain = new Character("doctor eggman");

    ArrayList<Character> charList;
    private String listName;

    // EFFECTS: if listName == "all characters available",
    //              - create empty list of Characters then loads all available characters
    //          otherwise, create an empty list of Characters
    public CharacterList(String listName) {
        charList = new ArrayList<Character>();
        this.listName = listName;
        if (listName == ALL_CHAR_AVAILABLE) {
            loadAllChar();
        }
    }

    // REQUIRES: c already in list (but only once)
    // MODIFIES: this
    // EFFECTS: remove given character from CharacterList
    public void removeChar(Character c) {
        for (Character a: charList) {
            if (a.equals(c)) {
                charList.remove(a);
                break;
            }
        }
    }

    // REQUIRES: charName is real, Character with charName is already in CharaterList (but only once)
    // EFFECTS: returns the Character in the list with given charName
    public Character getCharacter(String charName) {
        Character foundChar = null;
        for (Character c: charList) {
            if (c.getName() == charName) {
                foundChar = c;
                break;
            }
        }
        return foundChar;
    }

    // EFFECTS: returns true if a Character with given charName can be found in CharacterList
    public boolean containsChar(String charName) {
        boolean isFound = false;
        for (Character c: charList) {
            if (c.getName() == charName) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    // EFFECTS: returns true if CharacterList is empty, otherwise return false
    public boolean isEmpty() {
        if (charList.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns true if CharacterList is at MAX_SIZE, otherwise return false
    public boolean isFull() {
        return false;
    }

    // EFFECTS: returns number of Characters in CharacterList
    public int numOfChar() {
        return 0;
    }


    // EFFECTS: returns name of the list
    public String getListName() {
        return listName;
    }

    // REQUIRES: sonic not already in list
    // MODIFIES: this
    // EFFECTS: adds sonic to CharacterList
    public void addSonic() {}

    // REQUIRES: tails not already in list
    // MODIFIES: this
    // EFFECTS: adds tails to CharacterList
    public void addTails() {}

    // REQUIRES: knuckles not already in list
    // MODIFIES: this
    // EFFECTS: adds knuckles to CharacterList
    public void addKnuckles() {}

    // REQUIRES: amy not already in list
    // MODIFIES: this
    // EFFECTS: adds amy to CharacterList
    public void addAmy() {}

    // REQUIRES: villain not already in list
    // MODIFIES: this
    // EFFECTS: adds villain to CharacterList
    public void addVillain() {}


    // REQUIRES: this is empty, stringName = "all characters available" (not parameter req)
    // MODIFIES: this
    // EFFECTS: loads all available characters
    public void loadAllChar() {
        addSonic();
        addTails();
        addKnuckles();
        addAmy();
        addVillain();
    }
}
