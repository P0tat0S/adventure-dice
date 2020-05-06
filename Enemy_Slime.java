public class Enemy_Slime extends Enemy {
    public Enemy_Slime(String n, double[] stats, String job) {
        super(n, stats, job);
        Util.print(this.getName()+ " the Slime appeared.");
    }

    public void heal(Entity en) {//Slimes can regenarate a Lot
        Util.print(name + " takes a turn to heal up");
        health += maxHealth/5;
        return;
    }
}
