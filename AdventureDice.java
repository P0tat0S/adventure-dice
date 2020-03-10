import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class AdventureDice {
    public static void main(String[] args) throws IOException {
        //Create ArrayList of Player and Enemy
        ArrayList<Player> playerLi = groupPlayer();
        ArrayList<Enemy> enemyLi = groupEnemy();

        //Return a player list after a combat
        combatFlow(playerLi, enemyLi);
        System.exit(0);
    }

    public static ArrayList<Player> groupPlayer() {
        //ArrayList of Players
        ArrayList<Player> players = new ArrayList<Player>(3);

        //Add three different subclasses of Player into the Player List
        for(int i = 0; i<3; i++)
            players.add(randomPlayer());
        return players;
    }

    public static Player randomPlayer() {
        //Temporary stats for adventurers
        double[] warrior = {16, 6, 6, 8, 3, 4, 5, 3, 12};
        double[] mage = {10, 14, 3, 3, 9, 6, 4, 3, 5};
        double[] cleric = {8, 16, 3, 3, 6, 8, 5, 3, 5};

        //Randomize job
        switch (Util.diceRoller(1,3)) {
            case 1:
                return new Player_Warrior(Util.strInput("Name of the Warrior: "), warrior);
            case 2:
                return new Player_Mage(Util.strInput("Name of the Mage: "), mage);
            case 3:
                return new Player_Cleric(Util.strInput("Name of the Cleric: "), cleric);
        }
        return null;
    }

    public static ArrayList<Enemy> groupEnemy() {//Same as groupPlayer
        double[] skeleton = {8, 4, 4, 4, 4, 4, 4, 4};
        ArrayList<Enemy> enemies = new ArrayList<Enemy>(3);
        enemies.add(new Enemy_Skeleton(randomEnemyName(), skeleton));
        enemies.add(new Enemy_Skeleton(randomEnemyName(), skeleton));
        enemies.add(new Enemy_Skeleton(randomEnemyName(), skeleton));
        return enemies;
    }

    public static void combatFlow(ArrayList<Player> pLi, ArrayList<Enemy> eLi) {
        while (pLi.get(0).getHealth()>0 && pLi.get(0).getHealth()>0 && pLi.get(0).getHealth()>0) {
            Player targetP;
            Enemy targetE;
            int target = 0;
            //Enemy STANCE
            for (Enemy e : eLi) {
                ArrayList<WildDice> eDices = diceGenerator('e');
                e.addSpeed();
                e.addDice(eDices);
                e.selectTarget();
                Util.print(e.getName() + " is targeting " + pLi.get(e.getTarget()).getName() + "\n");
            }
            //Player STANCE
            for (Player p: pLi) {
                p.viewStats();
                ArrayList<WildDice> pDices = diceGenerator('p');
                p.addSpeed();
                p.selectDice(pDices);
                p.selectTarget(pLi, eLi);
            }
            //COMBAT
            //Ally turn
            for (int i = 0; i < pLi.size(); i++) {
                target = pLi.get(i).getTarget();
                targetP = pLi.get(target);
                targetE = eLi.get(target);
                switch (pLi.get(i).getTargetType()) {
                    case 'p':
                        targetP = pLi.get(i).performActionP(targetP);
                        break;
                    case 'e':
                        targetE = pLi.get(i).performActionE(targetE);
                        break;
                }
            }
            //Enemy turn
            for (int i = 0; i < eLi.size(); i++) {
                target = eLi.get(i).getTarget();
                targetP = pLi.get(target);
                targetE = eLi.get(target);
                switch (eLi.get(i).getTargetType()) {
                    case 'p':
                        targetP = eLi.get(i).performActionP(targetP);
                        break;
                    case 'e':
                        targetE = eLi.get(i).performActionE(targetE);
                        break;
                }
            }
        }
        Util.print("Party is dead. GAME OVER");
    }


    public static ArrayList<WildDice> diceGenerator (char type) {
        ArrayList<WildDice> wildDices = new ArrayList<WildDice>();
        for (int i = 0; i < 6; i++) {
            Util.print("DICE " + i);
            wildDices.add(new WildDice(type));
        }
        return wildDices;
    }

    public static String randomEnemyName() {//Name generator
        int d40 = Util.diceRoller(0, 39);
        String[] enemyName = {"Moransab","Mavorgezu","Thargha","Zergta","Vresan",
        "Thild'ula","Ha","Fenu","Imilphu","Bucu","Ronba","Nexla","Doomimgash",
        "Ball","Xyal","Grorn","Raruk","Mali","Thanxus","Irath","Rend","Hevorg",
        "Kil'grorn","Thusra","Bachom","Rornushang","Rothme","Dresh",
        "Rothshandze","Reshra","Naush","Motar","Baalshu","Lavi","Phekahud",
        "Zargver","Rath","Varorsharg","Kukruul","Becain"};
        return enemyName[d40]; //Returns a random enemy name
    } //End randomEnemyName

    public static String randomAdjective() {// Adjective generator
        int d50 = Util.diceRoller(0, 49);
        String[] adjectives = {"scintillating","acceptable","barbarous","flashy",
        "noxious","laughable","normal","hurried","puzzled","oval","overrated",
        "scandalous","dark","bloody","adventurous","lethal","groovy","whole",
        "adaptable","alive","smoltering","different","lopsided","malicious",
        "troubled","knowledgeable","simplistic","brash","encouraging","dusty",
        "inexpensive","smart","incandescent","weary","workable","grandiose",
        "splendid","precious","tragic","narrow","joyous","sharp","careless",
        "flat","resonant","bumpy","solid","strong","faulty","accidental"};
        return adjectives[d50]; //Returns a random adjective inside the array
    } //End randomAdjective

    public static JFrame defaultFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Close on X
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(1920,1080);
        return frame;
    }
}
