import java.io.*;

public class Player_Rogue extends Player implements Serializable {
    public Player_Rogue(String n, double[] stats, String job) {
        super(n, stats, job);
        Util.print(this.getName() + " the Rogue was created.");
    }
    
    public void attack(Entity en) {//Rogues can use magicka to increase crit chance
        double damage = 0;
        Util.print("You can use your magicka to guarantee a critical strike");
        switch(Util.intInput("Enter '0' to attack normally or '1' to crit. Magicka cost is 4")) {
            case 0:
                double critical1 = (double)Util.diceRoller(1,100);
                if(critical1 + getDexterity()/4 + getLuck()/2 > 95) {
                    Util.print("CRITICAL HIT");
                    damage = 2 * getStrength() * (1-en.getVitality()/100);
                } else
                    damage = getStrength() * (1-en.getVitality()/100);
                if(damage > 0)//If statement for the attacks to not give health
                    en.setHealth(-damage);
                return;
            case 1:
                magicka -= 4;
                double critical2 = (double)Util.diceRoller(1,100);
                if(critical2 + getDexterity()/4 + getLuck()/2 > 1) {//Guaranteed crit
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
    
    public void defend(Entity en) {//Rogue normal defense
        Util.print("You hide in shadows in hope to evade the attack");
        luck *= 4;
        Util.print("New improved vitality:" + vitality);
    }

    public void magic(Entity en) {//Same formula as attack but with basic magic
        Util.print("As a warrior you only know a basic spell. /nYou cast magic missile");
        double damage = getIntelligence() * (1-en.getMind()/100);
        magicka -= 1;
        en.setHealth(-damage);
    }
}
