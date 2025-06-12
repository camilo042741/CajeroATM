package atm;

import atm.TransactionHistoryScreen;

import javax.swing.*;

public class MainMenuScreen extends JFrame {
    private String accountNumber;

    public MainMenuScreen(String accountNumber) {
        this.accountNumber = accountNumber;

        setTitle("ColCashFlow - Main Menu");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel label = new JLabel("Main Menu");
        label.setBounds(110, 10, 100, 30);
        add(label);

        JButton balanceBtn = new JButton("1 - View Balance");
        balanceBtn.setBounds(50, 50, 200, 30);
        add(balanceBtn);

        JButton withdrawBtn = new JButton("2 - Withdraw Cash");
        withdrawBtn.setBounds(50, 90, 200, 30);
        add(withdrawBtn);

        JButton depositBtn = new JButton("3 - Deposit Funds");
        depositBtn.setBounds(50, 130, 200, 30);
        add(depositBtn);

        JButton historyBtn = new JButton("5 - View History");
        historyBtn.setBounds(50, 210, 200, 30);
        add(historyBtn);

        historyBtn.addActionListener(e -> {
            new TransactionHistoryScreen(accountNumber);
            dispose();
        });


        JButton exitBtn = new JButton("4 - Exit");
        exitBtn.setBounds(50, 170, 200, 30);
        add(exitBtn);

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

        setVisible(true);
    }
}
