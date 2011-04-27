package controllers;

import play.mvc.Controller;

/**
 *
 * @author pierregaste
 */
public class User extends Controller {
    
    /**
     * Action par défaut
     */
    public static void index()
    {
        dashboard();
    }
    
    /**
     * Tableau de bord de l'utilisateur
     */
    public static void dashboard()
    {
        render();
    }
    
    /**
     * Page de connexion
     */
    public static void login()
    {
        render();
    }
    
    /**
     * Action de déconnexion
     * @throws Throwable 
     */
    public static void logout() throws Throwable
    {
        Secure.logout();
    }
    
    /**
     * Page d'édition de profil
     */
    public static void profile()
    {
        render();
    }
    
}
