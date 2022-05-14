/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import services.*;
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
public class CategorieService {
    private Connection connexion;
    private Statement ste;
        private PreparedStatement pst;
        private ResultSet rs;


    public CategorieService() {
        connexion=Connexion.getInstance().getConnection();    
    }
    public void insert(CategorieProgramme p){
        String requete="insert into categorie_programme (libelle,description,image) values ('"+p.getLibelle()+"','"+p.getDescription()+"','"+p.getImage()+"'   )";
        try {
            ste=connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertPst(CategorieProgramme p){
        String requete="insert into categorie_programme (libelle,description) values (?,?)";
        try {
            pst=connexion.prepareStatement(requete);
            pst.setString(1, p.getLibelle());
            pst.setString(2, p.getDescription());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
       public List AllCatNames() {
        List<String> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT libelle FROM categorie_programme";
            PreparedStatement st = Connexion.getInstance().getConnection().prepareStatement(requete3);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String names = rs.getString("libelle");
                myList.add(names);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
       }
       
       
       
       public int rechercheParCat(String test) {
        int idcat = 0;
        try {
            String query = "SELECT id FROM categorie_programme WHERE libelle=? ";
            PreparedStatement pst = Connexion.getInstance().getConnection().prepareStatement(query);
            pst.setString(1, test);
            ResultSet rs = pst.executeQuery();
            rs.first();
            idcat = rs.getInt(1);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return idcat;
    }
       
    public List<CategorieProgramme> getAll(){
        String requete ="select * from categorie_programme ";
        List<CategorieProgramme> list=new ArrayList<>();
        
        try {
            ste=connexion.createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()) {
                list.add(new CategorieProgramme(rs.getInt("id"), rs.getString("libelle"), rs.getString("description")));
            }
                
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
            
    }
    
     public ObservableList <CategorieProgramme> liste2()
    {
        
        String req = "select id, libelle, description,image from categorie_programme"; 
        
       ObservableList <CategorieProgramme> list =FXCollections.observableArrayList(); 
       try {
       ste = connexion.createStatement(); 
       rs=ste.executeQuery(req); 
       CategorieProgramme CategorieProgramme;
       
       while (rs.next())
       {ImageView iv = new ImageView(new Image(this.getClass().getResourceAsStream(rs.getString("image"))));
           list.add(new CategorieProgramme(rs.getInt("id"), rs.getString("libelle"), rs.getString("description"), rs.getString("image"), iv ,new Button("affiche"))); 
       }
       
       }
       catch (SQLException ex)
       {
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
      "jdbc:mysql://localhost:3306/adomifit?useSSL=false", "root", "");
      //étape 3: créer l'objet statement 
      Statement stmt = conn.createStatement();
      //étape 4: exécuter la requête
      System.out.println("Suppression...");
      String sql = "DELETE FROM categorie_programme WHERE id ="+id;
      stmt.executeUpdate(sql);
      System.out.println("L'enregistrement avec l'id =  a été supprimer avec succès...");
      //étape 5: fermez l'objet de connexion
      conn.close();
    }
    catch(Exception e){ 
      System.out.println(e);
    }
  }
    
      
       public ObservableList<CategorieProgramme> search(String s){
        String requete ="select * from categorie_programme where libelle LIKE '%" + s + "%' OR description LIKE '%" + s + "%'   " ;
                    ObservableList <CategorieProgramme> listf =FXCollections.observableArrayList();

        try {
            ste=connexion.createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()) {
                
                       ImageView iv = new ImageView(new Image(this.getClass().getResourceAsStream(rs.getString("image"))));

               listf.add(new CategorieProgramme(rs.getInt("id"), rs.getString("libelle"), rs.getString("description"), rs.getString("image"), iv) );
            }
                
            
        } catch (SQLException ex) {
            Logger.getLogger(ProgrammeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listf;
            
    }
    
      
       
   
    
    
    public void update(CategorieProgramme p) {
            
         
        try {
                    String sql="UPDATE categorie_programme SET libelle = ?, description = ?,image = ? WHERE id = ? ";
            
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1,p.getLibelle() );
             statement.setString(3, p.getImage());
            statement.setString(2,p.getDescription());
            statement.setInt(4, p.getId());

           
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing categorie was updated successfully!");
            }       } catch (SQLException ex) {
            Logger.getLogger(CategorieProgramme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
          public ObservableList <Programme> ListeByCat(int id)
    {
         
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
 
    
            
    
    
}
