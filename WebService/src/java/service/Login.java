/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

/**
 * REST Web Service
 *
 * @author Nikos
 */
@Path("login/{username}/{pass}")//Τo URI path template εκεί όπου αποκρίνεται το web service
public class Login {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Login
     */
    public Login() {
    }

    /**
     * Retrieves representation of an instance of service.WSLogin
     *
     * @param username
     * @param pass
     *
     * @return an instance of java.lang.String
     */
    @GET//Επεξεργάζεται αιτήσεις με την μέθοδο get
    @Produces(MediaType.APPLICATION_JSON)//Επιστρέφει στον πελάτη αποτέλεσμα σε json μορφή

    //Συνάρτηση που επιστρέφει αντικείμενο απόκρισης(Response) με ορίσματα το όνομα χρήστη και τον κωδικό
    public Response getResponse(@PathParam("username") String username, @PathParam("pass") String pass) {

        String resp;//String μεταβλητή για την εκχώρηση σε αυτό το String ταυτοποίησης
        JSONObject response = new JSONObject();//Δημιουργία JSON αντικειμένου
        DBCheck ch = DBCheck.getInstance();//Δημιουργία instance της κλάσης DBCheck

        resp = ch.search(username, pass); //εκχώρηση στην μεταβλητή StringID της συνάρτησης search

        response.put(resp, username);//Εκχώρηση στο αντικείμενο την τιμή(value) username με όνομα το StringID(name/value)
        
//Επιστροφή αντικειμένου απόκρισης(Response χρησιμοποιώντας την κλάση ResponseBuilder)
//με entity το αντικέιμενο JSON σε String τύπο 

        return Response.ok().entity(response.toString()).build();//Δημιουργία απόκρισης με status ok

    }//end-getString

}
