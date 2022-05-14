package GUI;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entites.Produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private ImageView img;
    private Image image;
    @FXML
    private Label priceLable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void click(MouseEvent event) {
    }
     private Produit Prod;
     public void setData(Produit Prod){
        try {
            this.Prod = Prod;
            nameLabel.setText(Prod.getNom());
            priceLable.setText(String.valueOf(Prod.getDescription()));
            Image image = new Image(new FileInputStream("C:\\Users\\sonicmaster\\Desktop\\ESSAIFX\\Final maa jme3aa\\AdomifitDesktop\\src\\images\\"+Prod.getImage()));
            img.setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
}
