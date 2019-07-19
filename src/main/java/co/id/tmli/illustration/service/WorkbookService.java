/**
 * 
 */
package co.id.tmli.illustration.service;

import java.io.InputStream;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import co.id.tmli.illustration.utils.Cacher;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;


@Component
public class WorkbookService {
	
    @lombok.Getter
    private static final WorkbookService singleton = new WorkbookService();

    @Value("${app.version.id}")
    String id;

    private final Cacher<Workbook> cacher = new Cacher<>(this::loadWorkbook); 

    public Workbook getWorkbook() {
        return cacher.get();
    }
	
    private Workbook loadWorkbook() {             
        String path = "/config/Agency.xlsx";     
        ClassPathResource cpr = new ClassPathResource("config/Agency.xlsx");
        //try (InputStream xlsx = new FileInputStream(ResourceUtils.getFile("classpath:config/Agency.xlsx"))) {            
        try (InputStream xlsx = new FileInputStream(cpr.getFile())) {            
            POIFSFileSystem pfs = new POIFSFileSystem(xlsx);
            EncryptionInfo info = new EncryptionInfo(pfs);
            Decryptor decryptor = Decryptor.getInstance(info);
            if (Decryptor.getInstance(info).verifyPassword("AsdfghjkL")) {
                try (InputStream dataStream = decryptor.getDataStream(pfs);) {
                    return new XSSFWorkbook(dataStream);
                }
            }
            return null;
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            }
            throw new RuntimeException(e);
        }
    }

}
