package co.id.tmli.illustration.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

//@lombok.Getter
public class MoneyFormatter {

//    private final Double value;
//    private final CurrencyEnum currency;

//    public static MoneyFormatter of(double value, CurrencyEnum currency) {
//        return new MoneyFormatter(value, currency);
//    }

    public static Double parse(String scurrency, CurrencyEnum currency) {
        Locale locale = Locale.GERMAN;

        switch (currency) {
            case IDR:
                locale = Locale.GERMAN;
                break;
            case USD:
                locale = Locale.US;
                break;
            default:
                break;
        }

        DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

        switch (currency) {
            case IDR:
                symbols.setCurrencySymbol("Rp. ");
                break;
            case USD:
                symbols.setCurrencySymbol("$ ");
                break;
            default:
                symbols.setCurrencySymbol("Rp. ");
                break;
        }
        formatter.setDecimalFormatSymbols(symbols);
        formatter.setMinimumFractionDigits(0);
        formatter.setMaximumFractionDigits(0);
        formatter.setRoundingMode(RoundingMode.HALF_UP);

        try {
            return Double.parseDouble(formatter.parse(scurrency) + "");
        } catch (ParseException e) {
            return null;
        }
    }

//    public MoneyFormatter(Double value, CurrencyEnum currency) {
//        this.value = value;
//        this.currency = currency;
//    }

//    public static String formatMoney(Double money, CurrencyEnum currency) {
//        return currency.format(money);
//        Locale locale = null;
//        switch (currency) {
//            case IDR:
//                locale = Locale.GERMAN;
//                break;
//            case USD:
//                locale = Locale.US;
//                break;
//        }
//        DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);
//        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
//        symbols.setCurrencySymbol("");
//        formatter.setDecimalFormatSymbols(symbols);
//        formatter.setMinimumFractionDigits(0);
//        formatter.setMaximumFractionDigits(0);
//        formatter.setRoundingMode(RoundingMode.HALF_UP);
//        return formatter.format(money);
//    }
    public static String format(Double number, CurrencyEnum currency) {
//        return currency.formatWithCurrency(number);
        Locale locale = Locale.GERMAN;

        switch (currency) {
            case IDR:
                locale = Locale.GERMAN;
                break;
            case USD:
                locale = Locale.US;
                break;
        }
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        switch (currency) {
            case IDR:
                symbols.setCurrencySymbol("Rp. ");
                break;
            case USD:
                symbols.setCurrencySymbol("$ ");
                break;
            default:
                symbols.setCurrencySymbol("Rp. ");
                break;
        }
        formatter.setDecimalFormatSymbols(symbols);
        formatter.setMinimumFractionDigits(0);
        formatter.setMaximumFractionDigits(0);
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        return formatter.format(number);
    }

    public static void main(String[] args) {
        String s = format(123456.789, CurrencyEnum.IDR);
        System.out.println(s);
        Double d = parse(s, CurrencyEnum.IDR);
        System.out.println(d);
    }
}
