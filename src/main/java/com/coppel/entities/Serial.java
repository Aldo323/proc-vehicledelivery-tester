package com.coppel.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Data public class Serial implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    private String serie;
}
