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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class MesReclamationController implements Initializable {

    @FXML
    private HBox hbox_affichage;
    @FXML
    private ScrollPane scroll_affichage;
    @FXML
    private GridPane grid_affichage;
    
    
   
    private Label cnt;
    @FXML
    private Label titer_req;
    @FXML
    private Label contenu_req;

    public Label getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt.setText(cnt);
    }
    
       
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        //this.cnt.setText("firas");
        List<Reclamation> liste= new ArrayList<Reclamation>();
        ReclamationService  RS= new ReclamationService();
        liste=RS.MesReclamations();
        
        
        
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < liste.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemReclamation.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemReclamationController itemController = fxmlLoader.getController();
                itemController.setData(liste.get(i));
                grid_affichage.add(anchorPane, 0, row); //(child,column,row)
                
                   
                    row++;
                

               
            }
        }
           catch (IOException e) {
            e.printStackTrace();
        }
        
    }
public void Afficher_Contenu(Reclamation r)
{
   
    System.out.println(r.getContenu());
    this.cnt.setText(r.getContenu());
}

    @FXML
    private void redirectToEnvoi(ActionEvent event) {
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
    private void directToProduit(ActionEvent event) {
    }

    @FXML
    private void directToRegime(ActionEvent event) {
    }

    @FXML
    private void directToProgramme(ActionEvent event) {
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

    
}
