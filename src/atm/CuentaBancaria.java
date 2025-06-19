package atm; // Este archivo pertenece al paquete 'atm', que organiza las clases del sistema del cajero automático.

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una cuenta bancaria.
 * Almacena información clave como número de cuenta, PIN, saldo, moneda, cliente y transacciones.
 */
public class CuentaBancaria {

    // Número único asociado a la cuenta bancaria
    private String numeroCuenta;

    // Código secreto que permite el acceso a la cuenta
    private String pin;

    // Saldo actual de la cuenta (en la moneda base, generalmente dólares)
    private double balance;

    // Tipo de moneda que maneja la cuenta (por ejemplo USD, COP, EUR, etc.)
    private CurrencyType moneda;

    // Información del cliente dueño de esta cuenta
    private Cliente cliente;

    // Lista de transacciones realizadas en esta cuenta, almacenadas como textos simples
    private List<String> transacciones;

    /**
     * Constructor que inicializa una cuenta bancaria con todos sus datos.
     * @param numeroCuenta Número de cuenta asignado
     * @param pin Código PIN para acceso
     * @param balance Saldo inicial
     * @param moneda Moneda asociada
     * @param cliente Cliente dueño de la cuenta
     */
    public CuentaBancaria(String numeroCuenta, String pin, double balance, CurrencyType moneda, Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.pin = pin;
        this.balance = balance;
        this.moneda = moneda;
        this.cliente = cliente;
        this.transacciones = new ArrayList<>(); // Se inicia la lista de transacciones vacía
    }

    // Devuelve el saldo actual
    public double getBalance() {
        return balance;
    }

    // Devuelve la información del cliente dueño de la cuenta
    public Cliente getCliente() {
        return cliente;
    }

    // Devuelve el número de cuenta
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    // Devuelve el tipo de moneda de la cuenta
    public CurrencyType getMoneda() {
        return moneda;
    }

    // Devuelve el PIN de la cuenta
    public String getPin() {
        return pin;
    }

    // Permite actualizar el saldo (por ejemplo, tras un retiro o depósito)
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Agrega una transacción a la lista de historial.
     * @param tipo Tipo de transacción (ej: "Depósito", "Retiro", "Consulta")
     * @param monto Valor de la transacción
     */
    public void addTransaction(String tipo, double monto) {
        transacciones.add(tipo + ": " + monto);
    }

    /**
     * Devuelve la lista de transacciones registradas.
     * @return Lista de transacciones como cadenas de texto
     */
    public List<String> getTransacciones() {
        return transacciones;
    }
}

