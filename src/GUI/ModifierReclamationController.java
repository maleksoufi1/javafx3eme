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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ModifierReclamationController implements Initializable {

    @FXML
    private TextField mod_titre;
    @FXML
    private TextArea mod_contenu;
    @FXML
    private Button but_mod;
    private Reclamation r;
    @FXML
    private Label req_titre;
    @FXML
    private Label req_contenu;
    @FXML
    private Label titer_req;
    @FXML
    private Label contenu_req;

    public Reclamation getR() {
        return r;
    }

    public void setR(Reclamation r) {
        this.r = r;
        this.mod_contenu.setText(r.getContenu());
        this.mod_titre.setText(r.getTitre());
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //System.out.println(r);
       // this.mod_contenu.setText(r.getContenu());
       // mod_titre.setText(r.getTitre());
    }    

    @FXML
    private void update_rec(ActionEvent event) 
    {
        if(mod_titre.getText().equals(""))
         {
            req_titre.setText("titre obligatoire");
             return;
         }
            if(mod_contenu.getText().equals(""))
         {
            req_contenu.setText("contenu obligatoire");
             return;
         }
         int user_id=1;
         Reclamation r=new Reclamation(this.r.getId(),mod_titre.getText(),mod_contenu.getText(),user_id);
         //System.out.println(r);
         ReclamationService  ps =new ReclamationService();
         ps.ModifierReclamation(r);
         
         try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("MesReclamation.fxml"));
               Parent root=loader.load();
               
            
            req_contenu.getScene().setRoot(root);
            } catch (IOException ex) 
            {
            Logger.getLogger(ModifierReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
         
    }

    @FXML
    private void redirectToMesReclamation(ActionEvent event) {
    }

    @FXML
    private void RedirectToReponse(ActionEvent event) {
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
