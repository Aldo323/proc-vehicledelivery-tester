package com.coppel.entities.repuve;

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
@Data public class InvoiceRepuve implements Serializable{

    @Id
    private Integer invoiceNumber;
    private Integer storeNumber;
    private String storeSerial;
    private String sku;
    private Integer amount;
    private Integer price;
}
