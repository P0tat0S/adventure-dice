public class Enemy_DarkKnight extends Enemy {
    public Enemy_DarkKnight(String n, double[] stats, String job) {
        super(n, stats, job);
        Util.print(this.getName()+ " the DarkKnight appeared.");
    }
    
    public void attack(Entity en) {//Dark Knights have a special attack
        double damage = 0;
        if(Util.diceRoller(1,4) > 3) {
            Util.print(this.getName() + "uses Void Slash");
            double critical1 = (double)Util.diceRoller(1,100);
            if(critical1 + getDexterity() > 90) {
                Util.print("The enemy hits a devastating blow");
                damage = 3 * getStrength() * (1-en.getVitality()/100);
            } else
                damage = 1.5 * getStrength() * (1-en.getVitality()/100);
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

    public void heal(Entity en) {//Good healing
        Util.print(name + " takes a turn to heal up");
        health += maxHealth/10;
        return;
    }
}
