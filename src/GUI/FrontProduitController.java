/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import entites.Produit;
import entites.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import services.CategorieProduitService;
import services.ProduitService;
import services.CategorieProduitService;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class FrontProduitController implements Initializable {

    private Label idnamelabel;
    private Label idpricelabel;
    private ImageView imgV;
    @FXML
    private GridPane grid;
    @FXML
    private ScrollPane scroll;

    private Image image;
   

    private int test;
    private int idprod;
    private int qtefinal;

    @FXML
    private Button btnReche;

    private List<String> cat = new ArrayList<>();

    private AnchorPane anchorpane;

    private AnchorPane anchorpane1;
    @FXML
    private ComboBox<String> boxCat;
    @FXML
    private ComboBox<String> sort_cb;

    private List<Produit> listprod = new ArrayList();

    ProduitService Gr = new ProduitService();
    List<Produit> l = Gr.afficheProduit();

    ObservableList<String> sortitems = FXCollections.observableArrayList("Prix", "Nom");
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
    private Button btn_repas1;
    @FXML
    private Button btn_programme;

   /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategorieProduitService c = new CategorieProduitService();
        cat = c.AllCatNames();
        boxCat.getItems().addAll(cat);

        sort_cb.getItems().removeAll(sort_cb.getItems());
        sort_cb.setItems(sortitems);
        sort_cb.getSelectionModel();

        ObservableList<Produit> Produits = getData();
      

        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < Produits.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/item.fxml"));
                anchorpane = fxmlLoader.load();
//               
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(Produits.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorpane, column++, row); //(child,column,row)
//                set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorpane, new Insets(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   

    private ObservableList<Produit> getData() {
        ProduitService cp = new ProduitService();
        ObservableList<Produit> myProds = cp.afficheProduit();
        return myProds;
    }

    private int setChosenProd(Produit produit) {
        try {
            
            idnamelabel.setText(produit.getNom());
            idpricelabel.setText(String.valueOf(produit.getPrix()));
            Image image = new Image(new FileInputStream(produit.getImage()));
            imgV.setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrontProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produit.getPrix();
    }

    

    

    

    

   

    private void AfficherProduits(ActionEvent event
    ) {
        grid.getChildren().clear();
        ProduitService rc = new ProduitService();
        ObservableList<Produit> liste = rc.trier();
        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < liste.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/item.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
//               
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(liste.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorpane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorpane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
Produit p;
double somme=0;
List<String> prd = new ArrayList<>();
List<String> qantit = new ArrayList<>();

    @FXML
    private void RechercheProduit(ActionEvent event) {
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
            Logger.getLogger(FrontProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   

  
    
}
