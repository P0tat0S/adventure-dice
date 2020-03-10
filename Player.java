import java.util.*;

abstract class Player extends Entity {
    //Combat variables
    private int target;
    private char targetType;

    //Constructor
    public Player(String n, double[] stats) {
        super(n, stats);
    }

    //WildDice Method
    public void selectDice(ArrayList<WildDice> wd) {
        int choice = 0;
        boolean valid = false;
        while(!valid) {
            Util.print("Your combatSpeed is " + combatSpeed);
            choice = Util.intInput("Choose a dice by entering the number");
            if (wd.get(choice).getSpeedCost() <= combatSpeed) {
                combatSpeed -= wd.get(choice).getSpeedCost() ;
                valid = true;
            } else {
                Util.print("You decided to skip turn");
                addSpeed();
                skip = true;
                valid = true;
            }
            addEffect(wd, choice);
        }
    }

    /*************
    COMBAT METHODS
    *************/
    public void selectTarget(ArrayList<Player> p, ArrayList<Enemy> e) {
        target = Util.intInput("At which target ?"
            + "\n1 " + e.get(0).getName() + "\n2 " + e.get(1).getName()
            + "\n3 " + e.get(2).getName() + "\n4 " + p.get(0).getName()
            + "\n5 " + p.get(1).getName() + "\n6 " + p.get(2).getName());
        if (target <= 0 || target > 6) {
            Util.print("That is not a valid target try again.");
            selectTarget(p, e);
        } else if (target > 3) {
            switch (target) {
                case 4:
                    target = 0;
                    targetType = 'p';
                    break;
                case 5:
                    target = 1;
                    targetType = 'p';
                    break;
                case 6:
                    target = 2;
                    targetType = 'p';
                    break;
            }
        } else {
            targetType = 'e';
            target--;
        }
    }

    public void viewStats() {
        Util.print(
            "Level: " + level +
            "\nHealth: " + health +
            "\nMagicka:" + magicka +
            "\nStrength: " + strength +
            "\nVitality: " + vitality +
            "\nIntelligence:" + intelligence +
            "\nMind" + mind +
            "\nDexterity" + dexterity +
            "\nLuck" + luck
        );
    }

    /*************
    GETTER METHODS
    *************/
    public int getTarget() { return target;}
    public char getTargetType() { return targetType;}
}
