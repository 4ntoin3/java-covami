/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import play.data.validation.Valid;
import play.mvc.Controller;

/**
 *
 * @author Antoine
 */
public class Application extends Controller{
    
        /**
     * Page d'inscription au service
     */
    public static void subscribe() {
        render();
    }
    
    public static void addUser(@Valid models.User user){
        
        if (validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            redirect("/application/subscribe");
        }
        
        if(models.User.find("byEmail", user.email).first() != null){
            validation.addError("user.email", "email used");
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            redirect("/application/subscribe");
        }
        
        new models.User(user.email, user.password, user.firstname, user.lastname).save();
        redirect("/login");
    }
}
