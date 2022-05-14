/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Repas;
import entites.SuiviRegime;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import jdk.nashorn.internal.objects.NativeArray;
import services.RepasService;
import services.SuiviRegimeService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Kouki
 */
public class GestionRepasController implements Initializable {
    SuiviRegime SUIV =new SuiviRegime();
    private int suiviId ;
    private Parent fxml;
    @FXML
    private AnchorPane root;
    @FXML
    private Pane ContentPaneGestionnaire;
    @FXML
    private Button btn_AjouterRepas;
    @FXML
    private Label labelReapsAjour;
    @FXML
    private TableView<Repas> table_ReapsAujourdhui;
    @FXML
    private TableColumn<Repas, String> colTitreAuj;
    @FXML
    private TableColumn<Repas,String> colStartAuj;
    @FXML
    private TableColumn<Repas, String> colEndAuj;
    @FXML
    private TableColumn<Repas, String> colDescriptionAuj;
    @FXML
    private TableColumn<Repas, String> colToujoursAuj;
    @FXML
    private TableColumn<Repas, String> colActionsAuj;
    @FXML
    private Label labelReapsAjour1;
    @FXML
    private TableView<Repas> table_AutreReaps;
    @FXML
    private TableColumn<Repas, String> colTitreAutre;
    @FXML
    private TableColumn<Repas, String> colStartAutre;
    @FXML
    private TableColumn<Repas, String> colEndAutre;
    @FXML
    private TableColumn<Repas, String> colDescriptionAutre;
    @FXML
    private TableColumn<Repas, String> colToujoursAutre;
    @FXML
    private TableColumn<Repas, String> colActionsAutre;
    @FXML
    private Button btn_listCategorie;
    @FXML
    private Button btn_mesRegimes;
    @FXML
    private Button btn_Statistique;
    @FXML
    private Button btn_ListSuivi;
    RepasService repasServ = new RepasService();
    SuiviRegimeService suiser = new SuiviRegimeService();
    List<Repas> listReaps = null ;
    
      List<Repas>  lisReapsAuj = new ArrayList<Repas>(); 
           List<Repas>  listAutre =  new ArrayList<Repas>(); 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      // displayTabRepas();
    }    

    @FXML
    private void ajouterRepas(MouseEvent event) {
                            FXMLLoader loader = new FXMLLoader ();
                             loader.setLocation(getClass().getResource("RepasForm.fxml"));
                        
                              
                             try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(CategorieRegimeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                             RepasFormController formcnt =  loader.getController();
                             formcnt.setSuivId(SUIV.getId());
                             Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show(); 
    }

    @FXML
    private void showListCtegorieRegime(MouseEvent event) {
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
    private void showMesRegimes(MouseEvent event) {
         try {
            fxml = FXMLLoader.load(getClass().getResource("MesRegimes.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(DashboardNutritionnisteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 
    public void displayTabRepas() {
            
              LocalDate todaysDate = LocalDate.now();
          listReaps = repasServ.getAllCalendarSuivi(SUIV.getId());
         
           
           for (int i = 0; i < listReaps.size(); i++) {
               String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(listReaps.get(i).getEnd());
         
               if(date.equals(todaysDate.toString())){
                  
                   
                   lisReapsAuj.add(listReaps.get(i));
                 
               }else{
                   listAutre.add(listReaps.get(i));
                  
               }
           }
        ;
     
          ObservableList<Repas> ob = FXCollections.observableArrayList(lisReapsAuj);
       
       colTitreAuj.setCellValueFactory(new PropertyValueFactory<>("title"));
       colStartAuj.setCellValueFactory(new PropertyValueFactory<>("start")); 
       colEndAuj.setCellValueFactory(new PropertyValueFactory<>("end"));
       colDescriptionAuj.setCellValueFactory(new PropertyValueFactory<>("description"));  
       colToujoursAuj.setCellValueFactory(new PropertyValueFactory<>("toujour"));
     
         //les repas autres 
       
         ObservableList<Repas> ob2 = FXCollections.observableArrayList(listAutre);
       colTitreAutre.setCellValueFactory(new PropertyValueFactory<>("title"));
       colStartAutre.setCellValueFactory(new PropertyValueFactory<>("start")); 
       colEndAutre.setCellValueFactory(new PropertyValueFactory<>("end"));
       colDescriptionAutre.setCellValueFactory(new PropertyValueFactory<>("description"));  
       colToujoursAutre.setCellValueFactory(new PropertyValueFactory<>("toujour"));
        
      
       Callback<TableColumn<Repas, String>, TableCell<Repas, String>> cellFoctory = (TableColumn<Repas, String> param) -> {
           final TableCell<Repas, String> cell = new TableCell<Repas, String>() {
  
              
               
                @Override
                public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                           
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
                            alert.setTitle("Suppression du repas");
                            alert.setContentText("voulez vous vraiment supprimer cette repas ?");
                            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                            alert.getButtonTypes().setAll(okButton, noButton);
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == okButton){
                                 Repas rep = table_ReapsAujourdhui.getSelectionModel().getSelectedItem();
                                 repasServ.deleteRepas(rep.getId());
                                 String titles = "repas supprimée ";
                                    String msgs = "avec succées";
                                    TrayNotification trays = new TrayNotification();
                                AnimationType types = AnimationType.SLIDE;
                                trays.setAnimationType(types);
                                 trays.setTitle(titles);
                                trays.setMessage(msgs);
                                 trays.showAndDismiss(Duration.seconds(5));
                                 trays.setNotificationType(NotificationType.SUCCESS);
                                 displayTabRepas();
                            } else if (result.get() == noButton) {
                                 
                                  alert.close();
                                    }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                       
                        
                        });
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
       colActionsAuj.setCellFactory(cellFoctory);
       table_ReapsAujourdhui.setItems(ob);
       
         Callback<TableColumn<Repas, String>, TableCell<Repas, String>> cellFoctory2 = (TableColumn<Repas, String> param) -> {
           final TableCell<Repas, String> cell2 = new TableCell<Repas, String>() {
  
              
               
                @Override
                public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                           
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
                            alert.setTitle("Suppression du repas");
                            alert.setContentText("voulez vous vraiment supprimer cette repas ?");
                            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                            alert.getButtonTypes().setAll(okButton, noButton);
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == okButton){
                                 Repas rep = table_AutreReaps.getSelectionModel().getSelectedItem();
                                 repasServ.deleteRepas(rep.getId());
                                 String titles = "repas supprimée ";
                                    String msgs = "avec succées";
                                    TrayNotification trays = new TrayNotification();
                                AnimationType types = AnimationType.SLIDE;
                                trays.setAnimationType(types);
                                 trays.setTitle(titles);
                                trays.setMessage(msgs);
                                 trays.showAndDismiss(Duration.seconds(5));
                                 trays.setNotificationType(NotificationType.SUCCESS);
                                 displayTabRepas();
                            } else if (result.get() == noButton) {
                                 
                                alert.close();
                                    }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                       
                        
                        });
                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);
                    }
                }
       

  

           };
           
        return cell2;
    };
     
       colActionsAutre.setCellFactory(cellFoctory2);
       table_AutreReaps.setItems(ob2);
    }

  
    
    void initData(SuiviRegime suivi) {
    this.suiviId= suivi.getId();
  }
    
    
    
    void suiviId(int id) {
      this.suiviId = id;
      SUIV = suiser.getSuiviRegime(id);
      
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
