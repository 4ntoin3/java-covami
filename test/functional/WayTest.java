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
public class WayTest extends FunctionalTest {

    @Test
    public void testThatWayPageWorks() {
        Response response = GET("/way");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }
}
