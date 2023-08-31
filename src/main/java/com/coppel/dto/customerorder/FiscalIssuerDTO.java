package com.coppel.dto.customerorder;

import com.coppel.entities.customerorder.FiscalIssuerAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author oscar.pimentel
 */
@AllArgsConstructor
@NoArgsConstructor
@Data public class FiscalIssuerDTO {
    
    private String rfc;
    private String name;
    private int taxRegime;
    private String nameTaxRegime;
    private FiscalIssuerAddress taxResidence;
}
