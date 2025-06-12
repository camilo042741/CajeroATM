package atm;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {
    private JTextField accountField;
    private JPasswordField pinField;
    private JComboBox<CurrencyType> currencyBox;

    public LoginScreen() {
        setTitle("ColCashFlow - Login");
        setSize(350, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel label1 = new JLabel("Account Number:");
        label1.setBounds(30, 20, 120, 25);
        add(label1);

        accountField = new JTextField();
        accountField.setBounds(150, 20, 150, 25);
        add(accountField);

        JLabel label2 = new JLabel("PIN:");
        label2.setBounds(30, 60, 120, 25);
        add(label2);

        pinField = new JPasswordField();
        pinField.setBounds(150, 60, 150, 25);
        add(pinField);

        JLabel label3 = new JLabel("Moneda:");
        label3.setBounds(30, 100, 120, 25);
        add(label3);

        currencyBox = new JComboBox<>(CurrencyType.values());
        currencyBox.setBounds(150, 100, 150, 25);
        add(currencyBox);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(110, 150, 100, 30);
        add(loginButton);

        loginButton.addActionListener(e -> {
            String acc = accountField.getText();
            String pin = new String(pinField.getPassword());
            CurrencyType selectedCurrency = (CurrencyType) currencyBox.getSelectedItem();

            if (FakeDatabase.authenticate(acc, pin)) {
                FakeDatabase.setCurrency(acc, selectedCurrency);
                new MainMenuScreen(acc);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid account or PIN.");
            }
        });

        JButton registerButton = new JButton("Crear Cuenta");
        registerButton.setBounds(110, 200, 120, 30);
        add(registerButton);

        registerButton.addActionListener(e -> {
            new RegisterScreen();
            dispose();
        });

        setVisible(true);
    }
}
