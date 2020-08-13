package model.characterlist;

// CLASS LEVEL COMMENT: empty list of characters with name

import model.Character;

import java.util.ArrayList;

public abstract class CharList {
    ArrayList<Character> charList;
    protected String listName;

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

    public abstract Character getChar(String charName);

    // EFFECTS: returns name of the list
    public abstract String getListName();

    // EFFECTS: returns true if CharacterList is empty, otherwise return false
    public boolean isEmpty() {
        return charList.isEmpty();
    }

    // EFFECTS: returns number of Characters in CharacterList
    public int numOfChar() {
        return charList.size();
    }

}
