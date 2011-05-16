/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import javax.persistence.*;
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
    
    public Date date;

    public WayParticipation(Way way, User participant) {
        this.way = way;
        this.participant = participant;
        this.status = 0;
        this.date = new Date();
    }
}
