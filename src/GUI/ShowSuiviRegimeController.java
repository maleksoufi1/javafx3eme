/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import entites.Regime;
import entites.Repas;
import entites.SuiviRegime;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import services.RegimeService;
import services.RepasService;
import services.SuiviRegimeService;

/**
 * FXML Controller class
 *
 * @author Kouki
 */
public class ShowSuiviRegimeController implements Initializable {

    @FXML
    private Label nomNutritionniste;
   
    @FXML
    private Label typeRegime;

    @FXML
    private Label titreSuivi;

    @FXML
    private Label noteSuivi;

    @FXML
    private Text remarqueSuivi;
    
    SuiviRegime suiviCourant = new SuiviRegime();
   
    @FXML
    private VBox vbox_repas1;
    @FXML
    private Label RegimeTypeLabel;

 
    @FXML
    private VBox vbox_repas2;
   
    @FXML
    private Label labelReapsAjour;
    @FXML
    private Label RegimeTypeLabel1;
    @FXML
    private Label RegimeTypeLabel2;
    @FXML
    private Label RegimeTypeLabel3;
    @FXML
    private Label RegimeTypeLabel31;
    @FXML
    private Button btn_imprimer;
    @FXML
    private AnchorPane anch;
     private SoundRegime alarmSound;
    MediaPlayer mediaPlayer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // get suivi user connecté session   
        SuiviRegimeService servSuiv = new SuiviRegimeService();
        RegimeService servReg = new RegimeService();
        //session remplace 2
        suiviCourant = servSuiv.getSuiviUser(2); 
        
        
        if(suiviCourant.getTitre()==null){
             this.titreSuivi.setText("Titre non encore attribué");
        }else{
             this.titreSuivi.setText(suiviCourant.getTitre());
        }
        
        
         if(suiviCourant.getRemarque()==null){
             this.remarqueSuivi.setText("Votre nutritionniste n'a pas encore servi une remarque");
        }else{
             this.remarqueSuivi.setText(suiviCourant.getRemarque());
        }
         
         
               
        this.noteSuivi.setText(String.valueOf(suiviCourant.getNote()));

        Regime reg = servReg.getRegime(suiviCourant.getRegime_id());
        this.typeRegime.setText(reg.getType());
        
       AfficherRepas(); 
    } 
    void music(){
        alarmSound = null;
        try {
            alarmSound = new SoundRegime("C:\\Users\\Kouki\\Documents\\NetBeansProjects\\testFX\\src\\views\\TS_alarm_wav.wav");
        } catch (IOException ex) {
            Logger.getLogger(ShowSuiviRegimeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(ShowSuiviRegimeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(ShowSuiviRegimeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
                            if (alarmSound != null) alarmSound.playAudio();
                        } catch (IOException | LineUnavailableException e1) {
                            e1.printStackTrace();
                        }
                        
        
    }
    public void AfficherRepas(){
        LocalDate todaysDate = LocalDate.now();
        LocalTime todaysTime = LocalTime.now();
        labelReapsAjour.setText(labelReapsAjour.getText()+" : "+todaysDate);
        RepasService servR = new RepasService();
        List<Repas> listrepas = servR.getAllCalendarSuivi(suiviCourant.getId());
       
        for (int i = 0; i < listrepas.size(); i++) {
           
           
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(listrepas.get(i).getEnd());
 
          
          HBox hbox = new HBox(70);
            Label labelTitle = new Label();
            labelTitle.setPrefWidth(120);
            labelTitle.setAlignment(Pos.CENTER_LEFT);
            labelTitle.setText(listrepas.get(i).getTitle());
           //labelTitle.setTextFill(Color.web(listrepas.get(i).getBackground_color()));
           labelTitle.setStyle("-fx-border-color:"+listrepas.get(i).getBackground_color()+";");
            //start
            LocalTime startTime = LocalTime.of(listrepas.get(i).getStart().getHours(), listrepas.get(i).getStart().getMinutes());
             Label labelStart = new Label();
            labelStart.setPrefWidth(120);
            labelStart.setAlignment(Pos.CENTER_LEFT);
            labelStart.setText(startTime.toString());
            
            
            //end
            LocalTime endTime = LocalTime.of(listrepas.get(i).getEnd().getHours(), listrepas.get(i).getEnd().getMinutes());
             Label labelEnd = new Label();
            labelEnd.setPrefWidth(120);
            labelEnd.setAlignment(Pos.CENTER_LEFT);
            labelEnd.setText(endTime.toString());
            
            
            //Description
            
            Label labelDescription = new Label();
            labelDescription.setPrefWidth(120);
            labelDescription.setAlignment(Pos.CENTER_LEFT);
            labelDescription.setText(listrepas.get(i).getDescription());
            
            //allday
            Label labelallDay = new Label();
            labelallDay.setPrefWidth(120);
            labelallDay.setAlignment(Pos.CENTER_LEFT);
     
            if(listrepas.get(i).getAllday() == 1){
            labelallDay.setText("Oui");
            }else{
            labelallDay.setText("Non");
            }
             hbox.getChildren().addAll(labelTitle,labelStart,labelEnd,labelDescription,labelallDay);
             hbox.setStyle("-fx-border-color:"+listrepas.get(i).getBackground_color()+";");
            if(date.equals(todaysDate.toString())){
                 String pattern2 = "hh:mm:ss";
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern2);
            String datet = simpleDateFormat2.format(listrepas.get(i).getStart());
            if(datet.equals(todaysTime) ){
               
                music();
                System.out.println("its time to eat");
            }
           
            
                
                  vbox_repas1.getChildren().addAll(hbox);
            }
            if( (!date.equals(todaysDate.toString()) && (listrepas.get(i).getChecked() == 0 ) ) ){
             
                 vbox_repas2.getChildren().addAll(hbox);
            }
            
             
        }
       
    } 

    @FXML
    private void print(MouseEvent event) {
         PrinterJob job = PrinterJob.createPrinterJob();
          Node root= this.anch;
             if(job != null){
     job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
     Printer printer = job.getPrinter();
     PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
     boolean success = job.printPage(pageLayout, root);
     if(success){
        job.endJob();
     }
   }
    }
    
     
    
}
