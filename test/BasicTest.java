
import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteAll();
    }

    @Test
    public void createAndRetrieveUser() {
        // Create a new user and save it
        new User("bob@gmail.com", "secret", "Bob").save();

        // Retrieve the user with email address bob@gmail.com
        User bob = User.find("byEmail", "bob@gmail.com").first();

        // Test 
        assertNotNull(bob);
        assertEquals("Bob", bob.fullname);
    }

    @Test
    public void tryConnectAsUser() {
        // Create a new user and save it
        new User("bob@gmail.com", "secret", "Bob").save();

        // Test 
        assertNotNull(User.connect("bob@gmail.com", "secret"));
        assertNull(User.connect("bob@gmail.com", "badpassword"));
        assertNull(User.connect("tom@gmail.com", "secret"));
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
