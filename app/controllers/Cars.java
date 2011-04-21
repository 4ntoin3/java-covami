/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
