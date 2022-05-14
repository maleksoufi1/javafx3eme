/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
 
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import entites.User;
import static GUI.LoginController.u;
//import com.github.plushaze.traynotification.notification.TrayNotification;
import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Duration;
import javax.mail.MessagingException;
import static services.hashing.encrypt;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import utils.DataSource;

/**
 *
 * @author Asus
 */
public class UserService  {
    private Connection connexion;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    public UserService() {
         connexion =DataSource.getInstance().getConn();
    }
//    
//    public void insert(User u) throws Exception{
//         Key key = service.hashing.generateKey();
//         String pwd = encrypt(u.getPassword(),key);
//          
//         String activationToken = encrypt(u.getEmail(),key);
//        String requete= "insert into user (nom,prenom,email,password,activation_token) values('"+u.getNom()+"','"+u.getPrenom()+"','"+u.getEmail()+"','"+pwd+"','"+activationToken+"')";
//        
//       
//        try {
//            ste=connexion.createStatement();
//            ste.executeUpdate(requete);
//        } catch (SQLException ex) {
//            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
    
     public User signIn(String email , String pwd){
           Argon2 argon2 = Argon2Factory.create();
            
                // Hash password
                String hash = argon2.hash(10, 65536, 1, pwd);
        String req = "select * from user ";
        ObservableList <User> list=FXCollections.observableArrayList();
         try {
            ste=connexion.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
                list.add(new User(rs.getInt("id"),rs.getString("email"),rs.getString("password"),rs.getString("roles")));
            }
        } catch (SQLException ex) {
             Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
                 System.out.println(list.size());

        for (int i=0; i<list.size();i++){
            if(email.equals(list.get(i).getEmail()) ){
                if(argon2.verify(hash,pwd)){
                    return list.get(i);
                }
                else return null;
            }
        } 
        return null;
    }   
     public User signInEmail(String email){
        String req = "select * from user ";
        ObservableList <User> list=FXCollections.observableArrayList();
         try {
            ste=connexion.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
                list.add(new User(rs.getInt("id"),rs.getString("email"),rs.getString("password"),rs.getString("roles")));
            }
        } catch (SQLException ex) {
             Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
                 System.out.println(list.size());

        for (int i=0; i<list.size();i++){
            if(email.equals(list.get(i).getEmail()) ){
               
                    return list.get(i);
               
            }
             
                else return null;
        } 
        return null;
    }   
   
