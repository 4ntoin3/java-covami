/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.*;
import org.joda.time.DateTime;
import play.db.jpa.Model;

/**
 *
 * @author Antoine
 */
@Entity
public class WayParticipation extends Model {

    @ManyToOne
    public Way way;
   
    @ManyToOne
    public User participant;
    
    public Integer status;
    
    public DateTime dateHourRequest;

    public WayParticipation(Way way, User participant, Integer status) {
        this.way = way;
        this.participant = participant;
        this.status = status;
        this.dateHourRequest = new DateTime();
    }
}
