/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.CategorieEvenement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author myria
 */
public class CategorieEvenementService {
    private Connection connexion;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet rs;

    public CategorieEvenementService() {
        connexion = DataSource.getInstance().getConn();
    }
    
    public List<CategorieEvenement> getAll(){
     String requete="select * from categorie_evenement";
     List<CategorieEvenement> list = new ArrayList<>();
        try {
            ste = connexion.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) { 
                list.add(new CategorieEvenement(rs.getInt("id"),rs.getString("libelle"),rs.getString("description"),rs.getString("image")) );              
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieEvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
        public void addCategorieEvenement(CategorieEvenement cr){
       String requete="insert into categorie_evenement (libelle,description,image) values('"+cr.getLibelle()+"','"+cr.getDescription()+"','"+cr.getImage()+"')";
        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieEvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
        
        
        public void deleteCategorieEvenement(int id) {
     
         String requete = "DELETE FROM categorie_evenement WHERE id ="+id;
        
           try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieEvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
    
    public  CategorieEvenement getCategorieEvenement(int id) {
     
         String requete = "select * FROM categorie_evenement WHERE id = '" + id + "'";
        CategorieEvenement cr = new CategorieEvenement();
        
           try {
            ste = connexion.createStatement();
                       rs= ste.executeQuery(requete);
            while (rs.next()) { 
      
                
                 cr.setId(id);
                 cr.setLibelle(rs.getString("libelle"));
                 cr.setDescription(rs.getString("description"));
                 cr.setImage(rs.getString("image"));
                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieEvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
            return cr;
    }
    
    public void update(CategorieEvenement cr){
      String requete="UPDATE categorie_evenement SET libelle = '"+cr.getLibelle()+"' , description ='"+cr.getDescription()+"', image ='"+cr.getImage()+"' where id='" + cr.getId() + "' ";
       try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
    
        } catch (SQLException ex) {
            Logger.getLogger(CategorieEvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    
}
