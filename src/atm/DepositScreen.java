package atm; // Este archivo pertenece al paquete 'atm'.

import javax.swing.*; // Librería para crear interfaces gráficas (ventanas, botones, textos, etc.).
import java.awt.*;    // Librería para trabajar con componentes visuales como colores y fuentes.

/**
 * Pantalla gráfica para realizar depósitos a una cuenta bancaria.
 * Permite al usuario seleccionar una moneda, ingresar un monto y confirmar la operación.
 */
public class DepositScreen extends JFrame {

    private JTextArea display;                 // Área donde se muestra el monto ingresado
    private StringBuilder input = new StringBuilder(); // Objeto que acumula los dígitos del monto
    private JComboBox<CurrencyType> currencyBox;       // Desplegable para seleccionar tipo de moneda
    private String accountNumber;             // Número de cuenta del usuario que realiza el depósito

    /**
     * Constructor que inicializa la ventana de depósito.
     * @param accountNumber número de cuenta del usuario que accede.
     */
    public DepositScreen(String accountNumber) {
        this.accountNumber = accountNumber;

        // Configuración general de la ventana
        setTitle("ColCashFlow - Depósito");
        setSize(400, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(0, 150, 105, 234)); // Fondo rojo oscuro

        // Etiqueta "Moneda:"
        JLabel label = new JLabel("Moneda:");
        label.setBounds(30, 10, 80, 25);
        add(label);

        // ComboBox con los tipos de moneda definidos en CurrencyType
        currencyBox = new JComboBox<>(CurrencyType.values());
        currencyBox.setBounds(100, 10, 120, 25);
        add(currencyBox);

        // Área de texto donde se visualiza el monto digitado
        display = new JTextArea("Ingrese el monto a depositar:\n\n");
        display.setBounds(30, 50, 320, 100);
        display.setFont(new Font("Monospaced", Font.BOLD, 16));
        display.setBackground(Color.BLACK);
        display.setForeground(Color.GREEN);
        display.setEditable(false);
        display.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        add(display);

        // Botón para actualizar la visualización con la moneda seleccionada
        JButton updateBtn = new JButton("Actualizar");
        updateBtn.setBounds(240, 10, 110, 25);
        add(updateBtn);
        updateBtn.addActionListener(e -> updateDisplay());

        // Crear botones del 1 al 9 para simular un teclado numérico
        int x = 30, y = 170;
        for (int i = 1; i <= 9; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.setBounds(x, y, 90, 40);
            int digit = i;
            btn.addActionListener(e -> appendDigit(digit)); // Agrega el dígito a la entrada
            add(btn);
            x += 100;
            if (i % 3 == 0) {
                x = 30;
                y += 60;
            }
        }

        // Botón "0"
        JButton zeroBtn = new JButton("0");
        zeroBtn.setBounds(30, y, 90, 40);
        zeroBtn.addActionListener(e -> appendDigit(0));
        add(zeroBtn);

        // Botón "Borrar" para limpiar la entrada
        JButton clearBtn = new JButton("Borrar");
        clearBtn.setBounds(130, y, 90, 40);
        clearBtn.addActionListener(e -> {
            input.setLength(0); // Limpia el texto ingresado
            updateDisplay();
        });
        add(clearBtn);

        // Botón "Depositar" para realizar la transacción
        JButton enterBtn = new JButton("Depositar");
        enterBtn.setBounds(230, y, 90, 40);
        enterBtn.addActionListener(e -> performDeposit());
        add(enterBtn);

        // Etiqueta decorativa que simula el área de inserción de sobre
        JLabel envelopeSlot = new JLabel("Inserte sobre aquí", SwingConstants.CENTER);
        envelopeSlot.setBounds(100, 380, 200, 30);
        envelopeSlot.setOpaque(true);
        envelopeSlot.setBackground(Color.DARK_GRAY);
        envelopeSlot.setForeground(Color.WHITE);
        add(envelopeSlot);

        // Botón para volver al menú principal
        JButton backBtn = new JButton("Menú Principal");
        backBtn.setBounds(125, 430, 140, 30);
        add(backBtn);
        backBtn.addActionListener(e -> {
            new MainMenuScreen(accountNumber); // Regresa al menú principal
            dispose(); // Cierra la ventana actual
        });

        // Mostrar interfaz
        updateDisplay();
        setVisible(true);
    }

    /**
     * Actualiza el contenido de la pantalla con el monto ingresado y la moneda seleccionada.
     */
    private void updateDisplay() {
        CurrencyType currency = (CurrencyType) currencyBox.getSelectedItem();
        display.setText("Ingrese el monto a depositar:\n\n" + currency.getSymbol() + " " + input);
    }

    /**
     * Agrega un dígito al monto en construcción.
     * @param digit Dígito que el usuario seleccionó en el teclado.
     */
    private void appendDigit(int digit) {
        input.append(digit);
        updateDisplay();
    }

    /**
     * Ejecuta la lógica de validación y depósito del monto ingresado.
     * Convierte la moneda local a dólares y guarda la operación.
     */
    private void performDeposit() {
        CurrencyType currency = (CurrencyType) currencyBox.getSelectedItem();

        // Verifica que se haya ingresado al menos un número
        if (input.length() == 0) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto válido.");
            return;
        }

        try {
            double localAmount = Double.parseDouble(input.toString());

            // Rechaza montos negativos o cero
            if (localAmount <= 0) throw new NumberFormatException();

            // Convierte a dólares (moneda base)
            double usdAmount = currency.convertToUSD(localAmount);

            // Actualiza el saldo en la base de datos simulada
            FakeDatabase.deposit(accountNumber, usdAmount);
            FakeDatabase.saveAccounts();

            // Genera y guarda el recibo de la transacción
            ReceiptGenerator.generate(accountNumber, "Deposit", usdAmount);

            // Notifica al usuario y vuelve al menú
            JOptionPane.showMessageDialog(this,
                    "Depósito exitoso de " + currency.getSymbol() + " " + String.format("%.2f", localAmount));
            new MainMenuScreen(accountNumber);
            dispose();

        } catch (NumberFormatException ex) {
            // Captura errores por valores no numéricos o inválidos
            JOptionPane.showMessageDialog(this, "Monto no válido.");
        }
    }
}
