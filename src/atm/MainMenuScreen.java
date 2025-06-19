package atm;

import javax.swing.*;

/**
 * Esta clase representa el menú principal del cajero automático.
 */
public class MainMenuScreen extends JFrame {
    private String accountNumber;

    public MainMenuScreen(String accountNumber) {
        this.accountNumber = accountNumber;

        setTitle("ColCashFlow - Main Menu");
        setSize(300, 320);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Título del menú
        JLabel label = new JLabel("Main Menu");
        label.setBounds(110, 10, 100, 30);
        add(label);

        // Mostrar nombre del cliente
        Cliente cliente = FakeDatabase.getCliente(accountNumber);
        if (cliente != null) {
            JLabel clienteLabel = new JLabel("Cliente: " + cliente.getNombre());
            clienteLabel.setBounds(20, 40, 250, 20);
            add(clienteLabel);
        }

        // Botones del menú
        JButton balanceBtn = new JButton("1 - View Balance");
        balanceBtn.setBounds(50, 70, 200, 30);
        add(balanceBtn);

        JButton withdrawBtn = new JButton("2 - Withdraw Cash");
        withdrawBtn.setBounds(50, 110, 200, 30);
        add(withdrawBtn);

        JButton depositBtn = new JButton("3 - Deposit Funds");
        depositBtn.setBounds(50, 150, 200, 30);
        add(depositBtn);

        JButton exitBtn = new JButton("4 - Exit");
        exitBtn.setBounds(50, 190, 200, 30);
        add(exitBtn);

        JButton historyBtn = new JButton("5 - View History");
        historyBtn.setBounds(50, 230, 200, 30);
        add(historyBtn);

        // Acciones
        balanceBtn.addActionListener(e -> {
            new BalanceScreen(accountNumber);
            dispose();
        });

        withdrawBtn.addActionListener(e -> {
            new WithdrawScreen(accountNumber);
            dispose();
        });

        depositBtn.addActionListener(e -> {
            new DepositScreen(accountNumber);
            dispose();
        });

        exitBtn.addActionListener(e -> {
            new ExitScreen();
            dispose();
        });

        historyBtn.addActionListener(e -> {
            new TransactionHistoryScreen(accountNumber);
            dispose();
        });

        setVisible(true);
    }
}
