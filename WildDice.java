import java.util.*;

public class WildDice {
    private String diceNumber = "";
    private String diceType = "";
    private int speedCost;

    public WildDice(char type) {
        switch (type) {
            case 'p':
                diceCreation();
                Util.print("The dice are " + diceNumber);
                Util.print("The types are " + diceType);
                Util.print("And the Speed Cost is " + speedCost + "\n");
                break;
            case 'e':
                diceCreation();
                break;
        }
    }

    private void diceCreation() {
        rollNumber();
        rollType();
        addDice();
        removeLastChars();
    }

    private void rollNumber() {
        int roll = Util.diceRoller(1, 100);
        if (5 >= roll) {
            diceNumber += "D20,";
            speedCost += 8;
        } else if (15 >= roll) {
            diceNumber += "D12,";
            speedCost += 6;
        } else if (30 >= roll) {
            diceNumber += "D8,";
            speedCost += 5;
        } else if (50 >= roll) {
            diceNumber += "D6,";
            speedCost += 4;
        } else {
            diceNumber += "D4,";
            speedCost += 3;
        }
    }

    private void rollType() {
        int roll = Util.diceRoller(1, 100);
        if (5 >= roll) {
            diceType += "Violet,";
        } else if (12 >= roll) {
            diceType += "Orange,";
        } else if (20 >= roll) {
            diceType += "Yellow,";
        } else if (40 >= roll) {
            diceType += "White,";
        } else if (60 >= roll) {
            diceType += "Green,";
        } else if (80 >= roll) {
            diceType += "Blue,";
        } else {
            diceType += "Red,";
        }
    }

    private void addDice() {
        if(speedCost < 8) {
            int roll = Util.diceRoller(1, 2);
            if(roll == 2) {
                rollNumber();
                rollType();
                addDice();
            }
        }
    }

    public String getDiceNumber() {
        return diceNumber;
    }

    public String getDiceType() {
        return diceType;
    }

    public int getSpeedCost() {
        return speedCost;
    }

    private void removeLastChars() {
        diceType = diceType.substring(0, diceType.length() - 1);
        diceNumber = diceNumber.substring(0, diceNumber.length() - 1);
    }

}
