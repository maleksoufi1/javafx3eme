/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entites.CategorieRegime;
import entites.Regime;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author Kouki
 */
public class RegimeService {
    private Connection connexion;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet rs;

    public RegimeService() {
        connexion = DataSource.getInstance().getConn();
    }
            public List<Regime> getMesRegime(int id){
     String requete="select * from regime where user_id = "+id;
     List<Regime> list = new ArrayList<>();
        try {
            ste = connexion.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) {
                CategorieRegimeService ss = new CategorieRegimeService();
                CategorieRegime categorie = new CategorieRegime();
              categorie =  ss.getCategorieRegime(rs.getInt("categorie_regime_id"));
              
//                list.add(new Regime(rs.getInt("id"),rs.getString("type"),rs.getString("description"),rs.getString("dificulte"),rs.getString("image"),
//                rs.getFloat("prix"),rs.getInt("categorie_regime_id"),rs.getInt("user_id")) );
                
                
                list.add(new Regime(rs.getInt("id"), rs.getString("type"), rs.getString("description"),
                        rs.getString("dificulte"), rs.getString("image"),rs.getFloat("prix"),rs.getInt("categorie_regime_id"),rs.getInt("user_id"), categorie));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
        public List<Regime> getAll(){
     String requete="select * from regime";
     List<Regime> list = new ArrayList<>();
        try {
            ste = connexion.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) {
                CategorieRegimeService ss = new CategorieRegimeService();
                CategorieRegime categorie = new CategorieRegime();
              categorie =  ss.getCategorieRegime(rs.getInt("categorie_regime_id"));
              
//                list.add(new Regime(rs.getInt("id"),rs.getString("type"),rs.getString("description"),rs.getString("dificulte"),rs.getString("image"),
//                rs.getFloat("prix"),rs.getInt("categorie_regime_id"),rs.getInt("user_id")) );
                
                
                list.add(new Regime(rs.getInt("id"), rs.getString("type"), rs.getString("description"),
                        rs.getString("dificulte"), rs.getString("image"),rs.getFloat("prix"),rs.getInt("categorie_regime_id"),rs.getInt("user_id"), categorie));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        
        
             public void addRegime(Regime r){
                 
                 LocalDateTime now = LocalDateTime.now();
                 r.setCreated_at(now );
String requete="insert into regime (type,description,dificulte,image,prix,categorie_regime_id,user_id,created_at) values('"+r.getType()+"','"+r.getDescription()+
        "','"+r.getDificulte()+"','"+r.getImage()+"','"+r.getPrix()+"','"+r.getCategorie_regime_id()+"','"+r.getUser_id()+"','"+r.getCreated_at()+ "')";
        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
             
                 
        public void deleteRegime(int id) {
     
         String requete = "DELETE FROM regime WHERE id ="+id;
        
           try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
            public  Regime getRegime(int id) {
     
         String requete = "select * FROM regime WHERE id = '" + id + "'";
        Regime r = new Regime();
        
           try {
            ste = connexion.createStatement();
                       rs= ste.executeQuery(requete);
            while (rs.next()) { 
      
                
                 r.setId(id);
                 r.setType(rs.getString("type"));
                 r.setDescription(rs.getString("description"));
                 r.setDificulte(rs.getString("dificulte"));
                 r.setImage(rs.getString("image"));
                 r.setPrix(rs.getFloat("prix"));
                 r.setCategorie_regime_id(rs.getInt("categorie_regime_id"));
                 r.setUser_id(rs.getInt("user_id"));
            
                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
            return r;
    }
            
            
               public void updateRegime(Regime r){
      String requete="UPDATE regime SET type = '"+r.getType()+"' , description ='"+r.getDescription()+"', dificulte ='"+r.getDificulte()+"', image ='"+r.getImage()+
           "', prix ='"+r.getPrix()+"', categorie_regime_id ='"+r.getCategorie_regime_id()+"', user_id ='"+r.getUser_id()+
              "' where id='" + r.getId() + "' ";
       try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
    
        } catch (SQLException ex) {
            Logger.getLogger(CategorieRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
               
               
               
               
                 public ObservableList<Regime> getSearch(String s){
    String requete ="select * from regime where type LIKE '%" + s + "%' OR description LIKE '%" + s + "%'OR dificulte LIKE '%"+s+"%' " ;
     ObservableList<Regime> list = FXCollections.observableArrayList();
        try {
            ste = connexion.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) {
                CategorieRegimeService ss = new CategorieRegimeService();
                CategorieRegime categorie = new CategorieRegime();
              categorie =  ss.getCategorieRegime(rs.getInt("categorie_regime_id"));
              
//                list.add(new Regime(rs.getInt("id"),rs.getString("type"),rs.getString("description"),rs.getString("dificulte"),rs.getString("image"),
//                rs.getFloat("prix"),rs.getInt("categorie_regime_id"),rs.getInt("user_id")) );
                
                
                list.add(new Regime(rs.getInt("id"), rs.getString("type"), rs.getString("description"),
                        rs.getString("dificulte"), rs.getString("image"),rs.getFloat("prix"),rs.getInt("categorie_regime_id"),rs.getInt("user_id"), categorie));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       
    
}
