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
    public void testThatIndexPageWorks() {
        Response response = GET("/way");
        assertStatus(302,response);
    }
    
    @Test
    public void testThatListPageWorks() {
        Response response = GET("/way/list");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }
}
