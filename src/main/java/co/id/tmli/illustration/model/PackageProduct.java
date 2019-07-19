package co.id.tmli.illustration.model;

import java.util.Map;

@lombok.Data
public class PackageProduct {

    private String packageCode;
    private String packageName;
    private String productCode;
    private String planCode;
    private String packageType;
    private String packageGroup;
    private Integer minInsuredEntryAge;
    private Integer maxInsuredEntryAge;
    private Integer minHolderEntryAge;
    private Integer maxHolderEntryAge;
    private boolean insuredIsHolder;
    private Integer minPremiumTerm;
    private Integer defaultPremiumTerm;
    private Double regPremium;
    private Double regTopUp;
    private Double sumAssured;
    private Double totalPremium;
    private Double defaultTotalPremium;
    private Integer ape;
    private Integer minimalFundTerm;
    private Map<String, String> ridersMap;
    private Map<String, String> riderMandatoryMap;
    private Map<String, String> groupMandatoryMap;
    
    public boolean isValid(int insuredEntryAge, int holderEntryAge, boolean insuredIsHolder) {
        return isValidInsuredEntryAge(insuredEntryAge)
                && isValidPolicyHolderEntryAge(holderEntryAge)
                && isInsuredIsHolder() == insuredIsHolder;
    }

    private boolean isValidInsuredEntryAge(int age) {
        boolean vMin = minInsuredEntryAge == null;
        if (!vMin) {
            vMin = minInsuredEntryAge <= age;
        }
        boolean vMax = maxInsuredEntryAge == null;
        if (!vMax) {
            vMax = age <= maxInsuredEntryAge;
        }
        boolean v = vMin && vMax;
        return v;
    }

    private boolean isValidPolicyHolderEntryAge(int age) {
        boolean vMin = minHolderEntryAge == null;
        if (!vMin) {
            vMin = minHolderEntryAge <= age;
        }
        boolean vMax = maxHolderEntryAge == null;
        if (!vMax) {
            vMax = age <= maxHolderEntryAge;
        }
        boolean v = vMin && vMax;
        return v;
    }
}
