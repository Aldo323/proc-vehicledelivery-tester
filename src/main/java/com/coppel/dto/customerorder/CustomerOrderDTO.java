package com.coppel.dto.customerorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author oscar.pimentel
 */
@AllArgsConstructor
@NoArgsConstructor
@Data public class CustomerOrderDTO {
    
    private String orderId;
    private StoreDTO store;
    private InvoiceDTO invoice;
    private FiscalIssuerDTO fiscalIssuer;
    private FiscalReceiverDTO fiscalReceiver;
}
