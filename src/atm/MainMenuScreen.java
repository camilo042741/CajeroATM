package atm; // Indica que esta clase pertenece al paquete 'atm'.

import javax.swing.*; // Librería para interfaces gráficas (Swing).

/**
 * Clase que representa la pantalla principal del sistema ColCashFlow.
 * Ofrece al usuario opciones básicas de navegación: consultar saldo,
 * retirar, depositar, ver historial y salir del sistema.
 */
public class MainMenuScreen extends JFrame {

    private String accountNumber; // Número de cuenta del usuario actual.

    /**
     * Constructor: Inicializa el menú principal del cajero.
     *
     * @param accountNumber Número de cuenta autenticado.
     */
    public MainMenuScreen(String accountNumber) {
        this.accountNumber = accountNumber;

        // Configuración básica de la ventana
        setTitle("ColCashFlow - Main Menu");      // Título de la ventana
        setSize(300, 320);                         // Tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);   // Cierra la app al cerrar ventana
        setLocationRelativeTo(null);               // Centrada en la pantalla
        setLayout(null);                           // Posicionamiento manual

        // Título visual del menú
        JLabel label = new JLabel("Main Menu");
        label.setBounds(110, 10, 100, 30);
        add(label);

        // Mostrar nombre del cliente autenticado
        Cliente cliente = FakeDatabase.getCliente(accountNumber);
        if (cliente != null) {
            JLabel clienteLabel = new JLabel("Cliente: " + cliente.getNombre());
            clienteLabel.setBounds(20, 40, 250, 20);
            add(clienteLabel);
        }

        // Botón: Ver saldo
        JButton balanceBtn = new JButton("1 - View Balance");
        balanceBtn.setBounds(50, 70, 200, 30);
        add(balanceBtn);

        // Botón: Retirar efectivo
        JButton withdrawBtn = new JButton("2 - Withdraw Cash");
        withdrawBtn.setBounds(50, 110, 200, 30);
        add(withdrawBtn);

        // Botón: Depositar fondos
        JButton depositBtn = new JButton("3 - Deposit Funds");
        depositBtn.setBounds(50, 150, 200, 30);
        add(depositBtn);

        // Botón: Salir del sistema
        JButton exitBtn = new JButton("4 - Exit");
        exitBtn.setBounds(50, 190, 200, 30);
        add(exitBtn);

        // Botón: Ver historial de transacciones
        JButton historyBtn = new JButton("5 - View History");
        historyBtn.setBounds(50, 230, 200, 30);
        add(historyBtn);

        // Acciones de los botones

        // Al hacer clic en "Ver saldo"
        balanceBtn.addActionListener(e -> {
            new BalanceScreen(accountNumber); // Abre pantalla de saldo
            dispose(); // Cierra la ventana actual
        });

        // Al hacer clic en "Retirar"
        withdrawBtn.addActionListener(e -> {
            new WithdrawScreen(accountNumber); // Abre pantalla de retiro
            dispose();
        });

        // Al hacer clic en "Depositar"
        depositBtn.addActionListener(e -> {
            new DepositScreen(accountNumber); // Abre pantalla de depósito
            dispose();
        });

        // Al hacer clic en "Salir"
        exitBtn.addActionListener(e -> {
            new ExitScreen(); // Abre pantalla de despedida o salida
            dispose();
        });

        // Al hacer clic en "Ver historial"
        historyBtn.addActionListener(e -> {
            new TransactionHistoryScreen(accountNumber); // Muestra historial
            dispose();
        });

        // Muestra la ventana
        setVisible(true);
    }
}
