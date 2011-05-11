/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unit;

import models.*;
import org.junit.*;
import play.test.*;

/**
 *
 * @author Antoine
 */
public class RoadTest extends UnitTest{
    
    @Before
    public void setup() {
        Fixtures.deleteAll();
    }
    
    @Test
    public void createAndRetrieveWay() {
        City paris = new City("Paris", 75100, 48.866667, 2.333333).save();
        City leMans = new City("Le Mans", 72181, 48.00, 0.2).save();
        
         new Road("paris-lemans", paris, leMans).save();
         
         Road road = Road.find("byName", "paris-lemans").first();
        
        assertNotNull(road);
        assertNotNull(road.distance());
        assertEquals("Paris", road.firstCity.name);
    }
}
