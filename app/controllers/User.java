package controllers;

import play.mvc.*;

@With(Secure.class)
public class User extends Controller {

    @Before
    static void setConnectedUser() {
        if (Security.isConnected()) {
            models.User user = models.User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);
        }
    }

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

    /**
     * Page d'inscription au service
     */
    public static void subscribe()
    {
        render();
    }
}