     public void insertPst(User u) throws Exception{
       

            // Create instance
            Argon2 argon2 = Argon2Factory.create();

            // Read password from user
            String password = u.getPassword();
                // Hash password
                String hash = argon2.hash(10, 65536, 1, password);

               char quotes ='"';
          Key key = services.hashing.generateKey();
         //String pwd = encrypt(u.getPassword(),key);
             String activation = encrypt(u.getEmail(),key);
    String  rolesJson = "["+quotes+activation+quotes+"]";
        String requete = "insert into user (nom,prenom,email,password,sexe,tel,role,roles,activation_token) values (?,?,?,?,?,?,?,?,?)";
        try {
            pst =  connexion.prepareStatement(requete);
            pst.setString(1, u.getNom());
            System.out.println(rolesJson);
             pst.setString(2, u.getPrenom());
               pst.setString(3, u.getEmail());
               pst.setString(4, hash);
                
                   pst.setString(5, u.getSexe());
                     pst.setString(6, u.getTel());
                     
                   pst.setString(7, activation);
                     pst.setString(8, rolesJson);
                         pst.setString(9, activation);
                     
            pst.executeUpdate();
            String titles = "Client ajouté ";
                String msgs = "avec succées";
                TrayNotification trays = new TrayNotification();
                AnimationType types = AnimationType.SLIDE;
                trays.setAnimationType(types);
                trays.setTitle(titles);
                trays.setMessage(msgs);
                trays.showAndDismiss(Duration.seconds(10));
                trays.setNotificationType(NotificationType.SUCCESS);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    public void AjouterUser(User u) throws Exception{
       

            // Create instance
            Argon2 argon2 = Argon2Factory.create();

            // Read password from user
            String password = u.getPassword();
                // Hash password
                String hash = argon2.hash(10, 65536, 1, password);

               
          Key key = services.hashing.generateKey();
         //String pwd = encrypt(u.getPassword(),key);
             String activation = encrypt(u.getEmail(),key);
        String requete = "insert into user (nom,prenom,email,password,sexe,tel,role,roles) values (?,?,?,?,?,?,?,?)";
        try {
            pst =  connexion.prepareStatement(requete);
            pst.setString(1, u.getNom());
            
             pst.setString(2, u.getPrenom());
               pst.setString(3, u.getEmail());
               pst.setString(4, hash);
                
                   pst.setString(5, u.getSexe());
                     pst.setString(6, u.getTel());
                     
                   pst.setString(7, "client");
                     pst.setString(8, "[\"ROLE_CLIENT\"]");
                         
                     
            pst.executeUpdate();
            String titles = "Client ajouté ";
                String msgs = "avec succées";
                TrayNotification trays = new TrayNotification();
                AnimationType types = AnimationType.SLIDE;
                trays.setAnimationType(types);
                trays.setTitle(titles);
                trays.setMessage(msgs);
                trays.showAndDismiss(Duration.seconds(10));
                trays.setNotificationType(NotificationType.SUCCESS);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
 
  
    public List<User> getAll(){
        String requete ="select * from User";
        List<User> list= new ArrayList<>();
        try {
            ste =connexion.createStatement();
                    rs=ste.executeQuery(requete);
                    while (rs.next()){
                    list.add(new User(rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("password")));
                    }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

     public List<User> getpwd(String s) throws SQLException {
        List<User> UtilisateurList = new ArrayList<>();
        PreparedStatement pre = connexion.prepareStatement("SELECT * FROM `user`  WHERE  email = ?");
        pre.setString(1, s);

        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String telephone = rs.getString("tel");
            String role = rs.getString("role");

            User utilisateur = new User(id, nom, prenom, email, password, telephone, role);
            UtilisateurList.add(utilisateur);
        }

        return UtilisateurList;
    }
   
    public void delete(int id){
        String requete = "delete from user where id = ?";
        try {
            pst=connexion.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(User u){
      String requete="UPDATE user SET nom = '"+u.getNom()+"' , prenom ='"+u.getPrenom()+"', email ='"+u.getEmail()+"' where id='" + u.getId() + "' ";
       try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
    
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     public ObservableList<User> readAllClients() {
        String req = "select* from user where role = 'client'";
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            ste = connexion.createStatement();
            rs = ste.executeQuery(req);

            while (rs.next()) {
list.add(new User(rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("tel")));
  
            };
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        public String calculer_nbm(String mail) {
        String l = null ;
        String requete ="SELECT COUNT(*) FROM user where email='"+mail+"'"; 
        try {
           
           Statement ste =connexion.createStatement();
           ResultSet rs=ste.executeQuery(requete);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
          l=chaine;
            return l;}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
      return l;
    }
          
    public void modifierProfile(User c) throws Exception{
        
           // Create instance
            Argon2 argon2 = Argon2Factory.create();

            // Read password from user
            String password = u.getPassword();
                // Hash password
                String hash = argon2.hash(10, 65536, 1, password);

               
         Key key = services.hashing.generateKey();
         String pwd = encrypt(c.getPassword(),key);
        String req="update user set email = ?  , nom = ? , prenom = ?, tel = ?''  where id = ?";
        try {
            pst=connexion.prepareStatement(req);

            pst.setString(1, c.getEmail());
        
            pst.setString(2,c.getNom());
            pst.setString(3,c.getPrenom());

          
            pst.setString(4,c.getTel());
   
            
            pst.setInt(6,c.getId());

            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  public User activationToken(String email ){
        String req = "select * from user where email = '"+email+"'";
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            ste = connexion.createStatement();
            rs = ste.executeQuery(req);

            while (rs.next()) {
    list.add(new User(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("password"),rs.getString("tel"),rs.getString("role")));
            };
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list.get(0);
    }
    public User getClientById(int id ){
        String req = "select * from user where id = "+id+"";
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            ste = connexion.createStatement();
            rs = ste.executeQuery(req);

            while (rs.next()) {
    list.add(new User(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("password"),rs.getString("tel"),rs.getString("role")));
            };
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list.get(0);
    }
     public void ConfirmAccount(String email, String ac ) {
        String req2 = "UPDATE `user` SET role=? , roles=?, activation_token=null  where email=? and role=? ";

        try {
            PreparedStatement st1 = connexion.prepareStatement(req2);
           
            st1.setString(1, "client");
            st1.setString(2, "[\"ROLE_CLIENT\"]");
            st1.setString(3, email);
            st1.setString(4, ac);
            EmailAttachmentSender.sendEmailWithAttachments(email, "Compte Adomifit confirmé ", "<h1> "
                    + "Cher utilisateur,</h1></br> <p>Votre compte a été activé."
                    + ".</p></br></br> <h4>Merci pour votre confiance,</h4> </br> <h3>"
                    + " L'équipe de Adomifit +</h3>");
            st1.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Boolean Checkconfirmationtoken(String email, String code) {
        boolean exisit = false;
        try {
            String req = "select * from user where email=? and activation_token=? ";
            PreparedStatement st = connexion.prepareStatement(req);
            st.setString(1, email);
            st.setString(2, code);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                exisit = true;

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return exisit;
    }

    public List<User> getcode(String s) throws SQLException {
        List<User> UtilisateurList = new ArrayList<>();
        PreparedStatement pre = connexion.prepareStatement("SELECT * FROM `user`  WHERE  email = ?  ");

        pre.setString(1, s);

        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String telephone = rs.getString("tel");
            String role = rs.getString("role");
            String code = rs.getString("activation_token");

            User utilisateur = new User(id, nom, prenom, email, password, telephone, role, code);
            UtilisateurList.add(utilisateur);
        }

        return UtilisateurList;
    }
        public void banUtilisateurs(String email){
       String req2 = "UPDATE `user` SET role=?,roles=?   where email=?  ";

        try {
            PreparedStatement st1 = connexion.prepareStatement(req2);
           
            st1.setString(1, "bloque");
          st1.setString(2, "[\"ROLE_BLOQUE\"]");
            st1.setString(3, email);
            
            EmailAttachmentSender.sendEmailWithAttachments(email, "Compte Adomifit confirmé ", "<h1> "
                    + "Cher utilisateur,</h1></br> <p>Votre compte a été activé."
                    + ".</p></br></br> <h4>Merci pour votre confiance,</h4> </br> <h3>"
                    + " L'équipe de Adomifit +</h3>");
            st1.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    public void dbanUtilisateurs(String email){
       String req2 = "UPDATE `user` SET role=?,roles=?   where email=?  ";

        try {
            PreparedStatement st1 = connexion.prepareStatement(req2);
           
            st1.setString(1, "client");
          st1.setString(2, "[\"ROLE_CLIENT\"]");
            st1.setString(3, email);
            
            EmailAttachmentSender.sendEmailWithAttachments(email, "Compte Adomifit confirmé ", "<h1> "
                    + "Cher utilisateur,</h1></br> <p>Votre compte a été activé."
                    + ".</p></br></br> <h4>Merci pour votre confiance,</h4> </br> <h3>"
                    + " L'équipe de Adomifit +</h3>");
            st1.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
          
      }
   
    
   }
