package atm; // Este archivo forma parte del paquete 'atm'.

import javax.swing.*; // Librerías para interfaz gráfica.
import java.io.FileWriter; // Para escribir archivos de texto.
import java.io.IOException;
import java.time.LocalDateTime; // Para obtener la fecha y hora actual.
import java.time.format.DateTimeFormatter; // Para dar formato a la fecha.

/**
 * Clase encargada de generar recibos de transacciones o bienvenida.
 * Los recibos se guardan como archivos .txt y también se muestran en pantalla.
 */
public class ReceiptGenerator {

    /**
     * Genera un recibo por una operación bancaria (retiro, depósito, etc.).
     * El recibo se guarda en un archivo .txt con la fecha y hora del evento y se muestra al usuario.
     *
     * @param accountNumber Número de cuenta involucrado en la operación.
     * @param type Tipo de transacción (por ejemplo: "Deposit", "Withdrawal").
     * @param amountUSD Monto de la transacción expresado en dólares estadounidenses.
     */
    public static void generate(String accountNumber, String type, double amountUSD) {
        try {
            // Obtiene la cuenta asociada al número
            FakeDatabase.Account acc = FakeDatabase.getAccount(accountNumber);
            CurrencyType currency = acc.currency;

            // Convierte el monto a la moneda seleccionada por el cliente
            double localAmount = currency.convertFromUSD(amountUSD);

            // Formato para la fecha que se incluirá en el nombre del archivo
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

            // Escribe el recibo en un archivo de texto local
            FileWriter writer = new FileWriter(filename);
            writer.write(sb.toString());
            writer.close();

            // Muestra el recibo al usuario mediante una ventana
            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new java.awt.Dimension(400, 250));

            JOptionPane.showMessageDialog(null, scroll, "Recibo ColCashFlow", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace(); // Imprime error si hay fallos de escritura en archivo
        }
    }

    /**
     * Genera un recibo especial de bienvenida al crear una cuenta nueva.
     * Si el saldo inicial supera los $50 USD, se incluye un bono de $10.
     *
     * @param accountNumber Número de cuenta creada.
     * @param balanceUSD Saldo inicial ingresado por el cliente (en USD).
     * @param currency Moneda elegida por el cliente.
     * @param aplicaBono Indica si se aplicó el bono de bienvenida.
     */
    public static void generateWelcome(String accountNumber, double balanceUSD, CurrencyType currency, boolean aplicaBono) {
        try {
            // Calcula el saldo final teniendo en cuenta el bono (si aplica)
            double finalBalance = aplicaBono ? balanceUSD + 10 : balanceUSD;
            double localAmount = currency.convertFromUSD(finalBalance);

            // Formato de fecha para el nombre del archivo
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

            // Muestra el recibo de bienvenida
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
