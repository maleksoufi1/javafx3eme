/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Reclamation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.control.Notifications;
import services.MailService;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjoutReclamationController implements Initializable {

    @FXML
    private TextField Titre;
    @FXML
    private Button btn_ajouter;
    @FXML
    private TextArea contenu;
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
    private void save(ActionEvent event) 
    {
       titer_req.setText("");
       contenu_req.setText("");
         if(Titre.getText().equals(""))
         {
            titer_req.setText("titre obligatoire");
             return;
         }
            if(contenu.getText().equals(""))
         {
            contenu_req.setText("contenu obligatoire");
             return;
         }
           
            File doc= new File("C:\\Users\\Asus\\Desktop\\commentaire.txt");
        try {
            Scanner obj=new Scanner(doc);
            
            
            while(obj.hasNextLine())
            {
               if(contenu.getText().equals(obj.nextLine()))
               {
                     Notifications.create()
                     .title("")
                     .text("des mots vulgaires utilisé .. réfliché un peu")
                     .position(Pos.TOP_RIGHT)
                     .darkStyle()
                     .showWarning();
                 return;
               }
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            
         int user_id=1;
         Reclamation r=new Reclamation(Titre.getText(),contenu.getText(),user_id);
         ReclamationService  ps =new ReclamationService();
         ps.InsertCommentaire(r);
           Notifications.create()
                     .title("Reclamation")
                     .text("Reclamation ajouté")
                     .position(Pos.TOP_RIGHT)
                     .darkStyle()
                     .showInformation();
        try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("MesReclamation.fxml"));
               Parent root=loader.load();
               
      
      
              
               
            
            contenu.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(MesReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
     
    }

    @FXML
    private void redirectToMesReclamation(ActionEvent event) {
          try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("MesReclamation.fxml"));
               Parent root=loader.load();
               
            
            Titre.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void RedirectToReponse(ActionEvent event) {
          try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeReponse_client.fxml"));
               Parent root=loader.load();
               
            
            Titre.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    
}
