package controllers;

/**
 *
 * @author pierregaste
 */
public class Way extends CRUD {
    
    /**
     * Méthode par défaut appellé via :
     * /way
     */
    public static void index()
    {
        list();
    }
    
    public static void list()
    {
        String cov_title_page = "Tous les trajets";
        
        render(cov_title_page);
    }
}
