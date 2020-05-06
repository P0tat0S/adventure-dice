public class Enemy_Dragon extends Enemy {
    public Enemy_Dragon(String n, double[] stats, String job) {
        super(n, stats, job);
        Util.print(this.getName()+ " the Dragon appeared.");
    }
    
    public void attack(Entity en) {//Dragons deal a lot of damage with their firebreath
        Util.print(name + " ");
        double damage = 0;
        if(Util.diceRoller(1,6) > 5) {
            Util.print(this.getName() + "uses Fire Breath");
            double critical1 = (double)Util.diceRoller(1,100);
            if(critical1 + getDexterity() > 80) {
                Util.print("The enemy hits a devastating blow");
                damage = 2 * getStrength() * (1-en.getVitality()/100);
            } else
                damage = 4 * getStrength() * (1-en.getVitality()/100);
        } else {
            double critical2 = (double)Util.diceRoller(1,100);
            if(critical2 + getDexterity()/4 > 95) {
                Util.print("The enemy hits a devastating blow");
                damage = 2 * getStrength() * (1-en.getVitality()/100);
            } else
                damage = getStrength() * (1-en.getVitality()/100);
        }
        if(damage > 0) 
            en.setHealth(-damage);
        return;
    }

    public void heal(Entity en) {//Pretty good healing
        Util.print(name + " takes a turn to heal up");
        health += maxHealth/8;
        return;
    }
}
