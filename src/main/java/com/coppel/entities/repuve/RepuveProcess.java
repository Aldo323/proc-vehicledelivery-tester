package com.coppel.entities.repuve;

import com.coppel.entities.Bill;
import com.coppel.entities.customerorder.Invoice;
import com.coppel.entities.customerorder.Vehicle;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author oscar.pimentel
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data public class RepuveProcess implements Serializable{
    
    @Id
    private String orderId;
    private DeliveryData delivery;
    private Vehicle vehicle;
    private Invoice invoice;
    private Bill bill;
}
