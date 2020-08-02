package model;

// character with name, base cost, and 3 skills

import java.io.Serializable;
import java.util.Objects;

public class Character implements Serializable {
    private String name;
    private int characterCost;
    private Skill jump;
    private Skill dash;
    private Skill shield;

    // EFFECTS: create a character with characterName as name,
    //          with a base characterCost of 800;
    //          with level 1 jump, dash, and shield skills
    //          increment static variable numOfChar
    public Character(String characterName) {
        this.name = characterName;
        characterCost = 1000;
        jump = new Skill("jump");
        dash = new Skill("dash");
        shield = new Skill("shield");
    }

    // REQUIRES: skillName is "jump", "dash", or "shield"
    // EFFECTS: increment level of character skill with skillName by 1
    public void levelUp(String skillName) {
        if (skillName.equals("jump")) {
            jump.levelUpByOne();
        } else {
            if (skillName.equals("dash")) {
                dash.levelUpByOne();
            } else {
                shield.levelUpByOne();
            }
        }
    }

    // EFFECTS: returns name of character
    public String getName() {
        return name;
    }

    // EFFECTS: returns cost of character
    public int getCharacterCost() {
        return characterCost;
    }

    // EFFECTS: returns jump skill
    public Skill getJump() {
        return jump;
    }

    // EFFECTS: returns dash skill
    public Skill getDash() {
        return dash;
    }

    // EFFECTS: returns shield skill
    public Skill getShield() {
        return shield;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Character character = (Character) o;
        return name.equals(character.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
