package ui.panels;

import model.Character;
import model.Skill;
import model.characterlist.CharList;
import ui.CardLayoutGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkillPanel extends JPanel implements ActionListener {
    Character character;
    String charName;

    Skill jump;
    Skill dash;
    Skill shield;

    JPanel jumpPanel;
    JPanel dashPanel;
    JPanel shieldPanel;

    CharList cl;
    CardLayoutGUI frame;

    JLabel pic;
    JLabel instr;
    JPanel skills;

    public SkillPanel(Character character, CharList cl, CardLayoutGUI frame) {
        this.character = character;
        this.charName = character.getName();

        this.jump = character.getJump();
        this.dash = character.getDash();
        this.shield = character.getShield();

        this.cl = cl;
        this.frame = frame;

        run();
    }

    private void run() {
        skills = new JPanel();

        initComponents();

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(pic);
        add(instr);
        add(skills);
    }

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

    private void initSkills() {
        skills.removeAll();
        jumpPanel = generatePanel(jump);
        dashPanel = generatePanel(dash);
        shieldPanel = generatePanel(shield);
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        String skillName = e.getActionCommand();
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

        if (frame.coins >= s.getPowerUpCost()) {
            frame.updateCoins(-s.getPowerUpCost());
            character.levelUp(s.getName());
        } else {
            JOptionPane insufficient = new JOptionPane();
            insufficient.showMessageDialog(frame,"Sorry, insufficient coins!");
        }

        initComponents();
    }
}
