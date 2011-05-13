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
public class Friend extends Model{
    
    @ManyToOne
    @Required
    public User user;
    
    @Min(0)
    @Max(2)
    @Required
    public Integer status;
    
    @Required
    public Date date;
    
    public Friend(User user, Integer status, Date date){
        this.user = user;
        this.status = status;
        this.date = date;
    }
}