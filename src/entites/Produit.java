/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Objects;

/**
 *
 * @author MSI
 */
public class Produit {
    private int id,prix,categorie_produit_id;
    private String nom,image,description;

    public Produit() {
    }

    
    public Produit(int prix, int categorie_produit_id, String nom, String image, String description) {
        this.prix = prix;
        this.categorie_produit_id = categorie_produit_id;
        this.nom = nom;
        this.image = image;
        this.description = description;
    }

    public Produit(int id, int prix, int categorie_produit_id, String nom, String image, String description) {
        this.id = id;
        this.prix = prix;
        this.categorie_produit_id = categorie_produit_id;
        this.nom = nom;
        this.image = image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getCategorie_produit_id() {
        return categorie_produit_id;
    }

    public void setCategorie_produit_id(int categorie_produit_id) {
        this.categorie_produit_id = categorie_produit_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", prix=" + prix + ", categorie_produit_id=" + categorie_produit_id + ", nom=" + nom + ", image=" + image + ", description=" + description + '}';
    }

    

}
