package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import play.mvc.Controller;
import play.mvc.With;

/**
 *
 * @author pierregaste
 */
@With(Secure.class)
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
        List<models.User> friends = new ArrayList<models.User>();
        
        for (models.Friend friend : User.connected().friends) {
            friends.add(friend.user);
        }
        
        render(friends);
    }
    
    /**
     * Ajouter un nouvel ami pour le compte connecté
     */   
    public static void add(Long id){
        models.User user = User.connected();
        models.User friend = models.User.findById(id);
        
        
        models.Friend relation = new models.Friend(friend, 0, new Date());
        relation.save();
        user.friends.add(relation);
        user.save();
        
        redirect("/friend/list");
    }
    
    /**
     * Supprimer une relation d'amitité pour le compte connecté
     */ 
    public static void delete(Long id){
        models.User user = User.connected();
        models.Friend friend = models.Friend.find("byUser", models.User.findById(id)).first();
        
        user.friends.remove(friend);
        user.save();
        friend.delete();
        
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
