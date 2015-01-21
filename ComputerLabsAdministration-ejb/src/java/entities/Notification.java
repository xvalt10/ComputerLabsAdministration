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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tomas
 */
@Entity
@Table(name = "Notification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n"),
    @NamedQuery(name = "Notification.findByNotificationId", query = "SELECT n FROM Notification n WHERE n.notificationId = :notificationId"),
    @NamedQuery(name = "Notification.findByNotificationTitle", query = "SELECT n FROM Notification n WHERE n.notificationTitle = :notificationTitle"),
    @NamedQuery(name = "Notification.findByNotificationText", query = "SELECT n FROM Notification n WHERE n.notificationText = :notificationText"),
    @NamedQuery(name = "Notification.findByNotificationWasRead", query = "SELECT n FROM Notification n WHERE n.notificationWasRead = :notificationWasRead"),
    @NamedQuery(name = "Notification.findByCreationTimestamp", query = "SELECT n FROM Notification n WHERE n.creationTimestamp = :creationTimestamp")})
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "notificationId")
    private Integer notificationId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "notificationTitle")
    private String notificationTitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "notificationText")
    private String notificationText;
    @Basic(optional = false)
    @NotNull
    @Column(name = "notificationWasRead")
    private boolean notificationWasRead;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creationTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTimestamp;
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @ManyToOne(optional = false)
    private Users userId;

    public Notification() {
    }

    public Notification(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Notification(Integer notificationId, String notificationTitle, String notificationText, boolean notificationWasRead, Date creationTimestamp) {
        this.notificationId = notificationId;
        this.notificationTitle = notificationTitle;
        this.notificationText = notificationText;
        this.notificationWasRead = notificationWasRead;
        this.creationTimestamp = creationTimestamp;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public boolean getNotificationWasRead() {
        return notificationWasRead;
    }

    public void setNotificationWasRead(boolean notificationWasRead) {
        this.notificationWasRead = notificationWasRead;
    }

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Date creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notificationId != null ? notificationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.notificationId == null && other.notificationId != null) || (this.notificationId != null && !this.notificationId.equals(other.notificationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Notification[ notificationId=" + notificationId + " ]";
    }
    
}
