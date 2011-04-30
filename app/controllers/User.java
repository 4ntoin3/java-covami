package controllers;

import play.mvc.*;

public class User extends Secure.Security {
    
    @Before
    static void setConnectedUser() {
        if (Security.isConnected()) {
            models.User user = models.User.find("byEmail", Security.connected()).first();
            renderArgs.put("user", user.fullname);
        }
    }
    
    public static void index()
    {
        redirect("/");
    }
    
    public static void dashboard()
    {
        render();
    }
    
    public static void logout() throws Throwable
    {
        Secure.logout();
    }
    
    public static void profile()
    {
        render();
    }
    
    public static void subscribe()
    {
        render();
    }
    
    static boolean authenticate(String username, String password) {
        return models.User.connect(username, password) != null;
    }
    
    static void onDisconnected() {
        index();
    }

    static void onAuthenticated() {
        dashboard();
    }
    
    /*static boolean check(String profile) {
        if ("admin".equals(profile)) {
            return models.User.find("byEmail", connected()).<models.User>first().isAdmin;
        }
        return false;
    }*/
    
}
