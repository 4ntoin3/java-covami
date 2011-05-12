package controllers;

import java.util.List;
import play.mvc.Controller;

/**
 *
 * @author pierregaste
 */
public class Friend extends Controller {

    /**
     * Action par défaut
     */
    public static void index() {
        redirect("/friend/list");
    }

    /**
     * Liste les amis du compte connecté
     */
    public static void list() {
        List<models.User> friends = User.connected().friends;
        
        render(friends);
    }
    
    /**
     * Ajouter un nouvel ami pour le compte connecté
     */   
    public static void add(Long id){
        models.User user = User.connected();
        models.User friend = models.User.findById(id);
        
        user.friends.add(friend);
        user.save();
        
        redirect("/friend/list");
    }
    
    /**
     * Supprimer une relation d'amitité pour le compte connecté
     */ 
    public static void delete(Long id){
        models.User user = User.connected();
        models.User friend = models.User.findById(id);
        
        user.friends.remove(friend);
        user.save();
        
        redirect("/friend/list");
    }
    
    /**
     * Recherhcer de nouveaux amis
     */
    public static void search(String str)
    {
        List<User> friends;
        if(str == null || str.isEmpty()){
            friends = models.User.find("id != ?", User.connected().id).fetch();
            render("/friend/list.html", friends);
        }
        friends = models.User.find("email like ? OR firstname like ? OR lastname like ?", "%"+str+"%", "%"+str+"%", "%"+str+"%").fetch();        
        render("/friend/list.html", friends);
    }
}
