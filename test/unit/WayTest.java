/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unit;

import java.awt.Point;
import java.util.Date;
import models.Car;
import models.City;
import models.User;
import models.Way;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

/**
 *
 * @author Antoine
 */
public class WayTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteAll();
    }

    @Test
    public void createAndRetrieveWay() {
        City startCity = new City("paris", 1, new  Point(1, 4)).save();
        City finishCity = new City("marseille", 2, new  Point(1, 4)).save();
        User driver = new User("bob@gmail.com", "secret", "bob", "LEBRUNT").save();
        Double distance = new Double(300);
        Date dateHourStart = new Date();
        Car car = new Car("clio", 4, 2, driver).save();
        Integer placeAvailable = new Integer(3);
        
         new Way(startCity, finishCity, driver, distance, dateHourStart, car, placeAvailable).save();
         
         Way way = Way.find("byDistance", distance).first();
        
        assertNotNull(way);
        assertEquals("paris", way.startCity.name);
    }
}
