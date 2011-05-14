package models;

import java.util.*;
import javax.persistence.*;
import play.data.validation.*;

import play.db.jpa.*;

/**
 *
 * @author Antoine
 */
@Entity
public class FriendShip extends Model{
    
    @ManyToOne
    @Required
    public User friend;
    
    @OneToOne
    public User user;
    
    @Min(0)
    @Max(2)
    @Required
    public Integer status;
    
    @Required
    public Date date;
    
    public FriendShip(User friend, User user, Integer status, Date date){
        this.friend = friend;
        this.user = user;
        this.status = status;
        this.date = date;
    }
}