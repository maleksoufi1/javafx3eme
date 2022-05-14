/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Reclamation;
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
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class DetailReclamationController implements Initializable {

    @FXML
    private Label titre;
    @FXML
    private Label Contenu;
    @FXML
    private HBox hbox_affichage;
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
    
    public void Afficher_Contenu(Reclamation r)
{
   
    System.out.println(r.getContenu());
      this.titre.setText(r.getTitre());

    this.Contenu.setText(r.getContenu());
   
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
    @FXML
     private void redirectToReponse(ActionEvent event) {
         try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeReponse_client.fxml"));
               Parent root=loader.load();
               
            
            contenu_req.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(ModifierReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    
}
