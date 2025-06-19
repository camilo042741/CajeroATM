package atm;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AdministradorScreen extends JFrame {

    private JTextArea textArea;

    public AdministradorScreen() {
        setTitle("ColCashFlow - Arqueo de Cajero (Administrador)");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Historial de Transacciones (Arqueo de Cajero)", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton backBtn = new JButton("Cerrar");
        backBtn.addActionListener(e -> dispose());
        add(backBtn, BorderLayout.SOUTH);

        cargarTransacciones();
        setVisible(true);
    }

    private void cargarTransacciones() {
        try (Connection conn = DBManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM transacciones ORDER BY fecha DESC")) {

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("Fecha: ").append(rs.getString("fecha")).append("\n")
                        .append("Cuenta: ").append(rs.getString("cuenta")).append("\n")
                        .append("Nombre: ").append(rs.getString("nombre")).append("\n")
                        .append("DNI: ").append(rs.getString("dni")).append("\n")
                        .append("Tipo: ").append(rs.getString("tipo")).append("\n")
                        .append("Monto: $").append(rs.getDouble("monto")).append("\n")
                        .append("-----------------------------\n");
            }

            if (sb.length() == 0) {
                textArea.setText("No hay transacciones registradas.");
            } else {
                textArea.setText(sb.toString());
            }

        } catch (SQLException e) {
            textArea.setText("Error al consultar transacciones: " + e.getMessage());
        }
    }
}

