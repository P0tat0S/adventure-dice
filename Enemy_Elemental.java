public class Enemy_Elemental extends Enemy {
    public Enemy_Elemental(String n, double[] stats) {
        super(n, stats);
        System.out.println(this.getName()+ " the Elemental appeared.");
    }
}
