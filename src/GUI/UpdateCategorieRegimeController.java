/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entites.CategorieRegime;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import services.CategorieRegimeService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Kouki
 */
public class UpdateCategorieRegimeController implements Initializable {
    private int categorieId;
      private AnchorPane root;
    private Parent fxml;
    @FXML
    private JFXTextField libelleTF;
    @FXML
    private JFXTextArea DescriptionTF;
    @FXML
    private JFXColorPicker statColorTF;
    @FXML
    private Button btn_Update;
    @FXML
    private Button btn_close;
    Stage stage;
     private Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
        public void setLibelleTF(String value) {
        this.libelleTF.setText(value);  
        }
        public void setDescriptionTF(String value) {
        this.DescriptionTF.setText(value);  
        }
        public void setStatColorTF(String value) {
            this.statColorTF.setValue(Color.valueOf(value));
     
        }

    @FXML
    private void onUpdate(MouseEvent event) {
        
         if(libelleTF.getText().equals("") || DescriptionTF.getText().equals("") ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Verifier tout les champs ! ");
            alert.showAndWait();
            return;
        }else{
              String hex2 = "#" + Integer.toHexString(statColorTF.getValue().hashCode()).substring(0, 6);
             CategorieRegime cr = new CategorieRegime(categorieId,libelleTF.getText(),DescriptionTF.getText(),hex2);
            
             CategorieRegimeService cs = new CategorieRegimeService();
             cs.update(cr);
           String titles = "Catégorie modifié ";
                String msgs = "avec succées";
                TrayNotification trays = new TrayNotification();
                AnimationType types = AnimationType.SLIDE;
                trays.setAnimationType(types);
                trays.setTitle(titles);
                trays.setMessage(msgs);
                trays.showAndDismiss(Duration.seconds(5));
                trays.setNotificationType(NotificationType.SUCCESS);
                     
                        
          Stage stage = (Stage) libelleTF.getScene().getWindow();
    // do what you have to do
    FXMLLoader loader = new FXMLLoader ();
                             loader.setLocation(getClass().getResource("CategorieRegime.fxml"));
                             try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(CategorieRegimeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         CategorieRegimeController controller = loader.getController();
                            controller.displayTabCategorie();
                                    
                             Parent parent = loader.getRoot();
                            Stage staged = new Stage();
                            staged.setScene(new Scene(parent));
                            staged.initStyle(StageStyle.UTILITY);
                            staged.show(); 
    stage.close();
                
        }
    }

    @FXML
    private void closeDialog(ActionEvent event) {
        Stage stage = (Stage) btn_close.getScene().getWindow();
    // do what you have to do
    stage.close();
    }
        void setTextField(int id, String libell, String description, String statColor) {
            this.libelleTF.setText(libell);
            this.DescriptionTF.setText(description); 
            this.statColorTF.setValue(Color.valueOf(statColor));  
            categorieId = id;
      

    }    
      
    
}
