import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainSc extends JFrame {
    private JButton playButton;
    private JButton quitButton;

    public MainSc() {
        setTitle("Welcome to Town Simulator!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 400));
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Welcome to Town Simulator!");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        titlePanel.setBackground(new Color(8, 40, 94));
        titlePanel.add(title);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(8, 40, 94));

        playButton = new JButton("Play");
        playButton.setPreferredSize(new Dimension(120, 40));
        playButton.setFont(new Font("Arial", Font.BOLD, 16));
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(MainSc.this, "Enter your name:");
                if (name != null && name.trim().length() > 0) {
                    Owner owner = new Owner(name.trim());
                    new GameScreen(owner);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(MainSc.this, "Please enter a valid name.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(120, 40));
        quitButton.setFont(new Font("Arial", Font.BOLD, 16));
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(playButton);
        buttonPanel.add(quitButton);

        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainSc();
        });
    }
}
