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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * REST Web Service
 *
 * @author Nikos
 */
@Path("words")
public class Words {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Words
     */
    public Words() {
    }

    /**
     * Retrieves representation of an instance of service.Words
     * @param beginL
     * @param endL
     * @param strID
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    
    //Συνάρτηση που επιστρέφει αντικείμενο απόκρισης ένα αντικείμενο JSON σε String μορφή
   public Response getWords(@QueryParam("start") char beginL, @QueryParam("end") char endL, @QueryParam("sID") String strID) {
       //Δέχεται ως παραμέτρους δύο γράμματα και το String ταυτοποίησης που έχει παραχθεί στο Web Service Login
       
        String resp=null;//Η τελική απόκριση που επιστρέφεται
        
       DBCheck ch = DBCheck.getInstance();//Δημιουργία στιγμιοτύπου
       JSONObject result= new JSONObject();//Δημιουργία τελικού αντικειμένου JSON 
       JSONArray words;//Δήλωση πίνακα JSON
       
       int code=0;//Κωδικός απόκρισης
       String username;//Το όνομα χρήστη που καλεί την υπηρεσία
         
       if(ch.isActive()) {//Αν το String ταυτοποίησης είναι έγκυρο
           
        //Αν το StringID είναι έγκυρο τότε παίρνουμε την τιμή από το HashMap που περιέχεται αυτό
           if(ch.strIDs.containsKey(strID)){
               
              //Λήψη τιμής(όνομα χρήστη) της οποίας το κλειδί είναι το όρισμα(Το username δηλαδή) 
               username = ch.strIDs.get(strID);
               
              //καλούμε την συνάρτηση της κλάσης DBCheck που επιστρέφεται ο JSON πίνακας 
                words= ch.getResult(beginL,endL);
                
            //Δημιουργία ιδιότητας "Words Matching" με τιμή τον πίνακα επιστροφής   
               result.put("Words Matching",words);
               
          //Δημιουργία ιδιότητας ονόματος με τιμή το username χρήστη     
               result.put("username",username);
              
               
               resp=result.toString();//Το αντικείμενο JSON σε αναπαράσταση String
                
               code=200;//Status=ok(κωδικός απόκρισης)
         
         
             }//end-if-contains
                 }//end-if-active
       else 
       {
           //Αν το String δεν είναι έγκυρο επιστρέφεται το String αυτό αντί για JSON αντικείμενο
          resp="#Permission Denied#";
          code=403;//κωδικός HTTP σφάλματος
    
       }//end-else
       return Response.status(code).entity(resp).build();//Επιστροφή απόκρισης
       
   }//end-getWords

    
}//end-class
