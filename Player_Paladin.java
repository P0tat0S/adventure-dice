import java.io.*;

public class Player_Paladin extends Player implements Serializable {
    public Player_Paladin(String n, double[] stats, String job) {
        super(n, stats, job);
        Util.print(this.getName() + " the Paladin was created.");
    }

    public void attack(Entity en) {//Paladins can smite enemies.
        double damage = 0;
        Util.print("You can smite your enemies with holy magic, uses Mind to attack");
        switch(Util.intInput("Enter '0' to attack normally or '1' to smite thy enemies. Magicka Cost: 4")) {
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
                magicka -= 4;
                double critical2 = (double)Util.diceRoller(1,100);
                if(critical2 + getDexterity()/4 + getLuck()/2 > 90){
                    Util.print("CRITICAL HIT");
                    damage = 2 * getMind() + 2 * getStrength() * (1-en.getVitality()/100);
                }else
                    damage = getMind() + getStrength() * (1-en.getVitality()/100);
                if(damage > 0) 
                    en.setHealth(-damage);
                return;
            default:
                Util.print("You forget to attack and deal 0 damage");
                return;
        } 
    }
    
    public void defend(Entity en) {//Paladins also have a great defense
        Util.print("You take your holy shield and pray");
        vitality *= 7;
        Util.print("New improved vitality:" + vitality);
    }

    public void magic(Entity en) {//Basic magic
        Util.print("As a warrior you only know a basic spell. /nYou cast magic missile");
        double damage = getIntelligence() * (1-en.getMind()/100);
        magicka -= 1;
        en.setHealth(-damage);
    }

    public void heal(Entity en) {//Paladins can also heal but they have decreased healing
        double amount = 0;
        Util.print("You pray to your god and receive healing powers");
        switch(Util.intInput("Enter '0' to cast Lesser Heal. Mana Cost: 2" + 
            "\nEnter '1' to cast Healing Hands. Mana Cost: 4")) {
            case 0:
                magicka -= 2;
                amount = 0.4*getMind();
                en.setHealth(amount);
                return;
            case 1:
                magicka -= 4;
                amount = 0.8*getMind();
                en.setHealth(amount);
                return;
            default:
                Util.print("You forget to cast a healing spell and heal 0");
                return;
        }
    }

    public void setHealth(double n) {//Paladins also have resistance, reduce all damage 5%
        super.setHealth(n*0.95);
    }
}
