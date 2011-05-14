package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.FriendShip;
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
        List<models.User> friendShips = models.FriendShip.find("byUser", User.connected()).fetch();
        
        render(friendShips);
    }
    
    /**
     * Ajouter un nouvel ami pour le compte connecté
     */   
    public static void add(Long id){
        models.User user = User.connected();
        models.User friend = models.User.findById(id);
        
        new models.FriendShip(friend, user, 0, new Date()).save();
        
        redirect("/friend/list");
    }
    
    /**
     * Supprimer une relation d'amitité pour le compte connecté
     */ 
    public static void delete(Long id){
        models.FriendShip friendShip = models.FriendShip.findById(id);
        
        friendShip.delete();
        
        redirect("/friend/list");
    }
    
    /**
     * Recherhcer de nouveaux amis
     */
    public static void search(String str)
    {
        List<User> users;
        List<FriendShip> friendShips;
        ArrayList<models.User> friends = new ArrayList<models.User>();
        if(str == null || str.isEmpty()){
            users = models.User.find("id != ?", User.connected().id).fetch();
            friendShips = models.FriendShip.find("byUser", User.connected()).fetch();
            for (FriendShip friendShip : friendShips) {
                friends.add(friendShip.friend);
            }
            users.removeAll(friends);
            render("/friend/list.html", users);
        }
        users = models.User.find("email like ? OR firstname like ? OR lastname like ?", "%"+str+"%", "%"+str+"%", "%"+str+"%").fetch();        
        render("/friend/list.html", users);
    }
}
