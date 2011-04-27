package controllers;

import models.*;

/**
 *
 * @author Antoine
 */
public class Security extends Secure.Security {

    static boolean authentify(String username, String password) {
        return models.User.connect(username, password) != null;
    }

    static void onDisconnected() {
        Application.index();
    }

    static void onAuthenticated() {
         Covami.index();
    }

    static boolean check(String profile) {
        if ("admin".equals(profile)) {
            return models.User.find("byEmail", connected()).<models.User>first().isAdmin;
        }
        return false;
    }
}
