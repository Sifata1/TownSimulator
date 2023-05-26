import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TownGUI {
    private JFrame frame;
    private JTextArea statsArea;

    private JButton buyLandButton;
    private JButton ownerButton;
    private JButton sellLandButton;
    private Owner owner;
    public TownGUI() {
        this.owner = owner;
        frame = new JFrame("Town");
        frame.setTitle("Town");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 400));
        frame.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();

        buyLandButton = new JButton("Buy Land");
        ownerButton = new JButton("Return to Owner View");
        sellLandButton = new JButton("Sell Land");

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

        buttonPanel.add(buyLandButton);
        buttonPanel.add(sellLandButton);
        buttonPanel.add(ownerButton);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

        private void updateStatsArea() { statsArea.setText(owner.getStats());

    }
}
