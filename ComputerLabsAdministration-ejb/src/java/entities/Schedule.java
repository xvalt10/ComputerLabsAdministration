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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tomas
 */
@Entity
@Table(name = "Schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s"),
    @NamedQuery(name = "Schedule.findByTimeslotId", query = "SELECT s FROM Schedule s WHERE s.timeslotId = :timeslotId"),
    @NamedQuery(name = "Schedule.findByApprovalStatus", query = "SELECT s FROM Schedule s WHERE s.approvalStatus = :approvalStatus"),
    @NamedQuery(name = "Schedule.findByScheduleId", query = "SELECT s FROM Schedule s WHERE s.scheduleId = :scheduleId")})
public class Schedule implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "approvalStatus")
    private String approvalStatus;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "scheduleId")
    private Integer scheduleId;
    @JoinColumn(name = "labId", referencedColumnName = "labId")
    @ManyToOne(optional = false)
    private ComputerLabs labId;
    @JoinColumn(name = "timeslotId", referencedColumnName = "timeslotId")
    @ManyToOne(optional = false)
    private Timeslot timeslotId;

    public Schedule() {
    }

    public Schedule(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public ComputerLabs getLabId() {
        return labId;
    }

    public void setLabId(ComputerLabs labId) {
        this.labId = labId;
    }

    public Timeslot getTimeslotId() {
        return timeslotId;
    }

    public void setTimeslotId(Timeslot timeslotId) {
        this.timeslotId = timeslotId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scheduleId != null ? scheduleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) object;
        if ((this.scheduleId == null && other.scheduleId != null) || (this.scheduleId != null && !this.scheduleId.equals(other.scheduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Schedule[ scheduleId=" + scheduleId + " ]";
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
    
}
