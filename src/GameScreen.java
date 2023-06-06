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
    private JButton townButton;
    private JButton saveButton;
    private JButton loadButton;

    public GameScreen(Owner owner) {
        this.owner = owner;
        frame = new JFrame("Town");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(725, 400));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        workButton = new JButton("Work");
        gambleButton = new JButton("Gamble");
        buyLandButton = new JButton("Buy Land");
        returnToMainButton = new JButton("Return to Main Screen");
        sellLandButton = new JButton("Sell Land");
        townButton = new JButton("Town");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

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
                new MainSc();
            }
        });


        progressYearButton = new JButton("Progress Year");
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

        buttonPanel.add(progressYearButton);
        buttonPanel.add(workButton);
        buttonPanel.add(gambleButton);
        buttonPanel.add(buyLandButton);
        buttonPanel.add(sellLandButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(returnToMainButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        statsArea = new JTextArea(owner.getStats());
        statsArea.setEditable(false);
        statsArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(statsArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void updateStatsArea() {
        statsArea.setText(owner.getStats());
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
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Failed to save game data.");
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
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Failed to load game data.");
        }
    }
}
