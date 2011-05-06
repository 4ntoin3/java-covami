package controllers;

import play.mvc.Controller;

/**
 *
 * @author pierregaste
 */
public class Way extends Controller {
    
    /**
     * Action par défaut
     */
    public static void index()
    {
        redirect("/way/list");
    }
    
    /**
     * Listing des trajets
     */
    public static void list()
    {
        render();
    }
    
    public static void add(){
        render();
    }
    
    public static void edit(){
        render();
    }
    
    public static void detail(){
        render();
    }
    
    public static void cancel(){
        render();
    }
    
    public static void search(){
        render();
    }
}
