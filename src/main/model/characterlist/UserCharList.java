package model.characterlist;

// CLASS LEVEL COMMENT: an empty character list with ability to add new characters

import model.Character;

import java.io.Serializable;
import java.util.ArrayList;

// represents list of characters owned by user (adds characters from AllCharList)

public class UserCharList extends CharList {

    // EFFECTS: creates an empty list of Characters with name as listName
    public UserCharList(String listName) {
        super(listName);
    }

    // REQUIRES: this contains Character with charName (only once)
    // MODIFIES: this
    // EFFECTS: returns Character with given charName in list
    @Override
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
    @Override
    public String getListName() {
        return listName;
    }

    // REQUIRES: c not already in this (c should be get from AllCharList)
    // MODIFIES: this
    // EFFECTS: adds given character to your character list
    public void addChar(Character c) {
        charList.add(c);
    }
}
