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
    private Long idRoad;
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
     * @param idRoad nom de la route
     * @param endCity distance jusqu'a la ville d'arrivé
     * @param distanceCovored distance parcourus
     */
    public Node(Node prevNode, City city, Long idRoad, City endCity, double distanceCovored) {
        this.prevNode = prevNode;
        this.city = city;
        this.idRoad = idRoad;
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
     * Retourne la ville
     * @return sa ville
     */
    public City getCity() {
        return city;
    }

    /**
     * Retourne la route
     * @return sa route
     */
    public Long getIdRoad() {
        return idRoad;
    }

    /**
     * Retourne la distance parcouru
     * @return sa distance parcouru
     */
    public double getDistanceCovored() {
        return distanceCovored;
    }

    /**
     * Retourne la distance entre la ville dans le noeud et la ville d'arrivé
     * @return la distance
     */
    public double getDistanceEndCity() {
        return distanceEndCity;
    }

    /**
     * Retourne le cout du noeud
     * @return son cout
     */
    public double getCost() {
        return cost;
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
    
    @Override
    public String toString() {
        return this.city.name + "; distanceEndCity: " + this.distanceEndCity + "; distanceCovored: " + this.distanceCovored + "; cost = " + this.cost+"\n";
    }
}
