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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.UserService;
import static services.hashing.encrypt;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class LoginController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private TextField tf_email;
    @FXML
    private Button btn_signIn;
    @FXML
    private PasswordField tf_pwd;
    @FXML
    private Label lab1;
    @FXML
    private Label lab2;
    @FXML
    private Button btn_signUp;
    public static User u;
    @FXML
    private Button btn_fp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   

    
     @FXML
    private void signIn(ActionEvent event) throws IOException, Exception {
        
        Key key = services.hashing.generateKey();
        String email = tf_email.getText();
        String pwd= encrypt(tf_pwd.getText(),key);
        UserService cs = new UserService();
        
        if(cs.signIn(email, pwd)!=null){
            
//                u=cs.signIn(email, pwd);
//                Parent etab = FXMLLoader.load(getClass().getResource("Confirmation.fxml"));      
//                Scene scene = new Scene(etab);
//                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//                window.setScene(scene);
//                window.show(); 
//                
                
                
          
            if(cs.signIn(email, pwd).isRole().equals("[\"ROLE_CLIENT\"]") ){
                u=cs.signIn(email, pwd);
                Parent etab = FXMLLoader.load(getClass().getResource("Front.fxml"));      
                Scene scene = new Scene(etab);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
            else if(cs.signIn(email, pwd).isRole().equals("[\"ROLE_NUTRISIONNISTE\"]")){
                u=cs.signIn(email, pwd);
                Parent etab = FXMLLoader.load(getClass().getResource("CategorieRegime.fxml"));      
                Scene scene = new Scene(etab);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
                
            }
            else if(cs.signIn(email, pwd).isRole().equals("[\"ROLE_COACH\"]")){
                u=cs.signIn(email, pwd);
                Parent etab = FXMLLoader.load(getClass().getResource("Programme.fxml"));      
                Scene scene = new Scene(etab);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
                
            }
            else if(cs.signIn(email, pwd).isRole().equals("[\"ROLE_ADMIN\"]")){
                u=cs.signIn(email, pwd);
                Parent etab = FXMLLoader.load(getClass().getResource("Admin.fxml"));      
                Scene scene = new Scene(etab);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
                
            }
            else if(cs.signIn(email, pwd).isRole().equals("[\"ROLE_BLOQUE\"]")){
                 u=cs.signIn(email, pwd);
                 Parent etab = FXMLLoader.load(getClass().getResource("Login.fxml"));      
                Scene scene = new Scene(etab);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
                 Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("compte Bloque");
                alert.setHeaderText(null);
                alert.setContentText("votre compte est bloquer pour plus d'info contactez nous sur Adomifit.support@adomifit.com ");
               alert.showAndWait();
               
                
            }
            else {
               
              u=cs.signIn(email, pwd);
                Parent etab = FXMLLoader.load(getClass().getResource("Confirmation.fxml"));      
                Scene scene = new Scene(etab);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();   
            }
                
        }
        else{
            lab1.setVisible(true);
            lab2.setVisible(true);
            
        };
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
    private void forgotPassword(ActionEvent event) throws IOException {
         Parent etab = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    
}
