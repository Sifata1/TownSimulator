import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    public GameScreen(Owner owner) {
        this.owner = owner;
        frame = new JFrame("Town");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 400));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        workButton = new JButton("Work");
        gambleButton = new JButton("Gamble");
        buyLandButton = new JButton("Buy Land");
        returnToMainButton = new JButton("Return to Main Screen");
        sellLandButton = new JButton("Sell Land");

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

        buttonPanel.add(progressYearButton);
        buttonPanel.add(workButton);
        buttonPanel.add(gambleButton);
        buttonPanel.add(buyLandButton);
        buttonPanel.add(sellLandButton);
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
}
