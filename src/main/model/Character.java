package model;

public class Character {
    String name;
    int characterCost;
    String jumpSkill;
    String dashSkill;
    String shieldSkill;
    private int jumpStat;
    private int dashStat;
    private int shieldStat;
    private int jumpPowerUpCost;
    private int dashPowerUpCost;
    private int shieldPowerUpCost;

    // EFFECTS: create a character with characterName as name,
    //          3 basic skills all at a level of 0, and
    //          base cost to power up each skill at 100 coins;
    public Character(String characterName) {
        this.name = characterName;
        jumpSkill = "jump";
        dashSkill = "dash";
        shieldSkill = "shield";
        jumpStat = 0;
        dashStat = 0;
        shieldStat = 0;
        jumpPowerUpCost = 100;
        dashPowerUpCost = 100;
        shieldPowerUpCost = 100;
    }

    // EFFECTS: if there is sufficient balance on the account
    //            - subtracts amount cents from balance
    //            - adds reward points and then
    //                - if eligible, adds cash back reward(s) to account and deducts corresponding reward points
    //            - returns true
    //          otherwise, returns false

    // REQUIRES: skillName is one of the 3 available skills to power up
    // MODIFIES: this, totalCoins
    // EFFECTS: if the user has enough coins to power up a given skill,
    //              - subtract powerUpCost from totalCoins
    //              -
    public boolean powerUp(String skillName) {
        return false;
    }

    // EFFECTS: returns name of character
    public String getName() {
        return name;
    }

    public void setJumpStat(int jumpStat) {
        this.jumpStat = jumpStat;
    }

    public int getJumpStat() {
        return jumpStat;
    }

    public void setDashStat(int dashStat) {
        this.dashStat = dashStat;
    }

    public int getDashStat() {
        return dashStat;
    }

    public void setShieldStat(int shieldStat) {
        this.shieldStat = dashStat;
    }

    public int getShieldStat() {
        return shieldStat;
    }

    public void setJumpPowerUpCost(int jumpPowerUpCost) {
        this.jumpPowerUpCost = jumpPowerUpCost;
    }

    public int getJumpPowerUpCost() {
        return jumpPowerUpCost;
    }

    public void setDashPowerUpCost(int dashPowerUpCost) {
        this.dashPowerUpCost = dashPowerUpCost;
    }

    public int getDashPowerUpCost() {
        return dashPowerUpCost;
    }

    public void setShieldPowerUpCost(int shieldPowerUpCost) {
        this.shieldPowerUpCost = shieldPowerUpCost;
    }

    public int getShieldPowerUpCost() {
        return shieldPowerUpCost;
    }

}
