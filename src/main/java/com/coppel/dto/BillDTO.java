package com.coppel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author oscar.pimentel
 */
@AllArgsConstructor
@NoArgsConstructor
@Data public class BillDTO {
    
    private String fiscalbill;
    private String generatedDate;
    private String voucherType;
    private String storeNumber;
    private String area;
    private String cashier;
    private String status;
    private int invoiceNumber;
    private String clvfoliouuid;
}
