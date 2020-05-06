import java.util.*;
import java.io.*;

abstract class Entity implements Serializable {
    //Entity main info
    protected String name;
    protected String job;
    protected int level;
    //Entity level system;
    protected double xp;
    protected double xpMax;
    //Entity health
    protected double health;
    protected double maxHealth;
    //Entity magic points
    protected double magicka;
    protected double maxMagicka;
    //Entity physical damage
    protected double strength;
    protected double vitality;
    //Entity magical damage
    protected double intelligence;
    protected double mind;
    //Entity Accuracy
    protected double dexterity;
    protected double luck;
    //Entity Actions
    protected double speed;
    protected boolean skip;
    protected double combatSpeed;
    protected int actionNumber;
    //Entity Base stats
    protected double[] baseStats;

    //Constructor
    public Entity(String n, double[] stats, String role) {
        if(this instanceof Player) {
            name = n;
            level = 1;                 speed = stats[8];
            xp = 0.0;                  xpMax = 100.0;
            health = stats[0];         maxHealth = stats[0];
            magicka = stats[1];        maxMagicka = stats[1];
            strength = stats[2];       vitality = stats[3];
            intelligence = stats[4];   mind = stats[5];
            dexterity = stats[6];      luck = stats[7];
            baseStats = stats.clone(); job = role;
        } else {
            name = n;
            level = 1;
            health = stats[0];         maxHealth = stats[0];
            magicka = stats[1];        maxMagicka = stats[1];
            strength = stats[2];       vitality = stats[3];
            intelligence = stats[4];   mind = stats[5];
            dexterity = stats[6];      speed = stats[7];
            baseStats = stats.clone(); job = role;
        }
    }//End of the Constructor

    /***********
    DICE METHODS
    ***********/
    protected void addEffect(ArrayList<WildDice> wd, int choice) {//Add the dice effect
        double rD = 0;//Store the rolled value
        if(wd.get(choice).getDiceNumber().length() > 4) {//String parser for multiple dice
            String[] dN = wd.get(choice).getDiceNumber().split(",");
            String[] dT = wd.get(choice).getDiceType().split(",");
            for(int i = 0; dN.length > i; i++) {//Depending on how many dice you get and action
                rD = confirmDice(dN[i]);
                rollDice(dT[i], rD);
                actionNumber++;
            }
        } else {//This else statement is for only one die
            rD = confirmDice(wd.get(choice).getDiceNumber());
            rollDice(wd.get(choice).getDiceType(), rD);
            actionNumber++;
        }
    }//End addEffect

    private double confirmDice(String diceNumber) {
        switch (diceNumber) {//Different bonus for each die
            case "D20":
                return 2*Util.diceRoller(1, 20);
            case "D12":
                return 1.6*Util.diceRoller(1, 12);
            case "D8":
                return 1.4*Util.diceRoller(1, 8);
            case "D6":
                return 1.2*Util.diceRoller(1, 6);
            case "D4":
                return Util.diceRoller(1, 4);
        }
        return 0;
    }

    private void rollDice(String diceType, double rolledNum) {
        switch (diceType) {
            case "Violet":
                combatSpeed += rolledNum;
                Util.print(this.getName() + " new combatSpeed " + combatSpeed);
                break;
            case "Orange":
                dexterity = dexterity*(1+rolledNum/20);
                Util.print(this.getName() +" new dexterity " + dexterity);
                break;
            case "Yellow":
                if(this instanceof Player) {
                    luck = luck*(1+rolledNum/20);
                    Util.print(this.getName() + " new luck " + dexterity);
                } else {//Monsters don't have luck
                    dexterity = dexterity*(1+rolledNum/40);
                    Util.print(this.getName() +" new dexterity " + dexterity);
                    strength = strength*(1+rolledNum/40);
                    Util.print(this.getName() + " new strength " + strength);
                }
                break;
            case "White":
                vitality = vitality*(1+rolledNum/20);
                Util.print(this.getName() + " new vitality " + vitality);
                break;
            case "Green":
                mind = mind*(1+rolledNum/20);
                Util.print(this.getName() + " new mind " + mind);
                break;
            case "Blue":
                intelligence = intelligence*(1+rolledNum/20);
                Util.print(this.getName() + " new intelligence " + intelligence);
                break;
            case "Red":
                strength = strength*(1+rolledNum/20);
                Util.print(this.getName() + " new strength " + strength);
                break;
        }
    }

