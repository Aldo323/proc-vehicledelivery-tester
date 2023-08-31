package com.coppel.dto.invoice;

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
@Data public class VehicleInvoiceDTO {
    
    private String chassis;
    private String article;
    private String brand;
    private String model;
    private String color;
    private String year;
    private String engine;
    private String cylinderCapacity;
    private Date entryDate;
    private String nci;
    private String customspetition;
}
