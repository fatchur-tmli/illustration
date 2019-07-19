package co.id.tmli.illustration.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CurrencyEnum {
	
    IDR("Rp ", "Rupiah", "IDR", new Locale("in", "IN")), USD("$ ", "Dollar", "USD", Locale.US);

    @NonNull
    private final String symbol, name;
    @NonNull
    private final Currency currency;
    @NonNull
    private final Locale locale;

    private CurrencyEnum(String symbol, String name, String currencyCode, Locale locale) {    	
        this(symbol, name, Currency.getInstance(currencyCode), locale);
    }

    public DecimalFormat getFormatter(boolean withCurrencySymbol) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        if (withCurrencySymbol) {
            symbols.setCurrency(currency);
        } else {
            symbols.setCurrencySymbol("");
        }
        formatter.setDecimalFormatSymbols(symbols);
        formatter.setMinimumFractionDigits(0);
        formatter.setMaximumFractionDigits(0);
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        return formatter;
    }

    public String format(Number n, boolean withCurrencySymbol) {
        return getFormatter(withCurrencySymbol).format(n);
    }

    public String format(Number n) {
        return format(n, false);
    }

    public String formatWithCurrencySymbol(Number n) {
        return format(n, true);
    }

    @Override
    public String toString() {
        return getName();
    }

}
