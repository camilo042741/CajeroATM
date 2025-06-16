package atm;

import javax.swing.*;

/**
 * Pantalla para registrar una nueva cuenta bancaria.
 */
public class RegisterScreen extends JFrame {
    private static final double BONO_BIENVENIDA_USD = 10.0; // Bono por depósitos iniciales >= 50 USD

    private JTextField accountField;
    private JPasswordField pinField;
    private JTextField balanceField;
    private JComboBox<CurrencyType> currencyBox;

    public RegisterScreen() {
        setTitle("ColCashFlow - Crear Cuenta");
        setSize(350, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Campo: Número de cuenta
        JLabel label1 = new JLabel("Número de cuenta:");
        label1.setBounds(30, 20, 150, 25);
        add(label1);

        accountField = new JTextField();
        accountField.setBounds(170, 20, 130, 25);
        add(accountField);

        // Campo: PIN
        JLabel label2 = new JLabel("PIN:");
        label2.setBounds(30, 60, 150, 25);
        add(label2);

        pinField = new JPasswordField();
        pinField.setBounds(170, 60, 130, 25);
        add(pinField);

        // Campo: Saldo inicial
        JLabel label3 = new JLabel("Saldo inicial (USD):");
        label3.setBounds(30, 100, 150, 25);
        add(label3);

        balanceField = new JTextField();
        balanceField.setBounds(170, 100, 130, 25);
        add(balanceField);

        // Campo: Moneda
        JLabel label4 = new JLabel("Moneda:");
        label4.setBounds(30, 140, 150, 25);
        add(label4);

        currencyBox = new JComboBox<>(CurrencyType.values());
        currencyBox.setBounds(170, 140, 130, 25);
        add(currencyBox);

        // Botón: Crear Cuenta
        JButton createBtn = new JButton("Crear Cuenta");
        createBtn.setBounds(100, 190, 140, 30);
        add(createBtn);

        createBtn.addActionListener(e -> {
            String accNum = accountField.getText().trim();
            String pin = new String(pinField.getPassword());
            String balanceText = balanceField.getText().trim();
            CurrencyType currency = (CurrencyType) currencyBox.getSelectedItem();

            // Validar campos vacíos
            if (accNum.isEmpty() || pin.isEmpty() || balanceText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.");
                return;
            }

            // Verificar si la cuenta ya existe
            if (FakeDatabase.getAccount(accNum) != null) {
                JOptionPane.showMessageDialog(this, "La cuenta ya existe.");
                return;
            }

            try {
                double balanceUSD = Double.parseDouble(balanceText);
                double totalBalance = balanceUSD;
                boolean aplicaBono = false;

                // Verifica si aplica bono de bienvenida
                if (balanceUSD >= 50) {
                    totalBalance += BONO_BIENVENIDA_USD;
                    aplicaBono = true;
                }

                // Crea y guarda la cuenta
                FakeDatabase.addAccount(accNum, pin, totalBalance, currency);

                // Agrega la transacción del bono si aplica
                if (aplicaBono) {
                    FakeDatabase.getAccount(accNum).addTransaction("Welcome Bonus", BONO_BIENVENIDA_USD);
                }

                FakeDatabase.saveAccounts();

                // Genera recibo de bienvenida
                ReceiptGenerator.generateWelcome(accNum, balanceUSD, currency, aplicaBono);

                JOptionPane.showMessageDialog(this, "Cuenta creada con éxito.");
                new LoginScreen();
                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Saldo no válido.");
            }
        });

        setVisible(true);
    }
}
