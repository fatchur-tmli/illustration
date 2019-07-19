package co.id.tmli.illustration.service.repo;

import static co.id.tmli.illustration.excel.ExcelReadHelper.getCell;
import static co.id.tmli.illustration.excel.ExcelReadHelper.getCellValueInt;
import static co.id.tmli.illustration.excel.ExcelReadHelper.getCellValueString;
import co.id.tmli.illustration.model.ProductItem;
import co.id.tmli.illustration.model.TMProduct;
import co.id.tmli.illustration.utils.Cacher;
import co.id.tmli.illustration.utils.Constants;
import co.id.tmli.illustration.utils.CurrencyEnum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductRepoImpl implements ProductRepo {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepoImpl.class);

    private final Workbook workbook;
    @lombok.Setter
    private ProductValidationRepo productValidationRepo;
    @lombok.Setter
    private ProductReportConfigRepo productReportConfigRepo;
    @lombok.Setter
    private ProductPackageRepo productPackageRepo;
    @lombok.Setter
    private FundRepo fundRepo;
    @lombok.Setter
    private ProductFeatureRepo productFeatureRepo;

    private final Cacher<List<TMProduct>> cacher = new Cacher<>();

    public ProductRepoImpl(Workbook workbook) {
        this.workbook = workbook;
        cacher.setSupplier(this::loadProducts);
    }

    @Override
    public TMProduct getProduct(String productCode) {
        for (TMProduct p : getProducts()) {
            if (p.getProductItem().getProductCode().equals(productCode)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<TMProduct> getProducts() {
        return cacher.get();
    }

    private List<TMProduct> loadProducts() {
        List<TMProduct> list = loadProductSheet();
        for (TMProduct tmpd : list) {
            String productCode = tmpd.getProductItem().getProductCode();
            tmpd.setReportConfig(productReportConfigRepo.getProductReportConfig(productCode));
            tmpd.setPackageProductList(productPackageRepo.getPackageProducts(productCode));
            tmpd.setRuleValidation(productValidationRepo.getProductRuleValidation(productCode));
            tmpd.setFundConfigList(fundRepo.getFundsByProductCode(productCode));
            tmpd.setAdminFeeMap(productFeatureRepo.getAdminFeeMap(productCode));
            tmpd.setLoyaltyBonusMap(productFeatureRepo.getLoyaltyBonusMap(productCode));
            tmpd.setPremiumAllocationMap(productFeatureRepo.getPremiumAllocationMap(productCode));
            tmpd.setPremiumHolidayChargeMap(productFeatureRepo.getPremiumHolidayChargeMap(productCode));
            tmpd.setRegularTopUpAllocationMap(productFeatureRepo.getRegularTopUpAllocationMap(productCode));
            tmpd.setScheduleWithdrawalMap(productFeatureRepo.getScheduleWithdrawalMap(productCode));
            tmpd.setServiceChargeMap(productFeatureRepo.getServiceChargeMap(productCode));
            tmpd.setStuAllocationMap(productFeatureRepo.getStuAllocationMap(productCode));
            tmpd.setSurrenderChargeMap(productFeatureRepo.getSurrenderChargeMap(productCode));
            tmpd.setWithdrawalChargeMap(productFeatureRepo.getWithdrawalChargeMap(productCode));
            tmpd.setCostOfInsurances(productFeatureRepo.getCostOfInsurances(productCode));            
        }
        return list;
    }

    private List<TMProduct> loadProductSheet() {
        Sheet sheet = workbook.getSheet(Constants.SHEET_PRODUCT);
        List<TMProduct> list = new ArrayList();
        int b1 = getCellValueInt(getCell(sheet, "B1"));
        int c1 = getCellValueInt(getCell(sheet, "C1"));
        for (int rownum = b1 - 1; rownum < c1; rownum++) {
            // itemType
            String b = null;
            try {
                b = getCellValueString(sheet.getRow(rownum).getCell(1));
            } catch (NullPointerException e) {
            }
            if (ProductItem.ITEM_TYPE_PRODUCT.equals(b)) {
                // productGroup
                String c = getCellValueString(sheet.getRow(rownum).getCell(2));
                // syariahProduct
                String d = getCellValueString(sheet.getRow(rownum).getCell(3));
                // planCode
                String e = getCellValueString(sheet.getRow(rownum).getCell(4));
                // legalNumber
                String f = getCellValueString(sheet.getRow(rownum).getCell(5));
                // col g = productCode
                String productCode = getCellValueString(sheet.getRow(rownum).getCell(6));
                // productName
                String h = getCellValueString(sheet.getRow(rownum).getCell(7));

                ProductItem item = new ProductItem(b, c, "V".equalsIgnoreCase(d), e, f, productCode, h);
                TMProduct tmpd = new TMProduct(item);
                List<CurrencyEnum> currList = new ArrayList<>();
                String i = getCellValueString(sheet.getRow(rownum).getCell(8));
                if (i != null) {
                    currList.add(CurrencyEnum.IDR);
                }
                String j = getCellValueString(sheet.getRow(rownum).getCell(9));
                if (j != null) {
                    currList.add(CurrencyEnum.USD);
                }
                tmpd.setCurrencyList(currList);

                List<Constants.PaymentMode> paymentModeList = new ArrayList<>();
                String k = getCellValueString(sheet.getRow(rownum).getCell(10));
                if (k != null) {
                    paymentModeList.add(Constants.PaymentMode.Single);
                }
                String l = getCellValueString(sheet.getRow(rownum).getCell(11));
                if (l != null) {
                    paymentModeList.add(Constants.PaymentMode.Year);
                }
                String m = getCellValueString(sheet.getRow(rownum).getCell(12));
                if (m != null) {
                    paymentModeList.add(Constants.PaymentMode.Semester);
                }
                String n = getCellValueString(sheet.getRow(rownum).getCell(13));
                if (n != null) {
                    paymentModeList.add(Constants.PaymentMode.Quarter);
                }
                String o = getCellValueString(sheet.getRow(rownum).getCell(14));
                if (o != null) {
                    paymentModeList.add(Constants.PaymentMode.Month);
                }
                tmpd.setPaymentModeList(paymentModeList);

                String p = getCellValueString(sheet.getRow(rownum).getCell(15));
                tmpd.setCurrencyPremiumPolicyTerm(p);

                String q = getCellValueString(sheet.getRow(rownum).getCell(16));
                tmpd.setInputSaRegularPremium(q);

                String r = getCellValueString(sheet.getRow(rownum).getCell(17));
                tmpd.setInputPremiumTopUp(r);

                String s = getCellValueString(sheet.getRow(rownum).getCell(18));
                tmpd.setTotalPremium(s);

                String t = getCellValueString(sheet.getRow(rownum).getCell(19));
                tmpd.setSumAssured(t);

                String u = getCellValueString(sheet.getRow(rownum).getCell(20));
                tmpd.setPaymentFrequency(u);

                String v = getCellValueString(sheet.getRow(rownum).getCell(21));
                tmpd.setPremiumTerm(v);

                String w = getCellValueString(sheet.getRow(rownum).getCell(22));
                tmpd.setTypeOfPlanForTmWellBeing(w);

                String x = getCellValueString(sheet.getRow(rownum).getCell(23));
                tmpd.setCol23(x);

                String y = getCellValueString(sheet.getRow(rownum).getCell(24));
                tmpd.setRiskClassConfig(y);

                String z = getCellValueString(sheet.getRow(rownum).getCell(25));
                if (z != null) {
                    tmpd.setRiders(Arrays.asList(z.split(",")));
                }               

                list.add(tmpd);
            }
        }
        return list;
    }

}
