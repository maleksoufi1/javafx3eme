/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

//import com.github.plushaze.traynotification.notification.Notifications;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.xml.internal.utils.URI;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entites.Billet;
import entites.CategorieEvenement;
import entites.Evenement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
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
import javafx.geometry.Pos;
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
import services.BilletService;
import services.EvenementService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author myria
 */
public class BilletController implements Initializable {

    @FXML
    private FontAwesomeIconView add;
    @FXML
    private FontAwesomeIconView refresh;
    @FXML
    private FontAwesomeIconView print;
    @FXML
    private TableColumn<Billet, Date> HorraireCol;
    @FXML
    private TableColumn<Billet, String> editCol;
    @FXML
    private ImageView img;
    @FXML
    private Button gestionCat;
    @FXML
    private Button gestionEv;
    @FXML
    private JFXTextField recherche;
    @FXML
    private Text msg;
    @FXML
    private TableView<Billet> BilletT;
    @FXML
    private TableColumn<Billet, Double> numeroCol;
    @FXML
    private TableColumn<Billet, String> DetailCol;
    @FXML
    private TableColumn<Billet, String> EvenementCol;
    
    ObservableList<Billet> BilList = FXCollections.observableArrayList();
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Evenement Evenement = null;
    
    private AnchorPane root;
    private Parent fxml;
    @FXML
    private TableColumn<Billet, Integer> QuantiteCol;
    @FXML
    private Button Billet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         img.setImage(new Image("/images/upload.jpg"));
        displayTabBillet();
        refresh();
        RechercheAV();
    }    

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    private void refresh() {
        BilList.clear();
                BilletService RS = new BilletService();
        BilList.addAll(RS.getAll());
        RS.getAll();
        
        BilletT.setItems(BilList);
    }

    @FXML
    private void print(MouseEvent event) {
        
        String FILE_NAME = "C:\\Users\\myria\\OneDrive\\Bureau\\listebillet.pdf";
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
            document.open();
            Paragraph p = new Paragraph();
            p.add("Liste des Billets des évenements");
            p.setAlignment(Element.ALIGN_CENTER);
            p.add("\n");
            p.add("\n");
            p.add("\n");
            document.add(p);
//            Paragraph p2 = new Paragraph();
//            p2.add("Text 2"); //no alignment
//            document.add(p2);
            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(8);
//            document.add(new Paragraph("This is my paragraph 3", f));
             // document.add(Image.getInstance("C:\\Users\\myria\\OneDrive\\Bureau\\log.png")); 
            
            
            connection=DataSource.getInstance().getConn();  
            String query = "select * from billet";
            Statement stmt = null;
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Paragraph p3 = null;
            while (rs.next()) {
               // PdfPTable tb1 = new PdfPTable(5);
                p3 = new Paragraph();
                p3.add("Numero        Détail:        Horraire:        Evenement associé:\n");
                p3.add(rs.getDouble("numero")+"        "+rs.getString("detail")+"        "+rs.getDate("horaire")+"        "+rs.getString("evenement_id")+"\n");
                 p3.add("******************\n");
                 p3.add("\n");
                 
               // p3.add(rs.getString("detail"));
                //tb1.addCell(rs.getString("horaire"));
                document.add(p3);
            }
            document.close();
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
                     String titles = "Liste des Billets téléchargée ";
                String msgs = "avec succées";
                TrayNotification trays = new TrayNotification();
                AnimationType types = AnimationType.POPUP;
               
                trays.setAnimationType(types);
                trays.setTitle(titles);
                trays.setMessage(msgs);
                trays.showAndDismiss(javafx.util.Duration.seconds(10));
                trays.setNotificationType(NotificationType.INFORMATION);
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

    @FXML
    private void ajouterBil(MouseEvent event) {
       try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/AddBillet.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void displayTabBillet() {
         FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
    FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
        BilletService regServ = new BilletService();
      List<Billet> regList = regServ.getAll();
      ObservableList<Billet> ob = FXCollections.observableArrayList(regList);
       numeroCol.setCellValueFactory(new PropertyValueFactory<>("numero"));
       DetailCol.setCellValueFactory(new PropertyValueFactory<>("detail"));     
       HorraireCol.setCellValueFactory(new PropertyValueFactory<>("horaire"));
      
      
       EvenementCol.setCellValueFactory(new PropertyValueFactory<>("evenement"));
        QuantiteCol.setCellValueFactory(new PropertyValueFactory<>("quantite"));
       
   
       
       
       
       
       
       
       Callback<TableColumn<Billet, String>, TableCell<Billet, String>> cellFoctory = (TableColumn<Billet, String> param) -> {
           final TableCell<Billet, String> cell = new TableCell<Billet, String>() {
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
                                  
                                    Billet reg = (Billet) BilletT.getSelectionModel().getSelectedItem();
                                    regServ.deleteBillet(reg.getId());
                                    displayTabBillet();
                                    } else if (result.get() == noButton) {
                                 
                                 displayTabBillet();
                                    }
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                       Billet reg = (Billet) BilletT.getSelectionModel().getSelectedItem();
                                    
                             FXMLLoader loader = new FXMLLoader ();
                             loader.setLocation(getClass().getResource("/GUI/AddBillet.fxml"));
                             try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(BilletController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                             
                             AddBilletController FormController = loader.getController();
                           
                             FormController.setUpdate(true);
                              
                             FormController.setTextField(reg.getId(), reg.getNumero(), reg.getDetail(), 
                            reg.getEvenement(),reg.getHoraire().toLocalDate());
                          
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
       BilletT.setItems(ob);
        }

    private void RechercheAV() {
        
        
        
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Billet> filteredData = new FilteredList<>(BilList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(billet -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(billet.getNumero()).indexOf(lowerCaseFilter)!=-1 ) {
                                    this.msg.setText("");
					return true; // Filter matches first name.
				} else if (billet.getDetail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
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
		SortedList<Billet> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(BilletT.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		BilletT.setItems(sortedData); 
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
