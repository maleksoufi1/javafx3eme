/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package adomifitdesktop;

import java.security.Key;
import static services.hashing.encrypt;

/**
 *
 * @author Kouki
 */
public class AdomifitDesktop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        char quotes ='"';
         Key key = services.hashing.generateKey();
         //String pwd = encrypt(u.getPassword(),key);
             String activation = encrypt("geagaeg",key);
             String  rolesJson = "["+quotes+activation+quotes+"]";
             System.out.println(rolesJson);
    }
    
}
