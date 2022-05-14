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
import services.ReponseReclamationService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ListeReponseController implements Initializable {

    @FXML
    private ScrollPane scroll_affichage;
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
         List<ReponseReclamation> liste= new ArrayList<ReponseReclamation>();
        ReponseReclamationService  RS= new ReponseReclamationService();
        liste=RS.getAll();
         
        
        
        
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < liste.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item_rep.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                
                Item_repController itemController = fxmlLoader.getController();
                itemController.setData(liste.get(i));
                grid_affichage.add(anchorPane, 0, row); //(child,column,row)
                
                   
                    row++;
                

               
            }
        }
           catch (IOException e) {
            e.printStackTrace();
        }
        // TODO
    }    


    @FXML
    private void directToMesReclamation(ActionEvent event) {
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
    private void redirectToReclamation(ActionEvent event) {
        
         try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("Boite_Reclamation.fxml"));
               Parent root=loader.load();
               
            
            scroll_affichage.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
