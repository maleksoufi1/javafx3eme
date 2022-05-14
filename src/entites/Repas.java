/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.Objects;

/**
 *
 * @author Kouki
 */
public class Repas {
     private int id; 
     private String title;
     private Timestamp start;
     private Timestamp end;
     
     private String description;
     private int allday;
     private String background_color;
     private String border_color;
     private String text_color;
     private int suivi_regime_id;
     private int checked;
     
     private String toujour;

    public Repas() {
    }

    public Repas(int id, String title, Timestamp start, Timestamp end, String description, int allday, String background_color, String border_color, String text_color, int suivi_regime_id, int checked) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.allday = allday;
        this.background_color = background_color;
        this.border_color = border_color;
        this.text_color = text_color;
        this.suivi_regime_id = suivi_regime_id;
        this.checked = checked;
    }

    public Repas(String title, Timestamp start, Timestamp end, String description, int allday, String background_color, String border_color, String text_color, int suivi_regime_id, int checked) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.allday = allday;
        this.background_color = background_color;
        this.border_color = border_color;
        this.text_color = text_color;
        this.suivi_regime_id = suivi_regime_id;
        this.checked = checked;
    }

    public Repas(int id, String title, Timestamp start, Timestamp end, String description, int allday, String background_color, String border_color, String text_color, int suivi_regime_id) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.allday = allday;
        this.background_color = background_color;
        this.border_color = border_color;
        this.text_color = text_color;
        this.suivi_regime_id = suivi_regime_id;
    }

    public Repas(String title, Timestamp start, Timestamp end, String description, int allday, String background_color, String border_color, String text_color, int suivi_regime_id) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.allday = allday;
        this.background_color = background_color;
        this.border_color = border_color;
        this.text_color = text_color;
        this.suivi_regime_id = suivi_regime_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAllday() {
        return allday;
    }

    public void setAllday(int allday) {
        this.allday = allday;
    }

   

    public String getBackground_color() {
        return background_color;
    }

    public void setBackground_color(String background_color) {
        this.background_color = background_color;
    }

    public String getBorder_color() {
        return border_color;
    }

    public void setBorder_color(String border_color) {
        this.border_color = border_color;
    }

    public String getText_color() {
        return text_color;
    }

    public void setText_color(String text_color) {
        this.text_color = text_color;
    }

    public int getSuivi_regime_id() {
        return suivi_regime_id;
    }

    public void setSuivi_regime_id(int suivi_regime_id) {
        this.suivi_regime_id = suivi_regime_id;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

 

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Repas other = (Repas) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.start, other.start)) {
            return false;
        }
        return Objects.equals(this.end, other.end);
    }

    @Override
    public String toString() {
        return "Repas{" + "id=" + id + ", title=" + title + ", start=" + start + ", end=" + end + ", description=" + description + ", allday=" + allday + ", background_color=" + background_color + ", border_color=" + border_color + ", text_color=" + text_color + ", suivi_regime_id=" + suivi_regime_id + ", checked=" + checked + '}';
    }

    public String getToujour() {
      
        return toujour;
    }

    public void setToujour(String toujour) {
        this.toujour = toujour;
    }

    public Repas(int id, String title, Timestamp start, Timestamp end, String description, int allday, String background_color, String border_color, String text_color, int suivi_regime_id, int checked, String toujour) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.allday = allday;
        this.background_color = background_color;
        this.border_color = border_color;
        this.text_color = text_color;
        this.suivi_regime_id = suivi_regime_id;
        this.checked = checked;
        this.toujour = toujour;
    }
     
     
}
