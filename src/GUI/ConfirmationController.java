/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import entites.User;
import static GUI.LoginController.u;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.MailService1;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class ConfirmationController implements Initializable {

    
    @FXML
    private TextField tftoken;
    @FXML
    private Label lbconf;
 public static User a;
 static int  i =3;
 int j =3;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ConfirmAccount(ActionEvent event) throws SQLException, IOException {
        
        UserService us = new UserService();
        System.out.println(u.getEmail());
        if(us.Checkconfirmationtoken(u.getEmail(),tftoken.getText())){
         us.ConfirmAccount(u.getEmail(),tftoken.getText());
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("succes");
        alert.setHeaderText(null);
        alert.setContentText("Votre compte a été activé  !");
        alert.showAndWait();
        Parent etab = FXMLLoader.load(getClass().getResource("Login.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show(); 
    }else if (i!=0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("echec");
        alert.setHeaderText(null);
        alert.setContentText("code d'activation est incorrect vous reste "+i+" tetative");
        i=i-1;
        alert.showAndWait();
    
    }
        else if(i==0) {
             us.banUtilisateurs(u.getEmail());
             Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Bloque");
        alert.setHeaderText(null);
        alert.setContentText("Votre compte est bloque");
       alert.showAndWait();
        Parent etab = FXMLLoader.load(getClass().getResource("Login.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show(); 
                
                }
        
                

}

    @FXML
    private void RenvoyerCode(ActionEvent event) {
        String email = u.getEmail();
        
        
        
        
        List<User> p = new ArrayList<>();
        UserService u = new UserService();
        
         
         // String ac =u.getActivationToken(email);
       String ac= u.activationToken(email).getRole();
       
        String from = "adomifit.123@gmail.com";
        String pass = "Malek123456";
        String[] to = {email}; // list of recipient email addresses
        String subject = "Ceci est un e-mail du service client de adomifit ";
        String body = " Cher utilisateur,Nous sommes heureux que"
                + " vous ayez rejoint notre communauté de personnes formidables."
                  + "code ci-dessous.Merci pour votre confiance,"
                + "  Votre équipe Adomifit votre addresse :"
                + " " + email + "  votre code est d'activation : "+ac ;
        MailService1.sendFromGMail(from, pass, to, subject, body);
    }
}

