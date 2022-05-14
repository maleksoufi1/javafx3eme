/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.ReponseReclamation;
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
public class ReponseReclamationService 
{
       private Connection conn;
    private  Statement st;
    private PreparedStatement ps;
    private ResultSet rs,rs1;
    public ReponseReclamationService() 
    {
       conn=DataSource.getInstance().getConn();
    }
    
    public void InsertReponse(ReponseReclamation p)
    {
        p.setCreatedAt( new Timestamp(System.currentTimeMillis()));
            String req="insert into Reponse_Reclamation(contenu,created_at,user_id,reclamation_id) values('"+p.getContenu()+"','"+p.getCreatedAt()+"',"+1+","+p.getReclamation_id()+")";
         String req3=" SELECT * FROM `Reponse_Reclamation` order by created_at desc LIMIT 1";
       int id_reponse=0;
            try {
            st=conn.createStatement();
            st.executeUpdate(req);
                rs=st.executeQuery(req3);
                 while(rs.next())
        {
            
          
            id_reponse=rs.getInt("id");
        }
                    String req2="update  Reclamation set reponse_reclamation_id='"+id_reponse+"' where id='"+p.getReclamation_id()+"'";
              st=conn.createStatement();
            st.executeUpdate(req2);
        } catch (SQLException ex) {
            Logger.getLogger(ReponseReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public  void insertPst(ReponseReclamation p)
    {
           String req="insert into ReponseReclamation(titre,contenu,createdAt) values()";
        try
        {
           ps=conn.prepareStatement(req);
       
         ps.setString(2,p.getContenu());
         ps.setTimestamp(2,p.getCreatedAt());
         ps.executeUpdate();
        } catch(SQLException ex)
        {
        }
    }
     public List<ReponseReclamation> getAll()
     {
         List<ReponseReclamation> list=new ArrayList<>();
      String  requete="select r.*,d.user_id from Reponse_Reclamation r join reclamation d on r.id=d.reponse_reclamation_id";
      
      
      
      try
      {
        st=conn.createStatement();
        rs=st.executeQuery(requete);
        while(rs.next())
        {
            
          
            list.add(new ReponseReclamation(rs.getInt(1),rs.getString(3),rs.getTimestamp(4),rs.getInt(5)));
        }
       
        
      }catch(SQLException ex)
      {
          
      }
      return (list);
     }
     
     
     
      public void ModifierReponseReclamation(ReponseReclamation p)
    {
    
            String req="UPDATE Reponse_reclamation  SET  contenu = '"+p.getContenu()+"' WHERE id ="+p.getId();
            try {
            st=conn.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReponseReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
      public void SupprimerReponseReclamation(int id)
      {
      
          String req="DELETE FROM reponse_reclamation WHERE id ="+id;
            try {
            st=conn.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReponseReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
       public List<ReponseReclamation> getUserReponse() 
       {
                List<ReponseReclamation> list=new ArrayList<>();
      String  requete="select r.contenu,r.created_at from Reponse_Reclamation r join reclamation d on r.id=d.reponse_reclamation_id where d.user_id=2";
      try
      {
        st=conn.createStatement();
        rs=st.executeQuery(requete);
        while(rs.next())
        {
            
          
            list.add(new ReponseReclamation(rs.getString("contenu"),rs.getTimestamp("created_at")));
        }
       
        
      }catch(SQLException ex)
      {
          
      }
      return (list);
          
       }
    
}
