public class Player_Cleric extends Player {
    public Player_Cleric(String n, double[] stats) {
        super(n, stats);
        AdventureDice.print(this.getName()+ " the Cleric was created.");
    }

    public void heal() {
        System.out.println("Healing");
    }
}
