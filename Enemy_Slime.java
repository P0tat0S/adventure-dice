public class Enemy_Slime extends Enemy {
    public Enemy_Slime(String n, double[] stats) {
        super(n, stats);
        Util.print(this.getName()+ " the Slime appeared.");
    }
}
