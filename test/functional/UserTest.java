package functional;

import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class UserTest extends FunctionalTest {

    @Test
    public void testThatIndexPageSecure() {
        Response response = GET("/user");
        assertStatus(302, response);
        assertHeaderEquals("Location", "http://localhost/login", response);
    }

    @Test
    public void testThatDashboardPageSecure() {
        Response response = GET("/user/dashboard");
        assertStatus(302, response);
        assertHeaderEquals("Location", "http://localhost/login", response);
    }
    
    @Test
    public void testThatLogOnWorks() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", "bob@gmail.com");
        map.put("password", "secret");
        Response logon = POST("/login", map);
        assertStatus(302, logon);
        assertHeaderEquals("Location", "http://localhost/", logon);
        
        map.put("username", "toto");
        logon = POST("/login", map);
        assertStatus(302, logon);
        assertHeaderEquals("Location", "http://localhost/login", logon);
    }

    @Test
    public void testThatLogoutPageSecure() {
        Response response = GET("/user/logout");
        assertStatus(302, response);
        assertHeaderEquals("Location", "http://localhost/login", response);
    }

    @Test
    public void testThatProfilePageSecure() {
        Response response = GET("/user/profile");
        assertStatus(302, response);
        assertHeaderEquals("Location", "http://localhost/login", response);
    }

    @Test
    public void testThatSubscribePageSecure() {
        Response response = GET("/application/subscribe");
        assertStatus(200, response);
    }
}