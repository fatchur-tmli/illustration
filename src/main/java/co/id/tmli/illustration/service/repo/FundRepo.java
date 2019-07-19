/**
 * 
 */
package co.id.tmli.illustration.service.repo;

import java.util.List;
import co.id.tmli.illustration.model.Fund;
import co.id.tmli.illustration.utils.Constants;

public interface FundRepo {
	
    List<Fund> getFunds();

    List<Fund> getFundsByProductCode(String productCode);

    Double getFundGrowthValidation(Constants.Period a, Integer policyYear);

}
