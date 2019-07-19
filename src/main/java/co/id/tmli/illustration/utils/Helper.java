package co.id.tmli.illustration.utils;

import co.id.tmli.illustration.model.IllustrationData;
import static co.id.tmli.illustration.utils.Constants.UNLIMITED;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.id.tmli.illustration.utils.Constants;
import co.id.tmli.illustration.utils.Constants.PaymentMode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import net.objecthunter.exp4j.ExpressionBuilder;
import static co.id.tmli.illustration.utils.Constants.ONE_HUNDRED;
import static co.id.tmli.illustration.utils.Constants.FALSE;
import static co.id.tmli.illustration.utils.Constants.ONE_MILLION;
import co.id.tmli.illustration.model.RuleValidation;
import static co.id.tmli.illustration.utils.Constants.MIN_PREMIUM_TERM_5_YEARS;
import static co.id.tmli.illustration.utils.Constants.MIN_PREMIUM_TERM_3_YEARS;
import static co.id.tmli.illustration.utils.Constants.MIN_PREMIUM_TERM_4_YEARS;
import static co.id.tmli.illustration.utils.Constants.RIDERGROUP_CI;
import static co.id.tmli.illustration.utils.Constants.RIDERGROUP_EMBEDDED;
import static co.id.tmli.illustration.utils.Constants.RIDERGROUP_PAYOR;
import static co.id.tmli.illustration.utils.Constants.TMLegacyVIP_B;
import static co.id.tmli.illustration.utils.Constants.TMPOMPlus_B;
import static co.id.tmli.illustration.utils.Constants.TMPOMPlus_C;
import static co.id.tmli.illustration.utils.Constants.TMWellBeing;
import java.math.BigDecimal;
import co.id.tmli.illustration.model.TMProduct;
import static co.id.tmli.illustration.utils.Constants.RIDERCODE_SySpouseWaiver;
import static co.id.tmli.illustration.utils.Constants.TMPOM_PA;
import java.io.ByteArrayOutputStream;
import java.util.ResourceBundle;
import static co.id.tmli.illustration.utils.Constants.RIDER_RATE_A;
import java.util.List;
import net.objecthunter.exp4j.function.Function;
import static co.id.tmli.illustration.utils.MoneyFormatter.format;
import static co.id.tmli.illustration.utils.Constants.COR_TYPE_A;
import static co.id.tmli.illustration.utils.Constants.COR_TYPE_B;
import static co.id.tmli.illustration.utils.Constants.COR_TYPE_C;
import static co.id.tmli.illustration.utils.Constants.COR_TYPE_D;
import static co.id.tmli.illustration.utils.Constants.TMOptimaHealthHospitalSurgery;
import co.id.tmli.illustration.model.PackageProduct;
import co.id.tmli.illustration.model.TimeInterval;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    
    private static boolean productPackage;

    public static int getCurrIndex(CurrencyEnum curr) {
        switch (curr) {
            case IDR:
                return 0;
            case USD:
                return 1;
            default:
                return 0;
        }
    }

    public static Double geetB(CurrencyEnum curr) {
        switch (curr) {
            case IDR:
                return ONE_MILLION;
            case USD:
                return ONE_HUNDRED;
            default:
                return ONE_MILLION;
        }
    }

//    public static String getGender(boolean isMale) {
//        return isMale ? getCurrentBundle().getString("male") : getCurrentBundle().getString("female");
//    }

    public static int getPremiumTermTMLEdu(int insuredAge) {
        return Math.max(12 - insuredAge, 5);
    }

    private static float getAgeInYearAsFloat(LocalDate a) {
        LocalDate now = LocalDate.now();
        int numOfDays = diff(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), a.getYear(), a.getMonthValue(), a.getDayOfMonth());
        return numOfDays / 365.25f;
    }

    public static int getAgeInYear(LocalDate a) {
        return (int) Math.round(getAgeInYearAsFloat(a));
    }
    
    public static int getAgeInYear2(LocalDate a) {
        return (int) getAgeInYearAsFloat(a);
    }

    private static int diff(int y1, int m1, int d1, int y2, int m2, int d2) {
        return julianDay(y1, m1, d1) - julianDay(y2, m2, d2);
    }

    private static int julianDay(int yy, int mm, int dd) {
        int a = (14 - mm) / 12;
        int y = yy + 4800 - a;
        int m = mm + 12 * a - 3;
        int jdn = dd + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045;
        return jdn;
    }

    public static long getL(LocalDate date) {
        return ChronoUnit.MONTHS.between(date, LocalDate.now());
    }

//    public static boolean validateSumAssured(TextCurrencyField tfSumInsured, Integer insuredAge, String minSumAssuredExp, String maxSumAssuredExp, Map<String, Double> variables) {
//        Double sumInsured = tfSumInsured.getDoubleValue();
//        if (minSumAssuredExp != null) {
//            Double minSumAssured = null;
//            try {
//                minSumAssured = Double.parseDouble(minSumAssuredExp);
//            } catch (NumberFormatException nfe) {
//            }
//
//            if (minSumAssured == null) {
//                minSumAssured = evaluate(minSumAssuredExp, variables);
//                if (sumInsured < minSumAssured) {
//                    Dialogs.showValidationError(getCurrentBundle().getString("ytyuijkcsdhyfuaijakmfdsfkjksdjfkjsdfjsdf") + format(minSumAssured, tfSumInsured.getCurrency()));
//                    tfSumInsured.requestFocus();
//                    return false;
//                }
//            } else if (sumInsured < minSumAssured) {
//                Dialogs.showValidationError(getCurrentBundle().getString("ytyuijkcsdhyfuaijakmfdsfkjksdjfkjsdfjsdf") + format(minSumAssured, tfSumInsured.getCurrency()));
//                tfSumInsured.requestFocus();
//                return false;
//            }
//        }
//
//        if (maxSumAssuredExp != null) {
//            if (maxSumAssuredExp.equals(UNLIMITED)) {
//                return true;
//            }
//
//            Double maxSumAssured = null;
//            try {
//                maxSumAssured = Double.parseDouble(maxSumAssuredExp);
//            } catch (NumberFormatException nfe) {
//            }
//
//            if (maxSumAssured == null) {
//                if (insuredAge <= 16 && sumInsured > (FALSE ? 3_000_000d : 3_000_000_000d)) {
//                    Dialogs.showValidationError(getCurrentBundle().getString("tyuiasdmaskjfaysudikasmkdasidoaskdas"));
//                    tfSumInsured.requestFocus();
//                    return false;
//                }
//            } else if (sumInsured > maxSumAssured) {
//                Dialogs.showValidationError(getCurrentBundle().getString("tyujnkasytdtyuijmsakdfjasudmfvdjufisads") + format(maxSumAssured, tfSumInsured.getCurrency()));
//                tfSumInsured.requestFocus();
//                return false;
//            }
//        }
//        return true;
//    }

