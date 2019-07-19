package co.id.tmli.illustration.model;

import co.id.tmli.illustration.utils.CurrencyEnum;

@lombok.Data
public class FundValueProjection {

    private int year, insuredAge;
    private double fundValue, deathBenefit, surrenderValue;
    private double loyaltyBonus;
    private boolean hasLoyaltyBonus;
    private CurrencyEnum currency;

}
