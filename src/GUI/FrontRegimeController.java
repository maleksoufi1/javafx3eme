package GUI;
import tray.animations.AnimationType;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import services.RegimeService;
import services.SuiviRegimeService;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

//import tray.animations.AnimationType;
//import tray.notification.NotificationType;
//import tray.notification.TrayNotification;

public class FrontRegimeController implements Initializable {
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
    private Label RegimeDificulteLabel;
    @FXML
    private Label RegimeCategorieLabel;
    @FXML
    private Label RegimeNutritionnisteLabel;
    @FXML
    private ScrollPane scrollRegime;
    @FXML
    private GridPane gridRegime;
    
    private MyListener myListener;
    @FXML
    private Button acheterBtn;
    Regime regAch = new Regime();
    @FXML
    private AnchorPane root;
    @FXML
    private TextField rechercheTF;
    private void setChosenFruit(Regime regime) {
        regAch = regime;
             RegimeTypeLabel.setText(regime.getType());
        RegimePrixLabel.setText( String.valueOf(regime.getPrix())+"DT");
         String path = "C:\\xampp\\htdocs\\adomifitt\\public\\uploads\\images\\"+regime.getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
              RegimeImage.setImage(img);
        RegimeDescriptionLabel.setText(regime.getDescription());
        
        RegimeDescriptionLabel.setWrapText(true);
        RegimeDificulteLabel.setText(regime.getDificulte());
        RegimeCategorieLabel.setText(regime.getCategorie().getLibelle());
        RegimeNutritionnisteLabel.setText("Nutritionniste not found");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
     RegimeService regServ = new RegimeService();
      List<Regime> regList = regServ.getAll();
        if (regList.size() > 0) {
            setChosenFruit(regList.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Regime regime) {
                    setChosenFruit(regime);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < regList.size(); i++) {
                    
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("BoxRegime.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                BoxRegimeController boxController  = fxmlLoader.getController();
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
    private void acheterRegime(MouseEvent event) {
        
        SuiviRegimeService Ssuiv = new SuiviRegimeService();
       List<SuiviRegime> listSiuv = Ssuiv.getAll();
       //Session userid  remplace 4
       SuiviRegime ss = Ssuiv.getSuiviUser(2);
      
       if(listSiuv.contains(ss) == false){
            
            //Session userid remplace 2
            Ssuiv.addSuiviRegime(new SuiviRegime(null, null, 0, regAch.getId(), 2));
             String titles = "Regime acheté ";
                String msgs = "avec succées";
                TrayNotification trays = new TrayNotification();
                AnimationType types = AnimationType.SLIDE;
                trays.setAnimationType(types);
                trays.setTitle(titles);
                trays.setMessage(msgs);
                trays.showAndDismiss(Duration.seconds(5));
                trays.setNotificationType(NotificationType.SUCCESS);
            System.out.println("regime acheter avec succes ");
            
       }else{
            try {
            fxml = FXMLLoader.load(getClass().getResource("SuiviRegimeExiste.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(CategorieRegimeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
      
       
    }

    @FXML
    private void searchRegime(KeyEvent event) {
        RegimeService rs = new RegimeService();
        ObservableList<Regime> list = rs.getSearch(rechercheTF.getText());
         if (list.size() > 0) {
            setChosenFruit(list.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Regime regime) {
                    setChosenFruit(regime);
                }
            };
        }
    }

    @FXML
    private void redirectRegime(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("FrontRegime.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(DashboardNutritionnisteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirectEvenemnt(MouseEvent event) {
          try {
            fxml = FXMLLoader.load(getClass().getResource("FrontEvenementC.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(DashboardNutritionnisteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirectProduit(MouseEvent event) {
          try {
            fxml = FXMLLoader.load(getClass().getResource("Front_1.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(DashboardNutritionnisteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirectProgrammes(MouseEvent event) {
          try {
            fxml = FXMLLoader.load(getClass().getResource("Front.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(DashboardNutritionnisteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirectForum(MouseEvent event) {
          try {
            fxml = FXMLLoader.load(getClass().getResource("FrontEvenementC.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(DashboardNutritionnisteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
