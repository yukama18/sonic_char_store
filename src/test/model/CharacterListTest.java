package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterListTest {
    CharacterList ownedChar;
    CharacterList allChar;

    @BeforeEach
    public void runBefore() {
        ownedChar = new CharacterList("characters owned");
        assertEquals("characters owned", ownedChar.getListName());
        assertTrue(ownedChar.isEmpty());
    }

    @Test
    public void listWithAllChar() {
        allChar = new CharacterList("all available characters");
        assertTrue(allChar.isFull());
        assertTrue(allChar.containsChar("sonic"));
        assertTrue(allChar.containsChar("tails"));
        assertTrue(allChar.containsChar("knuckles"));
        assertTrue(allChar.containsChar("amy"));
        assertTrue(allChar.containsChar("doctor eggman"));
    }

    @Test
    public void addNewChar() {

    }

}