package co.id.tmli.illustration.service.repo;

import static co.id.tmli.illustration.utils.Constants.SHEET_FEATURES;
import co.id.tmli.illustration.excel.CellNavigator;
import static co.id.tmli.illustration.excel.ExcelReadHelper.getCell;
import static co.id.tmli.illustration.excel.ExcelReadHelper.getCellValueDouble;
import static co.id.tmli.illustration.excel.ExcelReadHelper.getCellValueInt;
import static co.id.tmli.illustration.excel.ExcelReadHelper.getCellValueString;
import co.id.tmli.illustration.excel.ExcelUtil;
import co.id.tmli.illustration.model.CostOfInsurance;
import co.id.tmli.illustration.model.GenderValue;
import co.id.tmli.illustration.utils.Cacher;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ProductFeatureRepoImpl implements ProductFeatureRepo {

    private final Workbook workbook;

    private final Cacher<Map<String, Map<Integer, Double>>> scheduleWithdrawalCacher = new Cacher<>();
    private final Cacher<Map<String, Map<Integer, Double>>> withdrawalChargeCacher = new Cacher<>();
    private final Cacher<Map<String, Map<Integer, Double>>> stuAllocationCacher = new Cacher<>();
    private final Cacher<Map<String, Map<Integer, Double>>> regularTopUpAllocationCacher = new Cacher<>();
    private final Cacher<Map<String, Map<Integer, Double>>> premiumAllocationCacher = new Cacher<>();
    private final Cacher<Map<String, List<CostOfInsurance>>> costOfInsuranceCacher = new Cacher<>();
    private final Cacher<Map<String, Map<Integer, Double>>> surrenderChargeCacher = new Cacher<>();
    private final Cacher<Map<String, Map<Integer, Double>>> premiumHolidayChargeCacher = new Cacher<>();
    private final Cacher<Map<String, Map<Integer, Double>>> serviceChargeCacher = new Cacher<>();
    private final Cacher<Map<String, Map<Integer, Double[]>>> adminFeeCacher = new Cacher<>();
    private final Cacher<Map<String, Map<Integer, Double>>> loyaltyBonusCacher = new Cacher<>();
    private final Cacher<Map<String, Map<Integer, Double>>> extraAllocationCacher = new Cacher<>();

    public ProductFeatureRepoImpl(Workbook workbook) {
        this.workbook = workbook;
        scheduleWithdrawalCacher.setSupplier(this::loadScheduleWithdrawalMap);
        withdrawalChargeCacher.setSupplier(this::loadWithdrawalChargeMap);
        stuAllocationCacher.setSupplier(this::loadStuAllocationMap);
        regularTopUpAllocationCacher.setSupplier(this::loadRegularTopUpAllocationMap);
        premiumAllocationCacher.setSupplier(this::loadPremiumAllocationMap);
        costOfInsuranceCacher.setSupplier(this::loadCostOfInsurance);
        surrenderChargeCacher.setSupplier(this::loadSurrenderChargeMap);
        premiumHolidayChargeCacher.setSupplier(this::loadPremiumHolidayChargeMap);
        serviceChargeCacher.setSupplier(this::loadServiceChargeMap);
        adminFeeCacher.setSupplier(this::loadAdminFeeMap);
        loyaltyBonusCacher.setSupplier(this::loadLoyaltyBonusMap);
        extraAllocationCacher.setSupplier(this::loadExtraAllocationMap);
    }

    /**
     *
     * @param productCode productCode
     * @return Map<ProductCode, Map<Year, Percent>>
     */
    @Override
    public Map<Integer, Double> getScheduleWithdrawalMap(String productCode) {
        return scheduleWithdrawalCacher.get().get(productCode);
    }

    /**
     *
     * @return Map<ProductCode, Map<Year, Percent>>
     */
    private Map<String, Map<Integer, Double>> loadScheduleWithdrawalMap() {
        Sheet sheet = workbook.getSheet(SHEET_FEATURES);
        // Map<ProductCode, Map<Year, Percent>>
        Map<String, Map<Integer, Double>> a = new TreeMap<>();
        String b = getCellValueString(getCell(sheet, "B13"));
        String c = ExcelUtil.getNextColumnName(b);
        int d = getCellValueInt(getCell(sheet, "C13"));
        int e = getCellValueInt(getCell(sheet, "D13")) + 1;
        int f = d;
        while (getCellValueString(getCell(sheet, c + f)) != null) {
            String productCode = getCellValueString(getCell(sheet, c + f));
            Map<Integer, Double> h = new TreeMap<>();
            for (int i = d + 1; i < e; i++) {
                Integer year = getCellValueInt(getCell(sheet, b + i));
                if (year != null) {
                    h.put(year, getCellValueDouble(getCell(sheet, c + i)));
                }
            }
            a.put(productCode, h);
            c = ExcelUtil.getNextColumnName(c);
        }
//        ConfigurationService.getSingleton().setScheduleWithdrawalMap(a);
        return a;
    }

    @Override
    public Map<Integer, Double> getWithdrawalChargeMap(String productCode) {
        return withdrawalChargeCacher.get().get(productCode);
    }

    private Map<String, Map<Integer, Double>> loadWithdrawalChargeMap() {
        Sheet sheet = workbook.getSheet(SHEET_FEATURES);
        Map<String, Map<Integer, Double>> a = new TreeMap<>();
        String b = getCellValueString(getCell(sheet, "B11"));
        String c = ExcelUtil.getNextColumnName(b);
        int d = getCellValueInt(getCell(sheet, "C11"));
        int e = getCellValueInt(getCell(sheet, "D11")) + 1;
        int f = d;
        while (getCellValueString(getCell(sheet, c + f)) != null) {
            String g = getCellValueString(getCell(sheet, c + f));
            Map<Integer, Double> h = new TreeMap<>();
            for (int i = d + 1; i < e; i++) {
                Integer j = getCellValueInt(getCell(sheet, b + i));
                if (j != null) {
                    h.put(j, getCellValueDouble(getCell(sheet, c + i)));
                }
            }
            a.put(g, h);
            c = ExcelUtil.getNextColumnName(c);
        }
//        ConfigurationService.getSingleton().setWithdrawalChargeMap(a);
        return a;
    }

    /**
     *
     * @param productCode
     * @return Map<ProductCode, Map<Year, Double>>
     */
    @Override
    public Map<Integer, Double> getStuAllocationMap(String productCode) {
        return stuAllocationCacher.get().get(productCode);
    }

    private Map<String, Map<Integer, Double>> loadStuAllocationMap() {
        Sheet sheet = workbook.getSheet(SHEET_FEATURES);
        // Map<ProductCode, Map<Year, Double>>
        Map<String, Map<Integer, Double>> a = new TreeMap<>();
        String b = getCellValueString(getCell(sheet, "B9"));
        String c = ExcelUtil.getNextColumnName(b);
        int d = getCellValueInt(getCell(sheet, "C9"));
        int e = getCellValueInt(getCell(sheet, "D9")) + 1;
        int f = d;
        while (getCellValueString(getCell(sheet, c + f)) != null) {
            String productCode = getCellValueString(getCell(sheet, c + f));
            Map<Integer, Double> h = new TreeMap<>();
            for (int i = d + 1; i < e; i++) {
                Integer year = getCellValueInt(getCell(sheet, b + i));
                if (year != null) {
                    h.put(year, getCellValueDouble(getCell(sheet, c + i)));
                }
            }
            a.put(productCode, h);
            c = ExcelUtil.getNextColumnName(c);
        }
//        ConfigurationService.getSingleton().setStuAllocationMap(a);
        return a;
    }

    @Override
    public Map<Integer, Double> getRegularTopUpAllocationMap(String productCode) {
        return regularTopUpAllocationCacher.get().get(productCode);
    }

    private Map<String, Map<Integer, Double>> loadRegularTopUpAllocationMap() {
        Sheet sheet = workbook.getSheet(SHEET_FEATURES);
        Map<String, Map<Integer, Double>> a = new TreeMap<>();
        // b3 = "J"
        String b3 = getCellValueString(getCell(sheet, "B3"));
        // c = "K"
        String k = ExcelUtil.getNextColumnName(b3);
        // c3 = 109
        int c3 = getCellValueInt(getCell(sheet, "C3"));
        // d3 = 208 + 1
        int d3 = getCellValueInt(getCell(sheet, "D3")) + 1;
        // col K...next
        while (getCellValueString(getCell(sheet, k + c3)) != null) {
            String productCode = getCellValueString(getCell(sheet, k + c3));
            Map<Integer, Double> productRTU = new TreeMap<>();
            // K110:K208
            for (int i = c3 + 1; i < d3; i++) {
                Integer year = getCellValueInt(getCell(sheet, b3 + i));
                if (year != null) {
                    productRTU.put(year, getCellValueDouble(getCell(sheet, k + i)));
                }
            }
            a.put(productCode, productRTU);
            k = ExcelUtil.getNextColumnName(k);
        }
//        ConfigurationService.getSingleton().setRegularTopUpAllocationPercentMap(a);
        return a;
    }

    @Override
    public Map<Integer, Double> getPremiumAllocationMap(String productCode) {
        return premiumAllocationCacher.get().get(productCode);
    }

    private Map<String, Map<Integer, Double>> loadPremiumAllocationMap() {
        Sheet sheet = workbook.getSheet(SHEET_FEATURES);
        Map<String, Map<Integer, Double>> a = new TreeMap<>();
        // b2 = "J"
        String b2 = getCellValueString(getCell(sheet, "B2"));
        // k = "K"
        String k = ExcelUtil.getNextColumnName(b2);
        // c2 = 7
        int c2 = getCellValueInt(getCell(sheet, "C2"));
        // d2 = 106 + 1
        int d2 = getCellValueInt(getCell(sheet, "D2")) + 1;
        // col K .... 
        while (getCellValueString(getCell(sheet, k + c2)) != null) {
            String productCode = getCellValueString(getCell(sheet, k + c2));
            Map<Integer, Double> premiumAlloc = new TreeMap<>();
            // K8:K106
            for (int i = c2 + 1; i < d2; i++) {
                Integer year = getCellValueInt(getCell(sheet, b2 + i));
                if (year != null) {
                    premiumAlloc.put(year, getCellValueDouble(getCell(sheet, k + i)));
                }
            }
            a.put(productCode, premiumAlloc);
            k = ExcelUtil.getNextColumnName(k);
        }
//        ConfigurationService.getSingleton().setPremiumAllocationMap(a);
        return a;
    }

    @Override
    public List<CostOfInsurance> getCostOfInsurances(String productCode) {
        return costOfInsuranceCacher.get().get(productCode);
    }

    private Map<String, List<CostOfInsurance>> loadCostOfInsurance() {
        Sheet sheet = workbook.getSheet(SHEET_FEATURES);
        // Map<productCode, List<CostOfInsurance>>
        Map<String, List<CostOfInsurance>> coiMap = new TreeMap<>();
        CellNavigator i = new CellNavigator(sheet, "B1");
        String colStart = i.getCellValueAsString();
        int rowStart = i.nextCol().getCellValueAsInteger();
        int rowFinish = i.nextCol().getCellValueAsInteger();

        i = new CellNavigator(sheet, colStart + rowStart);
        String productCode = i.nextCol().getCellValueAsString();
        while (productCode != null) {
            int prodCol = i.getColumnIndex();
            List<CostOfInsurance> list = new ArrayList<>();
            for (int rownum = rowStart + 1; rownum < rowFinish; rownum++) {
                i.setRow(rownum);
                Integer age = i.gotoInitColumn().getCellValueAsInteger();
                Double maleVal = i.setCol(prodCol).getCellValueAsDouble();
                Double femaleVal = i.nextCol().getCellValueAsDouble();
                CostOfInsurance coi = new CostOfInsurance(age, new GenderValue<>(maleVal, femaleVal));
                list.add(coi);
            }
            coiMap.put(productCode, list);

            i.nextCol().gotoInitRow();
            productCode = i.getCellValueAsString();
        }
        return coiMap;
    }

    @Override
    public Map<Integer, Double> getSurrenderChargeMap(String productCode) {
        return surrenderChargeCacher.get().get(productCode);
    }

    private Map<String, Map<Integer, Double>> loadSurrenderChargeMap() {
        Sheet sheet = workbook.getSheet(SHEET_FEATURES);
        // Map<ProductCode, Map<Year, Double>>
        Map<String, Map<Integer, Double>> a = new TreeMap<>();
        String b = getCellValueString(getCell(sheet, "B8"));
        String c = ExcelUtil.getNextColumnName(b);
        int d = getCellValueInt(getCell(sheet, "C8"));
        int e = getCellValueInt(getCell(sheet, "D8")) + 1;
        int f = d;
        while (getCellValueString(getCell(sheet, c + f)) != null) {
            String productCode = getCellValueString(getCell(sheet, c + f));
            Map<Integer, Double> h = new TreeMap<>();
            for (int i = d + 1; i < e; i++) {
                Integer year = getCellValueInt(getCell(sheet, b + i));
                if (year != null) {
                    h.put(year, getCellValueDouble(getCell(sheet, c + i)));
                }
            }
            a.put(productCode, h);
            c = ExcelUtil.getNextColumnName(c);
        }
//        ConfigurationService.getSingleton().setSurrenderChargeMap(a);
        return a;
    }

    @Override
    public Map<Integer, Double> getPremiumHolidayChargeMap(String productCode) {
        return premiumHolidayChargeCacher.get().get(productCode);
    }

    private Map<String, Map<Integer, Double>> loadPremiumHolidayChargeMap() {
        Sheet sheet = workbook.getSheet(SHEET_FEATURES);
        // Map<ProductCode, Map<Year, Double>>
        Map<String, Map<Integer, Double>> a = new TreeMap<>();
        String b7 = getCellValueString(getCell(sheet, "B7"));
        String k = ExcelUtil.getNextColumnName(b7);
        int c7 = getCellValueInt(getCell(sheet, "C7"));
        int d7 = getCellValueInt(getCell(sheet, "D7")) + 1;
        while (getCellValueString(getCell(sheet, k + c7)) != null) {
            String productCode = getCellValueString(getCell(sheet, k + c7));
            Map<Integer, Double> phcMap = new TreeMap<>();
            for (int i = c7 + 1; i < d7; i++) {
                Integer year = getCellValueInt(getCell(sheet, b7 + i));
                if (year != null) {
                    phcMap.put(year, getCellValueDouble(getCell(sheet, k + i)));
                }
            }
            a.put(productCode, phcMap);
            k = ExcelUtil.getNextColumnName(k);
        }
//        ConfigurationService.getSingleton().setPremiumHolidayChargeMap(a);
        return a;
    }

    @Override
    public Map<Integer, Double> getServiceChargeMap(String productCode) {
        return serviceChargeCacher.get().get(productCode);
    }

    private Map<String, Map<Integer, Double>> loadServiceChargeMap() {
        Sheet sheet = workbook.getSheet(SHEET_FEATURES);
        Map<String, Map<Integer, Double>> a = new TreeMap<>();
        String b6 = getCellValueString(getCell(sheet, "B6"));
        String k = ExcelUtil.getNextColumnName(b6);
        int c6 = getCellValueInt(getCell(sheet, "C6"));
        int d6 = getCellValueInt(getCell(sheet, "D6")) + 1;
        while (getCellValueString(getCell(sheet, k + c6)) != null) {
            String productCode = getCellValueString(getCell(sheet, k + c6));
            Map<Integer, Double> scMap = new TreeMap<>();
            for (int i = c6 + 1; i < d6; i++) {
                Integer year = getCellValueInt(getCell(sheet, b6 + i));
                if (year != null) {
                    scMap.put(year, getCellValueDouble(getCell(sheet, k + i)));
                }
            }
            a.put(productCode, scMap);
            k = ExcelUtil.getNextColumnName(k);
        }
//        ConfigurationService.getSingleton().setServiceChargeMap(a);
        return a;
    }

    @Override
    public Map<Integer, Double[]> getAdminFeeMap(String productCode) {
        return adminFeeCacher.get().get(productCode);
    }

    private Map<String, Map<Integer, Double[]>> loadAdminFeeMap() {
        Sheet sheet = workbook.getSheet(SHEET_FEATURES);
        // Map<ProductCode, Map<Year, Double[idr,usd]>>
        Map<String, Map<Integer, Double[]>> a = new TreeMap<>();
        // b5 = "J"
        String b5 = getCellValueString(getCell(sheet, "B5"));
        // k = "K"
        String k = ExcelUtil.getNextColumnName(b5);
        // c5 = 313
        int c5 = getCellValueInt(getCell(sheet, "C5"));
        // d5 = 412 + 1
        int d5 = getCellValueInt(getCell(sheet, "D5")) + 1;
        while (getCellValueString(getCell(sheet, k + c5)) != null) {
            String productCode = getCellValueString(getCell(sheet, k + c5));
            Map<Integer, Double[]> h = new TreeMap<>();
            for (int i = c5 + 1; i < d5; i++) {
                Integer year = getCellValueInt(getCell(sheet, b5 + i));
                if (year != null) {
                    String fee = getCellValueString(getCell(sheet, k + i));
                    if (fee != null) {
                        String[] fees = fee.split("_");
                        Double idr = Double.parseDouble(fees[0]);
                        Double usd = Double.parseDouble(fees[1]);
                        h.put(year, new Double[]{idr, usd});
                    }
                }
            }
            a.put(productCode, h);
            k = ExcelUtil.getNextColumnName(k);
        }
//        ConfigurationService.getSingleton().setAdminFeeMap(a);
        return a;
    }

    @Override
    public Map<Integer, Double> getLoyaltyBonusMap(String productCode) {
        return loyaltyBonusCacher.get().get(productCode);
    }

    private Map<String, Map<Integer, Double>> loadLoyaltyBonusMap() {
        Sheet sheet = workbook.getSheet(SHEET_FEATURES);
        // Map<ProductCode, Map<Year, Double>> 
        Map<String, Map<Integer, Double>> map = new TreeMap<>();
        // b4 = "J"
        String b4 = getCellValueString(getCell(sheet, "B4"));
        // k = "K"
        String k = ExcelUtil.getNextColumnName(b4);
        // c4 = 211
        int c4 = getCellValueInt(getCell(sheet, "C4"));
        // d4 = 310 + 1
        int d4 = getCellValueInt(getCell(sheet, "D4")) + 1;
        while (getCellValueString(getCell(sheet, k + c4)) != null) {
            String productCode = getCellValueString(getCell(sheet, k + c4));
            Map<Integer, Double> productLoyaltyBonus = new TreeMap<>();
            for (int i = c4 + 1; i < d4; i++) {
                Integer year = getCellValueInt(getCell(sheet, b4 + i));
                if (year != null) {
                    productLoyaltyBonus.put(year, getCellValueDouble(getCell(sheet, k + i)));
                }
            }
            map.put(productCode, productLoyaltyBonus);
            k = ExcelUtil.getNextColumnName(k);
        }
//        ConfigurationService.getSingleton().setLoyaltyBonusMap(a);
        return map;
    }
    
    private Map<String, Map<Integer, Double>> loadExtraAllocationMap() {
        Sheet sheet = workbook.getSheet(SHEET_FEATURES);
        Map<String, Map<Integer, Double>> map = new TreeMap<>();
        // b4 = "J"
        String b4 = getCellValueString(getCell(sheet, "B16"));
        // k = "K"
        String k = ExcelUtil.getNextColumnName(b4);
        // c4 = 211
        int c4 = getCellValueInt(getCell(sheet, "C16"));
        // d4 = 310 + 1
        int d4 = getCellValueInt(getCell(sheet, "D16")) + 1;
        while (getCellValueString(getCell(sheet, k + c4)) != null) {
            String productCode = getCellValueString(getCell(sheet, k + c4));
            Map<Integer, Double> productLoyaltyBonus = new TreeMap<>();
            for (int i = c4 + 1; i < d4; i++) {
                Integer year = getCellValueInt(getCell(sheet, b4 + i));
                if (year != null) {
                    productLoyaltyBonus.put(year, getCellValueDouble(getCell(sheet, k + i)));
                }
            }
            map.put(productCode, productLoyaltyBonus);
            k = ExcelUtil.getNextColumnName(k);
        }        
        return map;
    }

}
