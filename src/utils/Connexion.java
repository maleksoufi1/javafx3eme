/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
public class Connexion {
    private String url="jdbc:mysql://localhost:3306/adomifitt";
        private String login="root";
    private String pwd="root";
    
    private Connection conn;
    private static Connexion instance;
    
    
    private Connexion() {
    
        try {
            conn=DriverManager.getConnection(url ,login , pwd);
            System.out.println("connected to bd");
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public static Connexion getInstance(){
        if (instance == null)
             instance=new Connexion();
        return instance;
        
    }

    public Connection getConnection(){
        return conn;
    }
    
  

    
}
