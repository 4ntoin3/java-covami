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
     */
    public static void viewProfile()
    {
        render(User.connected());
    }
    
    public static void editProfile(String email, String password, String fullname)
    {
        models.User user = User.connected();
        user.email = email;
        user.password = password;
        user.fullname = fullname;
        user.save();
        
        redirect("/user/profile");
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
