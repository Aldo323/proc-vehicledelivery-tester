package com.coppel.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author oscar.pimentel
 */
@AllArgsConstructor
@NoArgsConstructor
@Data public class CtlVehicleDeliveryTransactionalDTO {
    
    private String orden;
    
    private String serial;

    private Short status;

    private Short attempt;
    
    private Date deliveryDate;

}
