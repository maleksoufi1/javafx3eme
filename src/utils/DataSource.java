/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kouki
 */
public class DataSource {
    private String url="jdbc:mysql://localhost:3306/adomifitt" ;
    private String login="root" ;
    private String pwd="root" ;
    private static DataSource instance;
    private Connection conn;
    
 
    public static final String URL_UPLOAD="jdbc:mysql://localhost:3306/imageServer.php";
    public static  final String URL_Image ="jdbc:mysql://localhost:3306/adomifitt/public/uploads/images/";
    
    private DataSource() {
        try {
            conn = DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion établi");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static DataSource getInstance(){
    if(instance == null){
        instance = new DataSource();
       
    }
     return instance;
    }

    public Connection getConn() {
        return conn;
    }
    
    
    
    
    
}
