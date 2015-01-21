/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Hardware;
import entities.Notification;
import entities.Software;
import entities.Users;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author t.valkovic
 */
@Stateless
public class NotificationFacade extends AbstractFacade<Notification> {
    @EJB
    private UsersFacade usersFacade;
    @EJB
    private SoftwareFacade softwareFacade;
    @EJB
    private HardwareFacade hardwareFacade;
    
    
    

    @PersistenceContext(unitName = "ComputerLabsAdministration-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotificationFacade() {
        super(Notification.class);
    }

    public void createNotification(String title, String text, Users user) {
        Notification notification = new Notification();
        notification.setCreationTimestamp(new Date());
        notification.setNotificationTitle(title);
        notification.setNotificationText(text);
        notification.setUserId(user);
        notification.setNotificationWasRead(false);
        create(notification);

    }
    
        @Schedule(dayOfWeek = "*", hour = "8", minute = "0", persistent = false)
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
        
     createNotification("HW warranty expiration alert", notificationText.toString(),usersFacade.getAdmin() );
        
    }
    
    @Schedule(dayOfWeek = "*", hour = "8", minute = "5", persistent = false)
    public void listSoftwareWithExpiredSoftware() {
        List<Software> softwareWithExpiredWarranty = softwareFacade.findSoftwareWithExpiredLicence();
        StringBuilder notificationText = new StringBuilder();
        for (Software software : softwareWithExpiredWarranty) {
            notificationText.append("Software name:").append(software.getType()).append("\n")
                   .append("Licence expiration date:").append(software.getExpirationOfLicence()).append("\n\n");
        }
        
        createNotification("SW licence expiration alert", notificationText.toString(),usersFacade.getAdmin() );
        
    }

    public Integer getCountOfUnreadNotifications(Users user){
       return  (Integer) em.createNativeQuery("select count(*) from Notification where notificationWasRead=0 and userId=?")
                .setParameter(1, user.getUserId()).getSingleResult();
    }
    
    public List<Notification> getAllNotificationsByUser(Users user) {

        return em.createNamedQuery("Notification.findByUserId", Notification.class).setParameter("userId", user).getResultList();
    }

}
