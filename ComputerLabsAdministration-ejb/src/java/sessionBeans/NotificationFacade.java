/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entities.Notification;
import entities.Users;
import java.util.Date;
import java.util.List;
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

    public int getCountOfUnreadNotifications(Users user){
       return (int) em.createNativeQuery("select count(*) from Notification where notificationWasRead=0 and userId=?")
                .setParameter(1, user).getSingleResult();
    }
    
    public List<Notification> getAllNotificationsByUser(Users user) {

        return em.createNamedQuery("Notification.findByUserId", Notification.class).setParameter("userId", user).getResultList();
    }

}
