import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameScreen {
    private JFrame frame;
    private JButton workButton;
    private JButton gambleButton;
    private JButton quitButton;
    private JButton buyLandButton;
    private JButton resetButton;
    private JButton returnToMainButton;
    private JTextArea statsArea;
    private Owner owner;
    private Town town;

    public GameScreen(Owner owner) {
        this.owner = owner;
        frame = new JFrame("Town");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));

        workButton = new JButton("Work");
        gambleButton = new JButton("Gamble");
        buyLandButton = new JButton("Buy Land");
        returnToMainButton = new JButton("Return to Main Screen");
        quitButton = new JButton("Save and Quit");
        resetButton = new JButton("Reset");

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

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        returnToMainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainSc();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                owner.setBalance(1000);
                owner.setWage(15);
                owner.setEmployed(true);
                town.setSizeInMiles(1);

            }
        });

        buttonPanel.add(workButton);
        buttonPanel.add(gambleButton);
        buttonPanel.add(buyLandButton);
        buttonPanel.add(returnToMainButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(quitButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        statsArea = new JTextArea(owner.getStats());
        statsArea.setEditable(false);

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
