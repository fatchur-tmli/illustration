/**
 * 
 */
package co.id.tmli.illustration.service;

import co.id.tmli.illustration.service.repo.FundRepo;
import co.id.tmli.illustration.service.repo.FundRepoImpl;
import co.id.tmli.illustration.service.repo.ProductFeatureRepo;
import co.id.tmli.illustration.service.repo.ProductFeatureRepoImpl;
import co.id.tmli.illustration.service.repo.ProductPackageRepo;
import co.id.tmli.illustration.service.repo.ProductPackageRepoImpl;
import co.id.tmli.illustration.service.repo.ProductRepo;
import co.id.tmli.illustration.service.repo.ProductRepoImpl;
import co.id.tmli.illustration.service.repo.ProductReportConfigRepo;
import co.id.tmli.illustration.service.repo.ProductReportConfigRepoImpl;
import co.id.tmli.illustration.service.repo.ProductValidationRepo;
import co.id.tmli.illustration.service.repo.ProductValidationRepoImpl;
import co.id.tmli.illustration.service.repo.RiderRepo;
import co.id.tmli.illustration.service.repo.RiderRepoImpl;
import java.io.Serializable;
import org.apache.poi.ss.usermodel.Workbook;
import co.id.tmli.illustration.utils.Cacher;
import lombok.Getter;
import org.springframework.stereotype.Component;


@Component
public class ServiceProvider implements Serializable {

    @Getter	
    private static final ServiceProvider singleton = new ServiceProvider();

    private final WorkbookService workbookService = new WorkbookService();
    
    public ProductRepo getProductRepo() {
        return productRepo.get();
    }
    Cacher<ProductRepo> productRepo = new Cacher<>(() -> {
        ProductRepoImpl pr = new ProductRepoImpl(getWorkbook());
        pr.setProductFeatureRepo(getProductFeatureRepo());
        pr.setFundRepo(getFundRepo());
        pr.setProductPackageRepo(getProductPackageRepo());
        pr.setProductReportConfigRepo(getProductReportConfigRepo());
        pr.setProductValidationRepo(getProductValidationRepo());
        return pr;
    });

    public Workbook getWorkbook() {
        return workbookService.getWorkbook();
    }
    
    public RiderService getRiderService() {
        return riderService.get();
    }
    Cacher<RiderServiceImpl> riderService = new Cacher<>(() -> new RiderServiceImpl(getRiderRepo()));
    
    public RiderRepo getRiderRepo() {
        return riderRepo.get();
    }
    Cacher<RiderRepo> riderRepo = new Cacher<>(() -> new RiderRepoImpl(getWorkbook()));
	
    public FundRepo getFundRepo() {
        return fundRepo.get();
    }
    Cacher<FundRepo> fundRepo = new Cacher<>(() -> new FundRepoImpl(getWorkbook()));
    
    public ProductFeatureRepo getProductFeatureRepo() {
        return productFeatureRepo.get();
    }
    Cacher<ProductFeatureRepo> productFeatureRepo = new Cacher<>(() -> new ProductFeatureRepoImpl(getWorkbook()));
    
    public ProductPackageRepo getProductPackageRepo() {
        return productPackageRepo.get();
    }
    Cacher<ProductPackageRepo> productPackageRepo = new Cacher<>(() -> new ProductPackageRepoImpl(getWorkbook()));
    
    public ProductReportConfigRepo getProductReportConfigRepo() {
        return productReportConfigRepo.get();
    }
    Cacher<ProductReportConfigRepo> productReportConfigRepo = new Cacher<>(() -> new ProductReportConfigRepoImpl(getWorkbook()));

    public ProductValidationRepo getProductValidationRepo() {
        return productValidationRepo.get();
    }
    Cacher<ProductValidationRepo> productValidationRepo = new Cacher<>(() -> new ProductValidationRepoImpl(getWorkbook()));
    
    public ProductService getProductService() {
        return productService.get();
    }
    Cacher<ProductService> productService = new Cacher<>(() -> new ProductServiceImpl(getProductRepo()));
	
}
