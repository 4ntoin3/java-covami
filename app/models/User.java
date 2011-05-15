package models;

import javax.persistence.*;
import play.data.validation.*;

import play.db.jpa.*;

/**
 *
 * @author Antoine
 */
@Entity
public class User extends Model {

    @Email
    @Required
    public String email;
    
    @Password
    @Required
    public String password;
    
    @Required
    public String firstname;
    
    @Required
    public String lastname;
    
//    @OneToMany(cascade=CascadeType.ALL)
//    public List<Friend> friends;
    
    public User(String email, String password, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
//        this.friends = new ArrayList<Friend>();
    }

    public static User connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }
}
