package co.id.tmli.illustration.service;

import co.id.tmli.illustration.model.Fund;
import co.id.tmli.illustration.model.Gender;
import co.id.tmli.illustration.model.ProductItem;
import co.id.tmli.illustration.utils.CurrencyEnum;
import java.util.List;

public interface ProductService {

    List<ProductItem> getProductItems(int insuredAge, int holderAge);

    List<Fund> getFundConfigList(String productCode, CurrencyEnum b);

    List<ProductItem> getPackageProductItems(int insuredAge, int holderAge, boolean insuredIsHolder);

    boolean isPackageProductAvailable();

    Double getCostOfInsurance(String productCode, Gender gender, int year);
}
