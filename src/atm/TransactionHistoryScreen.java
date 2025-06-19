package atm; // Este archivo pertenece al paquete 'atm'.

import javax.swing.*; // Importa componentes gráficos como botones, etiquetas y áreas de texto.
import java.util.List; // Importa la interfaz List para trabajar con listas de objetos.

/**
 * Pantalla que muestra el historial de transacciones realizadas por un usuario.
 */
public class TransactionHistoryScreen extends JFrame {

    /**
     * Constructor que recibe el número de cuenta para mostrar su historial.
     * @param accountNumber Número de cuenta del usuario.
     */
    public TransactionHistoryScreen(String accountNumber) {
        // Configuración de la ventana principal
        setTitle("ColCashFlow - Transaction History"); // Título de la ventana
        setSize(400, 350); // Tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cierra el programa al cerrar esta ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setLayout(null); // Posicionamiento manual de los componentes

        // Área de texto donde se mostrará el historial de transacciones
        JTextArea area = new JTextArea();
        area.setBounds(20, 60, 350, 180); // Posición y tamaño
        area.setEditable(false); // No editable por el usuario
        add(area); // Agrega el área a la ventana

        // Obtiene la información del cliente (nombre y DNI) desde la base de datos simulada
        Cliente cliente = FakeDatabase.getCliente(accountNumber);
        if (cliente != null) {
            // Muestra el nombre del titular de la cuenta
            JLabel nombreLabel = new JLabel("Titular: " + cliente.getNombre());
            nombreLabel.setBounds(20, 10, 200, 20);
            add(nombreLabel);

            // Muestra el DNI del titular
            JLabel dniLabel = new JLabel("DNI: " + cliente.getDni());
            dniLabel.setBounds(20, 30, 200, 20);
            add(dniLabel);
        }

        // Obtiene el historial de transacciones del usuario desde la base de datos simulada
        List<Transaction> history = FakeDatabase.getTransactionHistory(accountNumber);

        // Si no hay transacciones registradas, se muestra un mensaje
        if (history.isEmpty()) {
            area.setText("No transactions found.");
        } else {
            // Si hay transacciones, se formatea y se muestra cada una
            StringBuilder sb = new StringBuilder();
            for (Transaction t : history) {
                sb.append(t.toString()).append("\n"); // Cada transacción se agrega al texto
            }
            area.setText(sb.toString()); // Se muestra en el área de texto
        }

        // Botón para volver al menú principal
        JButton backBtn = new JButton("Back");
        backBtn.setBounds(140, 260, 100, 30);
        add(backBtn);

        // Acción del botón: vuelve al menú principal y cierra esta ventana
        backBtn.addActionListener(e -> {
            new MainMenuScreen(accountNumber); // Abre el menú principal
            dispose(); // Cierra esta pantalla
        });

        // Hace visible la ventana
        setVisible(true);
    }
}
