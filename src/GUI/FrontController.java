package GUI;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entites.Programme;

import static entites.Programme.filename;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.CategorieService;
import services.ProgrammeService;

/**
 * FXML Controller class
 *
 * @author sonicmaster
 */
public class FrontController implements Initializable {
 public String imagecomp; 
               Integer idact; 
               int idresactivites;
    @FXML
    private TableView<CategorieProgramme> affichageActivites;
    private TableColumn<CategorieProgramme, Integer> idactcol;
    @FXML
    private TableColumn<CategorieProgramme, String> nomactcol;
    @FXML
    private TableColumn<CategorieProgramme, String> descactcol;
    @FXML
    private TableColumn<CategorieProgramme, String> img;
    @FXML
    private TextField rechercheTF;
    private TextField libelleTF;
    private TextField descriptionTF;
    @FXML
    private Button frontTF;
    @FXML
    private ImageView imagefield;
    @FXML
    private TableColumn<CategorieProgramme,Button> actionactol;
    @FXML
    private Text titreTF;
    @FXML
    private Label RegimesButton;
    @FXML
    private Label EvenementsButton;
    @FXML
    private Label ProduitsButton;
    @FXML
    private Label ForumButton;
    
    

     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         
        // TODO
        
        CategorieService as = new CategorieService();
        
        
        
       
            
                
               
               affichageActivites.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Image iv = new Image(this.getClass().getResourceAsStream(as.liste2()
                       .get(affichageActivites.getSelectionModel().getSelectedIndex()).getImage()));
         
                
            imagefield.setImage(iv);
            
             titreTF.setText(as.liste2()
                        .get(affichageActivites.getSelectionModel().getSelectedIndex())
                        .getLibelle());
                
                
       
  
             Programme.cat=Integer.parseInt(String.valueOf(as.liste2()
                       .get(affichageActivites.getSelectionModel().getSelectedIndex()
                        ).getId()
                          ));
                ;
             
                       
                      
          
                       
         
     
         
        
        
                       
    }});    
               try {ObservableList<CategorieProgramme>list = (ObservableList<CategorieProgramme>) as.liste2();
            
           
           
            nomactcol.setCellValueFactory(new PropertyValueFactory<CategorieProgramme,String>("libelle"));
            descactcol.setCellValueFactory(new PropertyValueFactory<CategorieProgramme,String>("description"));
            actionactol.setCellValueFactory(new PropertyValueFactory<>("affiche"));//image
            img.setCellValueFactory(new PropertyValueFactory<CategorieProgramme,String>("photo"));//image

           
           affichageActivites.setItems(list);

            
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(ProgrammeController.class.getName()).log(Level.SEVERE, null, ex);
        }
                       
        
                       }

   @FXML//rechcerche
    private void on_on(KeyEvent event) {
       CategorieService as = new CategorieService();
           
                as.search(rechercheTF.getText());
        
                affichageActivites.setItems(as.search(rechercheTF.getText()));
               
            
            
            
    }
    

    private void uploadimg(ActionEvent event)throws FileNotFoundException, IOException {
                FileChooser f = new FileChooser();
        String img;
        


        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.png"));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            //System.out.println(fc.getName());
            img = fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(img);
            imagefield.setImage(i);
            imagecomp = fc.toString();
            System.out.println(imagecomp);
            int index = imagecomp.lastIndexOf('\\');
            
            if (index > 0) {
                
                filename = imagecomp.substring(index + 1);
            }

            CategorieProgramme.filename=  filename; //nom de l'image
            
            System.out.println(CategorieProgramme.filename);
                       
        }
        
       
        CategorieProgramme.pathfile = fc.getAbsolutePath();//path complet de l'image

        
    }


    private void SupprimerActivite(ActionEvent event) {
                CategorieProgramme a = affichageActivites.getSelectionModel().getSelectedItem();
                
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous vraiment supprimer cette activité  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            CategorieService as = new CategorieService();
            try {
                as.deleteProduit(a.getId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete");
                alert.setHeaderText(null);
                alert.setContentText(" Done!");
                alert.show();
               //affichageActivites.setItems((ObservableList<Programme>) as.getAll());
               affichageActivites.setItems(as.liste2());
                             // affichageActivites.setItems(as.getActivitesList());

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec");
                alert.setHeaderText(null);
                alert.setContentText("Delete Failed !");
            }

        } else {
            alert2.close();
        }
        
    }

    private void OnClickedPrint(ActionEvent event) {
         PrinterJob job = PrinterJob.createPrinterJob();
       
        Node root= this.affichageActivites;
        
     if(job != null){
     job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
     Printer printer = job.getPrinter();
     PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
     boolean success = job.printPage(pageLayout, root);
     if(success){
        job.endJob();
     }
   }
    }

    private void OnClickedStatistique(ActionEvent event) {
        try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("Piechart.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            //stage.getIcons().add(new Image("cc.png"));
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProgrammeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void LISTEPROGRAMMES(ActionEvent event) {
          try {
         Parent parent = FXMLLoader.load(getClass().getResource("Programme.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            //stage.getIcons().add(new Image("cc.png"));
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProgrammeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void back(ActionEvent event) {
         try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("Categorie.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            //stage.getIcons().add(new Image("cc.png"));
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    private void redirectRegime(MouseEvent event) {
        try {
            Parent tableViewParent=FXMLLoader.load(getClass().getResource("FrontRegime.fxml"));
            Scene tableViewScene= new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
        }
    }

    @FXML
    private void redirectEvenemnt(MouseEvent event) {
         try {
            Parent tableViewParent=FXMLLoader.load(getClass().getResource("FrontEvenementC.fxml"));
            Scene tableViewScene= new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
        }
    }

    @FXML
    private void redirectProduit(MouseEvent event) {
        
         try {
            Parent tableViewParent=FXMLLoader.load(getClass().getResource("Front_1.fxml"));
            Scene tableViewScene= new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
        }
    }

    @FXML
    private void redirectProgrammes(MouseEvent event) {
    }

    @FXML
    private void redirectForum(MouseEvent event) {
        try {
            Parent tableViewParent=FXMLLoader.load(getClass().getResource("MesReclamation.fxml"));
            Scene tableViewScene= new Scene(tableViewParent);
            Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        } catch (IOException ex) {
        }
    }
    }

    

