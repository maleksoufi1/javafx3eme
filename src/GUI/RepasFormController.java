/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import entites.Repas;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.MessagingException;
import services.RepasService;

/**
 * FXML Controller class
 *
 * @author Kouki
 */
public class RepasFormController implements Initializable {

    @FXML
    private Label repasTitreForm;

    @FXML
    private TextField titreTF;

    @FXML
    private JFXDatePicker dateStartTF;
    @FXML
    private JFXTimePicker timeStartTF;
    @FXML
    private Label repasTitreForm111;
    @FXML
    private JFXDatePicker dateEndTF;
    @FXML
    private JFXTimePicker timeEndTF;
    @FXML
    private Label repasTitreForm1111;
    @FXML
    private TextArea descriptionTF;
    @FXML
    private Label repasTitreForm11111;
    @FXML
    private ComboBox<String> comboToujour;
    @FXML
    private Label repasTitreForm111111;
    @FXML
    private Label repasTitreForm111112;
    @FXML
    private Label repasTitreForm111113;
    @FXML
    private Label repasTitreForm111114;
    @FXML
    private JFXColorPicker backgroundTF;
    @FXML
    private JFXColorPicker TextTF;
    @FXML
    private JFXColorPicker BorderTF;
    
    private int suivId;
    private boolean update;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       comboToujour.getItems().addAll("Oui","Non");
    }    
    void setSuivId(int id){
        this.suivId = id;
    }
    @FXML
    private void save(MouseEvent event) {
        System.out.println("id suiv = "+suivId);
        if(titreTF.getText().equals("") || descriptionTF.getText().equals("") || dateStartTF.getValue()== null
                 ||timeStartTF.getValue()== null|| dateEndTF.getValue()== null|| timeEndTF.getValue()== null
                ||comboToujour.getValue() == null ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Verifier tout les champs ! ");
            alert.showAndWait();
            return;
        }
        //ajout
        if (update == false) {
           
            int allday = 0;
            if(comboToujour.getValue().equals("Oui")){
                 allday = 1;
            }
             String hex1 = "#" + Integer.toHexString(backgroundTF.getValue().hashCode()).substring(0, 6);
             String hex2 = "#" + Integer.toHexString(BorderTF.getValue().hashCode()).substring(0, 6);
             String hex3 = "#" + Integer.toHexString(TextTF.getValue().hashCode()).substring(0, 6);
                LocalDate dateDep = LocalDate.parse(dateStartTF.getValue().toString());
                 LocalTime timeDep = LocalTime.parse(timeStartTF.getValue().toString());
                 LocalDateTime start = LocalDateTime.of(dateDep, timeDep);
               
                  LocalDate dateFin = LocalDate.parse(dateEndTF.getValue().toString());
                 LocalTime timeFin = LocalTime.parse(timeEndTF.getValue().toString());
                 LocalDateTime end = LocalDateTime.of(dateFin, timeFin);
                 
                Timestamp s = Timestamp.valueOf(start);
                Timestamp ss = Timestamp.valueOf(end);
              
                  
        Repas rep = new Repas(titreTF.getText(), s, ss, descriptionTF.getText(), allday, 
                hex1, hex2, hex3, suivId);
        
            RepasService rs = new RepasService();
            rs.addRepas(rep);
            
            try {
                Mail.sendMail("kuokihoussem@gmail.com", 2, "repas ajouté ");
            } catch (MessagingException ex) {
                Logger.getLogger(RepasFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Stage stage = (Stage) titreTF.getScene().getWindow();
    // do what you have to do
    FXMLLoader loader = new FXMLLoader ();
                             loader.setLocation(getClass().getResource("GestionRepas.fxml"));
                             try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(RepasFormController.class.getName()).log(Level.SEVERE, null, ex);
                            }
              GestionRepasController controller = loader.getController();
              controller.suiviId(suivId);
                            controller.displayTabRepas();
                                    
                             Parent parent = loader.getRoot();
                            Stage staged = new Stage();
                            staged.setScene(new Scene(parent));
                            staged.initStyle(StageStyle.UTILITY);
                            staged.show(); 
    stage.close();
        }
        
    }
    
}
