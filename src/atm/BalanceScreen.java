package atm; // Este archivo forma parte del paquete 'atm', que agrupa las clases del proyecto.

import javax.swing.*;  // Importa las clases necesarias para crear interfaces gráficas (botones, etiquetas, ventanas, etc.).
import java.awt.*;     // Importa clases relacionadas con el diseño gráfico (colores, fuentes, etc.).

/**
 * Clase que representa la pantalla donde el usuario puede consultar su saldo
 * en diferentes monedas.
 */
public class BalanceScreen extends JFrame {

    /**
     * Constructor que crea e inicializa la ventana de consulta de saldo.
     * @param accountNumber Número de cuenta del usuario para consultar su saldo.
     */
    public BalanceScreen(String accountNumber) {
        // Título de la ventana
        setTitle("ColCashFlow - Consulta de Saldo");

        // Tamaño de la ventana
        setSize(400, 300);

        // Cierra la aplicación al cerrar esta ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Centra la ventana en la pantalla
        setLocationRelativeTo(null);

        // Establece un diseño libre (posición manual de componentes)
        setLayout(null);

        // Establece el color de fondo (un azul oscuro)
        getContentPane().setBackground(new Color(0, 0, 30));

        // Obtiene la cuenta del usuario desde una base de datos simulada
        FakeDatabase.Account acc = FakeDatabase.getAccount(accountNumber);

        // Obtiene el saldo actual en dólares
        double balanceUSD = acc.balance;

        // Crea y configura el título de la pantalla
        JLabel title = new JLabel("Saldos en todas las monedas:", SwingConstants.CENTER);
        title.setBounds(50, 20, 300, 30);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 14));
        add(title);

        // Posición inicial en Y para las conversiones
        int y = 60;

        // Recorre todas las monedas disponibles para mostrar el saldo convertido
        for (CurrencyType currency : CurrencyType.values()) {
            double converted = currency.convertFromUSD(balanceUSD); // Convierte el saldo a la moneda actual
            JLabel line = new JLabel(currency.getSymbol() + ": " + String.format("%.2f", converted), SwingConstants.CENTER);
            line.setBounds(50, y, 300, 25);
            line.setForeground(Color.GREEN); // Color verde para los saldos
            add(line);
            y += 30; // Aumenta el espacio vertical para la siguiente moneda
        }

        // Botón para regresar al menú principal
        JButton backBtn = new JButton("Menú Principal");
        backBtn.setBounds(125, y + 10, 140, 30);
        add(backBtn);

        // Acción al hacer clic en el botón: abre el menú principal y cierra esta ventana
        backBtn.addActionListener(e -> {
            new MainMenuScreen(accountNumber); // Abre el menú principal
            dispose(); // Cierra la ventana actual
        });

        // Guarda una transacción de tipo "Consulta de saldo" con valor 0
        acc.addTransaction("Balance Check", 0);

        // Genera un recibo de esta consulta de saldo
        ReceiptGenerator.generate(accountNumber, "Balance Check", balanceUSD);

        // Muestra la ventana
        setVisible(true);
    }
}