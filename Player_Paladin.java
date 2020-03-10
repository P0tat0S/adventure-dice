public class Player_Paladin extends Player {
    public Player_Paladin(String n, double[] stats) {
        super(n, stats);
        Util.print(this.getName() + " the Paladin was created.");
    }

    public void attack() {
        Util.print("Attacking");
    }
}
