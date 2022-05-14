/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Reclamation;
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

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class Item_boiteController implements Initializable {

    @FXML
    private Label time_label;
    @FXML
    private Hyperlink titre;
 private  Reclamation rec;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }    
  public void setData(Reclamation r) {
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
        titre.setText(rec.getTitre());
       time_label.setText(labelT);
      
    }
    @FXML
    private void Repondre_Reclamation(ActionEvent event) 
    {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Detail_Reclamation_Admin.fxml"));
        Parent root=loader.load();
        Detail_Reclamation_AdminController MesRecController = loader.getController();
              
            MesRecController.Afficher_Contenu(rec);
             titre.getScene().setRoot(root);
        //MesRecController.Afficher_Contenu(this.getRec());
        }
        
      catch (Exception e) {
            e.printStackTrace();
        }
    }

  
    
}
