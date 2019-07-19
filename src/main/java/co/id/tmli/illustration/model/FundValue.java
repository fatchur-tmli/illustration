package co.id.tmli.illustration.model;

import co.id.tmli.illustration.utils.Constants;
import co.id.tmli.illustration.utils.CurrencyEnum;
import java.util.List;

@lombok.Data
public class FundValue implements Fund {

    private Fund fund;
    private Double value;

    public FundValue(Fund f) {
        this.fund = f;
    }

    @Override
    public String toString() {
        return fund.getDesc();
    }

    @Override
    public String getCode() {
        return fund.getCode();
    }

    @Override
    public String getDesc() {
        return fund.getDesc();
    }

    @Override
    public CurrencyEnum getCurrency() {
        return fund.getCurrency();
    }

    @Override
    public Double getGrowthAsumptionLow() {
        return fund.getGrowthAsumptionLow();
    }

    @Override
    public Double getGrowthAsumptionMedium() {
        return fund.getGrowthAsumptionMedium();
    }

    @Override
    public Double getGrowthAsumptionHigh() {
        return fund.getGrowthAsumptionHigh();
    }

    @Override
    public Double getFundGrowthAssumption(Constants.Period period, Constants.FundGrowth fundGrowth) {
        return fund.getFundGrowthAssumption(period, fundGrowth);
    }

    @Override
    public List<Double> getFundPerformanceYears() {
        return fund.getFundPerformanceYears();
    }

    @Override
    public String getNoteCode() {
        return fund.getNoteCode();
    }
}
