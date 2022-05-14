/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.ReponseReclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import services.ReclamationService;
import services.ReponseReclamationService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class Item_repController implements Initializable {

    @FXML
    private Label time_label;
    @FXML
    private Button sup_but;
    @FXML
    private Button mod_but;
    private ReponseReclamation rec;
    @FXML
    private Hyperlink contenu;
    @FXML
    private Label user_label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setData(ReponseReclamation r) {
        this.rec = r;
          Timestamp now = Timestamp.from(Instant.now());
     
          Long DeffTime=(Math.abs(now.getTime () - rec.getCreatedAt().getTime ())/1000)/60;
          String labelT="";
          if(DeffTime<60)
          {
             labelT=DeffTime.toString()+"min";
          }
          else
          {
            Long heure=DeffTime/60;
            if(heure>=24)
            {
              Long jours=heure/24;
              labelT=jours.toString()+" j";
            }else
            {
            Long minute=DeffTime%60;
            labelT=heure.toString()+"h "+minute.toString()+"min";
            }
          }
        contenu.setText(rec.getContenu());
       
       time_label.setText(labelT);
      
    }

    @FXML
    private void Envoyer_Rep_Reclamation(ActionEvent event) {
    }

    @FXML
    private void Supprimer_Rep_reclamation(ActionEvent event) 
    {
        ReponseReclamationService  ps =new ReponseReclamationService();
         ps.SupprimerReponseReclamation(this.rec.getId());
          try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeReponse.fxml"));
               Parent root=loader.load();
               
            
            mod_but.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(Item_repController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }

    @FXML
    private void Modifier_Rep_reclamation(ActionEvent event) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierRep_Reclamation.fxml"));
        
            Parent root=loader.load();
        
           
        
        ModifierRep_ReclamationController MRecController = loader.getController();
         MRecController.setR(rec);
            
        contenu.getScene().setRoot(root);
        } catch (IOException ex) {
             Logger.getLogger(ItemReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
