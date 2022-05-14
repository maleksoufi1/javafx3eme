/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.CategorieRegime;
import entites.Regime;
import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import services.RegimeService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Kouki
 */
public class MesRegimesController implements Initializable {
    private Image img;
   private ImageView imgv;
  @FXML
    private AnchorPane root;
    private Parent fxml;
    @FXML
    private Pane ContentPaneGestionnaire;
    @FXML
    private Button btn_listCategorie;
    @FXML
    private Button btn_mesRegimes;
    @FXML
    private Button btn_Statistique;
    @FXML
    private Button btn_ListSuivi;
    @FXML
    private TableView<Regime> tabRegime;
    @FXML
    private TableColumn<Regime, String> colType;
    @FXML
    private TableColumn<Regime, String>colDescription;
    @FXML
    private TableColumn<Regime, String>colDificulte;
    @FXML
    private TableColumn<Regime, Float> colPrix;
    @FXML
    private TableColumn<Regime, ImageView> colImage;
    @FXML
    private TableColumn<Regime, String> colActions;
    @FXML
    private TableColumn<Regime, String> colCategorie;
    @FXML
    private Button btn_ajouter;
    @FXML
    private ImageView imageRegime;
    
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       LocalDate todaysDate = LocalDate.now();
       displayTabRegime();
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

    @FXML
    void showStatistique(MouseEvent event) {
               try {
            fxml = FXMLLoader.load(getClass().getResource("StatistiqueRegime.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(CategorieRegimeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void displayTabRegime(){
    FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
    FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
        RegimeService regServ = new RegimeService();
        //les regime de user connecté
        //session user id 2
      List<Regime> regList = regServ.getMesRegime(2);
      ObservableList<Regime> ob = FXCollections.observableArrayList(regList);
       colType.setCellValueFactory(new PropertyValueFactory<>("type"));
       colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));     
       colDificulte.setCellValueFactory(new PropertyValueFactory<>("dificulte")); 
    
       colImage.setCellValueFactory(new PropertyValueFactory<>("image"));
     
       colImage.setPrefWidth(90);
       colCategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
       colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));    
        
      
       Callback<TableColumn<Regime, String>, TableCell<Regime, String>> cellFoctory = (TableColumn<Regime, String> param) -> {
           final TableCell<Regime, String> cell = new TableCell<Regime, String>() {
  
              
               
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
                            alert.setTitle("Suppression du regime");
                            alert.setContentText("voulez vous vraiment supprimer ce régime ?");
                            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                            alert.getButtonTypes().setAll(okButton, noButton);
                            Optional<ButtonType> result = alert.showAndWait();
                                    if (result.get() == okButton){
                                  
                                    Regime reg = tabRegime.getSelectionModel().getSelectedItem();
                                    regServ.deleteRegime(reg.getId());
                                    displayTabRegime();
                String titles = "régime supprimé ";
                String msgs = "avec succées";
                TrayNotification trays = new TrayNotification();
                AnimationType types = AnimationType.SLIDE;
                trays.setAnimationType(types);
                trays.setTitle(titles);
                trays.setMessage(msgs);
                trays.showAndDismiss(Duration.seconds(5));
                trays.setNotificationType(NotificationType.SUCCESS);
                   
                                    } else if (result.get() == noButton) {
                                 
                                 displayTabRegime();
                                    }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                       Regime reg = tabRegime.getSelectionModel().getSelectedItem();
                                    
                             FXMLLoader loader = new FXMLLoader ();
                             loader.setLocation(getClass().getResource("FormRegime.fxml"));
                             try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(MesRegimesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                             
                             FormRegimeController FormController = loader.getController();
                             FormController.setUpdate(true);
                             FormController.setTextField(reg.getId(), reg.getType(), reg.getDescription(), 
                             reg.getDificulte(),reg.getCategorie(),reg.getImage(),reg.getPrix());

                             Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                        
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
       colActions.setCellFactory(cellFoctory);
       tabRegime.setItems(ob);
       tabRegime.setRowFactory( tv -> {
    TableRow<Regime> row = new TableRow<>();
    row.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
            Regime rowData = row.getItem();
             String path = "C:\\xampp\\htdocs\\adomifitt\\public\\uploads\\images\\"+rowData.getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
              imageRegime.setImage(img);
           
        }
    });
    return row ;
});
            }
    

    

    @FXML
    private void ajouterRegime(MouseEvent event) {
         FXMLLoader loader = new FXMLLoader ();
                             loader.setLocation(getClass().getResource("FormRegime.fxml"));
                             try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(CategorieRegimeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                             Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show(); 
    }
    }
    
    

