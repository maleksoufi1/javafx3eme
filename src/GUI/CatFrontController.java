/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXTextField;
import entites.CategorieEvenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import services.CategorieEvenementService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author myria
 */
public class CatFrontController implements Initializable {

    @FXML
    private TableView<CategorieEvenement> CategorieEvT;
    @FXML
    private TableColumn<CategorieEvenement, ?> LibelleCol;
    @FXML
    private TableColumn<CategorieEvenement, ?> DescriptionCol;
    @FXML
    private TableColumn<CategorieEvenement, ?> ImageCol;
    @FXML
    private TableColumn<?, ?> editCol;
    private AnchorPane root;
    private Parent fxml;
    Connection connection = null;
     ObservableList<CategorieEvenement> CatList = FXCollections.observableArrayList();  
    @FXML
    private Text titreForm;
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
          recher();
    }    

    @FXML
    private void cat(MouseEvent event) {
        this.titreForm.setText("Catégorie");
        try {
            fxml = FXMLLoader.load(getClass().getResource("CatFront.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(CategorieEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void even(MouseEvent event) {
        this.titreForm.setText("Evenement");
         try {
            fxml = FXMLLoader.load(getClass().getResource("EvFront.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(CategorieEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDate() {
       connection=DataSource.getInstance().getConn();  
        
        
        
        LibelleCol.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        ImageCol.setCellValueFactory(new PropertyValueFactory<>("image"));  }

    @FXML
    private void REFRESH() {
         CatList.clear();
                CategorieEvenementService RS = new CategorieEvenementService();
        CatList.addAll(RS.getAll());
        RS.getAll();
        
        CategorieEvT.setItems(CatList);
    }

    private void recher() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<CategorieEvenement> filteredData = new FilteredList<>(CatList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(evenement -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (evenement.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                                    this.msg.setText("");
					return true; // Filter matches first name.
				} else if (evenement.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                    this.msg.setText("");
					return true; // Filter matches last name.
				}
				     else  {
                                    this.msg.setText("pas de résultat");
                                     return false; // Does not match.
                                }
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<CategorieEvenement> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(CategorieEvT.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		CategorieEvT.setItems(sortedData);
    }
    
}
