/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.CategorieRegime;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
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
public class CategorieRegimeController implements Initializable {
   
    @FXML
    private TableView<CategorieRegime>tabCategories;
    
    @FXML
    private Pane ContentPaneGestionnaire;
   @FXML
    private AnchorPane root;
    private Parent fxml;
    @FXML
    private TableColumn<CategorieRegime, String> colLibelle;

    @FXML
    private TableColumn<CategorieRegime, String> colDescription;

    @FXML
    private TableColumn<CategorieRegime, String> colStatColor;

    @FXML
    private Button btn_listCategorie;

    @FXML
    private Button btn_mesRegimes;

    @FXML
    private Button btn_Statistique;

    @FXML
    private Button btn_ListSuivi;
    @FXML
    private TableColumn<CategorieRegime, String> colActions;
    @FXML
    private Button btn_ajout;
    @FXML
    private ColorPicker statColorTF;
    @FXML
    private TextField libelleTF;
    @FXML
    private TextArea descriptionTF;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   displayTabCategorie();
    }    
    public void displayTabCategorie(){
     FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
    FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
          CategorieRegimeService cs = new CategorieRegimeService();
        List<CategorieRegime> CatList =  cs.getAll();
        ObservableList<CategorieRegime> ob = FXCollections.observableArrayList(CatList);
       colLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));      
       colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
          
       colStatColor.setCellValueFactory(new PropertyValueFactory<>("statcolor"));
             // colStatColor.setStyle("-fx-background-color: statcolor;");
              
       //add cell of actions 
            Callback<TableColumn<CategorieRegime, String>, TableCell<CategorieRegime, String>> cellFoctory = (TableColumn<CategorieRegime, String> param) -> {
            // make cell containing buttons
            final TableCell<CategorieRegime, String> cell = new TableCell<CategorieRegime, String>() {
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
                            alert.setTitle("Suppression du categorie regime");
                            alert.setContentText("voulez vous vraiment supprimer cette categorie ?");
                            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                            alert.getButtonTypes().setAll(okButton, noButton);
                            Optional<ButtonType> result = alert.showAndWait();
                                            if (result.get() == okButton){
                                  
                                    CategorieRegime categorie = tabCategories.getSelectionModel().getSelectedItem();
                                    cs.deleteCategorieRegime(categorie.getId());
                String titles = "Catégorie supprimée ";
                String msgs = "avec succées";
                TrayNotification trays = new TrayNotification();
                AnimationType types = AnimationType.SLIDE;
                trays.setAnimationType(types);
                trays.setTitle(titles);
                trays.setMessage(msgs);
                trays.showAndDismiss(Duration.seconds(5));
                trays.setNotificationType(NotificationType.SUCCESS);
                                    displayTabCategorie();
                                    } else if (result.get() == noButton) {
                                 
                                 displayTabCategorie();
                                    }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            CategorieRegime categorie = tabCategories.getSelectionModel().getSelectedItem();
                             FXMLLoader loader = new FXMLLoader ();
                             loader.setLocation(getClass().getResource("UpdateCategorieRegime.fxml"));
                             try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(CategorieRegimeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                             
                             UpdateCategorieRegimeController upController = loader.getController();
                             upController.setTextField(categorie.getId(), categorie.getLibelle(), categorie.getDescription(), categorie.getStatcolor());

                             Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show(); 
                            
               
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
       colActions.setCellFactory(cellFoctory);
  

       tabCategories.setItems(ob);
    }
  
         @FXML
    void showListCtegorieRegime(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("CategorieRegime.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(CategorieRegimeController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CategorieRegimeController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void showListCtegorieRegime(ActionEvent event) {
         try {
            fxml = FXMLLoader.load(getClass().getResource("CategorieRegime.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(DashboardNutritionnisteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    @FXML
    private void addCategorie(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setHeaderText(null);
           String hex2 = "#" + Integer.toHexString(statColorTF.getValue().hashCode()).substring(0, 6);
       if(libelleTF.getText().equals("")){
       libelleTF.setStyle("  -fx-text-box-border: red; -fx-focus-color: red ;");
        alert.setContentText("Verifier libelle du categorie ! ");
        alert.showAndWait();
       return;
       }
        if(descriptionTF.getText().equals("")){
       descriptionTF.setStyle("  -fx-text-box-border: red; -fx-focus-color: red ;");
        alert.setContentText("Verifier description du categorie ! ");
        alert.showAndWait();
       return;
       }
         if("#ffffff".equals(hex2)){
       statColorTF.setStyle("-fx-text-box-border: red; -fx-focus-color: red ;");
        alert.setContentText(" pas de couleur blanc dans statistique ! ");
        alert.showAndWait();
       return;
       }
        if(libelleTF.getText().equals("") || descriptionTF.getText().equals("") || statColorTF.getValue().equals(null)  ){
           
           
           
            return;
        }else{
            
         
           
         CategorieRegime cr = new CategorieRegime(libelleTF.getText(),descriptionTF.getText(),hex2);
         
         CategorieRegimeService cs = new CategorieRegimeService();
         cs.addCategorieRegime(cr);
         libelleTF.setText(null);
         descriptionTF.setText(null);
         statColorTF.setValue(null);
         displayTabCategorie();
                String titles = "Catégorie ajouté ";
                String msgs = "avec succées";
                TrayNotification trays = new TrayNotification();
                AnimationType types = AnimationType.SLIDE;
                trays.setAnimationType(types);
                trays.setTitle(titles);
                trays.setMessage(msgs);
                trays.showAndDismiss(Duration.seconds(5));
                trays.setNotificationType(NotificationType.SUCCESS);
                        
        }
    }

    
    
    
}
