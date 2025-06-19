package atm;

import javax.swing.*;
import java.util.List;

public class TransactionHistoryScreen extends JFrame {

    // Constructor que recibe el número de cuenta para mostrar su historial
    public TransactionHistoryScreen(String accountNumber) {
        setTitle("ColCashFlow - Transaction History");
        setSize(400, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Área de texto donde se mostrará el historial
        JTextArea area = new JTextArea();
        area.setBounds(20, 60, 350, 180);
        area.setEditable(false);
        add(area);

        // Mostrar información del cliente
        Cliente cliente = FakeDatabase.getCliente(accountNumber);
        if (cliente != null) {
            JLabel nombreLabel = new JLabel("Titular: " + cliente.getNombre());
            nombreLabel.setBounds(20, 10, 200, 20);
            add(nombreLabel);

            JLabel dniLabel = new JLabel("DNI: " + cliente.getDni());
            dniLabel.setBounds(20, 30, 200, 20);
            add(dniLabel);
        }

        // Obtener historial de transacciones
        List<Transaction> history = FakeDatabase.getTransactionHistory(accountNumber);

        if (history.isEmpty()) {
            area.setText("No transactions found.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Transaction t : history) {
                sb.append(t.toString()).append("\n");
            }
            area.setText(sb.toString());
        }

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(140, 260, 100, 30);
        add(backBtn);

        backBtn.addActionListener(e -> {
            new MainMenuScreen(accountNumber);
            dispose();
        });

        setVisible(true);
    }
}