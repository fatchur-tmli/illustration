package co.id.tmli.illustration.model;

import co.id.tmli.illustration.utils.CurrencyEnum;
import java.util.List;
import co.id.tmli.illustration.utils.Constants;

@lombok.Data
public class FundImpl implements Fund {

    String code;
    String desc;
    CurrencyEnum currency;

    String noteCode;

    Double growthAsumptionLow;
    Double growthAsumptionMedium;
    Double growthAsumptionHigh;

    List<Double> fundPerformanceYears;

    @Override
    public String toString() {
        return desc;
    }

    @Override
    public Double getFundGrowthAssumption(Constants.Period period, Constants.FundGrowth fundGrowth) {
        switch (period) {
            case Annual: {
                switch (fundGrowth) {
                    case Low:
                        return growthAsumptionLow;
                    case Med:
                        return growthAsumptionMedium;
                    case High:
                        return growthAsumptionHigh;
                    case Printing:
                        return 0d;
                }
            }
            case Monthly: {
                switch (fundGrowth) {
                    case Low:
                        return growthAsumptionLow / 12;
                    case Med:
                        return growthAsumptionMedium / 12;
                    case High:
                        return growthAsumptionHigh / 12;
                    case Printing:
                        return 0d;
                }
            }
        }
        return null;
    }

}
