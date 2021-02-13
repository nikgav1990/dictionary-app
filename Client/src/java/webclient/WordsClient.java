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
 * Jersey REST client generated for REST resource:Words [words]<br>
 * USAGE:
 * <pre>
 *        NewJerseyClient client = new NewJerseyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Nikos
 *////-----------------------------Δημιουργία Πελάτη του WebService Words-----------------------------------------//
public class WordsClient {

    private final WebTarget webTarget;
    private final Client client;
    private static final String BASE_URI = "http://localhost:8080/WebService/webresources";

    public WordsClient() {//Δημιουργία αντικειμένου πελάτη
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("words");
    }

    public <T> T getWords(Class<T> responseType, String start, String end, String sID) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (start != null) {
            resource = resource.queryParam("start", start);
        }
        if (end != null) {
            resource = resource.queryParam("end", end);
        }
        if (sID != null) {
            resource = resource.queryParam("sID", sID);
        }
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
    public static void main(String[] args){
       
   
     //Ο χρήστης εισάγει τα δυο γράμματα από τα οποία αρχίζουν και τελειώνουν οι λέξεις που θέλει ως αποτέλεσμα.
     //Επειτα εισάγει το String ταυτοποίησης που έλαβε και αν είναι έγκυρο εμφανίζεται μήνυμα χαιρετισμού
     //και οι λέξεις του αποτελέσματος
        String resp=null;
        System.out.println("Enter two letters to start searching: ");
        
        //Δημιουργία Scanner αντικειμένου για διάβασμα από το πληκτρολόγιο
        Scanner scan= new Scanner(System.in);
        
        //Εισαγωγή στοιχείων από το πληκτρολόγιο
        System.out.print("First Letter: ");
        String firstL=scan.nextLine();
        
        System.out.print("Second Letter: ");
        String secondL=scan.nextLine();
        
        System.out.print("String ID: ");
        String sid=scan.nextLine();
        
try{
//Τοποθετούμε όλο τον κώδικα σε try-catch block διότι αν το String ταυτοποίησης είναι άκυρο 
//δημιουργείται η εξαίρεση ClientErrorException και η πρόσβαση δεν επιτρέπεται.

     //Δημιουργία Client αντικειμένου και πέρασμα ορισμάτων στη συνάρτηση getWords για επιστροφή απόκρισης
       WordsClient wc= new WordsClient();
       
       //κλήση συνάρτησης για επιστροφή απόκρισης
        resp= wc.getWords(String.class, firstL, secondL, sid);
       
    //Εφόσον το αποτέλεσμα επιστρέφεται σε JSON μορφή απομονώνουμε με τις κατάλληλες String συναρτήσεις
    //από το JSON αντικείμενο το όνομα χρήστη και αρχικά καλωσορίζουμε τον χρήστη
    //και έπειτα εμφανίζονται οι λέξεις που ταιριάζουν με τα ορίσματα
    
    //Ελέγχουμε την τελευταία εμφάνιση του String username για να πάρουμε στη συνέχεια το όνομα χρήστη
       int f_index= resp.lastIndexOf("username");
       String username= resp.substring(f_index,resp.length());
       int sec_index= username.indexOf(":");
       String uname= username.substring(sec_index+2,username.length()-2);
       System.out.println("----------------------");
       System.out.println("Hello "+uname+"!");//Μήνυμα εμφάνισης χαιρετισμού
       
       
       //Απομονώνουμε τις λέξεις από τον πίνακα και τις εμφανίζουμε στον χρήστη στο τέλος
       int fbrace= resp.indexOf("[");//Θέση πρώτης αγκύλης
        int lbrace= resp.indexOf("]");//Θέση δεύτερης αγκύλης
       
       String words=resp.substring(fbrace+1,lbrace);//Το "εσωτερικό" των αγκυλών θα είναι οι λέξεις που ταιριάζουν
       System.out.println("=================================");
       System.out.println("Words Matching: "+words);
       System.out.println("=================================");
       
       
       //Κλείσιμο πελάτη
       wc.close();
       
       }//end-try//end-try
       //Χερισμός εξαίρεσης--επιστρέφεται η απόκριση όπως και στο web service '#Permission Denied#'  
       catch(ClientErrorException e){
         
          resp="#Permission Denied#"; 
          System.out.println("===========================");
          System.out.println(resp);
          System.out.println("===========================");
          
     }//end-catch
       
    }//end-main
    
}//end-class
