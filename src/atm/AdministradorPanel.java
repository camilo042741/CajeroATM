package atm;

import javax.swing.*;
import java.sql.*;

public class AdministradorPanel extends JFrame {

    public AdministradorPanel() {
        setTitle("Panel de Administración - Arqueo de Cajero");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JTextArea area = new JTextArea();
        JScrollPane scroll = new JScrollPane(area);
        add(scroll);

        try (Connection conn = DBManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM transacciones ORDER BY fecha DESC")) {

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append("Fecha: ").append(rs.getString("fecha"))
                        .append(" | Cuenta: ").append(rs.getString("cuenta"))
                        .append(" | Nombre: ").append(rs.getString("nombre"))
                        .append(" | DNI: ").append(rs.getString("dni"))
                        .append(" | Tipo: ").append(rs.getString("tipo"))
                        .append(" | Monto: ").append(rs.getDouble("monto"))
                        .append("\n");
            }
            area.setText(sb.toString());

        } catch (SQLException e) {
            area.setText("Error al cargar transacciones.");
            e.printStackTrace();
        }

        setVisible(true);
    }
}

