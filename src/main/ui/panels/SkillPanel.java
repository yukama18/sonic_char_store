package ui.panels;

// CLASS LEVEL COMMENT: generates panel for character skills

import model.Character;
import model.Skill;
import model.characterlist.CharList;
import ui.CardLayoutGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkillPanel extends JPanel implements ActionListener {
    private Character character;
    private String charName;
    private CharList cl;
    private CardLayoutGUI frame;

    private Skill jump;
    private Skill dash;
    private Skill shield;
    private JPanel jumpPanel;
    private JPanel dashPanel;
    private JPanel shieldPanel;

    private JLabel pic;
    private JLabel instr;
    private JPanel skills;

    // EFFECTS: constructs panel for each character's skills
    public SkillPanel(Character character, CharList cl, CardLayoutGUI frame) {
        this.character = character;
        this.charName = character.getName();
        this.cl = cl;
        this.frame = frame;

        this.jump = character.getJump();
        this.dash = character.getDash();
        this.shield = character.getShield();

        run();
    }

    // MODIFIES: this
    // EFFECTS: initializes and displays skill components
    private void run() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        skills = new JPanel();
        initComponents();

        add(pic);
        add(instr);
        add(skills);
    }

    // MODIFIES: this
    // EFFECTS: initializes character + skill panel
    private void initComponents() {
        ImageIcon charIcon = new ImageIcon("./data/char/" + charName + ".png");
        pic = new JLabel(charIcon);
        pic.setBackground(Color.blue);
        pic.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        instr = new JLabel(charName + " has the following skills. Which would you like to power up?");
        instr.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        instr.setAlignmentX(CENTER_ALIGNMENT);

        initSkills();
        skills.add(jumpPanel);
        skills.add(dashPanel);
        skills.add(shieldPanel);
        skills.revalidate();
    }

    // MODIFIES: this
    // EFFECTS: initializes each skill's panel
    private void initSkills() {
        skills.removeAll();
        jumpPanel = generatePanel(jump);
        dashPanel = generatePanel(dash);
        shieldPanel = generatePanel(shield);
    }

    // EFFECTS: generates panel for given skill
    private JPanel generatePanel(Skill skill) {
        JPanel skillPanel = new JPanel();
        skillPanel.setLayout(new BoxLayout(skillPanel,BoxLayout.Y_AXIS));
        skillPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 30));

        JButton skillButton = new JButton(skill.getName());
        skillButton.setActionCommand(skill.getName());
        skillButton.addActionListener(this);

        JLabel skillLevel = new JLabel("Level: " + skill.getLevel());
        JLabel skillCost = new JLabel("power up cost: " + skill.getPowerUpCost());

        skillPanel.add(skillButton);
        skillPanel.add(skillLevel);
        skillPanel.add(skillCost);

        return skillPanel;
    }

    // MODIFIES: this
    // EFFECTS: attempts to power up chosen skill
    @Override
    public void actionPerformed(ActionEvent e) {
        Skill s = getSkill(e.getActionCommand());

        attemptPowerUp(s);

        initComponents();
    }

    // MODIFIES: this, coins
    // EFFECTS: powers up level given sufficient coins, otherwise display fail msg
    private void attemptPowerUp(Skill s) {
        if (frame.coins >= s.getPowerUpCost()) {
            frame.updateCoins(-s.getPowerUpCost());
            character.levelUp(s.getName());

            JOptionPane insufficient = new JOptionPane();
            insufficient.showMessageDialog(frame,"You have successfully leveled up this skill!");
        } else {
            JOptionPane insufficient = new JOptionPane();
            insufficient.showMessageDialog(frame,"Sorry, insufficient coins!");
        }
    }

    // EFFECTS: returns skill given skill name
    private Skill getSkill(String skillName) {
        Skill s = null;
        if (skillName.equals("jump")) {
            s = jump;
        } else {
            if (skillName.equals("dash")) {
                s = dash;
            } else {
                s = shield;
            }
        }
        return s;
    }
}
