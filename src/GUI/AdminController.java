/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.CategorieRegime;
import entites.User;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import services.UserService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AdminController implements Initializable {

    @FXML
    private Button btn_delete;
    private TextField tf_id;
    @FXML
    private TableView<User> tv_clients;
    @FXML
    private TableColumn<User, String> col_nom;
    @FXML
    private TableColumn<User, String> col_prenom;

    @FXML
    private TableColumn<User, String> col_tel;
  
    @FXML
    private TableColumn<User, String> col_email;
    @FXML
    private Button excel;
    @FXML
    private TableColumn<User, String> col_action;
    @FXML
    private Button btn_delete1;
    @FXML
    private Button btn_listuser;
    @FXML
    private Button btn_evenement;
    @FXML
    private Button btn_produit;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button UserAjouter;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showClient();
    }    

  public void showClient(){
       FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
        ObservableList<User> list = new UserService().readAllClients();
       
       
        col_email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        col_nom.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
        col_tel.setCellValueFactory(new PropertyValueFactory<User, String>("tel"));
       
      //col_id.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id_user"));
        
       //add cell of actions 
            Callback<TableColumn<User, String>, TableCell<User, String>> cellFoctory = (TableColumn<User, String> param) -> {
            // make cell containing buttons
            final TableCell<User, String> cell = new TableCell<User, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                           // colStatColor.setStyle("-fx-background-color: statcolor;");
                         
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#D88300;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Suppression du user");
                            alert.setContentText("voulez vous vraiment supprimer cette user ?");
                            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                            alert.getButtonTypes().setAll(okButton, noButton);
                            Optional<ButtonType> result = alert.showAndWait();
                                            if (result.get() == okButton){
                                  
                                    User user = tv_clients.getSelectionModel().getSelectedItem();
                                   UserService bs= new UserService();
                                    bs.delete(user.getId());
                String titles = "user supprimée ";
                String msgs = "avec succées";
                TrayNotification trays = new TrayNotification();
                AnimationType types = AnimationType.SLIDE;
                trays.setAnimationType(types);
                trays.setTitle(titles);
                trays.setMessage(msgs);
                trays.showAndDismiss(Duration.seconds(5));
                trays.setNotificationType(NotificationType.SUCCESS);
                                    showClient();
                                    } else if (result.get() == noButton) {
                                 
                                 showClient();
                                    }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            User u = tv_clients.getSelectionModel().getSelectedItem();
                          
                             
                            
                             
                               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                          
                            alert.setTitle("ban user");
                            alert.setContentText("voulez vous vraiment banné cette user ?");
                            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                            alert.getButtonTypes().setAll(okButton, noButton);
                            Optional<ButtonType> result = alert.showAndWait();
                                            if (result.get() == okButton){
                                  
                                    User user = tv_clients.getSelectionModel().getSelectedItem();
                                   UserService bs= new UserService();
                                    bs.banUtilisateurs(u.getEmail());
                String titles = "user banné ";
                String msgs = "avec succées";
                TrayNotification trays = new TrayNotification();
                AnimationType types = AnimationType.SLIDE;
                trays.setAnimationType(types);
                trays.setTitle(titles);
                trays.setMessage(msgs);
                trays.showAndDismiss(Duration.seconds(5));
                trays.setNotificationType(NotificationType.SUCCESS);
                                    showClient();
                                    } else if (result.get() == noButton) {
                                 
                                 showClient();
                                     
                            
                                    }
                        });
                        
                              
//                            
                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
             col_action.setCellFactory(cellFoctory);
        tv_clients.setItems(list);

    }

  

    @FXML
    private void clientListClicked(MouseEvent event) {
        User  c = tv_clients.getSelectionModel().getSelectedItem();
        tf_id.setText(""+c.getId());
    }

    @FXML
    private void logout(ActionEvent event) throws IOException{
        Parent etab = FXMLLoader.load(getClass().getResource("login.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
 @FXML
    private void excel(ActionEvent event) throws IOException {
        UserService bs= new UserService();
         ObservableList <User> list;
         list = bs.readAllClients();
       Writer writer = null;
        try {
            //badel path fichier excel
            File file = new File("C:\\Users\\asus\\Desktop\\pidev\\Adomifit.csv");
            
            writer = new BufferedWriter(new FileWriter(file));
            String text1 = "Nom client"+",     " +"Prenom Client"+ ",        " + "Email"+ ",         " + "role"+ "\n";
            writer.write(text1);
            for (User ev : list) {

                String text = ev.getNom()+"," +ev.getPrenom()+ "," + ev.getEmail()+ "," + ev.isRole()+ "\n";

                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {

            writer.flush();
             writer.close();
             Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("excel");
        alert.setHeaderText(null);
        alert.setContentText("!!!excel exported!!!");
        alert.showAndWait();}
        }

    @FXML
    private void Profil(ActionEvent event) throws IOException {
          Parent etab = FXMLLoader.load(getClass().getResource("Profil.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


    private void user(ActionEvent event) throws IOException {
          Parent etab = FXMLLoader.load(getClass().getResource("Admin.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    private void evenement(ActionEvent event) throws IOException {
          Parent etab = FXMLLoader.load(getClass().getResource("CategorieEvenement.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    

    
    @FXML
    private void showListeUtilisateurs(ActionEvent event) throws IOException {
         Parent etab = FXMLLoader.load(getClass().getResource("Admin.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void showListEvenement(ActionEvent event) throws IOException {
         Parent etab = FXMLLoader.load(getClass().getResource("Billet.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void showListeProduits(ActionEvent event) throws IOException {
         Parent etab = FXMLLoader.load(getClass().getResource("Produit.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void showListeReclamation(ActionEvent event) throws IOException {
        Parent etab = FXMLLoader.load(getClass().getResource("Afficher.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void deleteClient(ActionEvent event) {
    }

    @FXML
    private void showListCtegorieRegime(MouseEvent event) {
    }

    @FXML
    private void showMesRegimes(MouseEvent event) {
    }

    @FXML
    private void showStatistique(MouseEvent event) {
    }

    @FXML
    private void showListeSuivis(MouseEvent event) {
    }

    @FXML
    private void AjouterUser(ActionEvent event) throws IOException {
         Parent etab = FXMLLoader.load(getClass().getResource("ajouterUser.fxml"));      
        Scene scene = new Scene(etab);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
