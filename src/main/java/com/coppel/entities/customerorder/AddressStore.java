package com.coppel.entities.customerorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author oscar.pimentel
 */
@AllArgsConstructor
@NoArgsConstructor
@Data public class AddressStore {
    
    private String extStoreNumber;
    private String storeStreet;
    private String storeNeighborhood;
    private String storeMunicipality;
    private String storeState;
}
