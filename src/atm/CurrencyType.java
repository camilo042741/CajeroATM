package atm;

public enum CurrencyType {
    USD(1.0),
    EUR(0.93),
    COP(4100.0);

    private final double rateToUSD;

    CurrencyType(double rateToUSD) {
        this.rateToUSD = rateToUSD;
    }

    public double convertFromUSD(double amount) {
        return amount * rateToUSD;
    }

    public double convertToUSD(double amount) {
        return amount / rateToUSD;
    }

    public double getRate() {
        return rateToUSD;
    }

    public String getSymbol() {
        switch (this) {
            case USD: return "USD";
            case EUR: return "€";
            case COP: return "COP";
            default: return "";
        }
    }
}

