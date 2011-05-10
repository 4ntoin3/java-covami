/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * La classe node est utilisée dans la classe Way pour rechercher un chemin
 * @author Antoine
 */
public class Node implements Comparable {

    //Noeud précédent
    private Node prevNode;
    //Ville dans le noeud
    private City city;
    //Nom de la route prise
    private String nameRoad;
    //Distance entre la ville et la ville d'arrivé
    private double distanceEndCity;
    //Distance parcourus
    private double distanceCovored;
    //Cout du noeud
    private double cost;

    /**
     * Constructeur par défaut
     */
    public Node() {
    }

    /**
     * Constructeur
     * @param prevNode noeud précédent
     * @param city villr
     * @param nameRoad nom de la route
     * @param endCity distance jusqu'a la ville d'arrivé
     * @param distanceCovored distance parcourus
     */
    public Node(Node prevNode, City city, String nameRoad, City endCity, double distanceCovored) {
        this.prevNode = prevNode;
        this.city = city;
        this.nameRoad = nameRoad;
        this.distanceEndCity = distanceEndCity(endCity);
        this.distanceCovored = distanceCovored;
        this.cost = this.distanceCovored + this.distanceEndCity;
    }

    /**
     * Retourne le noueud
     * @return son noeud
     */
    public Node getPrevNode() {
        return prevNode;
    }

    /**
     * Modifie le nooeud
     * @param prevNode
     */
    public void setPrevNode(Node prevNode) {
        this.prevNode = prevNode;
    }

    /**
     * Retourne la ville
     * @return sa ville
     */
    public City getCity() {
        return city;
    }

    /**
     * Modifie la ville
     * @param city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * Retourne la route
     * @return sa route
     */
    public String getNameRoad() {
        return nameRoad;
    }

    /**
     * Modifie la route
     * @param nameRoad
     */
    public void setNameRoad(String nameRoad) {
        this.nameRoad = nameRoad;
    }

    /**
     * Retourne la distance parcouru
     * @return sa distance parcouru
     */
    public double getDistanceCovored() {
        return distanceCovored;
    }

    /**
     * Modifie la distance parcouru
     * @param distanceCovored
     */
    public void setDistanceCovored(double distanceCovored) {
        this.distanceCovored = distanceCovored;
    }

    /**
     * Retourne la distance entre la ville dans le noeud et la ville d'arrivé
     * @return la distance
     */
    public double getDistanceEndCity() {
        return distanceEndCity;
    }

    /**
     * Modifie la distance entre la ville dans le noueud et la ville d'arrivé
     * @param distanceEndCity
     */
    public void setDistanceEndCity(double distanceEndCity) {
        this.distanceEndCity = distanceEndCity;
    }

    /**
     * Retourne le cout du noeud
     * @return son cout
     */
    public double getCost() {
        return cost;
    }

    /**
     * Modifie le cout du noeud
     * @param cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Calcul la distance entre la ville dans le noeud et la ville d'arrivé
     * @param endCity
     * @return la distance
     */
    private double distanceEndCity(City endCity) {
        return Math.sqrt(Math.pow((endCity.latitude - city.latitude), 2) + Math.pow((endCity.longitude - city.longitude), 2));
    }

    /**
     * Utilisé pour comparer un node à ce node
     * @param obj
     * @return 0 si les deux noeud ont le même cout
     */
    public int compareTo(Object obj) {
        Node node = (Node) obj;

        if (this.cost < node.cost) {
            return -1;
        } else if (this.cost > node.cost) {
            return 1;
        } else {
            return 0;
        }
    }
}
