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
        models.User userEdited = User.connected();

        if (validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            redirect("/user/profile");
        }
        
        if(models.User.find("byEmail", user.email).first() != null && !models.User.find("byEmail", user.email).first().equals(userEdited)){
            validation.addError("user.email", "email used");
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            redirect("/user/profile");
        }

        userEdited.email = user.email;
        userEdited.password = user.password;
        userEdited.firstname = user.firstname;
        userEdited.lastname = user.lastname;
        userEdited.save();

        redirect("/user/profile");
    }

    public static models.User connected() {
        models.User user = models.User.find("byEmail", Security.connected()).first();
        return user;
    }
}
