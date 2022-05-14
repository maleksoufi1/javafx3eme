/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */package GUI;


import com.itextpdf.text.DocumentException;
import entites.Produit;
import entites.Produit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;

import static java.rmi.Naming.list;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import services.ProduitService;
//import services.Smsapi;
import utils.DataSource;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.CategorieProduitService;
import services.Smsapi;
import services.pdfequipement;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ProduitController implements Initializable {

    @FXML
    private Button bsave;
    @FXML
    private Button bupdate;
    @FXML
    private Button bdelete;
    @FXML
    private TableView<Produit> table;
    @FXML
    private TableColumn<?, ?> colnom;
    @FXML
    private TableColumn<?, ?> colimage;
    @FXML
    private TableColumn<?, ?> colprix;
    @FXML
    private TableColumn<?, ?> coldesc;
    @FXML
    private TableColumn<?, ?> colcateg;
    @FXML
    private TextField desc;
    @FXML
    private TextField Nom;
    private TextField image;
    @FXML
    private TextField prix;
    @FXML
    private TextField tfrecherche;
    
    public ObservableList<Produit> dataa = FXCollections.observableArrayList();
    @FXML
    private TextField idprod;
    @FXML
    private Button pdf;
    @FXML
    private ImageView fxlogo;
    @FXML
    private ImageView imaagee;
    @FXML
    private Button boutonimage;
     public String filename="";
     private List<String> cat = new ArrayList<>();
    @FXML
    private ChoiceBox<String> boxCat;
    @FXML
    private Button btn_commandes;
    @FXML
    private Button btn_maison;
    @FXML
    private Button btn_repas;
    @FXML
    private Button btn_resrepas;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_user1;
    @FXML
    private Button btn_user11;
    @FXML
    private Button btn_reserveract;
    @FXML
    private Button btn_resmais;
    @FXML
    private Button btn_evenements;
    @FXML
    private ImageView fxlogo1;
    @FXML
    private Button btn_programme;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
            colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
    colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
    colcateg.setCellValueFactory(new PropertyValueFactory<>("categorie_produit_id"));
    Refresh();
    AfficherProjet();
    
    
    
    CategorieProduitService c = new CategorieProduitService();
        cat = c.AllCatNames();
        boxCat.getItems().addAll(cat);
         
    } 
     private Connection connexion;
    private Statement ste;
        private PreparedStatement pst;
        private ResultSet rs;


    public ProduitController() {
        connexion=DataSource.getInstance().getConn();    
    }
    private void Refresh() {
          dataa.clear();
                ProduitService RS = new ProduitService();
        dataa.addAll(RS.getAll());
        RS.getAll();
        
        table.setItems(dataa);
    }
       private void AfficherProjet()  {
           
           
           Refresh();
          // colcin.setCellValueFactory(new PropertyValueFactory<>("id"));
    colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
    colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
    colcateg.setCellValueFactory(new PropertyValueFactory<>("categorie_produit_id"));
         

    }
 
    
    
    
     private void insert() {
         CategorieProduitService cp = new CategorieProduitService();
         String catg = boxCat.getValue();
            int idcat = cp.rechercheParCat(catg);
            

        String insert = "INSERT INTO produit (`nom`,`prix`,`image`,`description`,`categorie_produit_id`) VALUES (?,?,?,?,?) ;";
        try {
        PreparedStatement st =DataSource.getInstance().getConn().prepareStatement(insert);
            st.setString(1, Nom.getText());
            st.setInt(2, Integer.parseInt(prix.getText()));
            st.setString(3,filename); 
            st.setString(4, desc.getText());
            st.setInt(5,idcat);
           
            st.executeUpdate();
             Refresh();
            
            
            Smsapi sms= new Smsapi();
          Smsapi.sendSMS("+21698975800", "Produit Ajouté"); //apres integration nzid getuser num bech yabath lel con
          
          
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Add Success");
            tray.setMessage("Produit ajouter avec succes ");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void saveEvent(ActionEvent event) {
        insert();
    
    }

    @FXML
    private void Update(ActionEvent event) {
        CategorieProduitService cp = new CategorieProduitService();
         String catg = boxCat.getValue();
            int idcat = cp.rechercheParCat(catg);
      int id = Integer.valueOf(idprod.getText());
        String nom = Nom.getText();
        String descrip = desc.getText();
        String img = filename;
         int prixx = Integer.valueOf(prix.getText());
        
      //  int catego = Integer.valueOf(categ.getText());
       
        
//        java.sql.Date date = java.sql.Date.valueOf(Mdate_rec.getValue());
        try {

            System.out.println("Modifié avec succes");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           // stage.close();
            Alert ModifyAdmintAlert = new Alert(Alert.AlertType.CONFIRMATION);
            ModifyAdmintAlert.setTitle("Modifier Produit");
            ModifyAdmintAlert.setHeaderText(null);
            ModifyAdmintAlert.setContentText("Voulez-vous vraiment Modifier ce PRODUIT ? ?");
            Optional<ButtonType> optionModifyBookAlert = ModifyAdmintAlert.showAndWait();
            if (optionModifyBookAlert.get() == ButtonType.OK) {
                Produit r = new Produit(prixx, idcat, nom, img, descrip);
                ProduitService ad = new ProduitService();
               ad.update(id, prixx, nom, img, descrip);
               dataa.clear();
                dataa.addAll(ad.getAll());
                
               
                Alert succModifyAdminAlert = new Alert(Alert.AlertType.INFORMATION);
                succModifyAdminAlert.setTitle("Modifier Produit");
                succModifyAdminAlert.setHeaderText("Resultats:");
                succModifyAdminAlert.setContentText("Produit Modifié !");
                succModifyAdminAlert.showAndWait();
                
            }else if (optionModifyBookAlert.get() == ButtonType.CANCEL) {
            }
            

        } catch (Exception ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
        if (table.getSelectionModel().getSelectedItem() != null) {
            Alert deleteEventAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteEventAlert.setTitle("Suppression d'une Réservation");
            deleteEventAlert.setHeaderText(null);
            deleteEventAlert.setContentText("Veuillez supprimer cette Réservation ?");
            Optional<ButtonType> optionDeleteBookAlert = deleteEventAlert.showAndWait();
            if (optionDeleteBookAlert.get() == ButtonType.OK) {
                Produit R = table.getSelectionModel().getSelectedItem();
                ProduitService rc= new ProduitService();
                rc.deleteProduit(R);
                dataa.clear();
                dataa.addAll(rc.getAll());
                
                
                
                  TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Delete Success");
            tray.setMessage("Produit Supprimé avec succes ");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
                
               
                Alert succDeleteEventAlert = new Alert(Alert.AlertType.INFORMATION);
                succDeleteEventAlert.setTitle("Supprimer Produit");
               
                succDeleteEventAlert.setContentText("Produit déja supprimée!");

                succDeleteEventAlert.showAndWait();
            } else if (optionDeleteBookAlert.get() == ButtonType.CANCEL) {

            }

        } else {

          
            Alert selectBookAlert = new Alert(Alert.AlertType.WARNING);
            selectBookAlert.setTitle("Sélectionner une Produit");
            selectBookAlert.setHeaderText(null);
            selectBookAlert.setContentText("D'abord il faut Sélectionner un Produit!");
            selectBookAlert.showAndWait();
           

        } 
        
    }

    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
         Produit c = table.getSelectionModel().getSelectedItem();
    idprod.setText(String.valueOf(c.getId()));
        Nom.setText(String.valueOf(c.getNom()));
        desc.setText(String.valueOf(c.getDescription()));
        //image.setText(String.valueOf(c.getImage()));
        prix.setText(String.valueOf(c.getPrix()));
        
        
       bsave.setDisable(true);
    }


    @FXML
    private void Rechercher(ActionEvent event) {
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Produit> filteredData = new FilteredList<>(dataa, p -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		tfrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Produit -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Produit.getNom().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (Produit.getDescription().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Produit> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
        //
        
        
        
    }

    @FXML
    private void afficherr(MouseEvent event) {
    }
    
    

    @FXML
    private void pdf(ActionEvent event) throws DocumentException, FileNotFoundException, com.lowagie.text.DocumentException {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("vous voulez creer une pdf qui contient la liste des produits ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
             pdfequipement pdfeq= new pdfequipement();
        pdfeq.liste_equipement();
        }
    }

    private void changeScreenCateg(ActionEvent event) {
        try {
            Parent tableViewParent=FXMLLoader.load(getClass().getResource("/controllerProgramme/CategorieProduit.fxml"));
            Scene tableViewScene= new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void changeScreenProduit(ActionEvent event) {
        try {
            Parent tableViewParent=FXMLLoader.load(getClass().getResource("/controllerProgramme/Produit.fxml"));
            Scene tableViewScene= new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void upload(ActionEvent event) {
        byte[] reclamation_image = null;
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try {                                    
            BufferedImage bufferedImage = ImageIO.read(file);
            
            filename = file.getName();
            String path = "C:\\Users\\sonicmaster\\Desktop\\ESSAIFX\\Final maa jme3aa\\AdomifitDesktop\\src\\images\\" + filename;
             File outputfile = new File(path);
                //save  
            ImageIO.write(bufferedImage,"png" , outputfile);
            
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imaagee.setImage(image);
            imaagee.setFitWidth(200);
            imaagee.setFitHeight(200);
            imaagee.scaleXProperty();
            imaagee.scaleYProperty();
            imaagee.setSmooth(true);
            imaagee.setCache(true);
            FileInputStream fin = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];

            for (int readNum; (readNum = fin.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            reclamation_image = bos.toByteArray();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void initialize(MouseEvent event) {
    }


    @FXML
    private void commandes(ActionEvent event) {
    }

    @FXML
    private void maison(ActionEvent event) {
    }

    @FXML
    private void repas(ActionEvent event) {
    }

    @FXML
    private void resrepas(ActionEvent event) {
    }

    @FXML
    private void reclamation(ActionEvent event) {
    }

    @FXML
    private void user(ActionEvent event) {
    }

    @FXML
    private void reservation(ActionEvent event) {
    }

    @FXML
    private void resmaison(ActionEvent event) {
    }

    @FXML
    private void evenement(ActionEvent event) {
    }

    @FXML
    private void Programme(ActionEvent event) {
        
         try {
            Parent tableViewParent=FXMLLoader.load(getClass().getResource("Front.fxml"));
            Scene tableViewScene= new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void categorieProduit(ActionEvent event) {
         try {
            Parent tableViewParent=FXMLLoader.load(getClass().getResource("CategorieProduit.fxml"));
            Scene tableViewScene= new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    
    
    
    

