public class Player_Warrior extends Player {
    public Player_Warrior(String n, double[] stats) {
        super(n, stats);
        Util.print(this.getName() + " the Warrior was created.");
    }

    public double attack(double vit) {
        Util.print("Warrior special move CUT");
        double damage = getStrength() * (1-vit/150);
        return -damage;
    }

    public void setHealth(double n) {
        super.setHealth(n*0.9);
    }

    public void viewStats() {
        Util.print(
            "Level: " + getLevel() +
            "\nHealth*: " + getHealth() +
            "\nMagicka:" + getMagicka() +
            "\nStrength: " + getStrength() +
            "\nVitality*: " + getVitality() +
            "\nIntelligence:" + getIntelligence() +
            "\nMind" + getMind() +
            "\nDexterity" + getDexterity() +
            "\nLuck" + getLuck() + "\n"
        );
    }
}
