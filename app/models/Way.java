/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.persistence.*;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 *
 * @author Antoine
 */
@Entity
public class Way extends Model {
    
    @ManyToOne
    @Required
    public City startCity;
    
    @ManyToOne
    @Required
    public City finishCity;
    
    @ManyToOne
    @Required
    public User driver;
    
    @ManyToMany
    public List<User> passengers;
    
    @ManyToMany
    public List<City> cities;
    
    @Required
    public Double distance;
    
    @Required
    public Date dateHourStart;
    
    @ManyToOne
    @Required
    public Car car;
    
    @Required
    public Integer placeAvailable;

    public Way(City startCity, City finishCity, User driver, Double distance, Date dateHourStart, Car car, Integer placeAvailable) {
        this.startCity = startCity;
        this.finishCity = finishCity;
        this.driver = driver;
        this.passengers = new ArrayList<User>();
        this.cities = new ArrayList<City>();                
        this.distance = distance;
        this.dateHourStart = dateHourStart;
        this.car = car;
        this.placeAvailable = placeAvailable;
    }
    
    
    /**
     * Recherche le chemin à prendre
     */
    public void calculateWay() {
        long start = System.currentTimeMillis();

        TreeSet<Node> citiesTree = new TreeSet();
        Node node = new Node();
        
        
        PrintWriter log = null;
        try {
            log =  new PrintWriter(new BufferedWriter
               (new FileWriter("tmp/calcul.txt")));
        } catch (IOException ex) {
            Logger.getLogger(Way.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        citiesTree.add(new Node(null, this.startCity, "", this.finishCity, 0));
        while (citiesTree.isEmpty() || node.getCity() != finishCity) {
            node = citiesTree.first();
            citiesTree.remove(node);

            for (Road road : node.getCity().roads()) {
                City cityTmp = (road.firstCity.id == node.getCity().id)? road.secondCity:road.firstCity;
//                City cityTmp = road.secondCity;
                log.println("node.getCity :"+node.getCity().name+", ville ajouté: "+cityTmp.name+", distance="+road.distance());
                citiesTree.add(new Node(node, cityTmp, road.name, this.finishCity, node.getDistanceCovored() + road.distance()));
            }
            if ((System.currentTimeMillis() - start) > 10000) {
//                Thread.currentThread().destroy();
                break;
            }
        }
        this.distance = node.getDistanceCovored();
        log.println(citiesTree);
        saveWay(node);
    }

    /**
     * Calcul et retourne les routes à emprunter
     * @param node
     */
    private void saveWay(Node node) {
        try {
            Node nodeTmp = node;

            while (nodeTmp.getPrevNode() != null) {
                this.cities.add(node.getCity());
                nodeTmp = nodeTmp.getPrevNode();
            }
            Collections.reverse(cities);            
            
            PrintWriter log;

            log =  new PrintWriter(new BufferedWriter
               (new FileWriter("tmp/itineraire.txt")));    
            
            log.println("Itineraire a suivre pour aller de "+startCity.name+" à "+finishCity.name+":");
            for (City city : this.cities) {
                System.out.println("ville :"+city.name);
            }
            log.close();
        } catch (IOException ex) {
            Logger.getLogger(Way.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
