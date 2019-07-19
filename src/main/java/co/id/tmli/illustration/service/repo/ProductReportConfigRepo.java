package co.id.tmli.illustration.service.repo;

import java.util.Map;

public interface ProductReportConfigRepo {

    Map<String, String> getProductReportConfig(String productCode);
}
