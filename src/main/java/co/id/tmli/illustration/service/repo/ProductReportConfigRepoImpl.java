package co.id.tmli.illustration.service.repo;

import static co.id.tmli.illustration.utils.Constants.E;
import static co.id.tmli.illustration.utils.Constants.F;
import static co.id.tmli.illustration.utils.Constants.PERNYATAAN;
import static co.id.tmli.illustration.utils.Constants.PRODUCT_NOTE;
import static co.id.tmli.illustration.utils.Constants.SHEET_REPORT;
import static co.id.tmli.illustration.excel.ExcelReadHelper.getCell;
import static co.id.tmli.illustration.excel.ExcelReadHelper.getCellValueInt;
import static co.id.tmli.illustration.excel.ExcelReadHelper.getCellValueString;
import co.id.tmli.illustration.utils.Cacher;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ProductReportConfigRepoImpl implements ProductReportConfigRepo {

    private final Workbook workbook;

    private final Cacher<Map<String, Map<String, String>>> cacher = new Cacher<>();

    public ProductReportConfigRepoImpl(Workbook workbook) {
        this.workbook = workbook;
        cacher.setSupplier(this::getProductReportConfigMap);
    }

    @Override
    public Map<String, String> getProductReportConfig(String productCode) {
        return cacher.get().get(productCode);
    }

    /**
     *
     * @return Map<ProductCode, Map<String, String>>
     */
    private Map<String, Map<String, String>> getProductReportConfigMap() {
        Sheet sheet = workbook.getSheet(SHEET_REPORT);
        Map<String, Map<String, String>> map = new HashMap();
        int rowstart = getCellValueInt(getCell(sheet, "A1")) - 1;
        int rowfinish = getCellValueInt(getCell(sheet, "B1"));
        for (int r = rowstart; r < rowfinish; r++) {
            String productCode = null;
            try {
                // C
                productCode = getCellValueString(sheet.getRow(r).getCell(2));
            } catch (NullPointerException e) {
            }
            if (productCode != null && !productCode.isEmpty()) {
                String h = getCellValueString(sheet.getRow(r).getCell(7));
                String i = getCellValueString(sheet.getRow(r).getCell(8));
                String j = getCellValueString(sheet.getRow(r).getCell(9));
                String k = getCellValueString(sheet.getRow(r).getCell(10));
                Map<String, String> reportConfig = new HashMap<>();
                reportConfig.put(E, h);
                reportConfig.put(F, i);
                reportConfig.put(PRODUCT_NOTE, j);
                reportConfig.put(PERNYATAAN, k);
//                ConfigurationService.getSingleton().setReportConfig(productCode, reportConfig);
                map.put(productCode, reportConfig);
            }
        }
        return map;
    }
}
