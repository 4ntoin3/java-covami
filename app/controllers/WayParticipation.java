package controllers;

import play.mvc.Controller;
import play.mvc.With;

/**
 *
 * @author pierregaste
 */
@With(Secure.class)
public class WayParticipation extends Controller {

    public static void add(){
        render();
    }
    
    public static void edit(){
        render();
    }
    
    public static void cancel(){
        render();
    }
    
    public static void accept(){
        render();
    }

    public static void refuse(){
        render();
    }
}
