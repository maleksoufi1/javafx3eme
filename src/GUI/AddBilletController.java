/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entites.CategorieEvenement;
import entites.Evenement;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.CategorieEvenementService;
import services.EvenementService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author myria
 */
public class AddBilletController implements Initializable {

    @FXML
    private Text titreForm;
    @FXML
    private JFXTextField NumeroF;
    @FXML
    private JFXTextField DetailF;
    @FXML
    private JFXDatePicker HorraireF;
    @FXML
    private ComboBox<Evenement> CombolistEvenement;
    @FXML
    private JFXButton btnValider;
    @FXML
    private JFXButton btnAnnuler;

    String query = null;
    
     private int BilletId;
    private boolean update;
    private FileChooser fileChooser;
    
    
    int BilId; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        EvenementService cs = new EvenementService();
         List<Evenement> listcat = cs.getAll()   ;
          
        ObservableList<Evenement> List = FXCollections.observableArrayList(listcat);
            CombolistEvenement.setItems(List);
        
        
    }    

//    @FXML
//    private void valider(MouseEvent event) {
//        if(NumeroF.getText().equals("") || DetailF.getText().equals("") 
//                ||CombolistEvenement.getValue() == null ){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("Verifier tout les champs ! ");
//            alert.showAndWait();
//            
//            return;
//        }else{
//            
//            getQuery();
//            insert();
//            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//            stage.close();
//            annuler( event);
//    }
//
//    
//
//   
//    
//}

    private void getQuery() {
       if (update == false) {
            
             query = "INSERT INTO billet (`numero`,`detail`,`horaire`,`evenement_id`) VALUES (?,?,?,?) ;";

        }else{
            query = "UPDATE `billet` SET "
                    + "`numero`=?,"
                    + "`detail`=?,"
                   
                    + "`horaire`=?,"
                    + "`evenement_id`=? WHERE id = '"+BilId+"'";
        } }

    @FXML
    private void annuler(MouseEvent event) {
        }

    private void insert() {
        try {
        PreparedStatement st =DataSource.getInstance().getConn().prepareStatement(query);
            st.setString(1, NumeroF.getText());
            st.setString(2,DetailF.getText()); 
          
              st.setString(3, String.valueOf(HorraireF.getValue()));
              st.setInt(4, CombolistEvenement.getValue().getId());
              
               
           
            st.executeUpdate();
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }}

    void setUpdate(boolean b) {
        this.update = b;}

    void setTextField(int id, double numero, String detail, Evenement evenement, LocalDate toLocalDate) {
        this.titreForm.setText("Modifier Billet");
        this.BilletId = id;
   this.NumeroF.setText(String.valueOf(numero));
       this.DetailF.setText(detail);
       this.HorraireF.setValue(toLocalDate);
      
       this.CombolistEvenement.setValue(evenement);}

    @FXML
    private void valider(ActionEvent event) {
        
        
        if(NumeroF.getText().equals("") || DetailF.getText().equals("") 
                ||CombolistEvenement.getValue() == null ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Verifier tout les champs ! ");
            alert.showAndWait();
             String titles = "ERREUR ";
                String msgs = "Veuiller vérifier les données";
                TrayNotification trays = new TrayNotification();
                AnimationType types = AnimationType.POPUP;
               
                trays.setAnimationType(types);
                trays.setTitle(titles);
                trays.setMessage(msgs);
                trays.showAndDismiss(Duration.seconds(10));
                trays.setNotificationType(NotificationType.ERROR);
            return;
        }else{
            
            getQuery();
            insert();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            //annuler( event);
    }
        String titles = "Catégorie Evenement ajoutée ";
                String msgs = "avec succées";
                TrayNotification trays = new TrayNotification();
                AnimationType types = AnimationType.POPUP;
               
                trays.setAnimationType(types);
                trays.setTitle(titles);
                trays.setMessage(msgs);
                trays.showAndDismiss(Duration.seconds(10));
                trays.setNotificationType(NotificationType.SUCCESS);
    }

    @FXML
    private void valider(MouseEvent event) {
    }
}
