package model;

import model.characterlist.AllCharList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AllCharListTest {
    AllCharList allChar;

    @BeforeEach
    public void runBefore() {
        allChar = new AllCharList();
        assertEquals("all characters available", allChar.getListName());
        assertEquals(5,allChar.numOfChar());
        assertTrue(allChar.containsChar("sonic"));
        assertTrue(allChar.containsChar("tails"));
        assertTrue(allChar.containsChar("knuckles"));
        assertTrue(allChar.containsChar("amy"));
        assertTrue(allChar.containsChar("doctor eggman"));
        assertFalse(allChar.containsChar("mario"));
        assertFalse(allChar.isEmpty());
        assertTrue(allChar.isFull());

        assertEquals(null,allChar.getChar("mario"));
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
        allChar.getChar("doctor eggman");
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
        assertFalse(allChar.containsChar("doctor eggman"));
    }

}