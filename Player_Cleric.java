public class Player_Cleric extends Player {
    public Player_Cleric(String n, double[] stats) {
        super(n, stats);
        Util.print(this.getName()+ " the Cleric was created.");
    }

    public void heal() {
        Util.print("Healing");
    }

    public void setHealth(double n) {
        super.setHealth(n*1.2);
    }

    public void viewStats() {
        Util.print(
            "Level: " + getLevel() +
            "\nHealth: " + getHealth() +
            "\nMagicka*:" + getMagicka() +
            "\nStrength: " + getStrength() +
            "\nVitality: " + getVitality() +
            "\nIntelligence:" + getIntelligence() +
            "\nMind*" + getMind() +
            "\nDexterity" + getDexterity() +
            "\nLuck" + getLuck() + "\n"
        );
    }
}
