/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Complaints;
import entities.Users;
import helperClasses.JsfUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import sessionBeans.ComplaintsFacade;
import sessionBeans.NotificationFacade;
import sessionBeans.UsersFacade;

/**
 *
 * @author Tomas
 */
@ManagedBean
@SessionScoped
public class ComplaintsManagedBean {

    @EJB
    private NotificationFacade notificationFacade;

    //injection of session beans used for CRUD operations on the DB
    @EJB
    private ComplaintsFacade complaintsFacade;
    @EJB
    private UsersFacade usersFacade;

    //declaration of variables
    private Users user;
    private Complaints complaint;
    private List<Complaints> complaints;

    //getters and setters for private variables
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Complaints> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaints> complaints) {
        this.complaints = complaints;
    }

    public Complaints getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaints complaint) {
        this.complaint = complaint;
    }

    public String getSubmittedComplaintsByUser() {
        complaints.clear();
        complaints = complaintsFacade.findSubmittedComplaintsByUser();
        return "viewComplaints";
    }

    public String getComplaintsAssignedToUser() {
        complaints.clear();
        complaints = complaintsFacade.findComplaintsAssignedToUser();
        return "viewComplaints";
    }

    /**
     * Creates a new instance of ComplaintsManagedBean
     */
    public ComplaintsManagedBean() {
    }

    /**
     * Checks whether the complaint has already been solved
     *
     * @returns true if complaint has been solved, otherwise returns false
     */
    public boolean isComplaintSolved() {
        if (complaint.getCurrentStatus() == null) {
            return false;
        } else {
            
            return complaint.getCurrentStatus().equalsIgnoreCase("Solved");
        }
    }

    /**
     * Persists the submitted complaint into the DB
     *
     * @param usernameOfSubmitter
     */
    public void submitComplaint(String usernameOfSubmitter) {
        complaint.setSubmissionTimestamp(new Date());
        complaint.setSubmittedBy(usersFacade.getUserByUsername(usernameOfSubmitter));
        complaint.setAssignedTo(usersFacade.getAdmin());
        complaint.setCurrentStatus("Submitted");

        complaintsFacade.create(complaint);
        JsfUtil.addSuccessMessage("Complaint has been succesfully submitted.");
        notificationFacade.createNotification("Submitted complaint:"+complaint.getComplaintTitle(), complaint.getComplaintBody(), usersFacade.getAdmin());
    }

    /**
     * Used by the admin to assign complaint to a user
     *
     * @param complaintId
     * @param userId
     */
    public void assignComplaintToUser(int complaintId, int userId) {
        complaint = complaintsFacade.find(complaintId);
        user = usersFacade.find(userId);
        complaint.setAssignedTo(user);
        complaintsFacade.edit(complaint);
        notificationFacade.createNotification("Ticket assigned:"+complaint.getComplaintId(), complaint.getComplaintBody(), user);
        JsfUtil.addSuccessMessage("Ticket with id:" + complaint.getComplaintId() + " has been assigned to user:" + user.getName());
    }

    /**
     * Loads complaint details from the db into the variable complaint
     *
     * @param id
     * @return String 'complaintDetails' - this is used to forward the request
     * to the complaintDetails page
     */
    public String loadComplaintDetails(int id) {
        complaint = complaintsFacade.find(id);
        return "complaintDetails";
    }

    /**
     * Sets the current status of complaint to solved and updates the relevant
     * DB entry
     */
    public void closeComplaint() {

        complaint.setCurrentStatus("Solved");
        complaintsFacade.edit(complaint);

        notificationFacade.createNotification("Complaint solved:" + complaint.getComplaintTitle(), complaint.getSolutionTitle(), complaint.getSubmittedBy());
        JsfUtil.addSuccessMessage("Ticket with id:" + complaint.getComplaintId() + " has ben closed");
           // complaint=new Complaints();

    }

    public String clearComplaint() {
        complaint = new Complaints();
        return "submitComplaint";
    }

    @PostConstruct
    public void init() {
        complaint = new Complaints();
        complaints = new ArrayList<>();
        user = new Users();
    }

}
