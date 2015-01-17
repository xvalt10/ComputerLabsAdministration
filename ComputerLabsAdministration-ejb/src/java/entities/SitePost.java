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
@Table(name = "sitePost")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SitePost.findAll", query = "SELECT s FROM SitePost s"),
    @NamedQuery(name = "SitePost.findBySitePostId", query = "SELECT s FROM SitePost s WHERE s.sitePostId = :sitePostId"),
    @NamedQuery(name = "SitePost.findByPostTitle", query = "SELECT s FROM SitePost s WHERE s.postTitle = :postTitle"),
    @NamedQuery(name = "SitePost.findByPostText", query = "SELECT s FROM SitePost s WHERE s.postText = :postText"),
    @NamedQuery(name = "SitePost.findByAttachmentFileName", query = "SELECT s FROM SitePost s WHERE s.attachmentFileName = :attachmentFileName"),
    @NamedQuery(name = "SitePost.findBySubmissionDate", query = "SELECT s FROM SitePost s WHERE s.submissionDate = :submissionDate")})
public class SitePost implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "sitePostId")
    private Integer sitePostId;
    @Size(max = 50)
    @Column(name = "postTitle")
    private String postTitle;
    @Size(max = 2147483647)
    @Column(name = "postText")
    private String postText;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "attachmentFileName")
    private String attachmentFileName;
    @Column(name = "submissionDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submissionDate;
    @JoinColumn(name = "labId", referencedColumnName = "labId")
    @ManyToOne
    private ComputerLabs labId;
    @JoinColumn(name = "submittedBy", referencedColumnName = "userId")
    @ManyToOne
    private Users submittedBy;

    public SitePost() {
    }

    public SitePost(Integer sitePostId) {
        this.sitePostId = sitePostId;
    }

    public SitePost(Integer sitePostId, String attachmentFileName) {
        this.sitePostId = sitePostId;
        this.attachmentFileName = attachmentFileName;
    }

    public Integer getSitePostId() {
        return sitePostId;
    }

    public void setSitePostId(Integer sitePostId) {
        this.sitePostId = sitePostId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getAttachmentFileName() {
        return attachmentFileName;
    }

    public void setAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public ComputerLabs getLabId() {
        return labId;
    }

    public void setLabId(ComputerLabs labId) {
        this.labId = labId;
    }

    public Users getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(Users submittedBy) {
        this.submittedBy = submittedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sitePostId != null ? sitePostId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SitePost)) {
            return false;
        }
        SitePost other = (SitePost) object;
        if ((this.sitePostId == null && other.sitePostId != null) || (this.sitePostId != null && !this.sitePostId.equals(other.sitePostId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SitePost[ sitePostId=" + sitePostId + " ]";
    }
    
}
