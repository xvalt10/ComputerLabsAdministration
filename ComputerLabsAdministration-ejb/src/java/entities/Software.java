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
@Table(name = "Software")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Software.findAll", query = "SELECT s FROM Software s"),
    @NamedQuery(name = "Software.findBySoftwareId", query = "SELECT s FROM Software s WHERE s.softwareId = :softwareId"),
    @NamedQuery(name = "Software.findByDateOfPurchae", query = "SELECT s FROM Software s WHERE s.dateOfPurchae = :dateOfPurchae"),
    @NamedQuery(name = "Software.findByExpirationOfLicence", query = "SELECT s FROM Software s WHERE s.expirationOfLicence = :expirationOfLicence"),
    @NamedQuery(name = "Software.findByType", query = "SELECT s FROM Software s WHERE s.type = :type"),
    @NamedQuery(name = "Software.findByCurrentState", query = "SELECT s FROM Software s WHERE s.currentState = :currentState")})
public class Software implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "softwareId")
    private Integer softwareId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateOfPurchae")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfPurchae;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expirationOfLicence")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationOfLicence;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "type")
    private String type;
    @Size(max = 50)
    @Column(name = "currentState")
    private String currentState;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "softwareId")
    private Collection<InstalledSoftware> installedSoftwareCollection;

    public Software() {
    }

    public Software(Integer softwareId) {
        this.softwareId = softwareId;
    }

    public Software(Integer softwareId, Date dateOfPurchae, Date expirationOfLicence, String type) {
        this.softwareId = softwareId;
        this.dateOfPurchae = dateOfPurchae;
        this.expirationOfLicence = expirationOfLicence;
        this.type = type;
    }

    public Integer getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(Integer softwareId) {
        this.softwareId = softwareId;
    }

    public Date getDateOfPurchae() {
        return dateOfPurchae;
    }

    public void setDateOfPurchae(Date dateOfPurchae) {
        this.dateOfPurchae = dateOfPurchae;
    }

    public Date getExpirationOfLicence() {
        return expirationOfLicence;
    }

    public void setExpirationOfLicence(Date expirationOfLicence) {
        this.expirationOfLicence = expirationOfLicence;
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
        hash += (softwareId != null ? softwareId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Software)) {
            return false;
        }
        Software other = (Software) object;
        if ((this.softwareId == null && other.softwareId != null) || (this.softwareId != null && !this.softwareId.equals(other.softwareId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Software[ softwareId=" + softwareId + " ]";
    }
    
}
