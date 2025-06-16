package atm;

import javax.swing.*;
import java.awt.*;

public class WithdrawScreen extends JFrame {
    private JTextArea display; // Área de texto donde se muestra el menú de retiro
    private JComboBox<CurrencyType> currencyBox; // ComboBox para seleccionar moneda
    private String accountNumber; // Número de cuenta del usuario

    // Constructor que recibe el número de cuenta
    public WithdrawScreen(String accountNumber) {
        this.accountNumber = accountNumber;

        setTitle("ColCashFlow - Retiro"); // Título de la ventana
        setSize(400, 550); // Tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cierra la app al cerrar la ventana
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(null); // Posicionamiento absoluto
        getContentPane().setBackground(new Color(150, 0, 0)); // Fondo rojo

        // Etiqueta "Moneda"
        JLabel currencyLabel = new JLabel("Moneda:");
        currencyLabel.setBounds(30, 10, 100, 25);
        add(currencyLabel);

        // ComboBox con tipos de moneda
        currencyBox = new JComboBox<>(CurrencyType.values());
        currencyBox.setBounds(100, 10, 120, 25);
        add(currencyBox);

        // Área de texto donde se muestran las opciones de retiro
        display = new JTextArea();
        display.setBounds(30, 50, 320, 150);
        display.setFont(new Font("Monospaced", Font.BOLD, 14)); // Fuente tipo consola
        display.setBackground(Color.BLACK); // Fondo negro
        display.setForeground(Color.GREEN); // Texto verde
        display.setEditable(false); // No editable
        display.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Borde blanco
        add(display);

        // Botón para actualizar las opciones según la moneda seleccionada
        JButton updateBtn = new JButton("Actualizar");
        updateBtn.setBounds(240, 10, 110, 25);
        add(updateBtn);
        updateBtn.addActionListener(e -> updateMenu()); // Al hacer clic, actualiza

        // Botones del 1 al 6 (opciones de retiro)
        int x = 30, y = 220;
        for (int i = 1; i <= 6; i++) {
            JButton btn = new JButton(String.valueOf(i)); // Crea botón con número
            btn.setBounds(x, y, 90, 40); // Posición del botón
            int option = i; // Guarda el número de opción para el listener
            btn.addActionListener(e -> handleWithdrawOption(option)); // Acción del botón
            add(btn); // Agrega el botón

            x += 100; // Se mueve hacia la derecha
            if (i % 3 == 0) {
                x = 30; // Nueva fila
                y += 60;
            }
        }

        // Simulación del cajero donde se retira el dinero
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
            new MainMenuScreen(accountNumber); // Abre el menú
            dispose(); // Cierra esta ventana
        });

        updateMenu(); // Llama al método para mostrar las opciones al inicio
        setVisible(true); // Muestra la ventana
    }

    // Método que actualiza el menú de opciones de retiro
    private void updateMenu() {
        CurrencyType selected = (CurrencyType) currencyBox.getSelectedItem(); // Moneda seleccionada
        double[] usdAmounts = {20, 40, 60, 100, 200}; // Monto en USD
        StringBuilder options = new StringBuilder("      MENÚ DE RETIRO\n");

        // Genera el texto del menú con valores convertidos
        for (int i = 0; i < usdAmounts.length; i++) {
            double local = selected.convertFromUSD(usdAmounts[i]);
            options.append((i + 1)).append(" - ").append(selected.getSymbol()).append(" ")
                    .append(String.format("%.2f", local)).append("\n");
        }
        options.append("6 - Cancelar\n\nSeleccione una opción:");
        display.setText(options.toString()); // Se muestra en el área de texto
    }

    // Método que maneja las opciones seleccionadas por el usuario
    private void handleWithdrawOption(int option) {
        CurrencyType currency = (CurrencyType) currencyBox.getSelectedItem(); // Moneda actual
        double[] usdAmounts = {20, 40, 60, 100, 200};

        if (option >= 1 && option <= 5) { // Opciones de retiro válidas
            double usdAmount = usdAmounts[option - 1]; // Monto en USD
            double localAmount = currency.convertFromUSD(usdAmount); // Convertido

            // Intenta retirar el dinero
            if (FakeDatabase.withdraw(accountNumber, usdAmount)) {
                FakeDatabase.saveAccounts(); // Guarda cambios
                ReceiptGenerator.generate(accountNumber, "Withdrawal", usdAmount); // Genera recibo
                JOptionPane.showMessageDialog(this,
                        "Retiro exitoso de " + currency.getSymbol() + " " + String.format("%.2f", localAmount));
                new MainMenuScreen(accountNumber); // Vuelve al menú
                dispose(); // Cierra esta pantalla
            } else {
                JOptionPane.showMessageDialog(this, "Fondos insuficientes."); // No hay dinero suficiente
            }
        } else if (option == 6) {
            new MainMenuScreen(accountNumber); // Cancelar → vuelve al menú
            dispose();
        }
    }
}
