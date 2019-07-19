package co.id.tmli.illustration.service;

import co.id.tmli.illustration.model.Fund;
import co.id.tmli.illustration.model.Gender;
import co.id.tmli.illustration.model.ProductItem;
import co.id.tmli.illustration.model.RuleValidation;
import co.id.tmli.illustration.model.TMProduct;
import co.id.tmli.illustration.service.repo.ProductRepo;
import co.id.tmli.illustration.utils.CurrencyEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<ProductItem> getProductItems(int insuredAge, int holderAge) {
        List<ProductItem> trad = new ArrayList<>(), link = new ArrayList<>();

        for (TMProduct tm : productRepo.getProducts()) {
            RuleValidation rv = tm.getRuleValidation();
            if (rv.getInsuredMinEntryAge() <= insuredAge
                    && insuredAge <= rv.getInsuredMaxEntryAge()
                    && holderAge <= rv.getPolicyHolderMaxEntryAge()) {
                ProductItem productItem = tm.getProductItem();
                if (ProductItem.PRODUCT_GROUP_TRADITIONAL.equals(productItem.getProductGroup())) {
                    trad.add(productItem);
                } else {
                    link.add(productItem);
                }
            }
        }

        List<ProductItem> resultList = new ArrayList();
        if (!link.isEmpty()) {
            Collections.sort(link, (s1, s2) -> ((String) s1.getProductCode()).compareTo((String) s2.getProductCode()));
            resultList.add(ProductItem.createGroupUnitLink());
            resultList.addAll(link);
        }
        if (!trad.isEmpty()) {
            Collections.sort(trad, (s1, s2) -> ((String) s1.getProductCode()).compareTo((String) s2.getProductCode()));
            resultList.add(ProductItem.createGroupTraditional());
            resultList.addAll(trad);
        }
        return resultList;
    }

    @Override
    public List<Fund> getFundConfigList(String productCode, CurrencyEnum b) {
        return productRepo.getProduct(productCode).getFundConfigList()
                .stream()
                .filter(f -> f.getCurrency().equals(b))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductItem> getPackageProductItems(int insuredAge, int holderAge, boolean insuredIsHolder) {

        Map<String, List<ProductItem>> map = new TreeMap<>();

        productRepo.getProducts().stream()
                .filter(tm -> tm.hasPackageProduct())
                .filter(tm -> {
                    RuleValidation rv = tm.getRuleValidation();
                    return rv.getInsuredMinEntryAge() <= insuredAge
                            && insuredAge <= rv.getInsuredMaxEntryAge()
                            && holderAge <= rv.getPolicyHolderMaxEntryAge();
                })
                .forEach(tm -> {
                    tm.getPackageProductList().stream()
                            .filter(pkg -> pkg.isValid(insuredAge, holderAge, insuredIsHolder))
                            .map(pkg -> {
                                ProductItem pi = tm.getProductItem().createNewCopy();
                                pi.setPackageProduct(pkg);
                                return pi;
                            })
                            .forEach(pi -> {
                                List<ProductItem> list = map.get(pi.getPackageProduct().getPackageGroup());
                                if (list == null) {
                                    map.put(pi.getPackageProduct().getPackageGroup(), list = new ArrayList());
                                }
                                list.add(pi);
                            });
                });

        List<ProductItem> resultList = new ArrayList<>();
        map.forEach((pkgGroup, list) -> {
            Collections.sort(list, (s1, s2) -> s1.getPackageProduct().getPackageCode().compareTo(s2.getPackageProduct().getPackageCode()));
            resultList.add(ProductItem.createGroup(pkgGroup));
            resultList.addAll(list);
        });
        return resultList;
    }

    private Boolean packageProductAvailable = null;

    @Override
    public boolean isPackageProductAvailable() {
        if (packageProductAvailable == null) {
            for (TMProduct p : productRepo.getProducts()) {
                if (p.hasPackageProduct()) {
                    packageProductAvailable = true;
                    break;
                }
            }
        }
        return packageProductAvailable == null ? false : packageProductAvailable;
    }

    @Override
    public Double getCostOfInsurance(String productCode, Gender gender, int year) {
        return productRepo.getProduct(productCode).getCostOfInsurance(gender, year);
    }

}
