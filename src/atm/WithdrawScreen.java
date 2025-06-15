package atm;

import javax.swing.*;
import java.awt.*;

public class WithdrawScreen extends JFrame {
    private JTextArea display;
    private JComboBox<CurrencyType> currencyBox;
    private String accountNumber;

    public WithdrawScreen(String accountNumber) {
        this.accountNumber = accountNumber;

        setTitle("ColCashFlow - Retiro");
        setSize(400, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(74, 168, 234)); // azul

        JLabel currencyLabel = new JLabel("Moneda:");
        currencyLabel.setBounds(30, 10, 100, 25);
        add(currencyLabel);

        currencyBox = new JComboBox<>(CurrencyType.values());
        currencyBox.setBounds(100, 10, 120, 25);
        add(currencyBox);

        display = new JTextArea();
        display.setBounds(30, 50, 320, 150);
        display.setFont(new Font("Monospaced", Font.BOLD, 14));
        display.setBackground(Color.BLACK);
        display.setForeground(Color.GREEN);
        display.setEditable(false);
        display.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        add(display);

        JButton updateBtn = new JButton("Actualizar");
        updateBtn.setBounds(240, 10, 110, 25);
        add(updateBtn);
        updateBtn.addActionListener(e -> updateMenu());

        int x = 30, y = 220;
        for (int i = 1; i <= 6; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.setBounds(x, y, 90, 40);
            int option = i;
            btn.addActionListener(e -> handleWithdrawOption(option));
            add(btn);
            x += 100;
            if (i % 3 == 0) {
                x = 30;
                y += 60;
            }
        }

        JLabel cashSlot = new JLabel("Retire su dinero aquí", SwingConstants.CENTER);
        cashSlot.setBounds(100, 380, 200, 30);
        cashSlot.setOpaque(true);
        cashSlot.setBackground(Color.DARK_GRAY);
        cashSlot.setForeground(Color.WHITE);
        add(cashSlot);

        JButton backBtn = new JButton("Menú Principal");
        backBtn.setBounds(125, 430, 140, 30);
        add(backBtn);
        backBtn.addActionListener(e -> {
            new MainMenuScreen(accountNumber);
            dispose();
        });

        updateMenu();
        setVisible(true);
    }

    private void updateMenu() {
        CurrencyType selected = (CurrencyType) currencyBox.getSelectedItem();
        double[] usdAmounts = {20, 40, 60, 100, 200};
        StringBuilder options = new StringBuilder("      MENÚ DE RETIRO\n");
        for (int i = 0; i < usdAmounts.length; i++) {
            double local = selected.convertFromUSD(usdAmounts[i]);
            options.append((i + 1)).append(" - ").append(selected.getSymbol()).append(" ")
                    .append(String.format("%.2f", local)).append("\n");
        }
        options.append("6 - Cancelar\n\nSeleccione una opción:");
        display.setText(options.toString());
    }

    private void handleWithdrawOption(int option) {
        CurrencyType currency = (CurrencyType) currencyBox.getSelectedItem();
        double[] usdAmounts = {20, 40, 60, 100, 200};

        if (option >= 1 && option <= 5) {
            double usdAmount = usdAmounts[option - 1];
            double localAmount = currency.convertFromUSD(usdAmount);

            if (FakeDatabase.withdraw(accountNumber, usdAmount)) {
                FakeDatabase.saveAccounts();
                ReceiptGenerator.generate(accountNumber, "Withdrawal", usdAmount);
                JOptionPane.showMessageDialog(this,
                        "Retiro exitoso de " + currency.getSymbol() + " " + String.format("%.2f", localAmount));
                new MainMenuScreen(accountNumber);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Fondos insuficientes.");
            }
        } else if (option == 6) {
            new MainMenuScreen(accountNumber);
            dispose();
        }
    }
}
