public class Enemy_Zombie extends Enemy {
    public Enemy_Zombie(String n, double[] stats, String job) {
        super(n, stats, job);
        Util.print(this.getName()+ " the Zombie appeared.");
    }

   public void heal(Entity en) {//Zombies have better regeneration
        Util.print("The zombie starts to regenerate");
        health += maxHealth/10;
    }
}
