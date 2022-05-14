package GUI;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.CategorieService;
import services.ProgrammeService;

/**
 * FXML Controller class
 *
 * @author sonicmaster
 */
public class CategorieController implements Initializable {
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
    @FXML
    private TextField libelleTF;
    @FXML
    private Button uploadTF;
    @FXML
    private TextField descriptionTF;
    @FXML
    private Button ajouterTF;
    @FXML
    private Button modifierTF;
    @FXML
    private Button frontTF;
    @FXML
    private Button SupprimerTF;
    @FXML
    private ImageView imagefield;
    @FXML
    private Button LISTEPROGRAMMES;
    @FXML
    private Button btn_Statistique;
    @FXML
    private Button btn_ListSuivi;

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
              
                //System.out.println(iddd);
                libelleTF.setText(as.liste2()
                        .get(affichageActivites.getSelectionModel().getSelectedIndex())
                        .getLibelle());
                
                descriptionTF.setText(as.liste2()
                        .get(affichageActivites.getSelectionModel().getSelectedIndex())
                        .getDescription());
                
               
               
  
             
             
                Image iv = new Image(this.getClass().getResourceAsStream(as.liste2()
                       .get(affichageActivites.getSelectionModel().getSelectedIndex()).getImage()));
         
                
            imagefield.setImage(iv);
                      
          
            
               
              //  txtp.setText(String.valueOf(as.getAll()
                      //  .get(affichageActivites.getSelectionModel().getSelectedIndex()
                      //  ).getPrix()
                     //     )
               // );
               
                };
          
              
         }); 
     
        
                     
         
     
         
        try {ObservableList<CategorieProgramme>list = (ObservableList<CategorieProgramme>) as.liste2();
            
           
           
           // rateactol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomactcol.setCellValueFactory(new PropertyValueFactory<CategorieProgramme,String>("libelle"));
            descactcol.setCellValueFactory(new PropertyValueFactory<CategorieProgramme,String>("description"));
            img.setCellValueFactory(new PropertyValueFactory<>("photo"));//image
           
           
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
    

    @FXML
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
            //Image aa = new Image("ee.jpg");
        Notifications not = Notifications.create()
        .title("uploaded")
        .text("image uploaded successfully")
      .graphic(new ImageView(i))
        .hideAfter(Duration.seconds(5))
         .position(Pos.TOP_RIGHT)
        .onAction(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
       
    });
        
        not.darkStyle();
        Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                                not.show();

                    }
                });
            
            if (index > 0) {
                
                filename = imagecomp.substring(index + 1);
            }

            CategorieProgramme.filename=  filename; //nom de l'image
            
            System.out.println(CategorieProgramme.filename);
                       
        }
        
       
        CategorieProgramme.pathfile = fc.getAbsolutePath();//path complet de l'image

      
    
    
    }


     @FXML
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
                   try {
                    Image aa = new Image("/GUI/images.png");
        Notifications not = Notifications.create()
        .title("supprimé !")
        .text("Categorie Supprimé avec succes !")
      .graphic(new ImageView(aa))
        .hideAfter(Duration.seconds(5))
         .position(Pos.TOP_RIGHT)
                
        .onAction(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
       
    });
        
        not.darkStyle();
        Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                                not.show();

                    }
                });
          }catch (Exception ex) {
             Logger.getLogger(ProgrammeController.class.getName()).log(Level.SEVERE, null, ex);
              Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("image INEXISTANTE !!!!");
                alert.show();
         }
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

     @FXML
    private void AjouterActivite(ActionEvent event) throws SQLException {
        
        
        
        
     //   *****************************************controle de saisie***********************************************************
            if (libelleTF.getText()==""||libelleTF.getText()==null)
            {
                        Alert alertx = new Alert(Alert.AlertType.WARNING);
                        alertx.show();
                        alertx.setTitle("ya weldi !");
                        alertx.setContentText("remplissage de titre obligatoire");
          
    }
                        

                       
 if (descriptionTF.getText()==""||descriptionTF.getText()==null){
 System.out.println("eroooooooooooooor");
                        Alert alertz = new Alert(Alert.AlertType.WARNING);
                        alertz.show();
                        alertz.setTitle("ya weldi !");
                        alertz.setContentText("remplissage de description obligatoire");   }    
        
 if (CategorieProgramme.filename==""||CategorieProgramme.filename==null){
 System.out.println("eroooooooooooooor");
                        Alert alerta = new Alert(Alert.AlertType.WARNING);
                        alerta.show();
                        alerta.setTitle("ya weldi !");
                        alerta.setContentText("inserer une photo "); }
   //   *****************************************controle de saisie***********************************************************

                               
                        CategorieProgramme a = new CategorieProgramme(libelleTF.getText(),descriptionTF.getText(),CategorieProgramme.filename);//Programme.filename
              
      CategorieService as = new CategorieService();
        if(a.getImage()==""||a.getDescription().isEmpty()||a.getLibelle().isEmpty())
        {  Alert alertb = new Alert(Alert.AlertType.WARNING);
                        alertb.show();
                        alertb.setTitle("ya weldi !");
                        alertb.setContentText("verifier vos donné ");
        }
        
        else{
        as.insert(a);
        
        CategorieProgramme.filename="";
        
        
            try {
                    Image aa = new Image("/images/images.png");
        Notifications not = Notifications.create()
        .title("Ajouté")
        .text("Categorie Ajouté")
      .graphic(new ImageView(aa))
        .hideAfter(Duration.seconds(5))
         .position(Pos.TOP_RIGHT)
        .onAction(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
       
    });
        
        not.darkStyle();
        Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                                not.show();

                    }
                });
          }catch (Exception ex) {
             Logger.getLogger(ProgrammeController.class.getName()).log(Level.SEVERE, null, ex);
              Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("image INEXISTANTE !!!!");
                alert.show();
         }
            affichageActivites.setItems(as.liste2());
          
        
     }
        
    }
    

    @FXML
  private void ModifierActivite(ActionEvent event) throws SQLException {
        
        CategorieProgramme a = affichageActivites.getSelectionModel().getSelectedItem()
                ;
        
        
         
       
        a.setLibelle(libelleTF.getText());

        a.setDescription(descriptionTF.getText());
       // a.setCategori(activiteCategorie.getValue()); 
        //a.setDate(Date.valueOf(txtda.getValue().toString()));
        if( CategorieProgramme.filename!="")
        a.setImage(CategorieProgramme.filename);
        
        CategorieProgramme.filename=a.getImage();
        CategorieService as = new CategorieService();
        
        
         
                    if(a.getImage()==""||a.getDescription().isEmpty()||a.getLibelle().isEmpty())
        {  Alert alertb = new Alert(Alert.AlertType.WARNING);
                        alertb.show();
                        alertb.setTitle("ya weldi !");
                        alertb.setContentText("verifier vos donné ");
        }
        
        else{   
        as.update(a);
            try {
                    Image aa = new Image("/GUI/images.png");
        Notifications not = Notifications.create()
        .title("Modifier!!")
        .text("Categorie modifiée avec succés ")
      .graphic(new ImageView(aa))
        .hideAfter(Duration.seconds(5))
         .position(Pos.TOP_RIGHT)
        .onAction(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
       
    });
        
        not.darkStyle();
        Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                                not.show();

                    }
                });
          }catch (Exception ex) {
             Logger.getLogger(ProgrammeController.class.getName()).log(Level.SEVERE, null, ex);
              Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("image INEXISTANTE !!!!");
                alert.show();
         }
          }
       affichageActivites.setItems(as.liste2());
    
  CategorieProgramme.filename="";
  }

    @FXML
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

    @FXML
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

    @FXML
    private void front(ActionEvent event) {
         try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/Front.fxml"));
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
    private void LISTEPROGRAMMES(ActionEvent event) {
          try {
         Parent parent = FXMLLoader.load(getClass().getResource("/GUI/Programme.fxml"));
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

    private void Produit(ActionEvent event) {
        try {
         Parent parent = FXMLLoader.load(getClass().getResource("Front_1.fxml"));
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
    private void showListCtegorieRegime(MouseEvent event) {
    }

    @FXML
    private void showMesRegimes(MouseEvent event) {
    }

    @FXML
    private void showStatistique(MouseEvent event) {
    }

    @FXML
    private void showListeSuivis(MouseEvent event) {
    }
    
    }

    

