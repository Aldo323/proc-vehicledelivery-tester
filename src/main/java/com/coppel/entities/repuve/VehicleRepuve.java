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
@Data public class VehicleRepuve implements Serializable{
    
    @Id
    private String chassis;
    private String article;
    private String brand;
    private String model;
    private String color;
    private String year;
    private String engine;
    private String serial;
    private String cylinderCapacity;
    private Date entryDate;
    private String nci;
    private String petition;
}
