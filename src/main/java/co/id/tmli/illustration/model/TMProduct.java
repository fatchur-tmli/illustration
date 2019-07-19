package co.id.tmli.illustration.model;

import co.id.tmli.illustration.utils.CurrencyEnum;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import co.id.tmli.illustration.utils.Constants.PaymentMode;


/**
 * Sheet PRODUCT
 */
@lombok.Data
public class TMProduct implements Serializable {

    private List<PackageProduct> packageProductList;
    private ProductItem productItem;
    private Map<String, String> reportConfig;
    private String inputPremiumTopUp;
    private String paymentFrequency;
    private List<Fund> fundConfigList;
    private String currencyPremiumPolicyTerm;
    private String inputSaRegularPremium;
    private String typeOfPlanForTmWellBeing;    
    private String sumAssured;
    private String premiumTerm;
    private RuleValidation ruleValidation;
    private String totalPremium;
    private String col23;
    private String riskClassConfig;
    private List<String> riders;
    private List<CurrencyEnum> currencyList;
    private List<PaymentMode> paymentModeList;
    
    public TMProduct() {
    }
    
    public TMProduct(ProductItem pi) {
        this.productItem = pi;
    }

    public boolean hasPackageProduct() {
        return packageProductList != null && !packageProductList.isEmpty();
    }

    public PackageProduct getPackageProduct(String packageCode) {
        if (packageProductList == null) {
            return null;
        }
        for (PackageProduct pp : packageProductList) {
            if (pp.getPackageCode().equals(packageCode)) {
                return pp;
            }
        }
        return null;
    }

    private Map<Integer, Double> scheduleWithdrawalMap;
    private Map<Integer, Double> stuAllocationMap;
    private Map<Integer, Double> withdrawalChargeMap;
    private Map<Integer, Double> surrenderChargeMap;
    private Map<Integer, Double> premiumHolidayChargeMap;
    private Map<Integer, Double> serviceChargeMap;
    private Map<Integer, Double[]> adminFeeMap;
    private Map<Integer, Double> loyaltyBonusMap;
    private Map<Integer, Double> regularTopUpAllocationMap;
    private Map<Integer, Double> premiumAllocationMap;    

    public Double getCostOfInsurance(Gender gender, int year) {
        for (CostOfInsurance coi : costOfInsurances) {
            if (coi.getAge() == year) {
                return coi.getValue().getValue(gender);
            }
        }
        return null;
    }
    private List<CostOfInsurance> costOfInsurances;

    @Override
    public String toString() {
        return getProductItem().getProductGroup() + " " + getProductItem().getProductCode();
    }
    
    public TMProduct toNewProduct(){
        TMProduct product = new TMProduct();
        product.setPackageProductList(packageProductList);
        product.setProductItem(productItem);
        product.setReportConfig(reportConfig);
        product.setInputPremiumTopUp(inputPremiumTopUp);
        product.setPaymentFrequency(paymentFrequency);
        product.setFundConfigList(fundConfigList);
        product.setCurrencyPremiumPolicyTerm(currencyPremiumPolicyTerm);
        product.setInputSaRegularPremium(inputSaRegularPremium);
        product.setTypeOfPlanForTmWellBeing(typeOfPlanForTmWellBeing);
        product.setSumAssured(sumAssured);
        product.setPremiumTerm(premiumTerm);
        product.setRuleValidation(ruleValidation);
        product.setTotalPremium(totalPremium);
        product.setCol23(col23);
        product.setRiskClassConfig(riskClassConfig);
        product.setRiders(riders);
        product.setCurrencyList(currencyList);
        product.setPaymentModeList(paymentModeList);
        product.setScheduleWithdrawalMap(scheduleWithdrawalMap);
        product.setStuAllocationMap(stuAllocationMap);
        product.setWithdrawalChargeMap(withdrawalChargeMap);
        product.setSurrenderChargeMap(surrenderChargeMap);
        product.setPremiumHolidayChargeMap(premiumHolidayChargeMap);
        product.setServiceChargeMap(serviceChargeMap);
        product.setAdminFeeMap(adminFeeMap);
        product.setLoyaltyBonusMap(loyaltyBonusMap);
        product.setRegularTopUpAllocationMap(regularTopUpAllocationMap);
        product.setPremiumAllocationMap(premiumAllocationMap);
        product.setCostOfInsurances(costOfInsurances);                
        
        return product;
    }
    
}
