package model;

import model.characterlist.AllCharList;
import model.characterlist.UserCharList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AllCharListTest {
    AllCharList allChar;
    AllCharList loadedAllChar;

    @BeforeEach
    public void runBefore() {
        allChar = new AllCharList();
        assertEquals("all characters available", allChar.getListName());
        assertEquals(5,allChar.numOfChar());
        assertTrue(allChar.containsChar("sonic"));
        assertTrue(allChar.containsChar("tails"));
        assertTrue(allChar.containsChar("knuckles"));
        assertTrue(allChar.containsChar("amy"));
        assertTrue(allChar.containsChar("eggman"));
        assertFalse(allChar.containsChar("mario"));
        assertFalse(allChar.isEmpty());
        assertTrue(allChar.isFull());

        assertEquals(null,allChar.getChar("mario"));
    }

    @Test
    public void loadedConstructor() {
        UserCharList loadedUserChar = new UserCharList("test load");
        loadedUserChar.addChar(new Character("sonic"));

        loadedAllChar = new AllCharList(loadedUserChar);
        assertEquals("all characters available", allChar.getListName());
        assertEquals(4,loadedAllChar.numOfChar());
        assertFalse(loadedAllChar.containsChar("sonic"));
        assertTrue(loadedAllChar.containsChar("tails"));
        assertTrue(loadedAllChar.containsChar("knuckles"));
        assertTrue(loadedAllChar.containsChar("amy"));
        assertTrue(loadedAllChar.containsChar("eggman"));
        assertFalse(loadedAllChar.containsChar("mario"));
        assertFalse(loadedAllChar.isEmpty());
        assertFalse(loadedAllChar.isFull());
    }

    @Test
    public void getOneChar() {
        Character removedChar = allChar.getChar("sonic");
        assertEquals(4,allChar.numOfChar());
        assertFalse(allChar.containsChar("sonic"));
        assertFalse(allChar.isFull());

        assertEquals("sonic",removedChar.getName());
        // assume rest of methods are tested by CharacterTest
    }

    @Test
    public void getAllChar() {
        allChar.getChar("eggman");
        allChar.getChar("amy");
        allChar.getChar("knuckles");
        allChar.getChar("tails");
        allChar.getChar("sonic");

        assertEquals(0,allChar.numOfChar());
        assertTrue(allChar.isEmpty());
        assertFalse(allChar.isFull());
        assertFalse(allChar.containsChar("sonic"));
        assertFalse(allChar.containsChar("tails"));
        assertFalse(allChar.containsChar("knuckles"));
        assertFalse(allChar.containsChar("amy"));
        assertFalse(allChar.containsChar("eggman"));
    }

}