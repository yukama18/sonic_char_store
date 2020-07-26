package model;

import model.characterlist.AllCharList;
import model.characterlist.YourCharList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class YourCharListTest {
    YourCharList yourList;
    AllCharList allChar = new AllCharList();

    @BeforeEach
    public void runBefore() {
        yourList = new YourCharList("your list");
        assertEquals("your list", yourList.getListName());
        assertEquals(0, yourList.numOfChar());
        assertFalse(yourList.containsChar("sonic"));
        assertFalse(yourList.containsChar("tails"));
        assertFalse(yourList.containsChar("knuckles"));
        assertFalse(yourList.containsChar("amy"));
        assertFalse(yourList.containsChar("doctor eggman"));
        assertFalse(yourList.containsChar("mario"));
        assertTrue(yourList.isEmpty());
    }

    @Test
    public void addOneChar() {
        yourList.addChar(allChar.getChar("sonic"));
        assertEquals(1,yourList.numOfChar());
        assertFalse(yourList.isEmpty());
        assertTrue(yourList.containsChar("sonic"));
    }
}
