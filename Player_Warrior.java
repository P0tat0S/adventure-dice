import java.io.*;

public class Player_Warrior extends Player implements Serializable {
    public Player_Warrior(String n, double[] stats, String job) {
        super(n, stats, job);
        Util.print(this.getName() + " the Warrior was created.");
    }

    public void attack(Entity en) {//Warriors have improve attacks by using magic
        double damage = 0;
        Util.print("You can imbue your sword with magic for the attack to ignore half of the defense");
        switch(Util.intInput("Enter '0' to attack normally or '1' to imbue the sword. Magicka cost is 2")) {
            case 0:
                double critical1 = (double)Util.diceRoller(1,100);
                if(critical1 + getDexterity()/4 + getLuck()/2 > 95){
                    Util.print("CRITICAL HIT");
                    damage = 2 * getStrength() * (1-en.getVitality()/100);
                }else
                    damage = getStrength() * (1-en.getVitality()/100);
                if(damage > 0)//If statement for the attacks to not give health
                    en.setHealth(-damage);
                return;
            case 1:
                magicka -= 2;
                double critical2 = (double)Util.diceRoller(1,100);
                if(critical2 + getDexterity()/4 + getLuck()/2 > 90){//Easier to crit
                    Util.print("CRITICAL HIT");
                    damage = 2 * getStrength() * (1-en.getVitality()/200);
                }else
                    damage = getStrength() * (1-en.getVitality()/200);
                if(damage > 0) 
                    en.setHealth(-damage);
                return;
            default:
                Util.print("You forget to attack and deal 0 damage");
                return;
        } 
    }
    
    public void defend(Entity en) {//Warriors have better defense
        Util.print("You take your shield and get ready for impact");
        vitality *= 8;
        Util.print("New improved vitality:" + vitality);
    }

    public void magic(Entity en) {//Same formula as attack but with basic magic
        Util.print("As a warrior you only know a basic spell." + "/nYou cast magic missile");
        double damage = getIntelligence() * (1-en.getMind()/100);
        magicka -= 1;
        en.setHealth(-damage);
    }

    public void heal(Entity en) {//Method that heals warrior for 10% of its health
        Util.print("Your resilience lets you heal more health");
        health += maxHealth/10;
    }

    public void setHealth(double n) {//Warrior passive innate resistance, reduce all damage 10%
        super.setHealth(n*0.9);
    }
}
