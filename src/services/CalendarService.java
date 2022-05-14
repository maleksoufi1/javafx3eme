/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entites.Calendar;
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
 * @author Kouki
 */
public class CalendarService {
    private Connection connexion;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet rs;

    public CalendarService() {
         connexion = DataSource.getInstance().getConn();
    }
    
    
            public List<Calendar> getAll(){
     String requete="select * from calendar";
     List<Calendar> list = new ArrayList<>();
        try {
            ste = connexion.createStatement();
           rs= ste.executeQuery(requete);
            while (rs.next()) { 
list.add(new Calendar(rs.getInt("id"), rs.getString("title"), rs.getTimestamp("start"), rs.getTimestamp("end"), rs.getString("description"), rs.getBoolean("allday"),
        rs.getString("background_color"), rs.getString("border_color"), rs.getString("text_color"),rs.getInt("suivi_regime_id"), rs.getBoolean("checked")) );              

            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieRegimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
            
            
                    
             public void addSuiviRegime(Calendar c){
        String requete="insert into calendar (title,start,end,description,allday,background_color,border_color,text_color,suivi_regime_id,checked) values(?,?,?,?,?,?,?,?,?,?)";
            
        try {
            pst = connexion.prepareStatement(requete);
            pst.setString(1, c.getTitle());
            pst.setTimestamp(2, c.getStart());
            pst.setTimestamp(3, c.getEnd());
            pst.setString(4, c.getDescription());
            pst.setBoolean(5, c.isAllday());
     
            pst.setString(6, c.getBackground_color());
            pst.setString(7, c.getBorder_color());
            pst.setString(8, c.getText_color());
            
            pst.setInt(9, c.getSuivi_regime_id());
            pst.setBoolean(10, c.isChecked());
             
             pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CalendarService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
    
    
}
