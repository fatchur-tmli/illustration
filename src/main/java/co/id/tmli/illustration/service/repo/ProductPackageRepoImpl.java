package co.id.tmli.illustration.service.repo;

import static co.id.tmli.illustration.utils.Constants.SHEET_PACKAGE_PRODUCT;
import co.id.tmli.illustration.excel.CellNavigator;
import co.id.tmli.illustration.model.PackageProduct;
import co.id.tmli.illustration.utils.Cacher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ProductPackageRepoImpl implements ProductPackageRepo {

    private final Workbook workbook;

    private final Cacher<Map<String, List<PackageProduct>>> cacher = new Cacher<>();

    public ProductPackageRepoImpl(Workbook workbook) {
        this.workbook = workbook;
        cacher.setSupplier(this::loadPackageProductsMap);
    }

    @Override
    public List<PackageProduct> getPackageProducts(String productCode) {
        return cacher.get().get(productCode);
    }

    private Map<String, List<PackageProduct>> loadPackageProductsMap() {
        Sheet sheet = workbook.getSheet(SHEET_PACKAGE_PRODUCT);
        Map<String, List<PackageProduct>> map = new HashMap<>();
        if (sheet != null) {
            CellNavigator i = new CellNavigator(sheet, "A1");
            final String startCell = i.getCell().getStringCellValue();
            i = new CellNavigator(sheet, startCell);
            
            final int riderRowBegin = i.getRowIndex() + 21;
            List<String> riders = this.loadRider(i, riderRowBegin);
            
            final int mandatoryRiderRowBegin = 30;
            List<String> mandatoryriders = this.loadRider(i, mandatoryRiderRowBegin);
            
            final int mandatoryGroupRowBegin = 37;
            List<String> mandatoryGroups = this.loadRider(i, mandatoryGroupRowBegin);

            for (i.gotoInitRow().nextCol(); i.hasCell(); i.gotoInitRow().nextCol()) {
                PackageProduct pkg = new PackageProduct();
                pkg.setPackageCode(i.getCellValueAsString());
                pkg.setPackageName(i.nextRow().getCellValueAsString());
                pkg.setPackageGroup(i.nextRow().getCellValueAsString());
                pkg.setPackageType(i.nextRow().getCellValueAsString());
                pkg.setProductCode(i.nextRow().getCellValueAsString());
                pkg.setPlanCode(i.nextRow().getCellValueAsString());
                pkg.setMinInsuredEntryAge(i.nextRow().getCellValueAsInteger());
                pkg.setMaxInsuredEntryAge(i.nextRow().getCellValueAsInteger());
                pkg.setMinHolderEntryAge(i.nextRow().getCellValueAsInteger());
                pkg.setMaxHolderEntryAge(i.nextRow().getCellValueAsInteger());
                String t = i.nextRow().getCellValueAsString();
                pkg.setInsuredIsHolder(t == null ? false : "V".equalsIgnoreCase(t.trim()));
                pkg.setMinPremiumTerm(i.nextRow().getCellValueAsInteger());
                //int ii = i.nextRow().getCellValueAsInteger();
                pkg.setDefaultPremiumTerm(i.nextRow().getCellValueAsInteger());
                pkg.setSumAssured(i.nextRow().getCellValueAsDouble());
                pkg.setRegPremium(i.nextRow().getCellValueAsDouble());
                pkg.setRegTopUp(i.nextRow().getCellValueAsDouble());
                pkg.setTotalPremium(i.nextRow().getCellValueAsDouble());
                pkg.setDefaultTotalPremium(i.nextRow().getCellValueAsDouble());
                pkg.setApe(i.nextRow().getCellValueAsInteger());
                pkg.setMinimalFundTerm(i.nextRow().getCellValueAsInteger());
                
                pkg.setRidersMap(new HashMap<>());
                i.setRow(riderRowBegin);
                for (String rider : riders) {
                    String value = i.getCellValueAsString();
                    if (value != null) {
                        value = value.trim();
                        if (!value.isEmpty()) {
                            pkg.getRidersMap().put(rider, value);
                        }
                    }
                    i.nextRow();
                }
                
                pkg.setRiderMandatoryMap(new HashMap<>());
                i.setRow(mandatoryRiderRowBegin);
                for (String rider : mandatoryriders) {
                    String value = i.getCellValueAsString();
                    if (value != null) {
                        value = value.trim();
                        if (!value.isEmpty()) {
                            pkg.getRiderMandatoryMap().put(rider, value);
                        }
                    }
                    i.nextRow();
                }
                
                pkg.setGroupMandatoryMap(new HashMap<>());
                i.setRow(mandatoryGroupRowBegin);
                for (String rider : mandatoryGroups) {
                    String value = i.getCellValueAsString();
                    if (value != null) {
                        value = value.trim();
                        if (!value.isEmpty()) {
                            pkg.getGroupMandatoryMap().put(rider, value);
                        }
                    }
                    i.nextRow();
                }
                
//            ConfigurationService.getSingleton().addPackageProduct(pkg);
                List<PackageProduct> list = map.get(pkg.getProductCode());
                if (list == null) {
                    map.put(pkg.getProductCode(), list = new ArrayList());
                }
                list.add(pkg);
            }
        }
        return map;
    }
    
    private List<String> loadRider(CellNavigator i, int rowStart){
        List<String> riders = new ArrayList();
        
        for (i.setRow(rowStart); i.hasCell(); i.nextRow()) {
            String riderCode = i.getCellValueAsString();
            if (riderCode != null) {
                riderCode = riderCode.trim();
                if (!riderCode.isEmpty()) {
                    riders.add(riderCode);
                }
            }
        }
        
        return riders;
    }
}
