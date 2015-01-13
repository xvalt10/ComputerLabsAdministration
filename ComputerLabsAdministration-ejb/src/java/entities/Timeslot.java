/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tomas
 */
@Entity
@Table(name = "Timeslot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Timeslot.findAll", query = "SELECT t FROM Timeslot t"),
    @NamedQuery(name = "Timeslot.findByTimeslotId", query = "SELECT t FROM Timeslot t WHERE t.timeslotId = :timeslotId"),
    @NamedQuery(name = "Timeslot.findByDay", query = "SELECT t FROM Timeslot t WHERE t.day = :day"),
    @NamedQuery(name = "Timeslot.findByStartTime", query = "SELECT t FROM Timeslot t WHERE t.startTime = :startTime"),
    @NamedQuery(name = "Timeslot.findByEndTime", query = "SELECT t FROM Timeslot t WHERE t.endTime = :endTime"),
    @NamedQuery(name = "Timeslot.findByIsOccupied", query = "SELECT t FROM Timeslot t WHERE t.isOccupied = :isOccupied")})
public class Timeslot implements Serializable {
    private static final long serialVersionUID = 1L;
   @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "timeslotId")
    private Integer timeslotId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "day")
    private int day;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startTime")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "endTime")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isOccupied")
    private boolean isOccupied;
    @JoinColumn(name = "classRoomId", referencedColumnName = "classRoomId")
    @ManyToOne(optional = false)
    private Classrooms classRoomId;

    public Timeslot() {
    }

    public Timeslot(Integer timeslotId) {
        this.timeslotId = timeslotId;
    }

    public Timeslot(Integer timeslotId, int day, Date startTime, Date endTime, boolean isOccupied) {
        this.timeslotId = timeslotId;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isOccupied = isOccupied;
    }

    public Integer getTimeslotId() {
        return timeslotId;
    }

    public void setTimeslotId(Integer timeslotId) {
        this.timeslotId = timeslotId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public Classrooms getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(Classrooms classRoomId) {
        this.classRoomId = classRoomId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (timeslotId != null ? timeslotId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Timeslot)) {
            return false;
        }
        Timeslot other = (Timeslot) object;
        if ((this.timeslotId == null && other.timeslotId != null) || (this.timeslotId != null && !this.timeslotId.equals(other.timeslotId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Timeslot[ timeslotId=" + timeslotId + " ]";
    }
    
}
