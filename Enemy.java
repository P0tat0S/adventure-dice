import java.util.*;

abstract class Enemy extends Entity {
    //Combat variables
    private int target;
    private char targetType;
    private int counter;

    //Constructor
    public Enemy(String n, double[] stats) {
        super(n, stats);
    }

    //WildDice Method
    public void addDice(ArrayList<WildDice> wd) {
        int choice = 0;
        boolean valid = false;
        while(!valid) {
            choice = Util.diceRoller(0,5);
            if (wd.get(choice).getSpeedCost() <= combatSpeed) {
                valid = true;
            } else if (counter > 60) {
                Util.print("\nThe enemy has decided to skip turn.");
                choice = -1;
                valid = true;
            }
            counter++;
        }

        counter = 0;
        if(choice == -1) {
            addSpeed();
            skip = true;
        } else {
            addEffect(wd, choice);
        }
    }

    /*************
    COMBAT METHODS
    *************/
    public Player performActionP(Player p) {
        if(skip == true) { skip = false; return p; }
        while(actionNumber > 0) {
            switch(1) {
                case 1:
                    p.setHealth(attack(p.getVitality()));
                    Util.print(p.getName() + " Remaining health is " + p.getHealth());
                    break;
                case 2:
                    defend();
                    break;
                case 3:
                    magic();
                    break;
                case 4:
                    heal();
                    break;
            }
            actionNumber--;
        }
        return p;
    }

    public Enemy performActionE(Enemy e) {
        if(skip == true) { skip = false; return e; }
        while(actionNumber > 0) {
            switch(1) {
                case 1:
                    e.setHealth(attack(e.getVitality()));
                    Util.print(e.getName() + " Remaining health is " + e.getHealth());
                    break;
                case 2:
                    defend();
                    break;
                case 3:
                    magic();
                    break;
                case 4:
                    heal();
                    break;
            }
            actionNumber--;
        }
        return e;
    }

    public void selectTarget() {
        target = Util.diceRoller(0,2);
        targetType = 'p';
    }

    /*************
    GETTER METHODS
    *************/
    public int getTarget() { return target;}
    public char getTargetType() { return targetType;}
}
