import java.util.*;
import java.io.*;

abstract class Player extends Entity implements Serializable {
    //Score variable
    private int score;
    //Combat variables
    private int target;
    private char targetType;

    //Constructor
    public Player(String n, double[] stats, String job) {
        super(n, stats, job);
    }

    //WildDice Method
    public void selectDice(ArrayList<WildDice> wd) {
        int choice = 0;
        boolean valid = false;
        while(!valid) {
            Util.print("Your combatSpeed is " + combatSpeed);
            choice = Util.intInput("Choose a dice by entering the number");
            if (choice > 5 || choice < 0) {
                Util.print("That is not in range .Please try again");
            } else if (wd.get(choice).getSpeedCost() <= combatSpeed) {
                combatSpeed -= wd.get(choice).getSpeedCost() ;
                valid = true;
                addEffect(wd, choice);
            } else {
                Util.print("You decided to skip turn");
                addSpeed();
                skip = true;
                valid = true;
            }
        }
    }

    /*************
    COMBAT METHODS
    *************/
    public void selectTarget(ArrayList<Player> p, ArrayList<Enemy> e) {
        boolean valid = false;
        target = Util.intInput("At which target ?"
            + "\n1 " + e.get(0).getName() + "\n2 " + e.get(1).getName()
            + "\n3 " + e.get(2).getName() + "\n4 " + p.get(0).getName()
            + "\n5 " + p.get(1).getName() + "\n6 " + p.get(2).getName());
        while(!valid) {
            if (target < 1 || target > 6) {
                Util.print("That is not a valid target try again.");
            } else if (target > 3) {
                switch (target) {
                    case 4:
                        valid = true;
                        target = 0;
                        targetType = 'p';
                        return;
                    case 5:
                        valid = true;
                        target = 1;
                        targetType = 'p';
                        return;
                    case 6:
                        valid = true;
                        target = 2;
                        targetType = 'p';
                        return;
                }
            } else {
                valid = true;
                targetType = 'e';
                target--;
                return;
            }
        }
    }

    public void viewStats() {
        Util.print(
            "Name: " + name + 
            "\nLevel: " + level +
            "\nHealth: " + health +
            "\nMagicka:" + magicka +
            "\nStrength: " + strength +
            "\nVitality: " + vitality +
            "\nIntelligence: " + intelligence +
            "\nMind: " + mind +
            "\nDexterity: " + dexterity +
            "\nLuck: " + luck + "\n"
        );
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
            "\nDexterity: " + dexterity +
            "\nLuck: " + luck + "\n");
        return stats;
    }

    public void addXp(double experiencePoints) {
        xp += experiencePoints;
        if(xp > xpMax) {
            Util.print("You have leveled up");
            levelUp();
        } else {
            Util.print("XP to next level is " + (xpMax-xp));
        }
    }
    
    public void recoverHealth(double amount) {//Method that restores health to the player
        health += amount;
        if(health > maxHealth)//Set health to maxmimum amount if you overheal
            health = maxHealth;
    }
    
    public void recoverMagicka(double amount) {//Same method as recoverHealth but for magicka
        magicka += amount;
        if(magicka > maxMagicka)
            magicka = maxMagicka;
    }
    
    public void addScore(int amount) {
        score += amount;
    }
    
    public void levelUp() {
        level += 1;//Level up for enemy scaling
        xp = xpMax - xp;//Pass xP
        xpMax *= 1.1;//Make the next level take more xP
        for(int i = 0; i < baseStats.length; i++)
            baseStats[i] *= 1.1;//Increase all stats by 10%
        setStats(baseStats);
    }
    
    public void setStats(double[] stats) {//Method that changes all stats after leveling up                    
        health = stats[0];         maxHealth = stats[0];
        magicka = stats[1];        maxMagicka = stats[1];
        strength = stats[2];       vitality = stats[3];
        intelligence = stats[4];   mind = stats[5];
        dexterity = stats[6];      luck = stats[7];
        speed = stats[8];
    }

    /*************
    GETTER METHODS
    *************/
    public int getTarget() { return target;}
    public char getTargetType() { return targetType;}
    public int getScore() { return score;}
}
