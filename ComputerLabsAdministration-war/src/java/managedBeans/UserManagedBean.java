/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.UserRoles;
import entities.Users;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
    
    public void registerUser(){
        
    user.setUserId(usersFacade.findMaxId()+1);
    usersFacade.create(user);
    
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
    
}
