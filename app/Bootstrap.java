
import play.*;
import play.jobs.*;
import play.test.*;
 
import models.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Antoine
 */
@OnApplicationStart
public class Bootstrap extends Job {
    
    public void doJob(){
        //Check if the database is empty
        if(User.count() == 0){
            Fixtures.load("initial-data.yml");
        } 
    }
    
}
