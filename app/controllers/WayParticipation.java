package controllers;

import play.mvc.Controller;
import play.mvc.With;

/**
 *
 * @author pierregaste
 */
@With(Secure.class)
public class WayParticipation extends Controller {

    public static void add(Long id){
        redirect("/way/search");
    }
    
    public static void edit(){
        redirect("/way/search");
    }
    
    public static void cancel(){
        redirect("/way/search");
    }
    
    public static void accept(){
        redirect("/way/search");
    }

    public static void refuse(){
        redirect("/way/search");
    }
}
