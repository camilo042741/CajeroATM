package atm;

import javax.swing.*;
import java.awt.*;

public class DepositScreen extends JFrame {
    private JTextArea display;
    private StringBuilder input = new StringBuilder();
    private JComboBox<CurrencyType> currencyBox;
    private String accountNumber;

    public DepositScreen(String accountNumber) {
        this.accountNumber = accountNumber;

        setTitle("ColCashFlow - Depósito");
        setSize(400, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(150, 0, 0)); // rojo

        JLabel label = new JLabel("Moneda:");
        label.setBounds(30, 10, 80, 25);
        add(label);

        currencyBox = new JComboBox<>(CurrencyType.values());
        currencyBox.setBounds(100, 10, 120, 25);
        add(currencyBox);

        display = new JTextArea("Ingrese el monto a depositar:\n\n");
        display.setBounds(30, 50, 320, 100);
        display.setFont(new Font("Monospaced", Font.BOLD, 16));
        display.setBackground(Color.BLACK);
        display.setForeground(Color.GREEN);
        display.setEditable(false);
        display.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        add(display);

        JButton updateBtn = new JButton("Actualizar");
        updateBtn.setBounds(240, 10, 110, 25);
        add(updateBtn);
        updateBtn.addActionListener(e -> updateDisplay());

        int x = 30, y = 170;
        for (int i = 1; i <= 9; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.setBounds(x, y, 90, 40);
            int digit = i;
            btn.addActionListener(e -> appendDigit(digit));
            add(btn);
            x += 100;
            if (i % 3 == 0) {
                x = 30;
                y += 60;
            }
        }

        JButton zeroBtn = new JButton("0");
        zeroBtn.setBounds(30, y, 90, 40);
        zeroBtn.addActionListener(e -> appendDigit(0));
        add(zeroBtn);

        JButton clearBtn = new JButton("Borrar");
        clearBtn.setBounds(130, y, 90, 40);
        clearBtn.addActionListener(e -> {
            input.setLength(0);
            updateDisplay();
        });
        add(clearBtn);

        JButton enterBtn = new JButton("Depositar");
        enterBtn.setBounds(230, y, 90, 40);
        enterBtn.addActionListener(e -> performDeposit());
        add(enterBtn);

        JLabel envelopeSlot = new JLabel("Inserte sobre aquí", SwingConstants.CENTER);
        envelopeSlot.setBounds(100, 380, 200, 30);
        envelopeSlot.setOpaque(true);
        envelopeSlot.setBackground(Color.DARK_GRAY);
        envelopeSlot.setForeground(Color.WHITE);
        add(envelopeSlot);

        JButton backBtn = new JButton("Menú Principal");
        backBtn.setBounds(125, 430, 140, 30);
        add(backBtn);
        backBtn.addActionListener(e -> {
            new MainMenuScreen(accountNumber);
            dispose();
        });

        updateDisplay();
        setVisible(true);
    }

    private void updateDisplay() {
        CurrencyType currency = (CurrencyType) currencyBox.getSelectedItem();
        display.setText("Ingrese el monto a depositar:\n\n" + currency.getSymbol() + " " + input);
    }

    private void appendDigit(int digit) {
        input.append(digit);
        updateDisplay();
    }

    private void performDeposit() {
        CurrencyType currency = (CurrencyType) currencyBox.getSelectedItem();

        if (input.length() == 0) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto válido.");
            return;
        }

        try {
            double localAmount = Double.parseDouble(input.toString());
            if (localAmount <= 0) throw new NumberFormatException();
            double usdAmount = currency.convertToUSD(localAmount);

            FakeDatabase.deposit(accountNumber, usdAmount);
            FakeDatabase.saveAccounts();
            ReceiptGenerator.generate(accountNumber, "Deposit", usdAmount);
            JOptionPane.showMessageDialog(this,
                    "Depósito exitoso de " + currency.getSymbol() + " " + String.format("%.2f", localAmount));
            new MainMenuScreen(accountNumber);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Monto no válido.");
        }
    }
}
