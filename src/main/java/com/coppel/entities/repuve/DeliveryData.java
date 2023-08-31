package com.coppel.entities.repuve;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
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
@Data public class DeliveryData implements Serializable{
    
    @Id
    private Date movementDate;
    private Date activationDate;
    private Date deliveryDate;
    private Date assortmentDate;
}
