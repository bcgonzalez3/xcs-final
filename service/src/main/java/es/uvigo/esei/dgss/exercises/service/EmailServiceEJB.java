package es.uvigo.esei.dgss.exercises.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import es.uvigo.esei.dgss.exercises.domain.User;

@Stateless
public class EmailServiceEJB {
	 
    @Resource(name = "java:jboss/mail/gmail")
    private Session session;
 
	public void sendEmail(User u, String subject, String body){
        try {
        	String addresses = u.getLogin();
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
            message.setSubject(subject);
            message.setText(body);
 
            Transport.send(message);
 
        } catch (MessagingException e) {
            Logger.getLogger(EmailServiceEJB.class.getName()).log(Level.WARNING, "Cannot send mail", e);
        }
	}

}
