package co.id.tmli.illustration.service.repo;

import co.id.tmli.illustration.model.TMProduct;
import java.util.List;

public interface ProductRepo {

    List<TMProduct> getProducts();

    TMProduct getProduct(String productCode);
}
