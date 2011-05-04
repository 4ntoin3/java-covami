package controllers;

import play.mvc.*;
import play.data.validation.*;

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
        models.User user = User.connected();
        render(user);
    }

    public static void editProfile(@Valid models.User user) {
        models.User userLog = User.connected();

        if (validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            render("/user/profile.html", userLog);
        }

        userLog.email = user.email;
        userLog.password = user.password;
        userLog.firstname = user.firstname;
        userLog.lastname = user.lastname;
        userLog.save();

        redirect("/user/profile");
    }

    /**
     * Page d'inscription au service
     */
    public static void subscribe() {
        render();
    }

    public static void subscribe(String email, String password, String firstname, String lastname) {
        new models.User(email, password, firstname, lastname).save();

        redirect("/login");
    }

    public static models.User connected() {
        models.User user = models.User.find("byEmail", Security.connected()).first();
        return user;
    }
}
