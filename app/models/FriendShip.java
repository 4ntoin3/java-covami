package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;

/**
 *
 * @author Antoine
 */
@Entity
public class FriendShip extends Model{
    
    @ManyToOne
    public User friend;
    
    @OneToOne
    public User user;
    
    public Integer status;
    
    public Date date;
    
    public FriendShip(User friend, User user, Integer status, Date date){
        this.friend = friend;
        this.user = user;
        this.status = status;
        this.date = date;
    }
}