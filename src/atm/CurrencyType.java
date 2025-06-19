package atm; // Esta clase forma parte del paquete 'atm', que organiza las clases del proyecto del cajero.

/**
 * Enum que representa los tipos de monedas que maneja el cajero.
 * Cada moneda tiene una tasa de conversión con respecto al dólar (USD).
 */
public enum CurrencyType {

    // Definición de las monedas disponibles y su tasa de conversión respecto al USD
    USD(1.0),     // Dólar estadounidense, tasa base (1 USD = 1 USD)
    EUR(0.93),    // Euro, 1 USD equivale a 0.93 EUR
    COP(4100.0);  // Peso colombiano, 1 USD equivale a 4100 COP

    // Variable que guarda la tasa de conversión para cada tipo de moneda
    private final double rateToUSD;

    /**
     * Constructor del enum que asigna la tasa de conversión para cada moneda.
     * @param rateToUSD Cuánto vale 1 dólar estadounidense en esta moneda.
     */
    CurrencyType(double rateToUSD) {
        this.rateToUSD = rateToUSD;
    }

    /**
     * Convierte un monto en dólares a la moneda correspondiente del enum.
     * @param amount Monto en dólares estadounidenses.
     * @return Monto convertido a esta moneda.
     */
    public double convertFromUSD(double amount) {
        return amount * rateToUSD; // Multiplica por la tasa para convertir desde USD
    }

    /**
     * Convierte un monto desde esta moneda a dólares.
     * @param amount Monto en la moneda actual.
     * @return Monto convertido a dólares estadounidenses.
     */
    public double convertToUSD(double amount) {
        return amount / rateToUSD; // Divide por la tasa para volver a USD
    }

    /**
     * Devuelve la tasa de conversión de esta moneda con respecto al USD.
     * @return Valor de 1 USD en esta moneda.
     */
    public double getRate() {
        return rateToUSD;
    }

    /**
     * Devuelve el símbolo representativo de cada moneda.
     * Esto se usa, por ejemplo, para mostrarlo en los saldos o transacciones.
     * @return Una cadena con el símbolo de la moneda.
     */
    public String getSymbol() {
        switch (this) {
            case USD: return "USD"; // Dólar
            case EUR: return "€";   // Euro
            case COP: return "COP"; // Peso colombiano
            default: return "";     // Valor por defecto (en caso de error, aunque no debería ocurrir)
        }
    }
}
