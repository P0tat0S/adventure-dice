import java.io.*;

public class Player_Jester extends Player implements Serializable {
    public Player_Jester(String n, double[] stats, String job) {
        super(n, stats, job);
        Util.print(this.getName() + " the Jester was created.");
    }
    
    public void attack(Entity en) {//Jester copies the attacks of Rogue and Warrior
        double damage = 0;
        Util.print("You can imbue your sword with magic for the attack to ignore 25% of the defense or have greater chances to crit.");
        switch(Util.intInput("Enter '0' to attack normally" + 
        "\nEnter '1' to imbue the sword. Magicka Cost: 2" +
        "\nEnter '2' to increase the chance of a critical hit. Magicka Cost: 4")) {
            case 0:
                double critical1 = (double)Util.diceRoller(1,100);
                if(critical1 + getDexterity()/4 + getLuck()/2 > 95) {
                    Util.print("CRITICAL HIT");
                    damage = 2 * getStrength() * (1-en.getVitality()/100);
                }else
                    damage = getStrength() * (1-en.getVitality()/100);
                if(damage > 0)//If statement for the attacks to not give health
                    en.setHealth(-damage);
            case 1:
                magicka -= 2;
                double critical2 = (double)Util.diceRoller(1,100);
                if(critical2 + getDexterity()/4 + getLuck()/2 > 95){//Easier to crit
                    Util.print("CRITICAL HIT");
                    damage = 2 * getStrength() * (1-en.getVitality()/150);
                } else
                    damage = getStrength() * (1-en.getVitality()/150);
                if(damage > 0) 
                    en.setHealth(-damage);
                return;
            case 2:
                magicka -= 4;
                double critical3 = (double)Util.diceRoller(1,100);
                if(critical3 + getDexterity()/4 + getLuck()/2 > 45) {//Much Easier to crit
                    Util.print("CRITICAL HIT");
                    damage = 2 * getStrength() * (1-en.getVitality()/100);
                } else
                    damage = getStrength() * (1-en.getVitality()/100);
                if(damage > 0) 
                    en.setHealth(-damage);
                return;
            default:
                Util.print("You forget to attack and deal 0 damage");
                return;
        } 
    }

    public void magic(Entity en) {//Knows a few magic spells
        double damage = 0;
        Util.print("You have some knowledge of magic that lets you cast different magic");
        switch(Util.intInput("Enter '0' to cast Magic Missile. Mana Cost: 1" + 
            "\nEnter '1' to cast Frost Bolt. Mana Cost: 2" + 
            "\nEnter '2' to cast Fireball. Mana Cost: 4")) {
            case 0:
                magicka -= 1;
                damage = getIntelligence() * (1-en.getMind()/100);//1.0 Multiplier
                if(damage > 0)//If statement for the attacks to not give health
                    en.setHealth(-damage);
                return;
            case 1:
                magicka -= 2;
                damage = 1.2*getIntelligence() * (1-en.getMind()/100);//1.2 
                if(damage > 0)
                    en.setHealth(-damage);
                return;
            case 2:
                magicka -= 4;
                damage = 1.4*getIntelligence() * (1-en.getMind()/100);//1.4
                if(damage > 0)
                    en.setHealth(-damage);
                return;
            default:
                Util.print("You forget to cast magic and deal 0 damage");
                return;
        }
    }

    public void heal(Entity en) {
        Util.print("Your wits lets you heal more health");
        health += maxHealth/15;
    }
}
