package ui;

import model.Skill;
import model.characterlist.AllCharList;
import model.characterlist.CharList;
import model.characterlist.YourCharList;
import model.Character;

import java.util.Scanner;

public class CharacterStore {
//  (reference: ca.ubc.cpsc210.bank.ui.TellerApp#runTeller)

    private Scanner input;
    private AllCharList allChar;
    private YourCharList yourChar;
    private int coins;

    // EFFECTS: runs the game store application
    public CharacterStore() {
        runStore();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runStore() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        initChar();

        while (keepGoing) {
            displayOptions();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommands(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    public void processCommands(String command) {
        if (command.equals("1")) {
            viewAllChar();
        } else if (command.equals("2")) {
            viewYourChar();
        } else if (command.equals("3")) {
            generateCoins();
        } else {
            System.out.println("Selection invalid :(");
        }
    }

    private void generateCoins() {
        System.out.println("You will be randomly generated a number of coins between 3000 and 600. Press r.");

        String command = input.next().toLowerCase();

        while (!(command.equals("r"))) {
            System.out.println("Press r to generate coin, or 0 to quit.");
            String command2 = input.next().toLowerCase();
            if (command2.equals("0")) {
                return;
            } else {
                if (command2.equals("r")) {
                    break;
                }
            }
        }

        int amountGenerated = randomGenerate();
        System.out.println("You have been generated " + amountGenerated + " coins.");
        coins = coins + amountGenerated;
        System.out.println(printCoins());
    }


    // EFFECTS: generate a random integer between 3000 and 600
    private int randomGenerate() {
        int amountGenerated = 0;
        int maxCoins = 3000;
        int minCoins = 600;

        amountGenerated = (int) (Math.random() * (maxCoins - minCoins)) + minCoins;

        return amountGenerated;
    }

    // EFFECTS: displays all characters owned and allows power ups
    private void viewYourChar() {
        if (!(yourChar.isEmpty())) {
            String welcome = "Here are all the characters that you currently own. ";
            System.out.println(welcome + "Please pick a character to power up");

            displayCharNames(yourChar);

            String command = input.next().toLowerCase();

            if (command.equals("0")) {
                return;
            } else {
                if (command.equals("sonic")) {
                    viewChar("sonic");
                } else if (command.equals("tails")) {
                    viewChar("tails");
                } else if (command.equals("knuckles")) {
                    viewChar("knuckles");
                } else if (command.equals("amy")) {
                    viewChar("amy");
                } else if (command.equals("eggman")) {
                    viewChar("eggman");
                }
            }
        } else {
            System.out.println("Sorry, you don't have any characters yet!");
        }
    }

    // EFFECTS: conducts viewing/levelling up of characters, given sufficient coins
    private void viewChar(String name) {
        Character selected = yourChar.getChar(name);
        if (selected == null) {
            System.out.println("Sorry, you don't own this character yet!");
            return;
        }
        System.out.println("\n" + name + " has the following skills. " + printCoins());

        Skill jump = selected.getJump();
        Skill dash = selected.getDash();
        Skill shield = selected.getShield();

        printLevel(jump, dash, shield);

        System.out.println("Which skill would you like to power up?");

        String command = input.next().toLowerCase();

        if (command.equals("0")) {
            return;
        } else {
            if (command.equals("jump")) {
                levelUp(selected, jump);
            } else if (command.equals("dash")) {
                levelUp(selected, dash);
            } else if (command.equals("shield")) {
                levelUp(selected, shield);
            }
        }
    }

    // EFFECTS: levels up given skill of character if sufficient coins
    private void levelUp(Character c, Skill s) {
        if (coins >= s.getPowerUpCost()) {
            coins = coins - s.getPowerUpCost();
            c.levelUp(s.getName());
            System.out.println("You have successfully leveled up this skill. " + printCoins());
        } else {
            System.out.println("Sorry, insufficient coins!");
        }
    }

    // EFFECTS: prints current level and power up cost of given character
    private void printLevel(Skill jump, Skill dash, Skill shield) {
        System.out.println("Jump: level " + jump.getLevel() + ", power up cost: " + jump.getPowerUpCost());
        System.out.println("Dash: level " + dash.getLevel() + ", power up cost: " + dash.getPowerUpCost());
        System.out.println("Shield: level " + shield.getLevel() + ", power up cost: " + shield.getPowerUpCost());
    }

    // EFFECTS: displays all available characters or conducts purchases of characters with sufficient costs
    private void viewAllChar() {
        String welcome = "Here are all the available characters in the store. Each costs 100 coins. ";
        System.out.println(welcome + printCoins() + " Type a character's name to view or purchase.");

        displayCharNames(allChar);

        String command = input.next().toLowerCase();

        if (command.equals("0")) {
            return;
        } else {
            if (coins >= 1000) {
                if (command.equals("sonic")) {
                    buyChar("sonic");
                } else if (command.equals("tails")) {
                    buyChar("tails");
                } else if (command.equals("knuckles")) {
                    buyChar("knuckles");
                } else if (command.equals("amy")) {
                    buyChar("amy");
                } else if (command.equals("eggman")) {
                    buyChar("eggman");
                }
            } else {
                System.out.println("Sorry, you have insufficient coins! Please generate with lucky toss.");
            }
        }
    }

    // EFFECTS: buys character
    private void buyChar(String name) {
        if (allChar.containsChar(name)) {
            Character toAdd = allChar.getChar(name);
            yourChar.addChar(toAdd);
            coins = coins - 1000;
            System.out.println("You have successfully purchased " + name + ". " + printCoins());
        } else {
            System.out.println("Already bought this character!");
        }
    }

    // EFFECTS: print out names of all characters available
    private void displayCharNames(CharList cl) {
        displayChar("sonic", cl);
        displayChar("tails", cl);
        displayChar("knuckles", cl);
        displayChar("amy", cl);
        displayChar("eggman", cl);
        System.out.println("0 -> return back to options");
    }

    // EFFECT: prints name or availability status of character in store
    private void displayChar(String name, CharList cl) {
        if (cl.containsChar(name)) {
            System.out.println(name);
        } else {
            if (cl.getListName() == "your characters") {
                System.out.println(name + " not yet bought!");
            } else {
                System.out.println(name + " already bought!");
            }
        }
    }

    // EFFECTS: displays options for user to choose from
    public void displayOptions() {
        System.out.println("\nHi, welcome to the Sonic character store. " + printCoins() + " Press");
        System.out.println("\t1 to view available characters");
        System.out.println("\t2 to view characters you now own");
        System.out.println("\t3 to generate lucky coin toss");
        System.out.println("\tq to leave store");
    }

    // EFFECTS: initializes list of all characters available and characters user owns
    public void initChar() {
        allChar = new AllCharList();
        yourChar = new YourCharList("your characters");
    }


    private String printCoins() {
        String printThis = "You currently have " + coins + " coins.";
        return printThis;
    }
}
