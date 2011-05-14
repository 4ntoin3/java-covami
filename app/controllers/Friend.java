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
        List<models.User> friendShips = models.FriendShip.find("user = ? and status = ?", User.connected(), 2).fetch();

        render(friendShips);
    }

    public static void accept(Long id) {
        FriendShip friendship = FriendShip.findById(id);
        if (friendship != null && friendship.status == 0 && friendship.friend == User.connected()) {
            friendship.status = 2;
            friendship.save();
            new FriendShip(friendship.user, friendship.friend, 2, new Date()).save();
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

        redirect("/friend/list");
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
        List<User> users;

        if (str == null || str.isEmpty()) {
            users = models.User.find("id != ? order by firstname", User.connected().id).fetch();
            removeFriendInArray(users);
            render(users);
        }
        users = models.User.find("email like ? OR firstname like ? OR lastname like ? order by firstname", "%" + str + "%", "%" + str + "%", "%" + str + "%").fetch();
        removeFriendInArray(users);
        render(users);
    }

    /**
     * Return une liste d'utilisateur sans les amis de l'utilisateur connecté
     * @param users une liste d'utilisateur
     * @return une liste d'utilisateur
     */
    private static List<User> removeFriendInArray(List<User> users) {
        List<FriendShip> friendShips;
        ArrayList<models.User> friends = new ArrayList<models.User>();
        friendShips = models.FriendShip.find("byUser", User.connected()).fetch();
        for (FriendShip friendShip : friendShips) {
            friends.add(friendShip.friend);
        }
        users.removeAll(friends);

        return users;
    }
}
