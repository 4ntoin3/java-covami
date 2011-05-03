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
    
    /**
     * Ajout une voiture pour le compte connecté
     */
    public static void add()
    {
        render();
    }
    
    /**
     * Edit une voiture pour le compte connecté
     */
    public static void edit()
    {
        render();
    }
    
    /**
     * Supprime une voiture pour le compte connecté
     */
    public static void delete()
    {
        
    }
    
    /**
     * Affiche les informations d'une voiture
     */
    public static void details()
    {
        render();
    }
    
}
