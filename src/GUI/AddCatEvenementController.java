/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entites.CategorieEvenement;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author myria
 */
public class AddCatEvenementController implements Initializable {
     ImageView imgView = new ImageView();
    
    private FileChooser fileChooser;
    Image imgs;
     BufferedImage bf;
      ImageView imgv;

    @FXML
    private JFXTextField LibelleF;
    @FXML
    private JFXTextField DescriptionF;
    @FXML
    private JFXTextField ImageF;
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    CategorieEvenement categorieEvenement = null;
    int CatEvId; 
     private boolean update;
    @FXML
    private Button btnupload;
    @FXML
    private JFXButton valider;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fileChooser= new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
    }    

//    @FXML
//    private void valider(MouseEvent event) {
//        connection=DataSource.getInstance().getConn();
//        String libelle = LibelleF.getText();
//        String description = DescriptionF.getText();
//        String image = ImageF.getText();
//        
//        
//        if(libelle.isEmpty()||description.isEmpty()||image.isEmpty()){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("Veuillez remplir tous les champs");
//            alert.showAndWait();
//        }else{
//            getQuery();
//            insert();
//            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//            stage.close();
//            annuler( event);
//        }
//        
//    }

    @FXML
    private void annuler(MouseEvent event) {
        LibelleF.setText(null);
        DescriptionF.setText(null);
        ImageF.setText(null);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        
    }

    private void insert() {
        
          
        try {
        PreparedStatement st =DataSource.getInstance().getConn().prepareStatement(query);
            st.setString(1, LibelleF.getText());
            st.setString(2,DescriptionF.getText()); 
            st.setString(3, ImageF.getText());
            
           
            st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   void setTextField(int id, String libelle, String description, String image) {

        CatEvId = id;
        LibelleF.setText(libelle);
        DescriptionF.setText(description);
        ImageF.setText(image);
       

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    private void getQuery() {
         if (update == false) {
            
             query = "INSERT INTO categorie_evenement (`libelle`,`description`,`image`) VALUES (?,?,?) ;";

        }else{
            query = "UPDATE `categorie_evenement` SET "
                    + "`libelle`=?,"
                    + "`description`=?,"
                    + "`image`=? WHERE id = '"+CatEvId+"'";
        }
       
       }

    @FXML
    private void upload(MouseEvent event) {
        
        File file= fileChooser.showOpenDialog(null);
        try {
            String url=file.toURI().toURL().toString();
             System.out.println("file : "+url);
             ImageF.setText(file.getAbsolutePath());
        } catch (MalformedURLException ex) {
            Logger.getLogger(AddEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void valider(ActionEvent event) {
        
         connection=DataSource.getInstance().getConn();
        String libelle = LibelleF.getText();
        String description = DescriptionF.getText();
        String image = ImageF.getText();
        
        
        if(libelle.isEmpty()||description.isEmpty()||image.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
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
        }else{
            getQuery();
            insert();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
           // annuler( event);
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
}
