/**
 * 
 */
package co.id.tmli.illustration.service.repo;

import co.id.tmli.illustration.excel.CellNavigator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Workbook;
import static co.id.tmli.illustration.utils.Constants.SHEET_FUND;
import co.id.tmli.illustration.model.Fund;
import co.id.tmli.illustration.model.FundImpl;
import co.id.tmli.illustration.utils.Cacher;
import co.id.tmli.illustration.utils.Constants;
import co.id.tmli.illustration.utils.CurrencyEnum;
import java.util.Arrays;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.Sheet;

public class FundRepoImpl implements FundRepo, Serializable {
        
    private final Workbook workbook;    

    private final Cacher<Map<Integer, Double>> fundGrowthValidationMapCacher = new Cacher<>();
    private final Cacher<List<Fund>> fundsCacher = new Cacher<>();
    private final Cacher<Map<String, List<Fund>>> productFundsCacher = new Cacher();

    public FundRepoImpl(Workbook workbook) {        
        this.workbook = workbook;
        fundGrowthValidationMapCacher.setSupplier(this::loadFundGrowthValidationMap);
        fundsCacher.setSupplier(this::loadFunds);
        productFundsCacher.setSupplier(this::loadProductFunds);
    }

    /**
     *
     * @return Map<PolicyYear, Growthrate>
     */
    private Map<Integer, Double> loadFundGrowthValidationMap() {        
        Sheet sheet = workbook.getSheet(SHEET_FUND);
        // Map<PolicyYear, GrowthRate>
        Map<Integer, Double> m = new HashMap<>();
        CellNavigator cn = new CellNavigator(sheet, "C3");
        while (cn.hasCell()) {
            Integer policyYear = cn.getCellValueAsInteger();
            Double fundGrowthRate = cn.nextCol().getCellValueAsDouble();
            m.put(policyYear, fundGrowthRate);
            cn.nextRow().gotoInitColumn();
        }
        return m;
    }

    @Override
    public Double getFundGrowthValidation(Constants.Period p, Integer policyYear) {
        switch (p) {
            case Annual:
                return 1 + fundGrowthValidationMapCacher.get().get(policyYear);
            case Monthly:
                return 1 + fundGrowthValidationMapCacher.get().get(policyYear) / 12;
        }
        return null;
    }

    private List<Fund> loadFunds() {
        List<Fund> list = new ArrayList();
        CellNavigator cn = new CellNavigator(workbook.getSheet(SHEET_FUND), "F3");
        while (cn.hasCell()) {
            String fundName = cn.getCellValueAsString();
            String currency = cn.nextCol().getCellValueAsString();
            Double low = cn.nextCol().getCellValueAsDouble();
            Double med = cn.nextCol().getCellValueAsDouble();
            Double hig = cn.nextCol().getCellValueAsDouble();

            Double[] performanceList = {
                cn.nextCol().getCellValueAsDouble(),
                cn.nextCol().getCellValueAsDouble(),
                cn.nextCol().getCellValueAsDouble(),
                cn.nextCol().getCellValueAsDouble(),
                cn.nextCol().getCellValueAsDouble(),
                cn.nextCol().getCellValueAsDouble()
            };

            String noteCode = cn.nextCol().getCellValueAsString();

            cn.nextRow().gotoInitColumn();

            CurrencyEnum curr = CurrencyEnum.IDR.name().equals(currency)
                    ? CurrencyEnum.IDR : CurrencyEnum.USD;

            FundImpl f = new FundImpl();
            f.setCode(fundName);
            f.setDesc(fundName);
            f.setCurrency(curr);
            f.setGrowthAsumptionLow(low);
            f.setGrowthAsumptionMedium(med);
            f.setGrowthAsumptionHigh(hig);
            f.setFundPerformanceYears(Arrays.asList(performanceList));
            f.setNoteCode(noteCode);
            list.add(f);
        }
        return list;
    }

    @Override
    public List<Fund> getFunds() {
        return fundsCacher.get();
    }

    @Override
    public List<Fund> getFundsByProductCode(String productCode) {
        return productFundsCacher.get().get(productCode);
    }

    private Map<String, List<Fund>> loadProductFunds() {
        Map<String, List<Fund>> map = new HashMap<>();

        CellNavigator cn = new CellNavigator(workbook.getSheet(SHEET_FUND), "S2");
        while (cn.hasCell()) {
            String productCode = cn.getCellValueAsString();
            List<Fund> list = new ArrayList();
            for (Fund fund : fundsCacher.get()) {
                if ("V".equalsIgnoreCase(cn.nextRow().getCellValueAsString())) {
                    list.add(fund);
                }
            }
            map.put(productCode, list);
            cn.nextCol().gotoInitRow();
        }

        return map;
    }    
    
}