//    public static boolean validateRegularPremium(RuleValidation r, PaymentMode p, TextCurrencyField tfRegPremium) {
//        Double regPrem = tfRegPremium.getDoubleValue();
//        CurrencyEnum curr = tfRegPremium.getCurrency();
//
//        if (r.getMinRegPremiAnnual(curr) != null) {
//            switch (p) {
//                case Year:
//                    if (regPrem < r.getMinRegPremiAnnual(curr)) {
//                        Dialogs.showValidationError(getCurrentBundle().getString("ujkmkmdasjdiaskfmdskjcuvixjvkdsuiaksmdaksjdaksd")
//                                + format(r.getMinRegPremiAnnual(curr), tfRegPremium.getCurrency()));
//                        tfRegPremium.requestFocus();
//                        return false;
//                    }
//                    break;
//                case Semester:
//                    if (regPrem < r.getMinRegPremiSemester(curr)) {
//                        Dialogs.showValidationError(getCurrentBundle().getString("ujkmkmdasjdiaskfmdskjcuvixjvkdsuiaksmdaksjdaksd")
//                                + format(r.getMinRegPremiSemester(curr), tfRegPremium.getCurrency()));
//                        tfRegPremium.requestFocus();
//                        return false;
//                    }
//                    break;
//                case Quarter:
//                    if (regPrem < r.getMinRegPremiTriwulan(curr)) {
//                        Dialogs.showValidationError(getCurrentBundle().getString("ujkmkmdasjdiaskfmdskjcuvixjvkdsuiaksmdaksjdaksd")
//                                + format(r.getMinRegPremiTriwulan(curr), tfRegPremium.getCurrency()));
//                        tfRegPremium.requestFocus();
//                        return false;
//                    }
//                    break;
//                case Month:
//                    if (regPrem < r.getMinRegPremiMonthly(curr)) {
//                        Dialogs.showValidationError(getCurrentBundle().getString("ujkmkmdasjdiaskfmdskjcuvixjvkdsuiaksmdaksjdaksd")
//                                + format(r.getMinRegPremiMonthly(curr), tfRegPremium.getCurrency()));
//                        tfRegPremium.requestFocus();
//                        return false;
//                    }
//                    break;
//            }
//        }
//
//        if (r.getMaxRegPremiAnnual(curr) != null && r.getMaxRegPremiAnnual(curr) != Double.MAX_VALUE) {
//            switch (p) {
//                case Year:
//                    if (regPrem > r.getMaxRegPremiAnnual(curr)) {
//                        Dialogs.showValidationError(getCurrentBundle().getString("tydjansdkuytdaysfuijnsdmkfusifkdmflisdoksdmmfds")
//                                + format(r.getMaxRegPremiAnnual(curr), tfRegPremium.getCurrency()));
//                        tfRegPremium.requestFocus();
//                        return false;
//                    }
//                    break;
//                case Semester:
//                    if (regPrem > r.getMaxRegPremiSemester(curr)) {
//                        Dialogs.showValidationError(getCurrentBundle().getString("tydjansdkuytdaysfuijnsdmkfusifkdmflisdoksdmmfds")
//                                + format(r.getMaxRegPremiSemester(curr), tfRegPremium.getCurrency()));
//                        tfRegPremium.requestFocus();
//                        return false;
//                    }
//                    break;
//                case Quarter:
//                    if (regPrem > r.getMaxRegPremiTriwulan(curr)) {
//                        Dialogs.showValidationError(getCurrentBundle().getString("tydjansdkuytdaysfuijnsdmkfusifkdmflisdoksdmmfds")
//                                + format(r.getMaxRegPremiTriwulan(curr), tfRegPremium.getCurrency()));
//                        tfRegPremium.requestFocus();
//                        return false;
//                    }
//                    break;
//                case Month:
//                    if (regPrem > r.getMaxRegPremiMonthly(curr)) {
//                        Dialogs.showValidationError(getCurrentBundle().getString("tydjansdkuytdaysfuijnsdmkfusifkdmflisdoksdmmfds")
//                                + format(r.getMaxRegPremiMonthly(curr), tfRegPremium.getCurrency()));
//                        tfRegPremium.requestFocus();
//                        return false;
//                    }
//                    break;
//            }
//        }
//        return true;
//    }

