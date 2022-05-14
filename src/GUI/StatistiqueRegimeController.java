/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Kouki
 */
public class StatistiqueRegimeController implements Initializable {
private Parent fxml;
 private Statement st;
    private ResultSet rs;
    private Connection cnx;
    @FXML
    private AnchorPane root;
    @FXML
    private Pane ContentPaneGestionnaire;
    @FXML
    private Button btn_listCategorie;
    @FXML
    private Button btn_mesRegimes;
    @FXML
    private Button btn_Statistique;
    @FXML
    private Button btn_ListSuivi;
    @FXML
    private PieChart pieCategorie;
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
    @FXML
    private PieChart pieDateRgime;
     ObservableList<PieChart.Data>data2=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cnx=DataSource.getInstance().getConn();
        statCategorie();
        statDateRegime();
    }    

    @FXML
    private void showListCtegorieRegime(MouseEvent event) {
          try {
            fxml = FXMLLoader.load(getClass().getResource("CategorieRegime.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(CategorieRegimeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    @FXML
    private void showMesRegimes(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("MesRegimes.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(CategorieRegimeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showListeSuivis(MouseEvent event) {
          try {
            fxml = FXMLLoader.load(getClass().getResource("ListeSuivis.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(DashboardNutritionnisteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    @FXML
    private void showStatistique(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("StatistiqueRegime.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(CategorieRegimeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void statCategorie() {
          try {
           
          String query = "SELECT COUNT(*),categorie_regime_id  FROM Regime GROUP BY categorie_regime_id " ;
       
             PreparedStatement PreparedStatement = cnx.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
            
                     
            while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("categorie_regime_id"),rs.getInt("COUNT(*)")));
            }     
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueRegimeController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        pieCategorie.setTitle(" Catégorie Régime ");
        pieCategorie.setLegendSide(Side.LEFT);
        pieCategorie.setData(data);
    }

    
    private void statDateRegime(){
      try {
           
          String query = "SELECT SUBSTRING(r.created_at, 1, 10) as dateRegimes, COUNT(*) as count FROM Regime r GROUP BY dateRegimes" ;
       
             PreparedStatement PreparedStatement = cnx.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
            
                     
            while (rs.next()){               
               data2.add(new PieChart.Data(rs.getString("dateRegimes"),rs.getInt("count")));
            }     
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueRegimeController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        pieDateRgime.setTitle(" Date Régime ");
        pieDateRgime.setLegendSide(Side.LEFT);
        pieDateRgime.setData(data2);
    }

    @FXML
    private void showListCtegorieRegime(ActionEvent event) {
    }
    
    
}
