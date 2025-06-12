package atm;

import javax.swing.*;

public class ExitScreen extends JFrame {
    public ExitScreen() {
        setTitle("ColCashFlow - Exit");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel label = new JLabel("Thank you for using our ATM!");
        label.setBounds(50, 40, 220, 30);
        add(label);

        setVisible(true);
    }
}
