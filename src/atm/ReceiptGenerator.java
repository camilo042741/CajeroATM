package atm;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptGenerator {
    public static void generate(String accountNumber, String type, double amountUSD) {
        try {
            FakeDatabase.Account acc = FakeDatabase.getAccount(accountNumber);
            CurrencyType currency = acc.currency;
            double localAmount = currency.convertFromUSD(amountUSD);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = dtf.format(LocalDateTime.now());
            String filename = "receipt_" + accountNumber + "_" + timestamp + ".txt";

            StringBuilder sb = new StringBuilder();
            sb.append("     ColCashFlow Receipt\n");
            sb.append("****************************\n");
            sb.append("Account: ").append(accountNumber).append("\n");
            sb.append("Operation: ").append(type).append("\n");
            sb.append("Date: ").append(LocalDateTime.now()).append("\n\n");
            sb.append("Amount (USD): $").append(String.format("%.2f", amountUSD)).append("\n");
            sb.append("Amount (").append(currency.getSymbol()).append("): ")
                    .append(String.format("%.2f", localAmount)).append("\n");
            sb.append("Currency selected: ").append(currency.name()).append("\n");
            sb.append("****************************\n");
            sb.append("Thank you for using ColCashFlow!\n");

            FileWriter writer = new FileWriter(filename);
            writer.write(sb.toString());
            writer.close();

            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new java.awt.Dimension(400, 250));

            JOptionPane.showMessageDialog(null, scroll, "Recibo ColCashFlow", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ✅ Recibo de bienvenida con o sin bono
    public static void generateWelcome(String accountNumber, double balanceUSD, CurrencyType currency, boolean aplicaBono) {
        try {
            double finalBalance = aplicaBono ? balanceUSD + 10 : balanceUSD;
            double localAmount = currency.convertFromUSD(finalBalance);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = dtf.format(LocalDateTime.now());
            String filename = "welcome_" + accountNumber + "_" + timestamp + ".txt";

            StringBuilder sb = new StringBuilder();
            sb.append("     Bienvenido a ColCashFlow\n");
            sb.append("****************************\n");
            sb.append("Cuenta creada exitosamente.\n");
            sb.append("Número de cuenta: ").append(accountNumber).append("\n");
            sb.append("Saldo inicial (USD): $").append(String.format("%.2f", balanceUSD)).append("\n");

            if (aplicaBono) {
                sb.append("¡Felicitaciones! Has recibido un bono de bienvenida.\n");
                sb.append("Bono de bienvenida (USD): $10.00\n");
            }

            sb.append("Saldo total (USD): $").append(String.format("%.2f", finalBalance)).append("\n");
            sb.append("Saldo en ").append(currency.getSymbol()).append(": ")
                    .append(String.format("%.2f", localAmount)).append("\n");
            sb.append("Moneda seleccionada: ").append(currency.name()).append("\n");
            sb.append("Fecha: ").append(LocalDateTime.now()).append("\n");
            sb.append("****************************\n");
            sb.append("Gracias por confiar en ColCashFlow\n");

            FileWriter writer = new FileWriter(filename);
            writer.write(sb.toString());
            writer.close();

            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new java.awt.Dimension(400, 250));

            JOptionPane.showMessageDialog(null, scroll, "Cuenta Creada", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
