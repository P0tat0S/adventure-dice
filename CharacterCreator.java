import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CharacterCreator extends AdventureDice {
    private double[] charStats = new double[8];
    private String nameInput;

    public CharacterCreator(String title) {
        JFrame frame = AdventureDice.defaultFrame(title);
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());

        JLabel background = new JLabel(new ImageIcon("./images/bkgd_CC.jpg"));
        frame.add(background);
        background.setLayout(new FlowLayout());

        JPanel mainPanel1 = new JPanel();
        background.add(mainPanel1);
        mainPanel1.setLayout(new BoxLayout(mainPanel1, BoxLayout.Y_AXIS));
        mainPanel1.setMaximumSize(new Dimension(960, 1080));
        mainPanel1.setOpaque(false);

        JPanel mainPanel2 = new JPanel();
        background.add(mainPanel2);
        mainPanel2.setLayout(new BoxLayout(mainPanel2, BoxLayout.Y_AXIS));
        mainPanel2.setMaximumSize(new Dimension(960, 1080));
        mainPanel2.setOpaque(false);

        JPanel pointPanel = new JPanel(new FlowLayout());
        mainPanel1.add(pointPanel);
        JLabel pointsLeft = new JLabel(new ImageIcon("./image-icons/pointsLeft.png"));
        pointPanel.add(pointsLeft);
        pointPanel.setMaximumSize(new Dimension(900,200));
        pointsLeft.setMaximumSize(new Dimension(900,200));
        pointPanel.setOpaque(false);

        JPanel namePanel = new JPanel(new FlowLayout());
        mainPanel2.add(namePanel);
        JLabel test = new JLabel(new ImageIcon("./image-icons/pointsLeft.png"));
        namePanel.add(test);
        namePanel.setMaximumSize(new Dimension(900,200));
        test.setMaximumSize(new Dimension(900,200));
        namePanel.setOpaque(false);

        frame.setVisible(true);//Showing a frame
    }
}
