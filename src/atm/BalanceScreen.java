package atm;

import javax.swing.*;
import java.awt.*;

public class BalanceScreen extends JFrame {
    public BalanceScreen(String accountNumber) {
        setTitle("ColCashFlow - Consulta de Saldo");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0, 0, 30));

        FakeDatabase.Account acc = FakeDatabase.getAccount(accountNumber);
        double balanceUSD = acc.balance;

        JLabel title = new JLabel("Saldos en todas las monedas:", SwingConstants.CENTER);
        title.setBounds(50, 20, 300, 30);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        add(title);

        int y = 60;
        for (CurrencyType currency : CurrencyType.values()) {
            double converted = currency.convertFromUSD(balanceUSD);
            JLabel line = new JLabel(currency.getSymbol() + ": " + String.format("%.2f", converted), SwingConstants.CENTER);
            line.setBounds(50, y, 300, 25);
            line.setForeground(Color.GREEN);
            add(line);
            y += 30;
        }

        JButton backBtn = new JButton("Menú Principal");
        backBtn.setBounds(125, y + 10, 140, 30);
        add(backBtn);

        backBtn.addActionListener(e -> {
            new MainMenuScreen(accountNumber);
            dispose();
        });

        // Guardar transacción y recibo
        acc.addTransaction("Balance Check", 0);
        ReceiptGenerator.generate(accountNumber, "Balance Check", balanceUSD);

        setVisible(true);
    }
}
