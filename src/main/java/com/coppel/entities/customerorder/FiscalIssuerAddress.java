package com.coppel.entities.customerorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author oscar.pimentel
 */
@AllArgsConstructor
@NoArgsConstructor
@Data public class FiscalIssuerAddress {
    
    private int extNumber;
    private String street;
    private String neighborhood;
    private String municipality;
    private int postalCode;
}
