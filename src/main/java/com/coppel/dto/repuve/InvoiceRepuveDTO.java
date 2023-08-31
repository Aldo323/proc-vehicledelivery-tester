package com.coppel.dto.repuve;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author oscar.pimentel
 */
@AllArgsConstructor
@NoArgsConstructor
@Data public class InvoiceRepuveDTO {
    
    private Integer invoiceNumber;
    private String sku;
    private Integer amount;
}
