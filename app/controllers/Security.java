package controllers;

public class Security extends Secure.Security {

    /**
     * Méthode d'authentification
     * 
     * @param username
     * @param password
     * @return 
     */
    static boolean authentify(String username, String password) {
        return models.User.connect(username, password) != null;
    }
    
    /**
     * Hook sur la déconnexion
     * 
     */
    static void onDisconnected() {
        User.index();
    }
    
    /**
     * Hook sur la connexion
     * 
     */
    static void onAuthenticated() {
         User.dashboard();
    }
}
