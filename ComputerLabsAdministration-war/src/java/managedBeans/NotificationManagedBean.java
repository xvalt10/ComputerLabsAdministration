/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entities.Hardware;
import entities.Notification;
import entities.Software;
import entities.Users;
import helperClasses.JsfUtil;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.faces.bean.ManagedBean;
import sessionBeans.HardwareFacade;
import sessionBeans.NotificationFacade;
import sessionBeans.SoftwareFacade;
import sessionBeans.UsersFacade;

/**
 *
 * @author t.valkovic
 */
@ManagedBean
@SessionScoped
public class NotificationManagedBean implements Serializable {
    
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private HardwareFacade hardwareFacade;
     @EJB
    private SoftwareFacade softwareFacade;
    @EJB
    private NotificationFacade notificationFacade;
    

    private Notification notification;

    /**
     * Creates a new instance of NotificationManagedBean
     */
    public NotificationManagedBean() {

    }
    
    public Integer countOfUnreadNotifications(){
    String username=JsfUtil.getUserNameOfLoggedInUser();
    return notificationFacade.getCountOfUnreadNotifications(usersFacade.getUserByUsername(username));
    }
    
    public List<Notification> getNotificationsForCurrentUser(){
        String username=JsfUtil.getUserNameOfLoggedInUser();
    return notificationFacade.getAllNotificationsByUser(usersFacade.getUserByUsername(username));
    }



    @PostConstruct
    public void init() {
        notification = new Notification();
    }

}
