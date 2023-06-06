import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GameScreen {
    private JFrame frame;
    private JButton workButton;
    private JButton gambleButton;
    private JButton buyLandButton;
    private JButton returnToMainButton;
    private JTextArea statsArea;
    private JButton progressYearButton;
    private Owner owner;
    private JButton sellLandButton;
    private JButton saveButton;
    private JButton loadButton;
    private JLabel yearLabel;
    private ImageIcon townImage;

    public GameScreen(Owner owner) {
        this.owner = owner;
        frame = new JFrame("Town");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.getContentPane().setBackground(new Color(8, 40, 94));
        frame.setLayout(new BorderLayout());

        yearLabel = new JLabel("YEAR: " + owner.getYear());
        yearLabel.setFont(new Font("Arial", Font.BOLD, 24));
        yearLabel.setForeground(Color.WHITE);
        yearLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(yearLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        frame.add(contentPanel, BorderLayout.CENTER);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BorderLayout());
        statsPanel.setBackground(new Color(8, 40, 94));

        statsArea = new JTextArea(owner.getStats());
        statsArea.setEditable(false);
        statsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        statsArea.setForeground(Color.WHITE);
        statsArea.setBackground(new Color(8, 40, 94));

        JScrollPane scrollPane = new JScrollPane(statsArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        statsPanel.add(scrollPane, BorderLayout.CENTER);

        townImage = new ImageIcon(getClass().getResource("aPmMCS-town-clipart-png-file.png"));
        JLabel townLabel = new JLabel(townImage);
        statsPanel.add(townLabel, BorderLayout.EAST);

        contentPanel.add(statsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.setBackground(new Color(8, 40, 94));

        workButton = createButton("Work", new Font("Arial", Font.BOLD, 18), Color.WHITE);
        gambleButton = createButton("Gamble", new Font("Arial", Font.BOLD, 18), Color.WHITE);
        buyLandButton = createButton("Buy Land", new Font("Arial", Font.BOLD, 18), Color.WHITE);
        returnToMainButton = createButton("Return to Main Screen", new Font("Arial", Font.BOLD, 16), Color.WHITE);
        sellLandButton = createButton("Sell Land", new Font("Arial", Font.BOLD, 18), Color.WHITE);
        saveButton = createButton("Save", new Font("Arial", Font.BOLD, 18), Color.WHITE);
        loadButton = createButton("Load", new Font("Arial", Font.BOLD, 18), Color.WHITE);
        progressYearButton = createButton("Progress Year", new Font("Arial", Font.BOLD, 18), Color.WHITE);

        buttonPanel.add(progressYearButton);
        buttonPanel.add(workButton);
        buttonPanel.add(gambleButton);
        buttonPanel.add(buyLandButton);
        buttonPanel.add(sellLandButton);
        buttonPanel.add(returnToMainButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        addListeners();
    }

    private JButton createButton(String text, Font font, Color foregroundColor) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setForeground(foregroundColor);
        button.setBackground(new Color(8, 40, 94));
        button.setFocusPainted(false);
        return button;
    }

    private void addListeners() {
        workButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String result = owner.work();
                JOptionPane.showMessageDialog(frame, result);
                updateStatsArea();
            }
        });

        gambleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Enter amount to gamble:");
                if (input != null && !input.equals("")) {
                    try {
                        int amount = Integer.parseInt(input);
                        boolean result = owner.canGamble(amount);
                        if (!result) {
                            JOptionPane.showMessageDialog(frame, "You do not have enough balance to gamble that amount.");
                        } else {
                            int gamble = owner.gamble(amount);
                            if (gamble > 0) {
                                JOptionPane.showMessageDialog(frame, "You won: $" + gamble);
                            } else {
                                JOptionPane.showMessageDialog(frame, "You lost: $" + Math.abs(gamble));
                            }
                            updateStatsArea();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid input.");
                    }
                }
            }
        });

        buyLandButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Enter number of miles to buy:");
                if (input != null && !input.equals("")) {
                    try {
                        int miles = Integer.parseInt(input);
                        String result = owner.buyLand(miles);
                        JOptionPane.showMessageDialog(frame, result);
                        updateStatsArea();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid input.");
                    }
                }
            }
        });

        sellLandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Enter number of miles to sell:");
                if (input != null && !input.equals("")) {
                    try {
                        int miles = Integer.parseInt(input);
                        String result = owner.sellLand(miles);
                        JOptionPane.showMessageDialog(frame, result);
                        updateStatsArea();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid input.");
                    }
                }
            }
        });

        returnToMainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainScreen();
            }
        });

        progressYearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                owner.progressYear();
                if (owner.die()) {
                    JOptionPane.showMessageDialog(frame, "You unfortunately died at the age of " + owner.getAge() + ".");
                    System.exit(0);
                }
                owner.getTown().increasePopulation();
                owner.getTown().incrementDeathCount();
                updateStatsArea();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadData();
                updateStatsArea();
            }
        });
    }

    private void updateStatsArea() {
        statsArea.setText(owner.getStats());
        yearLabel.setText("YEAR: " + owner.getYear());
    }

    private void saveData() {
        try {
            FileOutputStream fileOut = new FileOutputStream("game_data.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(owner);
            out.close();
            fileOut.close();
            JOptionPane.showMessageDialog(frame, "Game data saved successfully.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error occurred while saving game data.");
        }
    }

    private void loadData() {
        try {
            FileInputStream fileIn = new FileInputStream("game_data.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            owner = (Owner) in.readObject();
            in.close();
            fileIn.close();
            JOptionPane.showMessageDialog(frame, "Game data loaded successfully.");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(frame, "Error occurred while loading game data.");
        }
    }
}
