public class Player_Mage extends Player {
    public Player_Mage(String n, double[] stats) {
        super(n, stats);
        Util.print(this.getName() + " the Mage was created.");
    }

    public double attack(double vit) {
        Util.print("Mage weak attack");
        double damage = getStrength() * (1-vit/80);
        return -damage;
    }

    public void magic() {
        Util.print("Casting Magic");
    }

    public void setHealth(double n) {
        super.setHealth(n*1.1);
    }

    public void viewStats() {
        Util.print(
            "Level: " + getLevel() +
            "\nHealth: " + getHealth() +
            "\nMagicka*:" + getMagicka() +
            "\nStrength: " + getStrength() +
            "\nVitality: " + getVitality() +
            "\nIntelligence*:" + getIntelligence() +
            "\nMind" + getMind() +
            "\nDexterity" + getDexterity() +
            "\nLuck" + getLuck() + "\n"
        );
    }
}