//    public static boolean validateTopupRegularPremium(RuleValidation ruleVal, PaymentMode pm, TextCurrencyField tfTopupRegPremi) {
//        Double regPrem = tfTopupRegPremi.getDoubleValue();
//        CurrencyEnum curr = tfTopupRegPremi.getCurrency();
//
//        if (regPrem == 0d) {
//            return true;
//        } else {
//            if (ruleVal.getMinRegTopupPremiAnnual(curr) != null) {
//                switch (pm) {
//                    case Year:
//                        if (regPrem < ruleVal.getMinRegTopupPremiAnnual(curr)) {
//                            Dialogs.showValidationError(getCurrentBundle().getString("ytuijkasjdiuaijdkasjdkasdasdasdasdsa")
//                                    + format(ruleVal.getMinRegTopupPremiAnnual(curr), tfTopupRegPremi.getCurrency()));
//                            tfTopupRegPremi.requestFocus();
//                            return false;
//                        }
//                        break;
//                    case Semester:
//                        if (regPrem < ruleVal.getMinRegTopupPremiSemester(curr)) {
//                            Dialogs.showValidationError(getCurrentBundle().getString("ytuijkasjdiuaijdkasjdkasdasdasdasdsa")
//                                    + format(ruleVal.getMinRegTopupPremiSemester(curr), tfTopupRegPremi.getCurrency()));
//                            tfTopupRegPremi.requestFocus();
//                            return false;
//                        }
//                        break;
//                    case Quarter:
//                        if (regPrem < ruleVal.getMinRegTopupPremiTriwulan(curr)) {
//                            Dialogs.showValidationError(getCurrentBundle().getString("ytuijkasjdiuaijdkasjdkasdasdasdasdsa")
//                                    + format(ruleVal.getMinRegTopupPremiTriwulan(curr), tfTopupRegPremi.getCurrency()));
//                            tfTopupRegPremi.requestFocus();
//                            return false;
//                        }
//                        break;
//                    case Month:
//                        if (regPrem < ruleVal.getMinRegTopupPremiMonthly(curr)) {
//                            Dialogs.showValidationError(getCurrentBundle().getString("ytuijkasjdiuaijdkasjdkasdasdasdasdsa")
//                                    + format(ruleVal.getMinRegTopupPremiMonthly(curr), tfTopupRegPremi.getCurrency()));
//                            tfTopupRegPremi.requestFocus();
//                            return false;
//                        }
//                        break;
//                }
//            }
//        }
//        return true;
//    }

//    public static boolean sesas(RuleValidation r, PaymentMode p,
//            TextCurrencyField sds, TextCurrencyField trd, Double s) {
//        Double gt = sds.getDoubleValue();
//        Double sd = trd.getDoubleValue();
//        CurrencyEnum curr = trd.getCurrency();
//
//        if (s <= (FALSE ? 250_000d : 250_000_000d)) {
//            if (sd == 0d) {
//                return true;
//            } else {
//                if (r.getMinRegTopupPremiAnnual(curr) != null) {
//                    switch (p) {
//                        case Year:
//                            if (sd < r.getMinRegTopupPremiAnnual(curr)) {
//                                Dialogs.showValidationError(getCurrentBundle().getString("ytuijkasjdiuaijdkasjdkasdasdasdasdsa")
//                                        + format(r.getMinRegTopupPremiAnnual(curr), trd.getCurrency()));
//                                trd.requestFocus();
//                                return false;
//                            }
//                            break;
//                        case Semester:
//                            if (sd < r.getMinRegTopupPremiSemester(curr)) {
//                                Dialogs.showValidationError(getCurrentBundle().getString("ytuijkasjdiuaijdkasjdkasdasdasdasdsa")
//                                        + format(r.getMinRegTopupPremiSemester(curr), trd.getCurrency()));
//                                trd.requestFocus();
//                                return false;
//                            }
//                            break;
//                        case Quarter:
//                            if (sd < r.getMinRegTopupPremiTriwulan(curr)) {
//                                Dialogs.showValidationError(getCurrentBundle().getString("ytuijkasjdiuaijdkasjdkasdasdasdasdsa")
//                                        + format(r.getMinRegTopupPremiTriwulan(curr), trd.getCurrency()));
//                                trd.requestFocus();
//                                return false;
//                            }
//                            break;
//                        case Month:
//                            if (sd < r.getMinRegTopupPremiMonthly(curr)) {
//                                Dialogs.showValidationError(getCurrentBundle().getString("ytuijkasjdiuaijdkasjdkasdasdasdasdsa")
//                                        + format(r.getMinRegTopupPremiMonthly(curr), trd.getCurrency()));
//                                trd.requestFocus();
//                                return false;
//                            }
//                            break;
//                    }
//                }
//            }
//        } else if ((FALSE ? 250_000d : 250_000_000d) < s && s <= (FALSE ? 500_000d : 500_000_000d)) {
//            Double saas = .15 * gt;
//            if (sd < saas) {
//                Dialogs.showValidationError(getCurrentBundle().getString("tyuiasjdkoasuidyiaofdkmslkdoiasuuifjaskmdaksdsad")
//                        + format(saas, trd.getCurrency()));
//                trd.requestFocus();
//                return false;
//            }
//        } else if (s > (FALSE ? 500_000d : 500_000_000d)) {
//            Double utkd = .3 * gt;
//            if (sd < utkd) {
//                Dialogs.showValidationError(getCurrentBundle().getString("adsisokdfjoasjdjodsodnfsaomsadssfsasdasd")
//                        + format(utkd, trd.getCurrency()));
//                trd.requestFocus();
//                return false;
//            }
//        }
//
//        return true;
//    }

    public static final Double FIVE_MILLIONS = 50_000_000d;
    public static final Double FIVE_THOUSANDS = 5_000d;

//    public static boolean validatePremiumTerm(int policyTerm, IllustrationData illustrationData, TextField tf_payment_premium_plan,
//            TextCurrencyField tf_regular_premium, PaymentMode paymentMode) {
//        return validatePremiumTerm(policyTerm, illustrationData, tf_payment_premium_plan, tf_regular_premium, paymentMode, MIN_PREMIUM_TERM_5_YEARS);
//    }

