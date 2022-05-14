/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

/**
 *
 * @author Kouki
 */
public class CategorieRegime {
    private int id; 
    private String libelle; 
    private String description;
    private String statcolor;

    public CategorieRegime() {
    }

    public CategorieRegime(int id, String libelle, String description, String statcolor) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.statcolor = statcolor;
    }

    public CategorieRegime(String libelle, String description, String statcolor) {
        this.libelle = libelle;
        this.description = description;
        this.statcolor = statcolor;
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

    public String getStatcolor() {
        return statcolor;
    }

    public void setStatcolor(String statcolor) {
        this.statcolor = statcolor;
    }

    @Override
    public String toString() {
        return libelle ;
    }
    
    
    
}
