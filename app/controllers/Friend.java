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
    public static void index() {
        redirect("/friend/list");
    }

    /**
     * Liste les amis du compte connecté
     */
    public static void list() {
        render();
    }
    
    /**
     * Ajouter un nouvel ami pour le compte connecté
     */
    public static void add()
    {
        render();
    }
    
    /**
     * Supprimer une relation d'amitité pour le compte connecté
     */
    public static void delete()
    {
        render();
    }
    
    /**
     * Recherhcer de nouveaux amis
     */
    public static void search()
    {
        render();
    }
}
