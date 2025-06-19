package atm; // Este archivo pertenece al paquete 'atm'.

import java.time.LocalDateTime; // Importa la clase para manejar fechas y horas.

/**
 * Clase que representa una transacción bancaria individual.
 * Una transacción puede ser de tipo Depósito, Retiro, Consulta de saldo, etc.
 */
public class Transaction {

    private String type; // Tipo de transacción (por ejemplo, "Deposit", "Withdrawal", "Balance Check")
    private double amount; // Monto de dinero involucrado en la transacción
    private LocalDateTime dateTime; // Fecha y hora exacta en la que ocurrió la transacción

    /**
     * Constructor de la clase Transaction.
     * Al crear la transacción se especifica el tipo y el monto.
     * La fecha y hora se asignan automáticamente con el momento actual.
     *
     * @param type Tipo de transacción (texto)
     * @param amount Monto involucrado (en USD)
     */
    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.dateTime = LocalDateTime.now(); // Captura la fecha y hora actual del sistema
    }

    /**
     * Convierte la transacción a una cadena de texto legible.
     * Se usa para mostrar el historial al usuario.
     *
     * @return Cadena que representa la transacción con fecha, tipo y monto.
     */
    @Override
    public String toString() {
        return dateTime + " - " + type + ": $" + amount;
        // Ejemplo de salida: 2025-06-16T08:30:15 - Deposit: $100.0
    }
}
