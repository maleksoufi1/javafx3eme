/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import entites.CategorieRegime;
import entites.Regime;
import java.awt.image.BufferedImage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.swing.ImageIcon;
import services.CategorieRegimeService;
import services.RegimeService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Kouki
 */
public class FormRegimeController implements Initializable {

  
    
    private int regimeId;
    private boolean update;
 
    @FXML
    private TextField typeTF;
    @FXML
    private TextArea descriptionTF;
    @FXML
    private TextField dificulteTF;
    @FXML
    private TextField imageTF;
    @FXML
    private ComboBox<CategorieRegime> CombolistCategorie;
    @FXML
    private Button btn_ajout;
    @FXML
    private TextField prixTF;
    @FXML
    private Label titreForm;
    @FXML
    private Button btn_cancel;
    @FXML
    private Button btnUpload;
    @FXML
    private ImageView Image;
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CategorieRegimeService cs = new CategorieRegimeService();
         List<CategorieRegime> listcat = cs.getAll()   ;
          
        ObservableList<CategorieRegime> List = FXCollections.observableArrayList(listcat);
            CombolistCategorie.setItems(List);
      
    }    

    @FXML
    private void save(MouseEvent event) {
    
         if(typeTF.getText().equals("") || descriptionTF.getText().equals("") || dificulteTF.getText().equals("")
                 ||imageTF.getText().equals("")|| prixTF.getText().equals("") ||CombolistCategorie.getValue() == null ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Verifier tout les champs ! ");
            alert.showAndWait();
            return;
        }
          float p = 0;
        try {
            
             p =  Float.parseFloat(prixTF.getText());
        } catch (NumberFormatException ex) {
            System.out.println("Number Format Exception");
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("le prix est un nombre ! ");
            alert.showAndWait();
            return;
       
        }
        //ajout
        if (update == false) {
         // session id user connecté  2
         Regime r = new Regime(typeTF.getText(), descriptionTF.getText(), dificulteTF.getText(), imageTF.getText(), p,CombolistCategorie.getValue().getId(), 2);
        RegimeService rs = new RegimeService();
        rs.addRegime(r);
             try {
                 Mail.sendMail("kuokihoussem@gmail.com", 1, "régime ajouté ");
             } catch (MessagingException ex) {
                 Logger.getLogger(FormRegimeController.class.getName()).log(Level.SEVERE, null, ex);
             }
                        String titles = "régime ajouté ";
                String msgs = "avec succées";
                TrayNotification trays = new TrayNotification();
                AnimationType types = AnimationType.SLIDE;
                trays.setAnimationType(types);
                trays.setTitle(titles);
                trays.setMessage(msgs);
                trays.showAndDismiss(Duration.seconds(5));
                trays.setNotificationType(NotificationType.SUCCESS);
        }
    //update
        else{
          
            Regime r = new Regime(regimeId,typeTF.getText(), descriptionTF.getText(), dificulteTF.getText(), imageTF.getText(), p,CombolistCategorie.getValue().getId(), 2);
             RegimeService regserv = new RegimeService();
             regserv.updateRegime(r);
                        String titles = "régime modifié ";
                String msgs = "avec succées";
                TrayNotification trays = new TrayNotification();
                AnimationType types = AnimationType.SLIDE;
                trays.setAnimationType(types);
                trays.setTitle(titles);
                trays.setMessage(msgs);
                trays.showAndDismiss(Duration.seconds(5));
                trays.setNotificationType(NotificationType.SUCCESS);
        }
        
        
        //redirect and refresh
        
          Stage stage = (Stage) typeTF.getScene().getWindow();
    // do what you have to do
    FXMLLoader loader = new FXMLLoader ();
                             loader.setLocation(getClass().getResource("MesRegimes.fxml"));
                             try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(CategorieRegimeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
        MesRegimesController controller = loader.getController();
        controller.displayTabRegime();
                          
                             Parent parent = loader.getRoot();
                            Stage staged = new Stage();
                            staged.setScene(new Scene(parent));
                            staged.initStyle(StageStyle.UTILITY);
                            staged.show(); 
    stage.close();
        
    }

    void setTextField(int id, String type, String description, String dificulte, CategorieRegime categorie, String image, float prix) {
        this.titreForm.setText("Modifier Régime");
        this.regimeId = id;
       this.typeTF.setText(type);
       this.descriptionTF.setText(description);
       this.dificulteTF.setText(dificulte);
       this.imageTF.setText(image);
       this.prixTF.setText(String.valueOf(prix));
       this.CombolistCategorie.setValue(categorie);
    }

    void setUpdate(boolean b) {
         this.update = b;
    }

    @FXML
    private void closeDialog(MouseEvent event) {
         Stage stage = (Stage) btn_cancel.getScene().getWindow();
    // do what you have to do
    stage.close();
    }


      @FXML
    private void uploadImage(MouseEvent event) throws FileNotFoundException, IOException {
        
      Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\xampp\\htdocs\\adomifitt\\public\\uploads\\images\\"  + x + ".jpg";
   
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
           
            String path=file.getAbsolutePath();
            javafx.scene.image.Image img = new javafx.scene.image.Image(file.toURI().toString());
            Image.setImage(img);
            imageTF.setText(x + ".jpg");
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();
            
        } else {
            System.out.println("error");

        }
    }
    
    
    
    
    
    
//    
//    private void openNewImageWindow(File file){
//        Stage secondStage = new Stage();
//        
//        MenuBar menuBar = new MenuBar();
//        Menu menuFile = new Menu("File");
//        MenuItem menuItem_Save = new MenuItem("Save Image");
//        menuFile.getItems().addAll(menuItem_Save);
//        menuBar.getMenus().addAll(menuFile);
//        
//        Label name = new Label(file.getAbsolutePath());
//        Image image = new Image(file.toURI().toString());
//        ImageView imageView = new ImageView();
//        
//        menuItem_Save.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                FileChooser fileChooser = new FileChooser();
//                fileChooser.setTitle("Save Image");
//                
//                File file = fileChooser.showSaveDialog(secondStage);
//                if (file != null) {
//                    try {
//                        ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(),
//                                null), "png", file);
//                    } catch (IOException ex) {
//                        Logger.getLogger(
//                            FormRegimeController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
//        });
//
//        final VBox vbox = new VBox();
//        vbox.setAlignment(Pos.CENTER);
//        vbox.setSpacing(10);
//        vbox.setPadding(new Insets(0, 10, 0, 10));
//        vbox.getChildren().addAll(name, imageView);
//        
//        imageView.setFitHeight(400);
//        imageView.setPreserveRatio(true);
//        imageView.setImage(image);
//        imageView.setSmooth(true);
//        imageView.setCache(true);
//        
//        Scene scene = new Scene(new VBox(), 400, 350);
//        ((VBox)scene.getRoot()).getChildren().addAll(menuBar, vbox);
//        
//        secondStage.setTitle(file.getName());
//        secondStage.setScene(scene);
//        secondStage.show();
//    }
//    
    
}
