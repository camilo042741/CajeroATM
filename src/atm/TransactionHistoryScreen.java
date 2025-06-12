package atm;

import javax.swing.*;
import java.util.List;

public class TransactionHistoryScreen extends JFrame {
    public TransactionHistoryScreen(String accountNumber) {
        setTitle("ColCashFlow - Transaction History");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JTextArea area = new JTextArea();
        area.setBounds(20, 20, 350, 180);
        area.setEditable(false);
        add(area);

        List<Transaction> history = FakeDatabase.getTransactionHistory(accountNumber);
        if (history.isEmpty()) {
            area.setText("No transactions found.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Transaction t : history) {
                sb.append(t.toString()).append("\n");
            }
            area.setText(sb.toString());
        }

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(140, 220, 100, 30);
        add(backBtn);

        backBtn.addActionListener(e -> {
            new MainMenuScreen(accountNumber);
            dispose();
        });

        setVisible(true);
    }
}
