public class Player_Summoner extends Player {
    public Player_Summoner(String n, double[] stats) {
        super(n, stats);
        System.out.println(this.getName() + " the Summoner was created.");
    }

    public void attack() {
        System.out.println("Attacking");
    }
}
