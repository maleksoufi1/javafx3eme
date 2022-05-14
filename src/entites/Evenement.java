/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
/**
 *
 * @author myria
 */
public class Evenement {
    private int id; 
    private String titre; 
    private String description; 
    private String image; 
    private float longitude; 
    private float latitude;
    private Date horraire;
    private int categorie_evenement_id ;
    
     private CategorieEvenement categorie;

    public Evenement() {
    }

    public Evenement(int id, String titre, String description, String image, float longitude, float latitude, Date horraire, int categorie_evenement_id) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.longitude = longitude;
        this.latitude = latitude;
        this.horraire = horraire;
        this.categorie_evenement_id = categorie_evenement_id;
    }

    public Evenement(String titre, String description, String image, float longitude, float latitude, Date horraire, int categorie_evenement_id) {
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.longitude = longitude;
        this.latitude = latitude;
        this.horraire = horraire;
        this.categorie_evenement_id = categorie_evenement_id;
    }

    public Evenement(int id, String titre, String description, String image, float longitude, float latitude, int categorie_evenement_id) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.longitude = longitude;
        this.latitude = latitude;
        this.categorie_evenement_id = categorie_evenement_id;
    }

    public Evenement(String titre, String description, String image, float longitude, float latitude, int categorie_evenement_id) {
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.longitude = longitude;
        this.latitude = latitude;
        this.categorie_evenement_id = categorie_evenement_id;
    }

    public Evenement(String titre, String description, String image, float longitude, float latitude, Date horraire, int categorie_evenement_id, CategorieEvenement categorie) {
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.longitude = longitude;
        this.latitude = latitude;
        this.horraire = horraire;
        this.categorie_evenement_id = categorie_evenement_id;
        this.categorie = categorie;
    }

    public Evenement(int id, String titre, String description, String image, float longitude, float latitude, Date horraire, int categorie_evenement_id, CategorieEvenement categorie) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.longitude = longitude;
        this.latitude = latitude;
        this.horraire = horraire;
        this.categorie_evenement_id = categorie_evenement_id;
        this.categorie = categorie;
    }

    public Evenement(String text, String text0, String text1, int p, int l, String valueOf, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public Date getHorraire() {
        return horraire;
    }

        public void setHorraire(Date horraire) {
        this.horraire = horraire;
    }

    public int getCategorie_evenement_id() {
        return categorie_evenement_id;
    }

    public void setCategorie_evenement_id(int categorie_evenement_id) {
        this.categorie_evenement_id = categorie_evenement_id;
    }
    public CategorieEvenement getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieEvenement categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", image=" + image + ", longitude=" + longitude + ", latitude=" + latitude + ", horraire=" + horraire + ", categorie_evenement_id=" + categorie_evenement_id + '}';
    }
    
    
    
   
}
