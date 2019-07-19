package co.id.tmli.illustration.model;

import co.id.tmli.illustration.utils.Constants;

@lombok.Data
public class RiderValue implements Rider {

    Rider rider;
    Rider riderParent;
    Double valueProperty = new Double(0);
    
    // == add rider hsr property
    private String hsrType;
    private String hsrCoverageArea;
    private String hsrBooster;
    private String hsrBiayaTanggungSendiri;
    private String hsrBiayaCoSharing;

    public Double getValue() {
        return valueProperty;
    }

    public void setValue(Double v) {
        this.valueProperty = v;
    }

    
    
    public RiderValue(Rider rider) {
        this.rider = rider;
    }
    
    public String getHsrType() {
        if (this.hsrType.equalsIgnoreCase(Constants.HSR_TYPE_PRORATE)) {
            return "P1";
        } else {
            return "P0";
        }
    }
    
    public String getHsrCoverageArea() {
        if (this.hsrCoverageArea != null) {
            if (this.hsrCoverageArea.equalsIgnoreCase(Constants.HSR_COV_AREA_IND)) {
                return "B1";
            } else {
                return "B2";
            }
        }
        return "";
    }
    
    public String getHsrBooster() {
        if (this.hsrBooster != null) {
            if (this.hsrBooster.equalsIgnoreCase(Constants.HSR_BOOSTER_YA)) {
                return "W";
            } else {
                return "N";
            }
        }
        return "";
    }
    
    public String getHsrBiayaTanggungSendiri() {
        if (this.hsrBiayaTanggungSendiri != null) {
           if (this.hsrBiayaTanggungSendiri.equalsIgnoreCase(Constants.HSR_BIAYA_TNG_SNDR_0)) {
                return "D0";
            } else if (this.hsrBiayaTanggungSendiri.equalsIgnoreCase(Constants.HSR_BIAYA_TNG_SNDR_7500)) {
                return "D1";
            } else {
                return "D2";
            } 
        }
        return "";        
    }
    
    public String getHsrBiayaCoSharing() {
        if (this.hsrBiayaCoSharing.equalsIgnoreCase(Constants.HSR_BIAYA_CO_SHARING_0)) {
            return "C0";
        } else if (this.hsrBiayaCoSharing.equalsIgnoreCase(Constants.HSR_BIAYA_CO_SHARING_10)) {
            return "C1";
        } else {
            return "C2";
        }
    }

    @Override
    public String toString() {
        return getDesc();
    }

    @Override
    public String getRiderCode() {
        return rider.getRiderCode();
    }

    @Override
    public String getDesc() {        
        return rider.getDesc();        
    }

    @Override
    public String getDescKolomW() {
        return rider.getDescKolomW();
    }

    @Override
    public String getDescShort() {
        return rider.getDescShort();
    }

    @Override
    public String getGroupCode() {
        return rider.getGroupCode();
    }

    @Override
    public String getMinHolderEntryAge() {
        return rider.getMinHolderEntryAge();
    }

    @Override
    public String getMaxInsuredEntryAge() {
        return rider.getMaxInsuredEntryAge();
    }

    @Override
    public Double getMinSA() {
        return rider.getMinSA();
    }

    @Override
    public String getReleasedDate() {
        return rider.getReleasedDate();
    }

    @Override
    public Double getMaxSumInsured() {
        return rider.getMaxSumInsured();
    }

    @Override
    public boolean isSyariahProduct() {
        return rider.isSyariahProduct();
    }

    @Override
    public Integer getCoveredAges() {
        return rider.getCoveredAges();
    }

    @Override
    public String getValidationForAdd() {
        return rider.getValidationForAdd();
    }

    @Override
    public String getCurrency() {
        return rider.getCurrency();
    }

    @Override
    public String getBundledProduct() {
        return rider.getBundledProduct();
    }

    @Override
    public String getCorFormula() {
        return rider.getCorFormula();
    }

    @Override
    public String getRiderSA() {
        return rider.getRiderSA();
    }

    @Override
    public String getColN() {
        return rider.getColN();
    }

    @Override
    public String getIllustrationOutput() {
        return rider.getIllustrationOutput();
    }

    @Override
    public String getDescLong() {
        return rider.getDescLong();
    }

    @Override
    public String getCorType() {
        return rider.getCorType();
    }

    @Override
    public String getMinInsuredEntryAge() {
        return rider.getMinInsuredEntryAge();
    }

    @Override
    public String getMaxHolderEntryAge() {
        return rider.getMaxHolderEntryAge();
    }

    @Override
    public Double getMinBasicSA() {
        return rider.getMinBasicSA();
    }

    @Override
    public String getRateType() {
        return rider.getRateType();
    }

    @Override
    public Double getRate(Gender gender, Integer age, String rateType, Integer y, Integer riskClass) {
        return rider.getRate(gender, age, rateType, y, riskClass);
    }

}
