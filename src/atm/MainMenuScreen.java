package atm;

import atm.TransactionHistoryScreen; // Importa la pantalla de historial de transacciones

import javax.swing.*;

/**
 * Esta clase representa el menú principal del cajero automático.
 * Desde aquí el usuario puede consultar su saldo, retirar, depositar,
 * ver su historial de transacciones o salir.
 */
public class MainMenuScreen extends JFrame {
    private String accountNumber; // Guarda el número de cuenta del usuario

    /**
     * Constructor: crea la ventana del menú principal.
     * @param accountNumber Número de cuenta del usuario autenticado
     */
    public MainMenuScreen(String accountNumber) {
        this.accountNumber = accountNumber;

        setTitle("ColCashFlow - Main Menu"); // Título de la ventana
        setSize(300, 300); // Tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cierra la app al salir
        setLocationRelativeTo(null); // Centra la ventana
        setLayout(null); // Usa posiciones absolutas

        // Título del menú
        JLabel label = new JLabel("Main Menu");
        label.setBounds(110, 10, 100, 30);
        add(label);

        // Botón para ver el saldo
        JButton balanceBtn = new JButton("1 - View Balance");
        balanceBtn.setBounds(50, 50, 200, 30);
        add(balanceBtn);

        // Botón para retirar dinero
        JButton withdrawBtn = new JButton("2 - Withdraw Cash");
        withdrawBtn.setBounds(50, 90, 200, 30);
        add(withdrawBtn);

        // Botón para depositar fondos
        JButton depositBtn = new JButton("3 - Deposit Funds");
        depositBtn.setBounds(50, 130, 200, 30);
        add(depositBtn);

        // Botón para salir
        JButton exitBtn = new JButton("4 - Exit");
        exitBtn.setBounds(50, 170, 200, 30);
        add(exitBtn);

        // Botón para ver el historial de transacciones
        JButton historyBtn = new JButton("5 - View History");
        historyBtn.setBounds(50, 210, 200, 30);
        add(historyBtn);

        // Acción al hacer clic en "View Balance"
        balanceBtn.addActionListener(e -> {
            new BalanceScreen(accountNumber); // Abre la pantalla de saldo
            dispose(); // Cierra el menú actual
        });

        // Acción al hacer clic en "Withdraw"
        withdrawBtn.addActionListener(e -> {
            new WithdrawScreen(accountNumber); // Abre la pantalla de retiro
            dispose();
        });

        // Acción al hacer clic en "Deposit"
        depositBtn.addActionListener(e -> {
            new DepositScreen(accountNumber); // Abre la pantalla de depósito
            dispose();
        });

        // Acción al hacer clic en "Exit"
        exitBtn.addActionListener(e -> {
            new ExitScreen(); // Muestra mensaje de despedida
            dispose();
        });

        // Acción al hacer clic en "View History"
        historyBtn.addActionListener(e -> {
            new TransactionHistoryScreen(accountNumber); // Abre historial
            dispose();
        });

        // Muestra la ventana
        setVisible(true);
    }
}
