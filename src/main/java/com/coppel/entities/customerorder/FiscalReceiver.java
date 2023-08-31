package com.coppel.entities.customerorder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author mhernandez
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data public class FiscalReceiver implements Serializable {
    @Id
    private String rfc;
    private String name;
    private int taxRegime;
    private String nameTaxRegime;
    private String usoCFDI;
    private String email;
    private FiscalReceiverAddress taxResidence;

}
