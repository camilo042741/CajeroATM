package atm; // Este archivo pertenece al paquete 'atm', que agrupa las clases relacionadas con el sistema del cajero.

/**
 * Clase que representa a un cliente del sistema bancario.
 * Contiene información básica como el nombre y el número de identificación (DNI).
 */
public class Cliente {

    // Atributo que almacena el nombre del cliente
    private String nombre;

    // Atributo que almacena el número de identificación del cliente (Documento Nacional de Identidad)
    private String dni;

    /**
     * Constructor que permite crear un nuevo objeto Cliente con nombre y DNI.
     * @param nombre Nombre completo del cliente
     * @param dni Número de identificación del cliente
     */
    public Cliente(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    // Método que devuelve el nombre del cliente
    public String getNombre() {
        return nombre;
    }

    // Método que devuelve el DNI del cliente
    public String getDni() {
        return dni;
    }

    // Método que permite modificar el nombre del cliente
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método que permite modificar el DNI del cliente
    public void setDni(String dni) {
        this.dni = dni;
    }
}
