import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainSc extends JFrame {
    private JButton playButton;
    private JFrame frame;
    private JButton quitButton;
    private JLabel title;

    public MainSc() {
        title = new JLabel("Welcome to Town Simulator!");
        title.setFont(new Font("Ariel",Font.BOLD,28));
        title.setForeground(Color.white);

        frame = new JFrame("Welcome to the Game!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 400));
        frame.add(title);

        ImageIcon menu = new ImageIcon("background.jpg");
        JLabel label = new JLabel(menu);
        JPanel label1 = new JPanel();

        Color darkBlue = new Color(8,40,94);
        label1.setBackground(darkBlue);


        playButton = new JButton("Play");
        quitButton = new JButton("Quit");

        playButton.setBounds(150, 175, 60, 30);
        quitButton.setBounds(250, 175, 60, 30);

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Welcome to Town Simulator!");

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(frame, "Enter your name:");
                if (name != null && name.trim().length() > 0) {
                    Owner owner = new Owner(name.trim());
                    new GameScreen(owner);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        frame.add(playButton);
        frame.add(quitButton);

        frame.getContentPane().add(label1);
        frame.pack();
        frame.setVisible(true);
    }


}
