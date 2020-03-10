public class Enemy_Demon extends Enemy {
    public Enemy_Demon(String n, double[] stats) {
        super(n, stats);
        Util.print(this.getName()+ " the Demon appeared.");
    }
}
