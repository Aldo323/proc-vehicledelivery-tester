package com.coppel.dto.repuve;

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
@Data public class DeliveryDataDTO {
    private Date movementDate;
    private Date activationDate;
    private Date deliveryDate;
    private Date assortmentDate;
}
