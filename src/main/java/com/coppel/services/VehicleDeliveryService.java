package com.coppel.services;

import com.coppel.dto.ResponseVehicleDeliveryDTO;
import com.coppel.entities.Serial;
import org.springframework.stereotype.Service;

/**
 *
 * @author oscar.pimentel
 */
@Service
public interface VehicleDeliveryService {
    
    public Boolean healthz();
    public String inicVehicleProcess();
    public ResponseVehicleDeliveryDTO setVehicleDelivery(String orderid, Serial serial);
}
