package atm; // Este archivo pertenece al paquete 'atm'

import javax.swing.*; // Importa los componentes de la interfaz gráfica de Swing

/**
 * Ventana de inicio de sesión exclusiva para el administrador del sistema.
 * Permite ingresar con credenciales predefinidas y acceder al panel de administración.
 */
public class AdministradorLoginScreen extends JFrame {

    // Constructor de la clase: se ejecuta al crear una nueva ventana de login de administrador
    public AdministradorLoginScreen() {
        setTitle("Acceso Administrador");        // Establece el título de la ventana
        setSize(300, 200);                        // Tamaño de la ventana
        setLayout(null);                          // Usa un layout absoluto (coordenadas manuales)
        setLocationRelativeTo(null);              // Centra la ventana en la pantalla

        // --- Etiqueta y campo de texto para el nombre de usuario ---
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(30, 30, 80, 25);       // Posición y tamaño del label
        add(userLabel);                           // Agrega el label a la ventana

        JTextField userField = new JTextField();  // Campo de texto para el usuario
        userField.setBounds(100, 30, 150, 25);     // Posición y tamaño del campo
        add(userField);                           // Agrega el campo a la ventana

        // --- Etiqueta y campo para la contraseña ---
        JLabel passLabel = new JLabel("Clave:");
        passLabel.setBounds(30, 70, 80, 25);       // Posición y tamaño del label
        add(passLabel);                           // Agrega el label a la ventana

        JPasswordField passField = new JPasswordField(); // Campo para ingresar la clave (oculta los caracteres)
        passField.setBounds(100, 70, 150, 25);     // Posición y tamaño del campo
        add(passField);                           // Agrega el campo a la ventana

        // --- Botón de login ---
        JButton loginBtn = new JButton("Ingresar");
        loginBtn.setBounds(90, 110, 100, 30);      // Posición y tamaño del botón
        add(loginBtn);                            // Agrega el botón a la ventana

        // --- Acción al hacer clic en "Ingresar" ---
        loginBtn.addActionListener(e -> {
            String user = userField.getText();                    // Obtiene el texto del campo usuario
            String pass = new String(passField.getPassword());    // Obtiene la contraseña ingresada

            // Verifica si las credenciales son correctas (hardcoded: admin/admin123)
            if (user.equals("admin") && pass.equals("admin123")) {
                new AdministradorPanel(); // Abre el panel de administrador
                dispose();                // Cierra la ventana actual
            } else {
                // Muestra un mensaje de error si las credenciales no son válidas
                JOptionPane.showMessageDialog(this, "Acceso denegado.");
            }
        });

        // Hace visible la ventana después de configurarla
        setVisible(true);
    }
}
