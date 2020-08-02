package model.characterlist;

import model.Character;

// represents list of characters owned by user (adds characters from AllCharList)

public class UserCharList extends CharList {

    // EFFECTS: creates an empty list of Characters with name as listName
    public UserCharList(String listName) {
        super(listName);
    }

    // REQUIRES: c not already in this (c should be get from AllCharList)
    // MODIFIES: this
    // EFFECTS: adds given character to your character list
    public void addChar(Character c) {
        charList.add(c);
    }
}
