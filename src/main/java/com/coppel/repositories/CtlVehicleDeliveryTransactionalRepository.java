package com.coppel.repositories;

import com.coppel.entities.CtlVehicleDeliveryTransactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author oscar.pimentel
 */
public interface CtlVehicleDeliveryTransactionalRepository extends JpaRepository<CtlVehicleDeliveryTransactional,String>{
    
    List<CtlVehicleDeliveryTransactional> findByStatus(Short status);
}
