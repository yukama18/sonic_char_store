package model.characterlist;

import model.Character;

public class YourCharList extends CharList {

    public YourCharList(String listName) {
        super(listName);
    }

    // REQUIRES: c not already in this (c should be get from AllCharList)
    // MODIFIES: this
    // EFFECTS: adds given character to your character list
    public void addChar(Character c) {
        charList.add(c);
    }
}
