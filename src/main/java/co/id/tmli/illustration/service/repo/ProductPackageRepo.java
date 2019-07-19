package co.id.tmli.illustration.service.repo;

import co.id.tmli.illustration.model.PackageProduct;
import java.util.List;

public interface ProductPackageRepo {

    List<PackageProduct> getPackageProducts(String productCode);
}
