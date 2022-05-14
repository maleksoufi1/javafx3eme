/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entites.Calendar;
import entites.Repas;
import java.sql.Connection;
import java.sql.Date;
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
public class RepasService {
    private Connection connexion;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet rs;

    public RepasService() {
         connexion = DataSource.getInstance().getConn();
    }
      public List<Repas> getAll(){
     String requete="select * from calendar";
     List<Repas> list = new ArrayList<>();
        try {
            ste = connexion.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) { 
list.add(new Repas(rs.getInt("id"), rs.getString("title"), rs.getTimestamp("start"), rs.getTimestamp("end"), rs.getString("description"), rs.getInt("allday"),
        rs.getString("background_color"), rs.getString("border_color"), rs.getString("text_color"),rs.getInt("suivi_regime_id"), rs.getInt("checked")) );              

            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      
        public List<Repas> getAllCalendarSuivi(int id){
     String requete="select * from calendar where suivi_regime_id = "+id;
     List<Repas> list = new ArrayList<>();
     String  toujour;
        try {
            ste = connexion.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) {
                  if(rs.getInt("allday") == 0){
                     
             toujour = "Non";
        }else{
             toujour = "Oui";
        }
list.add(new Repas(rs.getInt("id"), rs.getString("title"), rs.getTimestamp("start"), rs.getTimestamp("end"), rs.getString("description"), rs.getInt("allday"),
        rs.getString("background_color"), rs.getString("border_color"), rs.getString("text_color"),rs.getInt("suivi_regime_id"), rs.getInt("checked"),toujour) );              

            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       
          public void addRepas(Repas c){
        String requete="insert into calendar (title,start,end,description,allday,background_color,border_color,text_color,suivi_regime_id,checked) values(?,?,?,?,?,?,?,?,?,?)";
            
        try {
            pst = connexion.prepareStatement(requete);
            pst.setString(1, c.getTitle());
            pst.setTimestamp(2,  c.getStart());
            pst.setTimestamp(3,  c.getEnd());
            pst.setString(4, c.getDescription());
            pst.setInt(5, c.getAllday());
     
            pst.setString(6, c.getBackground_color());
            pst.setString(7, c.getBorder_color());
            pst.setString(8, c.getText_color());
            
            pst.setInt(9, c.getSuivi_regime_id());
            pst.setInt(10, c.getChecked());
             
             pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CalendarService.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
          
          
          
             public void deleteRepas(int id) {
     
         String requete = "DELETE  FROM calendar WHERE id ="+id;
        
           try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(RepasService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
