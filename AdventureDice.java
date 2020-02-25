import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class AdventureDice {
    public static void main(String[] args) throws IOException {
        //CharacterCreator c = new CharacterCreator("AdventureDice");
        ArrayList<Player> playerLi = groupPlayer();
        ArrayList<Enemy> enemyLi = groupEnemy();
        playerLi = combatFlow(playerLi, enemyLi);
        System.exit(0);
    }

    public static ArrayList<Player> groupPlayer() {
        double[] warrior = {16, 6, 6, 8, 3, 4, 5, 3, 12};
        double[] mage = {10, 14, 3, 3, 9, 6, 4, 3, 5};
        double[] cleric = {8, 16, 3, 3, 6, 8, 5, 3, 5};
        ArrayList<Player> players = new ArrayList<Player>(3);
        players.add(new Player_Warrior("Mark", warrior));
        players.add(new Player_Mage("Tomas", mage));
        players.add(new Player_Cleric("Edward", cleric));
        return players;
    }

    public static ArrayList<Enemy> groupEnemy() {
        double[] skeleton = {8, 4, 4, 4, 4, 4, 4, 4};
        ArrayList<Enemy> enemies = new ArrayList<Enemy>(3);
        enemies.add(new Enemy_Skeleton(randomEnemyName(), skeleton));
        enemies.add(new Enemy_Skeleton(randomEnemyName(), skeleton));
        enemies.add(new Enemy_Skeleton(randomEnemyName(), skeleton));
        return enemies;
    }

    public static ArrayList<Player> combatFlow(ArrayList<Player> pLi, ArrayList<Enemy> eLi) {
        while (pLi.get(0).getHealth()>0 && pLi.get(0).getHealth()>0 && pLi.get(0).getHealth()>0) {
            Player targetP;
            Enemy targetE;
            int target = 0;
            int counter = 0;
            //Enemy STANCE
            for (Enemy e : eLi) {
                ArrayList<WildDice> eDices = diceGenerator('e', counter);
                e.addSpeed();
                e.addDice(eDices);
                e.selectTarget();
                System.out.println(e.getName() + " is targeting " + pLi.get(e.getTarget()).getName() + "\n");
                counter++;
            }
            //Player STANCE
            counter = 0;
            for (Player p: pLi) {
                ArrayList<WildDice> pDices = diceGenerator('p', counter);
                p.addSpeed();
                p.selectDice(pDices);
                p.selectTarget(pLi, eLi);
                counter++;
            }
            counter = 0;
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

            /*pLi.get(0).setHealth(-20);
            pLi.get(1).setHealth(-20);
            pLi.get(2).setHealth(-20);*/
        }
        print("Party is ded");
        return pLi;
    }

    public static ArrayList<WildDice> diceGenerator (char type, int n) {
        if (type == 'p') {
            print("\nDICE SET " + n + "\n");
        }
        ArrayList<WildDice> wildDices = new ArrayList<WildDice>();
        for (int i = 0; i < 6; i++) {
            print("DICE " + i);
            wildDices.add(new WildDice(type));
        }
        return wildDices;
    }

    //Random Generators
    public static String randomEnemyName() {
        int d40 = diceRoller(0, 39);
        String[] enemyName = {"Moransab","Mavorgezu","Thargha","Zergta","Vresan",
        "Thild'ula","Ha","Fenu","Imilphu","Bucu","Ronba","Nexla","Doomimgash",
        "Ball","Xyal","Grorn","Raruk","Mali","Thanxus","Irath","Rend","Hevorg",
        "Kil'grorn","Thusra","Bachom","Rornushang","Rothme","Dresh",
        "Rothshandze","Reshra","Naush","Motar","Baalshu","Lavi","Phekahud",
        "Zargver","Rath","Varorsharg","Kukruul","Becain"};
        return enemyName[d40]; //Returns a random enemy name
    } //End randomEnemyName

    public static String randomAdjective() {
        int d50 = diceRoller(0, 49);
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

    //Util Methods
    public static int diceRoller(int min, int max) {
        Random random = new Random(); //Creation of an object Random
        //Gets two integers and returns a random value between the given values
        return random.nextInt(max + 1 - min) + min;
    }

    public static void print(String message) {//Just an abbreviation method
        System.out.println(message);
        return;
    }

    public static JFrame defaultFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Close on X
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(1920,1080);
        return frame;
    }
}
