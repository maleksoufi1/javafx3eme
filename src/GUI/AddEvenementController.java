/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

//import com.github.plushaze.traynotification.notification.TrayNotification;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entites.CategorieEvenement;
import entites.CategorieRegime;
import entites.Evenement;
import entites.Regime;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.CategorieEvenementService;
import services.CategorieRegimeService;
import services.EvenementService;
import services.RegimeService;
import utils.DataSource;
import java.security.*;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import tray.notification.*;


/**
 * FXML Controller class
 *
 * @author myria
 */
public class AddEvenementController implements Initializable {
    ImageView imgView = new ImageView();
    private int evenementId;
    private boolean update;
    private FileChooser fileChooser;
    Image imgs;
     BufferedImage bf;
      ImageView imgv;

    @FXML
    private JFXTextField TitreF;
    @FXML
    private JFXTextField DescritionF;
    @FXML
    private JFXDatePicker HorraireF;
    @FXML
    private JFXTextField LongitudeF;
    @FXML
    private HBox LatitudeF;
    @FXML
    private HBox ImageF;
    @FXML
    private JFXButton btnUpload;
    @FXML
    private ComboBox<CategorieEvenement> CombolistCategorie;
    @FXML
    private JFXTextField LaltitudeTF;
    @FXML
    private JFXTextField ImageTF;
    @FXML
    private JFXButton btnValider;
    @FXML
    private JFXButton btnAnnuler;
    @FXML
    private Text titreForm;
    String query = null;
    
    
    String myAccountEmail = "myriambrahmi23@gmail.com";
    String password = "Mimo1901";
    
    int EvId; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CategorieEvenementService cs = new CategorieEvenementService();
         List<CategorieEvenement> listcat = cs.getAll()   ;
          
        ObservableList<CategorieEvenement> List = FXCollections.observableArrayList(listcat);
            CombolistCategorie.setItems(List);
        
