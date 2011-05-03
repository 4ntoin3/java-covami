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
public class User extends Model {

    @Column(unique = true)
    @Email
    @Required
    public String email;
    
    @Password
    @Required
    public String password;
    
    @Required
    public String fullname;
    
    @OneToMany
    public List<User> friends;
    
    public boolean isAdmin;

    public User(String email, String password, String fullname) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

    public static User connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }
    
    @Override
    public String toString(){
        return email;
    }
}
