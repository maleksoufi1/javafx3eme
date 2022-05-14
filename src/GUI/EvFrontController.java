/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXTextField;
import entites.CategorieEvenement;
import entites.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.CategorieEvenementService;
import services.EvenementService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author myria
 */
public class EvFrontController implements Initializable {

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
    @FXML
    private Text titreForm;
private AnchorPane root;
    private Parent fxml;
    Connection connection = null;
     ObservableList<Evenement> EvList = FXCollections.observableArrayList();
    @FXML
    private JFXTextField recherche;
    @FXML
    private Text msg;
    @FXML
    private ImageView img;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         img.setImage(new Image("/img/upload.jpg"));
         loadDate();
         REFRESH();
         rech();
    }    

    @FXML
    private void cat(MouseEvent event) {
         this.titreForm.setText("Catégorie");
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/CatFront.fxml"));
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
    private void even(MouseEvent event) {
        this.titreForm.setText("Evenement");
          try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/EvFront.fxml"));
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
    private void REFRESH() {
     EvList.clear();
                EvenementService RS = new EvenementService();
        EvList.addAll(RS.getAll());
        RS.getAll();
        
        EvenementT.setItems(EvList);
    }

    private void loadDate() {
        connection=DataSource.getInstance().getConn();  
        
        
        
        TitreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
       DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));     
       HorraireCol.setCellValueFactory(new PropertyValueFactory<>("horraire")); 
       LongitudeCol.setCellValueFactory(new PropertyValueFactory<>("longitude"));    
       LatitudeCol.setCellValueFactory(new PropertyValueFactory<>("latitude"));
       CategorieCol.setCellValueFactory(new PropertyValueFactory<>("categorie"));
         
        
    }

    private void rech() {
          
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
    
}
