package atm;

import javax.swing.*;
import java.util.List;

public class TransactionHistoryScreen extends JFrame {

    // Constructor que recibe el número de cuenta para mostrar su historial
    public TransactionHistoryScreen(String accountNumber) {
        setTitle("ColCashFlow - Transaction History"); // Título de la ventana
        setSize(400, 300); // Tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
        setLocationRelativeTo(null); // Centra la ventana en pantalla
        setLayout(null); // Se usará posicionamiento absoluto (coordenadas manuales)

        // Área de texto donde se mostrará el historial
        JTextArea area = new JTextArea();
        area.setBounds(20, 20, 350, 180); // Posición y tamaño del área
        area.setEditable(false); // No se permite edición del texto
        add(area); // Se añade el área a la ventana

        // Obtiene el historial de transacciones del usuario
        List<Transaction> history = FakeDatabase.getTransactionHistory(accountNumber);

        // Si no hay transacciones, muestra un mensaje
        if (history.isEmpty()) {
            area.setText("No transactions found.");
        } else {
            // Si hay transacciones, se recorren y se muestran en el área
            StringBuilder sb = new StringBuilder();
            for (Transaction t : history) {
                sb.append(t.toString()).append("\n"); // Cada transacción en una línea
            }
            area.setText(sb.toString()); // Se coloca el texto completo en el área
        }

        // Botón para regresar al menú principal
        JButton backBtn = new JButton("Back");
        backBtn.setBounds(140, 220, 100, 30); // Posición y tamaño del botón
        add(backBtn); // Se añade a la ventana

        // Acción al hacer clic en el botón "Back"
        backBtn.addActionListener(e -> {
            new MainMenuScreen(accountNumber); // Abre el menú principal
            dispose(); // Cierra la ventana actual
        });

        setVisible(true); // Muestra la ventana
    }
}
