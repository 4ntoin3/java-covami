/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unit;

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
        City startCity = new City("paris", 1, 2.0, 3.0).save();
        City finishCity = new City("marseille", 2, 5.0, 7.0).save();
        User driver = new User("bob@gmail.com", "secret", "bob", "LEBRUNT").save();
        Double distance = new Double(300);
        Date dateHourStart = new Date();
        Car car = new Car("clio", 4, 2, driver).save();
        Integer placeAvailable = new Integer(3);
        Double cost = new Double(0);
        
         new Way(startCity, finishCity, driver, dateHourStart, car, placeAvailable, cost, 0.0, 10.0).save();         
         Way way = Way.find("byDistance", distance).first();
        
        assertNotNull(way);
        assertEquals("paris", way.startCity.name);
    }
}
