package atm;

import java.time.LocalDateTime;

/**
 * Clase que representa una transacción bancaria.
 * Puede ser de tipo Depósito, Retiro, Consulta de saldo, etc.
 */
public class Transaction {
    private String type; // Ejemplo: "Deposit", "Withdrawal", "Balance Check"
    private double amount; // Monto involucrado en la transacción
    private LocalDateTime dateTime; // Fecha y hora de la transacción

    /**
     * Constructor: crea una transacción con tipo y monto especificado.
     * La fecha se guarda automáticamente al momento de crearla.
     */
    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.dateTime = LocalDateTime.now(); // Captura la fecha actual
    }

    /**
     * Devuelve una representación legible de la transacción.
     * Ejemplo: 2025-06-16T08:30:15 - Deposit: $100.0
     */
    @Override
    public String toString() {
        return dateTime + " - " + type + ": $" + amount;
    }
}
