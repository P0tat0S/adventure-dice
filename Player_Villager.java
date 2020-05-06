import java.io.*;

public class Player_Villager extends Player implements Serializable {
    public Player_Villager(String n, double[] stats, String job) {
        super(n, stats, job);
        Util.print(this.getName() + " the Villager was created.");
    }
    
    public void attack(Entity en) {//Villagers are lucky with attacks
        double damage = 0;
        Util.print("You stab the enemy with a pitchfork");
        double critical1 = (double)Util.diceRoller(1,100);
        if(critical1 + getDexterity()/2 + getLuck() > 90){//High chance of criting
            Util.print("CRITICAL HIT");
            damage = 2 * getStrength() * (1-en.getVitality()/100);
        } else
            damage = getStrength() * (1-en.getVitality()/100);
        if(damage > 0)//If statement for the attacks to not give health
            en.setHealth(-damage);
        return;
    }
    
    public void defend(Entity en) {//Villager has worst defense
        double critical2 = (double)Util.diceRoller(1,100);
        if(critical2 + getDexterity() + getLuck() > 90) {
            Util.print("You got lucky and evade the attack");
            vitality *= 2000;
        } else {
            Util.print("You cannot defend at all");
            vitality *= 2;
        }
        Util.print("New vitality:" + vitality);
    }

    public void heal(Entity en) {//You almost do not heal at all
        Util.print("You hide and try to heal by eating potatoes. It is not effective");
        health += maxHealth/40;
    }
}
