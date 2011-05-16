package controllers;

import java.util.*;
import models.*;
import play.data.binding.As;
import play.data.validation.Required;
import play.mvc.*;

/**
 *
 * @author pierregaste
 */
@With(Secure.class)
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
        List<models.Way> ways = models.Way.find("driver = ? order by datehourstart", User.connected()).fetch();
        render(ways);
    }

    public static void add() {

        List<City> cities = City.find("order by name").fetch();
        List<models.Car> cars = models.Car.find("owner = ? order by name", User.connected()).fetch();

        render(cities, cars);
    }

    public static void addWay(@Required Long startCityId, @Required Long finishCityId, @Required Long carId, @Required Double cost, @Required Integer placeAvailable, @Required @As("dd/MM/yyyy") Date dateStart, @Required String hourStart) {

        validation.match(hourStart, "\\d\\d:\\d\\d").message("heure non valide");
        if (validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            add();
        }
        
        City startCity = City.findById(startCityId);
        City finishCity = City.findById(finishCityId);
        models.User driver = User.connected();
        dateStart.setHours(Integer.parseInt(hourStart.split(":")[0]));
        dateStart.setMinutes(Integer.parseInt(hourStart.split(":")[1]));
        models.Car car = models.Car.findById(carId);
        
        models.Way way = new models.Way(startCity, finishCity, driver, dateStart, car, placeAvailable, cost);                
        try {
            way.calculateWay();
        } catch (Exception ex) {
            params.put("fail", "error during the calcul");
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            add();
        }
        way.save();
        redirect("/way/list");
    }
    
    public static void calculCost(Long startCityId, Long finishCityId, Long carId) throws Exception{
        models.City startCity = models.City.findById(startCityId);
        models.City finishCity = models.City.findById(finishCityId);
        models.Car car = models.Car.findById(carId);
        
        List<Object> jsonReturn = new ArrayList<Object>();
        jsonReturn.add(new models.Way(startCity, finishCity, car).cost());
        jsonReturn.add(car.nbPlace);
        
        renderJSON(jsonReturn);
    }

    public static void edit(Long id) {
        models.Way way = models.Way.findById(id);
        List<City> cities = City.find("order by name").fetch();
        List<models.Car> cars = models.Car.find("owner = ? order by name", User.connected()).fetch();
        
        render(way, cities, cars);
    }
    
    public static void editWay(Long id, @Required Long startCityId, @Required Long finishCityId, @Required Double cost, @Required Long carId, @Required Integer placeAvailable, @Required @As("dd/MM/yyyy") Date dateStart, @Required String hourStart) {

        validation.match(hourStart, "\\d\\d:\\d\\d").message("heure non valide");
        if (validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            add();
        }
        
        City startCity = City.findById(startCityId);
        City finishCity = City.findById(finishCityId);
        dateStart.setHours(Integer.parseInt(hourStart.split(":")[0]));
        dateStart.setMinutes(Integer.parseInt(hourStart.split(":")[1]));
        models.Car car = models.Car.findById(carId);
        
        models.Way way = models.Way.findById(id);
        way.startCity = startCity;
        way.finishCity = finishCity;
        way.dateHourStart = dateStart;
        way.car = car;
        
        try {
            way.calculateWay();
        } catch (Exception ex) {
            params.put("fail", "error during the calcul");
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            add();
        }
        way.save();
        redirect("/way/list");
    }
    public static void details(Long id) {
        models.Way way = models.Way.findById(id);
        render(way);
    }

    public static void cancel(Long id) {
        models.Way way = models.Way.findById(id);
        
        if(way != null && way.driver == User.connected()){
            List<models.WayParticipation> participants = models.WayParticipation.find("byWay", way).fetch();
            
            if(participants.isEmpty()){
                way.delete();
            }
            else{
                way.deleted = 1;
                for (models.WayParticipation participant : participants) {
                    participant.status = 3;
                    participant.save();
                }
                way.save();
            }
        }
        
        redirect("/way/list");
    }

    public static void search(String str) {
        List<models.Way> ways;

        if (str == null || str.isEmpty()) {
            ways = models.Way.find("driver.id != ? order by datehourstart", User.connected().id).fetch();
            ways = removeParticipationInArray(ways);
            render(ways);
        }
        ways = models.Way.find("driver.id != ? and (firstCity.name like ? OR finishCity.name like ?) order by datehourstart", User.connected().id, "%" + str + "%", "%" + str + "%").fetch();
        ways = removeParticipationInArray(ways);
        render(ways);
    }
    
    private static List<models.Way> removeParticipationInArray(List<models.Way> ways) {
        List<models.WayParticipation> wayParticipations;
        ArrayList<models.Way> participations = new ArrayList<models.Way>();
        
        wayParticipations = models.WayParticipation.find("participant = ? and status != 1", User.connected()).fetch();
        
        for (models.WayParticipation participation : wayParticipations) {
            participations.add(participation.way);
        }
        ways.removeAll(participations);

        return ways;
    }
}
