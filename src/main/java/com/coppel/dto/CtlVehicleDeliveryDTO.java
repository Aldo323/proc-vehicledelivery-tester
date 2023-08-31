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
@Data public class CtlVehicleDeliveryDTO {
    
    private Integer orden;

    private Short processId;
    
    private Short status;
    
    private Date processDate;

}