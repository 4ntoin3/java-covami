package controllers;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import models.*;
import org.joda.time.DateTime;
import play.data.binding.As;
import play.data.validation.Required;
import play.mvc.Controller;

/**
 *
 * @author pierregaste
 */
public class Way extends Controller {

    /**
     * Action par défaut
     */
    public static void index() {
        redirect("/way/list");
    }

    /**
     * Listing des trajets
     */
    public static void list() {
        List<models.Way> ways = models.Way.find("byDriver", User.connected()).fetch();
        render(ways);
    }

    public static void add() {

        List<City> cities = City.findAll();
        List<models.Car> cars = models.Car.find("byOwner", User.connected()).fetch();

        render(cities, cars);
    }

    public static void addWay(@Required Integer startCity, @Required Integer finishCity, @Required @As("yyyy-MM-dd") Date dateStart, @Required @As("hh:mm") Time hourStart, @Required Integer car) {

        if (validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            add();
        }
        
//        new models.Way(City.findById(startCity), City.findById(finishCity), 300, new DateTime, null, null, car)
    }

    public static void edit(Long id) {
        render();
    }

    public static void details(Long id) {
        models.Way way = models.Way.findById(id);
        render(way);
    }

    public static void cancel(Long id) {
        render();
    }

    public static void search() {
        render();
    }
}
