import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MainMenu extends JFrame implements ActionListener {
    private String[] args = {""};

    MainMenu(String title) {
        //Initial Settings of the frame
        JFrame frame = new JFrame(title);//Creation of a frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Close on X
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        frame.setSize(1920,1080);

        JLabel background = new JLabel(new ImageIcon("./images/background.jpg"));
        frame.add(background);
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel(new FlowLayout());
        JLabel titleGame = new JLabel(new ImageIcon("./images/title.png"));
        background.add(titlePanel);
        titlePanel.add(titleGame);
        titlePanel.setMaximumSize(new Dimension(1400,300));
        titlePanel.setOpaque(false);
        titleGame.setOpaque(false);

        //Creation of the 5 containers for title and button
        JPanel newPanel = new JPanel(new FlowLayout());
        JButton newGame = new JButton("NG");
        background.add(newPanel);
        newPanel.add(newGame);
        newPanel.setOpaque(false);
        newPanel.setMaximumSize(new Dimension(550,130));
        newGame.setIcon(new ImageIcon("./image-icons/newGame.png"));
        newGame.setOpaque(false);
        newGame.setContentAreaFilled(false);
        newGame.setBorderPainted(false);
        newGame.addActionListener(this);
        newGame.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}
            @Override
            public void mousePressed(MouseEvent arg0) {}
            @Override
            public void mouseExited(MouseEvent arg0) {
                newGame.setIcon(new ImageIcon("./image-icons/newGame.png"));
            }
            @Override
            public void mouseEntered(MouseEvent arg0) {
                newGame.setIcon(new ImageIcon("./image-icons/newGame-hv.png"));
            }
            @Override
            public void mouseClicked(MouseEvent arg0) {}
        });

        JPanel loadPanel = new JPanel(new FlowLayout());
        JButton loadGame = new JButton("LG");
        background.add(loadPanel);
        loadPanel.add(loadGame);
        loadPanel.setOpaque(false);
        loadPanel.setMaximumSize(new Dimension(550,140));
        loadGame.setIcon(new ImageIcon("./image-icons/loadGame.png"));
        loadGame.setOpaque(false);
        loadGame.setContentAreaFilled(false);
        loadGame.setBorderPainted(false);
        loadGame.addActionListener(this);
        loadGame.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}
            @Override
            public void mousePressed(MouseEvent arg0) {}
            @Override
            public void mouseExited(MouseEvent arg0) {
                loadGame.setIcon(new ImageIcon("./image-icons/loadGame.png"));
            }
            @Override
            public void mouseEntered(MouseEvent arg0) {
                loadGame.setIcon(new ImageIcon("./image-icons/loadGame-hv.png"));
            }
            @Override
            public void mouseClicked(MouseEvent arg0) {}
        });

        JPanel optionsPanel = new JPanel(new FlowLayout());
        JButton options = new JButton("O");
        background.add(optionsPanel);
        optionsPanel.add(options);
        optionsPanel.setOpaque(false);
        optionsPanel.setMaximumSize(new Dimension(550,140));
        options.setIcon(new ImageIcon("./image-icons/options.png"));
        options.setOpaque(false);
        options.setContentAreaFilled(false);
        options.setBorderPainted(false);
        options.addActionListener(this);
        options.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}
            @Override
            public void mousePressed(MouseEvent arg0) {}
            @Override
            public void mouseExited(MouseEvent arg0) {
                options.setIcon(new ImageIcon("./image-icons/options.png"));
            }
            @Override
            public void mouseEntered(MouseEvent arg0) {
                options.setIcon(new ImageIcon("./image-icons/options-hv.png"));
            }
            @Override
            public void mouseClicked(MouseEvent arg0) {}
        });

        JPanel creditsPanel = new JPanel(new FlowLayout());
        JButton credits = new JButton("C");
        background.add(creditsPanel);
        creditsPanel.add(credits);
        creditsPanel.setOpaque(false);
        creditsPanel.setMaximumSize(new Dimension(550,140));
        credits.setIcon(new ImageIcon("./image-icons/credits.png"));
        credits.setOpaque(false);
        credits.setContentAreaFilled(false);
        credits.setBorderPainted(false);
        credits.addActionListener(this);
        credits.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}
            @Override
            public void mousePressed(MouseEvent arg0) {}
            @Override
            public void mouseExited(MouseEvent arg0) {
                credits.setIcon(new ImageIcon("./image-icons/credits.png"));
            }
            @Override
            public void mouseEntered(MouseEvent arg0) {
                credits.setIcon(new ImageIcon("./image-icons/credits-hv.png"));
            }
            @Override
            public void mouseClicked(MouseEvent arg0) {}
        });

        JPanel exitPanel = new JPanel(new FlowLayout());
        JButton exitGame = new JButton("E");
        background.add(exitPanel);
        exitPanel.add(exitGame);
        exitPanel.setOpaque(false);
        exitPanel.setMaximumSize(new Dimension(550,140));
        exitGame.setIcon(new ImageIcon("./image-icons/exitGame.png"));
        exitGame.setOpaque(false);
        exitGame.setContentAreaFilled(false);
        exitGame.setBorderPainted(false);
        exitGame.addActionListener(this);
        exitGame.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {}
            @Override
            public void mousePressed(MouseEvent arg0) {}
            @Override
            public void mouseExited(MouseEvent arg0) {
                exitGame.setIcon(new ImageIcon("./image-icons/exitGame.png"));
            }
            @Override
            public void mouseEntered(MouseEvent arg0) {
                exitGame.setIcon(new ImageIcon("./image-icons/exitGame-hv.png"));
            }
            @Override
            public void mouseClicked(MouseEvent arg0) {}
        });

        frame.setVisible(true);//Showing a frame
    }

    public static void main(String[] args) {
        MainMenu m = new MainMenu("AdventureDice");
    }

    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "NG":
                System.out.println("You created a new game");
                AdventureDice.main(args);
                break;
            case "LG":
                System.out.println("You loaded a game");
                AdventureDice.main(args);
                break;
            case "O":
                System.out.println("Not Implemented");
                break;
            case "C":
                Credits c = new Credits("Credits");
                break;
            case "E":
                System.exit(0);
                break;
        }
    }
}
