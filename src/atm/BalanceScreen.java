package atm; // Este archivo forma parte del paquete 'atm', que agrupa las clases del proyecto.

import javax.swing.*;  // Importa clases para construir interfaces gráficas (ventanas, botones, etiquetas, etc.).
import java.awt.*;     // Importa herramientas gráficas para colores, fuentes, y disposición manual de componentes.

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
        // Configura el título de la ventana
        setTitle("ColCashFlow - Consulta de Saldo");

        // Define el tamaño de la ventana
        setSize(400, 300);

        // Indica que la aplicación se cerrará si se cierra esta ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Centra la ventana en la pantalla
        setLocationRelativeTo(null);

        // Se usará diseño manual, por lo tanto se deben posicionar los componentes con coordenadas
        setLayout(null);

        // Cambia el color de fondo de la ventana a un azul oscuro
        getContentPane().setBackground(new Color(0, 0, 30));

        // Obtiene los datos de la cuenta desde la base de datos simulada
        FakeDatabase.Account acc = FakeDatabase.getAccount(accountNumber);

        // Extrae el saldo en dólares de la cuenta
        double balanceUSD = acc.balance;

        // Crea un título para la pantalla, centrado y con estilo
        JLabel title = new JLabel("Saldos en todas las monedas:", SwingConstants.CENTER);
        title.setBounds(50, 20, 300, 30);            // Posición y tamaño del título
        title.setForeground(Color.WHITE);            // Color blanco
        title.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente en negrita
        add(title); // Agrega el título a la ventana

        // Coordenada vertical inicial para las líneas de saldo
        int y = 60;

        // Recorre cada tipo de moneda disponible
        for (CurrencyType currency : CurrencyType.values()) {
            // Convierte el saldo desde USD a la moneda actual
            double converted = currency.convertFromUSD(balanceUSD);

            // Crea una etiqueta para mostrar el saldo convertido
            JLabel line = new JLabel(currency.getSymbol() + ": " + String.format("%.2f", converted), SwingConstants.CENTER);
            line.setBounds(50, y, 300, 25);     // Posición
            line.setForeground(Color.GREEN);    // Verde para resaltar el valor
            add(line);                          // Agrega al panel
            y += 30;                             // Baja para la siguiente línea
        }

        // Botón para regresar al menú principal
        JButton backBtn = new JButton("Menú Principal");
        backBtn.setBounds(125, y + 10, 140, 30); // Posición
        add(backBtn); // Agrega el botón

        // Define qué hacer cuando se hace clic en el botón
        backBtn.addActionListener(e -> {
            new MainMenuScreen(accountNumber); // Abre la pantalla del menú principal
            dispose();                          // Cierra esta ventana
        });

        // Registra la transacción de tipo "Consulta de saldo" con monto 0
        acc.addTransaction("Balance Check", 0);

        // Genera un recibo PDF o impresión digital de la transacción de consulta
        ReceiptGenerator.generate(accountNumber, "Balance Check", balanceUSD);

        // Muestra la ventana al usuario
        setVisible(true);
    }
}
