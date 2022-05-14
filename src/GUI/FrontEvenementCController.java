/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.CategorieEvenement;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.CategorieEvenementService;
import services.RegimeService;
import services.SuiviRegimeService;


public class FrontEvenementCController implements Initializable {
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
    private ScrollPane scrollRegime;
    @FXML
    private GridPane gridRegime;
    
    private MyListenerC myList;
    @FXML
    private Button acheterBtn;
    CategorieEvenement regAch = new CategorieEvenement();
    @FXML
    private Button ev;
    
    
    ObservableList<CategorieEvenement> CatList = FXCollections.observableArrayList();
    @FXML
    private TextField rech;
    @FXML
    private AnchorPane root;
    private void setChosenFruit(CategorieEvenement categorie) {
        regAch = categorie;
             RegimeTypeLabel.setText(categorie.getLibelle());
      //  RegimePrixLabel.setText( String.valueOf(categorie.getPrix())+"DT");
        String path = categorie.getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
              RegimeImage.setImage(img);
        RegimeDescriptionLabel.setText(categorie.getDescription());
        
        RegimeDescriptionLabel.setWrapText(true);
        //RegimeDificulteLabel.setText(categorie.getDificulte());
        //RegimeCategorieLabel.setText(categorie.getCategorie().getLibelle());
        //RegimeNutritionnisteLabel.setText("categorie not found");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     CategorieEvenementService regServ = new CategorieEvenementService();
      List<CategorieEvenement> regList = regServ.getAll();
        if (regList.size() > 0) {
            setChosenFruit(regList.get(0));
            System.out.println("55");
            myList = new MyListenerC() {
                @Override
                public void onClickListener(CategorieEvenement categorie) {
                    setChosenFruit(categorie);
                    System.out.println("44");
                }
            };
//            myListener = new MyListenerC() {
//               @Override
//                public void onClickListener(CategorieEvenement categorie) {
//                    setChosenFruit(categorie);
//                    System.out.println("99");
//                }
//            };
              System.out.println("77");
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < regList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("BoxEvenementC.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                BoxEvenementCController boxController  = fxmlLoader.getController();
                boxController.setData(regList.get(i),myList);

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
        
 try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/FrontEvenement.fxml"));
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

    @FXML
    private void ev(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/FrontEvenement.fxml"));
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
    }

    @FXML
    private void redirectProgrammes(MouseEvent event) {
    }

    @FXML
    private void redirectForum(MouseEvent event) {
    }


}