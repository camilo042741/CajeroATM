package atm;

import javax.swing.*;

/**
 * Pantalla de acceso para el administrador.
 * Valida usuario y contraseña, y muestra la pantalla de arqueo si es correcto.
 */
public class AdminLoginScreen extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public AdminLoginScreen() {
        setTitle("ColCashFlow - Acceso Administrador");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(30, 30, 80, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 30, 180, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setBounds(30, 70, 80, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 180, 25);
        add(passwordField);

        JButton loginBtn = new JButton("Ingresar");
        loginBtn.setBounds(120, 110, 100, 30);
        add(loginBtn);

        loginBtn.addActionListener(e -> {
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword());

            if (user.equals("admin") && pass.equals("admin123")) {
                new AdministradorScreen(); // Abre la pantalla de arqueo
                dispose(); // Cierra la de login
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas.");
            }
        });

        setVisible(true);
    }
}
