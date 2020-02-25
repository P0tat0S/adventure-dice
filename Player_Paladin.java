public class Player_Paladin extends Player {
    public Player_Paladin(String n, double[] stats) {
        super(n, stats);
        System.out.println(this.getName() + " the Paladin was created.");
    }

    public void attack() {
        System.out.println("Attacking");
    }
}
