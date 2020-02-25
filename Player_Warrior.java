public class Player_Warrior extends Player {
    public Player_Warrior(String n, double[] stats) {
        super(n, stats);
        System.out.println(this.getName() + " the Warrior was created.");
    }

    public double attack(double vit) {
        System.out.println("Warrior special move CUT");
        double damage = getStregth() * (1-vit/150);
        return -damage;
    }
}
