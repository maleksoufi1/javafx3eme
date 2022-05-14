/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.CategorieEvenement;
import entites.CategorieRegime;
import entites.Evenement;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import javax.swing.JFileChooser;
import services.CategorieEvenementService;
import services.CategorieRegimeService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author myria
 */
public class CategorieEvenementController implements Initializable {

    @FXML
    private TableView<CategorieEvenement> CategorieEvT;
    @FXML
    private TableColumn<CategorieEvenement, String> LibelleCol;
    @FXML
    private TableColumn<CategorieEvenement, String> DescriptionCol;
    @FXML
    private TableColumn<CategorieEvenement, String> ImageCol;
    
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    CategorieEvenement categorieEvenement = null;
private AnchorPane root;
    private Parent fxml;
    ObservableList<CategorieEvenement> CatList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<CategorieEvenement, String> editCol;
    @FXML
    private JFXTextField recherche;
    @FXML
    private Text msg;
    @FXML
    private ImageView img;
    @FXML
    private FontAwesomeIconView print;
    @FXML
    private Button Billet;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         img.setImage(new Image("/images/upload.jpg"));
        loadDate();
        Refresh();
        recherche();
    }    

    @FXML
    private void Close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void getAdd(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/AddCatEvenement.fxml"));
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
    private void Refresh() {
        CatList.clear();
                CategorieEvenementService RS = new CategorieEvenementService();
        CatList.addAll(RS.getAll());
        RS.getAll();
        
        CategorieEvT.setItems(CatList);
//               String titles = "Hotelier ajouté ";
//                String msgs = "avec succées";
//                TrayNotification trays = new TrayNotification();
//                AnimationType types = AnimationType.POPUP;
//               
//                trays.setAnimationType(types);
//                trays.setTitle(titles);
//                trays.setMessage(msgs);
//                trays.showAndDismiss(Duration.seconds(10));
//                trays.setNotificationType(NotificationType.SUCCESS);

//Notifications notificationBuilder = Notifications.create().title("test").text("test").graphic(null).hideAfter(Duration.seconds(5));
    }

    @FXML
    private void Print(MouseEvent event) {
        
//        String path = "";
//        JFileChooser j  = new JFileChooser();
//        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        int x = j.showSaveDialog(j);
//        if (x== JFileChooser.APPROVE_OPTION){
//            
//            path = j.getSelectedFile().getPath();
//        }
//        Document doc = new Document();
//        try {
//            PdfWriter.getInstance(doc , new FileOutputStream(path+"liste.pdf"));
//            
//            
//            
//            
//            doc.open();
//            PdfPTable tb1 = new PdfPTable(3);
//            
//            
//            
//            
//            
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(CategorieEvenementController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (DocumentException ex) {
//            Logger.getLogger(CategorieEvenementController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void loadDate() {
//        System.out.println("saluttttttttttttttttttttttttttttttttt");
//          CategorieEvenementService cs = new CategorieEvenementService();
//        List<CategorieEvenement> CatList =  cs.getAll();
//         System.out.println("ssss"+CatList);
//        ObservableList<CategorieEvenement> ob = FXCollections.observableArrayList(CatList);
        connection=DataSource.getInstance().getConn();  
        
        
        
        LibelleCol.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        ImageCol.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        
        
        
        //add cell of button edit 
         Callback<TableColumn<CategorieEvenement, String>, TableCell<CategorieEvenement, String>> cellFoctory = (TableColumn<CategorieEvenement, String> param) -> {
            // make cell containing buttons
            final TableCell<CategorieEvenement, String> cell = new TableCell<CategorieEvenement, String>() {
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
                            
                            try {
                                categorieEvenement = CategorieEvT.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `categorie_evenement` WHERE id  ="+categorieEvenement.getId();
                                connection=DataSource.getInstance().getConn();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                Refresh();
                                
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(CategorieEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            categorieEvenement = CategorieEvT.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/GUI/AddCatEvenement.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(CategorieEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddCatEvenementController addCatEvenementController = loader.getController();
                            addCatEvenementController.setUpdate(true);
                            addCatEvenementController.setTextField( categorieEvenement.getId(),categorieEvenement.getLibelle(), 
                                    categorieEvenement.getDescription(), categorieEvenement.getImage());
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
         editCol.setCellFactory(cellFoctory ) ;
         CategorieEvT.setItems(CatList);
        
        }

    private void categories(MouseEvent event) {
        
    }

    private void evenement(MouseEvent event) {
        
    }

    @FXML
    private void gestionCat(MouseEvent event) {
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
    private void gestionEv(MouseEvent event) {
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

    private void recherche() {// Wrap the ObservableList in a FilteredList (initially display all data).
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
		CategorieEvT.setItems(sortedData); }

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
