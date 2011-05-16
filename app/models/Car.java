package models;

import javax.persistence.*;
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
    @Max(9)
    @Required
    public Integer nbPlace; 
    
    @Min(0)
    @Required
    public Integer cost;
    
    @ManyToOne
    public User owner;
    
    public Integer deleted;

    public Car(String name, Integer nbPlace, Integer cost, User owner) {
        this.name = name;
        this.nbPlace = nbPlace;
        this.cost = cost;
        this.owner = owner;
        this.deleted = 0;
    }
}
