/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.joda.time.DateTime;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 *
 * @author Antoine
 */
@Entity
public class WayParticipation extends Model{
    
    @ManyToOne
    @Required
    public Way way;
    
    @OneToMany
    @Required
    public List<User> participants;
    
    @Required
    public DateTime dateHourRequest;
    
    public DateTime dateHourResponse;

    public WayParticipation(Way way, List<User> participants) {
        this.way = way;
        this.participants = new ArrayList<User>();
        this.participants = participants;
        this.dateHourRequest = new DateTime();
    }   
}