    /*************
    COMBAT METHODS
    *************/
    public void actionOnPlayer(Player p) {
        while(actionNumber > 0) {
            switch(Util.intInput("\n1 to Attack" + "\n2 to Defend" + "\n3 to Cast Magic" + "\n4 to Heal")) {
                case 1:
                    attack(p);
                    Util.print(p.getName() + " Remaining health is " + p.getHealth());
                    break;
                case 2:
                    defend(p);
                    break;
                case 3:
                    magic(p);
                    Util.print(p.getName() + " Remaining health is " + p.getHealth());
                    break;
                case 4:
                    heal(p);
                    if(this instanceof Enemy)
                        Util.print(name + " Remaining health is " + health);
                    else
                        Util.print(p.getName() + " Remaining health is " + p.getHealth());
                    break;
                default:
                    Util.print("You skip turn");
                    break;
            }
            actionNumber--;
        }
    }

    public Enemy actionOnEnemy(Enemy e) {
        Util.print(name + "'s Turn");
        while(actionNumber > 0) {
            switch(Util.intInput("\n1 to attack" + "\n2 to Defend" + "\n3 to cast Magic" + "\n4 to Heal")) {
                case 1:
                    attack(e);
                    Util.print(e.getName() + " Remaining health is " + e.getHealth());
                    break;
                case 2:
                    defend(e);
                    break;
                case 3:
                    magic(e);
                    Util.print(e.getName() + " Remaining health is " + e.getHealth());
                    break;
                case 4:
                    heal(e);
                    Util.print(e.getName() + " Remaining health is " + e.getHealth());
                    break;
                default:
                    Util.print("You skip turn");
                    break;
            }
            actionNumber--;
        }
        return e;
    }

    public void normalise() {//Return all base stats of players and enemies
        if(this instanceof Player) {
            speed = baseStats[8];          luck = baseStats[7];
            strength = baseStats[2];       vitality = baseStats[3];
            intelligence = baseStats[4];   mind = baseStats[5];
            dexterity = baseStats[6];      skip = false;
        } else {
            strength = baseStats[2];       vitality = baseStats[3];
            intelligence = baseStats[4];   mind = baseStats[5];
            dexterity = baseStats[6];      speed = baseStats[7];    skip = false;
        }
    }

    public void addSpeed() {//Simple method that acts as a counter to add speed
        combatSpeed += speed;
    }

    public void attack(Entity en) {
        Util.print(name + " is Attacking...");
        double damage = 0;
        double critical = (double)Util.diceRoller(1,100);
        if(critical + getDexterity()/4 > 95) {
            damage = 2 * getStrength() * (1-en.getVitality()/100);
            en.setHealth(-damage);
        } else {
            damage = getStrength() * (1-en.getVitality()/100);
            en.setHealth(-damage);
        }
    }

    public void defend(Entity en) {
        Util.print("You enter in a defensive stance");
        vitality *= 4;
        Util.print("New vitality: " + vitality);
    }

    public void magic(Entity en) {
        Util.print("It was not able to cast magic");
    }

    public void heal(Entity en) {
        Util.print(name + " takes a turn to heal up");
        health += maxHealth/20;
    }

    /*************
    SETTER METHODS
    *************/
    public void setHealth(double n) {
        health += n;
    }
    
    public void setMagicka(double n ) {
        magicka += n;
    }

    /*************
    GETTER METHODS
    *************/
    public String getName() { return name;}
    public int getLevel() { return level;}
    public double getXp() { return xp;}
    public double getHealth() { return health;}
    public double getMagicka() { return magicka;}
    public double getStrength() { return strength;}
    public double getVitality() { return vitality;}
    public double getIntelligence() { return intelligence;}
    public double getMind() { return mind;}
    public double getDexterity() { return dexterity;}
    public double getLuck() { return luck;}
    public double getSpeed() { return speed;}
    public boolean getSkip() { return skip;}
    public String getJob() { return job;}
}
