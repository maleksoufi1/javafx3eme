/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Billet;
import entites.Evenement;
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
public class BilletService {
    private Connection connexion;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet rs;

    public BilletService() {
        connexion = DataSource.getInstance().getConn();
    }
    
         public List<Billet> getAll(){
     String requete="select * from billet";
     List<Billet> list = new ArrayList<>();
        try {
            ste = connexion.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) { 
                 EvenementService ss = new EvenementService();
                Evenement evenement = new Evenement();
                evenement =  ss.getEvenement(rs.getInt("evenement_id"));
                list.add(new Billet(rs.getInt("id"),rs.getDouble("numero"),rs.getString("detail"),rs.getDate("horaire"),rs.getInt("quantite"),
               rs.getInt("evenement_id"),evenement) );              
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        
        
             public void addBillet(Billet b){
                 
                // LocalDateTime now = LocalDateTime.now();
                 //r.setHorraire(now );
String requete="insert into billet (numero,detail,quantite,evenement_id) values('"+b.getNumero()+
        "','"+b.getDetail()+"','"+b.getQuantite()+"','"+b.getEvenement_id()+ "')";
        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
             
                 
        public void deleteBillet(int id) {
     
         String requete = "DELETE FROM billet WHERE id ="+id;
        
           try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
            public  Billet getBillet(int id) {
     
         String requete = "select * FROM billet WHERE id = '" + id + "'";
        Billet b = new Billet();
        
           try {
            ste = connexion.createStatement();
                       rs= ste.executeQuery(requete);
            while (rs.next()) { 
      
                
                 b.setId(id);
                 b.setNumero(rs.getInt("numero"));
                 b.setDetail(rs.getString("detail"));
                 
                 b.setQuantite(rs.getInt("quantite"));
                 b.setEvenement_id(rs.getInt("evenement_id"));
                 
            
                 
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
            return b;
    }
            
            
               public void updateBillet(Billet b){
      String requete="UPDATE billet SET numero = '"+b.getNumero()+"' , detail ='"+b.getDetail()+"', evenement_id ='"+b.getEvenement_id()+
              "' where id='" + b.getId() + "' ";
       try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
    
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
}
