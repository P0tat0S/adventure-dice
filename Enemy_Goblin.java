public class Enemy_Goblin extends Enemy {
    public Enemy_Goblin(String n, double[] stats) {
        super(n, stats);
        Util.print(this.getName()+ " the Goblin appeared.");
    }
}
