/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

/**
 *
 * @author Kouki
 */
public class SuiviRegime {
    private int id; 
    private String titre;
    private String remarque;
    private int note; 
    private int regime_id;
    private int user_id;
    
    private Regime regime;

    public SuiviRegime() {
    }

    public SuiviRegime(int id, String titre, String remarque, int note, int regime_id, int user_id) {
        this.id = id;
        this.titre = titre;
        this.remarque = remarque;
        this.note = note;
        this.regime_id = regime_id;
        this.user_id = user_id;
    }

    public SuiviRegime(String titre, String remarque, int note, int regime_id, int user_id) {
        this.titre = titre;
        this.remarque = remarque;
        this.note = note;
        this.regime_id = regime_id;
        this.user_id = user_id;
    }

    public SuiviRegime(int id, String titre, String remarque, int note, int regime_id, int user_id, Regime regime) {
        this.id = id;
        this.titre = titre;
        this.remarque = remarque;
        this.note = note;
        this.regime_id = regime_id;
        this.user_id = user_id;
        this.regime = regime;
    }

 
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getRegime_id() {
        return regime_id;
    }

    public void setRegime_id(int regime_id) {
        this.regime_id = regime_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "SuiviRegime{" + "id=" + id + ", titre=" + titre + ", remarque=" + remarque + ", note=" + note + ", regime_id=" + regime_id + ", user_id=" + user_id +  ", regime=" + regime + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SuiviRegime other = (SuiviRegime) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.regime_id != other.regime_id) {
            return false;
        }
        return this.user_id == other.user_id;
    }

    public Regime getRegime() {
        return regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }
    
    
    
}
