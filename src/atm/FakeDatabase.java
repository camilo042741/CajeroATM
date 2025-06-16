package atm; // El archivo pertenece al paquete 'atm'.

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Esta clase simula una base de datos en memoria que guarda las cuentas de los usuarios.
 */
public class FakeDatabase {
    // Mapa que almacena las cuentas, usando el número de cuenta como clave
    private static final HashMap<String, Account> accounts = new HashMap<>();

    // Bloque estático: se ejecuta una vez al iniciar el programa, agregando cuentas de ejemplo
    static {
        accounts.put("12345", new Account("12345", "54321", 500.00, CurrencyType.USD));
        accounts.put("11111", new Account("11111", "22222", 300.00, CurrencyType.COP));
    }

    /**
     * Verifica si el número de cuenta y el PIN coinciden.
     */
    public static boolean authenticate(String account, String pin) {
        return accounts.containsKey(account) && accounts.get(account).pin.equals(pin);
    }

    /**
     * Realiza un retiro si hay saldo suficiente.
     */
    public static boolean withdraw(String account, double amount) {
        Account acc = accounts.get(account);
        if (acc.balance >= amount) {
            acc.balance -= amount;
            acc.addTransaction("Withdrawal", amount); // Registra la transacción
            return true;
        }
        return false;
    }

    /**
     * Realiza un depósito a la cuenta.
     */
    public static void deposit(String account, double amount) {
        Account acc = accounts.get(account);
        acc.balance += amount;
        acc.addTransaction("Deposit", amount); // Registra la transacción
    }

    /**
     * Consulta el saldo actual de la cuenta.
     */
    public static double getBalance(String account) {
        accounts.get(account).addTransaction("Balance Check", 0); // Se registra como consulta
        return accounts.get(account).balance;
    }

    /**
     * Retorna la cuenta asociada al número.
     */
    public static Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    /**
     * Guarda todas las cuentas en un archivo CSV (cuentas.csv).
     */
    public static void saveAccounts() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("cuentas.csv"))) {
            for (Account acc : accounts.values()) {
                bw.write(acc.accountNumber + "," + acc.pin + "," + acc.balance + "," + acc.currency.name() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Devuelve el historial de transacciones de una cuenta.
     */
    public static List<Transaction> getTransactionHistory(String accountNumber) {
        Account acc = accounts.get(accountNumber);
        return acc != null ? acc.getHistory() : new ArrayList<>();
    }

    /**
     * Cambia la moneda preferida de la cuenta.
     */
    public static void setCurrency(String accountNumber, CurrencyType currency) {
        Account acc = accounts.get(accountNumber);
        if (acc != null) {
            acc.currency = currency;
        }
    }

    /**
     * Agrega una nueva cuenta al sistema.
     */
    public static void addAccount(String accountNumber, String pin, double balanceUSD, CurrencyType currency) {
        accounts.put(accountNumber, new Account(accountNumber, pin, balanceUSD, currency));
    }

    /**
     * Clase interna que representa una cuenta bancaria.
     */
    static class Account {
        String accountNumber; // Número de cuenta
        String pin;           // PIN de seguridad
        double balance;       // Saldo disponible en USD
        CurrencyType currency; // Moneda asociada a la cuenta
        List<Transaction> history = new ArrayList<>(); // Historial de transacciones

        /**
         * Constructor de la cuenta.
         */
        Account(String accountNumber, String pin, double balance, CurrencyType currency) {
            this.accountNumber = accountNumber;
            this.pin = pin;
            this.balance = balance;
            this.currency = currency;
        }

        /**
         * Agrega una transacción al historial.
         */
        public void addTransaction(String type, double amount) {
            history.add(new Transaction(type, amount));
        }

        /**
         * Devuelve el historial de transacciones.
         */
        public List<Transaction> getHistory() {
            return history;
        }
    }
}
