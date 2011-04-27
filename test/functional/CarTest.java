/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package functional;

import org.junit.Test;
import play.mvc.Http.Response;
import play.test.FunctionalTest;

/**
 *
 * @author Antoine
 */
public class CarTest extends FunctionalTest{
    
    @Test
    public void testThatCarPageWorks() {
        Response response = GET("/car");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }
}
