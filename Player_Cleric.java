import java.io.*;

public class Player_Cleric extends Player implements Serializable {
    public Player_Cleric(String n, double[] stats, String job) {
        super(n, stats, job);
        Util.print(this.getName()+ " the Cleric was created.");
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
    
    public void defend(Entity en) {//Same as mages, clerics ahve low defense
        Util.print("You go into a defensive stance");
        vitality *= 3;
        Util.print("New vitality:" + vitality);
    }

    public void magic(Entity en) {//A few holy spells, use Mind instead of Intelligence
        double damage = 0;
        Util.print("You pray to you god and unleash different magic");
        switch(Util.intInput("Enter '0' to cast Sacred Flame. Mana Cost: 1" + 
            "\nEnter '1' to cast Guiding Bolt. Mana Cost: 2" + 
            "\nEnter '2' to cast Holy Smite. Mana Cost: 4")) {
            case 0:
                magicka -= 1;
                damage = getMind() * (1-en.getMind()/100);//1.0 Multiplier
                if(damage > 0)
                    en.setHealth(-damage);
                return;
            case 1:
                magicka -= 2;
                damage = 1.2*getMind() * (1-en.getMind()/100);//1.2 
                if(damage > 0)
                    en.setHealth(-damage);
                return;
            case 2:
                magicka -= 4;
                damage = 1.4*getMind() * (1-en.getMind()/100);//1.4
                if(damage > 0)
                    en.setHealth(-damage);
                return;
            default:
                Util.print("You forget to cast magic and deal 0 damage");
                return;
        }
    }

    public void heal(Entity en) {//Cleric Method for healing
        double amount = 0;
        Util.print("As a cleric you specialize on healing.");
        switch(Util.intInput("Enter '0' to cast Lesser Heal. Mana Cost: 2" + 
            "\nEnter '1' to cast Healing Hands. Mana Cost: 4" + 
            "\nEnter '2' to cast Greater Heal. Mana Cost: 8")) {
            case 0:
                magicka -= 2;
                amount = 0.5*getMind();
                en.setHealth(amount);
                return;
            case 1:
                magicka -= 4;
                amount = 1.0*getMind();
                en.setHealth(amount);
                return;
            case 2:
                magicka -= 8;
                amount = 1.5*getMind();
                en.setHealth(amount);
                return;
            default:
                Util.print("You forget to cast a healing spell and heal 0");
                return;
        }
    }

    public void setHealth(double n) {//As mages clerics are prone to damage
        super.setHealth(n*1.05);
    }
}
