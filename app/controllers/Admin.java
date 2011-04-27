package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import play.data.validation.*;

/**
 *
 * @author Antoine
 */
@Check("admin")
@With(Secure.class)
public class Admin extends Controller {

    @Before
    static void setConnectedUser() {
        if (Security.isConnected()) {
            models.User user = models.User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);

        }
    }

    public static void index() {
        render();
    }
}
