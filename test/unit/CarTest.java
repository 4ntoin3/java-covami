/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unit;

import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

/**
 *
 * @author Antoine
 */
public class CarTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteAll();
    }

    @Test
    public void createCar() {
        //Create a new User and Save it
        User bob = new User("bob@gmail.com", "secret", "Bob").save();

        //Create a new Car
        new Car("clio", 2, bob).save();

        //Test that the car has been created
        assertEquals(1, Car.count());

        //Retrieve all car's bob
        List<Car> bobCars = Car.find("byOwner", bob).fetch();

        //Tests
        assertEquals(1, bobCars.size());
        Car firstCar = bobCars.get(0);
        assertNotNull(firstCar);
        assertEquals(bob, firstCar.owner);
        assertEquals("clio", firstCar.name);
        assertEquals(2, firstCar.cost);
    }
}
