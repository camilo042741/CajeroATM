package atm; // Esta clase forma parte del paquete 'atm'

import javax.swing.*; // Importa las clases necesarias para crear la interfaz gráfica (ventanas, botones, campos de texto, etc.)

/**
 * Pantalla de acceso para el administrador.
 * Valida usuario y contraseña, y muestra la pantalla de arqueo si las credenciales son correctas.
 */
public class AdminLoginScreen extends JFrame {

    // Campos de entrada para usuario y contraseña
    private JTextField usernameField;
    private JPasswordField passwordField;

    /**
     * Constructor que inicializa la ventana de login para el administrador.
     */
    public AdminLoginScreen() {
        setTitle("ColCashFlow - Acceso Administrador"); // Título de la ventana
        setSize(350, 200); // Tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cierra la aplicación si se cierra esta ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setLayout(null); // Usa diseño absoluto (posiciones manuales)

        // Etiqueta: "Usuario"
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(30, 30, 80, 25); // Posición y tamaño
        add(userLabel);

        // Campo de texto para el nombre de usuario
        usernameField = new JTextField();
        usernameField.setBounds(120, 30, 180, 25); // Posición y tamaño
        add(usernameField);

        // Etiqueta: "Contraseña"
        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setBounds(30, 70, 80, 25);
        add(passLabel);

        // Campo de contraseña (oculta los caracteres)
        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 180, 25);
        add(passwordField);

        // Botón: "Ingresar"
        JButton loginBtn = new JButton("Ingresar");
        loginBtn.setBounds(120, 110, 100, 30); // Posición y tamaño
        add(loginBtn);

        // Acción cuando se hace clic en el botón
        loginBtn.addActionListener(e -> {
            String user = usernameField.getText().trim(); // Obtiene el texto del campo de usuario
            String pass = new String(passwordField.getPassword()); // Obtiene la contraseña

            // Verifica si las credenciales son las esperadas
            if (user.equals("admin") && pass.equals("admin123")) {
                new AdministradorScreen(); // Abre la pantalla de historial de transacciones (arqueo)
                dispose(); // Cierra esta ventana
            } else {
                // Muestra un mensaje de error si las credenciales no coinciden
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas.");
            }
        });

        setVisible(true); // Muestra la ventana
    }
}
