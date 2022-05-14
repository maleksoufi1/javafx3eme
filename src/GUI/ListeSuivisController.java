/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Regime;
import entites.SuiviRegime;
import java.io.IOException;
import java.net.URL;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
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
public class ListeSuivisController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Pane ContentPaneGestionnaire;
    @FXML
    private TableView<SuiviRegime> tableSuivis;
    @FXML
    private TableColumn<SuiviRegime, String> colRegime;
    @FXML
    private TableColumn<SuiviRegime, String> colTitre;
    @FXML
    private TableColumn<SuiviRegime, Integer> colNote;
    @FXML
    private TableColumn<SuiviRegime, String> colRemarque;
    @FXML
    private TableColumn<SuiviRegime, String> colClient;
    @FXML
    private TableColumn<SuiviRegime, String> colActions;
    @FXML
    private Button btn_listCategorie;
    @FXML
    private Button btn_mesRegimes;
    @FXML
    private Button btn_Statistique;
    @FXML
    private Button btn_ListSuivi;
 private Parent fxml;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       displayTabSuivis();
    }    

           @FXML
    void showListCtegorieRegime(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("CategorieRegime.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(DashboardNutritionnisteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void showListeSuivis(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("ListeSuivis.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(DashboardNutritionnisteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void showMesRegimes(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("MesRegimes.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(DashboardNutritionnisteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void displayTabSuivis(){
        SuiviRegimeService suivServ = new SuiviRegimeService();
        //les suivis de user connecté
        //session user id 2
      List<SuiviRegime> suivList = suivServ.getMesSuivis(2);
      ObservableList<SuiviRegime> ob = FXCollections.observableArrayList(suivList);
      
      colRegime.setCellValueFactory(new PropertyValueFactory<>("regime"));
      colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
      colNote.setCellValueFactory(new PropertyValueFactory<>("note"));
      colRemarque.setCellValueFactory(new PropertyValueFactory<>("remarque"));
      colClient.setCellValueFactory(new PropertyValueFactory<>("user_id"));
      
     
      Callback<TableColumn<SuiviRegime, String>, TableCell<SuiviRegime, String>> cellFoctory = (TableColumn<SuiviRegime, String> param) -> {
      final TableCell<SuiviRegime, String> cell = new TableCell<SuiviRegime, String>() {
          @Override
                public void updateItem(String item, boolean empty) {
                     //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    }else {
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        FontAwesomeIconView calIcon = new FontAwesomeIconView(FontAwesomeIcon.CALENDAR);
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
                        calIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#0000FF;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Suppression du suivis");
                            alert.setContentText("voulez vous vraiment supprimer ce suivis ?");
                            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                            alert.getButtonTypes().setAll(okButton, noButton);
                            Optional<ButtonType> result = alert.showAndWait();
                             if (result.get() == okButton){
                                  
                                    SuiviRegime suivi = tableSuivis.getSelectionModel().getSelectedItem();
                                    suivServ.deleteSuiviRegime(suivi.getId());
                                    displayTabSuivis();
                                    String titles = "suivis supprimé ";
                                    String msgs = "avec succées";
                                    TrayNotification trays = new TrayNotification();
                                    AnimationType types = AnimationType.SLIDE;
                                    trays.setAnimationType(types);
                                     trays.setTitle(titles);
                                    trays.setMessage(msgs);
                                    trays.showAndDismiss(Duration.seconds(5));
                                        trays.setNotificationType(NotificationType.SUCCESS);
                   
                                    } else if (result.get() == noButton) {
                                 
                                 displayTabSuivis();
                                    }
                        });
                        
                        
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            SuiviRegime suivi = tableSuivis.getSelectionModel().getSelectedItem();
                             FXMLLoader loader = new FXMLLoader ();
                             loader.setLocation(getClass().getResource("FormSuivi.fxml"));
                             try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ListeSuivisController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                             
                             FormSuiviController formcnt =  loader.getController();
                             formcnt.setTextField(suivi.getId(), suivi.getTitre(),suivi.getNote(), suivi.getRemarque(), suivi.getRegime_id(), suivi.getUser_id());
                             Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                        });
                        calIcon.setOnMouseClicked((MouseEvent event) -> {
                                 SuiviRegime suivi = tableSuivis.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionRepas.fxml"));
       
        try {
            Parent root = loader.load();
            GestionRepasController controller = loader.getController();
            controller.suiviId(suivi.getId());
            controller.displayTabRepas();
            //ay element mel scene lokhra bech nbadlou root lih houaa
            btn_mesRegimes.getScene().setRoot(root);
        } catch (Exception e) {
            
        }
                            
                        });
                        HBox managebtn = new HBox(editIcon, deleteIcon , calIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(calIcon, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                        setText(null);
                    }
                }
      };
      return cell;
      };
        colActions.setCellFactory(cellFoctory);
        
        colRemarque.setCellFactory(ChoiceBoxTableCell.forTableColumn("Item1", "Item2", "Item3"));
       tableSuivis.setItems(ob);
    }

   
    @FXML
    private void showStatistique(MouseEvent event) {
               try {
            fxml = FXMLLoader.load(getClass().getResource("StatistiqueRegime.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(CategorieRegimeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
