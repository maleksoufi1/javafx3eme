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
import javafx.scene.control.TextField;
import services.ReclamationService;
import services.ReponseReclamationService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ModifierRep_ReclamationController implements Initializable {

    @FXML
    private TextArea mod_contenu;
    @FXML
    private Button but_mod;
    @FXML
    private Label req_contenu;
    
    private ReponseReclamation r;
    @FXML
    private Label contenu_req;
 public ReponseReclamation getR() {
        return r;
    }

    public void setR(ReponseReclamation r) {
        this.r = r;
        this.mod_contenu.setText(r.getContenu());
   
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void update_rec(ActionEvent event) {
     
            if(mod_contenu.getText().equals(""))
         {
            req_contenu.setText("contenu obligatoire");
             return;
         }
         int user_id=1;
         ReponseReclamation r=new ReponseReclamation(this.r.getId(),mod_contenu.getText());
         //System.out.println(r);
         ReponseReclamationService  ps =new ReponseReclamationService();
         ps.ModifierReponseReclamation(r);
         
         try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeReponse.fxml"));
               Parent root=loader.load();
               
            
            req_contenu.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(ModifierReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }

 
    @FXML
    private void RedirectToReponse(ActionEvent event) {
    }

    @FXML
    private void redirectToProduit(ActionEvent event) {
    }

    @FXML
    private void RedirectToRégime(ActionEvent event) {
    }

    @FXML
    private void RedirectToProgramme(ActionEvent event) {
    }

    @FXML
    private void RedirectToReclamation(ActionEvent event) {
        try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("Boite_Reclamation.fxml"));
               Parent root=loader.load();
               
            
            mod_contenu.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void redirectToMesReclamation(ActionEvent event) {
    }
    
}
