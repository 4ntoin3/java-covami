package controllers;

import java.util.List;
import javax.persistence.Query;
import play.data.validation.*;
import play.db.jpa.JPA;
import play.mvc.Controller;
import play.mvc.With;

/**
 *
 * @author pierregaste
 */
@With(Secure.class)
public class Car extends Controller {

    /**
     * Action par défaut
     */
    public static void index()
    {
        redirect("/car/list");
    }
    
    /**
     * Liste des voitures du compte connecté
     * 
     * @view app/view/car/list.html
     */
    public static void list()
    {
        List<Car> cars =  models.Car.find("byOwner", User.connected()).fetch();
        render(cars);
    }
    
    /**
     * Ajout une voiture pour le compte connecté
     */
    public static void add()
    {        
        render();
    }
    
    public static void addCar(@Required String name, @Required @Min(1) Integer nbPlace, @Required @Min(0) Integer cost){
        
        if (validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            add();
        }
        
        new models.Car(name, nbPlace, cost, User.connected()).save();

        redirect("/car/list");
    }
    
    /**
     * Edit une voiture pour le compte connecté
     */
    public static void edit(Long id)
    {
        checkIfTheCarBelongTheUserConnected(id);
        models.Car car = models.Car.findById(id);              
        render(car);
    }

    private static void checkIfTheCarBelongTheUserConnected(Long id) {
        if(!models.Car.find("byOwner", User.connected()).fetch().contains(models.Car.findById(id))){
            redirect("/car/list");
        }
    }
    
    public static void editCar(Long id, @Required String name, @Required @Min(1) Integer nbPlace, @Required @Min(0) Integer cost){
        checkIfTheCarBelongTheUserConnected(id);
        models.Car carEdited = models.Car.findById(id);
        
        if (validation.hasErrors()) {
            params.flash(); // add http parameters to the flash scope
            validation.keep(); // keep the errors for the next request
            edit(carEdited.id);
        }
        
        carEdited.name = name;
        carEdited.nbPlace = nbPlace;
        carEdited.cost = cost;
        carEdited.save();
        
        redirect("/car/list");
    }
    
    /**
     * Supprime une voiture pour le compte connecté
     */
    public static void delete(Long id)
    {
        checkIfTheCarBelongTheUserConnected(id);
        models.Car car = models.Car.findById(id);
        car.delete();
        
        redirect("/car/list");
    }
    
    /**
     * Affiche les informations d'une voiture
     */
    public static void details(Long id)
    {
        checkIfTheCarBelongTheUserConnected(id);
        models.Car car = models.Car.findById(id);
        List<models.Way> ways = models.Way.find("byCar", car).fetch();
        Query q = JPA.em().createQuery ("SELECT SUM(w.distance) FROM Way w WHERE car.id ="+car.id);
        Number totalDistance = (Number) q.getSingleResult();
        System.out.println("distance :"+totalDistance);
        render(car, ways);
    }
    
}
