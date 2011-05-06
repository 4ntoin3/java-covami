/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.Point;
import javax.persistence.Entity;
import play.db.jpa.Model;

/**
 *
 * @author Antoine
 */
@Entity
public class City extends Model{
    
    public String name;
    public int codeINSEE;
    public Point pxPosition;
    public Point gpsPosition;

    public City(String name, int codeINSEE, Point gpsPosition) {
        this.name = name;
        this.codeINSEE = codeINSEE;
        this.pxPosition = new Point();
        this.gpsPosition = gpsPosition;        
    }
}
