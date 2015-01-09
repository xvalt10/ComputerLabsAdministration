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
@Table(name = "Complaints")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Complaints.findAll", query = "SELECT c FROM Complaints c"),
    @NamedQuery(name = "Complaints.findByComplaintId", query = "SELECT c FROM Complaints c WHERE c.complaintId = :complaintId"),
    @NamedQuery(name = "Complaints.findBySubmissionTimestamp", query = "SELECT c FROM Complaints c WHERE c.submissionTimestamp = :submissionTimestamp"),
    @NamedQuery(name = "Complaints.findByComplaintBody", query = "SELECT c FROM Complaints c WHERE c.complaintBody = :complaintBody"),
    @NamedQuery(name = "Complaints.findByComplaintTitle", query = "SELECT c FROM Complaints c WHERE c.complaintTitle = :complaintTitle"),
    @NamedQuery(name = "Complaints.findBySolutionTitle", query = "SELECT c FROM Complaints c WHERE c.solutionTitle = :solutionTitle"),
    @NamedQuery(name = "Complaints.findBySolutionBody", query = "SELECT c FROM Complaints c WHERE c.solutionBody = :solutionBody"),
    @NamedQuery(name = "Complaints.findByCurrentStatus", query = "SELECT c FROM Complaints c WHERE c.currentStatus = :currentStatus")})
public class Complaints implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "complaintId")
    private Integer complaintId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "submissionTimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submissionTimestamp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "complaintBody")
    private String complaintBody;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "complaintTitle")
    private String complaintTitle;
    @Size(max = 50)
    @Column(name = "solutionTitle")
    private String solutionTitle;
    @Size(max = 50)
    @Column(name = "solutionBody")
    private String solutionBody;
    @Size(max = 50)
    @Column(name = "currentStatus")
    private String currentStatus;
    @JoinColumn(name = "submittedBy", referencedColumnName = "userId")
    @ManyToOne(optional = false)
    private Users submittedBy;
    @JoinColumn(name = "assignedTo", referencedColumnName = "userId")
    @ManyToOne(optional = false)
    private Users assignedTo;

    public Complaints() {
    }

    public Complaints(Integer complaintId) {
        this.complaintId = complaintId;
    }

    public Complaints(Integer complaintId, Date submissionTimestamp, String complaintBody, String complaintTitle) {
        this.complaintId = complaintId;
        this.submissionTimestamp = submissionTimestamp;
        this.complaintBody = complaintBody;
        this.complaintTitle = complaintTitle;
    }

    public Integer getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Integer complaintId) {
        this.complaintId = complaintId;
    }

    public Date getSubmissionTimestamp() {
        return submissionTimestamp;
    }

    public void setSubmissionTimestamp(Date submissionTimestamp) {
        this.submissionTimestamp = submissionTimestamp;
    }

    public String getComplaintBody() {
        return complaintBody;
    }

    public void setComplaintBody(String complaintBody) {
        this.complaintBody = complaintBody;
    }

    public String getComplaintTitle() {
        return complaintTitle;
    }

    public void setComplaintTitle(String complaintTitle) {
        this.complaintTitle = complaintTitle;
    }

    public String getSolutionTitle() {
        return solutionTitle;
    }

    public void setSolutionTitle(String solutionTitle) {
        this.solutionTitle = solutionTitle;
    }

    public String getSolutionBody() {
        return solutionBody;
    }

    public void setSolutionBody(String solutionBody) {
        this.solutionBody = solutionBody;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Users getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(Users submittedBy) {
        this.submittedBy = submittedBy;
    }

    public Users getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Users assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (complaintId != null ? complaintId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Complaints)) {
            return false;
        }
        Complaints other = (Complaints) object;
        if ((this.complaintId == null && other.complaintId != null) || (this.complaintId != null && !this.complaintId.equals(other.complaintId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Complaints[ complaintId=" + complaintId + " ]";
    }
    
}
