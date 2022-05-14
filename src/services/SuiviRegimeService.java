/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entites.Regime;
import entites.SuiviRegime;
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
 * @author Kouki
 */
public class SuiviRegimeService {
    private Connection connexion;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet rs;

    public SuiviRegimeService() {
         connexion = DataSource.getInstance().getConn();
    }
    
   
        public  SuiviRegime getSuiviUser(int id) {
     
       
          String requete = "select * FROM suivi_regime WHERE user_id = '" + id + "'";
           SuiviRegime r = new SuiviRegime();
            
           try {
            ste = connexion.createStatement();
            rs= ste.executeQuery(requete);
                         
            while (rs.next()) { 
                
             
                 r.setId(rs.getInt("id"));
                 
                 r.setNote(rs.getInt("note"));
                 r.setRemarque(rs.getString("remarque"));
                 r.setRegime_id(rs.getInt("regime_id"));
                 r.setUser_id(id);
                 r.setTitre(rs.getString("titre"));
            
                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuiviRegime.class.getName()).log(Level.SEVERE, null, ex);
        }
            return r;
    }
    
        public List<SuiviRegime> getAll(){
     String requete="select * from suivi_regime";
     List<SuiviRegime> list = new ArrayList<>();
        try {
            ste = connexion.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) { 
list.add(new SuiviRegime(rs.getInt("id"),rs.getString("titre"),rs.getString("remarque"),rs.getInt("note"),rs.getInt("regime_id"),rs.getInt("user_id")) );              
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        
        
             public void addSuiviRegime(SuiviRegime s){
        String requete="insert into suivi_regime (titre,remarque,note,regime_id,user_id) values(?,?,?,?,?)";
      
        try {
            pst = connexion.prepareStatement(requete);
            pst.setString(1, s.getTitre());
            pst.setString(2, s.getRemarque());
            pst.setInt(3, s.getNote());
            pst.setInt(4, s.getRegime_id());
            pst.setInt(5, s.getUser_id());
             
             pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SuiviRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
             
             
                 public void deleteSuiviRegime(int id) {
     
         String requete = "DELETE FROM suivi_regime WHERE id ="+id;
        
           try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(SuiviRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
                 
                 
                 
                 
                 
        public  SuiviRegime getSuiviRegime(int id) {
     
         String requete = "select * FROM suivi_regime WHERE id = '" + id + "'";
        SuiviRegime sr = new SuiviRegime();
        
           try {
            ste = connexion.createStatement();
            rs= ste.executeQuery(requete);
            while (rs.next()) { 
      
                
                 sr.setId(id);
                 sr.setTitre(rs.getString("titre"));
                 sr.setRemarque(rs.getString("remarque"));
                 sr.setNote(rs.getInt("note"));
                 sr.setRegime_id(rs.getInt("regime_id"));
                 sr.setUser_id(rs.getInt("user_id"));

                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuiviRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
            return sr;
    }
        
        
        
           public void update(SuiviRegime sr){
      String requete="UPDATE suivi_regime SET titre = '"+sr.getTitre()+"' , remarque ='"+sr.getRemarque()+"', note ='"+sr.getNote()+
           "', regime_id ='"+sr.getRegime_id()+"', user_id ='"+sr.getUser_id()+   "' where id='" + sr.getId() + "' ";
       try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
    
        } catch (SQLException ex) {
            Logger.getLogger(SuiviRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
           
        
           
              public List<SuiviRegime> getMesSuivis(int id){
     String requete="SELECT * FROM suivi_regime s , regime r WHERE s.regime_id = r.id and r.user_id = "+id;
     List<SuiviRegime> list = new ArrayList<>();
        try {
            ste = connexion.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) { 
                RegimeService regSer = new RegimeService();
                Regime regime = new Regime();
                regime = regSer.getRegime(rs.getInt("regime_id"));
              
 list.add(new SuiviRegime(rs.getInt("id"), rs.getString("titre"),rs.getString("remarque"),
         rs.getInt("note"),rs.getInt("regime_id"), rs.getInt("user_id"),regime));
//list.add(new SuiviRegime(rs.getInt("id"),rs.getString("titre"),rs.getString("remarque"),rs.getInt("note"),rs.getInt("regime_id"),rs.getInt("user_id"),reg) );              
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
           
    
    
    
    
}
