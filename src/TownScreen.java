import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TownScreen {
    private JFrame frame;
    private JButton workButton;
    private JButton gambleButton;
    private JButton buyLandButton;
    private JButton returnToMainButton;
    private JTextArea statsArea;
    private Owner owner;

    public TownScreen(Owner owner) {
        this.owner = owner;
        frame = new JFrame("Town");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));

        workButton = new JButton("Work");
        gambleButton = new JButton("Gamble");
        buyLandButton = new JButton("Buy Land");
        returnToMainButton = new JButton("Return to Main Screen");

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
                        int result = owner.gamble(amount);
                        if (result == -1) {
                            JOptionPane.showMessageDialog(frame, "You do not have enough balance to gamble that amount.");
                        } else {
                            JOptionPane.showMessageDialog(frame, "You " + (result > owner.getBalance() ? "lost" : "won") + " $" + Math.abs(result - owner.getBalance()) + ".");
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

        returnToMainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new OwnerGUI(owner);
            }
        });

        buttonPanel.add(workButton);
        buttonPanel.add(gambleButton);
        buttonPanel.add(buyLandButton);
        buttonPanel.add(returnToMainButton);

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