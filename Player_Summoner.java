public class Player_Summoner extends Player {
    public Player_Summoner(String n, double[] stats) {
        super(n, stats);
        Util.print(this.getName() + " the Summoner was created.");
    }

    public void attack() {
        Util.print("Attacking");
    }
}
