package model;

public class Skill {
    static final int MAX_LEVEL = 5;
    static final int STAT_INCREMENTER = 1380;
    static final int POWER_UP_BASE_COST = 300;

    private String name;
//    private int stat;
    private int level;
    private int powerUpCost;

    // EFFECTS: creates new Skill with name,
    //                                 base statistics of 0, (removed for now)
    //                                 base level = 1,
    //                                 base powerUpCost = POWER_UP_BASE_COST
    public Skill(String name) {
        this.name = name;
//        stat = 0;
        level = 1;
        powerUpCost = POWER_UP_BASE_COST;
    }

    // REQUIRES: level != MAX_LEVEL
    // MODIFIES: this
    // EFFECTS: increases skill level by 1 and increment powerUpCost and stat
    public void levelUpByOne() {
        incrementLevel();
        incrementPowerUpCost();
//        incrementStat();
    }

    // helper
    // EFFECTS: increment skill level by 1
    private void incrementLevel() {
        level = level + 1;
    }

    // helper
    // MODIFIES: this
    // EFFECTS: increments powerUpCost by 200 * level
    private void incrementPowerUpCost() {
        int incrementCostBy = 200 * level;
        powerUpCost = powerUpCost + incrementCostBy;
    }

//    // helper
//    // MODIFIES: this
//    // EFFECTS: increments statistics of skill by STAT_INCREMENTER
//    private void incrementStat() {
//        stat = stat + STAT_INCREMENTER;
//    }

    // EFFECTS: returns skill name
    public String getName() {
        return name;
    }

//    // EFFECTS: returns current skill statistic
//    public int getStat() {
//        return stat;
//    }

    // EFFECTS: returns current skill level
    public int getLevel() {
        return level;
    }

    // EFFECTS: returns current cost to power up skill to next level
    public int getPowerUpCost() {
        return powerUpCost;
    }

}
