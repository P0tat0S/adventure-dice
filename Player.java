import java.util.*;

abstract class Player extends AdventureDice {
    //Character main info
    private String name;
    private int level;
    //Character level system;
    private double xp;
    private double xpMax;
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
    private double luck;
    //Character Actions
    private double speed;
    //Combat variables
    private boolean skip;
    private double combatSpeed = 0;
    private int target;
    private char targetType;
    private int actionNumber;

    public Player(String n, double[] stats) {
        name = n;
        level = 1;                 speed = stats[8];
        xp = 0.0;                  xpMax = 100.0;
        health = stats[0];         maxHealth = stats[0];
        magicka = stats[1];        maxMagicka = stats[1];
        strength = stats[2];       vitality = stats[3];
        intelligence = stats[4];   mind = stats[5];
        dexterity = stats[6];      luck = stats[7];
    }

    public void selectDice(ArrayList<WildDice> wd) {
        int choice = 0;
        boolean valid = false;
        while(!valid) {
            System.out.println("Your combatSpeed is " + combatSpeed);
            choice = receiveInput("Choose a dice by entering the number");
            if (wd.get(choice).getSpeedCost() <= combatSpeed) {
                combatSpeed -= wd.get(choice).getSpeedCost() ;
                valid = true;
            } else {
                System.out.println("You decided to skip turn");
                addSpeed();
                skip = true;
                valid = true;
            }
            addEffect(wd, choice);
        }
    }

    private void addEffect(ArrayList<WildDice> wd, int choice) {
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

    private void rollDice(String diceType, double rolledNum) {
        switch (diceType) {
            case "Violet":
                combatSpeed += rolledNum;
                System.out.println(this.getName() + " new combatSpeed " + combatSpeed);
                break;
            case "Orange":
                dexterity = dexterity*(1+rolledNum/20);
                System.out.println(this.getName() +" new dexterity " + dexterity);
                break;
            case "Yellow":
                luck = luck*(1+rolledNum/20);
                System.out.println(this.getName() + " new luck " + dexterity);
                break;
            case "White":
                vitality = vitality*(1+rolledNum/20);
                System.out.println(this.getName() + " new vitality " + vitality);
                break;
            case "Green":
                mind = mind*(1+rolledNum/20);
                System.out.println(this.getName() + " new mind " + mind);
                break;
            case "Blue":
                intelligence = intelligence*(1+rolledNum/20);
                System.out.println(this.getName() + " new intelligence " + intelligence);
                break;
            case "Red":
                strength = strength*(1+rolledNum/20);
                System.out.println(this.getName() + " new strength " + strength);
                break;
        }
    }

    public void addSpeed() {
        combatSpeed += speed;
    }

    public void selectTarget(ArrayList<Player> p, ArrayList<Enemy> e) {
        target = receiveInput("At which target ?"
            + "\n1 " + e.get(0).getName() + "\n2 " + e.get(1).getName()
            + "\n3 " + e.get(2).getName() + "\n4 " + p.get(0).getName()
            + "\n5 " + p.get(1).getName() + "\n6 " + p.get(2).getName());
        if (target <= 0 || target > 6) {
            System.out.println("That is not a valid target try again.");
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

    public Player performActionP(Player p) {
        if(skip == true) { skip = false; return p; }
        while(actionNumber > 0) {
            switch(receiveInput("\n1 to Attack" + "\n2 to Defend"
            + "\n3 to Cast Magic" + "\n4 to Heal")) {
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
                default:
                    System.out.println("Thanks for not complying");
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
            switch(receiveInput("\n1 to attack" + "\n2 to Defend"
            + "\n3 to cast Magic" + "\n4 to Heal")) {
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
                default:
                    System.out.println("Thanks for not complying");
                    System.exit(0);
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
        if(this instanceof Player_Villager)
            AdventureDice.print("");
        else
            AdventureDice.print("It was not able to Defend");
        return;
    }

    public void magic() {
        if(this instanceof Player_Mage)
            AdventureDice.print("");
        else
            AdventureDice.print("It was not able to cast Magic");
        return;
    }

    public void heal() {
        if(this instanceof Player_Cleric)
            AdventureDice.print("");
        else
            AdventureDice.print("It was not able to cast Heal");
        return;
    }

    private int receiveInput(String message) {
        System.out.println(message);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public String getName() { return name;}
    public int getLevel() { return level;}
    public double getXp() { return xp;}
    public double getHealth() { return health;}
    public double getMagicka() { return magicka;}
    public double getStregth() { return strength;}
    public double getVitality() { return vitality;}
    public double getIntelligence() { return intelligence;}
    public double getMind() { return mind;}
    public double getDexterity() { return dexterity;}
    public double getLuck() { return luck;}
    public double getSpeed() { return speed;}
    public int getTarget() { return target;}
    public char getTargetType() { return targetType;}

    public void setHealth(double n) {
        health += n;
    }
}
