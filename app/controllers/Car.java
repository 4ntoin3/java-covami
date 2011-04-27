package controllers;

import play.mvc.Controller;

/**
 *
 * @author pierregaste
 */
public class Car extends Controller {

    /**
     * Action par défaut
     */
    public static void index()
    {
        redirect("/car/list");
    }
    
    /**
     * Listing des voitures de l'utilisateur
     */
    public static void list()
    {
        render();
    }
    
}
