package atm; // Indica que esta clase pertenece al paquete 'atm'.

import javax.swing.*; // Librerías necesarias para la interfaz gráfica.

/**
 * Clase que representa la pantalla de inicio de sesión del cajero ColCashFlow.
 * Permite autenticarse como usuario o acceder al registro o modo administrador.
 */
public class LoginScreen extends JFrame {

    // Campos de texto para ingreso de cuenta y PIN
    private JTextField accountField;
    private JPasswordField pinField;

    // Desplegable para elegir la moneda
    private JComboBox<CurrencyType> currencyBox;

    /**
     * Constructor que inicializa todos los componentes gráficos de la pantalla.
     */
    public LoginScreen() {
        setTitle("ColCashFlow - Login");     // Título de la ventana
        setSize(350, 340);                   // Dimensiones de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Finaliza la aplicación al cerrar
        setLocationRelativeTo(null);         // Centrar en pantalla
        setLayout(null);                     // Layout absoluto (por coordenadas)

        // Campo: Número de cuenta
        JLabel label1 = new JLabel("Account Number:");
        label1.setBounds(30, 20, 120, 25);
        add(label1);

        accountField = new JTextField();
        accountField.setBounds(150, 20, 150, 25);
        add(accountField);

        // Campo: PIN
        JLabel label2 = new JLabel("PIN:");
        label2.setBounds(30, 60, 120, 25);
        add(label2);

        pinField = new JPasswordField();
        pinField.setBounds(150, 60, 150, 25);
        add(pinField);

        // Selección de moneda
        JLabel label3 = new JLabel("Moneda:");
        label3.setBounds(30, 100, 120, 25);
        add(label3);

        currencyBox = new JComboBox<>(CurrencyType.values());
        currencyBox.setBounds(150, 100, 150, 25);
        add(currencyBox);

        // Botón de inicio de sesión
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(110, 150, 100, 30);
        add(loginButton);

        // Acción del botón Login
        loginButton.addActionListener(e -> {
            String acc = accountField.getText();                    // Número de cuenta ingresado
            String pin = new String(pinField.getPassword());        // PIN ingresado
            CurrencyType selectedCurrency =
                    (CurrencyType) currencyBox.getSelectedItem();       // Moneda seleccionada

            // Verificación en la base de datos (FakeDatabase)
            if (FakeDatabase.authenticate(acc, pin)) {
                FakeDatabase.setCurrency(acc, selectedCurrency);    // Se guarda la moneda seleccionada
                new MainMenuScreen(acc);                            // Abre el menú principal
                dispose();                                          // Cierra esta ventana
            } else {
                JOptionPane.showMessageDialog(this, "Invalid account or PIN.");
            }
        });

        // Botón para crear una nueva cuenta
        JButton registerButton = new JButton("Crear Cuenta");
        registerButton.setBounds(110, 200, 120, 30);
        add(registerButton);

        // Acción para abrir pantalla de registro
        registerButton.addActionListener(e -> {
            new RegisterScreen();
            dispose();
        });

        // Botón para ingresar como administrador
        JButton adminBtn = new JButton("Administrador");
        adminBtn.setBounds(110, 250, 120, 30);
        add(adminBtn);

        // Acción para abrir el panel de login del administrador
        adminBtn.addActionListener(e -> {
            new AdminLoginScreen(); // Asegúrate de que esta clase esté implementada
            dispose();
        });

        // Hace visible la ventana
        setVisible(true);
    }
}
