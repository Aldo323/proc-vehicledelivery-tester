package com.coppel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author oscar.pimentel
 */
@AllArgsConstructor
@NoArgsConstructor
@Data public class MailDeliveryExecutiveDTO {
    
    private Integer region;

    private String name;
    
    private String email;
}
