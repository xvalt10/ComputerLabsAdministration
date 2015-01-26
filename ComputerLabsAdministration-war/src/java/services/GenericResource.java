/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Users;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import sessionBeans.UsersFacade;

/**
 * REST Web Service
 *
 * @author Tomas
 */
@Path("/confirmRegistration")
public class GenericResource {
    @EJB
    UsersFacade usersFacade;
    
    
    

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of services.GenericResource
     * @param username
     * @param password
     * @param mobile
     * @param email
     * @param department
     * @return an instance of java.lang.String
     */
    
    @Path("/{username}")
    @GET
    @Produces("text/plain")
    public String confirmUserRegistration(
            @PathParam("username") String username) {
        
        Users user=usersFacade.getUserByUsername(username);
        user.setAccountStatus("Activated");
        usersFacade.edit(user);
        return "Username:"+username+" has been activated";
        
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

}
