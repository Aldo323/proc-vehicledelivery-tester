package com.coppel.entities;

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
@Data public class Bill implements Serializable {
    @Id
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
