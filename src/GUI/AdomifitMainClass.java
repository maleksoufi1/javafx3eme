package GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Kouki
 */
public class AdomifitMainClass extends Application {

    
    @Override
    public void start(Stage stage) {
       
        try {
       // Parent root = FXMLLoader.load(getClass().getResource("CategorieRegime.fxml"));
          // Parent root = FXMLLoader.load(getClass().getResource("RepasForm.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.setTitle("Adomifit");
        stage.setScene(new Scene(root));
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdomifitMainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
