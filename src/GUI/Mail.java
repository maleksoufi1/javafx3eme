/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Kouki
 */
public class Mail {

    public static void sendMail(String recepient, String objet, String contenu) {

    }
    
public static void sendMail(String recepient, int code, String text) throws MessagingException {
Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "houssem.kouki@esprit.tn";
        String myAccountPassword = "211JMT2261";
        
          Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, myAccountPassword);
            }
        });
            if (code ==1) { // si le code=1 NV régime
            Message message = prepareMessageNouvelleRegimeAjoute(session, myAccountEmail, recepient);
            Transport.send(message);
                System.out.println("mail envoyé ! ");
        }
            
             if (code ==2) { // si le code=2 NV repas
            Message message = prepareMessageNouvelleRepasAjoute(session, myAccountEmail, recepient);
            Transport.send(message);
                System.out.println("mail envoyé ! ");
        }
}




private static Message prepareMessageNouvelleRegimeAjoute(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Régime ajouté");
            message.setText("Bonjour cher nutritionniste,\n  Votre régime a été bien enregistrer,   \n  Vérifier la liste de votre régimes \n Cordialement");
            return message;

        } catch (Exception ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


private static Message prepareMessageNouvelleRepasAjoute(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Nouvelle Repas");
            message.setText("Bonjour cher client,\n  Votre nutritionniste a vous a ajouter un nouveau repas,   \n  Vérifier la liste de votre repas \n Cordialement");
            return message;

        } catch (Exception ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}