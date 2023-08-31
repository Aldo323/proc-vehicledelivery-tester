package com.coppel.entities.customerorder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CustomerOrder
 * @author oscar.pimentel
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data public class CustomerOrder  implements Serializable {

    @Id
    private String orderId;
    private Store store;
    private Invoice invoice;
    private FiscalIssuer fiscalIssuer;
    private FiscalReceiver fiscalReceiver;
}
