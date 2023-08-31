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
@Data public class RepuveProcessDTO {
 
    private String orderId;
    private DeliveryDataDTO delivery;
    private VehicleRepuveDTO vehicle;
    private InvoiceRepuveDTO invoice;
    private BillRepuveDTO bill;
}
