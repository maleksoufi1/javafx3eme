/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Timestamp;

/**
 *
 * @author Asus
 */
public class ReponseReclamation {
    private int id ;
    private String contenu;
    private Timestamp createdAt;
    private int user_id,reclamation_id;

    public ReponseReclamation(String contenu, int user_id, int reclamation_id) {
        this.contenu = contenu;
        this.user_id = user_id;
        this.reclamation_id = reclamation_id;
    }

    public ReponseReclamation(String contenu) {
        this.contenu = contenu;
    }

    public ReponseReclamation(int id, String contenu) {
        this.id = id;
        this.contenu = contenu;
    }
    

    public ReponseReclamation() {
    }

    @Override
    public String toString() {
        return "ReponseReclamation{" + "id=" + id + ", contenu=" + contenu + ", createdAt=" + createdAt + '}';
    }

    public ReponseReclamation(int id, String contenu, Timestamp createdAt) {
        this.id = id;
        this.contenu = contenu;
        this.createdAt = createdAt;
    }

    public ReponseReclamation(int id, String contenu, Timestamp createdAt, int reclamation_id) {
        this.id = id;
        this.contenu = contenu;
        this.createdAt = createdAt;
        this.reclamation_id = reclamation_id;
    }

    public ReponseReclamation(String contenu, Timestamp createdAt) {
        this.contenu = contenu;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getReclamation_id() {
        return reclamation_id;
    }

    public void setReclamation_id(int reclamation_id) {
        this.reclamation_id = reclamation_id;
    }
    
    
    
}
