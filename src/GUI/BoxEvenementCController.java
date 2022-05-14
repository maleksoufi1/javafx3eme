/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.CategorieEvenement;
import entites.Regime;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class BoxEvenementCController {
     @FXML
    private Label typeRegimeBox;
    @FXML
    private ImageView imgRegimeBox;
    private CategorieEvenement categorie;
    private MyListenerC myListener;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(categorie);
    }



     void setData(CategorieEvenement categorie,MyListenerC myListener) {
       this.categorie = categorie;
       this.myListener = myListener;
       typeRegimeBox.setText(categorie.getLibelle());
      // prixRegimeBox.setText( String.valueOf(categorie.getPrix())+"DT");
        String path = categorie.getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
              imgRegimeBox.setImage(img);
       //categorieRegimeBox.setText(categorie.getCategorie().getLibelle());
    }
}
