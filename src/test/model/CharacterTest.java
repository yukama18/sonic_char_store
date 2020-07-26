package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    Character c;

    @BeforeEach
    public void runBefore() {
        c = new Character("test");
        assertEquals("test", c.getName());
        assertEquals(1000, c.getCharacterCost());

        //test skills just be name and skill level
        correctSkills(1,1,1);
    }

    @Test
    public void levelUpOnlyJump() {
        c.levelUp("jump");
        correctSkills(2,1,1);
    }

    @Test
    public void levelUpOnlyDash() {
        c.levelUp("dash");
        correctSkills(1,2,1);
    }

    @Test
    public void levelUpOnlyShield() {
        c.levelUp("shield");
        correctSkills(1,1,2);
    }

    @Test
    public void levelUpAllSkills() {
        c.levelUp("jump");
        c.levelUp("dash");
        c.levelUp("shield");
        correctSkills(2,2,2);
    }


    private void correctSkills(int j, int d, int s) {
        assertEquals("jump", c.getJump().getName());
        assertEquals("dash", c.getDash().getName());
        assertEquals("shield", c.getShield().getName());
        assertEquals(j,c.getJump().getLevel());
        assertEquals(d,c.getDash().getLevel());
        assertEquals(s,c.getShield().getLevel());

    }

}