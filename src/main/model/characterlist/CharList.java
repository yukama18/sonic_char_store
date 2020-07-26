package model.characterlist;

import model.Character;

import java.util.ArrayList;

public abstract class CharList {
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
            if (c.getName() == charName) {
                containsChar = true;
                break;
            }
        }
        return containsChar;
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

}
