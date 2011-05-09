package controllers;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import models.*;
import play.data.binding.As;
import play.data.validation.Match;
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

    public static void addWay(@Required Long startCityId, @Required Long finishCityId, @Required Long carId, @Required Integer placeAvailable, @Required @As("dd/MM/yyyy") Date dateStart, @Required String hourStart) {

        validation.match(hourStart, "\\d\\d:\\d\\d").message("heure non valide");
        if (validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            add();
        }
        
        City startCity = City.findById(startCityId);
        City finishCity = City.findById(finishCityId);
        models.User driver = User.connected();
        Double distance = new Double(0);
        dateStart.setHours(Integer.parseInt(hourStart.split(":")[0]));
        dateStart.setMinutes(Integer.parseInt(hourStart.split(":")[1]));
        models.Car car = models.Car.findById(carId);
        
        new models.Way(startCity, finishCity, driver, distance, dateStart, car, placeAvailable).save();                
        redirect("/way/list");
    }

    public static void edit(Long id) {
        render();
    }

    public static void details(Long id) {
        models.Way way = models.Way.findById(id);
        render(way);
    }

    public static void cancel(Long id) {
        models.Way.findById(id)._delete();
        
        redirect("/way/list");
    }

    public static void search() {
        render();
    }
}