//    public static boolean validatePremiumTerm(int policyTerm, IllustrationData illustrationData, TextField tf_payment_premium_plan,
//            TextCurrencyField tf_regular_premium, PaymentMode paymentMode, int minPremiumTerm) {
//        int premiumTerm;
//        int maxPremiumTerm = policyTerm - illustrationData.getInsuredAge();
//        if (tf_payment_premium_plan.getText().trim().length() == 0) {
//            premiumTerm = maxPremiumTerm;
//            illustrationData.setPremiumPaymentPeriod(premiumTerm);
//        } else {
//            try {
//                premiumTerm = Integer.parseInt(tf_payment_premium_plan.getText().trim());
//            } catch (NumberFormatException nfe) {
//                Dialogs.showValidationError(getCurrentBundle().getString("etryasuidkasgpoidipsodaisasiffsadfds"));
//                tf_payment_premium_plan.requestFocus();
//                return false;
//            }
////            minPremiumTerm = FIVE;
//            double regPremium;
//            if (illustrationData.getProductCode().equals(Constants.LinkMIP) || illustrationData.getProductCode().equals(Constants.LinkMIPPlus)) {
//                //TODO : change this if else with controller mapping [START]
//                if("AGRIS".equals(MainApp.getInstance().getConfig().getVersionId())){
//                    minPremiumTerm = MIN_PREMIUM_TERM_3_YEARS;
//                } else {
//                    regPremium = tf_regular_premium.getCurrency() == CurrencyEnum.IDR ? FIVE_MILLIONS : FIVE_THOUSANDS;
//                    switch (paymentMode) {
//                        case Year:
//                            break;
//                        case Semester:
//                            regPremium = regPremium / 2;
//                            break;
//                        case Quarter:
//                            regPremium = regPremium / 4;
//                            break;
//                        case Month:
//                            regPremium = regPremium / 12;
//                            break;
//                        default:
//                            break;
//                    }
//                    minPremiumTerm = tf_regular_premium.getDoubleValue() >= regPremium ? MIN_PREMIUM_TERM_3_YEARS : MIN_PREMIUM_TERM_5_YEARS;
//                }   //TODO : change this if else with controller mapping [END]
//            } else if (illustrationData.getProductCode().equals(Constants.LinkIProCst) || illustrationData.getProductCode().equals(Constants.LinkIProStaff)) {
//                minPremiumTerm = MIN_PREMIUM_TERM_4_YEARS;
//            } else if (illustrationData.getProductCode().equals(Constants.GlobalSignatureLink)) {
//                minPremiumTerm = MIN_PREMIUM_TERM_3_YEARS;
//            }
//
//            if (premiumTerm < minPremiumTerm || premiumTerm > maxPremiumTerm) {
//                Dialogs.showValidationError("Rencana Masa Pembayaran Premi Min: " + minPremiumTerm + " Max: " + maxPremiumTerm);
//                tf_payment_premium_plan.requestFocus();
//                return false;
//            }
//
//            illustrationData.setPremiumPaymentPeriod(premiumTerm);
//        }
//
//        return true;
//    }

    public static ObservableList<Integer> asdsdsds(String a,
            int b, int c) {
        if (a == null || a.equalsIgnoreCase("V")) {
            return null;
        }
        ObservableList<Integer> d = FXCollections.observableArrayList();
        ObservableList<Integer> e = sdswas(a);
        for (Integer period : e) {
            if (b + period <= c) {
                d.add(period);
            }
        }
        return d;
    }

    public static String formatDate(LocalDate date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("in", "IN"));
        return date.format(format);
    }

    public static ObservableList<Integer> sdswas(String str) {
        if (str != null) {
            if (str.toUpperCase().trim().equalsIgnoreCase("V")) {
                return null;
            } else {
                ObservableList<Integer> list = FXCollections.observableArrayList();
                for (String year : str.split(",")) {
                    list.add(Integer.parseInt(year.trim()));
                }
                return list;
            }
        }
        return null;
    }

    public static ObservableList<String> parseCsvAsList(String str) {
        if (str != null) {
            if (str.toUpperCase().trim().equalsIgnoreCase("V")) {
                return null;
            } else {
                ObservableList<String> list = FXCollections.observableArrayList();
                for (String year : str.split(",")) {
                    list.add(year.trim());
                }
                return list;
            }
        }
        return null;
    }

    public static ObservableList<String> ijtkd(String str, CurrencyEnum currency) {
        if (str != null) {
            if (str.toUpperCase().trim().equalsIgnoreCase("V")) {
                return null;
            } else {
                ObservableList<String> list = FXCollections.observableArrayList();
                for (String year : str.split(",")) {
                    list.add(format(Double.parseDouble(year.trim()), currency));
                }
                return list;
            }
        }
        return null;
    }
    protected static final Logger log = LoggerFactory.getLogger(Helper.class);

    public static String getHtml(Class<?> clazz, String a) throws IOException {
        InputStream is = clazz.getClassLoader().getResourceAsStream("html/" + a);
        return new String(toByteArray(is), "UTF-8");
    }

    public static byte[] toByteArray(InputStream in) throws IOException {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream();) {
            byte[] buffer = new byte[16384];
            for (int len; (len = in.read(buffer)) != -1;) {
                os.write(buffer, 0, len);
            }
            os.flush();
            return os.toByteArray();
        }
    }

    public static String getImagePath(Class<?> clazz, String imageName) throws IOException {
        URL url = clazz.getClassLoader().getResource("/images/" + imageName);
        return url.getPath().replace("/", "\\\\").replace("%20", " ");
    }

    public static InputStream getImageStream(Class<?> clazz, String imageName) throws IOException {
        return clazz.getClassLoader().getResourceAsStream("images/" + imageName);
    }

    public static String getReportPath(Class<?> clazz, String reportName) throws IOException {
        URL url = clazz.getClassLoader().getResource("/reports/" + reportName);
        return url.getPath().replace("/", "\\\\").replace("%20", " ");
    }

    public static InputStream getReportStream(Class<?> clazz, String reportName) throws IOException {
        log.debug("[getReportStream]-{}", reportName);
        String name = "reports/" + reportName;
        InputStream i = clazz.getClassLoader().getResourceAsStream(name);
        if (i == null) {
            throw new IOException("Resource could not be found, '" + name + "'");
        }
        return i;
    }

    public static String generateIllustrationNumber() {
        String[] uuidlist = UUID.randomUUID().toString().toUpperCase().split("-");
        return "ILL-" + uuidlist[3].concat("-").concat(uuidlist[4]);
    }

    public static InputStream getConfigPath(Class<?> clazz, String config) throws IOException {
        return clazz.getClassLoader().getResourceAsStream("config/" + config);
    }

    public static Integer[] freee(String str) {
        if (str == null) {
            return new Integer[]{-1, -1};
        }
        if (str.toLowerCase().contains("year")) {
            return new Integer[]{Integer.parseInt(str.split("_")[0]), -1};
        } else if (str.toLowerCase().contains("day")) {
            return new Integer[]{-1, Integer.parseInt(str.split("_")[0])};
        } else {
            return new Integer[]{-1, -1};
        }
    }

    public static Double parseMaxSumInsuredExpression(String expression, LocalDate insuredBirtdate) {
        if (expression == null) {
            return null;
        }
        switch (expression) {
            case UNLIMITED:
                return Double.MAX_VALUE;
            case "up_A":
                return (getAgeInYear(insuredBirtdate) <= 16) ? (FALSE ? 3_000_000d : 3_000_000_000d) : Double.MAX_VALUE;
            default:
                return Double.parseDouble(expression);
        }
    }

    public static Double parseSumInsuredExpression(String expression) {
        if (expression == null) {
            return null;
        }
        return (UNLIMITED.equals(expression) ? Double.MAX_VALUE : Double.parseDouble(expression));
    }

    public static Double calc(String a, Map<String, Double> b, Double c, boolean f) {
        Double expression1value = evaluate(a, b);
        return (f) ? Math.min(expression1value, c) : Math.max(expression1value, c);
    }

    public static double evaluate(String expression, Map<String, Double> variables) {
        ExpressionBuilder eb = new ExpressionBuilder(expression)
                .variables(variables.keySet());
        if (expression.contains("max")) {
            eb.function(new Function("max", 2) {
                @Override
                public double apply(double... values) {
                    return values[0] < values[1] ? values[1] : values[0];
                }
            });
        }
        return eb.build().setVariables(variables).evaluate();
    }

    public static double getNotNull(Double a) {
        return a != null ? a : 0d;
    }

    public static double getTyjg(String a, Double b) {
        //double c = 0;
        //double d = Math.max(FALSE ? 250_000d : 250_000_000d, 3 * b);
        
//        if (a.equalsIgnoreCase(Constants.PA_A)) {
//            c = Math.min(FALSE ? 2_000_000d : 2_000_000_000d, d);
//        } else if (a.equalsIgnoreCase(Constants.PA_AB)) {
//            c = Math.min(FALSE ? 500_000d : 500_000_000d, d);
//        } else if (a.equalsIgnoreCase(Constants.RIDERCODE_CI55)) {
//            c = Math.min(b, FALSE ? 2_000_000d : 2_000_000_000d);
//        } else if (a.equalsIgnoreCase(Constants.RIDERCODE_CI_EarlyCare)) {
//            c = Math.min(b, FALSE ? 3_000_000d : 3_000_000_000d);
//        }
//        return c;
        
        double c = 0;
        if (a.equalsIgnoreCase(Constants.PA_A)) {
            c = Math.min(FALSE ? 2_000_000d : 2_000_000_000d, 3 * b);
        } else if (a.equalsIgnoreCase(Constants.PA_AB)) {
            c = Math.min(FALSE ? 500_000d : 500_000_000d, 3 * b);
        } else if (a.equalsIgnoreCase(Constants.RIDERCODE_CI55)) {
            c = Math.min(b, FALSE ? 2_000_000d : 2_000_000_000d);
        } else if (a.equalsIgnoreCase(Constants.RIDERCODE_CI_EarlyCare)) {
            c = Math.min(b, FALSE ? 3_000_000d : 3_000_000_000d);
        }
        return c;
    }

    /*public static boolean validateRiders(List<RiderValue> list, double b, CurrencyEnum curr) {
        for (RiderValue sds : list) {
            if (sds.getMinBasicSA() != null && b < sds.getMinBasicSA()) {
                Dialogs.showValidationError(getCurrentBundle().getString("taydsuikafesfadsusjmqwkasivujsakiajs") + sds.getDesc() + " adalah " + format(sds.getMinBasicSA(), curr));
                return false;
            }
            if (sds.getMaxSumInsured() != null && b > sds.getMaxSumInsured()) {
                Dialogs.showValidationError(getCurrentBundle().getString("ujikmkijnmkjidaskmdasdmkasdnasmkdasmdasdmasd") + sds.getDesc() + " adalah " + format(sds.getMaxSumInsured(), curr));
                return false;
            }
            if (sds.getRiderSA().equalsIgnoreCase("true")
                    && sds.getIllustrationOutput().equalsIgnoreCase(Constants.INPUT_RIDER_SA)) {
                double upRiderMin = Helper.getNotNull(sds.getMinSA());
                double upRiderMax = Helper.getTyjg(sds.getColN(), b);
                if (sds.getValue() < upRiderMin || sds.getValue() > upRiderMax) {
                    Dialogs.showValidationError("UP untuk rider " + sds.getDesc() + " : Min " + MoneyFormatter.format(upRiderMin, curr) + ", Max " + MoneyFormatter.format(upRiderMax, curr));
                    return false;
                }
            }
        }
        return true;
    }*/
    
