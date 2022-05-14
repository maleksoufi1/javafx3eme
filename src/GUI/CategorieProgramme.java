/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;
import GUI.ProgrammeController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author MSI
 */
public class CategorieProgramme {
    
      private int id;
      private String libelle ;
      private String description;
            private String image;
            
            
            
            
     
       Button affiche ;
       ImageView photo ;
       
        public static String filename="";
        public static String pathfile; 

   

    @Override
    public String toString() {
        return "CategorieProgramme{" + "id=" + id + ", libelle=" + libelle + ", description=" + description  + ", image=" + image +  '}';
    }
    

  

    public CategorieProgramme() {}

    

    public CategorieProgramme(int id, String libelle, String description, String image, ImageView photo, Button affiche) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.image = image;
        this.affiche = affiche;
        this.photo = photo;
        
        
        
        
        affiche.setOnAction(event ->{
               try {
         Parent parent = FXMLLoader.load(getClass().getResource("ProgrammeByCat.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            //stage.getIcons().add(new Image("cc.png"));
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProgrammeByCatController.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
    }

    public CategorieProgramme(int id, String libelle, String description, String image) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.image = image;
    }

    public CategorieProgramme(String libelle, String description, String image) {
        this.libelle = libelle;
        this.description = description;
        this.image = image;
    }

    public CategorieProgramme(int id, String libelle, String description, String image, ImageView photo) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.image = image;
        this.photo = photo;
    }

    public CategorieProgramme(String libelle, String description, String image, ImageView photo) {
        this.libelle = libelle;
        this.description = description;
        this.image = image;
        this.photo = photo;
    }

    public CategorieProgramme(int id, String libelle, String description, ImageView photo) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.photo = photo;
    }

    public CategorieProgramme(int id, String libelle, String description) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
    }

    public CategorieProgramme(String libelle, String description) {
        this.libelle = libelle;
        this.description = description;
    }
    

   

  

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public Button getAffiche() {
        return affiche;
    }

    public void setAffiche(Button affiche) {
        this.affiche = affiche;
    }
      
    
}
