package com.coppel.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author oscar.pimentel
 */
@Entity
@Table(name = "cat_maildeliveryexecutive")
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Data public class MailDeliveryExecutive implements Serializable{
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "region")
    private Integer region;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "name")
    private String name;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "email")
    private String email;
    
    
}