//    public static boolean validateRiders(List<RiderValue> list, double sumInsured, CurrencyEnum curr) {
//        return Helper.validateRiders(list, sumInsured, curr, null);
//    }
    
//    public static boolean validateRiders(List<RiderValue> list, double sumInsured, CurrencyEnum curr, PackageProduct packageProduct){
//        for (RiderValue riderValue : list) {
//            if (riderValue.getMinBasicSA() != null && sumInsured < riderValue.getMinBasicSA()) {
//                Dialogs.showValidationError(getCurrentBundle().getString("taydsuikafesfadsusjmqwkasivujsakiajs") + riderValue.getDesc() + " adalah " + format(riderValue.getMinBasicSA(), curr));
//                return false;
//            }
//            if (riderValue.getMaxSumInsured() != null && sumInsured > riderValue.getMaxSumInsured()) {
//                Dialogs.showValidationError(getCurrentBundle().getString("ujikmkijnmkjidaskmdasdmkasdnasmkdasmdasdmasd") + riderValue.getDesc() + " adalah " + format(riderValue.getMaxSumInsured(), curr));
//                return false;
//            }
//            if (riderValue.getRiderSA().equalsIgnoreCase("true")
//                    && riderValue.getIllustrationOutput().equalsIgnoreCase(Constants.INPUT_RIDER_SA)) {
//                double upRiderMin = 0;
//                double upRiderMax = 0;
//                if(packageProduct != null){
//                    String roles = packageProduct.getRiderMandatoryMap().get(riderValue.getRiderCode());
//                    ProductPackageRiderRoles riderRoles = new Gson().fromJson(roles, ProductPackageRiderRoles.class);
//                    riderRoles.calculate(new BigDecimal(Double.toString(sumInsured)), riderValue);
//                    upRiderMin = riderRoles.getMinimal().doubleValue();
//                    upRiderMax = riderRoles.getMaximal().doubleValue();
//                }else{
//                    //TODO 
//                    upRiderMin = Helper.getNotNull(riderValue.getMinSA());
//                    upRiderMax = Helper.getTyjg(riderValue.getColN(), sumInsured);
//                }
//                if (riderValue.getValue() < upRiderMin || riderValue.getValue() > upRiderMax) {
//                    Dialogs.showValidationError("UP untuk rider " + riderValue.getDesc() + " : Min " + MoneyFormatter.format(upRiderMin, curr) + ", Max " + MoneyFormatter.format(upRiderMax, curr));
//                    return false;
//                }
//            }
//            //check premi & rider
//            
//        }
//        return true;
//    }

