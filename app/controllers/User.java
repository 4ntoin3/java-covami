package controllers;

import play.mvc.*;

@With(Secure.class)
public class User extends Controller {
    
    /**
     * Action par défaut
     */
    public static void index()
    {
        redirect("/");
    }

    /**
     * Tableau de bord de l'utilisateur
     */
    public static void dashboard()
    {
        render(User.connected());
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
     * 
     * @view app/view/user/profile.html
     */
    public static void profile()
    {
        render(User.connected());
    }

    /**
     * Page d'inscription au service
     */
    public static void subscribe()
    {        
        render();
    }
    
    public static void subscribe(String email, String password, String fullname){
        new models.User(email, password, fullname).save();
        
        redirect("/login");
    }
            
    public static models.User connected() {
        models.User user = models.User.find("byEmail", Security.connected()).first();
        return user;
    }
}
