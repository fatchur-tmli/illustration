package co.id.tmli.illustration.service.repo;

import co.id.tmli.illustration.model.CostOfInsurance;
import java.util.List;
import java.util.Map;

public interface ProductFeatureRepo {

    Map<Integer, Double> getScheduleWithdrawalMap(String productCode);

    Map<Integer, Double> getWithdrawalChargeMap(String productCode);

    Map<Integer, Double> getStuAllocationMap(String productCode);

    Map<Integer, Double> getRegularTopUpAllocationMap(String productCode);

    Map<Integer, Double> getPremiumAllocationMap(String productCode);

    List<CostOfInsurance> getCostOfInsurances(String productCode);

    Map<Integer, Double> getSurrenderChargeMap(String productCode);

    Map<Integer, Double> getPremiumHolidayChargeMap(String productCode);

    Map<Integer, Double> getServiceChargeMap(String productCode);

    Map<Integer, Double[]> getAdminFeeMap(String productCode);

    Map<Integer, Double> getLoyaltyBonusMap(String productCode);
       
}
