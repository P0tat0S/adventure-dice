import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Credits extends JFrame {
    Credits(String title) {
        JFrame frame = new JFrame(title);//Creation of a frame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Close on X
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        frame.setSize(640,360);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        JButton returnMenu = new JButton("Return");
        frame.add(returnMenu);
        frame.setVisible(true);
    }
}
