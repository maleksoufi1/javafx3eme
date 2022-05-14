package GUI;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import static entites.Programme.filename;
import static entites.Programme.pathfile;
import javafx.geometry.Insets;

import entites.Programme;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.AjoutMailService;
import services.ModifMailService;
import services.ProgrammeService;
import services.SupprMailService;

/**
 * FXML Controller class
 *
 * @author sonicmaster
 */
public class ProgrammeByCatController implements Initializable {
     public String imagecomp; 
               Integer idact; 
               int idresactivites;
               int idP ;

    @FXML
    private Button btn_boutique;
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
    private Label titreTF;
    private TextField descriptionTF;
    @FXML
    private Button imprimerTF;
    @FXML
    private Button statistiqueTF;
    @FXML
    private Button frontTF;
    private ComboBox<String> catTF;
     ObservableList<String> options = FXCollections.observableArrayList(
                "1",
                "2",
                "3",
                "4"
               
        );
    @FXML
    private Button LISTECATEGORIES;
    @FXML
    private TableColumn<?, ?> rateactol;
    @FXML
    private Button likerTF;
    @FXML
    private Button dislikerTF;
    @FXML
    private Button qrcodeTF;
   

          
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // TODO
        
        // TODO
        
        ProgrammeService as = new ProgrammeService();
        
        
        
       
              affichageActivites.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            
             
                Image iv = new Image(this.getClass().getResourceAsStream(as.ListeByCat(Programme.cat)
                       .get(affichageActivites.getSelectionModel().getSelectedIndex()).getImage()));
         
                
            imagefield.setImage(iv);
                      
              titreTF.setText(as.ListeByCat(Programme.cat)
                        .get(affichageActivites.getSelectionModel().getSelectedIndex())
                        .getTitre());
          
            
               
