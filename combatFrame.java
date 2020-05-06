import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class combatFrame extends JFrame {
    public combatFrame(ArrayList<Player> pLi, ArrayList<Enemy> eLi) {
        JFrame frame = new JFrame("Combat Status");
        frame.setSize(1920,1080);
        frame.setLayout(null);
        
        JButton next = new JButton("NEXT");
        next.setBounds(760, 50, 400, 200);
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(next);
        
        /*********
        CHARACTERS
        *********/
        JLabel character1 = new JLabel(new ImageIcon(characterImage(pLi.get(0).getJob())));
        JLabel character2 = new JLabel(new ImageIcon(characterImage(pLi.get(1).getJob())));
        JLabel character3 = new JLabel(new ImageIcon(characterImage(pLi.get(2).getJob())));
        
        character1.setBounds(40, 300, 260, 450);
        character2.setBounds(300, 300, 260, 450);
        character3.setBounds(560, 300, 260, 450);
        
        frame.add(character1);
        frame.add(character2);
        frame.add(character3);
        
        //STATS
        JTextArea cStats1 = new JTextArea(pLi.get(0).JStats());
        JTextArea cStats2 = new JTextArea(pLi.get(1).JStats());
        JTextArea cStats3 = new JTextArea(pLi.get(2).JStats());
        
        cStats1.setEditable(false);
        cStats2.setEditable(false);
        cStats3.setEditable(false);
        
        cStats1.setBounds(45, 770, 240, 160);
        cStats2.setBounds(305, 770, 240, 160);
        cStats3.setBounds(565, 770, 240, 160);
        
        frame.add(cStats1);
        frame.add(cStats2);
        frame.add(cStats3);
        
        /******
        ENEMIES
        ******/
        JLabel enemy1 = new JLabel(new ImageIcon(enemyImage(eLi.get(0).getJob())));
        JLabel enemy2 = new JLabel(new ImageIcon(enemyImage(eLi.get(1).getJob())));
        JLabel enemy3 = new JLabel(new ImageIcon(enemyImage(eLi.get(2).getJob())));
        
        enemy1.setBounds(1080, 300, 260, 450);
        enemy2.setBounds(1340, 300, 260, 450);
        enemy3.setBounds(1600, 300, 260, 450);
        
        frame.add(enemy1);
        frame.add(enemy2);
        frame.add(enemy3);
        
        //STATS
        JTextArea eStats1 = new JTextArea(eLi.get(0).JStats());
        JTextArea eStats2 = new JTextArea(eLi.get(1).JStats());
        JTextArea eStats3 = new JTextArea(eLi.get(2).JStats());
        
        eStats1.setEditable(false);
        eStats2.setEditable(false);
        eStats3.setEditable(false);
        
        eStats1.setBounds(1085, 770, 240, 160);
        eStats2.setBounds(1345, 770, 240, 160);
        eStats3.setBounds(1605, 770, 240, 160);
        
        frame.add(eStats1);
        frame.add(eStats2);
        frame.add(eStats3);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public String characterImage(String job) {
        switch(job) {
            case "warrior":
                return "./image-characters/warrior.png";
            case "mage":
                return "./image-characters/mage.png";
            case "cleric":
                return "./image-characters/cleric.png";
            case "rogue":
                return "./image-characters/rogue.png";
            case "paladin":
                return "./image-characters/paladin.png";
            case "jester":
                return "./image-characters/jester.png";
            case "villager":
                return "./image-characters/villager.png";
        }
        return null;
    }
    
    public String enemyImage(String job) {
        switch(job) {
            case "dragon":
                return "./image-enemies/dragon.png";
            case "goblin":
                return "./image-enemies/goblin.png";
            case "zombie":
                return "./image-enemies/zombie.png";
            case "orc":
                return "./image-enemies/orc.png";
            case "slime":
                return "./image-enemies/slime.png";
            case "skeleton":
                return "./image-enemies/skeleton.png";
            case "darkKnight":
                return "./image-enemies/darkKnight.png";
        }
        return null;
    }
}
