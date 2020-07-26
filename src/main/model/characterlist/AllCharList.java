package model.characterlist;

import model.Character;

public class AllCharList extends CharList {
    static final int MAX_SIZE = 5;
    private static final String ALL_CHAR_AVAIL = "all characters available";
    private Character sonic;                 // = new Character("sonic");
    private Character tails;                 // = new Character("tails");
    private Character knuckles;              // = new Character("knuckles");
    private Character amy;                   // = new Character("amy");
    private Character villain;               // = new Character("eggman");

    // REQUIRES: only called when listName == "all characters available"
    // EFFECTS: creates an empty charList first
    //              - create empty list of Characters then loads all available characters
    public AllCharList() {
        super(ALL_CHAR_AVAIL);
        sonic = new Character("sonic");
        tails = new Character("tails");
        knuckles = new Character("knuckles");
        amy = new Character("amy");
        villain = new Character("eggman");
        loadAllChar();
    }

    // REQUIRES: this is empty
    // MODIFIES: this
    // EFFECTS: loads all available characters once
    public void loadAllChar() {
        charList.add(sonic);
        charList.add(tails);
        charList.add(knuckles);
        charList.add(amy);
        charList.add(villain);
    }

    // REQUIRES: this contains Character with charName (only once)
    // MODIFIES: this
    // EFFECTS: removes Character with given charName in list, then returns it
    //          (lab5 ca.ubc.cpsc210.helpdesk.model.IncidentQueue#getNextIncident)
    @Override
    public Character getChar(String charName) {
        Character foundChar = null;
        for (Character c: charList) {
            if (c.getName() == charName) {
                foundChar = c;
                charList.remove(c);
                break;
            }
        }
        return foundChar;
    }

    // EFFECTS: returns true if charList currently contains all characters available
    public boolean isFull() {
        boolean isFull = false;
        if (charList.size() == MAX_SIZE) {
            isFull = true;
        }
        return isFull;
    }

    @Override
    public String getListName() {
        super.getListName();
        return ALL_CHAR_AVAIL;
    }

}