//    public static BigDecimal calcSumAtRiskPayor(IllustrationData d) {
//        if (d.getRiders() != null) {
//            for (RiderValue rv : d.getRiders()) {
//                if (RIDERGROUP_PAYOR.equals(rv.getGroupCode())
//                        || RIDERCODE_SySpouseWaiver.equals(rv.getRiderCode())) {
//                    double atp = getAnnualizedPremium(d.getProductCode(), d.getTotalPremium(), d.getPaymentMode());
//                    int rpt = calcRiderPolicyTerm(d, rv.getRiderCode());
//                    // sumAtRisk = (0.5 * atp * rpt);
//                    return new BigDecimal(0.5 * atp * rpt);
//                }
//            }
//        }
//        // return null if there is NO rider Payor
//        return null;
//    }

//    public static BigDecimal calcSumAtRiskInsured(IllustrationData d) {
//        BigDecimal sumAtRisk = calcSumAtRiskBasic(d);
//        if (d.getRiders() != null) {
//            for (RiderValue rv : d.getRiders()) {
//                switch (rv.getGroupCode()) {
//                    case RIDERGROUP_CI:
//                        BigDecimal rsa = new BigDecimal(rv.getValue());
//                        sumAtRisk = sumAtRisk.add(rsa);
//                        break;
//                }
//            }
//        }
//        return sumAtRisk;
//    }

    public static BigDecimal calcSumAtRiskBasic(IllustrationData d) {
        BigDecimal sumAtRisk;
        switch (d.getProductCode()) {
            case TMPOMPlus_B:
            case TMPOMPlus_C:
            case TMWellBeing:
            case TMLegacyVIP_B:
            case TMPOM_PA:
            case TMOptimaHealthHospitalSurgery:
                sumAtRisk = BigDecimal.ZERO;
                break;
            default:
                sumAtRisk = new BigDecimal(d.getSumInsured());
        }
        return sumAtRisk;
    }

