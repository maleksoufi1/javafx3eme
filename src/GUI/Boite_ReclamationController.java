/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class Boite_ReclamationController implements Initializable {

    @FXML
    private ScrollPane liste_reclamation;
    @FXML
    private GridPane grid_affichage;
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
        List<Reclamation> liste= new ArrayList<Reclamation>();
        ReclamationService  RS= new ReclamationService();
        liste=RS.getAll();
        
        
        
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < liste.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Item_boite.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

              Item_boiteController Item_boiteController = fxmlLoader.getController();
               Item_boiteController.setData(liste.get(i));
                grid_affichage.add(anchorPane, 0, row); //(child,column,row)
                
                   
                    row++;
                

               
            }
        }
           catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void redirectToEnvoi(ActionEvent event) {
    }


    @FXML
    private void directToProduit(ActionEvent event) {
    }

    @FXML
    private void directToRegime(ActionEvent event) {
    }

    @FXML
    private void directToProgramme(ActionEvent event) {
    }

    @FXML
    private void directToMesReponse(ActionEvent event) 
    {
        
          try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeReponse.fxml"));
               Parent root=loader.load();
               
            
            liste_reclamation.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
