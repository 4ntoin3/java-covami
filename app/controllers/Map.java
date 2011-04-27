package controllers;

import play.mvc.Controller;

/**
 *
 * @author pierregaste
 */
public class Map extends Controller {
    public static void index(){
        show();
    }
    
    public static void show(){
        render();
    }
}
