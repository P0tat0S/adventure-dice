import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Combat {
    public Combat(ArrayList<Player> playerLi) {
        ArrayList<Enemy> enemyLi = groupEnemy(playerLi.get(0).getLevel());
        combatFrame combat = new combatFrame(playerLi, enemyLi);
        combatFlow(playerLi, enemyLi);
    }
    
    private ArrayList<Enemy> groupEnemy(int level) {//Same as groupPlayer
        ArrayList<Enemy> enemies = new ArrayList<Enemy>(3);
        for(int i = 0; i<3; i++) {
            enemies.add(randomEnemy(level));
        }
        return enemies;
    }

    private Enemy randomEnemy(int level) {//Enemies scale with player level
        //Stats of the enemies
        double[] goblin = {6, 2, 6, 3, 2, 3, 6, 6};//Total stats 30
        double[] zombie = {12, 2, 5, 6, 2, 6, 5, 4};//35
        double[] skeleton = {8, 4, 4, 4, 4, 4, 4, 4};//30
        double[] orc = {14, 2, 6, 6, 2, 2, 6, 5};//35
        double[] slime = {14, 2, 2, 7, 2, 7, 1, 3};//30
        double[] darkKnight = {16, 10, 7, 7, 7, 7, 7, 7};//55
        double[] dragon = {20, 10, 10, 8, 10, 8, 5, 4};//60

        int roll = Util.diceRoller(1, 100);
        if (3 >= roll)//3% Dragon
            return new Enemy_Dragon(RndGen.randomEnemyName(), scaleEnemy(level, dragon), "dragon");
        else if (8 >= roll)//5% Dark Knight
            return new Enemy_DarkKnight(RndGen.randomEnemyName(), scaleEnemy(level, darkKnight), "darkKnight");
        else if (24 >= roll)//16% Zombie
            return new Enemy_Zombie(RndGen.randomEnemyName(), scaleEnemy(level, zombie), "zombie");
        else if (40 >= roll)//16% Orc
            return new Enemy_Orc(RndGen.randomEnemyName(), scaleEnemy(level, orc), "orc");
        else if (60 >= roll)//20% Goblin
            return new Enemy_Goblin(RndGen.randomEnemyName(), scaleEnemy(level, goblin), "goblin");
        else if (80 >= roll)//20% Skeleton
            return new Enemy_Skeleton(RndGen.randomEnemyName(), scaleEnemy(level, skeleton), "skeleton");
        else //20% Slime
            return new Enemy_Slime(RndGen.randomEnemyName(), scaleEnemy(level, slime), "slime");
    }

    private void combatFlow(ArrayList<Player> pLi, ArrayList<Enemy> eLi) {
        while (pLi.get(0).getHealth()>=0 || pLi.get(1).getHealth()>=0 || pLi.get(2).getHealth()>=0) {//Main premise while alive
            if(eLi.get(0).getHealth()<=0 && eLi.get(1).getHealth()<=0 && eLi.get(2).getHealth()<=0) {//If all enemies are dead, WIN and get loot
                gainRewards(pLi, eLi);
                return;
            }
            //Enemy STANCE, enemy show intention
            setEnemies(pLi, eLi);
            Util.pause();
            //Player STANCE, player choose targets
            setPlayers(pLi, eLi);
            //Ally TURN, player performs actions
            playerTurn(pLi, eLi);
            //Enemy turn
            enemyTurn(pLi, eLi);
            //Return stats to normal
            returnStats(pLi, eLi);
            //Show state of battle
            combatFrame combat = new combatFrame(pLi, eLi);
        }
    }

    private void gainRewards(ArrayList<Player> pLi, ArrayList<Enemy> eLi){
        double totalXP = 0;
        //Total XP gained is equal to the sum of stats of the enemies
        for (Enemy e: eLi) {
            totalXP += e.givenXP();
        }
        //Divide XP into the three characters  and return combat speed
        for (Player p: pLi) {
            p.addXp(totalXP/3);
            p.combatSpeed = 0;
        }
    }

    private void returnStats(ArrayList<Player> pLi, ArrayList<Enemy> eLi){
        //Return all stats except hp and mp to their starting values
        for (Player p: pLi) {
            p.normalise();
            Util.print(p.getName() + "'s stats went back to normal");
        }
        for (Enemy e: eLi) {
            e.normalise();
            Util.print(e.getName() + "'s stats went back to normal");
        }
    }

    private void setEnemies(ArrayList<Player> pLi, ArrayList<Enemy> eLi) {
        //Method that randomly gives a dice and enemy and randomly choose a ally
        for (Enemy e : eLi) {
            if(e.getHealth() >= 0) {
                ArrayList<WildDice> eDices = diceGenerator('e');
                e.addSpeed();
                e.addDice(eDices);
                if(!e.getSkip()) {
                    e.selectTarget();
                    Util.print(e.getName() + " is targeting " + pLi.get(e.getTarget()).getName() + "\n");
                }
            }
        }
    }

    private void setPlayers(ArrayList<Player> pLi, ArrayList<Enemy> eLi) {
        //Method that shows current stats then makes you choose a dice and enemy
        for (Player p: pLi) {
            if(p.getHealth() >= 0) {
                p.viewStats();
                ArrayList<WildDice> pDices = diceGenerator('p');
                p.addSpeed();
                p.selectDice(pDices);
                if(!p.getSkip())
                    p.selectTarget(pLi, eLi);
            }
        }
    }

    private void playerTurn(ArrayList<Player> pLi, ArrayList<Enemy> eLi) {
        //Method for targetting system and perform action on that object
        int target = 0;
        for (Player p: pLi) {//for all players perform actions
            if(p.getSkip()) {//If you have skipped turn, return
                p.skip = false; 
                return;
            } else {//Depend on the target type perform the action
                if(p.getTargetType()=='p') {
                    p.actionOnPlayer(pLi.get(p.getTarget()));
                } else {
                    p.actionOnEnemy(eLi.get(p.getTarget()));
                }
            }
        }
    }

    private void enemyTurn(ArrayList<Player> pLi, ArrayList<Enemy> eLi) {
        //Same as playerTurn but the enemy perform actions randomly
        int target = 0;
        for (Enemy e: eLi) {//for all players perform actions
            if(e.getSkip()) {//If you have skipped turn, return
                e.skip = false; 
                return;
            } else {//Depend on the target type perform the action
                if(e.getTargetType()=='p') {
                    e.actionOnPlayer(pLi.get(e.getTarget()));
                } else {
                    e.actionOnEnemy(eLi.get(e.getTarget()));
                }
            }
        }
    }

    private ArrayList<WildDice> diceGenerator (char type) {
        //Method that creates random dices and numbers them
        ArrayList<WildDice> wildDices = new ArrayList<WildDice>();
        for (int i = 0; i < 6; i++) {
            if(type == 'p') { Util.print("DICE " + i); }
            wildDices.add(new WildDice(type));
        }
        return wildDices;
    }
    
    private double[] scaleEnemy(int scaling, double[] stats) {
        for(int i = 0; i < stats.length; i++)
            stats[i] *= ((double)scaling/10 + 0.9); 
        return stats;
    }
}
