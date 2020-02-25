public class Player_Mage extends Player {
    public Player_Mage(String n, double[] stats) {
        super(n, stats);
        AdventureDice.print(this.getName() + " the Mage was created.");
    }

    public void magic() {
        AdventureDice.print("Casting Magic");
    }
}
