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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import services.MailService;
import services.ReponseReclamationService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class Detail_Reclamation_AdminController implements Initializable {

    @FXML
    private Label titre;
    @FXML
    private Label Contenu;
    @FXML
    private TextArea reponse;
    @FXML
    private Label contenu_req;
   private Reclamation rec;
    @FXML
    private Label titer_req;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        
    }    

    @FXML
    private void Envoyer_Reponse(ActionEvent event) 
    {
       contenu_req.setText("");
        
            
            if(reponse.getText().equals(""))
         {
            contenu_req.setText("contenu obligatoire");
             return;
         }
         int user_id=1;
         int Reclamation_id=rec.getId();
         ReponseReclamation r=new ReponseReclamation(reponse.getText(),user_id,Reclamation_id);
         ReponseReclamationService  ps =new ReponseReclamationService();
         ps.InsertReponse(r);
         
       
         MailService  mail =new MailService();
         mail.SendMail();
        try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeReponse.fxml"));
               Parent root=loader.load();
               
            
            reponse.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(MesReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
    public void Afficher_Contenu(Reclamation r)
{
   this.rec=r;
    System.out.println(r.getContenu());
      this.titre.setText(r.getTitre());

    this.Contenu.setText(r.getContenu());
   
}


    @FXML
    private void redirectToReponse(ActionEvent event) 
    {
         try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeReponse.fxml"));
               Parent root=loader.load();
               
            
            Contenu.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void redirectToReclamation(ActionEvent event) 
    {
          try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("Boite_Reclamation.fxml"));
               Parent root=loader.load();
               
            
            Contenu.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
