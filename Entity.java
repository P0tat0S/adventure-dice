import java.util.*;

abstract class Entity {
    //Entity main info
    protected String name;
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

    //Constructor
    public Entity(String n, double[] stats) {
        if(this instanceof Player) {
            name = n;
            level = 1;                 speed = stats[8];
            xp = 0.0;                  xpMax = 100.0;
            health = stats[0];         maxHealth = stats[0];
            magicka = stats[1];        maxMagicka = stats[1];
            strength = stats[2];       vitality = stats[3];
            intelligence = stats[4];   mind = stats[5];
            dexterity = stats[6];      luck = stats[7];
        } else {
            name = n;
            level = 1;
            health = stats[0];         maxHealth = stats[0];
            magicka = stats[1];        maxMagicka = stats[1];
            strength = stats[2];       vitality = stats[3];
            intelligence = stats[4];   mind = stats[5];
            dexterity = stats[6];      speed = stats[7];
        }
    }

    /***********
    DICE METHODS
    ***********/
    protected void addEffect(ArrayList<WildDice> wd, int choice) {
        double rD = 0;
        if(wd.get(choice).getDiceNumber().length() > 4) {
            String[] dN = wd.get(choice).getDiceNumber().split(",");
            String[] dT = wd.get(choice).getDiceType().split(",");
            for(int i = 0; dN.length > i; i++) {
                rD = confirmDice(dN[i]);
                rollDice(dT[i], rD);
                actionNumber++;
            }
        } else {
            rD = confirmDice(wd.get(choice).getDiceNumber());
            rollDice(wd.get(choice).getDiceType(), rD);
            actionNumber++;
        }
    }

    private double confirmDice(String diceNumber) {
        switch (diceNumber) {
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
                } else {

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
    public Player performActionP(Player p) {
        if(skip == true) { skip = false; return p; }
        while(actionNumber > 0) {
            switch(Util.intInput("\n1 to Attack" + "\n2 to Defend"
            + "\n3 to Cast Magic" + "\n4 to Heal")) {
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
                default:
                    Util.print("Thanks for not complying");
                    System.exit(0);
                    break;
            }
            actionNumber--;
        }
        return p;
    }

    public Enemy performActionE(Enemy e) {
        if(skip == true) { skip = false; return e; }
        while(actionNumber > 0) {
            switch(Util.intInput("\n1 to attack" + "\n2 to Defend"
            + "\n3 to cast Magic" + "\n4 to Heal")) {
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
                default:
                    Util.print("Thanks for not complying");
                    System.exit(0);
                    break;
            }
            actionNumber--;
        }
        return e;
    }

    public void addSpeed() {
        combatSpeed += speed;
    }

    public double attack(double vit) {
        Util.print(name + " is Attacking...");
        double damage = strength * (1-vit/100);
        return -damage;
    }

    public void defend() {
        if(this instanceof Player_Villager)
            Util.print("");
        else
            Util.print("It was not able to Defend");
        return;
    }

    public void magic() {
        if(this instanceof Player_Mage)
            Util.print("");
        else
            Util.print("It was not able to cast Magic");
        return;
    }

    public void heal() {
        if(this instanceof Player_Cleric)
            Util.print("");
        else
            Util.print("It was not able to cast Heal");
        return;
    }

    /*************
    SETTER METHODS
    *************/
    public void setHealth(double n) {
        health += n;
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
}
