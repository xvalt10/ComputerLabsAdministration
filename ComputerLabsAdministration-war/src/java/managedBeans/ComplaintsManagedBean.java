/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Complaints;
import entities.Users;
import helperClasses.JsfUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessionBeans.ComplaintsFacade;
import sessionBeans.UsersFacade;

/**
 *
 * @author Tomas
 */
@ManagedBean
@SessionScoped
public class ComplaintsManagedBean {
    @EJB
    private ComplaintsFacade complaintsFacade;
    @EJB
    private UsersFacade usersFacade;
    
    
    
    
    private Users user;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    private Complaints complaint;
    private List<Complaints> complaints;

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
    
      public String getSubmittedComplaintsByUser(){
          complaints.clear();
    complaints= complaintsFacade.findSubmittedComplaintsByUser();
    return "viewComplaints";
    }
      
       public String getComplaintsAssignedToUser(){
          complaints.clear();
    complaints= complaintsFacade.findComplaintsAssignedToUser();
    return "viewComplaints";
    }

    /**
     * Creates a new instance of ComplaintsManagedBean
     */
    public ComplaintsManagedBean() {
    }
    
    public boolean isComplaintSolved(){
        if(complaint.getCurrentStatus()==null){return false;}
        else{
    return complaint.getCurrentStatus().equalsIgnoreCase("Solved");}
    }
    
    public void submitComplaint(String usernameOfSubmitter){
        complaint.setSubmissionTimestamp(new Date());
        complaint.setSubmittedBy(usersFacade.getUserByUsername(usernameOfSubmitter));
        complaint.setAssignedTo(usersFacade.getAdmin());
        complaint.setCurrentStatus("Submitted");
        
        complaintsFacade.create(complaint);
        
        
        JsfUtil.addSuccessMessage("Complaint has been succesfully submitted.");
        
        complaint=new Complaints();
    
    }
    
    public void assignComplaintToUser(int complaintId,int userId){
    complaint=complaintsFacade.find(complaintId);
    user=usersFacade.find(userId);
    complaint.setAssignedTo(user);
    complaintsFacade.edit(complaint);
    JsfUtil.addSuccessMessage("Ticket with id:"+complaint.getComplaintId()+" has ben assigned to user:"+user.getName());
    }
    
    public String loadComplaintDetails(int id){
    complaint=complaintsFacade.find(id);
    return "complaintDetails";
    }
    
    public void closeComplaint(){
    complaint.setCurrentStatus("Solved");
    complaintsFacade.edit(complaint);
    
    JsfUtil.addSuccessMessage("Ticket with id:"+complaint.getComplaintId()+" has ben closed");
    
    }
    
    @PostConstruct
    public void init(){
    complaint=new Complaints();
    complaints=new ArrayList<>();
    user=new Users();
    }
    
    
}
