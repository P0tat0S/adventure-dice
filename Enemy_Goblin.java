public class Enemy_Goblin extends Enemy {
    public Enemy_Goblin(String n, double[] stats, String job) {
        super(n, stats, job);
        Util.print(this.getName()+ " the Goblin appeared.");
    }
    
    public void attack(Entity en) {//Goblins have higher chances to crit
        Util.print(name + " is Attacking...");
        double damage = 0;
        double critical = (double)Util.diceRoller(1,100);
        if(critical + getDexterity()/2 > 90) {
            Util.print("The enemy hits a devastating blow");
            damage = 2 * getStrength() * (1-en.getVitality()/100);
        } else
            damage = getStrength() * (1-en.getVitality()/100);
        if(damage > 0)//If statement for the attacks to not give health
            en.setHealth(-damage);
        return;
    }

    public void heal(Entity en) {//Less effective healing
        Util.print(name + " takes a turn to heal up");
        health += maxHealth/25;
        return;
    }
}
