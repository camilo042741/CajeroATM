package atm; // Esta clase forma parte del paquete 'atm'.

import javax.swing.*; // Importa las clases para crear interfaces gráficas.

/**
 * Pantalla final que se muestra cuando el usuario decide salir del cajero.
 */
public class ExitScreen extends JFrame {

    /**
     * Constructor que configura la ventana de despedida.
     */
    public ExitScreen() {
        // Título de la ventana
        setTitle("ColCashFlow - Exit");

        // Tamaño de la ventana
        setSize(300, 150);

        // Cierra completamente la aplicación al cerrar esta ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Centra la ventana en la pantalla
        setLocationRelativeTo(null);

        // No se usa un layout automático, se posicionan los elementos manualmente
        setLayout(null);

        // Etiqueta con el mensaje de despedida
        JLabel label = new JLabel("Thank you for using our ATM!"); // "¡Gracias por usar nuestro cajero!"
        label.setBounds(50, 40, 220, 30); // Posiciona la etiqueta dentro de la ventana
        add(label); // Agrega la etiqueta a la ventana

        // Muestra la ventana al usuario
        setVisible(true);
    }
}
