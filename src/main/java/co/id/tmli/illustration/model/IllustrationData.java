package co.id.tmli.illustration.model;

import co.id.tmli.illustration.utils.Constants.FundGrowth;
import co.id.tmli.illustration.utils.Constants.PaymentMode;
import co.id.tmli.illustration.utils.CurrencyEnum;
import co.id.tmli.illustration.utils.Helper;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@lombok.Data
public class IllustrationData {

    public Integer getHolderAge() {
        return holderBirthDate != null ? Helper.getAgeInYear(holderBirthDate) : null;
    }
    private LocalDate spouseBirthdate;
    private String productCode;
    private CurrencyEnum currency;
    private Double sumInsured;
    private Double regularPremium;
    private Double regularTopUp;
    private Double totalPremium;
    private Integer spouseAge;

    public Integer getInsuredAge() {       
        return insuredBirthDate != null ? Helper.getAgeInYear(insuredBirthDate) : null;
    }
    
    public Integer getInsuredAge4UW() {
        if (insuredBirthDate != null) {
            if (Helper.getAgeInYear2(insuredBirthDate) == 0) {
                return Helper.getAgeInYear2(insuredBirthDate);
            } else {
                return Helper.getAgeInYear(insuredBirthDate);
            }
        } else {
            return null;
        }
    }

    private Integer premiumPaymentPeriod;
    private PaymentMode paymentMode;
    private List<FundValue> fundList;
    private List<SingleTopupWithdrawalYear> singleTopupWithdrawalYearList;
    private List<RiderValue> riders;
    private LocalDate insuredBirthDate;
    private LocalDate holderBirthDate;
    private Double ac = 0d;
    private Double ad = 0d;
    private Double ae = 0d;
    private Double af = 0d;
    private Double ag = 0d;
    private Double ah = 0d;
    private Double ai = 0d;
    private Double aj = 0d;
    private Double ak = 0d;
    private Double singlePremi;
    private int pensionAge;
    private List<ScheduleWithdrawalData> scheduleWithdrawalDataList;
    private Double regPremium;
    private Double riderPremium;
    private Integer policyTerm;
    private String plan;
    private List<FundValueProjection> fundValueProjectionsLow, fundValueProjectionsMed, fundValueProjectionsHigh;
    private List<FundValueProjection> fundValueProjectionsLowTableG, fundValueProjectionsMedTableG, fundValueProjectionsHighTableG;
    private List<Pair7<String>> insuranceBenefitReportTable;
    private String packageCode;
    private Occupation insuredOccupation;
    private Occupation holderOccupation;
    private String insuredName;
    private Boolean insuredGender;
    private String holderName;
    private Boolean holderGender;
    private Boolean spouseGender;
    private Boolean insuredHolder;
    private Boolean isShowUnderwriting;
    private String classRoom [];
    private String coverageArea [];
    private String claimPayment [];
    private String booster [];
    private String deductible [];
    private String coSharing [];
    private Map<String, List<String>> hsrDetails;
    private String sa;
    
    
    public SingleTopupWithdrawalYear getSingleTopupWithdrawalYear(int year) {
        return singleTopupWithdrawalYearList.stream()
                .filter((t) -> t.getYear() == year)
                .findFirst()
                .orElse(null);
    }

    public double getLoyaltyBonus(int year) {
        /*
        loyalty bonus can be got from fundValueProjectionsLow || fundValueProjectionsMed || fundValueProjectionsHigh.
        all have same loyalty bonus value
         */
        for (FundValueProjection fvp : fundValueProjectionsLow) {
            if (fvp.getYear() == year) {
                return fvp.getLoyaltyBonus();
            }
        }
        return 0;
    }

    public FundValueProjection getFundValueProjection(int year, FundGrowth fg) {
        List<FundValueProjection> list = null;
        if (FundGrowth.Low == fg) {
            list = fundValueProjectionsLow;
        } else if (FundGrowth.Med == fg) {
            list = fundValueProjectionsMed;
        } else if (FundGrowth.High == fg) {
            list = fundValueProjectionsHigh;
        }
        if (list != null) {
            for (FundValueProjection fvp : list) {
                if (fvp.getYear() == year) {
                    return fvp;
                }
            }
        }
        return null;
    }
    
    public FundValueProjection getFundValueProjectionTableG(int year, FundGrowth fg) {
        List<FundValueProjection> list = null;
        if (FundGrowth.Low == fg) {
            list = fundValueProjectionsLowTableG;
        } else if (FundGrowth.Med == fg) {
            list = fundValueProjectionsMedTableG;
        } else if (FundGrowth.High == fg) {
            list = fundValueProjectionsHighTableG;
        }
        if (list != null) {
            for (FundValueProjection fvp : list) {
                if (fvp.getYear() == year) {
                    return fvp;
                }
            }
        }
        return null;
    }
    
    public IllustrationData toNewIllustationData(){
        IllustrationData data = new IllustrationData();
        data.setSpouseBirthdate(spouseBirthdate);
        data.setProductCode(productCode);
        data.setCurrency(currency);
        data.setSumInsured(sumInsured);
        data.setRegularPremium(regularPremium);
        data.setRegularTopUp(regularTopUp);
        data.setTotalPremium(totalPremium);
        data.setSpouseAge(spouseAge);
        data.setPremiumPaymentPeriod(premiumPaymentPeriod);
        data.setPaymentMode(paymentMode);
        data.setFundList(fundList);
        data.setSingleTopupWithdrawalYearList(singleTopupWithdrawalYearList);
        data.setRiders(riders);
        data.setInsuredBirthDate(insuredBirthDate);
        data.setHolderBirthDate(holderBirthDate);
        data.setAc(ac);
        data.setAd(ad);
        data.setAe(ae);
        data.setAf(af);
        data.setAg(ag);
        data.setAh(ah);
        data.setAi(ai);
        data.setAj(aj);
        data.setAk(ak);
        data.setSinglePremi(singlePremi);
        data.setPensionAge(pensionAge);
        data.setScheduleWithdrawalDataList(scheduleWithdrawalDataList);
        data.setRegPremium(regPremium);
        data.setRiderPremium(riderPremium);
        data.setPolicyTerm(policyTerm);
        data.setPlan(plan);
        data.setFundValueProjectionsLow(fundValueProjectionsLow);
        data.setFundValueProjectionsMed(fundValueProjectionsMed);
        data.setFundValueProjectionsHigh(fundValueProjectionsHigh);
        data.setFundValueProjectionsLowTableG(fundValueProjectionsLowTableG);
        data.setFundValueProjectionsMedTableG(fundValueProjectionsMedTableG);
        data.setFundValueProjectionsHighTableG(fundValueProjectionsHighTableG);
        data.setInsuranceBenefitReportTable(insuranceBenefitReportTable);
        data.setPackageCode(packageCode);
        data.setInsuredOccupation(insuredOccupation);
        data.setHolderOccupation(holderOccupation);
        data.setInsuredName(insuredName);
        data.setInsuredGender(insuredGender);
        data.setHolderName(holderName);
        data.setHolderGender(holderGender);
        data.setSpouseGender(spouseGender);
        data.setInsuredHolder(insuredHolder);
        data.setIsShowUnderwriting(isShowUnderwriting);
        data.setSa(sa);
        
        return data; 
    }

}
