package com.coppel.entities.pk;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author oscar.pimentel
 */
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CtlVehicleDeliveryPK implements Serializable {
    
    private String orden;
    private Short processid;
    
    @Override
     public int hashCode() {
         return this.orden.hashCode();
     }
     
    @Override
      public boolean equals(Object obj) {
         if (obj == this) return true;
         if (!(obj instanceof CtlVehicleDeliveryPK)) return false;
         CtlVehicleDeliveryPK pk = (CtlVehicleDeliveryPK) obj;
         return pk.orden.equals(this.orden) && pk.processid.equals(this.processid);
     }
}
