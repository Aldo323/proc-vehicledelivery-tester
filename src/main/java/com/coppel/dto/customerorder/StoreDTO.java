package com.coppel.dto.customerorder;

import com.coppel.entities.customerorder.AddressStore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author oscar.pimentel
 */
@AllArgsConstructor
@NoArgsConstructor
@Data public class StoreDTO {
    
    private int storeNumber;
    private String branchName;
    private AddressStore addressStore;
}
