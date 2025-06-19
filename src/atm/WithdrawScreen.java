package atm; // Este archivo pertenece al paquete 'atm'.

import javax.swing.*; // Librería para crear interfaces gráficas.
import java.awt.*;    // Librería para componentes visuales como colores y fuentes.

/**
 * Pantalla para que el usuario realice retiros de su cuenta.
 * Ofrece múltiples opciones de monto y permite elegir la moneda.
 */
public class WithdrawScreen extends JFrame {

    private JTextArea display; // Área donde se muestra el menú de retiro
    private JComboBox<CurrencyType> currencyBox; // Menú desplegable para seleccionar la moneda
    private String accountNumber; // Número de cuenta del usuario

    /**
     * Constructor que inicializa la ventana de retiro.
     * @param accountNumber Número de cuenta del usuario que realiza el retiro.
     */
    public WithdrawScreen(String accountNumber) {
        this.accountNumber = accountNumber;

        // Configuración general de la ventana
        setTitle("ColCashFlow - Retiro");
        setSize(400, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0, 150, 105, 234)); // Color de fondo personalizado

        // Etiqueta "Moneda:"
        JLabel currencyLabel = new JLabel("Moneda:");
        currencyLabel.setBounds(30, 10, 100, 25);
        add(currencyLabel);

        // ComboBox con los tipos de moneda disponibles
        currencyBox = new JComboBox<>(CurrencyType.values());
        currencyBox.setBounds(100, 10, 120, 25);
        add(currencyBox);

        // Área de texto donde se muestran las opciones de retiro
        display = new JTextArea();
        display.setBounds(30, 50, 320, 150);
        display.setFont(new Font("Monospaced", Font.BOLD, 14));
        display.setBackground(Color.BLACK);
        display.setForeground(Color.GREEN);
        display.setEditable(false);
        display.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        add(display);

        // Botón para actualizar el menú según la moneda seleccionada
        JButton updateBtn = new JButton("Actualizar");
        updateBtn.setBounds(240, 10, 110, 25);
        add(updateBtn);
        updateBtn.addActionListener(e -> updateMenu());

        // Botones de opción de retiro (1 al 6)
        int x = 30, y = 220;
        for (int i = 1; i <= 6; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.setBounds(x, y, 90, 40);
            int option = i;
            btn.addActionListener(e -> handleWithdrawOption(option));
            add(btn);

            x += 100;
            if (i % 3 == 0) {
                x = 30;
                y += 60;
            }
        }

        // Simula la ranura donde se retira el dinero
        JLabel cashSlot = new JLabel("Retire su dinero aquí", SwingConstants.CENTER);
        cashSlot.setBounds(100, 380, 200, 30);
        cashSlot.setOpaque(true);
        cashSlot.setBackground(Color.DARK_GRAY);
        cashSlot.setForeground(Color.WHITE);
        add(cashSlot);

        // Botón para volver al menú principal
        JButton backBtn = new JButton("Menú Principal");
        backBtn.setBounds(125, 430, 140, 30);
        add(backBtn);
        backBtn.addActionListener(e -> {
            new MainMenuScreen(accountNumber);
            dispose();
        });

        // Carga el menú de opciones inicialmente
        updateMenu();
        setVisible(true);
    }

    /**
     * Genera dinámicamente el menú de opciones de retiro con base en la moneda seleccionada.
     * Convierte los montos desde dólares a la moneda seleccionada.
     */
    private void updateMenu() {
        CurrencyType selected = (CurrencyType) currencyBox.getSelectedItem();
        double[] usdAmounts = {20, 40, 60, 100, 200}; // Opciones estándar en USD
        StringBuilder options = new StringBuilder("      MENÚ DE RETIRO\n");

        for (int i = 0; i < usdAmounts.length; i++) {
            double local = selected.convertFromUSD(usdAmounts[i]);
            options.append((i + 1)).append(" - ")
                    .append(selected.getSymbol()).append(" ")
                    .append(String.format("%.2f", local)).append("\n");
        }

        options.append("6 - Cancelar\n\nSeleccione una opción:");
        display.setText(options.toString());
    }

    /**
     * Maneja la opción de retiro seleccionada por el usuario.
     * @param option Número de opción seleccionada (1-6).
     */
    private void handleWithdrawOption(int option) {
        CurrencyType currency = (CurrencyType) currencyBox.getSelectedItem();
        double[] usdAmounts = {20, 40, 60, 100, 200};

        if (option >= 1 && option <= 5) {
            double usdAmount = usdAmounts[option - 1];
            double localAmount = currency.convertFromUSD(usdAmount);

            // Intenta realizar el retiro desde la base de datos simulada
            if (FakeDatabase.withdraw(accountNumber, usdAmount)) {
                FakeDatabase.saveAccounts(); // Guarda cambios
                ReceiptGenerator.generate(accountNumber, "Withdrawal", usdAmount); // Genera recibo
                JOptionPane.showMessageDialog(this,
                        "Retiro exitoso de " + currency.getSymbol() + " " + String.format("%.2f", localAmount));
                new MainMenuScreen(accountNumber);
                dispose();
            } else {
                // No hay suficientes fondos
                JOptionPane.showMessageDialog(this, "Fondos insuficientes.");
            }
        } else if (option == 6) {
            // Cancelar operación
            new MainMenuScreen(accountNumber);
            dispose();
        }
    }
}
