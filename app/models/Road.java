/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.*;
import play.db.jpa.Model;

/**
 *
 * @author Antoine
 */
@Entity
public class Road extends Model{
    
    public String name;
    
    @ManyToOne
    public City firstCity;
    
    @ManyToOne
    public City secondCity;
        
    public Road(String name, City firstCity, City secondCity) {
        this.name = name;
        this.firstCity = firstCity;
        this.secondCity = secondCity;
    }
    
    public Double distance(){
        return new Double(Math.sqrt(Math.pow((secondCity.latitude - firstCity.latitude), 2) + Math.pow((secondCity.longitude - firstCity.longitude), 2)));       
    }
}
