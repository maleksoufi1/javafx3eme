package GUI;

import entites.Evenement;
import entites.Regime;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class BoxEvenementController {
     @FXML
    private Label typeRegimeBox;
    @FXML
    private Label prixRegimeBox;
    @FXML
    private ImageView imgRegimeBox;
    @FXML
    private Label categorieRegimeBox;
    private Evenement evenement;
    private MyListenerE myListener;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(evenement);
    }



     void setData(Evenement evenement,MyListenerE myListener) {
       this.evenement = evenement;
       this.myListener = myListener;
       typeRegimeBox.setText(evenement.getTitre());
       prixRegimeBox.setText( String.valueOf(evenement.getHorraire()));
        String path = evenement.getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
              imgRegimeBox.setImage(img);
       categorieRegimeBox.setText(evenement.getCategorie().getLibelle());
    }
}
