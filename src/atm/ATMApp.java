package atm; // Este archivo pertenece al paquete llamado 'atm', que organiza el código del proyecto.

/**
 * Clase principal que inicia la aplicación del cajero automático.
 */
public class ATMApp {

    /**
     * Método principal que se ejecuta al iniciar el programa.
     * Aquí se crea y muestra la pantalla de inicio de sesión.
     */
    public static void main(String[] args) {
        new LoginScreen(); // Se crea una nueva instancia de la pantalla de inicio de sesión.
        // Al crear el objeto, se muestra la interfaz gráfica al usuario.
    }
}