//    public static int calcRiderPolicyTerm(IllustrationData illustrationData, String riderCode) {
//        TMProduct product = IllustrationService.getSingleton().getTMProduct(illustrationData.getProductCode());
//        int insuredAge = illustrationData.getInsuredAge();
//        int holderAge = illustrationData.getHolderAge();
//        Integer spouseAge = illustrationData.getSpouseAge();
//
//        int policyTerm = product.getRuleValidation().getMaxPolicyTerm() - insuredAge;
//        for (RiderValue rider : illustrationData.getRiders()) {
//            if (rider.getRiderCode().equals(riderCode)) {
//                if (RIDERGROUP_EMBEDDED.equalsIgnoreCase(rider.getGroupCode())) {
//                    continue;
//                }
//                Integer riderPolicyTerm = null;
//                if (rider.getRateType().equals(RIDER_RATE_A)) {
//                    switch (rider.getCorType()) {
//                        case COR_TYPE_A:
//                        case COR_TYPE_C:
//                            riderPolicyTerm = rider.getCoveredAges() - insuredAge;
//                            break;
//                        case COR_TYPE_B:
//                            riderPolicyTerm = rider.getCoveredAges() - holderAge;
//                            break;
//                        case COR_TYPE_D:
//                            riderPolicyTerm = rider.getCoveredAges() - spouseAge;
//                            break;
//                    }
//                } else if (rider.getRateType().contains("B_")) {
//                    riderPolicyTerm = Integer.parseInt(rider.getRateType().split("_")[1]);
//                }
//                if (riderPolicyTerm == null) {
//                    throw new NullPointerException("riderPolicyTerm is null for rider " + rider.getRiderCode());
//                }
//                return Math.min(policyTerm, riderPolicyTerm);
//            }
//        }
//        throw new NullPointerException("riderPolicyTerm is null for rider " + riderCode);
//    }

//    public static ResourceBundle getCurrentBundle() {
//        return MainApp.getInstance().getResourceBundle();
//    }

    public static double roundUpToThousand(double n) {
        double m = n % 1000;
        return m > 0 ? 1000 - m + n : n;
    }
    
//    public static String getRiderMaxHolderEntryAge(RiderValue rider, double annualizedPremium, boolean isBeforeFirstFeb){
//        String maxHolderEntryAge = rider.getMaxHolderEntryAge();
//        return Helper.getRiderMaxEntryAge(maxHolderEntryAge, rider, annualizedPremium, isBeforeFirstFeb);
//    }
    
//    public static String getRiderMaxInsuredEntryAge(RiderValue rider, double annualizedPremium, boolean isBeforeFirstFeb){
//        String maxInsuredEntryAge = rider.getMaxInsuredEntryAge();
//        return Helper.getRiderMaxEntryAge(maxInsuredEntryAge, rider, annualizedPremium, isBeforeFirstFeb);
//    }
    
//    public static String getRiderMaxEntryAge(String maxEntryAge, RiderValue rider, double annualizedPremium, boolean isBeforeFirstFeb){
//        if(maxEntryAge != null){
//            switch (rider.getGroupCode()) {                
//                case Constants.RIDERGROUP_HSR:
//                case Constants.RIDERGROUP_HSR_NEW:
//                case Constants.RIDERGROUP_ADVMED:
//                    if(isBeforeFirstFeb){
//                        if(annualizedPremium >= Constants.ONE_HUNDRED_MILLION){
//                            maxEntryAge = Constants.YEARS_65;
//                        }
//                    }else{
//                        if(annualizedPremium >= Constants.TWO_HUNDRED_MILLION){
//                            maxEntryAge = Constants.YEARS_65;
//                        }
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//        return maxEntryAge;
//    }
    
    
//    public static boolean checkRiderAge(String entryAge, Integer actualAge, LocalDate birthDate, int riderAgeValidationMode, 
//            String validationMessage, boolean isWithSpec){
//        boolean isNotValid = false;
//        if(entryAge != null){
//            TimeInterval spec = new TimeInterval(entryAge);
//            TimeInterval input = entryAge.contains(Constants.AgeUnit.years.name())
//                        ? new TimeInterval(actualAge, Constants.AgeUnit.years)
//                        : TimeInterval.countIntervalToNow(birthDate, spec.getTimeUnit());
//            
//            if(isWithSpec) validationMessage = validationMessage + " adalah " + spec.toString();
//            switch (riderAgeValidationMode) {
//                case Constants.RiderAgeValidationMode.IS_LESS_THAN:
//                    if (spec.isLessThan(input)) isNotValid = Helper.showRiderAgeValidation(validationMessage);
//                    break;
//                case Constants.RiderAgeValidationMode.IS_NOT_LESS_THAN:
//                    if (!spec.isLessThan(input)) isNotValid = Helper.showRiderAgeValidation(validationMessage);
//                    break;
//                case Constants.RiderAgeValidationMode.IS_LESS_OR_EQUALL_THAN:
//                    if(spec.isLessOrEqualsThan(input)) isNotValid = Helper.showRiderAgeValidation(validationMessage);
//                    break;
//                case Constants.RiderAgeValidationMode.IS_NOT_LESS_OR_EQUALL_THAN:
//                    if(!spec.isLessOrEqualsThan(input)) isNotValid = Helper.showRiderAgeValidation(validationMessage);
//                    break;
//                default:
//                    break;
//            }
//        }
//        
//        return isNotValid;
//        
//    }
        
//    private static boolean showRiderAgeValidation(String validationMessage){
//        Dialogs.showValidationError(validationMessage);
//        return true;
//    }
    
