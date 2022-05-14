/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Asus
 */
public class Reclamation {
    private int id;
    private String titre;
    private String contenu ;
    private int id_user;
    private int id_reclamation;
    
    private Timestamp createdAt;

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public Reclamation() {
    }

    public Reclamation(String titre, String contenu, int id_user, Timestamp createdAt) {
        this.titre = titre;
        this.contenu = contenu;
        this.id_user = id_user;
        this.createdAt = createdAt;
    }

    public Reclamation(String titre, String contenu, Timestamp createdAt) {
        this.titre = titre;
        this.contenu = contenu;
        this.createdAt = createdAt;
    }

    public Reclamation(int id, String titre, String contenu, int id_user) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.id_user = id_user;
    }

    public Reclamation(int id, String titre, String contenu, Timestamp createdAt) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.createdAt = createdAt;
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

    public Reclamation(String titre, String contenu,int id_user) {
        this.titre = titre;
        this.contenu = contenu;
        this.id_user=id_user;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", titre=" + titre + ", contenu=" + contenu + ", createdAt=" + createdAt + '}';
    }

    
    
    
}

