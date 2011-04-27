package controllers;

import play.mvc.Controller;

/**
 *
 * @author pierregaste
 */
public class Friend extends Controller {
    
    /**
     * Action par défaut
     */
    public static void index()
    {
        redirect("friend/list");
    }
    
    /**
     * Listing des amis
     */
    public static void list()
    {
        render();
    }
}
