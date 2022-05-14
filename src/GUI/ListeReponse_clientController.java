/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import services.ReponseReclamationService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ListeReponse_clientController implements Initializable {

    @FXML
    private HBox hbox_affichage;
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
        // TODO
        List<ReponseReclamation> liste=new ArrayList<>();
        ReponseReclamationService rs= new ReponseReclamationService();
       liste= rs.getUserReponse();
        
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < liste.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemRep_client.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                
                ItemRep_clientController itemController = fxmlLoader.getController();
                itemController.setData(liste.get(i));
                grid_affichage.add(anchorPane, 0, row); //(child,column,row)
                
                   
                    row++;
                

               
            }
        }
           catch (IOException e) {
            e.printStackTrace();
        }
    }    

    @FXML
    private void directToMesReclamation(ActionEvent event) {
         try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("MesReclamation.fxml"));
               Parent root=loader.load();
               
            
            contenu_req.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(ModifierReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void directToProduit(ActionEvent event) {
          try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("MesReclamation.fxml"));
               Parent root=loader.load();
               
            
            contenu_req.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(ModifierReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void directToRegime(ActionEvent event) {
          try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("MesReclamation.fxml"));
               Parent root=loader.load();
               
            
            contenu_req.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(ModifierReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void directToProgramme(ActionEvent event) {
          try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("MesReclamation.fxml"));
               Parent root=loader.load();
               
            
            contenu_req.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(ModifierReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void redirectToEnvoi(ActionEvent event) 
    {
            try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutReclamation.fxml"));
               Parent root=loader.load();
               
            
            contenu_req.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(ModifierReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
    }
    
}
