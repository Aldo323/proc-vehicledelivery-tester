package com.coppel.entities;

import com.coppel.entities.pk.CtlVehicleDeliveryPK;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
@Table(name = "ctl_vehicledelivery_proc_orchestration")
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Data public class CtlVehicleDelivery implements Serializable {
    
    @EmbeddedId
    private CtlVehicleDeliveryPK id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private Short status;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "process_date")
    private Date processDate;
    
    
}

