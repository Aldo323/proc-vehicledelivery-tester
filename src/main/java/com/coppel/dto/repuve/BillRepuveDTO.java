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
@Data public class BillRepuveDTO {
    
    private String fiscalbill;
    private String generatedDate;
    private String voucherType;
    private String area;
    private String cashier;
    private String status;
    private String clvfoliouuid;
}
