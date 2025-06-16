package atm; // Este archivo pertenece al paquete 'atm'

import javax.swing.*;  // Importa los componentes gráficos de Swing
import java.awt.*;     // Importa clases para manipular la interfaz gráfica

/**
 * Esta clase representa la pantalla de inicio de sesión del cajero automático.
 * El usuario ingresa su número de cuenta, PIN y selecciona su moneda preferida.
 */
public class LoginScreen extends JFrame {
    // Campos para ingresar número de cuenta y PIN
    private JTextField accountField;
    private JPasswordField pinField;

    // Lista desplegable para seleccionar la moneda
    private JComboBox<CurrencyType> currencyBox;

    /**
     * Constructor: configura la ventana y todos los elementos gráficos.
     */
    public LoginScreen() {
        setTitle("ColCashFlow - Login"); // Título de la ventana
        setSize(350, 300); // Tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cierra la app al salir
        setLocationRelativeTo(null); // Centra la ventana en pantalla
        setLayout(null); // Usa posiciones absolutas (no recomendadas, pero funcionales)

        // Etiqueta para el campo de número de cuenta
        JLabel label1 = new JLabel("Account Number:");
        label1.setBounds(30, 20, 120, 25);
        add(label1);

        // Campo de texto donde el usuario escribe su número de cuenta
        accountField = new JTextField();
        accountField.setBounds(150, 20, 150, 25);
        add(accountField);

        // Etiqueta para el campo de PIN
        JLabel label2 = new JLabel("PIN:");
        label2.setBounds(30, 60, 120, 25);
        add(label2);

        // Campo de contraseña para ingresar el PIN
        pinField = new JPasswordField();
        pinField.setBounds(150, 60, 150, 25);
        add(pinField);

        // Etiqueta para seleccionar la moneda preferida
        JLabel label3 = new JLabel("Moneda:");
        label3.setBounds(30, 100, 120, 25);
        add(label3);

        // Menú desplegable con los tipos de moneda disponibles
        currencyBox = new JComboBox<>(CurrencyType.values());
        currencyBox.setBounds(150, 100, 150, 25);
        add(currencyBox);

        // Botón para iniciar sesión
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(110, 150, 100, 30);
        add(loginButton);

        // Acción cuando se hace clic en "Login"
        loginButton.addActionListener(e -> {
            String acc = accountField.getText(); // Obtiene el número de cuenta ingresado
            String pin = new String(pinField.getPassword()); // Obtiene el PIN
            CurrencyType selectedCurrency = (CurrencyType) currencyBox.getSelectedItem(); // Moneda seleccionada

            // Verifica si los datos son válidos
            if (FakeDatabase.authenticate(acc, pin)) {
                // Si son válidos, se actualiza la moneda de la cuenta
                FakeDatabase.setCurrency(acc, selectedCurrency);

                // Muestra el menú principal y cierra la pantalla de login
                new MainMenuScreen(acc);
                dispose();
            } else {
                // Muestra un mensaje si el login es incorrecto
                JOptionPane.showMessageDialog(this, "Invalid account or PIN.");
            }
        });

        // Botón para ir a la pantalla de creación de cuentas
        JButton registerButton = new JButton("Crear Cuenta");
        registerButton.setBounds(110, 200, 120, 30);
        add(registerButton);

        // Acción al hacer clic en "Crear Cuenta"
        registerButton.addActionListener(e -> {
            new RegisterScreen(); // Abre la pantalla de registro
            dispose(); // Cierra la pantalla de login
        });

        // Muestra la ventana
        setVisible(true);
    }
}
