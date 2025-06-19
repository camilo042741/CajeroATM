package atm;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Esta clase simula una base de datos en memoria que guarda las cuentas de los usuarios.
 * Utiliza una estructura HashMap para almacenar objetos de tipo Account.
 */
public class FakeDatabase {
    // Mapa que relaciona el número de cuenta con su objeto Account
    private static final HashMap<String, Account> accounts = new HashMap<>();

    // Bloque estático: se cargan dos cuentas de prueba al iniciar la aplicación
    static {
        accounts.put("12345", new Account("12345", "54321", 500.00, CurrencyType.USD));
        accounts.put("11111", new Account("11111", "22222", 300.00, CurrencyType.COP));
    }

    // Método para autenticar usuario por número de cuenta y PIN
    public static boolean authenticate(String account, String pin) {
        return accounts.containsKey(account) && accounts.get(account).pin.equals(pin);
    }

    // Método para realizar un retiro: descuenta saldo si hay fondos suficientes
    public static boolean withdraw(String account, double amount) {
        Account acc = accounts.get(account);
        if (acc.balance >= amount) {
            acc.balance -= amount;
            acc.addTransaction("Withdrawal", amount); // Registra la transacción
            return true;
        }
        return false;
    }

    // Método para depositar dinero en una cuenta
    public static void deposit(String account, double amount) {
        Account acc = accounts.get(account);
        acc.balance += amount;
        acc.addTransaction("Deposit", amount); // Registra la transacción
    }

    // Retorna el saldo actual de una cuenta, registrando también una consulta de saldo
    public static double getBalance(String account) {
        accounts.get(account).addTransaction("Balance Check", 0);
        return accounts.get(account).balance;
    }

    // Retorna el objeto Account asociado a un número de cuenta
    public static Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    // Devuelve el historial de transacciones de una cuenta
    public static List<Transaction> getTransactionHistory(String accountNumber) {
        Account acc = accounts.get(accountNumber);
        return acc != null ? acc.getHistory() : new ArrayList<>();
    }

    // Retorna información del cliente (nombre y DNI)
    public static String getClientInfo(String accountNumber) {
        Account acc = accounts.get(accountNumber);
        if (acc != null && acc.cliente != null) {
            return "Nombre: " + acc.cliente.getNombre() + ", DNI: " + acc.cliente.getDni();
        }
        return "Cuenta no encontrada.";
    }

    // Retorna el objeto Cliente de una cuenta
    public static Cliente getCliente(String accountNumber) {
        Account acc = accounts.get(accountNumber);
        return acc != null ? acc.getCliente() : null;
    }

    // Cambia la moneda configurada para una cuenta
    public static void setCurrency(String accountNumber, CurrencyType currency) {
        Account acc = accounts.get(accountNumber);
        if (acc != null) {
            acc.currency = currency;
        }
    }

    // Agrega una nueva cuenta a la base de datos simulada
    public static void addAccount(String accountNumber, String pin, double balanceUSD, CurrencyType currency, Cliente cliente) {
        accounts.put(accountNumber, new Account(accountNumber, pin, balanceUSD, currency, cliente));
    }

    // Guarda todas las cuentas en un archivo CSV (persistencia simple)
    public static void saveAccounts() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("cuentas.csv"))) {
            for (Account acc : accounts.values()) {
                bw.write(acc.accountNumber + "," + acc.pin + "," + acc.balance + "," +
                        acc.currency.name() + "," +
                        (acc.cliente != null ? acc.cliente.getNombre() : "") + "," +
                        (acc.cliente != null ? acc.cliente.getDni() : "") + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clase interna que representa una cuenta bancaria.
     * Almacena PIN, saldo, tipo de moneda, historial de transacciones y datos del cliente.
     */
    static class Account {
        String accountNumber;
        String pin;
        double balance;
        CurrencyType currency;
        List<Transaction> history = new ArrayList<>();
        Cliente cliente;

        // Constructor principal con cliente incluido
        Account(String accountNumber, String pin, double balance, CurrencyType currency, Cliente cliente) {
            this.accountNumber = accountNumber;
            this.pin = pin;
            this.balance = balance;
            this.currency = currency;
            this.cliente = cliente;
        }

        // Constructor alternativo (sin cliente explícito)
        Account(String accountNumber, String pin, double balance, CurrencyType currency) {
            this(accountNumber, pin, balance, currency, new Cliente("Desconocido", "00000000"));
        }

        // Agrega una transacción al historial y la registra en la base de datos real (SQLite)
        public void addTransaction(String type, double amount) {
            history.add(new Transaction(type, amount));
            if (cliente != null) {
                DBManager.registrarTransaccion(accountNumber, type, amount, cliente.getNombre(), cliente.getDni());
            }
        }

        // Retorna la lista de transacciones
        public List<Transaction> getHistory() {
            return history;
        }

        // Retorna el cliente asociado a la cuenta
        public Cliente getCliente() {
            return cliente;
        }
    }
}
