package GUI;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entites.CategorieProduit;
import entites.Produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import services.CategorieProduitService;
import services.Smsapi;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class CategorieProduitController implements Initializable {

    @FXML
    private Button bsave;
    @FXML
    private TableView<CategorieProduit> table;
    @FXML
    private TableColumn<?, ?> col_lib;
    @FXML
    private TableColumn<?, ?> col_desc;
    @FXML
    private TextField libelle;
    @FXML
    private TextField description;
    private TextField idprod;
     public ObservableList<CategorieProduit> dataa = FXCollections.observableArrayList();
    @FXML
    private TextField idcateg;
    @FXML
    private Button btn_commandes;
    @FXML
    private Button btn_maison;
    @FXML
    private Button btn_repas;
    @FXML
    private Button btn_resrepas;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_user1;
    @FXML
    private Button btn_reserveract;
    @FXML
    private Button btn_resmais;
    @FXML
    private Button btn_evenements;
    @FXML
    private Button btn_programme;
    @FXML
    private Button bupdate;
    @FXML
    private Button bdelete;
    @FXML
    private TextField tfrecherche;
    @FXML
    private ImageView fxlogo;
    @FXML
    private Button btn_categorieprogramme;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           
    col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
    col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
    Refresh();
    AfficherCategProduit();
    }    
     private void Refresh() {
          dataa.clear();
                CategorieProduitService RS = new CategorieProduitService();
        dataa.addAll(RS.getAll());
        RS.getAll();
        
        table.setItems(dataa);
    }
         private void AfficherCategProduit()  {
           
           Refresh();
           
    col_lib.setCellValueFactory(new PropertyValueFactory<>("libelle"));
    col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
    

    }

    @FXML
    private void saveEvent(ActionEvent event) {
        String insert = "INSERT INTO categorie_produit (`libelle`,`description`) VALUES (?,?) ;";
        try {
        PreparedStatement st =DataSource.getInstance().getConn().prepareStatement(insert);
            st.setString(1, libelle.getText());
            st.setString(2, description.getText());            
            st.executeUpdate();
            
            Refresh();
            File f = new File("C:\\Users\\sonicmaster\\Desktop\\Adomifit\\src\\sounds\\ajoutcat.mp3");
              Media m = new Media(f.toURI().toString());
              MediaPlayer mp = new MediaPlayer(m);
              MediaView mv = new MediaView(mp);
              mp.play();
            
            
            Smsapi sms= new Smsapi();
          Smsapi.sendSMS("+21698975800", "categorie_produit Ajouté"); //apres integration nzid getuser num bech yabath lel con
          
          
          
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Add Success");
            tray.setMessage("categorie_produit ajouter avec succes ");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void Update(ActionEvent event) {
        int id = Integer.valueOf(idcateg.getText());
        String lib = libelle.getText();
        String desc = description.getText();
         
        
       
        
//        java.sql.Date date = java.sql.Date.valueOf(Mdate_rec.getValue());
        try {

            System.out.println("Modifié avec succes");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           // stage.close();
            Alert ModifyAdmintAlert = new Alert(Alert.AlertType.CONFIRMATION);
            ModifyAdmintAlert.setTitle("Modifier categorie_produit");
            ModifyAdmintAlert.setHeaderText(null);
            ModifyAdmintAlert.setContentText("Voulez-vous vraiment Modifier ce categorie_produit ? ?");
            Optional<ButtonType> optionModifyBookAlert = ModifyAdmintAlert.showAndWait();
            if (optionModifyBookAlert.get() == ButtonType.OK) {
                CategorieProduit r = new CategorieProduit(id, lib, desc);
                CategorieProduitService ad = new CategorieProduitService();
               ad.update(id, lib, desc);
               dataa.clear();
                dataa.addAll(ad.getAll());
                Alert succModifyAdminAlert = new Alert(Alert.AlertType.INFORMATION);
                succModifyAdminAlert.setTitle("Modifier categorie_produit");
                succModifyAdminAlert.setHeaderText("Resultats:");
                succModifyAdminAlert.setContentText("categorie_produit Modifié !");
                succModifyAdminAlert.showAndWait();
                File f = new File("C:/Users/MSI/Documents/NetBeansProjects/Adomifit/src/sounds/catmodif.mp3");
              Media m = new Media(f.toURI().toString());
              MediaPlayer mp = new MediaPlayer(m);
              MediaView mv = new MediaView(mp);
              mp.play();
                
            }else if (optionModifyBookAlert.get() == ButtonType.CANCEL) {
            }
            

        } catch (Exception ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }

    
    
    
    @FXML
    private void deleteEvent(ActionEvent event) {
         if (table.getSelectionModel().getSelectedItem() != null) {
            Alert deleteEventAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteEventAlert.setTitle("Suppression d'une Categorie");
            deleteEventAlert.setHeaderText(null);
            deleteEventAlert.setContentText("Veuillez supprimer ce Categorie?");
            Optional<ButtonType> optionDeleteBookAlert = deleteEventAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                CategorieProduit R = table.getSelectionModel().getSelectedItem();
                CategorieProduitService rc= new CategorieProduitService();
                rc.deleteCat(R);
                dataa.clear();
                dataa.addAll(rc.getAll());
                
                  TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Delete Success");
            tray.setMessage("Categorie Supprimé avec succes ");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
            
            File f = new File("C:/Users/MSI/Documents/NetBeansProjects/Adomifit/src/sounds/catsupp.mp3");
              Media m = new Media(f.toURI().toString());
              MediaPlayer mp = new MediaPlayer(m);
              MediaView mv = new MediaView(mp);
              mp.play();
                
               
                Alert succDeleteEventAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteEventAlert.setTitle("Supprimer Categorie");
                succDeleteEventAlert.setContentText("Categorie déja supprimée!");
                succDeleteEventAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {
            }
        } else {
              File f = new File("C:/Users/ASUS/Desktop/codenameone/Sprint_java/src/sounds/erreur.mp3");
              Media m = new Media(f.toURI().toString());
              MediaPlayer mp = new MediaPlayer(m);
              MediaView mv = new MediaView(mp);
              mp.play();
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Sélectionner un Categorie");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("D'abord il faut Sélectionner un Categorie !");
            selectBookAlert.showAndWait();
            
        } 
    }

    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
         CategorieProduit c = table.getSelectionModel().getSelectedItem();
    idcateg.setText(String.valueOf(c.getId()));
        libelle.setText(String.valueOf(c.getLibelle()));
        description.setText(String.valueOf(c.getDescription()));
        
       bsave.setDisable(true);
    }


    @FXML
    private void changeScreencateg(ActionEvent event) {
           try {
            Parent tableViewParent=FXMLLoader.load(getClass().getResource("/controllerProgramme/CategorieProduit.fxml"));
            Scene tableViewScene= new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void changeScreenProduit(ActionEvent event) {
          try {
            Parent tableViewParent=FXMLLoader.load(getClass().getResource("/controllerProgramme/Produit.fxml"));
            Scene tableViewScene= new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void commandes(ActionEvent event) {
    }

    @FXML
    private void maison(ActionEvent event) {
    }

    @FXML
    private void repas(ActionEvent event) {
    }

    @FXML
    private void resrepas(ActionEvent event) {
    }

    @FXML
    private void reclamation(ActionEvent event) {
    }

    @FXML
    private void user(ActionEvent event) {
    }

    @FXML
    private void reservation(ActionEvent event) {
    }

    @FXML
    private void resmaison(ActionEvent event) {
    }

    @FXML
    private void evenement(ActionEvent event) {
    }

    @FXML
    private void Programme(ActionEvent event) {
        
         try {
            Parent tableViewParent=FXMLLoader.load(getClass().getResource("/controllerProgramme/Programme.fxml"));
            Scene tableViewScene= new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void CatProg(ActionEvent event) {
         try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("/controllerProgramme/Categorie.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            //stage.getIcons().add(new Image("cc.png"));
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void Rechercher(ActionEvent event) {
    }
    
    
}
