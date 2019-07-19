package co.id.tmli.illustration.service.repo;

import co.id.tmli.illustration.utils.Constants;
import static co.id.tmli.illustration.utils.Constants.SHEET_RIDER;
import co.id.tmli.illustration.excel.CellNavigator;
import static co.id.tmli.illustration.excel.ExcelReadHelper.getCell;
import static co.id.tmli.illustration.excel.ExcelReadHelper.getCellValueDouble;
import static co.id.tmli.illustration.excel.ExcelReadHelper.getCellValueInt;
import static co.id.tmli.illustration.excel.ExcelReadHelper.getCellValueString;
import co.id.tmli.illustration.excel.ExcelUtil;
import co.id.tmli.illustration.model.RangeData;
import co.id.tmli.illustration.model.Rider;
import co.id.tmli.illustration.model.RiderImpl;
import co.id.tmli.illustration.model.RiskRate;
import co.id.tmli.illustration.utils.Cacher;
import co.id.tmli.illustration.utils.Helper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RiderRepoImpl implements RiderRepo {

    private static final Logger logger = LoggerFactory.getLogger(RiderRepoImpl.class);

    private Workbook workbook;
    private final Cacher<List<Rider>> cacher = new Cacher<>();

    public RiderRepoImpl(Workbook workbook) {
        this.workbook = workbook;
        cacher.setSupplier(this::loadRiders);
    }

    @Override
    public List<Rider> getRiders() {
        return cacher.get();
    }

    private List<Rider> loadRiders() {
        Sheet sheet = workbook.getSheet(SHEET_RIDER);
        List<Rider> list = new ArrayList();
        int i = 4;
        String rider = getCellValueString(getCell(sheet, "A" + i));
        while (rider != null && !rider.trim().isEmpty()) {
            RiderImpl r = new RiderImpl();
            r.setGroupCode(rider);
            r.setRiderCode(getCellValueString(getCell(sheet, "B" + i)));
            logger.debug("[loadRiders]-{}", r.getRiderCode());
            r.setDesc(getCellValueString(getCell(sheet, "C" + i)));
            r.setSyariahProduct("V".equalsIgnoreCase(getCellValueString(getCell(sheet, "D" + i))));
            r.setCoveredAges(getCellValueInt(getCell(sheet, "E" + i)));
            r.setValidationForAdd(getCellValueString(getCell(sheet, "F" + i)));
            r.setMinInsuredEntryAge(getCellValueString(getCell(sheet, "G" + i)));
            r.setMinHolderEntryAge(getCellValueString(getCell(sheet, "H" + i)));
            r.setMaxInsuredEntryAge(getCellValueString(getCell(sheet, "I" + i)));
            r.setMaxHolderEntryAge(getCellValueString(getCell(sheet, "J" + i)));
            r.setMinBasicSA(Helper.parseSumInsuredExpression(getCellValueString(getCell(sheet, "K" + i))));
            r.setMaxSumInsured(Helper.parseSumInsuredExpression(getCellValueString(getCell(sheet, "L" + i))));
            r.setMinSA(Helper.parseSumInsuredExpression(getCellValueString(getCell(sheet, "M" + i))));
            r.setColN(getCellValueString(getCell(sheet, "N" + i)));
            r.setRateType(getCellValueString(getCell(sheet, "O" + i)));
            String colRate = getCellValueString(getCell(sheet, "P" + i));
            Integer rateFirstRow = getCellValueInt(getCell(sheet, "Q" + i));
            Integer rateLastRow = getCellValueInt(getCell(sheet, "R" + i));           
            
            if (Constants.RIDER_RATE_A.equals(r.getRateType())) {
                // Map<Age, Rate>
                Map<Integer, Double> riderRateMale = new TreeMap<>();
                Map<Integer, Double> riderRateFemale = new TreeMap<>();
                String colMale = ExcelUtil.getNextColumnName(colRate);
                String colFemale = ExcelUtil.getNextColumnName(colMale);
                for (int rownum = rateFirstRow; rownum <= rateLastRow; rownum++) {
                    Integer age = getCellValueInt(getCell(sheet, colRate + rownum));
                    if (age != null) {
                        Double maleRate = getCellValueDouble(getCell(sheet, colMale + rownum));
                        Double femaleRate = getCellValueDouble(getCell(sheet, colFemale + rownum));
                        riderRateMale.put(age, maleRate);
                        riderRateFemale.put(age, femaleRate);
                    }
                    
                }
                r.setRiderRateMaleA(riderRateMale);
                r.setRiderRateFemaleA(riderRateFemale);
            } else if (r.getRateType() != null && r.getRateType().startsWith(Constants.RIDER_RATE_B)) {
                // Map<Year, Map<Age, Rate>>
                Map<Integer, Map<Integer, Double>> rateMale = new TreeMap<>();
                int maxYear = Integer.parseInt(r.getRateType().split("_")[1].trim());
                String colYear = ExcelUtil.getNextColumnName(colRate);
                for (int year = 1; year <= maxYear; year++) {
                    Map<Integer, Double> rateMap = new TreeMap<>();
                    for (int rownum = rateFirstRow; rownum <= rateLastRow; rownum++) {
                        Integer age = getCellValueInt(getCell(sheet, colRate + rownum));
                        if (age != null) {
                            Double rate = getCellValueDouble(getCell(sheet, colYear + rownum));
                            rateMap.put(age, rate);
                        }
                    }
                    rateMale.put(year, rateMap);
                    colYear = ExcelUtil.getNextColumnName(colYear);
                }
                r.setRiderRateMaleB(rateMale);
                // rate for female
                Map<Integer, Map<Integer, Double>> rateFemale = new TreeMap<>();
                for (int year = 1; year <= maxYear; year++) {
                    Map<Integer, Double> rateMap = new TreeMap<>();
                    for (int rownum = rateFirstRow; rownum <= rateLastRow; rownum++) {
                        Integer age = getCellValueInt(getCell(sheet, colRate + rownum));
                        if (age != null) {
                            Double rate = getCellValueDouble(getCell(sheet, colYear + rownum));
                            rateMap.put(age, rate);
                        }
                    }
                    rateFemale.put(year, rateMap);
                    colYear = ExcelUtil.getNextColumnName(colYear);
                }
                r.setRiderRateFemaleB(rateFemale);
            } else if (Constants.RIDER_RATE_C.equals(r.getRateType())) {
                List<RiskRate> riskRateList = new ArrayList();
                CellNavigator cn = new CellNavigator(sheet, colRate + rateFirstRow);
                Integer ageFrom = cn.nextRow().getCellValueAsInteger();
                Integer ageTo = cn.nextCol().getCellValueAsInteger();
                cn.gotoInitColumn().nextRow().nextRow();
                while (cn.hasCell()) {
                    Integer riskClass = cn.getCellValueAsInteger();
                    Double rate = cn.nextCol().getCellValueAsDouble();
                    RiskRate riskRate = new RiskRate();
                    riskRate.setAgeRange(new RangeData<>(ageFrom, ageTo));
                    riskRate.setRisk(riskClass);
                    riskRate.setRate(rate);
                    riskRateList.add(riskRate);
                    logger.debug("[loadRiders]-rider={}, riskRate={}", r.getRiderCode(), riskRate);
                    cn.gotoInitColumn().nextRow();
                }
                r.setRiskRates(riskRateList);
            }

            r.setCorType(getCellValueString(getCell(sheet, "S" + i)));
            r.setCorFormula(getCellValueString(getCell(sheet, "T" + i)));
            r.setRiderSA(getCellValueString(getCell(sheet, "U" + i)));
            r.setIllustrationOutput(getCellValueString(getCell(sheet, "V" + i)));
            r.setDescKolomW(getCellValueString(getCell(sheet, "W" + i)));
            r.setDescShort(getCellValueString(getCell(sheet, "X" + i)));
            r.setDescLong(getCellValueString(getCell(sheet, "Y" + i)));
            r.setReleasedDate(getCellValueString(getCell(sheet, "AA" + i)));
            r.setCurrency(getCellValueString(getCell(sheet, "AB" + i)));
            r.setBundledProduct(getCellValueString(getCell(sheet, "AC" + i)));

            list.add(r);

            i++;
            rider = getCellValueString(getCell(sheet, "A" + i));
        }
        return list;
    }

}
