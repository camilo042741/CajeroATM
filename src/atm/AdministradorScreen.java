package atm; // Esta clase pertenece al paquete 'atm'

import javax.swing.*; // Librería para crear interfaces gráficas (ventanas, botones, etiquetas, etc.)
import java.awt.*;    // Librería para manejar el diseño y gráficos (colores, layouts, fuentes)
import java.sql.*;    // Librería para conectarse y trabajar con bases de datos SQL

/**
 * Clase que muestra una pantalla para el administrador con el historial
 * completo de transacciones (arqueo de cajero).
 */
public class AdministradorScreen extends JFrame {

    private JTextArea textArea; // Área de texto donde se mostrará el historial de transacciones

    /**
     * Constructor: inicializa y muestra la interfaz gráfica del administrador.
     */
    public AdministradorScreen() {
        setTitle("ColCashFlow - Arqueo de Cajero (Administrador)"); // Título de la ventana
        setSize(600, 400);                                           // Tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);                    // Cierra la aplicación al cerrar esta ventana
        setLocationRelativeTo(null);                                // Centra la ventana en la pantalla
        setLayout(new BorderLayout());                              // Usa un diseño con regiones (NORTH, CENTER, SOUTH, etc.)

        // Título en la parte superior
        JLabel title = new JLabel("Historial de Transacciones (Arqueo de Cajero)", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente y tamaño
        add(title, BorderLayout.NORTH); // Añade el título en la parte superior

        // Área de texto para mostrar el historial de transacciones
        textArea = new JTextArea();
        textArea.setEditable(false); // Solo lectura, el usuario no puede escribir
        add(new JScrollPane(textArea), BorderLayout.CENTER); // Añade scroll automático al área de texto

        // Botón para cerrar la ventana
        JButton backBtn = new JButton("Cerrar");
        backBtn.addActionListener(e -> dispose()); // Al hacer clic, cierra la ventana actual
        add(backBtn, BorderLayout.SOUTH); // Añade el botón en la parte inferior

        cargarTransacciones(); // Carga las transacciones desde la base de datos
        setVisible(true); // Muestra la ventana
    }

    /**
     * Método que consulta la base de datos y carga las transacciones en el JTextArea.
     */
    private void cargarTransacciones() {
        try (
                Connection conn = DBManager.connect(); // Abre conexión a la base de datos
                Statement stmt = conn.createStatement(); // Permite ejecutar comandos SQL
                ResultSet rs = stmt.executeQuery("SELECT * FROM transacciones ORDER BY fecha DESC") // Consulta las transacciones
        ) {
            StringBuilder sb = new StringBuilder(); // Acumulador de texto

            // Recorre todas las filas del resultado y construye el texto a mostrar
            while (rs.next()) {
                sb.append("Fecha: ").append(rs.getString("fecha")).append("\n")
                        .append("Cuenta: ").append(rs.getString("cuenta")).append("\n")
                        .append("Nombre: ").append(rs.getString("nombre")).append("\n")
                        .append("DNI: ").append(rs.getString("dni")).append("\n")
                        .append("Tipo: ").append(rs.getString("tipo")).append("\n")
                        .append("Monto: $").append(rs.getDouble("monto")).append("\n")
                        .append("-----------------------------\n");
            }

            // Si no hay transacciones
            if (sb.length() == 0) {
                textArea.setText("No hay transacciones registradas.");
            } else {
                textArea.setText(sb.toString()); // Muestra el texto en el área
            }

        } catch (SQLException e) {
            // Si hay un error al consultar la base de datos
            textArea.setText("Error al consultar transacciones: " + e.getMessage());
        }
    }
}
