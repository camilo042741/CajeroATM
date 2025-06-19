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
        // Establece el título de la ventana
        setTitle("ColCashFlow - Exit");

        // Define el tamaño de la ventana (ancho x alto)
        setSize(300, 150);

        // Define que al cerrar la ventana, se cierre toda la aplicación
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Centra la ventana en el centro de la pantalla
        setLocationRelativeTo(null);

        // Se desactiva el layout automático para ubicar elementos manualmente
        setLayout(null);

        // Crea una etiqueta con el mensaje de despedida al usuario
        JLabel label = new JLabel("Thank you for using our ATM!"); // Mensaje: "¡Gracias por usar nuestro cajero!"

        // Establece la posición y tamaño de la etiqueta (x, y, ancho, alto)
        label.setBounds(50, 40, 220, 30);

        // Agrega la etiqueta a la ventana
        add(label);

        // Hace visible la ventana al usuario
        setVisible(true);
    }
}
