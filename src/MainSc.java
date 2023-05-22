import javax.swing.*;
import java.awt.*;

public class MainSc extends JFrame {
    JButton playButton;
    JButton quitButton;

    MainSc() {
        ImageIcon menu = new ImageIcon("13853-gradient.jpg");
        JLabel label = new JLabel(menu);
        JPanel label1 = new JPanel();

        Color darkBlue = new Color(8,40,94);

        label1.setBackground(darkBlue);
        this.setPreferredSize(new Dimension(1000, 500));

        playButton = new JButton();
        quitButton = new JButton();

        playButton.setBounds(300, 350, 60, 30);
        quitButton.setBounds(400, 350, 60, 30);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Welcome to Town Simulator!");

        this.add(playButton);
        this.add(quitButton);

        this.add(label);
        this.getContentPane().add(label1);
        this.pack();
        this.setVisible(true);
    }
}
