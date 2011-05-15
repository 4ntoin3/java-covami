package controllers;

import java.util.Date;
import play.mvc.Controller;
import play.mvc.With;

/**
 *
 * @author pierregaste
 */
@With(Secure.class)
public class WayParticipation extends Controller {

    public static void add(Long id) {
        models.Way way = models.Way.findById(id);

        if (way != null) {
            new models.WayParticipation(way, User.connected(), 0).save();
        }

        redirect("/way/search");
    }

    public static void cancel(Long id) {
        models.WayParticipation participation = models.WayParticipation.findById(id);
//        participation.status = 1;

        redirect("/way/search");
    }

    public static void accept(Long id) {
        models.WayParticipation participation = models.WayParticipation.findById(id);

        if (participation != null && participation.status == 0 && participation.way.driver == User.connected()) {
            participation.status = 2;
            participation.save();
        }

        User.dashboard();
    }

    public static void refuse(Long id) {
        models.WayParticipation participation = models.WayParticipation.findById(id);
        
        if (participation != null && participation.status == 0 && participation.way.driver == User.connected()) {
            participation.status = 1;
            participation.save();
        }

        User.dashboard();
    }

    public static void validNotification(Long id) {
        models.WayParticipation participation = models.WayParticipation.findById(id);

        if (participation != null && participation.participant == User.connected()) {
            if (participation.status == 1) {
                participation.delete();
            }
            else if (participation.status == 2) {
                participation.status = 4;
                participation.save();
            }
        }

        User.dashboard();
    }
}
