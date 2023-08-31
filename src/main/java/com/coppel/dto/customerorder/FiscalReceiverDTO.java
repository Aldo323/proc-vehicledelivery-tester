package com.coppel.dto.customerorder;

import com.coppel.entities.customerorder.FiscalReceiverAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author mhernandez
 */
@AllArgsConstructor
@NoArgsConstructor
@Data public class FiscalReceiverDTO {
    
    private String rfc;
    private String name;
    private int taxRegime;
    private String nameTaxRegime;
    private String usoCFDI;
    private String email;
    private FiscalReceiverAddress taxResidence;
}
