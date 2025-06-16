package atm; // Este archivo pertenece al paquete 'atm'.

import javax.swing.*; // Librería para crear interfaces gráficas.
import java.awt.*;    // Librería para manejar colores, fuentes, etc.

/**
 * Pantalla gráfica para realizar depósitos a una cuenta bancaria.
 */
public class DepositScreen extends JFrame {

    // Área de texto donde se muestra el monto a depositar
    private JTextArea display;

    // Objeto para ir construyendo el número que el usuario digita
    private StringBuilder input = new StringBuilder();

    // Desplegable para elegir la moneda del depósito
    private JComboBox<CurrencyType> currencyBox;

    // Número de cuenta del usuario
    private String accountNumber;

    /**
     * Constructor que inicializa la interfaz para realizar depósitos.
     */
    public DepositScreen(String accountNumber) {
        this.accountNumber = accountNumber;

        // Configuración de la ventana
        setTitle("ColCashFlow - Depósito");
        setSize(400, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(150, 0, 0)); // Fondo rojo

        // Etiqueta de "Moneda:"
        JLabel label = new JLabel("Moneda:");
        label.setBounds(30, 10, 80, 25);
        add(label);

        // ComboBox para seleccionar el tipo de moneda
        currencyBox = new JComboBox<>(CurrencyType.values());
        currencyBox.setBounds(100, 10, 120, 25);
        add(currencyBox);

        // Área de texto donde se muestra el monto digitado
        display = new JTextArea("Ingrese el monto a depositar:\n\n");
        display.setBounds(30, 50, 320, 100);
        display.setFont(new Font("Monospaced", Font.BOLD, 16));
        display.setBackground(Color.BLACK);
        display.setForeground(Color.GREEN);
        display.setEditable(false);
        display.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        add(display);

        // Botón para actualizar la visualización según la moneda seleccionada
        JButton updateBtn = new JButton("Actualizar");
        updateBtn.setBounds(240, 10, 110, 25);
        add(updateBtn);
        updateBtn.addActionListener(e -> updateDisplay());

        // Crea botones del 1 al 9 como un teclado numérico
        int x = 30, y = 170;
        for (int i = 1; i <= 9; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.setBounds(x, y, 90, 40);
            int digit = i;
            btn.addActionListener(e -> appendDigit(digit)); // Agrega el dígito al monto
            add(btn);
            x += 100;
            if (i % 3 == 0) {
                x = 30;
                y += 60; // Pasa a la siguiente fila después de cada 3 botones
            }
        }

        // Botón para el número 0
        JButton zeroBtn = new JButton("0");
        zeroBtn.setBounds(30, y, 90, 40);
        zeroBtn.addActionListener(e -> appendDigit(0));
        add(zeroBtn);

        // Botón para borrar todo lo digitado
        JButton clearBtn = new JButton("Borrar");
        clearBtn.setBounds(130, y, 90, 40);
        clearBtn.addActionListener(e -> {
            input.setLength(0); // Limpia el texto ingresado
            updateDisplay();
        });
        add(clearBtn);

        // Botón para realizar el depósito
        JButton enterBtn = new JButton("Depositar");
        enterBtn.setBounds(230, y, 90, 40);
        enterBtn.addActionListener(e -> performDeposit());
        add(enterBtn);

        // Representación gráfica del lugar donde se "inserta el sobre"
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
            new MainMenuScreen(accountNumber); // Abre menú principal
            dispose(); // Cierra la ventana actual
        });

        // Muestra el texto inicial
        updateDisplay();
        setVisible(true);
    }

    /**
     * Actualiza el texto del área de visualización con el monto actual ingresado
     * y el símbolo de la moneda seleccionada.
     */
    private void updateDisplay() {
        CurrencyType currency = (CurrencyType) currencyBox.getSelectedItem();
        display.setText("Ingrese el monto a depositar:\n\n" + currency.getSymbol() + " " + input);
    }

    /**
     * Agrega un dígito al monto que el usuario está ingresando.
     */
    private void appendDigit(int digit) {
        input.append(digit);
        updateDisplay();
    }

    /**
     * Valida e intenta realizar el depósito.
     * Convierte el monto ingresado a dólares y lo guarda en la base de datos simulada.
     */
    private void performDeposit() {
        CurrencyType currency = (CurrencyType) currencyBox.getSelectedItem();

        // Si no se ha ingresado ningún número
        if (input.length() == 0) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto válido.");
            return;
        }

        try {
            double localAmount = Double.parseDouble(input.toString());

            // Validación para montos negativos o cero
            if (localAmount <= 0) throw new NumberFormatException();

            // Convierte el monto a dólares
            double usdAmount = currency.convertToUSD(localAmount);

            // Deposita el dinero en la cuenta
            FakeDatabase.deposit(accountNumber, usdAmount);

            // Guarda los cambios en la base de datos simulada
            FakeDatabase.saveAccounts();

            // Genera un recibo de la transacción
            ReceiptGenerator.generate(accountNumber, "Deposit", usdAmount);

            // Muestra mensaje de éxito
            JOptionPane.showMessageDialog(this,
                    "Depósito exitoso de " + currency.getSymbol() + " " + String.format("%.2f", localAmount));

            // Vuelve al menú principal
            new MainMenuScreen(accountNumber);
            dispose();

        } catch (NumberFormatException ex) {
            // Si el número ingresado no es válido (por ejemplo, texto o negativo)
            JOptionPane.showMessageDialog(this, "Monto no válido.");
        }
    }
}
