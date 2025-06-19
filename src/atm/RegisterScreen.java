package atm; // Este archivo pertenece al paquete 'atm'.

import javax.swing.*; // Librería para interfaces gráficas Swing.

/**
 * Pantalla gráfica para registrar una nueva cuenta bancaria en el sistema ColCashFlow.
 * Permite ingresar número de cuenta, PIN, saldo inicial, moneda, nombre y DNI.
 * Si el usuario deposita $50 USD o más, se aplica automáticamente un bono de bienvenida.
 */
public class RegisterScreen extends JFrame {

    // Monto del bono de bienvenida, en dólares
    private static final double BONO_BIENVENIDA_USD = 10.0;

    // Campos de texto e interfaz para capturar los datos del nuevo usuario
    private JTextField accountField;
    private JPasswordField pinField;
    private JTextField balanceField;
    private JComboBox<CurrencyType> currencyBox;
    private JTextField nameField;
    private JTextField dniField;

    /**
     * Constructor de la ventana de registro de cuentas.
     * Inicializa y configura todos los componentes gráficos.
     */
    public RegisterScreen() {
        setTitle("ColCashFlow - Crear Cuenta"); // Título de la ventana
        setSize(350, 400);                      // Tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Comportamiento al cerrar
        setLocationRelativeTo(null);             // Centrar en pantalla
        setLayout(null);                         // Posicionamiento absoluto

        // Campos del formulario
        JLabel label1 = new JLabel("Número de cuenta:");
        label1.setBounds(30, 20, 150, 25);
        add(label1);
        accountField = new JTextField();
        accountField.setBounds(170, 20, 130, 25);
        add(accountField);

        JLabel label2 = new JLabel("PIN:");
        label2.setBounds(30, 60, 150, 25);
        add(label2);
        pinField = new JPasswordField();
        pinField.setBounds(170, 60, 130, 25);
        add(pinField);

        JLabel label3 = new JLabel("Saldo inicial (USD):");
        label3.setBounds(30, 100, 150, 25);
        add(label3);
        balanceField = new JTextField();
        balanceField.setBounds(170, 100, 130, 25);
        add(balanceField);

        JLabel label4 = new JLabel("Moneda:");
        label4.setBounds(30, 140, 150, 25);
        add(label4);
        currencyBox = new JComboBox<>(CurrencyType.values());
        currencyBox.setBounds(170, 140, 130, 25);
        add(currencyBox);

        JLabel label5 = new JLabel("Nombre:");
        label5.setBounds(30, 180, 150, 25);
        add(label5);
        nameField = new JTextField();
        nameField.setBounds(170, 180, 130, 25);
        add(nameField);

        JLabel label6 = new JLabel("DNI:");
        label6.setBounds(30, 220, 150, 25);
        add(label6);
        dniField = new JTextField();
        dniField.setBounds(170, 220, 130, 25);
        add(dniField);

        // Botón para crear cuenta
        JButton createBtn = new JButton("Crear Cuenta");
        createBtn.setBounds(100, 270, 140, 30);
        add(createBtn);

        // Acción del botón: validación, creación de cuenta y registro
        createBtn.addActionListener(e -> {
            String accNum = accountField.getText().trim();
            String pin = new String(pinField.getPassword());
            String balanceText = balanceField.getText().trim();
            CurrencyType currency = (CurrencyType) currencyBox.getSelectedItem();
            String nombre = nameField.getText().trim();
            String dni = dniField.getText().trim();

            // Validación de campos vacíos
            if (accNum.isEmpty() || pin.isEmpty() || balanceText.isEmpty() || nombre.isEmpty() || dni.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.");
                return;
            }

            // Verifica si la cuenta ya existe
            if (FakeDatabase.getAccount(accNum) != null) {
                JOptionPane.showMessageDialog(this, "La cuenta ya existe.");
                return;
            }

            try {
                double balanceUSD = Double.parseDouble(balanceText);
                double totalBalance = balanceUSD;
                boolean aplicaBono = false;

                // Aplica bono de bienvenida si el saldo inicial es mayor o igual a $50
                if (balanceUSD >= 50) {
                    totalBalance += BONO_BIENVENIDA_USD;
                    aplicaBono = true;
                }

                // Crea el objeto cliente y la cuenta bancaria
                Cliente cliente = new Cliente(nombre, dni);
                FakeDatabase.addAccount(accNum, pin, totalBalance, currency, cliente);

                // Registra transacción de bono, si aplica
                if (aplicaBono) {
                    FakeDatabase.getAccount(accNum).addTransaction("Welcome Bonus", BONO_BIENVENIDA_USD);
                }

                // Guarda la cuenta y genera un recibo
                FakeDatabase.saveAccounts();
                ReceiptGenerator.generateWelcome(accNum, balanceUSD, currency, aplicaBono);

                // Notifica al usuario y abre la pantalla de login
                JOptionPane.showMessageDialog(this, "Cuenta creada con éxito.");
                new LoginScreen();
                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Saldo no válido.");
            }
        });

        setVisible(true); // Muestra la ventana
    }
}
