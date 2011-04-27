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
public class MapTest extends FunctionalTest{
    
    @Test
    public void testThatIndexPageWorks() {
        Response response = GET("/carte");
        assertStatus(302,response);
    }
    
    @Test
    public void testThatShowPageWorks() {
        Response response = GET("/carte/show");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }
}
