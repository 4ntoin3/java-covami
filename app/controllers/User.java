package controllers;

import play.mvc.*;

/**
 *
 * @author pierregaste
 */
public class User extends CRUD {
    public static void dashboard()
    {
        String cov_title_page = "Tableau de bord utilisateur";
        
        render(cov_title_page);
    }
}
