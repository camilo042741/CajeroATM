package atm;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase encargada de generar recibos en archivos de texto y también mostrarlos por pantalla.
 */
public class ReceiptGenerator {

    /**
     * Genera un recibo por una operación (depósito, retiro, etc.) y lo guarda como archivo .txt.
     * También lo muestra en pantalla con un cuadro de diálogo.
     *
     * @param accountNumber Número de cuenta del usuario
     * @param type Tipo de operación (por ejemplo: "Deposit", "Withdrawal")
     * @param amountUSD Monto en dólares (USD)
     */
    public static void generate(String accountNumber, String type, double amountUSD) {
        try {
            // Obtiene la cuenta del usuario y su moneda
            FakeDatabase.Account acc = FakeDatabase.getAccount(accountNumber);
            CurrencyType currency = acc.currency;

            // Convierte el monto de USD a la moneda local
            double localAmount = currency.convertFromUSD(amountUSD);

            // Formatea la fecha y hora actual para usarla en el nombre del archivo
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = dtf.format(LocalDateTime.now());
            String filename = "receipt_" + accountNumber + "_" + timestamp + ".txt";

            // Construye el contenido del recibo
            StringBuilder sb = new StringBuilder();
            sb.append("     ColCashFlow Receipt\n");
            sb.append("****************************\n");
            sb.append("Cuenta ").append(accountNumber).append("\n");
            sb.append("Operacion: ").append(type).append("\n");
            sb.append("Fecha: ").append(LocalDateTime.now()).append("\n\n");
            sb.append("Monto (USD): $").append(String.format("%.2f", amountUSD)).append("\n");
            sb.append("Monto(").append(currency.getSymbol()).append("): ")
                    .append(String.format("%.2f", localAmount)).append("\n");
            sb.append("Moneda seleccionada: ").append(currency.name()).append("\n");
            sb.append("****************************\n");
            sb.append("Gracias por usar ColCashFlow!\n");

            // Guarda el recibo en un archivo de texto
            FileWriter writer = new FileWriter(filename);
            writer.write(sb.toString());
            writer.close();

            // Muestra el recibo en una ventana
            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new java.awt.Dimension(400, 250));

            JOptionPane.showMessageDialog(null, scroll, "Recibo ColCashFlow", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera un recibo de bienvenida para una nueva cuenta, con o sin bono de $10.
     *
     * @param accountNumber Número de cuenta
     * @param balanceUSD Saldo inicial en USD
     * @param currency Moneda seleccionada
     * @param aplicaBono Si es true, se agrega un bono de $10 al saldo inicial
     */
    public static void generateWelcome(String accountNumber, double balanceUSD, CurrencyType currency, boolean aplicaBono) {
        try {
            // Calcula el saldo total si se aplica el bono
            double finalBalance = aplicaBono ? balanceUSD + 10 : balanceUSD;
            double localAmount = currency.convertFromUSD(finalBalance);

            // Genera timestamp para el nombre del archivo
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = dtf.format(LocalDateTime.now());
            String filename = "welcome_" + accountNumber + "_" + timestamp + ".txt";

            // Construye el contenido del recibo de bienvenida
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

            // Guarda el recibo en archivo
            FileWriter writer = new FileWriter(filename);
            writer.write(sb.toString());
            writer.close();

            // Muestra el recibo en pantalla
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
