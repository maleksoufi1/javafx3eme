/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.image.ImageView;

/**
 *
 * @author Kouki
 */
public class Regime {
        
    private int id; 
    private String type; 
    private String description; 
    private String image; 
    private String dificulte; 
    private float prix;
    private LocalDateTime created_at;
    private int user_id;
     ImageView photo ;
    
    private int categorie_regime_id ;
     
    private CategorieRegime categorie;
    
      
        
    public Regime() {
    }

    public Regime(int id, String type, String description,  String dificulte, String image, float prix, int categorie_regime_id, int user_id) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.image = image;
        this.dificulte = dificulte;
        this.prix = prix;
        this.categorie_regime_id = categorie_regime_id;
        this.user_id = user_id;
    }

    public Regime(String type, String description,  String dificulte, String image, float prix, int categorie_regime_id, int user_id) {
        this.type = type;
        this.description = description;
        this.image = image;
        this.dificulte = dificulte;
        this.prix = prix;
        this.categorie_regime_id = categorie_regime_id;
        this.user_id = user_id;
    }

    public Regime(int id, String type, String description,  String dificulte, String image,float prix, int user_id, int categorie_regime_id, CategorieRegime categorie) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.image = image;
        this.dificulte = dificulte;
        this.prix = prix;
        this.user_id = user_id;
        this.categorie_regime_id = categorie_regime_id;
        this.categorie = categorie;
    }

    public Regime(int id, String type, String description, String image, String dificulte, float prix, LocalDateTime created_at, int user_id, ImageView photo, int categorie_regime_id, CategorieRegime categorie) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.image = image;
        this.dificulte = dificulte;
        this.prix = prix;
        this.created_at = created_at;
        this.user_id = user_id;
        this.photo = photo;
        this.categorie_regime_id = categorie_regime_id;
        this.categorie = categorie;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getDificulte() {
        return dificulte;
    }

    public void setDificulte(String dificulte) {
        this.dificulte = dificulte;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getCategorie_regime_id() {
        return categorie_regime_id;
    }

    public void setCategorie_regime_id(int categorie_regime_id) {
        this.categorie_regime_id = categorie_regime_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public CategorieRegime getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieRegime categorie) {
        this.categorie = categorie;
    }
     
    @Override
    public String toString() {
        return type;
    }
    
   
}
