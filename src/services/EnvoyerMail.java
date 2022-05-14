/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author asus
 */


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
    public class EnvoyerMail {
    private final String username = "adomifit.123@gmail.com";
    private final String password = "Malek123456";

    public void envoyer(String ml) {
    // Etape 1 : Création de la session
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable","true");
    props.put("mail.smtp.host","smtp.gmail.com");
    props.put("mail.smtp.port","587");
    Session session = Session.getInstance(props,
    new javax.mail.Authenticator() {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(username, password);
    }
    });

    try {
    // Etape 2 : Création de l'objet Message
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress("adomifit.123@gmail.com"));
    message.setRecipients(Message.RecipientType.TO,
    InternetAddress.parse(ml));
    message.setSubject("Bienvenu");
    message.setText("Bienvenu chez Adomifit !   "
            + "Nous sommes heureux de vous compter parmi nos clients !"
            + "Voici votre code d'activation de compte lors de la prochaine connection");
    // Etape 3 : Envoyer le message
    Transport.send(message);
    System.out.println("Message_envoye");
    } catch (MessagingException e) {
    throw new RuntimeException(e);
    } }
    //Etape 4 : Tester la méthode
    public static void main(String[] args) {
    EnvoyerMail test = new EnvoyerMail();
    test.envoyer();
    } 

    private void envoyer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}