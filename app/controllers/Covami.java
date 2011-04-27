/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
/**
 *
 * @author Antoine
 */
@With(Secure.class)
public class Covami extends Controller {

    @Before
    static void setConnectedUser() {
        if (Security.isConnected()) {
            models.User user = models.User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);

        }
    }
    
    public static void index(){
        render();
    }
}
