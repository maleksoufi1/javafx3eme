/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Reclamation;
import entites.ReponseReclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import services.MailService;
import services.ReclamationService;
import services.ReponseReclamationService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjoutReponseReclamationController implements Initializable {

    @FXML
    private TextArea contenu;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Label titer_req;
    @FXML
    private Label contenu_req;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save(ActionEvent event) {
        
         titer_req.setText("");
       contenu_req.setText("");
        
            
            if(contenu.getText().equals(""))
         {
            contenu_req.setText("contenu obligatoire");
             return;
         }
         int user_id=1;
         int Reclamation_id=6;
         ReponseReclamation r=new ReponseReclamation(contenu.getText(),user_id,Reclamation_id);
         ReponseReclamationService  ps =new ReponseReclamationService();
         ps.InsertReponse(r);
         MailService  mail =new MailService();
            mail.SendMail();
        try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeReponse.fxml"));
               Parent root=loader.load();
               
            
            contenu.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(MesReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
         
            
    }
    
}
