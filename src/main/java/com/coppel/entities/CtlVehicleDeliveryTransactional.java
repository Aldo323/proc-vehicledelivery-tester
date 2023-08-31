package com.coppel.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
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
@Table(name = "ctl_vehicledelivery_transactional")
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Data public class CtlVehicleDeliveryTransactional implements Serializable {
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "orden")
    private String orden;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "serial")
    private String serial;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private Short status;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "attempt")
    private Short attempt;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "delivery_date")
    private Date deliveryDate;

}
