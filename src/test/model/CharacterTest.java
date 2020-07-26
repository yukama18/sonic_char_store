package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    Character c1;
    Character c2;

    @BeforeEach
    public void runBefore() {
        c1 = new Character("test");
        assertEquals(1,Character.numOfChar);
        assertEquals("test", c1.getName());
        assertEquals(1000, c1.getCharacterCost());
        // assume jump, dash, and shield skills are instantiated properly
    }

    @Test
    public void testNumOfCharIncrements() {
        c2 = new Character("test2");
        assertEquals(2,Character.numOfChar);
    }

    // how do i test if levelupbyone is working properly

    // test setters/getters?

}