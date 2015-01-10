/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Complaints;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Tomas
 */
@ManagedBean
@SessionScoped
public class ComplaintsManagedBean {
    
    private Complaints complaint;

    /**
     * Creates a new instance of ComplaintsManagedBean
     */
    public ComplaintsManagedBean() {
    }
    
    @PostConstruct
    public void init(){
    complaint=new Complaints();
    }
}
