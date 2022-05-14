package GUI;

import entites.Regime;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class BoxRegimeController {
     @FXML
    private Label typeRegimeBox;
    @FXML
    private Label prixRegimeBox;
    @FXML
    private ImageView imgRegimeBox;
    @FXML
    private Label categorieRegimeBox;
    private Regime regime;
    private MyListener myListener;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(regime);
    }



     void setData(Regime regime,MyListener myListener) {
       this.regime = regime;
       this.myListener = myListener;
       typeRegimeBox.setText(regime.getType());
       prixRegimeBox.setText( String.valueOf(regime.getPrix())+"DT");
        String path = "C:\\xampp\\htdocs\\adomifitt\\public\\uploads\\images\\"+regime.getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
              imgRegimeBox.setImage(img);
       categorieRegimeBox.setText(regime.getCategorie().getLibelle());
    }
}
