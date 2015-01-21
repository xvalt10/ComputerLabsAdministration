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
    
    public int countOfUnreadNotifications(){
    String username=JsfUtil.getUserNameOfLoggedInUser();
    return notificationFacade.getCountOfUnreadNotifications(usersFacade.getUserByUsername(username));
    }
    
    public List<Notification> getNotificationsForCurrentUser(){
        String username=JsfUtil.getUserNameOfLoggedInUser();
    return notificationFacade.getAllNotificationsByUser(usersFacade.getUserByUsername(username));
    }

    @Schedule(dayOfWeek = "*", hour = "18", minute = "0", persistent = false)
    public void listHardwareWithExpiredWarranty() {
        List<Hardware> hardwareWithExpiredWarranty = hardwareFacade.findHardwareWithExpiredWarranty();
        StringBuilder notificationText = new StringBuilder();
        for (Hardware hardware : hardwareWithExpiredWarranty) {
            notificationText.append("Hardware name:").append(hardware.getType()).append("\n")
                   .append("Hardware location:")
                   .append("classroom no.").append(hardware.getClassRoomId().getRoomNumber())
                   .append(",seat number:").append(hardware.getSeatNo()).append("\n")
                   .append("Warranty expiration date:").append(hardware.getExpirationOfWarranty()).append("\n\n");
        }
        
        notificationFacade.createNotification("HW warranty expiration alert", notificationText.toString(),usersFacade.getAdmin() );
        
    }
    
    @Schedule(dayOfWeek = "*", hour = "18", minute = "0", persistent = false)
    public void listSoftwareWithExpiredSoftware() {
        List<Software> softwareWithExpiredWarranty = softwareFacade.findSoftwareWithExpiredLicence();
        StringBuilder notificationText = new StringBuilder();
        for (Software software : softwareWithExpiredWarranty) {
            notificationText.append("Software name:").append(software.getType()).append("\n")
                   .append("Licence expiration date:").append(software.getExpirationOfLicence()).append("\n\n");
        }
        
        notificationFacade.createNotification("SW licence expiration alert", notificationText.toString(),usersFacade.getAdmin() );
        
    }

    @PostConstruct
    public void init() {
        notification = new Notification();
    }

}
