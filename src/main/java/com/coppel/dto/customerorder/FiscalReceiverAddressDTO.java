package com.coppel.dto.customerorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author mhernandez
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FiscalReceiverAddressDTO {

    private String num;
    private String calle;
    private String colonia;
    private String municipio;
    private String codigoPostal;
}
