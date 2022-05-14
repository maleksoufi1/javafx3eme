/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Produit;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static jdk.nashorn.internal.runtime.Debug.id;
import utils.DataSource;


/**
 *
 * @author MSI
 */
public class ProduitService {
    private Connection connexion;
    private Statement ste;
        private PreparedStatement pst;
        private ResultSet rs;


    public ProduitService() {
        connexion=DataSource.getInstance().getConn();    
    }
    public void insert(Produit p){
        String requete="insert into produit (nom,prix,image,description,categorie_produit_id) values ('"+p.getNom()+"','"+p.getPrix()+"','"+p.getImage()+"','"+p.getDescription()+"','"+p.getCategorie_produit_id()+"')";
        try {
            ste=connexion.createStatement();
            ste.executeUpdate(requete);
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  
    
    public List<Produit> getAll(){
        String requete ="select * from produit ";
        List<Produit> list=new ArrayList<>();
        
        try {
            ste=connexion.createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()) {
                list.add(new Produit(rs.getInt("id"),rs.getInt("prix"),rs.getInt("categorie_produit_id") ,rs.getString("nom") ,rs.getString("image"),rs.getString("description")));
            }
                
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
            
    }
    
    public void deleteProduit(Produit r){
	 try {
             String sql = "DELETE FROM produit WHERE id=" + r.getId();;
             
      Statement st = DataSource.getInstance().getConn().createStatement();
            st.executeUpdate(sql);
            System.out.println("Produit supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    public void update(int id,int prix,String nom, String image, String description) {
       
            
         
        try {
            String sql = "UPDATE produit SET prix=?, nom=?, image=? , description=? WHERE id=?";
            
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setInt(1,prix=prix );
            statement.setString(2, nom=nom);
            statement.setString(3, image=image);
            statement.setString(4, description=description);
            statement.setInt(5, id=id);
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing Produit was updated successfully!");
            }       } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    
    
        public void update2(Produit p) {
            
         
        try {
        
        String sql="UPDATE produit SET nom = ?, description = ?, image = ?, prix = ?,categorie_produit_id = ?  WHERE id = ? ";
            
            PreparedStatement statement = connexion.prepareStatement(sql);
            statement.setString(1,p.getNom() );
             statement.setInt(6, p.getId());
            statement.setString(2,p.getDescription());
            statement.setString(3,p.getImage());
            statement.setInt(4,p.getPrix());
             statement.setInt(5,p.getCategorie_produit_id());
           
           
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing user was updated successfully!");
            }       } catch (SQLException ex) {
            Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
         public ObservableList<Produit> afficheProduit() {
        ObservableList<Produit> myList = FXCollections.observableArrayList();

        try {
            String requete5 = "SELECT * FROM produit ";
            Statement st = DataSource.getInstance().getConn().createStatement();
            ResultSet rs = st.executeQuery(requete5);
            while (rs.next()) {
                Produit p = new Produit();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrix(rs.getInt("prix"));
                p.setImage(rs.getString("image"));
                
                p.setDescription(rs.getString("description"));
                p.setCategorie_produit_id(rs.getInt("categorie_produit_id"));
                myList.add(p);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }
      
          public ObservableList<Produit> trier() {
        ObservableList<Produit> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM produit ";
            PreparedStatement pst = DataSource.getInstance().getConn().prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Produit(rs.getInt("id"),rs.getInt("prix"),rs.getInt("categorie_produit_id") ,rs.getString("nom") ,rs.getString("image"),rs.getString("description")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //TRI
        Collections.sort(list, new MyComparatorProduit());

        return list;
    }
          class MyComparatorProduit implements Comparator<Produit> {

        @Override
        public int compare(Produit o1, Produit o2) {
            return o1.getNom().compareTo(o2.getNom());

        }
        
        
      
     



}
}
               

