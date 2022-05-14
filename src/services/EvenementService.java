/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.CategorieEvenement;
import entites.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author myria
 */
public class EvenementService {
    private Connection connexion;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet rs;

    public EvenementService() {
        connexion = DataSource.getInstance().getConn();
    }
    
        public List<Evenement> getAll(){
            
     String requete="select * from evenement";
     List<Evenement> list = new ArrayList<>();
        try {
            ste = connexion.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) { 
                CategorieEvenementService ss = new CategorieEvenementService();
                CategorieEvenement categorie = new CategorieEvenement();
              categorie =  ss.getCategorieEvenement(rs.getInt("categorie_evenement_id"));
                list.add(new Evenement(rs.getInt("id"),rs.getString("titre"), rs.getString("description"),rs.getString("image"),rs.getFloat("latitude"),rs.getFloat("longitude")
               ,rs.getDate("horraire"),rs.getInt("categorie_evenement_id"),categorie) );              
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieEvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        
        
             public void addEvenement(Evenement r){
                 
                // LocalDateTime now = LocalDateTime.now();
                 //r.setCreated_at(now );
String requete="insert into evenement (titre,description,image,longitude,latitude,categorie_evenement_id) values('"+r.getTitre()+"','"+r.getDescription()+
        "','"+r.getImage()+"','"+r.getLongitude()+"','"+r.getLatitude()+"','"+r.getCategorie_evenement_id()+ "')";
        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieEvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
             
                 
        public void deleteEvenement(int id) {
     
         String requete = "DELETE FROM evenement WHERE id ="+id;
        
           try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieEvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
            public  Evenement getEvenement(int id) {
     
         String requete = "select * FROM evenement WHERE id = '" + id + "'";
        Evenement r = new Evenement();
        
           try {
            ste = connexion.createStatement();
                       rs= ste.executeQuery(requete);
            while (rs.next()) { 
      
                
                 r.setId(id);
                 r.setTitre(rs.getString("titre"));
                 r.setDescription(rs.getString("description"));
                 r.setImage(rs.getString("image"));
                 r.setLongitude(rs.getFloat("longitude"));
                 r.setLatitude(rs.getFloat("latitude"));
                 
                 r.setCategorie_evenement_id(rs.getInt("categorie_evenement_id"));
                 
            
                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieEvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
            return r;
    }
            
            
               public void updateEvenement(Evenement r){
      String requete="UPDATE evenement SET titre = '"+r.getTitre()+"' , description ='"+r.getDescription()+"', image ='"+r.getImage()+
              "', longitude ='"+r.getLongitude()+"', latitude ='"+r.getLatitude()+"', categorie_evenement_id ='"+r.getCategorie_evenement_id()+
              "' where id='" + r.getId() + "' ";
       try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
    
        } catch (SQLException ex) {
            Logger.getLogger(CategorieEvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
     
}
