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
@Table(name = "Classrooms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classrooms.findAll", query = "SELECT c FROM Classrooms c"),
    @NamedQuery(name = "Classrooms.findByClassRoomId", query = "SELECT c FROM Classrooms c WHERE c.classRoomId = :classRoomId"),
    @NamedQuery(name = "Classrooms.findByNumberOfSeats", query = "SELECT c FROM Classrooms c WHERE c.numberOfSeats = :numberOfSeats"),
    @NamedQuery(name = "Classrooms.findByRoomNumber", query = "SELECT c FROM Classrooms c WHERE c.roomNumber = :roomNumber")})
public class Classrooms implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classRoomId")
    private Collection<Hardware> hardwareCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "classRoomId")
    private Integer classRoomId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numberOfSeats")
    private int numberOfSeats;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "roomNumber")
    private String roomNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classRoomId")
    private Collection<Timeslot> timeslotCollection;

    public Classrooms() {
    }

    public Classrooms(Integer classRoomId) {
        this.classRoomId = classRoomId;
    }

    public Classrooms(Integer classRoomId, int numberOfSeats, String roomNumber) {
        this.classRoomId = classRoomId;
        this.numberOfSeats = numberOfSeats;
        this.roomNumber = roomNumber;
    }

    public Integer getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(Integer classRoomId) {
        this.classRoomId = classRoomId;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @XmlTransient
    public Collection<Timeslot> getTimeslotCollection() {
        return timeslotCollection;
    }

    public void setTimeslotCollection(Collection<Timeslot> timeslotCollection) {
        this.timeslotCollection = timeslotCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classRoomId != null ? classRoomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classrooms)) {
            return false;
        }
        Classrooms other = (Classrooms) object;
        if ((this.classRoomId == null && other.classRoomId != null) || (this.classRoomId != null && !this.classRoomId.equals(other.classRoomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Classrooms[ classRoomId=" + classRoomId + " ]";
    }

    @XmlTransient
    public Collection<Hardware> getHardwareCollection() {
        return hardwareCollection;
    }

    public void setHardwareCollection(Collection<Hardware> hardwareCollection) {
        this.hardwareCollection = hardwareCollection;
    }
    
}
