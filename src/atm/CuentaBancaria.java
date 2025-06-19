package atm;

import java.util.ArrayList;
import java.util.List;

public class CuentaBancaria {
    private String numeroCuenta;
    private String pin;
    private double balance;
    private CurrencyType moneda;
    private Cliente cliente;
    private List<String> transacciones;

    public CuentaBancaria(String numeroCuenta, String pin, double balance, CurrencyType moneda, Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.pin = pin;
        this.balance = balance;
        this.moneda = moneda;
        this.cliente = cliente;
        this.transacciones = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public CurrencyType getMoneda() {
        return moneda;
    }

    public String getPin() {
        return pin;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addTransaction(String tipo, double monto) {
        transacciones.add(tipo + ": " + monto);
    }

    public List<String> getTransacciones() {
        return transacciones;
    }
}
