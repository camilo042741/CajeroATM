package atm;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class FakeDatabase {
    private static final HashMap<String, Account> accounts = new HashMap<>();

    static {
        accounts.put("12345", new Account("12345", "54321", 500.00, CurrencyType.USD));
        accounts.put("11111", new Account("11111", "22222", 300.00, CurrencyType.COP));
    }

    public static boolean authenticate(String account, String pin) {
        return accounts.containsKey(account) && accounts.get(account).pin.equals(pin);
    }

    public static boolean withdraw(String account, double amount) {
        Account acc = accounts.get(account);
        if (acc.balance >= amount) {
            acc.balance -= amount;
            acc.addTransaction("Withdrawal", amount);
            return true;
        }
        return false;
    }

    public static void deposit(String account, double amount) {
        Account acc = accounts.get(account);
        acc.balance += amount;
        acc.addTransaction("Deposit", amount);
    }

    public static double getBalance(String account) {
        accounts.get(account).addTransaction("Balance Check", 0);
        return accounts.get(account).balance;
    }

    public static Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public static void saveAccounts() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("cuentas.csv"))) {
            for (Account acc : accounts.values()) {
                bw.write(acc.accountNumber + "," + acc.pin + "," + acc.balance + "," + acc.currency.name() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Transaction> getTransactionHistory(String accountNumber) {
        Account acc = accounts.get(accountNumber);
        return acc != null ? acc.getHistory() : new ArrayList<>();
    }

    public static void setCurrency(String accountNumber, CurrencyType currency) {
        Account acc = accounts.get(accountNumber);
        if (acc != null) {
            acc.currency = currency;
        }
    }

    public static void addAccount(String accountNumber, String pin, double balanceUSD, CurrencyType currency) {
        accounts.put(accountNumber, new Account(accountNumber, pin, balanceUSD, currency));
    }

    static class Account {
        String accountNumber;
        String pin;
        double balance;
        CurrencyType currency;
        List<Transaction> history = new ArrayList<>();

        Account(String accountNumber, String pin, double balance, CurrencyType currency) {
            this.accountNumber = accountNumber;
            this.pin = pin;
            this.balance = balance;
            this.currency = currency;
        }

        public void addTransaction(String type, double amount) {
            history.add(new Transaction(type, amount));
        }

        public List<Transaction> getHistory() {
            return history;
        }
    }
}
