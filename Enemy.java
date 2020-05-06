import java.util.*;

abstract class Enemy extends Entity {
    //Combat variables
    private int target;
    private char targetType;
    private int counter;

    //Constructor
    public Enemy(String n, double[] stats, String job) {
        super(n, stats, job);
    }

    //WildDice Method
    public void addDice(ArrayList<WildDice> wd) {
        int choice = 0;
        boolean valid = false;
        while(!valid) {//Loop until enemy chooses a usable dice, if not able skip turn
            choice = Util.diceRoller(0,5);
            if (wd.get(choice).getSpeedCost() <= combatSpeed) {
                valid = true;
            } else if (counter > 50) {
                Util.print("\nThe enemy has decided to skip turn.");
                choice = -1;
                valid = true;
            }
            counter++;
        }

        counter = 0;
        if(choice == -1) {//Enemy skips turn and gains more speed
            addSpeed();
            skip = true;
        } else {
            addEffect(wd, choice);
        }
    }

    /*************
    COMBAT METHODS
    *************/
    public void actionOnPlayer(Player p) {
        while(actionNumber > 0) {
            switch(Util.diceRoller(1,3)) {
                case 1:
                    attack(p);
                    Util.print(p.getName() + " Remaining health is " + p.getHealth());
                    break;
                case 2:
                    attack(p);
                    Util.print(p.getName() + " Remaining health is " + p.getHealth());
                    break;
                case 3:
                    heal(p);
                    Util.print(p.getName() + " Remaining health is " + p.getHealth());
                    break;
            }
            actionNumber--;
        }
    }

    public void selectTarget() {//Enemy randomly selects a player
        target = Util.diceRoller(0,2);
        targetType = 'p';
    }

    public double givenXP() {
        return maxHealth/2 + maxMagicka/2 + strength + vitality + intelligence + mind + dexterity + speed;
    }
    
    public String JStats() {
        String stats = ("Name: " + name + 
            "\nLevel: " + level +
            "\nHealth: " + health +
            "\nMagicka:" + magicka +
            "\nStrength: " + strength +
            "\nVitality: " + vitality +
            "\nIntelligence: " + intelligence +
            "\nMind: " + mind +
            "\nDexterity: " + dexterity);
        return stats;
    }

    /*************
    GETTER METHODS
    *************/
    public int getTarget() { return target;}
    public char getTargetType() { return targetType;}
}
