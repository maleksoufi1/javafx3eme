/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entites.CategorieRegime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataSource;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Kouki
 */
public class CategorieRegimeService {
    private Connection connexion;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet rs;

    public CategorieRegimeService() {
        connexion = DataSource.getInstance().getConn();
    }
   
    public List<CategorieRegime> getAll(){
     String requete="select * from categorie_regime";
     List<CategorieRegime> list =new ArrayList();
        try {
            ste = connexion.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) { 
                list.add(new CategorieRegime(rs.getInt("id"),rs.getString("libelle"),rs.getString("description"),rs.getString("statcolor")) );              
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
        public void addCategorieRegime(CategorieRegime cr){
       String requete="insert into categorie_regime (libelle,description,statcolor) values('"+cr.getLibelle()+"','"+cr.getDescription()+"','"+cr.getStatcolor()+"')";
        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
        
        
        public void deleteCategorieRegime(int id) {
     
         String requete = "DELETE FROM categorie_regime WHERE id ="+id;
        
           try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
    
    public  CategorieRegime getCategorieRegime(int id) {
     
         String requete = "select * FROM categorie_regime WHERE id = '" + id + "'";
        CategorieRegime cr = new CategorieRegime();
        
           try {
            ste = connexion.createStatement();
                       rs= ste.executeQuery(requete);
            while (rs.next()) { 
      
                
                 cr.setId(id);
                 cr.setLibelle(rs.getString("libelle"));
                 cr.setDescription(rs.getString("description"));
                 cr.setStatcolor(rs.getString("statcolor"));
                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
            return cr;
    }
    
    public void update(CategorieRegime cr){
      String requete="UPDATE categorie_regime SET libelle = '"+cr.getLibelle()+"' , description ='"+cr.getDescription()+"', statcolor ='"+cr.getStatcolor()+"' where id='" + cr.getId() + "' ";
       try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
    
        } catch (SQLException ex) {
            Logger.getLogger(CategorieRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    
    
    
}
