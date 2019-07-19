/**
 * 
 */
package co.id.tmli.illustration.model;

import java.util.List;
import co.id.tmli.illustration.utils.Constants;
import co.id.tmli.illustration.utils.CurrencyEnum;

public interface Fund {
	
    String getCode();

    String getDesc();

    String getNoteCode();

    CurrencyEnum getCurrency();

    Double getGrowthAsumptionLow();

    Double getGrowthAsumptionMedium();

    Double getGrowthAsumptionHigh();

    Double getFundGrowthAssumption(Constants.Period period, Constants.FundGrowth fundGrowth);

    List<Double> getFundPerformanceYears();

}
