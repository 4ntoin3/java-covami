package controllers;

import java.util.*;
import models.FriendShip;
import play.mvc.*;

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
        List<models.User> friendShips = models.FriendShip.find("user = ? and (status = ? or status = ?)", User.connected(), 2, 3).fetch();
        List<models.User> friendShips_awaiting = models.FriendShip.find("user = ? and status = 0", User.connected()).fetch();
        
        render(friendShips, friendShips_awaiting);
    }

    /**
     * Accepter une demande d'amitiée
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

    public static void refuse(Long id) {
        FriendShip friendship = FriendShip.findById(id);
        if (friendship != null && friendship.status == 0 && friendship.friend == User.connected()) {
            friendship.status = 1;
            friendship.save();
        }
        User.dashboard();
    }

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
     * Ajouter un nouvel ami pour le compte connecté
     */
    public static void add(Long id) {
        models.User user = User.connected();
        models.User friend = models.User.findById(id);

        new models.FriendShip(friend, user, 0, new Date()).save();

        redirect("/friend/search");
    }

    /**
     * Supprimer une relation d'amitité pour le compte connecté
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
     * Recherhcer de nouveaux amis
     */
    public static void search(String str) {
        List<models.User> users;

        if (str == null || str.isEmpty()) {
            users = models.User.find("id != ? order by firstname", User.connected().id).fetch();
            users = removeFriendInArray(users);
            render(users);
        }
        users = models.User.find("id !=? and (email like ? OR firstname like ? OR lastname like) ? order by firstname", User.connected(), "%" + str + "%", "%" + str + "%", "%" + str + "%").fetch();
        removeFriendInArray(users);
        render(users);
    }

    /**
     * Return une liste d'utilisateur sans les amis de l'utilisateur connecté
     * @param users une liste d'utilisateur
     * @return une liste d'utilisateur
     */
    private static List<models.User> removeFriendInArray(List<models.User> users) {
        users.removeAll(User.connected().friends());

        return users;
    }
}
