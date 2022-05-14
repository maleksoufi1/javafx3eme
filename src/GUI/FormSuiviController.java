/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import entites.SuiviRegime;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import services.SuiviRegimeService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Kouki
 */
public class FormSuiviController implements Initializable {
    private int suiviId ;
    private int regimeId ;
    private int userId ;
    @FXML
    private TextField titreTF;
    @FXML
    private TextField noteTF;
    @FXML
    private TextArea remarqueTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    } 
    
      void setTextField(int id,String Titre , int note , String remarque ,int regimeId, int userId ) {
          this.suiviId = id;
           System.out.println("salut = "+suiviId);
          this.titreTF.setText(Titre);
          this.noteTF.setText(String.valueOf(note));
          this.titreTF.setText(remarque);
          this.userId = userId;
          this.regimeId=regimeId; 
    }

    @FXML
    private void save(MouseEvent event) {
        int n = Integer.parseInt(noteTF.getText());
        SuiviRegime s = new SuiviRegime(suiviId, titreTF.getText(), remarqueTF.getText(),n, regimeId, userId);
        SuiviRegimeService servSuiv  = new SuiviRegimeService();
        servSuiv.update(s);
         String titles = "Suivi modifié ";
                String msgs = "avec succées";
                TrayNotification trays = new TrayNotification();
                AnimationType types = AnimationType.SLIDE;
                trays.setAnimationType(types);
                trays.setTitle(titles);
                trays.setMessage(msgs);
                trays.showAndDismiss(Duration.seconds(5));
                trays.setNotificationType(NotificationType.SUCCESS);
                        Stage stage = (Stage) remarqueTF.getScene().getWindow();
    // do what you have to do
    FXMLLoader loader = new FXMLLoader ();
                             loader.setLocation(getClass().getResource("ListeSuivis.fxml"));
                             try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(FormSuiviController.class.getName()).log(Level.SEVERE, null, ex);
                            }
        ListeSuivisController controller = loader.getController();
        controller.displayTabSuivis();
                          
                             Parent parent = loader.getRoot();
                            Stage staged = new Stage();
                            staged.setScene(new Scene(parent));
                            staged.initStyle(StageStyle.UTILITY);
                            staged.show(); 
    stage.close();
        
    }

    @FXML
    private void cancel(MouseEvent event) {
    }
    
}
