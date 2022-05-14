/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.User;
import java.io.IOException;
import java.net.URL;
import java.security.Key;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.MailService1;
import services.UserService;
import javax.mail.MessagingException;
import static services.hashing.decrypt;
import static services.hashing.encrypt;


/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ForgotPasswordController implements Initializable {

    @FXML
    private TextField tf_email;
    @FXML
    private Label lab1;
    @FXML
    private Label lab2;
    @FXML
    private Button btn_signUp;
    @FXML
    private Button btn_fp;
  public static int id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


      @FXML
    private void signUp(ActionEvent event) throws IOException {
        Parent etab = FXMLLoader.load(getClass().getResource("Inscription.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void forgotP(ActionEvent event) throws SQLException, MessagingException, Exception  {
       List<User> p = new ArrayList<>();
        UserService u = new UserService();
        p.addAll(u.getpwd(tf_email.getText()));
         Key key = services.hashing.generateKey();
       
        String pwd= decrypt(p.get(0).getPassword(),key);
        System.out.println(p);

        String from = "adomifit.123@gmail.com";
        String pass = "Malek123456";
        String[] to = {tf_email.getText()}; // list of recipient email addresses
        String subject = "Adomifit récupération du mot de passe";
        String body = "Votre addresse : " + tf_email.getText() + "/n Votre  mot de passe est "
                + ": " + pwd;
        MailService1.sendFromGMail(from, pass, to, subject, body);

    }

    }
    

