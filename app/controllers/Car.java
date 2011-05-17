package controllers;

import java.util.List;
import play.data.validation.*;
import play.mvc.*;

@With(Secure.class)
public class Car extends Controller {

    /**
     * [GET] Action par défaut
     * 
     */
    public static void index() {
        redirect("/car/list");
    }

    /**
     * [GET] Liste les voitures de l'user connecté
     * 
     */
    public static void list() {
        List<Car> cars = models.Car.find("owner = ? and deleted = 0", User.connected()).fetch();

        render(cars);
    }

    /**
     * [GET] Formulaire d'ajout de voiture
     * 
     */
    public static void add() {
        render();
    }

    /**
     * [POST] Soumission d'une nouvelle voiture
     * 
     * @param car 
     */
    public static void addCar(@Valid models.Car car) {
        if (validation.hasErrors()) {
            params.flash();
            validation.keep();
            add();
        }

        new models.Car(car.name, car.nbPlace, car.cost, User.connected()).save();

        redirect("/car/list");
    }

    /**
     * [GET] Formulaire d'édition de voiture
     * 
     * @param id 
     */
    public static void edit(Long id) {
        _checkIfTheCarBelongTheUserConnected(id);
        models.Car car = models.Car.findById(id);

        render(car);
    }

    /**
     * [POST] Soumission d'édition d'une voiture
     * 
     * @param id
     * @param car 
     */
    public static void editCar(Long id, @Valid models.Car car) {
        _checkIfTheCarBelongTheUserConnected(id);
        models.Car carEdited = models.Car.findById(id);

        if (validation.hasErrors()) {
            params.flash();
            validation.keep();
            edit(carEdited.id);
        }

        carEdited.name = car.name;
        carEdited.nbPlace = car.nbPlace;
        carEdited.cost = car.cost;
        carEdited.save();

        redirect("/car/list");
    }

    /**
     * [GET] Soumission de suppression d'une voiture
     * 
     * @param id 
     */
    public static void delete(Long id) {
        _checkIfTheCarBelongTheUserConnected(id);
        models.Car car = models.Car.findById(id);
        car.deleted = 1;
        car.save();

        List<models.Way> ways = models.Way.find("byCar", car).fetch();

        for (models.Way way : ways) {
            Way.cancel(way.id);
        }

        redirect("/car/list");
    }

    /**
     * [GET] Page de détails d'une voiture
     * 
     * @param id 
     */
    public static void details(Long id) {
        _checkIfTheCarBelongTheUserConnected(id);
        models.Car car = models.Car.findById(id);
        List<models.Way> ways = models.Way.find("byCar", car).fetch();
        //Query q = JPA.em().createQuery("SELECT SUM(w.distance) FROM Way w WHERE car.id =" + car.id);
        //Number totalDistance = (Number) q.getSingleResult();
        //System.out.println("distance :" + totalDistance);
        
        render(car, ways);
    }

    /**
     * Vérifie qu'une voiture appartient à l'utilisateur connecté
     * 
     * @param id 
     */
    private static void _checkIfTheCarBelongTheUserConnected(Long id) {
        if (!models.Car.find("byOwner", User.connected()).fetch().contains(models.Car.findById(id))) {
            redirect("/car/list");
        }
    }
}