//    public static boolean validateInsuredAndHolderRiderAge(Double annualizedPremium, RiderValue rider,
//            String minInsuredEntryAge, String maxInsuredEntryAge, Integer insuredAge, LocalDate insuredBirthDate, 
//            String minHolderEntryAge, String maxHolderEntryAge, Integer holderAge, LocalDate  holderBirthDate,
//            boolean isBeforeFirstFeb
//            ){
//        
//        //1. check rider minimum Insured Entry Age
//        boolean minInsuredEntryAgeValidation = Helper.checkRiderAge(minInsuredEntryAge, insuredAge, 
//                    insuredBirthDate, Constants.RiderAgeValidationMode.IS_NOT_LESS_OR_EQUALL_THAN, 
//                    getCurrentBundle().getString("rider.minimal.insured.age") + rider.getDesc(), true);
//        if(minInsuredEntryAgeValidation) 
//            return true;
//        
//        //2. check rider maximum Insured Entry Age
//        boolean isMaxInsuredEntryWithSpec = true;
//        String errorMessageMaxInsured = getCurrentBundle().getString("rider.maximal.insured.age") + rider.getDesc();
//        switch (rider.getGroupCode()) {
//            case Constants.RIDERGROUP_HSR:
//            case Constants.RIDERGROUP_HSR_NEW:
//            case Constants.RIDERGROUP_ADVMED:
//                if(insuredAge >= 61 && insuredAge <= 65){
//                    String nominalMaxInsured = isBeforeFirstFeb ? "Rp 100.000.000" : "Rp 200.000.000";
//                    errorMessageMaxInsured = getCurrentBundle().getString("rider.minimal.premi") + rider.getDesc() + " adalah " + nominalMaxInsured;
//                    isMaxInsuredEntryWithSpec = false;
//                }
//                break;
//            default:
//                break;
//        }
//        boolean maxInsuredEntryAgeValidation = Helper.checkRiderAge(maxInsuredEntryAge, insuredAge, 
//                    insuredBirthDate, Constants.RiderAgeValidationMode.IS_LESS_THAN, errorMessageMaxInsured, isMaxInsuredEntryWithSpec);
//        if(maxInsuredEntryAgeValidation)
//            return true;
//        
//        //3. check rider minimum Holder Entry Age
//        boolean minHolderEntryAgeValidation = Helper.checkRiderAge(minHolderEntryAge, holderAge, 
//                    holderBirthDate, Constants.RiderAgeValidationMode.IS_NOT_LESS_OR_EQUALL_THAN, 
//                    getCurrentBundle().getString("rider.minimal.holder.age") + rider.getDesc(), true);
//        if(minHolderEntryAgeValidation)
//            return true;
//        
//        //4. check rider maximum Holder Entry Age
//        boolean isMaxholderEntryWithSpec = true;
//        String errorMessageMaxHolder = getCurrentBundle().getString("rider.maximal.holder.age") + rider.getDesc();
//        switch (rider.getGroupCode()) {
//            case Constants.RIDERGROUP_HSR:
//            case Constants.RIDERGROUP_HSR_NEW:
//            case Constants.RIDERGROUP_ADVMED:
//                if(holderAge >= 61 && holderAge <= 65){
//                    String nominalMaxHolder = isBeforeFirstFeb ? "Rp 100.000.000" : "Rp 200.000.000";
//                    errorMessageMaxHolder = getCurrentBundle().getString("rider.minimal.premi") + rider.getDesc() + " adalah " + nominalMaxHolder;
//                    isMaxholderEntryWithSpec = false;
//                }
//                break;
//            default:
//                break;
//        }
//        boolean maxHolderEntryAgeValidation = Helper.checkRiderAge(maxHolderEntryAge, holderAge, 
//                    holderBirthDate, Constants.RiderAgeValidationMode.IS_LESS_THAN, errorMessageMaxHolder, isMaxholderEntryWithSpec);
//        if(maxHolderEntryAgeValidation)
//            return true;
//        
//        return false;
//    }
    
//    public static boolean validateInsuredAndHolderRiderAge(Double annualizedPremium, RiderValue rider, 
//            Integer insuredAge, LocalDate insuredBirthDate, Integer holderAge, LocalDate  holderBirthDate){
//        
//        //spesial case is before 1 feb 2018 date
//        boolean isBeforeFirstFeb = false;
//        try {
//            Date now = new Date(System.currentTimeMillis());
//            Date febFirst = new SimpleDateFormat("dd/MM/yyyy").parse("01/02/2018");
//            isBeforeFirstFeb = now.compareTo(febFirst) < 0;
//        } catch (ParseException e) {
//            Dialogs.showValidationError(e.getMessage());
//        }
//        //get minimum Insured Entry Age
//        String minInsuredEntryAge = rider.getMinInsuredEntryAge();
//            
//        //get minumum Holder Entry Age
//        String minHolderEntryAge = rider.getMinHolderEntryAge();
//            
//        //get maximum Insured Entry Age
////            String maxInsuredEntryAge = rider.getMaxInsuredEntryAge();
//        String maxInsuredEntryAge = Helper.getRiderMaxInsuredEntryAge(rider, annualizedPremium, isBeforeFirstFeb);
//            
//        //get maximum Holder Entry Age
////            String maxHolderEntryAge = rider.getMaxHolderEntryAge();
//        String maxHolderEntryAge = Helper.getRiderMaxHolderEntryAge(rider, annualizedPremium, isBeforeFirstFeb);
//        
//        //check rider minimum and maximum insured holder entry age
//        return Helper.validateInsuredAndHolderRiderAge(annualizedPremium, rider,
//                minInsuredEntryAge, maxInsuredEntryAge, insuredAge, insuredBirthDate,
//                minHolderEntryAge, maxHolderEntryAge, holderAge, holderBirthDate,
//                isBeforeFirstFeb);
//    }
    
    public static Double roundUp(double number, int multiple) {
        int result = multiple;
        if (number % multiple == 0) {
            return number;
        }

        if (number % multiple != 0) {
            int division = (int) ((number / multiple) + 1);
            result = division * multiple;
        }
        return (double) result;
    }

    /**
     * @return the productPackage
     */
    public static boolean isProductPackage() {
        return productPackage;
    }

    /**
     * @param productPackage the productPackage to set
     */
    public static void setProductPackage(boolean productPackage) {
        Helper.productPackage = productPackage;
    }
    
    public static String getPrefixHsrCode(String s) {
        Pattern regex = Pattern.compile("^[^\\]IW]*");
        Matcher regexMatcher = regex.matcher(s);
        if (regexMatcher.find()) {
            return regexMatcher.group();            
        }
        return null;
    }

}
