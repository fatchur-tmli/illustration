/**
 * 
 */
package co.id.tmli.illustration.controller;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractProductController implements Serializable {

    private static final long serialVersionUID = -5454586711695391101L;

    private static final Logger log = LoggerFactory.getLogger(AbstractProductController.class);

    abstract String getReportType();	

}
