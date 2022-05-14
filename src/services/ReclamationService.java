/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author Asus
 */
public class ReclamationService {
    private Connection conn;
    private  Statement st;
    private PreparedStatement ps;
    private ResultSet rs;
    public ReclamationService() 
    {
       conn=DataSource.getInstance().getConn();
    }
    
    public void InsertCommentaire(Reclamation p)
    {
        p.setCreatedAt( new Timestamp(System.currentTimeMillis()));
       
            String req="insert into Reclamation(titre,contenu,created_at,user_id) values('"+p.getTitre()+"','"+p.getContenu()+"','"+p.getCreatedAt()+"','"+p.getId_user()+"')";
            try {
            st=conn.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
//   / public  void insertPst(Reclamation p)
//    {
//           String req="insert into Reclamation(titre,contenu,createdAt) values()";
//        try
//        {
//           ps=conn.prepareStatement(req);
//        ps.setString(1,p.getTitre());
//         ps.setString(2,p.getContenu());
//        
//         ps.executeUpdate();
//        } catch(SQLException ex)
//        {
//        }
   // }
     public List<Reclamation> getAll()
     {
         List<Reclamation> list=new ArrayList<>();
      String  requete="select * from Reclamation";
      try
      {
        st=conn.createStatement();
        rs=st.executeQuery(requete);
        while(rs.next())
        {
            
            list.add(new Reclamation(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getTimestamp(5)));
        }
        
       
        
      }catch(SQLException ex)
      {
         
      }
      return (list);
     }
     
     
      public List<Reclamation> MesReclamations()
     {
         List<Reclamation> list=new ArrayList<>();
         int myId=1;
      String  requete="select * from Reclamation where user_id="+myId;
      try
      {
        st=conn.createStatement();
        rs=st.executeQuery(requete);
        while(rs.next())
        {
            
            list.add(new Reclamation(rs.getInt(1),rs.getString(3),rs.getString(4),rs.getTimestamp(5)));
        }
        
       
        
      }catch(SQLException ex)
      {
        
      }
      return (list);
     }
     
      public void ModifierReclamation(Reclamation p)
    {
        p.setCreatedAt( new Timestamp(System.currentTimeMillis()));
            String req="UPDATE reclamation  SET titre = '"+p.getTitre()+"', contenu = '"+p.getContenu()+"' WHERE id ="+p.getId();
            try {
            st=conn.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
      public void SupprimerReclamation(int id)
      {
      
          String req="DELETE FROM reclamation WHERE id ="+id;
            try {
            st=conn.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    
}
