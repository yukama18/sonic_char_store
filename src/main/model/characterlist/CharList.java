package model.characterlist;

import model.Character;

import java.util.ArrayList;

// empty list of characters

public class CharList {
    ArrayList<Character> charList;
    private String listName;

    // EFFECTS: creates an empty list of Characters with name as listName
    public CharList(String listName) {
        charList = new ArrayList<Character>();
        this.listName = listName;
    }

    // EFFECTS: returns true if given Character can be found in CharacterList
    //              - compare name of c against each character in charList
    //              - return true if match found, false otherwise
    public boolean containsChar(String charName) {
        boolean containsChar = false;
        for (Character c: charList) {
            if (c.getName().equals(charName)) {
                containsChar = true;
                break;
            }
        }
        return containsChar;
    }

    // REQUIRES: this contains Character with charName (only once)
    // MODIFIES: this
    // EFFECTS: returns Character with given charName in list
    public Character getChar(String charName) {
        Character foundChar = null;
        for (Character c: charList) {
            if (c.getName().equals(charName)) {
                foundChar = c;
                break;
            }
        }
        return foundChar;
    }

    // EFFECTS: returns name of the list
    public String getListName() {
        return listName;
    }

    // EFFECTS: returns true if CharacterList is empty, otherwise return false
    public boolean isEmpty() {
        return charList.isEmpty();
    }

    // EFFECTS: returns number of Characters in CharacterList
    public int numOfChar() {
        return charList.size();
    }

    // REQUIRES: c not already in this (c should be get from AllCharList)
    // MODIFIES: this
    // EFFECTS: adds given character to your character list
    public void addChar(Character c) {
        charList.add(c);
    }
}
