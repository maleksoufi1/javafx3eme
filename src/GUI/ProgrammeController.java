/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import static entites.Programme.filename;
import static entites.Programme.pathfile;

import entites.Programme;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
import services.CategorieProduitService;
import services.AjoutMailService;
import services.CategorieService;
import services.ModifMailService;
import services.ProgrammeService;
import services.SupprMailService;

/**
 * FXML Controller class
 *
 * @author sonicmaster
 */
public class ProgrammeController implements Initializable {
     public String imagecomp; 
               Integer idact; 
               int idresactivites;

    @FXML
    private TableView<Programme> affichageActivites;
    @FXML
    private TableColumn<Programme, String> nomactcol;
    @FXML
    private TableColumn<Programme, String> descactcol;
    @FXML
    private TableColumn<Programme, Integer> categorieactcol;
    @FXML
    private TableColumn<Programme,String> img;
    @FXML
    private ImageView imagefield;
    @FXML
    private TextField rechercheTF;
    
    @FXML
    private TextField titreTF;
    @FXML
    private Button uploadTF;
    @FXML
    private TextField descriptionTF;
    @FXML
    private Button ajouterTF;
    @FXML
    private Button modifierTF;
   
    @FXML
    private Button SupprimerTF;
    @FXML
    private Button LISTECATEGORIES;
    @FXML
    private TableColumn<?, ?> likeactol;
    @FXML
    private TableColumn<?, ?> dislikeactol;
    @FXML
    private TableColumn<?, ?> rateactol;
     private List<String> cat = new ArrayList<>();

    @FXML
    private ChoiceBox<String> boxCat;
    @FXML
    private Button frontTF1;
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
        
              CategorieService c = new CategorieService();
        cat = c.AllCatNames();
        boxCat.getItems().addAll(cat);
            
        // TODO
        
        ProgrammeService as = new ProgrammeService();
        
        
        
       
              affichageActivites.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                idact = as.liste2()
                        .get(affichageActivites.getSelectionModel().getSelectedIndex())
                        .getId();
                //System.out.println(iddd);
                titreTF.setText(as.liste2()
                        .get(affichageActivites.getSelectionModel().getSelectedIndex())
                        .getTitre());
                
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
     
        
                     
         
     
         
        try {ObservableList<Programme>list = (ObservableList<Programme>) as.liste2();
            
           
           
            nomactcol.setCellValueFactory(new PropertyValueFactory<Programme,String>("titre"));
            descactcol.setCellValueFactory(new PropertyValueFactory<Programme,String>("description"));
            categorieactcol.setCellValueFactory(new PropertyValueFactory<Programme,Integer>("categorie_programme_id"));
            img.setCellValueFactory(new PropertyValueFactory<>("photo"));//image
             rateactol.setCellValueFactory(new PropertyValueFactory<>("rate"));
             likeactol.setCellValueFactory(new PropertyValueFactory<>("like"));
             dislikeactol.setCellValueFactory(new PropertyValueFactory<>("dislike"));
             



           
           affichageActivites.setItems(list);

            
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(ProgrammeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }    


    private void commandes(ActionEvent event) {try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("CategorieProduitController.fxml"));
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

    private void maison(ActionEvent event) {
        try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("Produit.fxml"));
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


   @FXML//rechcerche
    private void on_on(KeyEvent event) {
        ProgrammeService as = new ProgrammeService();
           
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

            Programme.filename=  filename; //nom de l'image
            
            System.out.println("Programme.filename");
                        System.out.println(Programme.filename);
System.out.println("Programme.filename");
                       
        }
        
       
        Programme.pathfile = fc.getAbsolutePath();//path complet de l'image
                System.out.println("Programme.pathfile");

        System.out.println(Programme.pathfile);
    }


