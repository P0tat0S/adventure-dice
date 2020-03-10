public class Enemy_Zombie extends Enemy {
    public Enemy_Zombie(String n, double[] stats) {
        super(n, stats);
        Util.print(this.getName()+ " the Zombie appeared.");
    }
}
