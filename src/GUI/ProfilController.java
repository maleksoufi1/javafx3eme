/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.User;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ProfilController implements Initializable {

    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_prenom;
    @FXML
    private TextField tf_tel;
    @FXML
    private TextField tf_email;
    @FXML
    private PasswordField tf_pwd;
    @FXML
    private Button btn_conf;
    @FXML
    private Button btn_listuser;
    @FXML
    private Button btn_evenement;
    @FXML
    private Button btn_produit;
    @FXML
    private Button btn_reclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         UserService cs =new UserService();
        User c =cs.getClientById(LoginController.u.getId());
       
        tf_email.setText( c.getEmail());
        tf_nom.setText(c.getNom());
        tf_prenom.setText(c.getPrenom());
       
        tf_tel.setText(c.getTel());
      

    }    

    @FXML
    private void confirm(ActionEvent event) throws Exception {
        UserService cs = new UserService();
        User c = new User(LoginController.u.getId(),tf_nom.getText(), tf_prenom.getText(), tf_email.getText(), tf_pwd.getText(),tf_tel.getText(),"client");
        cs.modifierProfile(c);
        System.out.println("compte modifie");
     
        tf_email.setText("");
        tf_nom.setText("");
        tf_prenom.setText("");
        tf_pwd.setText("");
        tf_tel.setText("");
         Parent etab = FXMLLoader.load(getClass().getResource("Login.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
      
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Parent etab = FXMLLoader.load(getClass().getResource("Login.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    
    @FXML
    private void showListeUtilisateurs(ActionEvent event) throws IOException {
         Parent etab = FXMLLoader.load(getClass().getResource("Admin.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void showListEvenement(ActionEvent event) throws IOException {
         Parent etab = FXMLLoader.load(getClass().getResource("Billet.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void showListeProduits(ActionEvent event) throws IOException {
         Parent etab = FXMLLoader.load(getClass().getResource("Produit.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void showListeReclamation(ActionEvent event) throws IOException {
        Parent etab = FXMLLoader.load(getClass().getResource("Afficher.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void showListCtegorieRegime(MouseEvent event) {
    }

    @FXML
    private void showMesRegimes(MouseEvent event) {
    }

    @FXML
    private void showStatistique(MouseEvent event) {
    }

    @FXML
    private void showListeSuivis(MouseEvent event) {
    }

}
