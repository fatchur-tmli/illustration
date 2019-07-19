package co.id.tmli.illustration.service.repo;

import static co.id.tmli.illustration.utils.Constants.SHEET_PROD_VAL;
import static co.id.tmli.illustration.excel.ExcelReadHelper.getCell;
import static co.id.tmli.illustration.excel.ExcelReadHelper.getCellValueDouble;
import static co.id.tmli.illustration.excel.ExcelReadHelper.getCellValueInt;
import static co.id.tmli.illustration.excel.ExcelReadHelper.getCellValueString;
import co.id.tmli.illustration.excel.ExcelUtil;
import co.id.tmli.illustration.model.ProductRuleValidation;
import co.id.tmli.illustration.utils.Cacher;
import co.id.tmli.illustration.utils.Helper;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ProductValidationRepoImpl implements ProductValidationRepo {

    private final Workbook workbook;

    private final Cacher<Map<String, ProductRuleValidation>> cacher = new Cacher<>();

    public ProductValidationRepoImpl(Workbook workbook) {
        this.workbook = workbook;
        cacher.setSupplier(this::loadProductValidationMap);
    }

    @Override
    public ProductRuleValidation getProductRuleValidation(String productCode) {
        return cacher.get().get(productCode);
    }

    /**
     *
     * @return Map<ProductCode, ProductRuleValidation>
     */
    private Map<String, ProductRuleValidation> loadProductValidationMap() {
        Sheet sheet = workbook.getSheet(SHEET_PROD_VAL);
        Map<String, ProductRuleValidation> map = new HashMap();
        // Validation Rule Data for UnitLink Product
        String b1 = getCellValueString(getCell(sheet, "B1"));
        if (b1 != null) {
            for (String d : b1.split(",")) {
                String e = ExcelUtil.getNextColumnName(d);
                String f = ExcelUtil.getNextColumnName(e);
                String g = ExcelUtil.getNextColumnName(f);
                String h = ExcelUtil.getNextColumnName(g);
                Integer h2 = getCellValueInt(getCell(sheet, h + "2"));
                Integer h3 = getCellValueInt(getCell(sheet, h + "3"));
                Integer h4 = getCellValueInt(getCell(sheet, h + "4"));
                Integer h5 = getCellValueInt(getCell(sheet, h + "5"));
                Double h8 = getCellValueDouble(getCell(sheet, h + "8"));
                Double h9 = Helper.parseSumInsuredExpression(getCellValueString(getCell(sheet, h + "9")));
                String h10 = getCellValueString(getCell(sheet, h + "10"));
                String h11 = getCellValueString(getCell(sheet, h + "11"));
                String h12 = getCellValueString(getCell(sheet, h + "12"));
                String h13 = getCellValueString(getCell(sheet, h + "13"));
                String h14 = getCellValueString(getCell(sheet, h + "14"));
                String h15 = getCellValueString(getCell(sheet, h + "15"));
                String h16 = getCellValueString(getCell(sheet, h + "16"));
                String h17 = getCellValueString(getCell(sheet, h + "17"));
                String h18 = getCellValueString(getCell(sheet, h + "18"));
                String h19 = getCellValueString(getCell(sheet, h + "19"));
                String h20 = getCellValueString(getCell(sheet, h + "20"));
                String h21 = getCellValueString(getCell(sheet, h + "21"));
                String h22 = getCellValueString(getCell(sheet, h + "22"));
                String h23 = getCellValueString(getCell(sheet, h + "23"));
                String h24 = getCellValueString(getCell(sheet, h + "24"));
                Double h24Double = Helper.parseSumInsuredExpression(h24);
                Integer h27 = getCellValueInt(getCell(sheet, h + "27"));
                Integer g28 = getCellValueInt(getCell(sheet, g + "28"));
                Integer h28 = getCellValueInt(getCell(sheet, h + "28"));

                ProductRuleValidation vrr = new ProductRuleValidation();
                String prod = getCellValueString(getCell(sheet, d + "1"));
                vrr.insuredMinEntryAge = h2;
                vrr.insuredMaxEntryAge = h3;
                vrr.policyHolderMaxEntryAge = h4;
                vrr.maxPolicyTerm = h5;
                vrr.minSinglePremium = h8;
                vrr.maxSinglePremium = h9;
                vrr.minSumAssuredExpression = h10;
                vrr.maxSumAssuredExpression = h11;
                vrr.minRegPremiAnnual = h12;
                vrr.minRegPremiSemester = h13;
                vrr.minRegPremiTriwulan = h14;
                vrr.minRegPremiMonthly = h15;
                vrr.maxRegPremiAnnual = h16;
                vrr.maxRegPremiSemester = h17;
                vrr.maxRegPremiTriwulan = h18;
                vrr.maxRegPremiMonthly = h19;
                vrr.minRegTopupPremiAnnual = h20;
                vrr.minRegTopupPremiSemester = h21;
                vrr.minRegTopupPremiTriwulan = h22;
                vrr.minRegTopupPremiMonthly = h23;
                vrr.ko = h24Double;
                vrr.printingValidationFirstLayer = h27;
                vrr.printingValidationSecondLayer2 = h28;
                vrr.printingValidationSecondLayer1 = g28;
//                ConfigurationService.getSingleton().addRuleValidation(prod, vrr);
                map.put(prod, vrr);
            }
        }

        // Validation Rule Data for Traditional Product
        String b40 = getCellValueString(getCell(sheet, "B40"));
        if (b40 != null) {
            for (String d : b40.split(",")) {
                String e = ExcelUtil.getNextColumnName(d);
                String f = ExcelUtil.getNextColumnName(e);
                String g = ExcelUtil.getNextColumnName(f);
                String h = ExcelUtil.getNextColumnName(g);
                Integer h41 = getCellValueInt(getCell(sheet, h + "41"));
                Integer h42 = getCellValueInt(getCell(sheet, h + "42"));
                Integer h43 = getCellValueInt(getCell(sheet, h + "43"));
                Integer h44 = getCellValueInt(getCell(sheet, h + "44"));
                String h45 = getCellValueString(getCell(sheet, h + "45"));
                String h46 = getCellValueString(getCell(sheet, h + "46"));
                ProductRuleValidation m = new ProductRuleValidation();
                m.insuredMinEntryAge = h41;
                m.insuredMaxEntryAge = h42;
                m.policyHolderMaxEntryAge = h43;
                m.maxPolicyTerm = h44;
                m.minSumAssuredExpression = h45;
                m.maxSumAssuredExpression = h46;

                String productCode = getCellValueString(getCell(sheet, d + "40"));
//                ConfigurationService.getSingleton().addRuleValidation(productCode, m);
                map.put(productCode, m);
            }
        }
        return map;
    }
}
