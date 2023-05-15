import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class MainScreen extends JPanel {
    private JFrame frame;
    private Image backgroundImage;
    private JButton playButton;
    private JButton quitButton;

    public MainScreen() {
        frame = new JFrame("Welcome to the Game!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.setLayout(new BorderLayout());



        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        playButton = new JButton("Play");
        quitButton = new JButton("Quit");

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

        buttonPanel.add(playButton);
        buttonPanel.add(quitButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);

    }



    public static void main(String[] args) throws IOException {
        new MainScreen();
    }
}
