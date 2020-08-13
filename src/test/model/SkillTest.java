package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SkillTest {
    Skill t;

    @BeforeEach
    public void runBefore() {
        t = new Skill("test skill");
        assertEquals("test skill", t.getName());
//        assertEquals(0,t.getStat());
        assertEquals(1,t.getLevel());
        assertEquals(Skill.BASE_POWER_UP_COST,t.getPowerUpCost());
    }

    @Test
    public void levelUpFrom1() {
        t.levelUpByOne();
//        assertEquals(0 + Skill.STAT_INCREMENTER,t.getStat());
        assertEquals(2,t.getLevel());
        assertEquals(Skill.BASE_POWER_UP_COST + 400,t.getPowerUpCost());
    }

    @Test
    public void levelUpFrom2() {
        t.levelUpByOne();
        t.levelUpByOne();
//        assertEquals(Skill.STAT_INCREMENTER * 2,t.getStat());
        assertEquals(3,t.getLevel());
        assertEquals(Skill.BASE_POWER_UP_COST + 400 + 600,t.getPowerUpCost());
    }

    @Test
    public void levelUpFrom3() {
        t.levelUpByOne();
        t.levelUpByOne();
        t.levelUpByOne();
//        assertEquals(Skill.STAT_INCREMENTER * 3,t.getStat());
        assertEquals(4,t.getLevel());
        assertEquals(Skill.BASE_POWER_UP_COST + 400 + 600 + 800,t.getPowerUpCost());
    }

    @Test
    public void levelUpFrom4() {
        t.levelUpByOne();
        t.levelUpByOne();
        t.levelUpByOne();
        t.levelUpByOne();
//        assertEquals(Skill.STAT_INCREMENTER * 4,t.getStat());
        assertEquals(5,t.getLevel());
        assertEquals(Skill.BASE_POWER_UP_COST + 400 + 600 + 800 + 1000,t.getPowerUpCost());
    }

}

