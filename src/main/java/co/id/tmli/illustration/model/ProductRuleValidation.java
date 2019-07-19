package co.id.tmli.illustration.model;


import co.id.tmli.illustration.utils.CurrencyEnum;
import static co.id.tmli.illustration.utils.Helper.getCurrIndex;
import static co.id.tmli.illustration.utils.Constants.MULTI_CURR_SEPARATOR;
import static co.id.tmli.illustration.utils.Helper.parseSumInsuredExpression;

public class ProductRuleValidation implements RuleValidation {

    public Integer insuredMinEntryAge;
    public String minRegPremiTriwulan;
    public String minRegPremiMonthly;
    public Integer insuredMaxEntryAge;
    public Integer policyHolderMaxEntryAge;
    public String maxRegPremiSemester;
    public String maxRegPremiTriwulan;
    public Double maxSinglePremium;
    public String minRegPremiAnnual;
    public String minRegPremiSemester;
    public Integer maxPolicyTerm;
    public Double minSinglePremium;
    public String maxRegPremiAnnual;
    public String minRegTopupPremiMonthly;
    public String minSumAssuredExpression;
    public String maxSumAssuredExpression;
    public String minRegTopupPremiAnnual;
    public String minRegTopupPremiSemester;
    public String minRegTopupPremiTriwulan;
    public Integer printingValidationSecondLayer2;
    public Integer printingValidationSecondLayer1;
    public Integer printingValidationFirstLayer;
    public Double ko;
    public String maxRegPremiMonthly;

    private static Double parseMultiCurr(String str, CurrencyEnum curr) {
        if (str == null) {
            return null;
        } else if (str.contains(MULTI_CURR_SEPARATOR)) {
            return Double.parseDouble(str.split(MULTI_CURR_SEPARATOR)[getCurrIndex(curr)]);
        }
        return parseSumInsuredExpression(str);
    }

    @Override
    public Integer getInsuredMinEntryAge() {
        return insuredMinEntryAge;
    }

    @Override
    public Integer getInsuredMaxEntryAge() {
        return insuredMaxEntryAge;
    }

    @Override
    public Integer getPolicyHolderMaxEntryAge() {
        return policyHolderMaxEntryAge;
    }

    @Override
    public Integer getMaxPolicyTerm() {
        return maxPolicyTerm;
    }

    @Override
    public Double getMinSinglePremium() {
        return minSinglePremium;
    }

    @Override
    public String getMinSumAssuredExpression() {
        return minSumAssuredExpression;
    }

    @Override
    public String getMaxSumAssuredExpression() {
        return maxSumAssuredExpression;
    }

    @Override
    public Double getMinRegPremiAnnual(CurrencyEnum curr) {
        return parseMultiCurr(minRegPremiAnnual, curr);
    }

    @Override
    public Double getMinRegPremiSemester(CurrencyEnum curr) {
        return parseMultiCurr(minRegPremiSemester, curr);
    }

    @Override
    public Double getMinRegPremiTriwulan(CurrencyEnum curr) {
        return parseMultiCurr(minRegPremiTriwulan, curr);
    }

    @Override
    public Double getMinRegPremiMonthly(CurrencyEnum curr) {
        return parseMultiCurr(minRegPremiMonthly, curr);
    }

    @Override
    public Double getMaxRegPremiAnnual(CurrencyEnum curr) {
        return parseMultiCurr(maxRegPremiAnnual, curr);
    }

    @Override
    public Double getMaxRegPremiSemester(CurrencyEnum curr) {
        return parseMultiCurr(maxRegPremiSemester, curr);
    }

    @Override
    public Double getMaxRegPremiTriwulan(CurrencyEnum curr) {
        return parseMultiCurr(maxRegPremiTriwulan, curr);
    }

    @Override
    public Double getMaxRegPremiMonthly(CurrencyEnum curr) {
        return parseMultiCurr(maxRegPremiMonthly, curr);
    }

    @Override
    public Double getMinRegTopupPremiAnnual(CurrencyEnum curr) {
        return parseMultiCurr(minRegTopupPremiAnnual, curr);
    }

    @Override
    public Double getMinRegTopupPremiSemester(CurrencyEnum curr) {
        return parseMultiCurr(minRegTopupPremiSemester, curr);
    }

    @Override
    public Double getMinRegTopupPremiTriwulan(CurrencyEnum curr) {
        return parseMultiCurr(minRegTopupPremiTriwulan, curr);
    }

    @Override
    public Double getMinRegTopupPremiMonthly(CurrencyEnum curr) {
        return parseMultiCurr(minRegTopupPremiMonthly, curr);
    }

    @Override
    public Integer getPrintingValidationFirstLayer() {
        return printingValidationFirstLayer;
    }

    @Override
    public Integer getPrintingValidationSecondLayer2() {
        return printingValidationSecondLayer2;
    }

    @Override
    public Integer getPrintingValidationSecondLayer1() {
        return printingValidationSecondLayer1;
    }

}
