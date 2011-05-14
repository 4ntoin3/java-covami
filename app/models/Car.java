package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.data.validation.*;
import play.db.jpa.Model;

/**
 *
 * @author Antoine
 */
@Entity
public class Car extends Model {

    @Required
    public String name;
    
    @Min(1)
    @Required
    public Integer nbPlace; 
    
    @Min(0)
    @Required
    public Integer cost;
    
    @Required
    @ManyToOne
    public User owner;

    public Car(String name, Integer nbPlace, Integer cost, User owner) {
        this.name = name;
        this.nbPlace = nbPlace;
        this.cost = cost;
        this.owner = owner;
    }
}
