/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;

/**
 *
 * @author myria
 */
public class Billet {
    private int id; 
    private double numero;
    private String detail;
    private Date horaire;
    private int quantite;
    private int evenement_id;
    private Evenement evenement;

    public Billet() {
    }

    public Billet(int id, double numero, String detail, Date horaire, int quantite, int evenement_id) {
        this.id = id;
        this.numero = numero;
        this.detail = detail;
        this.horaire = horaire;
        this.quantite = quantite;
        this.evenement_id = evenement_id;
    }

    public Billet(double numero, String detail, Date horaire, int quantite, int evenement_id) {
        this.numero = numero;
        this.detail = detail;
        this.horaire = horaire;
        this.quantite = quantite;
        this.evenement_id = evenement_id;
    }

    public Billet(int id, double numero, String detail, int quantite, int evenement_id) {
        this.id = id;
        this.numero = numero;
        this.detail = detail;
        this.quantite = quantite;
        this.evenement_id = evenement_id;
    }

    public Billet( double numero, String detail, int quantite, int evenement_id) {
       
        this.numero = numero;
        this.detail = detail;
        this.quantite = quantite;
        this.evenement_id = evenement_id;
    }

    public Billet(int id, double numero, String detail, Date horaire, int quantite, int evenement_id, Evenement evenement) {
        this.id = id;
        this.numero = numero;
        this.detail = detail;
        this.horaire = horaire;
        this.quantite = quantite;
        this.evenement_id = evenement_id;
        this.evenement = evenement;
    }

    public Billet(double numero, String detail, Date horaire, int quantite, int evenement_id, Evenement evenement) {
        this.numero = numero;
        this.detail = detail;
        this.horaire = horaire;
        this.quantite = quantite;
        this.evenement_id = evenement_id;
        this.evenement = evenement;
    }

    public Billet(int id, double numero, String detail, Date horaire, int evenement_id, Evenement evenement) {
        this.id = id;
        this.numero = numero;
        this.detail = detail;
        this.horaire = horaire;
        this.evenement_id = evenement_id;
        this.evenement = evenement;
    }

   

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getHoraire() {
        return horaire;
    }

    public void setHoraire(Date horaire) {
        this.horaire = horaire;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }
    
     public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    @Override
    public String toString() {
        return "Billet{" + "id=" + id + ", numero=" + numero + ", detail=" + detail + ", horaire=" + horaire + ", quantite=" + quantite + ", evenement_id=" + evenement_id + ", evenement=" + evenement + '}';
    }

    
    
}