          fileChooser= new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }    

    @FXML
    private void uploadImage(MouseEvent event) {
        File file= fileChooser.showOpenDialog(null);
        try {
            String url=file.toURI().toURL().toString();
             System.out.println("file : "+url);
             ImageTF.setText(file.getAbsolutePath());
        } catch (MalformedURLException ex) {
            Logger.getLogger(AddEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @FXML
//    private void valider(MouseEvent event) throws Exception {
//        if(TitreF.getText().equals("") || DescritionF.getText().equals("") 
//                 ||ImageTF.getText().equals("")|| LongitudeF.getText().equals("") || LaltitudeTF.getText().equals("")||CombolistCategorie.getValue() == null ){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("Verifier tout les champs ! ");
//            alert.showAndWait();
//            
//            return;
//        }else{
//            
//            getQuery();
//            insert();
//            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//            stage.close();
//            annuler( event);
//            sendMail("myriam.brahmi@esprit.tn");
//           
//        }

//        int l = 0;
//        try {
//             l = Integer.parseInt(LaltitudeTF.getText());
//        } catch (NumberFormatException ex) {
//            System.out.println("Number Format Exception");
//             Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("l'altitude est un nombre ! ");
//            alert.showAndWait();
//            return;
//       
//        }
        //ajout
//        if (update == false) {
//         // session id user connecté  2
//         Evenement r;
//            r = new Evenement(TitreF.getText(), DescritionF.getText(),ImageTF.getText(),p,l,CombolistCategorie.getValue().getId());
//        EvenementService rs = new EvenementService();
//        rs.addEvenement(r);
//         
//        
//        }
//    //update
//        else{
//          
//            Evenement r;
//            r = new Evenement(TitreF.getText(), DescritionF.getText(), ImageTF.getText(),p,l,CombolistCategorie.getValue().getId());
//         EvenementService regserv = new EvenementService();
//        regserv.updateEvenement(r);
//        
//        }
//        
//        
//        //redirect and refresh
//        
//          Stage stage = (Stage) TitreF.getScene().getWindow();
//    // do what you have to do
//    FXMLLoader loader = new FXMLLoader ();
//                             loader.setLocation(getClass().getResource("Evenement.fxml"));
//                             try {
//                                loader.load();
//                            } catch (IOException ex) {
//                                Logger.getLogger(CategorieEvenementController.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//        EvenementController controller = loader.getController();
//        controller.displayTabEvenement();
//                          
//                             Parent parent = loader.getRoot();
//                            Stage staged = new Stage();
//                            staged.setScene(new Scene(parent));
//                            staged.initStyle(StageStyle.UTILITY);
//                            staged.show(); 
//    stage.close();
//    }

    @FXML
    private void annuler(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    void setUpdate(boolean b) {
        this.update = b;
        }

    void setTextField(int id, String titre, String description, CategorieEvenement categorie, String image,LocalDate toLocalDate ,float longitude, float latitude) {
         this.titreForm.setText("Modifier Evenement");
        this.evenementId = id;
       this.TitreF.setText(titre);
       this.DescritionF.setText(description);
       this.HorraireF.setValue(toLocalDate);
       this.ImageTF.setText(image);
       this.LongitudeF.setText(String.valueOf(longitude));
       this.LaltitudeTF.setText(String.valueOf(latitude));
       this.CombolistCategorie.setValue(categorie);}

    private void getQuery() {
       if (update == false) {
            
             query = "INSERT INTO evenement (`titre`,`description`,`image`,`longitude`,`latitude`,`horraire`,`categorie_evenement_id`) VALUES (?,?,?,?,?,?,?) ;";

        }else{
            query = "UPDATE `evenement` SET "
                    + "`titre`=?,"
                    + "`description`=?,"
                    + "`image`=?,"
                     + "`longitude`=?,"
                    + "`latitude`=?,"
                    + "`horraire`=?,"
                    + "`categorie_evenement_id`=? WHERE id = '"+EvId+"'";
        }}

    private void insert() {
        try {
        PreparedStatement st =DataSource.getInstance().getConn().prepareStatement(query);
            st.setString(1, TitreF.getText());
            st.setString(2,DescritionF.getText()); 
            st.setString(3, ImageTF.getText());
             st.setString(4, LongitudeF.getText());
              st.setString(5, LaltitudeTF.getText());
              st.setString(6, String.valueOf(HorraireF.getValue()));
              st.setInt(7, CombolistCategorie.getValue().getId());
              
               
           
            st.executeUpdate();
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    private void sendMail(String recepient) throws Exception {
        System.out.println("preparing");
        
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
       
          Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(myAccountEmail,password);
            }
              
});
          Message  message = prepareMessage(session,myAccountEmail,recepient);
        Transport.send(message);
         System.out.println("sent");
    }

    private Message prepareMessage(Session session,String myAccountEmail,String recepient ) {
        try {
            Message message = new MimeMessage(session );
              message.setFrom(new InternetAddress(myAccountEmail ));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("NOUVEL EVENEMENT§");
            message.setText("Bonjour Madame/Monsieur,\n Un nouvel evenement vient d'être posté. N'hésitez pas d'aller le CHECKER!!\n Cordialement");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(AddEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
       }

    @FXML
    private void valider(ActionEvent event) throws Exception {
        if(TitreF.getText().equals("") || DescritionF.getText().equals("") 
                 ||ImageTF.getText().equals("")|| LongitudeF.getText().equals("") || LaltitudeTF.getText().equals("")||CombolistCategorie.getValue() == null ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Verifier tout les champs ! ");
            alert.showAndWait();
             String titles = "ERREUR ";
                String msgs = "Veuiller vérifier les données";
                TrayNotification trays = new TrayNotification();
                AnimationType types = AnimationType.POPUP;
               
                trays.setAnimationType(types);
                trays.setTitle(titles);
                trays.setMessage(msgs);
                trays.showAndDismiss(Duration.seconds(10));
                trays.setNotificationType(NotificationType.ERROR);
            return;
        }else{
            
            getQuery();
            insert();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
            //annuler( event);
            sendMail("myriam.brahmi@esprit.tn");
           
        }
        String titles = "Evenement ajouté ";
                String msgs = "avec succées\n Mail envoyé";
                TrayNotification trays = new TrayNotification();
                AnimationType types = AnimationType.POPUP;
               
                trays.setAnimationType(types);
                trays.setTitle(titles);
                trays.setMessage(msgs);
                trays.showAndDismiss(Duration.seconds(10));
                trays.setNotificationType(NotificationType.SUCCESS);
    }
    
}
