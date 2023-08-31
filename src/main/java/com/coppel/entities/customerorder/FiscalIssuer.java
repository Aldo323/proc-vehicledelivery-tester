package com.coppel.entities.customerorder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
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
@Data public class FiscalIssuer implements Serializable {
    
    @Id
    private String rfc;
    private String name;
    private int taxRegime;
    private String nameTaxRegime;
    private FiscalIssuerAddress taxResidenceIssuer;
}
