package controllers;

import play.mvc.Controller;
import play.mvc.With;

/**
 *
 * @author pierregaste
 */
@With(Secure.class)
public class WayParticipation extends Controller {

    public static void add(Long id){
        models.Way way = models.Way.findById(id);
        
        new models.WayParticipation(way, User.connected(), 0).save();
        
        redirect("/way/search");
    }
    
    public static void cancel(Long id){
        models.WayParticipation participation = models.WayParticipation.findById(id);
        participation.status = 1;
        
        redirect("/way/search");
    }
    
    public static void accept(Long id){
        models.WayParticipation participation = models.WayParticipation.findById(id);
        participation.status = 2;
        redirect("/way/search");
    }

    public static void refuse(Long id){
        models.WayParticipation participation = models.WayParticipation.findById(id);

        
        redirect("/way/search");
    }
}
