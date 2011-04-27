package controllers;

/**
 * Classe de gestion de l'authentification de l'application
 * 
 * @version 1.0
 * @author Antoine
 */
public class Security extends Secure.Security {

    /**
     * Retourne si la tentative d'authentification à réussi ou non
     * 
     * @param username
     * @param password
     * @return boolean
     */
    static boolean authentify(String username, String password) {
        return models.User.connect(username, password) != null;
    }

    /**
     * Méthode appellée lors de la déconnexion de l'utilisateur
     */
    static void onDisconnected() {
        User.login();
    }
    
    /**
     * Méthode appellée lors de la connexion de l'utilisateur
     */
    static void onAuthenticated() {
         User.dashboard();
    }
    
    /**
     * @todo REFACTORING!
     * @param profile
     * @return 
     */
    /*static boolean check(String profile) {
        if ("admin".equals(profile)) {
            return models.User.find("byEmail", connected()).<models.User>first().isAdmin;
        }
        return false;
    }*/
    
}
