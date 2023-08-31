package com.coppel.dto.customerorder;

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
@Data public class InvoiceDTO {
    
    private String invoiceNumber;
    private int storeNumber;
    private int cashier;
    private String sku;
    private int amount;
    private int employeeNumber;
    private int clientNumber;
    private Date saleDate;
    private String formaPago;
    private String paymentConditions;
    private String currency;
    private double subTotal;
    private double total;
    private String paymentMethod;
    private String storeSerial;
    private String vat;
    private String phoneNumber;
    private String claveProdServ;
    private String noIdentificacion;
    private String keyUnit;
    private String unit;
    private String objetoImp;	
    private String tax;	
    private String factorType;
    private String voucherType;	
    private String area;
}
