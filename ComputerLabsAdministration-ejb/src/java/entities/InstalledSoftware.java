/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tomas
 */
@Entity
@Table(name = "InstalledSoftware")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InstalledSoftware.findAll", query = "SELECT i FROM InstalledSoftware i"),
    @NamedQuery(name = "InstalledSoftware.findByInstalledSoftwareId", query = "SELECT i FROM InstalledSoftware i WHERE i.installedSoftwareId = :installedSoftwareId")})
public class InstalledSoftware implements Serializable {
    private static final long serialVersionUID = 1L;
   @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "installedSoftwareId")
    private Integer installedSoftwareId;
    @JoinColumn(name = "computerId", referencedColumnName = "hardwareId")
    @ManyToOne(optional = false)
    private Hardware computerId;
    @JoinColumn(name = "softwareId", referencedColumnName = "softwareId")
    @ManyToOne(optional = false)
    private Software softwareId;

    public InstalledSoftware() {
    }

    public InstalledSoftware(Integer installedSoftwareId) {
        this.installedSoftwareId = installedSoftwareId;
    }

    public Integer getInstalledSoftwareId() {
        return installedSoftwareId;
    }

    public void setInstalledSoftwareId(Integer installedSoftwareId) {
        this.installedSoftwareId = installedSoftwareId;
    }

    public Hardware getComputerId() {
        return computerId;
    }

    public void setComputerId(Hardware computerId) {
        this.computerId = computerId;
    }

    public Software getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(Software softwareId) {
        this.softwareId = softwareId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (installedSoftwareId != null ? installedSoftwareId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InstalledSoftware)) {
            return false;
        }
        InstalledSoftware other = (InstalledSoftware) object;
        if ((this.installedSoftwareId == null && other.installedSoftwareId != null) || (this.installedSoftwareId != null && !this.installedSoftwareId.equals(other.installedSoftwareId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.InstalledSoftware[ installedSoftwareId=" + installedSoftwareId + " ]";
    }
    
}