              //  txtp.setText(String.valueOf(as.getAll()
                      //  .get(affichageActivites.getSelectionModel().getSelectedIndex()
                      //  ).getPrix()
                     //     )
               // );
               
                };
          
              
         }); 
     
        
       
        
        
        
       
     
         try {ObservableList<Programme>list = (ObservableList<Programme>) as.ListeByCat(Programme.cat);
        
             nomactcol.setCellValueFactory(new PropertyValueFactory<Programme,String>("titre"));
             descactcol.setCellValueFactory(new PropertyValueFactory<Programme,String>("description"));
             categorieactcol.setCellValueFactory(new PropertyValueFactory<Programme,Integer>("categorie_programme_id"));
             img.setCellValueFactory(new PropertyValueFactory<>("photo"));//image
             rateactol.setCellValueFactory(new PropertyValueFactory<>("rate"));
            
           

             
             affichageActivites.setItems(list);

            
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(ProgrammeController.class.getName()).log(Level.SEVERE, null, ex);
        }
               
                     
         
     
         
       
        
        
        
        
    }    

    @FXML
    private void boutique(ActionEvent event) {
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

    
    
   @FXML//rechcerche
    private void on_on(KeyEvent event) {
        ProgrammeService as = new ProgrammeService();
           
                as.searchFront(rechercheTF.getText());
        
                affichageActivites.setItems(as.searchFront(rechercheTF.getText()));
               
            
            
            
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

            Programme.filename=  filename; //nom de l'image
            
            System.out.println("Programme.filename");
                        System.out.println(Programme.filename);
System.out.println("Programme.filename");
                       
        }
        
       
        Programme.pathfile = fc.getAbsolutePath();//path complet de l'image
                System.out.println("Programme.pathfile");

        System.out.println(Programme.pathfile);
    }


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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete");
                alert.setHeaderText(null);
                alert.setContentText(" Done!");
                alert.show();
                 try {
             SupprMailService.sendMail();
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

    private void AjouterActivite(ActionEvent event) throws SQLException {
        
        
        
        
     //   *****************************************controle de saisie***********************************************************
            if (titreTF.getText()==""||titreTF.getText()==null)
            {
                        Alert alertx = new Alert(Alert.AlertType.WARNING);
                        alertx.show();
                        alertx.setTitle("ya weldi !");
                        alertx.setContentText("remplissage de titre obligatoire");}
                        
 if (catTF.getValue()==null||catTF.getValue()==null){
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

                                
                        Programme a = new Programme(titreTF.getText(),descriptionTF.getText(),Integer.parseInt(catTF.getValue().toString()),Programme.filename);//Programme.filename
              
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
        
        
         
              
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Done");
                alert.setContentText("Ajouté!");
                
                alert.show();
                
                 try {
             AjoutMailService.sendMail();
         } catch (Exception ex) {
             Logger.getLogger(ProgrammeController.class.getName()).log(Level.SEVERE, null, ex);
         }
              

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
    

  private void ModifierActivite(ActionEvent event) throws SQLException {
        
        Programme a = affichageActivites.getSelectionModel().getSelectedItem()
                ;
        
        String p = catTF.getValue().toString();
         int cat_id = Integer.parseInt(p);
         
       
        a.setTitre(titreTF.getText());

        a.setDescription(descriptionTF.getText());
       // a.setCategori(activiteCategorie.getValue()); 
        a.setCategorie_programme_id(Integer.parseInt(catTF.getValue()));
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
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.show();
                        alert.setTitle("updated !");
                        alert.setContentText("updated succesfully");
                        affichageActivites.refresh();
                        
                         try {
             ModifMailService.sendMail();
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
    private void OnClickedLike(ActionEvent event) {
                Programme a = affichageActivites.getSelectionModel().getSelectedItem();

              int id = a.getId() ;
              

         ProgrammeService as = new ProgrammeService();
         
         try {
               as.Like(id);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Like !!!");
                alert.setHeaderText(null);
                alert.setContentText(" Done!");
                alert.show();
                 
               //affichageActivites.setItems((ObservableList<Programme>) as.getAll());
               affichageActivites.setItems(as.ListeByCat(Programme.cat));
                             // affichageActivites.setItems(as.getActivitesList());

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec");
                alert.setHeaderText(null);
                alert.setContentText("like Failed !");
            }

         
        
                                 affichageActivites.refresh();
    }
    
    @FXML
    private void OnClickedDislike(ActionEvent event) {
        Programme a = affichageActivites.getSelectionModel().getSelectedItem();
              int id = a.getId() ;
          ProgrammeService as = new ProgrammeService();
        try {
               as.Dislike(id);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dislike !!!");
                alert.setHeaderText(null);
                alert.setContentText(" Done!");
                alert.show();
                 
               //affichageActivites.setItems((ObservableList<Programme>) as.getAll());
               affichageActivites.setItems(as.ListeByCat(Programme.cat));
                             // affichageActivites.setItems(as.getActivitesList());

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec");
                alert.setHeaderText(null);
                alert.setContentText("like Failed !");
            }

         
        
                                 affichageActivites.refresh();
    }

    @FXML
    private void OnClickedStatistique2(ActionEvent event) {
        try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("Piechart2.fxml"));
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
    private void QRCODE(ActionEvent event)
    {
     
//
        Programme p = affichageActivites.getSelectionModel().getSelectedItem();
              int id = p.getId() ;
       // affichageActivites.setOnMouseClicked((Event e) -> {

                    try {
                        QRCodeWriter qrCodeWriter = new QRCodeWriter();
                        String myWeb = "http://127.0.0.1:8000/programme_show/"+id;
                        String a = "";
                        int width = 300;
                        int height = 300;
                        String fileType = "png";

                        BufferedImage bufferedImage = null;

                        BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
                        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                        bufferedImage.createGraphics();

                        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
                        graphics.setColor(java.awt.Color.WHITE);
                        graphics.fillRect(0, 0, width, height);
                        graphics.setColor(java.awt.Color.BLACK);

                        for (int ii = 0; ii < height; ii++) {
                            for (int j = 0; j < width; j++) {
                                if (byteMatrix.get(ii, j)) {
                                    graphics.fillRect(ii, j, 1, 1);
                                }
                            }
                        }
                        ImageView qrView = new ImageView();
                        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
                        System.out.println("Success...");
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("QR CODE");

                        alert.initModality(Modality.APPLICATION_MODAL);
                        // alert.initOwner(stage);
                        alert.getDialogPane().setContentText("pour plus de détails scanner ce code et visiter notre site web");

                        DialogPane dialogPane = alert.getDialogPane();
                        GridPane grid = new GridPane();
                        ColumnConstraints graphicColumn = new ColumnConstraints();
                        graphicColumn.setFillWidth(false);
                        graphicColumn.setHgrow(Priority.NEVER);
                        ColumnConstraints textColumn = new ColumnConstraints();
                        textColumn.setFillWidth(true);
                        textColumn.setHgrow(Priority.ALWAYS);
                        grid.getColumnConstraints().setAll(graphicColumn, textColumn);
                        grid.setPadding(new Insets(5));

                        ImageView imageView = new ImageView();
                        imageView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
                        imageView.setFitWidth(350);
                        imageView.setFitHeight(350);
                        StackPane stackPane = new StackPane(imageView);
                        stackPane.setAlignment(Pos.CENTER);
                        grid.add(stackPane, 0, 0);

                        dialogPane.setHeader(grid);
                        dialogPane.setGraphic(null);

                        alert.showAndWait()
                                .filter(response -> response == ButtonType.OK)
                                .ifPresent(response -> System.out.println("The alert was approved"));

                    } catch (WriterException ex) {
                        Logger.getLogger(ProgrammeByCatController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                };
    @FXML
    private void ScannerQRCODE(ActionEvent event)
    {
     

           String cmd= "C:\\Program Files (x86)\\CodeTwo\\QR Code Desktop Reader & Generator\\CodeTwo";
try {
Runtime r = Runtime.getRuntime();
Process p = r.exec(cmd);
p.waitFor();//si l'application doit attendre a ce que ce process fini
}catch(Exception e) {
System.out.println("erreur d'execution " + cmd + e.toString());
}
        
                };
    
    
}

