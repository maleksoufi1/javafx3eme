/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author Asus
 */
public class User {
       private int id;
    private String nom;
   private String prenom; 
   private String email; 
   private String password; 
   private String role; 
   private String roles; 
   private String photo; 
   private int poid; 
   private int taille ; 
   private String sexe; 
   private String tel; 
   private  String activation_token;

    public String getActivation_token() {
        return activation_token;
    }

    public void setActivation_token(String activation_token) {
        this.activation_token = activation_token;
    }

    public User(int id, String nom, String prenom, String email, String password, String tel, String role, String activation_token) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.tel = tel;
        this.activation_token = activation_token;
    }
   
    public User(int id, String nom, String prenom, String email, String password, String tel, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.role = role;
    }

    public User() {
    }

    public User(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
 public String isRole() {
        return role;
    }
  public String isActivation() {
        return activation_token;
    }
    public User(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
         this.password = password;
    }

    public User(int id, String nom, String prenom, String email, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

    public User(int id, String email, String password, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }
   
   
   

    public User(int id, String nom, String prenom, String email, String password, String role, String roles, String photo, int poid, int taille, String sexe, String tel,String activation_token) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
        this.roles = roles;
        this.photo = photo;
        this.poid = poid;
        this.taille = taille;
        this.sexe = sexe;
        this.tel = tel;
         this.activation_token = activation_token;
    }

    public User(String nom, String prenom, String email, String password, String sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.sexe = sexe;
    } 
    
  

    public User(String nom, String prenom, String email, String password,  String sexe, String tel,String role , String roles, String activation_token) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
     
        this.sexe = sexe;
        this.tel = tel;
        this.role= role;
        this.roles=roles;
        this.activation_token= activation_token;
    }

  

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
  
    public String getPrenom() {
        return prenom;
    }

    public User(String activation_token) {
        this.activation_token = activation_token;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getRoles() {
        return roles;
    }

    public String getPhoto() {
        return photo;
    }

    public int getPoid() {
        return poid;
    }

    public int getTaille() {
        return taille;
    }

    public String getSexe() {
        return sexe;
    }

    public String getTel() {
        return tel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", role=" + role + ", roles=" + roles + ", photo=" + photo + ", poid=" + poid + ", taille=" + taille + ", sexe=" + sexe + ", tel=" + tel + '}';
    }
   
   
}
