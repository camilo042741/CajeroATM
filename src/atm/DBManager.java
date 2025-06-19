package atm; // Indica que esta clase forma parte del paquete 'atm'

import java.sql.*; // Importa todas las clases necesarias para trabajar con bases de datos SQL

/**
 * Clase encargada de la conexión y operaciones básicas con la base de datos SQLite del cajero.
 * Se encarga de conectar con la base, crear las tablas necesarias e insertar registros de transacciones.
 */
public class DBManager {

    // URL de conexión a la base de datos SQLite. El archivo se llama 'cajero.db' y se genera localmente.
    private static final String URL = "jdbc:sqlite:cajero.db";

    /**
     * Establece y retorna una conexión a la base de datos SQLite.
     * @return Objeto Connection para ejecutar consultas SQL.
     * @throws SQLException si ocurre un error al intentar conectarse.
     */
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /**
     * Crea la tabla de transacciones si no existe previamente.
     * Esta tabla almacena los datos de cada operación realizada por los usuarios (retiros, depósitos, etc.).
     */
    public static void createTables() {
        // Instrucción SQL para crear la tabla 'transacciones'
        String sql = """
            CREATE TABLE IF NOT EXISTS transacciones (
                id INTEGER PRIMARY KEY AUTOINCREMENT,   -- Identificador único de cada transacción
                cuenta TEXT NOT NULL,                   -- Número de cuenta asociada
                tipo TEXT NOT NULL,                     -- Tipo de transacción: Retiro, Depósito, Consulta, etc.
                monto REAL NOT NULL,                    -- Valor monetario de la transacción
                fecha TEXT DEFAULT (datetime('now')),   -- Fecha y hora en que se realizó (valor automático)
                nombre TEXT NOT NULL,                   -- Nombre del titular de la cuenta
                dni TEXT NOT NULL                       -- Documento de identidad del cliente
            );
        """;

        // Ejecuta la instrucción SQL para crear la tabla, si no existe
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Intentando crear tabla transacciones...");
        } catch (SQLException e) {
            System.err.println("❌ Error al crear tabla: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Crea la tabla de cuentas si no existe.
     * Esta tabla almacena la información básica de los usuarios del cajero automático.
     */
    public static void createCuentasTable() {
        // Instrucción SQL para crear la tabla 'cuentas'
        String sql = """
        CREATE TABLE IF NOT EXISTS cuentas (
            numero TEXT PRIMARY KEY,       -- Número de cuenta (clave única de la tabla)
            pin TEXT NOT NULL,             -- Contraseña o PIN del usuario
            nombre TEXT NOT NULL,          -- Nombre del cliente
            dni TEXT NOT NULL,             -- Documento de identidad
            moneda TEXT NOT NULL,          -- Moneda principal de la cuenta (USD, EUR, COP, etc.)
            saldo REAL DEFAULT 0           -- Saldo actual de la cuenta (inicia en 0 por defecto)
        );
        """;

        // Ejecuta la instrucción SQL para crear la tabla
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("✅ Tabla cuentas creada/verificada.");
        } catch (SQLException e) {
            System.err.println("❌ Error al crear tabla cuentas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Registra (inserta) una nueva transacción en la base de datos.
     *
     * @param cuenta Número de cuenta del usuario.
     * @param tipo Tipo de transacción (por ejemplo: Depósito, Retiro, Consulta).
     * @param monto Monto involucrado en la transacción.
     * @param nombre Nombre del cliente.
     * @param dni Documento de identidad del cliente.
     */
    public static void registrarTransaccion(String cuenta, String tipo, double monto, String nombre, String dni) {
        // Instrucción SQL preparada para insertar los datos
        String sql = "INSERT INTO transacciones (cuenta, tipo, monto, fecha, nombre, dni) VALUES (?, ?, ?, datetime('now'), ?, ?)";

        // Usa PreparedStatement para insertar datos de forma segura (evita inyección SQL)
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cuenta);    // Asigna el número de cuenta
            pstmt.setString(2, tipo);      // Asigna el tipo de transacción
            pstmt.setDouble(3, monto);     // Asigna el monto de la transacción
            pstmt.setString(4, nombre);    // Asigna el nombre del titular
            pstmt.setString(5, dni);       // Asigna el documento del titular
            pstmt.executeUpdate();         // Ejecuta la inserción en la base de datos
            System.out.println("✅ Transacción registrada: " + tipo + " - " + monto);
        } catch (SQLException e) {
            System.err.println("❌ Error registrando transacción: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
