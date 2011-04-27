package functional;

import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class UserTest extends FunctionalTest {

    @Test
    public void testThatIndexPageWorks() {
        Response response = GET("/user");
        assertStatus(302,response);
    }    
    
    @Test
    public void testThatDashboardPageWorks() {
        Response response = GET("/user/dashboard");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }    
    
    @Test
    public void testThatLoginPageWorks() {
        Response response = GET("/user/login");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }
    
    @Test
    public void testThatLogoutPageWorks() {
        Response response = GET("/user/logout");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }
    
    @Test
    public void testThatProfilePageWorks() {
        Response response = GET("/user/profile");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }
    
    @Test
    public void testThatSubscribePageWorks() {
        Response response = GET("/user/subscribe");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }
}