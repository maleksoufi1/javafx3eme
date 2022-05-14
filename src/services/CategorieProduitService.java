/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.CategorieProduit;
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
import utils.DataSource;

/**
 *
 * @author MSI
 */
public class CategorieProduitService {
     private Connection connexion;
    private Statement ste;
        private PreparedStatement pst;
        private ResultSet rs;


    public CategorieProduitService() {
        connexion=DataSource.getInstance().getConn();    
    }
    public void insert(CategorieProduit p){
        String requete="insert into categorie_produit (libelle,description,image) values ('"+p.getLibelle()+"','"+p.getDescription()+"','"+p.getImage()+"')";
        try {
            ste=connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  
    
    public List<CategorieProduit> getAll(){
        String requete ="select * from categorie_produit ";
        List<CategorieProduit> list=new ArrayList<>();
        
        try {
            ste=connexion.createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()) {
                list.add(new CategorieProduit(rs.getInt("id"),rs.getString("libelle"),rs.getString("description") ,rs.getString("image")));
            }
                
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
            
    }
    
    public void deleteCat(CategorieProduit r){
         try {
             String sql = "DELETE FROM categorie_produit WHERE id=" + r.getId();
             
           Statement st = DataSource.getInstance().getConn().createStatement();
            st.executeUpdate(sql);
            System.out.println("Produit supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  }
    
    
    
    
       public void update(int id,String libelle, String description) {
       
            
         
        try {
            String sql = "UPDATE categorie_produit SET libelle=?, description=? WHERE id=?";
            
            PreparedStatement statement = connexion.prepareStatement(sql);
            
            statement.setString(1, libelle=libelle);
           
            statement.setString(2, description=description);
            statement.setInt(3, id=id);
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing Produit was updated successfully!");
            }       } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
       public List AllCatNames() {
        List<String> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT libelle FROM categorie_produit";
            PreparedStatement st = DataSource.getInstance().getConn().prepareStatement(requete3);
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
            String query = "SELECT id FROM categorie_produit WHERE libelle=? ";
            PreparedStatement pst = DataSource.getInstance().getConn().prepareStatement(query);
            pst.setString(1, test);
            ResultSet rs = pst.executeQuery();
            rs.first();
            idcat = rs.getInt(1);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return idcat;
    }

    
}
