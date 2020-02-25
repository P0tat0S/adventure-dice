import java.util.*;

abstract class Enemy extends AdventureDice {
    //Character main info
    private String name;
    private int level;
    //Character health
    private double health;
    private double maxHealth;
    //Character magic points
    private double magicka;
    private double maxMagicka;
    //Character physical damage
    private double strength;
    private double vitality;
    //Character magical damage
    private double intelligence;
    private double mind;
    //Character Accuracy
    private double dexterity;
    //Character Actions
    private double speed;
    //Combat variables
    private boolean skip;
    private double combatSpeed = 0;
    private int counter;
    private int target;
    private char targetType;
    private int actionNumber;

    public Enemy(String n, double[] stats) {
        name = n;
        level = 1;
        health = stats[0];         maxHealth = stats[0];
        magicka = stats[1];        maxMagicka = stats[1];
        strength = stats[2];       vitality = stats[3];
        intelligence = stats[4];   mind = stats[5];
        dexterity = stats[6];      speed = stats[7];
    }

    public void addDice(ArrayList<WildDice> wd) {
        int choice = 0;
        boolean valid = false;
        while(!valid) {
            choice = AdventureDice.diceRoller(0,5);
            if (wd.get(choice).getSpeedCost() <= combatSpeed) {
                valid = true;
            } else if (counter > 60) {
                System.out.println("\nThe enemy has decided to skip turn.");
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

    public void addEffect(ArrayList<WildDice> wd, int choice) {
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

    public double confirmDice(String diceNumber) {
        switch (diceNumber) {
            case "D20":
                return 2*AdventureDice.diceRoller(1, 20);
            case "D12":
                return 1.6*AdventureDice.diceRoller(1, 12);
            case "D8":
                return 1.4*AdventureDice.diceRoller(1, 8);
            case "D6":
                return 1.2*AdventureDice.diceRoller(1, 6);
            case "D4":
                return AdventureDice.diceRoller(1, 4);
        }
        return 0;
    }

    public void rollDice(String diceType, double rolledNum) {
        switch (diceType) {
            case "Violet":
                combatSpeed += rolledNum;
                System.out.println("new combatSpeed " + combatSpeed);
                break;
            case "Orange":
                dexterity = dexterity*(1+rolledNum/20);
                System.out.println("new dexterity " + dexterity);
                break;
            case "Yellow":
                dexterity = dexterity*(1+rolledNum/40);
                System.out.println("new dexterity " + dexterity);
                strength = strength*(1+rolledNum/40);
                System.out.println("new strength " + strength);
                break;
            case "White":
                vitality = vitality*(1+rolledNum/20);
                System.out.println("new vitality " + vitality);
                break;
            case "Green":
                mind = mind*(1+rolledNum/20);
                System.out.println("new mind " + mind);
                break;
            case "Blue":
                intelligence = intelligence*(1+rolledNum/20);
                System.out.println("new intelligence " + intelligence);
                break;
            case "Red":
                strength = strength*(1+rolledNum/20);
                System.out.println("new strength " + strength);
                break;
        }
    }

    public Player performActionP(Player p) {
        if(skip == true) { skip = false; return p; }
        while(actionNumber > 0) {
            switch(1) {
                case 1:
                    p.setHealth(attack(p.getVitality()));
                    System.out.println(p.getName() + " Remaining health is " + p.getHealth());
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
                    System.out.println(e.getName() + " Remaining health is " + e.getHealth());
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

    public double attack(double vit) {
        System.out.println(name + " is Attacking...");
        double damage = strength * (1-vit/100);
        return -damage;
    }

    public void defend() {
        if(this instanceof Enemy_Slime)
            AdventureDice.print("");
        else
            AdventureDice.print("It was not able to Defend");
        return;
    }

    public void magic() {
        if(this instanceof Enemy_Slime)
            AdventureDice.print("");
        else
            AdventureDice.print("It was not able to cast Magic");
        return;
    }

    public void heal() {
        if(this instanceof Enemy_Slime)
            AdventureDice.print("");
        else
            AdventureDice.print("It was not able to cast Heal");
        return;
    }

    public void selectTarget() {
        target = AdventureDice.diceRoller(0,2);
        targetType = 'p';
    }

    public void addSpeed() {
        combatSpeed += speed;
    }

    public void setHealth(double n) {
        health += n;
    }

    public String getName() { return name;}
    public int getLevel() { return level;}
    public double getHealth() { return health;}
    public double getMagicka() { return magicka;}
    public double getStregth() { return strength;}
    public double getVitality() { return vitality;}
    public double getIntelligence() { return intelligence;}
    public double getMind() { return mind;}
    public double getDexterity() {return dexterity;}
    public double getSpeed() {return speed;}
    public int getTarget() { return target;}
    public char getTargetType() { return targetType;}

}
