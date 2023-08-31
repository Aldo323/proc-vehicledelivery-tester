package com.coppel.entities.customerorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author mhernandez
 */
@NoArgsConstructor
@AllArgsConstructor
@Data public class FiscalReceiverAddress {

    private int extNumber;
    private String street;
    private String neighborhood;
    private String municipality;
    private String postalCode;

}
