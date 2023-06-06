import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private JButton playButton;
    private JButton quitButton;

    public MainScreen() {
        setTitle("Welcome to Town Simulator!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(750, 500));
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(8, 40, 94)); // Set background color

        // Add an image to the middle of the MainScreen frame
        ImageIcon townImage = new ImageIcon(getClass().getResource("City-PNG-Transparent-Image.png"));
        JLabel townLabel = new JLabel(townImage);
        townLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the image horizontally
        townLabel.setVerticalAlignment(SwingConstants.CENTER); // Center the image vertically
        add(townLabel, BorderLayout.CENTER);

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
        playButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(MainScreen.this, "Enter your name:");
            if (name != null && !name.trim().isEmpty()) {
                Owner owner = new Owner(name.trim());
                new GameScreen(owner);
                dispose();
            } else {
                JOptionPane.showMessageDialog(MainScreen.this, "Please enter a valid name.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(120, 40));
        quitButton.setFont(new Font("Arial", Font.BOLD, 16));
        quitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(playButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0))); // Add spacing between buttons
        buttonPanel.add(quitButton);

        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainScreen::new);
    }
}
