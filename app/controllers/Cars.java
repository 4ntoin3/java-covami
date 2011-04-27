package controllers;

import play.*;
import play.mvc.*;
/**
 *
 * @author Antoine
 */
@Check("admin")
@With(Secure.class)
public class Cars extends CRUD {
    
}
