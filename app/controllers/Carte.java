package controllers;

import play.mvc.Controller;
import play.mvc.With;

/**
 *
 * @author pierregaste
 */
@With(Secure.class)
public class Carte extends Controller {
    public static void index(){
        redirect("/carte/show");
    }
    
    public static void show(){
        render();
    }
}
