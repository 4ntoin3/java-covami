package controllers;

import java.util.*;
import models.FriendShip;
import play.mvc.*;

@With(Secure.class)
public class Friend extends Controller {

    /**
     * [GET] Action par défaut
     */
    public static void index() {
        redirect("/friend/list");
    }
    
    /**
     * [GET] Liste les amis / amis en attente de l'utilisateur connecté
     * 
     */
    public static void list() {
        List<models.User> friendShips = models.FriendShip.find("user = ? and (status = 2 or status = 3)", User.connected()).fetch();
        List<models.User> friendShips_awaiting = models.FriendShip.find("user = ? and status = 0", User.connected()).fetch();
        
        render(friendShips, friendShips_awaiting);
    }

    /**
     * [GET] Accepte une relation d'amitié
     * 
     * @param id 
     */
    public static void accept(Long id) {
        FriendShip friendship = FriendShip.findById(id);
        if (friendship != null && friendship.status == 0 && friendship.friend == User.connected()) {
            friendship.status = 2;
            friendship.save();
            new FriendShip(friendship.user, friendship.friend, 3, new Date()).save();
        }
        User.dashboard();
    }

    /**
     * [GET] Refuse une relation d'amitié
     * 
     * @param id 
     */
    public static void refuse(Long id) {
        FriendShip friendship = FriendShip.findById(id);
        if (friendship != null && friendship.status == 0 && friendship.friend == User.connected()) {
            friendship.status = 1;
            friendship.save();
        }
        User.dashboard();
    }

    /**
     * [GET] Marque une notification comme lu
     * 
     * @param id 
     */
    public static void validNotification(Long id) {
        FriendShip friendship = FriendShip.findById(id);

        if (friendship != null && friendship.user == User.connected()) {
            if (friendship.status == 1) {
                friendship.delete();
            } else if (friendship.status == 2) {
                friendship.status = 3;
                friendship.save();
            }
        }
        User.dashboard();
    }

    /**
     * [GET] Soumission d'ajout d'amitié
     * 
     * @param id 
     */
    public static void add(Long id) {
        models.User user = User.connected();
        models.User friend = models.User.findById(id);

        new models.FriendShip(friend, user, 0, new Date()).save();

        redirect("/friend/search");
    }

    /**
     * [GET] Soumission de suppression d'amitié
     * 
     * @param id 
     */
    public static void delete(Long id) {
        models.FriendShip friendShipUser = models.FriendShip.findById(id);

        if (friendShipUser == null || friendShipUser.user != User.connected()) {
            redirect("/friend/list");
        }

        models.FriendShip friendShipFriend = models.FriendShip.find("friend = ? and user = ?", friendShipUser.user, friendShipUser.friend).first();

        friendShipUser.delete();
        friendShipFriend.delete();

        redirect("/friend/list");
    }

    /**
     * [GET] Recherche de nouveaux amis
     * 
     * @param str 
     */
    public static void search(String str) {
        List<models.User> users;

        if (str == null || str.isEmpty()) {
            users = models.User.find("id != ? order by firstname", User.connected().id).fetch();
            users = _removeFriendInArray(users);
            render(users);
        }
        users = models.User.find("id !=? and (email like ? OR firstname like ? OR lastname like) ? order by firstname", User.connected(), "%" + str + "%", "%" + str + "%", "%" + str + "%").fetch();
        _removeFriendInArray(users);
        render(users);
    }

    /**
     * Suppression des amis que l'utilisateur connecté a.
     * 
     * @param users
     * @return 
     */
    private static List<models.User> _removeFriendInArray(List<models.User> users) {
        users.removeAll(User.connected().friends());

        return users;
    }
}
