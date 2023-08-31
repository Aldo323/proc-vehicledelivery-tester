package com.coppel.controllers;

import com.coppel.dto.ApiResponseDTO;
import com.coppel.entities.Serial;
import com.coppel.services.VehicleDeliveryService;
import com.coppel.util.Meta;
import java.net.URISyntaxException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


/**
 *
 * @author oscar.pimentel
 */
@RestController
public class VehicleDeliveryController {

    @Autowired
    private VehicleDeliveryService vehicleDeliveryService;
    
    private final Meta meta = new Meta(UUID.randomUUID().toString(), "OK", 200);
    private static final String FORMATO = "El orderid/serial no cuentan con el formato correcto";
    
    @GetMapping(value="healthz")
    public ApiResponseDTO getTest(){
        return new ApiResponseDTO(meta,vehicleDeliveryService.healthz());
    }
    
    @GetMapping(value="vehicleprocess")
    public ApiResponseDTO vehicleProcess() throws URISyntaxException {
        ApiResponseDTO api = null;
        try{    
            api = new ApiResponseDTO(meta, vehicleDeliveryService.inicVehicleProcess());
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,FORMATO);
        }
        return api;
    }
    
    @PostMapping(value="vehicledelivery/order/{orderid}")
    public ApiResponseDTO vehicleDeliveryProcess(@RequestBody Serial serial,@PathVariable String orderid) throws URISyntaxException {
        ApiResponseDTO api = null;
        Serial serie;
        String order;
        if(serial==null || orderid==null){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,FORMATO);
        }
        else{
            serie = serial;
            order = orderid;
            try{
            api =  new ApiResponseDTO(meta, vehicleDeliveryService.setVehicleDelivery(order, serie));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,FORMATO);
        }
        return api;
        }
    }
}
