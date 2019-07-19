/**
 * 
 */
package co.id.tmli.illustration.controller;

import co.id.tmli.illustration.model.Fund;
import co.id.tmli.illustration.model.Rider;
import co.id.tmli.illustration.model.TMProduct;
import co.id.tmli.illustration.service.IllustrationService;
import co.id.tmli.illustration.service.ServiceProvider;
import co.id.tmli.illustration.utils.CurrencyEnum;
import java.io.Serializable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fatchurrahman
 *
 */
@RestController
@RequestMapping("/api")
public class MainController implements Serializable {
    
    private static final Logger log = LoggerFactory.getLogger(AbstractProductController.class);

    @Autowired
    private ServiceProvider ServiceProvider;
    
    @Autowired
    private IllustrationService IllustrationService;
    
    @GetMapping(value = "/product")
    ResponseEntity<List<TMProduct>> getProducts() {               
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ServiceProvider.getProductRepo().getProducts());
    }   
    
    @GetMapping(value = "/product/{productCode}")
    ResponseEntity<TMProduct> getProductByProductCode(@PathVariable(required = true) String productCode) {               
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ServiceProvider.getProductRepo().getProduct(productCode));
    }           
    
    @GetMapping(value = "/fund")
    ResponseEntity<List<Fund>> getFunds() {               
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ServiceProvider.getFundRepo().getFunds());
    }         
    
    @GetMapping(value = "/fund/{productCode}/{currency}")
    ResponseEntity<List<Fund>> getFundByProductCode(
            @PathVariable(value = "productCode", required = true) String productCode,
            @PathVariable(value = "currency", required = true) String currency) {      
        if (currency.equals("rp")) {
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(IllustrationService.getFundConfigList(productCode, CurrencyEnum.IDR));            
        } else {            
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(IllustrationService.getFundConfigList(productCode, CurrencyEnum.USD));
        }        
    }
    
    @GetMapping(value = "/rider")
    ResponseEntity<List<Rider>> getRiders() {               
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ServiceProvider.getRiderRepo().getRiders());
    }
    
    @RequestMapping(value = "/rider/{productCode}" , method = RequestMethod.GET)
    private List<String> getRiderByProduct(@PathVariable(value = "productCode", required = true) String productCode) {                
        return ServiceProvider.getProductRepo().getProduct(productCode).getRiders();
    }
    
    @PostMapping
    ResponseEntity<Void> submit(
        @PathVariable(value = "id", required = true) String id,
        @PathVariable(value = "productCode", required = true) String productCode,
        @PathVariable(value = "up", required = true) String up,
        @PathVariable(value = "currency", required = true) String currency,
        @PathVariable(value = "premi", required = true) String premi,
        @PathVariable(value = "fund", required = true) String fund,
        @PathVariable(value = "period", required = true) String period) {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(null);
    }
    
}
