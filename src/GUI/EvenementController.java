/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.CategorieEvenement;
import entites.Evenement;
import entites.Regime;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import services.CategorieEvenementService;
import services.EvenementService;
import services.RegimeService;

/**
 * FXML Controller class
 *
 * @author myria
 */
public class EvenementController implements Initializable {

    @FXML
    private FontAwesomeIconView add;
    @FXML
    private FontAwesomeIconView refresh;
    @FXML
    private FontAwesomeIconView print;
    @FXML
    private TableColumn<Evenement, String> TitreCol;
    @FXML
    private TableColumn<Evenement, String> DescriptionCol;
    @FXML
    private TableColumn<Evenement, Date> HorraireCol;
    @FXML
    private TableColumn<Evenement, Float> LongitudeCol;
    @FXML
    private TableColumn<Evenement, Float> LatitudeCol;
    @FXML
    private TableColumn<Evenement, String> ImageCol;
    @FXML
    private TableColumn<Evenement, String> CategorieCol;
    @FXML
    private TableView<Evenement> EvenementT;
    @FXML
    private TableColumn<Evenement, String> editCol;
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Evenement Evenement = null;
    
    private AnchorPane root;
    private Parent fxml;

    ObservableList<Evenement> EvList = FXCollections.observableArrayList();
    @FXML
    private Button gestionCat;
    @FXML
    private Button gestionEv;
    @FXML
    private JFXTextField recherche;
    @FXML
    private Text msg;
    @FXML
    private Button gestionBil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayTabEvenement();
        refresh();
        RechercheAV();
    }    

    public void displayTabEvenement() {
        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
    FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
        EvenementService regServ = new EvenementService();
      List<Evenement> regList = regServ.getAll();
      ObservableList<Evenement> ob = FXCollections.observableArrayList(regList);
       TitreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
       DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));     
       HorraireCol.setCellValueFactory(new PropertyValueFactory<>("horraire")); 
       LongitudeCol.setCellValueFactory(new PropertyValueFactory<>("longitude"));    
       LatitudeCol.setCellValueFactory(new PropertyValueFactory<>("latitude"));
       ImageCol.setCellValueFactory(new PropertyValueFactory<>("image"));
       CategorieCol.setCellValueFactory(new PropertyValueFactory<>("categorie"));
       
   
       
       
       
       
       
       
       Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> cellFoctory = (TableColumn<Evenement, String> param) -> {
           final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {
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
                                + "-fx-fill:#071715;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Suppression de l'evenement");
                            alert.setContentText("voulez vous vraiment supprimer cet évenement ?");
                            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                            alert.getButtonTypes().setAll(okButton, noButton);
                            Optional<ButtonType> result = alert.showAndWait();
                                    if (result.get() == okButton){
                                  
                                    Evenement reg = EvenementT.getSelectionModel().getSelectedItem();
                                    regServ.deleteEvenement(reg.getId());
                                    displayTabEvenement();
                                    } else if (result.get() == noButton) {
                                 
                                 displayTabEvenement();
                                    }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                       Evenement reg = EvenementT.getSelectionModel().getSelectedItem();
                                    
                             FXMLLoader loader = new FXMLLoader ();
                             loader.setLocation(getClass().getResource("/GUI/AddEvenement.fxml"));
                             try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                             
                             AddEvenementController FormController = loader.getController();
                           
                             FormController.setUpdate(true);
                              
                             FormController.setTextField(reg.getId(), reg.getTitre(), reg.getDescription(), 
                            reg.getCategorie(),reg.getImage(),reg.getHorraire().toLocalDate(),reg.getLongitude(),reg.getLatitude());
                          
                             Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            System.out.println("ee");
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
       editCol.setCellFactory(cellFoctory);
       EvenementT.setItems(ob);
       
            }

    @FXML
    private void ajouterEv(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/AddEvenement.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    
    private void refresh() {
        EvList.clear();
                EvenementService RS = new EvenementService();
        EvList.addAll(RS.getAll());
        RS.getAll();
        
        EvenementT.setItems(EvList);
    }

    @FXML
    private void print(MouseEvent event) {
    }

    @FXML
    private void close(MouseEvent event) {
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    private void gestionCat1(MouseEvent event) {
      try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/CategorieEvenement.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void gestionEv1(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/Evenement.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    private void RechercheAV() {
          
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Evenement> filteredData = new FilteredList<>(EvList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(evenement -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (evenement.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                                    this.msg.setText("");
					return true; // Filter matches first name.
				} else if (evenement.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                    this.msg.setText("");
					return true; // Filter matches last name.
				}else if (String.valueOf(evenement.getHorraire()).indexOf(lowerCaseFilter)!=-1){
                                    this.msg.setText("");
                                    return true;
                                }
				     
                                else if (String.valueOf(evenement.getLongitude()).indexOf(lowerCaseFilter)!=-1){
                                    this.msg.setText("");
                                    return true;
                                }
				     
                                else if (String.valueOf(evenement.getLatitude()).indexOf(lowerCaseFilter)!=-1){
                                    this.msg.setText("");
                                    return true;
                                }
				     
				     else 
                                {
                                     this.msg.setText("pas de résultat");
                                     return false; // Does not match.
                                }
				    	
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Evenement> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(EvenementT.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		EvenementT.setItems(sortedData);
       }

    @FXML
    private void gestionBil(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/Billet.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }

    
        
    

