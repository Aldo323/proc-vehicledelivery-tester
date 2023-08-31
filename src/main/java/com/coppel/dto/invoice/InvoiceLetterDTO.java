package com.coppel.dto.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author oscar.pimentel
 */
@AllArgsConstructor
@NoArgsConstructor
@Data public class InvoiceLetterDTO {
    
    private Integer invoiceNumber;
    private String sku;
    private Integer amount;
    private String storeSerial;
    private double subTotal;
}
