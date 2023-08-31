package com.coppel.entities.invoice;

import com.coppel.dto.customerorder.FiscalIssuerDTO;
import com.coppel.dto.customerorder.FiscalReceiverDTO;
import com.coppel.dto.customerorder.StoreDTO;
import com.coppel.dto.invoice.BillInvoiceDTO;
import com.coppel.dto.invoice.InvoiceLetterDTO;
import com.coppel.dto.invoice.VehicleInvoiceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author oscar.pimentel
 */
@AllArgsConstructor
@NoArgsConstructor
@Data public class InvoiceRequest {
    
    StoreDTO store;
    FiscalIssuerDTO fiscalIssuer;
    FiscalReceiverDTO fiscalReceiver;
    VehicleInvoiceDTO vehicleInvoice;
    InvoiceLetterDTO invoiceLetter;
    BillInvoiceDTO billInvoice;
}
