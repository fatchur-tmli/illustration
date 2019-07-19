/**
 * 
 */
package co.id.tmli.illustration.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class DataAgent implements Serializable {
	
    private static final long serialVersionUID = 4893104046313063847L;
	
    private String agentName;
    private String agentCode;
    private String officeName;

}
