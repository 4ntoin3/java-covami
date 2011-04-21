/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    @Required
    public String password;
    
    @Required
    public String fullname;
    public boolean isAdmin;

    public User(String email, String password, String fullname) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

    public static User connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }
    
    public String toString(){
        return email;
    }
}
