package co.id.tmli.illustration.model;

import java.util.Map;

import co.id.tmli.illustration.utils.Constants;
import java.util.List;

@lombok.Data
public class RiderImpl implements Rider {

    private String riderCode;
    private String desc;
    private String descKolomW;
    private String descShort;
    private String groupCode;
    private String minHolderEntryAge;
    private String maxHolderEntryAge;
    private String minInsuredEntryAge;
    private String maxInsuredEntryAge;
    private Double minSA;
    private String releasedDate;
    private Double maxSumInsured;
    private boolean syariahProduct;
    private Integer coveredAges;
    private String validationForAdd;
    private String currency;
    private String bundledProduct;
    private String corFormula;
    private String riderSA;
    private String colN;
    private String illustrationOutput;
    private String descLong;
    private String corType;
    private Double minBasicSA;
    private String rateType;
    // Map<Age, Rate>
    private Map<Integer, Double> riderRateMaleA;
    private Map<Integer, Double> riderRateFemaleA;
    // Map<Year, Map<Age, Rate>>
    private Map<Integer, Map<Integer, Double>> riderRateMaleB;
    private Map<Integer, Map<Integer, Double>> riderRateFemaleB;

    private List<RiskRate> riskRates;

    public Double getRate(Gender gender, Integer age, String rateType, Integer y, Integer riskClass) {
        switch (rateType) {
            case Constants.RIDER_RATE_A:
                return gender.isMale() ? riderRateMaleA.get(age) : riderRateFemaleA.get(age);
            case Constants.RIDER_RATE_B:
                if (y != null) {
                    return gender.isMale() ? riderRateMaleB.get(y).get(age) : riderRateFemaleB.get(y).get(age);
                }
                return 0d;
            case Constants.RIDER_RATE_C:
                return riskRates.stream()
                        .filter(rr -> {
                            boolean b = rr.getAgeRange().check(age) && rr.getRisk().equals(riskClass);
//                            logger.debug("[getRate]-found={}, riskRate={}, age={}, riskClass={}", b, rr, age, riskClass);
                            return b;
                        })
                        .findFirst()
                        // .orElseThrow(() -> new RiderException("Rate not found, rider=" + riderCode + ", age=" + age + ", riskClass=" + riskClass))
                        .orElse(new RiskRate(null, null, 0d))
                        .getRate();
        }
        return 0d;
    }

    @Override
    public String toString() {
        return desc;
    }

}
