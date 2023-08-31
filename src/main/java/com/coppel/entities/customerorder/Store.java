package com.coppel.entities.customerorder;

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
@Data public class Store implements Serializable {
    
    @Id
    private int storeNumber;
    private String branchName;
    private AddressStore addressStore;
}
