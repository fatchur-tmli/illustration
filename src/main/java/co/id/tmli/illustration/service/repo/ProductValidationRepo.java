package co.id.tmli.illustration.service.repo;

import co.id.tmli.illustration.model.ProductRuleValidation;

public interface ProductValidationRepo {

    ProductRuleValidation getProductRuleValidation(String productCode);
}