     @FXML
    private void SupprimerActivite(ActionEvent event) {
                Programme a = affichageActivites.getSelectionModel().getSelectedItem();
                
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous vraiment supprimer cette activité  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            ProgrammeService as = new ProgrammeService();
            try {
                as.deleteProduit(a.getId());
               
                
                 Image aa = new Image("/Images/images.png");
        Notifications not = Notifications.create()
        .title("supprimé")
        .text("programme supprimé avec success")
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
                 try {
             services.SupprMailService.sendMail();
         } catch (Exception ex) {
             Logger.getLogger(ProgrammeController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        
        
        CategorieService cp = new CategorieService();
         String catg = boxCat.getValue();
         int idcat = cp.rechercheParCat(catg);
     //   *****************************************controle de saisie***********************************************************
            if (titreTF.getText()==""||titreTF.getText()==null)
            {
                        Alert alertx = new Alert(Alert.AlertType.WARNING);
                        alertx.show();
                        alertx.setTitle("ya weldi !");
                        alertx.setContentText("remplissage de titre obligatoire");}
                        
 if (boxCat.getValue()==null){
 System.out.println("eroooooooooooooor");
                        Alert alerty = new Alert(Alert.AlertType.WARNING);
                        alerty.show();
                        alerty.setTitle("ya weldi !");
                        alerty.setContentText("inserer la categorie auquelle appartient ce programme !");   }  
                        
                       
 if (descriptionTF.getText()==""||descriptionTF.getText()==null){
 System.out.println("eroooooooooooooor");
                        Alert alertz = new Alert(Alert.AlertType.WARNING);
                        alertz.show();
                        alertz.setTitle("ya weldi !");
                        alertz.setContentText("remplissage de description obligatoire");   }    
        
 if (Programme.filename==""||Programme.filename==null){
 System.out.println("eroooooooooooooor");
                        Alert alerta = new Alert(Alert.AlertType.WARNING);
                        alerta.show();
                        alerta.setTitle("ya weldi !");
                        alerta.setContentText("inserer une photo "); }
   //   *****************************************controle de saisie***********************************************************

                                
                        Programme a = new Programme(titreTF.getText(),descriptionTF.getText(),idcat,Programme.filename);//Programme.filename
              
       ProgrammeService as = new ProgrammeService();
        if(a.getImage()==""||a.getDescription().isEmpty()||a.getTitre().isEmpty())
        {  Alert alertb = new Alert(Alert.AlertType.WARNING);
                        alertb.show();
                        alertb.setTitle("ya weldi !");
                        alertb.setContentText("verifier vos donné ");
        }
        
        else{
            try {
        as.insert(a);
        Programme.filename="";
        
        
          try {
                    Image aa = new Image("/Images/images.png");
        Notifications not = Notifications.create()
        .title("Ajouté")
        .text("Programme Ajouté")
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
                
                 try {
             services.AjoutMailService.sendMail();
         } catch (Exception ex) {
             Logger.getLogger(ProgrammeController.class.getName()).log(Level.SEVERE, null, ex);
         }
              

              // affichageActivites.refresh();
              

              // affichageActivites.refresh();
            } catch (Exception ee) {
                System.out.println("errrooooor");
                System.out.println(ee);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Categorie INEXISTANTE !!!!");
                alert.show();
            }
            affichageActivites.setItems(as.liste2());
          
        
     }
        
        
    }
    

    @FXML
  private void ModifierActivite(ActionEvent event) throws SQLException {
          CategorieService cp = new CategorieService();
         String catg = boxCat.getValue();
            int idcat = cp.rechercheParCat(catg);
            
        Programme a = affichageActivites.getSelectionModel().getSelectedItem()
                ;
        
       
         
       
        a.setTitre(titreTF.getText());

        a.setDescription(descriptionTF.getText());
       // a.setCategori(activiteCategorie.getValue()); 
        a.setCategorie_programme_id(idcat);
        a.setTitre(titreTF.getText());
        //a.setDate(Date.valueOf(txtda.getValue().toString()));
        if( Programme.filename!="")
        a.setImage(Programme.filename);
        
        Programme.filename=a.getImage();
        System.out.println(Programme.filename);
        ProgrammeService as = new ProgrammeService();
         if(a.getImage()==""||a.getDescription().isEmpty()||a.getTitre().isEmpty())
        {  Alert alertb = new Alert(Alert.AlertType.WARNING);
                        alertb.show();
                        alertb.setTitle("ya weldi !");
                        alertb.setContentText("verifier vos donné ");
        }
        
        else{
        
          try {
                       
        as.update(a);
                  try {
                    Image aa = new Image("/Images/images.png");
        Notifications not = Notifications.create()
        .title("Ajouté")
        .text("Programme Modifier")
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
                        affichageActivites.refresh();
                        
                         try {
             services.ModifMailService.sendMail();
         } catch (Exception ex) {
             Logger.getLogger(ProgrammeController.class.getName()).log(Level.SEVERE, null, ex);
         }
                        

                    } catch (Exception e) {
                        System.out.println(e);
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.show();
                        alert.setTitle("fail !");
                        alert.setContentText("failed succesfully");

                    }
         }
       affichageActivites.setItems(as.liste2());
    
  Programme.filename="";
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
                   
            Parent parent = FXMLLoader.load(getClass().getResource("Front.fxml"));
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
    private void LISTECATEGORIES(ActionEvent event) {
         try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("Categorie.fxml"));
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
    private void initialize(MouseEvent event) {
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
