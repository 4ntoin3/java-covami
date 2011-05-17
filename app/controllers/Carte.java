package controllers;

import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Carte extends Controller {
    
    /**
     * [GET] Action par défaut
     * 
     */
    public static void index(){
        redirect("/carte/show");
    }
    
    /**
     * [GET] Affichage de la carte
     * 
     * @todo Not yet implemented
     */
    public static void show(){
        render();
    }
}
