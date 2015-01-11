/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tomas
 */
@Entity
@Table(name = "ComputerLabs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComputerLabs.findAll", query = "SELECT c FROM ComputerLabs c"),
    @NamedQuery(name = "ComputerLabs.findByLabId", query = "SELECT c FROM ComputerLabs c WHERE c.labId = :labId"),
    @NamedQuery(name = "ComputerLabs.findByLabName", query = "SELECT c FROM ComputerLabs c WHERE c.labName = :labName")})
public class ComputerLabs implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "approvalstatus")
    private String approvalstatus;
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "labId")
    private Integer labId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "labName")
    private String labName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "labId")
    private Collection<Schedule> scheduleCollection;
    @JoinColumn(name = "instructor", referencedColumnName = "userId")
    @ManyToOne(optional = false)
    private Users instructor;

    public ComputerLabs() {
    }

    public ComputerLabs(Integer labId) {
        this.labId = labId;
    }

    public ComputerLabs(Integer labId, String labName) {
        this.labId = labId;
        this.labName = labName;
    }

    public Integer getLabId() {
        return labId;
    }

    public void setLabId(Integer labId) {
        this.labId = labId;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    @XmlTransient
    public Collection<Schedule> getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(Collection<Schedule> scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    public Users getInstructor() {
        return instructor;
    }

    public void setInstructor(Users instructor) {
        this.instructor = instructor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (labId != null ? labId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComputerLabs)) {
            return false;
        }
        ComputerLabs other = (ComputerLabs) object;
        if ((this.labId == null && other.labId != null) || (this.labId != null && !this.labId.equals(other.labId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ComputerLabs[ labId=" + labId + " ]";
    }

    public String getApprovalstatus() {
        return approvalstatus;
    }

    public void setApprovalstatus(String approvalstatus) {
        this.approvalstatus = approvalstatus;
    }
    
}
