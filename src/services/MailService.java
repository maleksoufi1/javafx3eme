/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

  import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 *
 * @author Asus
 */
public class MailService 
{


public static void SendMail(){
  
    String host="firas.benhajyedder@esprit.tn";  //← my email address
        final String user="firas.benhajyedder@esprit.tn";//← my email address
        final String password="213JMT0016";//change accordingly   //← my email password

        String to="firasbenhajyedder@hotmail.com";//→ the EMAIL i want to send TO →

        // session object
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });

        //My message :
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(" NOTIFICATION  !!! ");
            //Text in emial :
            //message.setText("  → Text message for Your Appointement ← ");
            //Html code in email :
            message.setContent(
                    "<h1 style =\"color:red\" >vous avez une réponse à votre réclamation consulter notre application !! </h1> <br/> <img width=\"50%\" height=\"50%\" src=https://i.imgur.com/iYcBkOf.png>",
                    "text/html");

            //send the message
            Transport.send(message);

            System.out.println("message sent successfully via mail ... !!! ");

        } catch (MessagingException e) {e.printStackTrace();}

    }
}
