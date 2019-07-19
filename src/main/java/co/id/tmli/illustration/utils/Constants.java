/**
 * 
 */
package co.id.tmli.illustration.utils;

import co.id.tmli.illustration.model.LabelizedEnum;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
    
    public static final String DEFAULT_STYLE = "/styles/default.css";
    public static final String UNLIMITED = "~";
    public static final String MULTI_CURR_SEPARATOR = "_";
    public static final boolean FALSE = false;
    public static final Double ONE_MILLION = FALSE ? 1000d : 1000_000d;
    public static final Double ONE_HUNDRED = 100d;
    public static final Double ONE_HUNDRED_MILLION = Double.valueOf("100000000");
    public static final Double TWO_HUNDRED_MILLION = Double.valueOf("200000000");

    public static final String AGENT_NAME = "AGENT_NAME";
    public static final String AGENT_CODE = "AGENT_CODE";
    public static final String AGENT_BRANCH = "AGENT_BRANCH";

    public static final List<Integer> Y31_Y99 = Arrays.asList(
            31, 32, 33, 34, 36, 37, 38, 39,
            41, 42, 43, 44, 46, 47, 48, 49,
            51, 52, 53, 54, 56, 57, 58, 59,
            61, 62, 63, 64, 66, 67, 68, 69,
            71, 72, 73, 74, 76, 77, 78, 79,
            81, 82, 83, 84, 86, 87, 88, 89,
            91, 92, 93, 94, 96, 97, 98, 99
    );
    public static final int MIN_PREMIUM_TERM_5_YEARS = 5;
    public static final int MIN_PREMIUM_TERM_3_YEARS = 3;
    public static final int MIN_PREMIUM_TERM_4_YEARS = 4;
    public static final int MIN_PREMIUM_TERM_10_YEARS = 10;
            
    public static final List<String> ROOM_BOARD = Arrays.asList(
            "1000", "1500","3000","6000","10000","20000"
    );  
    
    public static final List<String> BEDDED = Arrays.asList(
            "Executive 1", "Executive 2", "Executive 3"
    );
    
    public static final String RB_ROOM_BOARD = "rb_room_board";
    public static final String RB_BEDDED = "rb_bedded";
    public static final String RB_COV_AREA_INDO = "rb_cov_area_indo";
    public static final String RB_COV_AREA_WORLD = "rb_cov_area_world";
    public static final String RB_BOOSTER_YA = "rb_booster_ya";
    public static final String RB_BOOSTER_NO = "rb_booster_no";
    public static final String RB_PRORATE = "rb_prorate";
    public static final String RB_DEDUCT_0 = "rb_deduct_0";
    public static final String RB_DEDUCT_75 = "rb_deduct_75";
    public static final String RB_COSHARING_0 = "rb_cosharing_0";
    public static final String RB_COSHARING_10 = "rb_cosharing_10";
    
    public static final String EXECUTIVE_1 = "Executive 1";
    public static final String EXECUTIVE_2 = "Executive 2";
    public static final String EXECUTIVE_3 = "Executive 3";
    public static final String EXECUTIVE_4 = "Executive 4";
    public static final String EXECUTIVE_5 = "Executive 5";
    public static final String EXECUTIVE_6 = "Executive 6";

    public static final Map<String, String> RIDER = Collections.unmodifiableMap(new HashMap(7) {
        {
            put("CI", "Critical Illness");
            put("PA", "Personal Accident");
            put("HSR", "Hospital and Surgery");
            put("HCP", "Hospital Cash Plan");
            put("Payor", "Payor");
            put("Waiver", "Waiver");
            put("AdvMed", "Advanced Medicare");
        }
    });

    public static final String SHEET_PACKAGE_PRODUCT = "PACKAGE PRODUCT";
    public static final String SHEET_PRODUCT = "PRODUCT";
    public static final String SHEET_PROD_VAL = "PROD Val";
    public static final String SHEET_TRAD = "TRAD";
    public static final String SHEET_REPORT = "REPORT";
    public static final String SHEET_FUND = "FUND";
    public static final String SHEET_RIDER = "RIDER";
    public static final String SHEET_FEATURES = "Features";
    public static final String SHEET_UNDERWRITING = "UNDERWRITING";
    public static final String SHEET_HSR_CODE = "HSR CODE";
    public static final String SHEET_HSR_RATE = "HSR RATE";
    public static final String SHEET_HSR_RATE_NONPRORATE = "HSR RATE NONPRORATE";

    public static final String VAL_MSG = "Val Msg";
    /**
     * Sheet REPORT, Col H
     */
    public static final String E = "E";
    /**
     * Sheet REPORT, Col I
     */
    public static final String F = "F";
    /**
     * Sheet REPORT, Col J
     */
    public static final String PRODUCT_NOTE = "ProductNote";
    /**
     * Sheet REPORT, Col K
     */
    public static final String PERNYATAAN = "Pernyataan";
    public static final String RIDERGROUP_EMBEDDED = "EMBEDDED";
    public static final String RIDERGROUP_HSR_OLD = "HSR_OLD";
    public static final String RIDERGROUP_HSR_NEW = "HSR_NEW";
    public static final String RIDERGROUP_CI = "CI";
    public static final String RIDERGROUP_PAYOR = "Payor";
    public static final String RIDERGROUP_WAIVER = "Waiver";
    public static final String RIDERGROUP_HSR = "HSR";
    public static final String RIDERGROUP_ADVMED = "AdvMed";
    public static final String RIDERCODE_CI55 = "CI55";
    public static final String RIDERCODE_CI_EarlyCare = "CI_EarlyCare";
    public static final String RIDERCODE_SySpouseWaiver = "SySpouseWaiver";
    public static final String HSR_ID200 = "HSR_ID200";
    public static final String HSR_IDPlat500 = "HSR_IDPlat500";
    public static final String HSR_IDPlat1000 = "HSR_IDPlat1000";
    public static final String HSR_IDPlat1500 = "HSR_IDPlat1500";
    public static final String HSR_IDPlatPlus500 = "HSR_IDPlatPlus500";
    public static final String HSR_IDPlatPlus1000 = "HSR_IDPlatPlus1000";
    public static final String HSR_IDPlatPlus1500 = "HSR_IDPlatPlus1500";
    public static final String HSR_200 = "HSR_200";
    public static final String HSR_500 = "HSR_500";
    public static final String HSR_1000 = "HSR_1000";
    public static final String HSR_1500 = "HSR_1500";
    public static final String HSR_3000 = "HSR_3000";
    public static final String HSR_6000 = "HSR_6000";
    public static final String HSR_10000 = "HSR_10000";
    public static final String HSR_20000 = "HSR_20000";
    public static final String HSR_EXC_PLAN_A = "HSR_IDExcPlanA";
    public static final String HSR_EXC_PLAN_B = "HSR_IDExcPlanB";
    public static final String HSR_EXC_PLAN_C = "HSR_IDExcPlanC";
    public static final String HSR_EXC_PLAN_D = "HSR_WExcPlanD";
    public static final String HSR_EXC_PLAN_E = "HSR_WExcPlanE";
    public static final String HSR_EXC_PLAN_F = "HSR_WExcPlanF";
    public static final String HSR_WPlat500 = "HSR_WPlat500";
    public static final String HSR_WPlat1000 = "HSR_WPlat1000";
    public static final String HSR_WPlat1500 = "HSR_WPlat1500";
    public static final String HSR_WPlatPlus500 = "HSR_WPlatPlus500";
    public static final String HSR_WPlatPlus1000 = "HSR_WPlatPlus1000";
    public static final String HSR_WPlatPlus1500 = "HSR_WPlatPlus1500";
    public static final String AdvMedA = "AdvMedA";
    public static final String AdvMedB = "AdvMedB";
    public static final String AdvMedC = "AdvMedC";
    public static final String AdvMedD = "AdvMedD";
    public static final String AdvMedE = "AdvMedE";
    public static final String AdvMedF = "AdvMedF";
    public static final String _A_ = " A ";
    public static final String FXML_LinkIProCst = "/fxml/LinkIProCst.fxml";
    public static final String FXML_LinkIProStaff = "/fxml/LinkIProStaff.fxml";
    public static final String FXML_TMPOMPlus_B = "/fxml/TMPOMPlus_B.fxml";
    public static final String FXML_TMLegacyVIP_A = "/fxml/TMLegacyVIP_A.fxml";
    public static final String FXML_LinkProteksiKu = "/fxml/LinkProteksiKu.fxml";
    public static final String FXML_LinkProtectionPlus = "/fxml/LinkProtectionPlus.fxml";
    public static final String FXML_LinkWealthEnhancement = "/fxml/LinkWealthEnhancement.fxml";
    public static final String FXML_LinkWealthAccumulation = "/fxml/LinkWealthAccumulation.fxml";
    public static final String FXML_LinkMIP = "/fxml/LinkMIP.fxml";
    public static final String FXML_TMEducation = "/fxml/TMEducation.fxml";
    public static final String FXML_LinkFlexiBuilder = "/fxml/LinkFlexiBuilder.fxml";
    public static final String FXML_LinkEducation = "/fxml/LinkEducation.fxml";
    public static final String FXML_LinkRetirement = "/fxml/LinkRetirement.fxml";
    public static final String FXML_LinkInvestasiKu = "/fxml/LinkInvestasiKu.fxml";
    public static final String FXML_TMPOM = "/fxml/TMPOM.fxml";
    public static final String FXML_TMPOMPlus_A = "/fxml/TMPOMPlus_A.fxml";
    public static final String FXML_EMPTY = "/fxml/EMPTY.fxml";
    public static final String FXML_TMPOMPlus_C = "/fxml/TMPOMPlus_C.fxml";
    public static final String FXML_TMPOM_PA = "/fxml/TMPOM_PA.fxml";
    public static final String FXML_TMWellBeing = "/fxml/TMWellBeing.fxml";
    public static final String FXML_LinkMIPPlus = "/fxml/LinkMIPPlus.fxml";
    public static final String FXML_TMLegacyVIP_B = "/fxml/TMLegacyVIP_B.fxml";
    public static final String FXML_TMBusinessInsurance = "/fxml/TMBusinessInsurance.fxml";
    public static final String FXML_TMOptimaHealthHospitalSurgery = "/fxml/TMOHHospitalSurgery.fxml";
    public static final String FXML_TMTermLife = "/fxml/TMTermLife.fxml";
    public static final String FXML_GlobalSignatureLink = "/fxml/GlobalSignatureLink.fxml";

    public static final String LinkWealthEnhancement = "LinkWealthEnhancement";
    public static final String LinkProteksiKu = "LinkProteksiKu";
    public static final String LinkInvestasiKu = "LinkInvestasiKu";
    public static final String LinkWealthAccumulation = "LinkWealthAccumulation";
    public static final String LinkMIP = "LinkMIP";
    public static final String LinkMIPPlus = "LinkMIPPlus";
    public static final String LinkFlexiBuilder = "LinkFlexiBuilder";
    public static final String LinkEducation = "LinkEducation";
    public static final String LinkRetirement = "LinkRetirement";
    public static final String LinkIProCst = "LinkIProCst";
    public static final String LinkIProStaff = "LinkIProStaff";
    public static final String LinkProtectionPlus = "LinkProtectionPlus";
    public static final String TMEducation = "TMEducation";
    public static final String TMPOM = "TMPOM";
    public static final String TMPOMPlus_A = "TMPOMPlus_A";
    public static final String TMPOMPlus_B = "TMPOMPlus_B";
    public static final String TMPOMPlus_C = "TMPOMPlus_C";
    public static final String TMPOM_PA = "TMPOM_PA";
    public static final String TMWellBeing = "TMWellBeing";
    public static final String TMLegacyVIP_A = "TMLegacyVIP_A";
    public static final String TMLegacyVIP_B = "TMLegacyVIP_B";
    public static final String TMBusinessInsurance = "TMBusinessInsurance";
    public static final String TMOptimaHealthHospitalSurgery = "TMOptimaHealthHospitalSurgery";
    public static final String TMTermLife = "TMTermLife";
    public static final String GlobalSignatureLink = "GlobalSignatureLink";
    public static final String USING_SA_PRODUCT = "usingSAproduct";
