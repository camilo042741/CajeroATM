package atm; // Indica que esta clase pertenece al paquete 'atm'

import javax.swing.*; // Importa componentes de interfaz gráfica (ventanas, botones, etc.)
import java.sql.*;    // Importa clases para trabajar con bases de datos SQL (JDBC)

/**
 * Clase que representa la interfaz gráfica del panel de administrador.
 * Muestra todas las transacciones registradas en el sistema.
 */
public class AdministradorPanel extends JFrame {

    // Constructor de la clase: se ejecuta al crear una nueva ventana de administración
    public AdministradorPanel() {
        setTitle("Panel de Administración - Arqueo de Cajero"); // Título de la ventana
        setSize(600, 400);                                       // Tamaño de la ventana
        setLocationRelativeTo(null);                             // Centra la ventana en la pantalla
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // Usa un layout vertical

        // Área de texto para mostrar el historial de transacciones
        JTextArea area = new JTextArea();
        JScrollPane scroll = new JScrollPane(area); // Permite hacer scroll en el área de texto
        add(scroll); // Añade el scroll (con el área dentro) a la ventana

        // --- Consulta a la base de datos ---
        try (
                Connection conn = DBManager.connect(); // Conecta a la base de datos
                Statement stmt = conn.createStatement(); // Crea un objeto para ejecutar SQL
                ResultSet rs = stmt.executeQuery("SELECT * FROM transacciones ORDER BY fecha DESC") // Ejecuta la consulta
        ) {
            StringBuilder sb = new StringBuilder(); // Acumulador de texto para mostrar en el JTextArea

            // Recorre cada fila del resultado (una transacción por fila)
            while (rs.next()) {
                sb.append("Fecha: ").append(rs.getString("fecha"))          // Fecha de la transacción
                        .append(" | Cuenta: ").append(rs.getString("cuenta"))     // Número de cuenta
                        .append(" | Nombre: ").append(rs.getString("nombre"))     // Nombre del cliente
                        .append(" | DNI: ").append(rs.getString("dni"))           // Documento
                        .append(" | Tipo: ").append(rs.getString("tipo"))         // Tipo de transacción
                        .append(" | Monto: ").append(rs.getDouble("monto"))       // Valor
                        .append("\n");                                            // Salto de línea
            }

            // Establece el contenido del área de texto con el resultado acumulado
            area.setText(sb.toString());

        } catch (SQLException e) {
            // En caso de error en la base de datos, muestra un mensaje
            area.setText("Error al cargar transacciones.");
            e.printStackTrace(); // Imprime el error en consola para depuración
        }

        // Hace visible la ventana del panel
        setVisible(true);
    }
}
