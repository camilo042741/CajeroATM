package atm;

import java.sql.*;

public class DBManager {
    private static final String URL = "jdbc:sqlite:cajero.db";

    /**
     * Establece y retorna una conexión a la base de datos SQLite.
     */
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /**
     * Crea la tabla de transacciones si no existe.
     */
    public static void createTables() {
        String sql = """
            CREATE TABLE IF NOT EXISTS transacciones (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                cuenta TEXT NOT NULL,
                tipo TEXT NOT NULL,
                monto REAL NOT NULL,
                fecha TEXT DEFAULT (datetime('now')),
                nombre TEXT NOT NULL,
                dni TEXT NOT NULL
            );
        """;

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Intentando crear tabla transacciones...");
        } catch (SQLException e) {
            System.err.println("❌ Error al crear tabla: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void createCuentasTable() {
        String sql = """
        CREATE TABLE IF NOT EXISTS cuentas (
            numero TEXT PRIMARY KEY,
            pin TEXT NOT NULL,
            nombre TEXT NOT NULL,
            dni TEXT NOT NULL,
            moneda TEXT NOT NULL,
            saldo REAL DEFAULT 0
        );
    """;

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("✅ Tabla cuentas creada/verificada.");
        } catch (SQLException e) {
            System.err.println("❌ Error al crear tabla cuentas: " + e.getMessage());
            e.printStackTrace();
        }
    }





    /**
     * Inserta una nueva transacción en la base de datos.
     *
     * @param cuenta Número de cuenta
     * @param tipo Tipo de transacción (Depósito, Retiro, etc.)
     * @param monto Valor de la transacción
     * @param nombre Nombre del cliente
     * @param dni DNI del cliente
     */
    public static void registrarTransaccion(String cuenta, String tipo, double monto, String nombre, String dni) {
        String sql = "INSERT INTO transacciones (cuenta, tipo, monto, fecha, nombre, dni) VALUES (?, ?, ?, datetime('now'), ?, ?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cuenta);
            pstmt.setString(2, tipo);
            pstmt.setDouble(3, monto);
            pstmt.setString(4, nombre);
            pstmt.setString(5, dni);
            pstmt.executeUpdate();
            System.out.println("✅ Transacción registrada: " + tipo + " - " + monto);
        } catch (SQLException e) {
            System.err.println("❌ Error registrando transacción: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
