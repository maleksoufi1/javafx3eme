/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.*;
import javafx.scene.image.ImageView;

/**
 *
 * @author sonicmaster
 */
public class Programme {
      private int id;
      private int rate;
      private int like;
      private int dislike;

      

      private String titre ;
      private String description;
      private int categorie_programme_id;
       private String image;
       ImageView photo ;
       
       
       public static int cat ;
       public static String filename="";
       public static String pathfile; 



    public Programme() {
    }

    public Programme(int id, String titre, String description, int categorie_programme_id, String image, ImageView photo) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.categorie_programme_id = categorie_programme_id;
        this.image = image;
        this.photo = photo;
    }

    public Programme(int id, String titre, String description, int categorie_programme_id, String image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.categorie_programme_id = categorie_programme_id;
        this.image = image;
    }

    public Programme(String titre, String description, int categorie_programme_id, String image) {
        this.titre = titre;
        this.description = description;
        this.categorie_programme_id = categorie_programme_id;
        this.image = image;
    }

    

    @Override
    public String toString() {
        return "Programme{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", categorie_programme_id=" + categorie_programme_id + ", image=" + image+ ", like=" + like +  ", dislike=" + dislike + ", rate=" + rate +  '}';
    }

    public Programme(int id, int rate, int like, int dislike, String titre, String description, int categorie_programme_id, String image, ImageView photo) {
        this.id = id;
        this.rate = rate;
        this.like = like;
        this.dislike = dislike;
        this.titre = titre;
        this.description = description;
        this.categorie_programme_id = categorie_programme_id;
        this.image = image;
        this.photo = photo;
    }
    

    public Programme(int id, String titre, String description, int categorie_programme_id) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.categorie_programme_id = categorie_programme_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategorie_programme_id() {
        return categorie_programme_id;
    }

    public void setCategorie_programme_id(int categorie_programme_id) {
        this.categorie_programme_id = categorie_programme_id;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }
    
    
    
    
    
    
    
    
    
    
}
