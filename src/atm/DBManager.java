package atm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static final String DB_URL = "jdbc:sqlite:colcashflow.db";

    static {
        crearTablas();
    }

    // Crear tablas si no existen
    private static void crearTablas() {
        try (Connection conn = DriverManager.getConnection(DB_URL); Statement stmt = conn.createStatement()) {
            // Tabla de cuentas
            String sqlCuentas = """
                CREATE TABLE IF NOT EXISTS cuentas (
                    accountNumber TEXT PRIMARY KEY,
                    pin TEXT NOT NULL,
                    balance REAL NOT NULL,
                    currency TEXT NOT NULL,
                    nombre TEXT NOT NULL,
                    dni TEXT NOT NULL
                )""";

            // Tabla de transacciones
            String sqlTransacciones = """
                CREATE TABLE IF NOT EXISTS transacciones (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    cuenta TEXT NOT NULL,
                    tipo TEXT NOT NULL,
                    monto REAL,
                    fecha TEXT,
                    nombre TEXT,
                    dni TEXT
                )""";

            stmt.execute(sqlCuentas);
            stmt.execute(sqlTransacciones);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean verificarLogin(String cuenta, String pin) {
        String sql = "SELECT * FROM cuentas WHERE accountNumber = ? AND pin = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cuenta);
            pstmt.setString(2, pin);
            return pstmt.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static FakeDatabase.Account obtenerCuenta(String cuenta) {
        String sql = "SELECT * FROM cuentas WHERE accountNumber = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cuenta);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String pin = rs.getString("pin");
                double balance = rs.getDouble("balance");
                CurrencyType currency = CurrencyType.valueOf(rs.getString("currency"));
                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");
                Cliente cliente = new Cliente(nombre, dni);
                return new FakeDatabase.Account(cuenta, pin, balance, currency, cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void actualizarCuenta(FakeDatabase.Account cuenta) {
        String sql = "UPDATE cuentas SET balance = ?, currency = ?, nombre = ?, dni = ? WHERE accountNumber = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, cuenta.balance);
            pstmt.setString(2, cuenta.currency.name());
            pstmt.setString(3, cuenta.cliente.getNombre());
            pstmt.setString(4, cuenta.cliente.getDni());
            pstmt.setString(5, cuenta.accountNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertarCuenta(FakeDatabase.Account cuenta) {
        String sql = "INSERT INTO cuentas (accountNumber, pin, balance, currency, nombre, dni) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cuenta.accountNumber);
            pstmt.setString(2, cuenta.pin);
            pstmt.setDouble(3, cuenta.balance);
            pstmt.setString(4, cuenta.currency.name());
            pstmt.setString(5, cuenta.cliente.getNombre());
            pstmt.setString(6, cuenta.cliente.getDni());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void registrarTransaccion(String cuenta, String tipo, double monto, String nombre, String dni) {
        String sql = "INSERT INTO transacciones (cuenta, tipo, monto, fecha, nombre, dni) VALUES (?, ?, ?, datetime('now'), ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cuenta);
            pstmt.setString(2, tipo);
            pstmt.setDouble(3, monto);
            pstmt.setString(4, nombre);
            pstmt.setString(5, dni);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Transaction> obtenerTransacciones(String cuenta) {
        List<Transaction> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacciones WHERE cuenta = ? ORDER BY fecha DESC";
        try (Connection conn = DriverManager.getConnection(DB_URL); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cuenta);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                double monto = rs.getDouble("monto");
                Timestamp fecha = rs.getTimestamp("fecha");
                Transaction t = new Transaction(tipo, monto);
                t.setDateTime(fecha.toLocalDateTime());
                lista.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
