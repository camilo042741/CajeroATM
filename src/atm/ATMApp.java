package atm; // Este archivo pertenece al paquete llamado 'atm', que organiza el código del proyecto.

/**
 * Clase principal que inicia la aplicación del cajero automático.
 * Esta clase contiene el método 'main', punto de entrada de cualquier programa en Java.
 */
public class ATMApp {

    /**
     * Método principal que se ejecuta al iniciar el programa.
     * Es el punto de inicio donde se lanza la interfaz gráfica del cajero.
     */
    public static void main(String[] args) {
        // Se crea una nueva instancia de LoginScreen, que muestra la ventana de inicio de sesión
        new LoginScreen();
        // En cuanto se instancia LoginScreen, su constructor se encarga de mostrar la interfaz.
    }
}
