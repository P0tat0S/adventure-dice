import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Room {
    private String roomType;
    private String roomName;
    
    public Room(ArrayList<Player> pLi) {//Room constrcutor that sends the players into a room
        roomName = "The " + RndGen.randomAdjective() + " " + RndGen.randomNoun() + " room";
        Util.print("You have entered the room " + roomName);
        Util.pause();
        switch(Util.diceRoller(1,4)) {
            case 1:
                roomType = "combat";
                enterCombat(pLi);
                break;
            case 2:
                roomType = "rest";
                enterRest(pLi);
                break;
            case 3:
                roomType = "treasure";
                enterTreasure(pLi);
                break;
            case 4:
                roomType = "trap";
                enterTrap(pLi);
                break;
        }
    }
    
    private void enterCombat(ArrayList<Player> pLi) {
        Combat battle = new Combat(pLi);
        for(Player p: pLi) {//The players gain score
            p.addScore(1);
        }
        Util.pause();
    }
    
    private void enterRest(ArrayList<Player> pLi) {
        restFrame rest = new restFrame(pLi);
        for(Player p: pLi) {//You heal a random number between 1 and 4 and scales with level
            p.recoverHealth((1+0.1*p.getLevel())*(double)Util.diceRoller(1,4));
            Util.print(p.getName() + " has healed and now it is at " + p.getHealth());
            p.recoverMagicka((1+0.1*p.getLevel())*(double)Util.diceRoller(1,4));
            Util.print(p.getName() + " has recovered Magicka and now it is at " + p.getMagicka());
        }
        Util.pause();
    }
    
    private void enterTreasure(ArrayList<Player> pLi) {
        treasureFrame treasure = new treasureFrame(pLi);
        for(Player p: pLi) {//The players gain a random amount of points
            int amount = Util.diceRoller(2,3);
            Util.print(p.getName() + " searches the room and gains " + amount + " treasures");
            p.addScore(amount);
        }
        Util.pause();
    }
    
    private void enterTrap(ArrayList<Player> pLi) {
        trapFrame trap = new trapFrame(pLi);
        Util.print(RndGen.randomTrap());
        for(Player p: pLi) {//The players enter a trap and roll to avoid damage
            p.setHealth(-(double)Util.diceRoller(1,4)*(1+0.2*p.getLevel()));
            Util.print(p.getName() + " was caught in the trap and now it is at " + p.getHealth());
        }
        Util.pause();
    }
}
