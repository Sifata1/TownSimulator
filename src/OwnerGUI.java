import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OwnerGUI {
    private JFrame frame;
    private Owner owner;
    private JLabel nameLabel, balanceLabel, employedLabel, wageLabel, workCountLabel, townStatsLabel;
    private JButton workButton, gambleButton, buyLandButton;

    public OwnerGUI(Owner owner) {
        this.owner = owner;
        frame = new JFrame(owner.getName() + "'s Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.setLayout(new BorderLayout());

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(7, 2));

        nameLabel = new JLabel("Name: " + owner.getName());
        balanceLabel = new JLabel("Balance: $" + owner.getBalance());
        employedLabel = new JLabel("Employed: " + owner.isEmployed());
        wageLabel = new JLabel("Wage: $" + owner.getWage());
        workCountLabel = new JLabel("Work Count: " + owner.getWorkCount());
        townStatsLabel = new JLabel("Town Stats:\n" + owner.getTown().getStats());

        statsPanel.add(nameLabel);
        statsPanel.add(balanceLabel);
        statsPanel.add(employedLabel);
        statsPanel.add(wageLabel);
        statsPanel.add(workCountLabel);
        statsPanel.add(townStatsLabel);

        workButton = new JButton("Work");
        gambleButton = new JButton("Gamble");
        buyLandButton = new JButton("Buy Land");

        workButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String result = owner.work();
                balanceLabel.setText("Balance: $" + owner.getBalance());
                workCountLabel.setText("Work Count: " + owner.getWorkCount());
                JOptionPane.showMessageDialog(frame, result);
            }
        });

        gambleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Enter the amount you want to gamble:");
                int amount = Integer.parseInt(input);
                int result = owner.gamble(amount);
                if (result == -1) {
                    JOptionPane.showMessageDialog(frame, "You do not have enough balance to gamble.");
                } else {
                    balanceLabel.setText("Balance: $" + owner.getBalance());
                    JOptionPane.showMessageDialog(frame, "You " + (result > owner.getBalance() ? "lost" : "won") + " $" + Math.abs(result - owner.getBalance()) + ".");
                }
            }
        });

        buyLandButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Enter the number of miles you want to buy:");
                int miles = Integer.parseInt(input);
                String result = owner.buyLand(miles);
                balanceLabel.setText("Balance: $" + owner.getBalance());
                townStatsLabel.setText("Town Stats:\n" + owner.getTown().getStats());
                JOptionPane.showMessageDialog(frame, result);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(workButton);
        buttonPanel.add(gambleButton);
        buttonPanel.add(buyLandButton);

        frame.add(statsPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Owner owner = new Owner("John");
        new OwnerGUI(owner);
    }
}
