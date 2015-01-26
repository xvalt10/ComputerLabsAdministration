/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helperbeans;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;

/**
 *
 * @author Tomas
 */
@Stateless
@LocalBean
public class MailSenderBean {
    


   

    @Resource(name = "mail/xvalt10")
    private Session mailSession;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void sendMail(String email, String subject, String body) throws NamingException, MessagingException {
        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject(subject);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
        message.setContent(body, "text/html");
        
        Transport.send(message);
    }

    
}