//    public static final String PROTEKSIKU_FLEXI = "PROTEKSIKU FLEXI";
    public static final String PROTEKSIKU_FLEXI_HEALTH = "HEALTH";
    public static final String PROTEKSIKU_FLEXI_LEGACY = "LEGACY";
    // AP = Annual Premium (Premi Tahunan)
    public static final String AP = "AP";
    // SA = Sum Assured (Uang Pertanggungan)
    public static final String SA = "SA";
    public static final String RATE = "Rate";
    public static final String READ_RATE = "READ_RATE";
    public static final String EMPTY = "EMPTY";
    public static final String ABOVE_1_BIO = "SA 1 Bio and above";
    public static final String UNDER_1_BIO = "SA 250 mio to < 1 bio";
    public static final String PLAN_A_TERM_LIFE = "Hingga usia 75";
    public static final String PLAN_B_TERM_LIFE = "Hingga usia 88";
    public static final String PLAN_C_TERM_LIFE = "Plan C";
    public static final String PLAN_D_TERM_LIFE = "5 Tahun";
    /**
     * Sheet RIDER, Col V
     */
    public static final String INPUT_RIDER_SA = "inputRiderSA";
    /**
     * Sheet RIDER, Col V
     */
    public static final String SPOUSE = "spouse";
    /**
     * Sheet RIDER, Col V
     */
    public static final String REPORT_HSR = "HSR";
    /**
     * Sheet RIDER, Col V
     */
    public static final String REPORT_EXC_HSR = "EXC";
    /**
     * Sheet RIDER, Col V
     */
    public static final String REPORT_HCP = "HCP";
    /**
     * Sheet RIDER, Col V
     */
    public static final String REPORT_ADV_MED = "AdvMed";
    public static final String COR_TYPE_A = "A";
    public static final String COR_TYPE_B = "B";
    public static final String COR_TYPE_C = "C";
    public static final String COR_TYPE_D = "D";
    public static final String COR_TYPE_E = "E";
    public static final String RIDER_RATE_A = "A";
    public static final String RIDER_RATE_B = "B";
    public static final String RIDER_RATE_C = "C";

    // Rider Personal Accident Plan A
    public static final String PA_A = "PA_A";
    // Rider Personal Accident Plan AB
    public static final String PA_AB = "PA_AB";
    // Rider 
    // Rider 
    public static final String UP_DASAR = "up_dasar";
    public static final String PAYOR_WAIVER = "PayorWaiver";
    public static final String INSURED_ISNOT_HOLDER = "Ins!=Hold";
    public static final String INSURED_IS_HOLDER = "Ins==Hold";
    public static final String NO_NEED_UNIQUE = "NoNeedUnique";
    
    public static final String BLANK = "";
    public static final String ZERO = "0";
    public static final String ONE_HUNDRED_STRING = "100";
    public static final String YES = "Y";
    public static final String NO = "N";
    public static final String PayorB65a = "PayorB65a";
    public static final String WaiverB65a = "WaiverB65a";
    public static final String PACKAGE_LinkProteksiKu_IHF1_18_35_53 = "08_LinkProteksiKu_IHF1_18_35_53";
    public static final String PACKAGE_LinkProteksiKu_IHF1_36_50_54 = "08_LinkProteksiKu_IHF1_36_50_54";
    public static final String PACKAGE_LinkProteksiKu_IHF1_51_55_55 = "08_LinkProteksiKu_IHF1_51_55_55";
    public static final String PACKAGE_LinkProteksiKu_IHF2_1_35_56 = "08_LinkProteksiKu_IHF2_1_35_56";
    public static final String PACKAGE_LinkProteksiKu_IHF2_36_50_57 = "08_LinkProteksiKu_IHF2_36_50_57";
    public static final String PACKAGE_LinkProteksiKu_IHF2_51_55_58 = "08_LinkProteksiKu_IHF2_51_55_58";
    public static final String PACKAGE_LinkProteksiKu_ILF1_18_35_59 = "05_LinkProteksiKu_ILF1_18_35_59";
    public static final String PACKAGE_LinkProteksiKu_ILF1_36_50_60 = "05_LinkProteksiKu_ILF1_36_50_60";
    public static final String PACKAGE_LinkProteksiKu_ILF1_51_55_61 = "05_LinkProteksiKu_ILF1_51_55_61";
    public static final String PACKAGE_LinkProteksiKu_ILF2_1_35_62 = "05_LinkProteksiKu_ILF2_1_35_62";
    public static final String PACKAGE_LinkProteksiKu_ILF2_36_50_63 = "05_LinkProteksiKu_ILF2_36_50_63";
    public static final String PACKAGE_LinkProteksiKu_ILF2_51_55_64 = "05_LinkProteksiKu_ILF2_51_55_64";
    public static final String YEARS_65 = "65_years";
    
    /*
    *
    * NEW HSR ID, RADIO BUTTON
    *
    */    
    public static final String HSR_TYPE_PRORATE = "rb_type_prorate";
    public static final String HSR_TYPE_NON_PRORATE = "rb_type_non_prorate";
    public static final String HSR_COV_AREA_IND = "rb_cov_area_indo_malay";
    public static final String HSR_COV_AREA_DUNIA = "rb_cov_area_dunia";
    public static final String HSR_BOOSTER_YA = "rb_booster_ya";
    public static final String HSR_BOOSTER_TIDAK = "rb_booster_tidak";
    public static final String HSR_BIAYA_TNG_SNDR_0 = "rb_biaya_tng_sndr_0";
    public static final String HSR_BIAYA_TNG_SNDR_7500 = "rb_biaya_tng_sndr_7500";
    public static final String HSR_BIAYA_TNG_SNDR_15jt = "rb_biaya_tng_sndr_15jt";
    public static final String HSR_BIAYA_CO_SHARING_0 = "rb_biaya_co_shrng_0";
    public static final String HSR_BIAYA_CO_SHARING_10 = "rb_biaya_co_shrng_10";
    public static final String HSR_BIAYA_CO_SHARING_20 = "rb_biaya_co_shrng_20";
    
    public class RiderAgeValidationMode{
        public static final int IS_LESS_THAN = 0;
        public static final int IS_NOT_LESS_THAN = 1;
        public static final int IS_LESS_OR_EQUALL_THAN = 2;
        public static final int IS_NOT_LESS_OR_EQUALL_THAN = 3;
    }

    public enum PaymentMode implements LabelizedEnum {
        Single, Year, Semester, Quarter, Month;

        @Override
        public String getLabel() {
            return "";
            //return MainApp.getInstance().getResourceBundle().getString("PaymentMode." + name());
        }

        public static PaymentMode fromLabel(String label) {
            if (label == null) {
                return null;
            }
            for (PaymentMode enumValue : PaymentMode.values()) {
                if (label.equals(enumValue.getLabel())) {
                    return enumValue;
                }
            }
            return null;
        }
    }

    public enum FundGrowth {
        Low, Med, High, Printing;
    }

    public enum Period {
        Monthly, Annual;
    }

    public enum AgeUnit {
        years(ChronoUnit.YEARS), months(ChronoUnit.MONTHS), days(ChronoUnit.DAYS);

        private AgeUnit(ChronoUnit cu) {
            this.chronoUnit = cu;
        }
        private final ChronoUnit chronoUnit;

        public ChronoUnit getChronoUnit() {
            return chronoUnit;
        }
    }

}
