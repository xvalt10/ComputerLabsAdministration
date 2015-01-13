/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Complaints;
import entities.ComputerLabs;
import entities.Timeslot;
import entities.UserRoles;
import entities.Users;
import helperClasses.JsfUtil;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import sessionBeans.ComputerLabsFacade;
import sessionBeans.ScheduleFacade;
import sessionBeans.UserRolesFacade;
import sessionBeans.UsersFacade;

/**
 *
 * @author Tomas
 */
@ManagedBean
@SessionScoped
public class UserManagedBean {
    @EJB
    private ComputerLabsFacade computerLabsFacade;
    @EJB
    private ScheduleFacade scheduleFacade;
    
    @EJB
    private UserRolesFacade userRolesFacade;
    
    @EJB
    private UsersFacade usersFacade;
    
    
    
    
    
    
    private Users user;
    
    private List<Users> usersList;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }
    
    

    /**
     * Creates a new instance of UserManagedBean
     */
    public UserManagedBean() {
        
        
    }
    
    public boolean isUserInRole(String role){
    return usersFacade.getUserByUsername(JsfUtil.getUserNameOfLoggedInUser()).getRoleId().getRoleName().equalsIgnoreCase(role);
    
    }
    
    public String getInstructorNameForTimeSlot(Timeslot timeSlot){
        Integer labId = scheduleFacade.findLabByTimeslot(timeSlot);
        if (labId==null){
        return "";
        }
        ComputerLabs lab = computerLabsFacade.find(labId);
        return lab.getInstructor().getName();
    }
    
public List<Users> getInstructorsByDepartment(){
        String department=usersFacade.getUserByUsername(JsfUtil.getUserNameOfLoggedInUser()).getDepartment();
        List<Users> instructorsByDepartment = usersFacade.findInstructorsByDepartment(department);
        return instructorsByDepartment;
    }

public List<Users> getTechnicalStaffUsers(){
      return usersFacade.findUsersWithRoleTechnicalStaff();
    }
    
    public void registerUser(){
        
        //user.setUserId(usersFacade.findMaxId()+1);
        user.setPassword(encryptPassword(user.getPassword()));
    usersFacade.create(user);
    
        JsfUtil.addSuccessMessage("User "+user.getUsername()+ "has been successfully registered.");
    user=new Users();
    }
    
    public UserRoles loadUserRole(int roleId){
    return userRolesFacade.find(roleId);
    }

    
    
    @PostConstruct
    public void init(){
    user=new Users();
    usersList=new ArrayList<>();
    }
    
    public String clearUserDetails(){
    user=new Users();
    return "registerUser";
    }
    
  
    public String getUserRole(){
        Principal principal=FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        
        if(principal==null){
       return "no user found.";
       }
        else{
            return userRolesFacade.getUserRoleByUsername(principal.getName());}
    }
        public String encryptPassword(String password) {
        MessageDigest md;
        StringBuilder sb = new StringBuilder();
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sb.toString();
    }
    
}
