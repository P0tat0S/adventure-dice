import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdventureDice {
    public static void main(String[] args) throws IOException {
        boolean valid = false;
        ArrayList<Player> playerLi = new ArrayList<Player>(3);
        while(!valid) {
            if(Util.strInput("Type 'N' to create a (N)ew Game \nType 'L' to (L)oad the previous Game").equalsIgnoreCase("N")) {
                playerLi = groupPlayer();
                valid = true;
            } else {
                playerLi = loadGame();
                valid = true;
            }
        }
        
        //While alive go through different rooms endlessly
        while(playerLi.get(0).getHealth()>=0 || playerLi.get(1).getHealth()>=0 || playerLi.get(2).getHealth()>=0) {
            enterRoom(playerLi);
            saveGame(playerLi);
        }
        
        //Print total Score when you finish the game
        Util.print("You have lost. GAME OVER\n");
        displayScore(playerLi);
        System.exit(0);
    }
    
    public static void enterRoom(ArrayList<Player> pLi) {
        Room room = new Room(pLi);
        return;
    }
    
    public static void saveGame(ArrayList<Player> pLi) {
        try {
            ObjectOutputStream player1 = new ObjectOutputStream(new FileOutputStream("./saveData/playerData1.txt"));//Save to this path
            player1.writeObject(pLi.get(0));//Save data to a file
            player1.close();
            
            ObjectOutputStream player2 = new ObjectOutputStream(new FileOutputStream("./saveData/playerData2.txt"));//Repeat 2 more times
            player2.writeObject(pLi.get(1));
            player2.close();
            
            ObjectOutputStream player3 = new ObjectOutputStream(new FileOutputStream("./saveData/playerData3.txt"));
            player3.writeObject(pLi.get(2));
            player3.close();
        } catch (IOException e) {
            Util.print("Error in Saving.");
            e.printStackTrace();//Print the error
        }
    }
    
    public static ArrayList<Player> loadGame() {
        ArrayList<Player> players = new ArrayList<Player>(3);
        try {
            ObjectInputStream player1 = new ObjectInputStream(new FileInputStream("./saveData/playerData1.txt"));//Load from a file
            players.add((Player) player1.readObject());//Cast into player type and copy data
            player1.close();
            
            ObjectInputStream player2 = new ObjectInputStream(new FileInputStream("./saveData/playerData2.txt"));//Repeat 2 more times
            players.add((Player) player2.readObject());
            player2.close();
            
            ObjectInputStream player3 = new ObjectInputStream(new FileInputStream("./saveData/playerData3.txt"));
            players.add((Player) player3.readObject());
            player3.close();
            
        } catch (IOException e) {
            Util.print("Was not able to load the game. Starting a new game.");
            e.printStackTrace();
            players = groupPlayer();
        } catch (ClassNotFoundException c) {
            Util.print("Was not able to load the game. Starting a new game.");
            c.printStackTrace();
        }
        return players;
    }

    public static ArrayList<Player> groupPlayer() {
        //ArrayList of Players
        ArrayList<Player> players = new ArrayList<Player>(3);

        //Add three different subclasses of Player into the Player List
        for(int i = 0; i<3; i++)
            players.add(playerCreation());
        return players;
    }

    public static Player playerCreation() {
        //Stats of the adventurers
        double[] warrior = {16, 6, 6, 8, 3, 4, 5, 3, 5};
        double[] rogue = {10, 6, 8, 4, 3, 4, 8, 3, 7};
        double[] mage = {10, 14, 3, 3, 9, 6, 4, 3, 5};
        double[] cleric = {8, 16, 3, 3, 6, 8, 5, 3, 5};
        double[] jester = {10, 10, 5, 5, 5, 5, 5, 5, 5};
        double[] villager = {6, 6, 3, 3, 3, 3, 3, 20, 4};
        double[] paladin = {16, 10, 3, 8, 3, 8, 3, 3, 4};

        //Choose job
        boolean valid = false;
        while(!valid) {
            switch (Util.intInput("Choose a starting job by entering the number:"
                + "\n1. Warrior: High Health and Defense"
                + "\n2. Rogue: High Speed and High Attack"
                + "\n3. Mage: High Intelligence and Magic"
                + "\n4. Cleric: High Healing and Magic"
                + "\n5. Jester: Jack of all Trades, Master of none"
                + "\n6. Villager: Low Stats, but HIGH Luck"
                + "\n7. Paladin: High Health and Healing")) {
                case 1:
                    valid = true;
                    return new Player_Warrior(Util.strInput("Name of the Warrior: "), warrior, "warrior");
                case 2:
                    valid = true;
                    return new Player_Rogue(Util.strInput("Name of the Rogue: "), rogue, "rogue");
                case 3:
                    valid = true;
                    return new Player_Mage(Util.strInput("Name of the Mage: "), mage, "mage");
                case 4:
                    valid = true;
                    return new Player_Cleric(Util.strInput("Name of the Cleric: "), cleric, "cleric");
                case 5:
                    valid = true;
                    return new Player_Jester(Util.strInput("Name of the Jester: "), jester, "jester");
                case 6:
                    valid = true;
                    return new Player_Villager(Util.strInput("Name of the Villager: "), villager, "villager");
                case 7:
                    valid = true;
                    return new Player_Paladin(Util.strInput("Name of the Paladin: "), paladin, "paladin");
                default:
                    Util.print("That is not a valid character");
            }
        }
        return null;
    }   

    public static void displayScore(ArrayList<Player> pLi) {
        int totalScore = 0;
        for(Player p: pLi) {
            totalScore += p.getScore();
        }
        Util.print("The total score you gained is " + totalScore);
    }
}
