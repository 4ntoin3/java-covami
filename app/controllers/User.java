package controllers;

import play.mvc.*;

@With(Secure.class)
public class User extends Secure.Security {

    @Before
    static void setConnectedUser() {
        if (Security.isConnected()) {
            models.User user = models.User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);
        }
    }

    public static void index() {
        redirect("/");
    }

    public static void dashboard() {
        render();
    }

    public static void logout() throws Throwable {
        Secure.logout();
    }

    public static void profile() {
        render();
    }

    public static void subscribe() {
        render();
    }
}
