public class Enemy_Orc extends Enemy {
    public Enemy_Orc(String n, double[] stats, String job) {
        super(n, stats, job);
        Util.print(this.getName()+ " the Orc appeared.");
    }
    
    public void attack(Entity en) {//Orcs can crit
        Util.print(name + " is Attacking...");
        double damage = 0;
        double critical = (double)Util.diceRoller(1,100);
        if(critical + getDexterity()/2 > 95) {
            Util.print("The enemy hits a devastating blow");
            damage = 2 * getStrength() * (1-en.getVitality()/100);
        } else
            damage = getStrength() * (1-en.getVitality()/100);
        if(damage > 0)//If statement for the attacks to not give health
            en.setHealth(-damage);
        return;
    }

    public void heal(Entity en) {//Orcs can regenarate better
        Util.print(name + " takes a turn to heal up");
        health += maxHealth/10;
    }
}
