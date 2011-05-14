package functional;

import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class ApplicationTest extends FunctionalTest {

    @Test
    public void testThatIndexPageSecure() {
        Response response = GET("/");
        assertStatus(302, response);
        assertHeaderEquals("Location", "http://localhost/login", response);
    }
    
    @Test
    public void testThatLoginPageWorks() {
        Response response = GET("/login");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }
}
