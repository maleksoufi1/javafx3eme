/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AfficherController implements Initializable {
    
    @FXML
    private Label nomLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label prenomLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setIdlabel(int id) {
        this.idLabel.setText(id +"");
                 // this.idLabel.setText(String.valueOf(id));
    }
     public void setprenomLabel(String prenom) {
        this.prenomLabel.setText(prenom);
                 // this.idLabel.setText(String.valueOf(id));
    }
      public void setnomLabel(String nom) {
        this.nomLabel.setText(nom);
                 // this.idLabel.setText(String.valueOf(id));
    }
    
}
