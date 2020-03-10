public class Enemy_Orc extends Enemy {
    public Enemy_Orc(String n, double[] stats) {
        super(n, stats);
        Util.print(this.getName()+ " the Orc appeared.");
    }
}
