package co.id.tmli.illustration.model;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@SuppressWarnings("serial")
@lombok.Data
public class ProductItem implements Serializable {

    public static final String ITEM_TYPE_GROUP = "group";
    public static final String ITEM_TYPE_PRODUCT = "product";
    public static final String PRODUCT_GROUP_TRADITIONAL = "TRADITIONAL";
    public static final String PRODUCT_GROUP_UNITLINK = "UNITLINK";
    private final StringProperty itemTypeProperty = new SimpleStringProperty();
    private final StringProperty productGroupProperty = new SimpleStringProperty();
    private final StringProperty productCodeProperty = new SimpleStringProperty();
    private final StringProperty valueProperty = new SimpleStringProperty();
    private boolean syariahProduct;
    private String planCode;
    private String legalNumber;
    private PackageProduct packageProduct;

    public ProductItem(String itemType, String productGroup, boolean syariahProduct, String planCode, String legalNumber, String productCode, String productName) {
        setItemType(itemType);
        setProductGroup(productGroup);
        setSyariahProduct(syariahProduct);
        setPlanCode(planCode);
        setLegalNumber(legalNumber);
        setProductCode(productCode);
        setProductName(productName);
    }

    public static ProductItem createGroup(String groupName) {
        return new ProductItem(ProductItem.ITEM_TYPE_GROUP, null, false, null, null, null, groupName);
    }

    public static ProductItem createGroupUnitLink() {
        return createGroup(PRODUCT_GROUP_UNITLINK);
    }

    public static ProductItem createGroupTraditional() {
        return createGroup(PRODUCT_GROUP_TRADITIONAL);
    }

    public ProductItem createNewCopy() {
        return new ProductItem(getItemType(), getProductGroup(), syariahProduct, planCode, legalNumber, getProductCode(), getProductName());
    }

    public final void setItemType(String type) {
        getItemTypeProperty().set(type);
    }

    public final String getItemType() {
        return getItemTypeProperty().get();
    }

    public final void setProductName(String value) {
        getValueProperty().set(value);
    }

    public final String getProductName() {
        return getValueProperty().get();
    }

    public final void setProductGroup(String group) {
        getProductGroupProperty().set(group);
    }

    public final String getProductGroup() {
        return getProductGroupProperty().get();
    }

    public final void setProductCode(String code) {
        getProductCodeProperty().set(code);
    }

    public final String getProductCode() {
        return getProductCodeProperty().get();
    }

    @Override
    public String toString() {
        return getPackageProduct() != null ? getPackageProduct().getPackageName() : getProductName();
    }

}
