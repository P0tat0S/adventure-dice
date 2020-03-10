public class Enemy_Dragon extends Enemy {
    public Enemy_Dragon(String n, double[] stats) {
        super(n, stats);
        Util.print(this.getName()+ " the Dragon appeared.");
    }
}
