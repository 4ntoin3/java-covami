package controllers;

import play.data.validation.Valid;
import play.mvc.Controller;

public class Application extends Controller{
    
    /**
     * [GET] Formulaire d'inscription
     * 
     */
    public static void subscribe() {
        render();
    }
    
    /**
     * [POST] Soumission d'une inscription
     * 
     * @param user 
     */
    public static void addUser(@Valid models.User user){
        if (validation.hasErrors()) {
            params.flash();
            validation.keep();
            redirect("/application/subscribe");
        }
        
        if(models.User.find("byEmail", user.email).first() != null){
            validation.addError("user.email", "Cet email est déjà utilisé.");
            params.flash();
            validation.keep();
            redirect("/application/subscribe");
        }
        
        new models.User(user.email, user.password, user.firstname, user.lastname).save();
        redirect("/login");
    }
}
