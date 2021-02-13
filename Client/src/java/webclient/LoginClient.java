/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webclient;

import java.util.Scanner;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:Login
 * [login/{username}/{pass}]<br>
 * USAGE:
 * <pre>
 *        LoginClient client = new LoginClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Nikos
 *///-----------------------------Δημιουργία Πελάτη του WebService Login-----------------------------------------//
public class LoginClient {  

    private WebTarget webTarget;
    private final  Client client;
    private static final String BASE_URI = "http://localhost:8080/WebService/webresources";

    public LoginClient(String username, String pass) {
        
        client = javax.ws.rs.client.ClientBuilder.newClient();//Δημιουργία client αντικειμένου
        String resourcePath = java.text.MessageFormat.format("login/{0}/{1}", new Object[]{username, pass});
        webTarget = client.target(BASE_URI).path(resourcePath);
    }
    
    public void setResourcePath(String username, String pass) {//Δημιουργία Web resource
        String resourcePath = java.text.MessageFormat.format("login/{0}/{1}", new Object[]{username, pass});
        webTarget = client.target(BASE_URI).path(resourcePath);
    }

    public <T> T getResponse(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        //Επιστροφή απόκρισης από το resource
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

   
    //Κλείσιμο πελάτη
    public void close() {
        client.close(); 
    }
    
    public static void main(String[] args){
        
   
     //O χρήστης εισάγει τα στοιχεία του από το πληκτρολόγιο και έπειτα καλούμε τον constructor της κλάσης LoginClient
     //ώστε να δημιουργήσουμε ένα αντικείμενο πελάτη με τα στοιχεία αυτά.
     //Έπειτα ενημερώνεται και το Path της υπηρεσίας και τέλος επιστρέφεται σε JSON μορφή το αποτέλεσμα
        System.out.println("Enter your username and your password: ");
        
        //Δημιουργία Scanner αντικειμένου για διάβασμα από το πληκτρολόγιο
        Scanner scan= new Scanner(System.in);
        
        //Εισαγωγή στοιχείων από το πληκτρολόγιο
        System.out.print("Username: ");
        String usern=scan.nextLine();
        
        System.out.print("Password: ");
        String pass=scan.nextLine();
        
    //Δημιουργία Client αντικειμένου και επιστροφή απόκρισης σε String δίνοντας ως όρισμα αντικείμενο της κλάσης String
       LoginClient lg= new LoginClient(usern,pass);
       String resp= lg.getResponse(String.class);
       
       //Εφόσον το αποτέλεσμα επιστρέφεται στην μορφή name:value κρατάμε την θέση του χαρακτήρα ':' 
       //ώστε να πάρουμε το πρώτο μέρος που είναι το String ταυτοποίησης που επιστρέφεται
       int index= resp.indexOf(":");
       
       //Το τελικό StringID θα είναι το υποString από την θέση 2 εώς το χαρακτήρα ':'.
       String StringID= resp.substring(2,index-1);
       
       //Εκτύπωση αποτελέσματος
       System.out.println("================================"); 
       System.out.println("Your StringID is : "+StringID);
       System.out.println("================================");
       
       //Κλείσιμο πελάτη
       lg.close();
      
  }//end-main
    
    
    
}//-----------------Τέλος Δημιουργίας Πελάτη WebService Login---------------------------------//
