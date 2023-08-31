package com.coppel.repositories;

import com.coppel.entities.CtlVehicleDelivery;
import com.coppel.entities.pk.CtlVehicleDeliveryPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author oscar.pimentel
 */
public interface VehicleDeliveryRepository extends JpaRepository<CtlVehicleDelivery,CtlVehicleDeliveryPK> {
    
}
