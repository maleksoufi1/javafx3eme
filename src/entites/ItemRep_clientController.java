/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import GUI.*;
import entites.ReponseReclamation;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ItemRep_clientController implements Initializable {

    @FXML
    private Label time_label;
    @FXML
    private Hyperlink contenu;
    private ReponseReclamation rec;

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
    private void rep_detail(ActionEvent event) {
        
    }
    
}
