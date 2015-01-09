/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tomas
 */
@Entity
@Table(name = "Hardware")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hardware.findAll", query = "SELECT h FROM Hardware h"),
    @NamedQuery(name = "Hardware.findByHardwareId", query = "SELECT h FROM Hardware h WHERE h.hardwareId = :hardwareId"),
    @NamedQuery(name = "Hardware.findByDateOfPurchase", query = "SELECT h FROM Hardware h WHERE h.dateOfPurchase = :dateOfPurchase"),
    @NamedQuery(name = "Hardware.findByExpirationOfWarranty", query = "SELECT h FROM Hardware h WHERE h.expirationOfWarranty = :expirationOfWarranty"),
    @NamedQuery(name = "Hardware.findByType", query = "SELECT h FROM Hardware h WHERE h.type = :type"),
    @NamedQuery(name = "Hardware.findByCurrentState", query = "SELECT h FROM Hardware h WHERE h.currentState = :currentState")})
public class Hardware implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "hardwareId")
    private Integer hardwareId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateOfPurchase")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfPurchase;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expirationOfWarranty")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationOfWarranty;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "type")
    private String type;
    @Size(max = 50)
    @Column(name = "currentState")
    private String currentState;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "computerId")
    private Collection<InstalledSoftware> installedSoftwareCollection;

    public Hardware() {
    }

    public Hardware(Integer hardwareId) {
        this.hardwareId = hardwareId;
    }

    public Hardware(Integer hardwareId, Date dateOfPurchase, Date expirationOfWarranty, String type) {
        this.hardwareId = hardwareId;
        this.dateOfPurchase = dateOfPurchase;
        this.expirationOfWarranty = expirationOfWarranty;
        this.type = type;
    }

    public Integer getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(Integer hardwareId) {
        this.hardwareId = hardwareId;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Date getExpirationOfWarranty() {
        return expirationOfWarranty;
    }

    public void setExpirationOfWarranty(Date expirationOfWarranty) {
        this.expirationOfWarranty = expirationOfWarranty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    @XmlTransient
    public Collection<InstalledSoftware> getInstalledSoftwareCollection() {
        return installedSoftwareCollection;
    }

    public void setInstalledSoftwareCollection(Collection<InstalledSoftware> installedSoftwareCollection) {
        this.installedSoftwareCollection = installedSoftwareCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hardwareId != null ? hardwareId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hardware)) {
            return false;
        }
        Hardware other = (Hardware) object;
        if ((this.hardwareId == null && other.hardwareId != null) || (this.hardwareId != null && !this.hardwareId.equals(other.hardwareId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Hardware[ hardwareId=" + hardwareId + " ]";
    }
    
}
