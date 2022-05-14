package GUI;

import entites.Evenement;
import entites.Mapa;
import entites.Regime;
import entites.SuiviRegime;
import java.io.File;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import java.awt.BorderLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.swing.MapView;

import com.teamdev.jxmaps.*;
import entites.Billet;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.BilletService;
import services.EvenementService;
import services.RegimeService;
import services.SuiviRegimeService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class FrontEvenementController implements Initializable {
    private Parent fxml;
   @FXML
    private VBox chosenRegimeCard;
    @FXML
    private Label RegimeTypeLabel;   
    @FXML
    private Label RegimePrixLabel;
    @FXML
    private ImageView RegimeImage;
    @FXML
    private Label RegimeDescriptionLabel;
    @FXML
    private Label RegimeCategorieLabel;
    @FXML
    private ScrollPane scrollRegime;
    @FXML
    private GridPane gridRegime;
    
    private MyListenerE myListener;
    @FXML
    private Button acheterBtn;
    Evenement regAch = new Evenement();
    @FXML
    private AnchorPane root;
    Mapa mape;
    @FXML
    private Button mapa;
    @FXML
    private TextField rechT;
    @FXML
    private Button cat;
    
    
    String myAccountEmail = "myriambrahmi23@gmail.com";
    String password = "Mimo1901";
    private void setChosenFruit(Evenement evenement) {
        regAch = evenement;
             RegimeTypeLabel.setText(evenement.getTitre());
        RegimePrixLabel.setText( String.valueOf(evenement.getHorraire()));
         String path = evenement.getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
              RegimeImage.setImage(img);
        RegimeDescriptionLabel.setText(evenement.getDescription());
        
        RegimeDescriptionLabel.setWrapText(true);
       // RegimeDificulteLabel.setText(evenement.getDificulte());
        RegimeCategorieLabel.setText(evenement.getCategorie().getLibelle());
        //RegimeNutritionnisteLabel.setText("Evenement not found");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     EvenementService regServ = new EvenementService();
      List<Evenement> regList = regServ.getAll();
        if (regList.size() > 0) {
            setChosenFruit(regList.get(0));
            myListener = new MyListenerE() {
                @Override
                public void onClickListener(Evenement evenement) {
                    setChosenFruit(evenement);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < regList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("BoxEvenement.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                BoxEvenementController boxController  = fxmlLoader.getController();
                boxController.setData(regList.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                gridRegime.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                gridRegime.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridRegime.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridRegime.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridRegime.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridRegime.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridRegime.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void acheterRegime(MouseEvent event)throws Exception {
        
         String titles = "Participation Enregistrée ";
                String msgs = "avec succées\n Mail envoyé";
                TrayNotification trays = new TrayNotification();
                AnimationType types = AnimationType.POPUP;
               
                trays.setAnimationType(types);
                trays.setTitle(titles);
                trays.setMessage(msgs);
                trays.showAndDismiss(javafx.util.Duration.seconds(10));
                trays.setNotificationType(NotificationType.SUCCESS);
                sendMail("myriam.brahmi@esprit.tn");
               
       
//        
//        boolean b = listSiuv.contains(ss);
//        
//        System.out.println("contains : "+listSiuv.contains(ss.getId()));
//       if(listSiuv.contains(ss.getId()) == false){
//            System.out.println("le suivi non disponible : ");
//            //Session userid remplace 2
//            Ssuiv.addSuiviRegime(new SuiviRegime(null, null, 0, regAch.getId(), 2));
//            System.out.println("regime acheter avec succes : ");
//            
//            root.getChildren().removeAll();
//            root.getChildren().setAll(fxml);
//        } catch (IOException ex) {
//            Logger.getLogger(CategorieRegimeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       }
//      
///       }else{
//            System.out.println("le suivi a acheter est : "+ss);
//           
//            
//              try {
//            fxml = FXMLLoader.load(getClass().getResource("SuiviRegimeExiste.fxml"));
//            root.getChildren().removeAll();
//            root.getChildren().setAll(fxml);
//        } catch (IOException ex) {
//            Logger.getLogger(CategorieRegimeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       }
//      
//       
    }

    @FXML
    private void map() {
        final Mapa example = new Mapa("test");
      
		example.generateMarker(mape.getCenter());
    }

//    @FXML
//    private void raiting(MouseEvent event) {
//          try {
//            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/raiting.fxml"));
//            Scene scene = new Scene(parent);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.initStyle(StageStyle.UTILITY);
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(CategorieEvenementController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @FXML
    private void cat(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/FrontEvenementC.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
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
            message.setSubject("Merci Pour Votre Participation");
            message.setText("Bonjour Madame/Monsieur,\n Votre Participation a été bien enregistrée ,vous avez reservez un billet pour cet evenement .Restez connectés ,vous recvrez des nouvelles de notre part! .\n Cordialement");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(AddEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
       }

}
