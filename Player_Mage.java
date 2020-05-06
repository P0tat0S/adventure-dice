import java.io.*;

public class Player_Mage extends Player implements Serializable {
    public Player_Mage(String n, double[] stats, String job) {
        super(n, stats, job);
        Util.print(this.getName() + " the Mage was created.");
    }

    public void attack(Entity en) {//Normal attack with chance to crit
        double damage = 0;
        double critical1 = (double)Util.diceRoller(1,100);
        if(critical1 + getDexterity()/4 + getLuck()/2 > 95) {
            Util.print("CRITICAL HIT");
            damage = 2 * getStrength() * (1-en.getVitality()/100);
        } else
            damage = getStrength() * (1-en.getVitality()/100);
        if(damage > 0)//If statement for the attacks to not give health
            en.setHealth(-damage);
        return;
    }
    
    public void defend(Entity en) {//Mages have low defense
        Util.print("You go into a defensive stance");
        vitality *= 3;
        Util.print("New vitality:" + vitality);
    }

    public void magic(Entity en) {//Same formula as attack but with basic magic
        double damage = 0;
        Util.print("Your vast knowledge of magic lets you cast different magic");
        switch(Util.intInput("Enter '0' to cast Magic Missile. Mana Cost: 1" + 
            "\nEnter '1' to cast Frost Bolt. Mana Cost: 2" + 
            "\nEnter '2' to cast Fireball. Mana Cost: 4" + 
            "\nEnter '3' to cast Thundercrack. Mana Cost: 8" + 
            "\nEnter '4' to cast Void Blast. Mana Cost: 16")) {
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
            case 3:
                magicka -= 8;
                damage = 1.6*getIntelligence() * (1-en.getMind()/200);//1.6 Mult. and ignore half Magic def
                if(damage > 0)
                    en.setHealth(-damage);
                return;
            case 4:
                magicka -= 16;
                damage = 2.0*getIntelligence() * (1-en.getMind()/400);//2.0 Mult. and ignore 75% magic def
                if(damage > 0)
                    en.setHealth(-damage);
                return;
            default:
                Util.print("You forget to cast magic and deal 0 damage");
                return;
        }
    }

    public void setHealth(double n) {//Mages are weaker and more prone to damage
        super.setHealth(n*1.05);
    }
}
