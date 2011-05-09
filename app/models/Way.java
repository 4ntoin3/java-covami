/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 *
 * @author Antoine
 */
@Entity
public class Way extends Model {
    
    @ManyToOne
    @Required
    public City startCity;
    
    @ManyToOne
    @Required
    public City finishCity;
    
    @ManyToOne
    @Required
    public User driver;
    
    @ManyToMany
    public List<User> passengers;
    
    @Required
    public Double distance;
    
    @Required
    public Date dateHourStart;
    
    @ManyToOne
    @Required
    public Car car;
    
    @Required
    public Integer placeAvailable;

    public Way(City startCity, City finishCity, User driver, Double distance, Date dateHourStart, Car car, Integer placeAvailable) {
        this.startCity = startCity;
        this.finishCity = finishCity;
        this.driver = driver;
        this.passengers = new ArrayList<User>();
        this.distance = distance;
        this.dateHourStart = dateHourStart;
        this.car = car;
        this.placeAvailable = placeAvailable;
    }    
}
