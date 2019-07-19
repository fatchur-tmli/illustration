package co.id.tmli.illustration.model;

import co.id.tmli.illustration.utils.CurrencyEnum;

public interface RuleValidation {
	String A = "->";
	 
	Integer getPrintingValidationFirstLayer();
	Integer getPrintingValidationSecondLayer2();
	Integer getPrintingValidationSecondLayer1();
	Double getMinRegTopupPremiAnnual(CurrencyEnum cry);
	Double getMinRegTopupPremiSemester(CurrencyEnum cry);
	Double getMinRegTopupPremiTriwulan(CurrencyEnum cry);
	Double getMinRegTopupPremiMonthly(CurrencyEnum cry);
	Double getMinRegPremiAnnual(CurrencyEnum cry);
	Double getMinRegPremiSemester(CurrencyEnum cry);
	Double getMinRegPremiTriwulan(CurrencyEnum cry);
	Double getMinRegPremiMonthly(CurrencyEnum cry);
	Double getMaxRegPremiAnnual(CurrencyEnum cry);
	Double getMaxRegPremiSemester(CurrencyEnum cry);
	Double getMaxRegPremiTriwulan(CurrencyEnum cry);
	Double getMaxRegPremiMonthly(CurrencyEnum cry);
	Integer getMaxPolicyTerm();
	Double getMinSinglePremium();
	String getMinSumAssuredExpression();
	String getMaxSumAssuredExpression();
	Integer getInsuredMinEntryAge();
	Integer getInsuredMaxEntryAge();
	Integer getPolicyHolderMaxEntryAge();
}
