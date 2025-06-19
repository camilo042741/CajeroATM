import atm.DBManager;
import atm.LoginScreen;

public class Main {
    public static void main(String[] args) {
        DBManager.createCuentasTable();
        DBManager.createTables(); // ✅ crea la tabla si no existe
        new LoginScreen();        // ✅ lanza la app
    }
}
