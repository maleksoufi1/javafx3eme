/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.EnvoyerMail;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class InscriptionController implements Initializable {


    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_tel;
    @FXML
    private TextField tf_taille;
    @FXML
    private TextField tf_poid;
    @FXML
    private TextField tf_sexe;
    @FXML
    private TextField tf_mail;
    @FXML
    private Button btn_confirm;
    @FXML
    private TextField tf_pwd;

    private ComboBox  roles;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
 void selectRole() {
        
        String s = roles.getSelectionModel().getSelectedItem().toString(); 

    }
    public static String role1;
    
    @FXML
    private void onSave(ActionEvent event) throws Exception {
        
        if(ValidateFields()){
            if (Validatemail()){
                String nom = tf_nom.getText();
        String prenom = tf_prenom.getText();
        if(setnumtel()){
             String tel = tf_tel.getText();
        if(accepterEmail()){
        String email = tf_mail.getText();
           User u11 = new User(nom, prenom, email,tf_pwd.getText(),tf_sexe.getText(),tel,"client","client","e5b251959dfc7ada94fda29c1a00e677");
        UserService us = new UserService();
        us.insertPst(u11);
         
        EnvoyerMail em = new EnvoyerMail();
        em.envoyer(email );
        
        
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("succes");
        alert.setHeaderText(null);
        alert.setContentText("Votre compte a ??t?? cr???? avec succ??s !");
        alert.showAndWait();
        Parent etab = FXMLLoader.load(getClass().getResource("Login.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show(); 

       
        }}}
    }}

  
 @FXML
    private void backToSignIn(ActionEvent event) throws IOException {
        Parent etab = FXMLLoader.load(getClass().getResource("Login.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
   // controle saisie adresse email
    public boolean setmail() {
          boolean ok = false;
        Pattern rfc2822 = Pattern.compile("^[0-9]+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]");
        if (rfc2822.matcher(tf_tel.getText()).matches()) {
            ok = true;
        }
        else {
             Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validate Fields");
        alert.setHeaderText(null);
        alert.setContentText("Veillez entrer une adresse email valide");
        alert.showAndWait();
        }
         return(ok);
    } 
        
        
          private boolean ValidateFields() {
        if(tf_nom.getText().isEmpty() | tf_prenom.getText().isEmpty()|  tf_tel.getText().isEmpty() |  tf_mail.getText().isEmpty()|  tf_pwd.getText().isEmpty() )  {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validate Fields");
        alert.setHeaderText(null);
        alert.setContentText("Please enter into the fields");
        alert.showAndWait();
        return false;
        }
        return true;
        }
           private boolean Validatemail() {
        UserService bs= new UserService();
        if(Integer.parseInt(bs.calculer_nbm(tf_mail.getText())) != 0) {
             Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validate Fields");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez entrer une Adresse email inexistante !");
        alert.showAndWait();
        return false;
        }
        return true;
        }
        // controle saisie numero de telephone
    public boolean setnumtel() {
          boolean ok = false;
        Pattern rfc2822 = Pattern.compile("^[0-9]+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]");
        if (rfc2822.matcher(tf_tel.getText()).matches()) {
            ok = true;
        }
        else {
             Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validate Fields");
        alert.setHeaderText(null);
        alert.setContentText("Veillez entrer un num??ro de t??l??phone valide");
        alert.showAndWait();
        }
         return(ok);
    } 
                     
        //Controle saisie Email    
        public boolean accepterEmail() {
        boolean ok = false;
        String message = "", tex = tf_mail.getText().trim();
        int posiArrobase = 0, posiPoint = 0, posi2 = 0;
 
        if (tex.contains(" ")) {         
               Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Adresse email invalide");
            alert.setHeaderText(null);
            alert.setContentText("L'Adresse email ne doit pas contenir du vide");
            alert.showAndWait();
            return (ok);
        }
        if (message.length() == 0) {
            posiArrobase = tex.indexOf("@");
            if (posiArrobase < 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Adresse email invalide");
            alert.setHeaderText(null);
            alert.setContentText("L'arrobase @ manque dans l'Adresse email");
            alert.showAndWait();
            return (ok);
            }
        if ((posiArrobase == 0) || (tex.endsWith("@"))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Adresse email invalide");
            alert.setHeaderText(null);
            alert.setContentText("L'arrobase @ est mal plac??e dans l'Adresse email");
            alert.showAndWait();
            return (ok);
            }
        if ((posiArrobase > 0) && (posiArrobase < tex.length())) {
            posi2 = tex.indexOf("@",posiArrobase+1);
            if (posi2 > posiArrobase) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Adresse email invalide");
            alert.setHeaderText(null);
            alert.setContentText("Double arrobase @ manque dans l'Adresse email");
            alert.showAndWait();
            return (ok);
                }
            }
            if (message.length() == 0) {
                posiPoint = tex.indexOf(".");
                if (posiPoint == -1) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Adresse email invalide");
            alert.setHeaderText(null);
            alert.setContentText("L'Adresse email doit contenir au moin un point");
            alert.showAndWait();
            return (ok);
                }
                if ((posiPoint == 0) || (tex.endsWith(".")))  {
                Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Adresse email invalide");
            alert.setHeaderText(null);
            alert.setContentText("Le point est mal plac??e dans l'Adresse email");
            alert.showAndWait();
            return (ok);
                }
            }
            if (message.length() == 0) {
                if (tex.length() == 0) {
                    message = " l'adresse email est vide ";
                Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Adresse email invalide");
            alert.setHeaderText(null);
            alert.setContentText("L'Adresse email est vide");
            alert.showAndWait();
            return (ok);
                }
            }
        }
        if (message.length() == 0) {
            ok = true;
        }
        else {
            JOptionPane.showMessageDialog(null,message);
            //tf_email.requestFocusInWindow();
        }
         return(ok);
        }
        
}
