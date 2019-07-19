package co.id.tmli.illustration.service;

import java.time.LocalDate;
import java.util.List;

import co.id.tmli.illustration.utils.Constants.Period;
import co.id.tmli.illustration.model.Fund;
import co.id.tmli.illustration.model.Gender;
import co.id.tmli.illustration.model.HsrCode;
import co.id.tmli.illustration.model.Occupation;
import co.id.tmli.illustration.model.ProductItem;
import co.id.tmli.illustration.model.Rider;
import co.id.tmli.illustration.model.TMProduct;
import co.id.tmli.illustration.utils.Helper;
import co.id.tmli.illustration.utils.CurrencyEnum;
import co.id.tmli.illustration.model.RuleValidation;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

@Component
public class IllustrationService {

    @lombok.Getter
    private static final IllustrationService singleton = new IllustrationService();

    public boolean isPackageProductAvailable() {
        return ServiceProvider.getSingleton().getProductService().isPackageProductAvailable();
    }

    public List<ProductItem> getPackageProductItems(int insuredAge, int holderAge, boolean insuredIsHolder) {
        return ServiceProvider.getSingleton().getProductService().getPackageProductItems(insuredAge, holderAge, insuredIsHolder);
    }

    public List<ProductItem> getProductItems(int insuredAge, int holderAge) {
        return ServiceProvider.getSingleton().getProductService().getProductItems(insuredAge, holderAge);
    }

    public TMProduct getTMProduct(String productCode) {
        return ServiceProvider.getSingleton().getProductRepo().getProduct(productCode);
    }

    public List<Fund> getFundConfigList(String productCode, CurrencyEnum b) {
        return ServiceProvider.getSingleton().getProductService().getFundConfigList(productCode, b);
    }

    public RuleValidation getRuleValidation(String productCode) {
        return ServiceProvider.getSingleton().getProductRepo().getProduct(productCode).getRuleValidation();
    }

    public Rider getRiderConfig(String riderPlan) {
        return ServiceProvider.getSingleton().getRiderService().getRider(riderPlan);
    }

    public List<Rider> getRiders(boolean isSyariahProduct, boolean isInsuredIsHolder, long getInsuredAge, long getHolderAge, CurrencyEnum curr, List<String> riders) {
        return ServiceProvider.getSingleton().getRiderService().getRiders(isSyariahProduct, isInsuredIsHolder, getInsuredAge, getHolderAge, curr, riders);
    }

    public List<Rider> getRidersByBundledProduct(String productCode) {
        return ServiceProvider.getSingleton().getRiderService().getRidersByBundledProduct(productCode);
    }

    public Double getCostOfInsurance(Integer year, boolean isMale, String productCode) {
        return getTMProduct(productCode).getCostOfInsurance(Gender.parse(isMale), year);
    }

    public Double getPremiumAllocationPercent(String productCode, Integer year) {
        return getTMProduct(productCode).getPremiumAllocationMap().get(year);
    }

    public Double getRegularTopUpAllocationPercent(String productCode, Integer year) {
        return getTMProduct(productCode).getRegularTopUpAllocationMap().get(year);
    }

    public Double getLoyaltyBonus(String productCode, Integer year) {
        return getTMProduct(productCode).getLoyaltyBonusMap().get(year);
    }

    public Double getAdminFee(String productCode, Integer year, CurrencyEnum c) {
        return getTMProduct(productCode).getAdminFeeMap().get(year)[Helper.getCurrIndex(c)];
    }

    public Double getServiceCharge(String productCode, Integer year) {
        return getTMProduct(productCode).getServiceChargeMap().get(year);
    }

    public Double getPremiumHolidayCharge(String productCode, Integer year) {
        return getTMProduct(productCode).getPremiumHolidayChargeMap().get(year);
    }

    public Double getSurrenderChargePercent(String productCode, Integer year) {
        return getTMProduct(productCode).getSurrenderChargeMap().get(year);
    }

    public Double getWithdrawalChargePercent(String productCode, Integer year) {
        return getTMProduct(productCode).getWithdrawalChargeMap().get(year);
    }

    public Double getStuAllocation(String productCode, Integer year) {
        return getTMProduct(productCode).getStuAllocationMap().get(year);
    }

    public Double getFundGrowthValidation(Period p, Integer policyYear) {
        return ServiceProvider.getSingleton().getFundRepo().getFundGrowthValidation(p, policyYear);
    }

    public Double getScheduleWithdrawal(String productCode, Integer year) {
        return ServiceProvider.getSingleton().getProductRepo().getProduct(productCode).getScheduleWithdrawalMap().get(year);
    }    

}
