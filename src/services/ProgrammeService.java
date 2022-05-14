/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import GUI.CategorieProgramme;
import entites.Programme;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Connexion;


/**
 *
 * @author MSI
 */
public class ProgrammeService {
    private Connection connexion;
    private Statement ste;
        private PreparedStatement pst;
        private ResultSet rs;


    public ProgrammeService() {
        connexion=Connexion.getInstance().getConnection();    
    }
    public void insert(Programme p){
        String requete="insert into programme (titre,description,categorie_programme_id,image,jaime,jaimepas,abn) values (?,?,?,?,?,?,?)";
        try {
                 
            pst=connexion.prepareStatement(requete);
            pst.setString(1,p.getTitre());
            pst.setString(2, p.getDescription());
            pst.setInt(3, p.getCategorie_programme_id() );
            pst.setString(4, p.getImage());
            pst.setInt(5,0);
            pst.setInt(6,0);
            pst.setInt(7,0);

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProgrammeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
  
    
     public ObservableList <Programme> liste2()
    {
        
        String req = "select id, titre, description, categorie_programme_id,image,jaime,jaimepas,abn from programme"; 
        
       ObservableList <Programme> list =FXCollections.observableArrayList(); 
       try {
       ste = connexion.createStatement(); 
       rs=ste.executeQuery(req); 
       Programme programme;
       
       while (rs.next())
       {ImageView iv = new ImageView(new Image(this.getClass().getResourceAsStream(rs.getString("image"))));
           list.add(new Programme(rs.getInt("id"),rs.getInt("abn"),rs.getInt("jaime"),rs.getInt("jaimepas"), rs.getString("titre"), rs.getString("description"), rs.getInt("categorie_programme_id"), rs.getString("image"), iv )); 
       }
       
       }
       catch (SQLException ex)
       {
       Logger.getLogger(ProgrammeService.class.getName()).log(Level.SEVERE, null, ex);
       }
    return list; 
    }
     
      public ObservableList <Programme> ListeByCat(int id)
    {
         Abn(id);
        String req = "select id, titre, description, categorie_programme_id,image,jaime,jaimepas,abn from programme WHERE categorie_programme_id ="+id; 
        
       ObservableList <Programme> list =FXCollections.observableArrayList(); 
       try {
       ste = connexion.createStatement(); 
       rs=ste.executeQuery(req); 
       Programme programme;
       
       while (rs.next())
       {ImageView iv = new ImageView(new Image(this.getClass().getResourceAsStream(rs.getString("image"))));
           list.add(new Programme(rs.getInt("id"),rs.getInt("abn"),rs.getInt("jaime"),rs.getInt("jaimepas"), rs.getString("titre"), rs.getString("description"), rs.getInt("categorie_programme_id"), rs.getString("image"), iv )); 
       }
       
       }
       catch (SQLException ex)
       {
       Logger.getLogger(ProgrammeService.class.getName()).log(Level.SEVERE, null, ex);
       }
    return list; 
    }
    
     public List<Programme> getById(int id){
        String requete ="select * from programme WHERE categorie_programme_id ="+id;
        List<Programme> list=new ArrayList<>();
        
        try {
            ste=connexion.createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()) {
               list.add(new Programme(rs.getInt("id"), rs.getString("titre"), rs.getString("description"),rs.getInt("categorie_programme_id") ) );
            }
                
            
        } catch (SQLException ex) {
            Logger.getLogger(ProgrammeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
            
    }
    
      public void deleteProduit(int id){
			try
    {
      //étape 1: charger la classe driver
      Class.forName("com.mysql.jdbc.Driver");
      //étape 2: créer l'objet de connexion
      Connection conn = DriverManager.getConnection(
      "jdbc:mysql://localhost:3306/adomifitt?useSSL=false", "root", "");
      //étape 3: créer l'objet statement 
      Statement stmt = conn.createStatement();
      //étape 4: exécuter la requête
      System.out.println("Suppression...");
      String sql = "DELETE FROM programme WHERE id ="+id;
                  PreparedStatement statement = connexion.prepareStatement(sql);

      
      stmt.executeUpdate(sql);
      System.out.println("L'enregistrement avec l'id =  a été supprimer avec succès...");
      //étape 5: fermez l'objet de connexion
      conn.close();
    }
    catch(Exception e){ 
      System.out.println(e);
    }
  }
    
      
    public void update(Programme p) {
            
         
        try {
           
        String sql="UPDATE programme SET titre = ?, description = ?,image = ? WHERE id ="+p.getId();
            
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1,p.getTitre());
            statement.setString(2,p.getDescription());
            statement.setString(3,p.getImage());

           
           
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing categorie was updated successfully!");
            }       } catch (SQLException ex) {
            Logger.getLogger(Programme.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    
    
    
    
    
    
    public ObservableList<Programme> search(String s){
        String requete ="select * from programme where titre LIKE '%" + s + "%' OR description LIKE '%" + s + "%'  " ;
                    ObservableList <Programme> list =FXCollections.observableArrayList();

        try {
            ste=connexion.createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()) {
                
                       ImageView iv = new ImageView(new Image(this.getClass().getResourceAsStream(rs.getString("image"))));

               list.add(new Programme(rs.getInt("id"), rs.getString("titre"), rs.getString("description"),rs.getInt("categorie_programme_id") , rs.getString("image"), iv) );
            }
                
            
        } catch (SQLException ex) {
            Logger.getLogger(ProgrammeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
            
    }
    
    public ObservableList<Programme> searchFront(String s){
        String requete ="select * from programme where titre LIKE '%" + s + "%' OR description LIKE '%" + s + "%' HAVING categorie_programme_id="+Programme.cat;
        System.out.println(Programme.cat);
                    ObservableList <Programme> list =FXCollections.observableArrayList();

        try {
            ste=connexion.createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()) {
                
                       ImageView iv = new ImageView(new Image(this.getClass().getResourceAsStream(rs.getString("image"))));

               list.add(new Programme(rs.getInt("id"), rs.getString("titre"), rs.getString("description"),rs.getInt("categorie_programme_id") , rs.getString("image"), iv) );
            }
                
            
        } catch (SQLException ex) {
            Logger.getLogger(ProgrammeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
            
    }
    
      public void Like(int id) {
            
         
        try {
           
        String sql="UPDATE programme SET jaime=programme.jaime+1 WHERE id ="+id;
            
            PreparedStatement statement = connexion.prepareStatement(sql);

           
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("LIKE!");
            }       } catch (SQLException ex) {
            Logger.getLogger(Programme.class.getName()).log(Level.SEVERE, null, ex);
        }
         Abn(id);
          
           
           

    }
    
      
      public void Dislike(int id) {
            
         
        try {
           
        String sql="UPDATE programme SET jaimepas=programme.jaimepas+1 WHERE id ="+id;
            
            PreparedStatement statement = connexion.prepareStatement(sql);

                  

           
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("DISLIKE :-( ");
            }       } catch (SQLException ex) {
            Logger.getLogger(Programme.class.getName()).log(Level.SEVERE, null, ex);
        }
         Abn(id);
                   ListeByCat(Programme.cat);

 
    }
      
      
      public void Abn(int id) {
            
         
        try {
           
        String sql="UPDATE programme SET abn=programme.jaime*100/(programme.jaimepas+programme.jaime) WHERE id ="+id;
            
            PreparedStatement statement = connexion.prepareStatement(sql);

           
           
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println(" :-( ");
            }       } catch (SQLException ex) {
            Logger.getLogger(Programme.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
}
