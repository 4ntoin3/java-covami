/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    @Required
    public int cost;
    
    @Required
    @ManyToOne
    public User owner;

    public Car(String name, int cost, User owner) {
        this.name = name;
        this.cost = cost;
        this.owner = owner;
    }
}
