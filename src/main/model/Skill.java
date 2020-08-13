package model;

// CLASS LEVEL COMMENT: skill with name, level, and power up cost

import java.io.Serializable;

public class Skill implements Serializable {
    static final int BASE_POWER_UP_COST = 300;

    private String name;
    private int level;
    private int powerUpCost;

    // EFFECTS: creates new Skill with name, base level = 1, base powerUpCost = BASE_POWER_UP_COST
    public Skill(String name) {
        this.name = name;
        level = 1;
        powerUpCost = BASE_POWER_UP_COST;
    }

    // REQUIRES: level < 5
    // MODIFIES: this
    // EFFECTS: increases skill level by 1 and increment powerUpCost
    public void levelUpByOne() {
        incrementLevel();
        incrementPowerUpCost();
    }

    // EFFECTS: increment skill level by 1
    private void incrementLevel() {
        level = level + 1;
    }

    // MODIFIES: this
    // EFFECTS: increments powerUpCost by 200 * level
    private void incrementPowerUpCost() {
        int incrementCostBy = 200 * level;
        powerUpCost = powerUpCost + incrementCostBy;
    }

    // EFFECTS: returns skill name
    public String getName() {
        return name;
    }

    // EFFECTS: returns current skill level
    public int getLevel() {
        return level;
    }

    // EFFECTS: returns current cost to power up skill to next level
    public int getPowerUpCost() {
        return powerUpCost;
    }
}
