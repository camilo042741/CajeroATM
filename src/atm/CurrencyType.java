package atm; // Esta clase forma parte del paquete 'atm'.

/**
 * Enum que representa los tipos de monedas que maneja el cajero.
 * Cada moneda tiene una tasa de conversión con respecto al dólar (USD).
 */
public enum CurrencyType {
    USD(1.0),     // Dólar estadounidense (tasa base)
    EUR(0.93),    // Euro (1 USD = 0.93 EUR)
    COP(4100.0);  // Peso colombiano (1 USD = 4100 COP)

    // Tasa de conversión con respecto al dólar estadounidense
    private final double rateToUSD;

    /**
     * Constructor que asigna la tasa de conversión correspondiente a cada tipo de moneda.
     * @param rateToUSD Cuánto vale 1 USD en esta moneda.
     */
    CurrencyType(double rateToUSD) {
        this.rateToUSD = rateToUSD;
    }

    /**
     * Convierte un monto en dólares a esta moneda.
     * @param amount Monto en dólares (USD).
     * @return Monto convertido a la moneda correspondiente.
     */
    public double convertFromUSD(double amount) {
        return amount * rateToUSD;
    }

    /**
     * Convierte un monto de esta moneda a dólares.
     * @param amount Monto en la moneda actual.
     * @return Monto convertido a dólares.
     */
    public double convertToUSD(double amount) {
        return amount / rateToUSD;
    }

    /**
     * Devuelve la tasa de conversión de esta moneda respecto al USD.
     * @return Valor de 1 USD en esta moneda.
     */
    public double getRate() {
        return rateToUSD;
    }

    /**
     * Devuelve el símbolo asociado a la moneda.
     * @return Símbolo de la moneda (por ejemplo, "€" para Euro).
     */
    public String getSymbol() {
        switch (this) {
            case USD: return "USD";
            case EUR: return "€";
            case COP: return "COP";
            default: return ""; // En caso de que no coincida con ninguna (caso de seguridad)
        }
    }
}
