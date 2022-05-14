/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Kouki
 */
public class SuiviRegimeExisteController implements Initializable {
private Parent fxml;
   @FXML
    private AnchorPane root;
    @FXML
    private Label btn_accueil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showAccueil(MouseEvent event) {
            try {
            fxml = FXMLLoader.load(getClass().getResource("FrontRegime.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(CategorieRegimeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showSuiviRegimeUser(MouseEvent event) {
              try {
            fxml = FXMLLoader.load(getClass().getResource("showSuiviRegime.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(CategorieRegimeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
