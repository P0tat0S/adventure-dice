import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class trapFrame extends JFrame {
    public trapFrame(ArrayList<Player> pLi) {
        JFrame frame = new JFrame("Main Window");
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
        
        /***
        Trap   
        ***/
        JLabel trap = new JLabel(new ImageIcon("./image-rooms/room3.jpg"));
        trap.setSize(780, 450);
        trap.setBounds(1080, 300, 780, 450);
        frame.add(trap);
        
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
}
