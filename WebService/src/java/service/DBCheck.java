/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import org.json.simple.JSONArray;

/**
 *
 * @author Nikos
 */
public class DBCheck {
    //Μεταβλητές της κλάσης αρχικοποιημένες σε null
    static DBCheck inst = null;
    HashMap<String,String> strIDs;

      //Δήλωση Driver και URL της βάσης
       String JDBC_Driver=null;
       String DB_URL=null;

      //Αρχικοποίηση αντικειμένων σύνδεσης σε null
       Connection conn=null;
       java.sql.Statement dbstate=null;

       //Το username του χρήστη που χρησιμοποιηθεί
       String username=null;

      //Ο αριθμός long που εκφράζει την τρέχουσα ώρα του συστήματος σε milliseconds
       long created=0;

       private DBCheck(){
           
        //Εκχωρούμε τιμές στις μεταβλητές της κλάσης στον κατασκευαστή της
       strIDs= new HashMap<>();//Δημιουργίας ενός HashMap για αποθήκευση των μεταβλητών stringid-username(K,V))
       JDBC_Driver= "com.mysql.jdbc.Driver";//Ο driver που θα χρειαστεί για την σύνδεση στη βάση
       DB_URL="jdbc:mysql://localhost:3306/services";//To URL της βάσης

       }//end-constructor

       //Boolean συνάρτηση που πραγματοποιεί την σύνδεση στη βάση δεδομένων
       //Επιστρέφει ένα flag(true/flase) ανάλογα αν η σύνδεση επιτεύχθηκε η όχι
       boolean connect(){

           boolean flag=false;
          try{

         //Φόρτωση driver στην μνήμη
         Class.forName(JDBC_Driver);
      //Άνοιγμα σύνδεσης με την βάση δίνοντας το URL της βάσης,όνομα χρήστη και κωδικό
       conn = DriverManager.getConnection(DB_URL,"root","");

              if(conn!=null){
                //Αν η σύνδεση πραγματοποιηθεί επιστρέφει true
                  return true;

              }//end-if

             }//end-try
          
         //---------Catching Exceptions-----------//
            catch(ClassNotFoundException | SQLException e){
             flag=false;
             return flag;
             }//end-catch

        return flag;//Επιστροφή της τιμής flag
       }//end-connect


///Συνάρτηση που ελέγχει αν ο χρήστης υπάρχει στην βάση.
 //Δέχεται ως όρισμα το όνομα χρήστη και τον κωδικό του και επιστρέφει το String ταυτοποίησης η ERROR, 
       //ανάλογα αν υπάρχουν τα στοιχεία στην βάση.
       String search(String name,String pass){

            String resp;//Το αποτέλεσμα που επιστρέφεται
            //Αν η σύνδεση επιτευχθεί επιτυχώς τότε συνεχίζουμε.Καλείται η παραπάνω συνάρτηση connect
           if (connect()) {
               
         try{

           dbstate=conn.createStatement();//Δημιουργία statement
          String sql="SELECT * FROM users WHERE username='"+name+"' AND password='"+pass+"' ";

               //Το αποτέλεσμα της αναζήτησης μέσω του interface Result
               ResultSet rs= dbstate.executeQuery(sql);
               
//Αν ο δείκτης δεν είναι πριν την πρώτη γραμμή των αποτελεσμάτων 
//τότε επιστρέφεται ERROR ως απόκριση.
               if(!rs.isBeforeFirst()){

                    resp="#ERROR#";
                    return resp;//Επιστροφή αρνητικής απάντησης

            }//end-if-rows
               
//Αλλιώς αν υπάρχουν αποτελέσματα παίρνουμε το όνομα χρήστη
              while (rs.next()) {

              username= rs.getString("username");

              }//end-while

//Το αποτέλεσμα που επιστρέφεται θα είναι το String που παράγεται από την παρακάτω συνάρτηση
             resp= getResponse();
          ///--------------------------------------------------------//
                 strIDs.clear();//Προαιρετική Εκκαθάριση HashMap
          ///--------------------------------------------------------//
             strIDs.put(resp,username);//Αποθήκευση εγγραφής στο HashMap ως (Κ,V) μορφή
             created=System.currentTimeMillis();//Η τρέχουσα στιγμή που δημιουργήθηκε το StringID
             
             //Αν ο χρήστης λάβει String ταυτοποίησης τότε αυξάνεται η τιμή του πεδίου successful_logins κατά μία
             String sqlu="UPDATE users SET successful_logins= successful_logins+1 WHERE username='"+username+"'";
             
             //Το αποτέλεσμα της ενημέρωση θα είναι οι γραμμές που επηρεάστηκαν από το query
              dbstate.executeUpdate(sqlu);  
             
             
             
             
             //Κλείσιμο συνδέσεων
              conn.close();
              dbstate.close();

              return resp;//Επιστροφή θετικού αποτελέσματος

                 }//end-try

            catch(SQLException e){
                
//Αν προκύψει εξαίρεση τότε επιστέφεται ως απόκριση το μήνυμα της εξαίρεσης και αδείαζει ο πίνακας εγγραφών
             username=null;
             strIDs.clear();//Εκκαθάριση HashMap
             resp=e.getMessage();//αποστολή μηνύματος εξαίρεσης ως απόκριση

           }//end-catch

   }//end-if-connect
              else {//Αν προκύψει εξ αρχής πρόβλημα με την σύνδεση στη βάση
               //τότε κατευθείαν επιστρέφεται το παρακάτω μήνυμα
                  resp="ERROR IN CONNECTION!";
                
              }//end-else
           
           return resp;//Επιστροφή τελικής απόκρισης
           
}//end-search

       String getResponse(){
//Συνάρτηση δημιουργίας του String ταυτοποίησης

           String Resp="";
            Random rand=  new Random();//Χρησιμοποιoύμε την κλάση Random για την δημιουργία τυχαίων αριθμών
            String chars= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//Το αλφάβητο που θα χρησιμοποιηθεί για την παραγωγή String
              for (int i = 0; i < 10; i++) {
//Σε κάθε επανάληψη παράγεται ένας τυχαίος αριθμός από 0 έως 25 ο οποίος αντιστοιχεί
//κάθε φορά σε μια θέση του  αρχικού String
                 int num= rand.nextInt(chars.length());

                Resp+= chars.charAt(num);//Το τελικό String θα είναι ο προηγούμενος χαρακτήρας συν τον καινούργιο που προέκυψε

              }//end-for


         return Resp;//Επιστροφή δεκαψήφιου τυχαίου StringID

           }//end-getResponse


       boolean isActive(){//Συνάρτηση που επιστέφει true/false ανάλογα αν το StringID είναι έγκυρο η όχι

          boolean valid=true;//Η flag μεταβλητή που θα επιστραφεί

          long limit=90000;//Το όριο που θέλουμε να διαρκεί κάθε String ταυτοποίησης
          long passed=System.currentTimeMillis();//Ο τρέχων χρόνος που έχει περάσει απο την δημιουργία

       if(passed-created >= limit) {
//Αν ο χρόνος που έχει περάσει είναι πάνω απο 90 δευτερόλεπτα τότε το String ακυρώνεται
           valid=false;//Επθστροφή false αποτελέσματος

       }//end-if

         return valid;//Επιστροφή αποτελέσματος

       }//end-func

       JSONArray getResult(char begin, char end) {
//Συνάρτηση που επιστρέφει ένα JSON πίνακα με τις λέξεις που ταιριάζουν με τα γράμματα ως ορίσματα

             JSONArray words= new JSONArray();//Δημιουργία JSON πίνακα words

          if(connect()){//Αν έχει επιτευχθεί η σύνδεση με την βάση
              
                try{

               dbstate=conn.createStatement();//Δημιουργία statement
              //Ερώτημα sql να επιστραφούν οι εγγραφές των οποίων οι λέξεις ξεκινούν με το πρώτο γράμμα και τελειώνουν με το δεύτερο
               String sql="SELECT * FROM words WHERE word LIKE '"+begin+"%"+end+"' ";

               //Το αποτέλεσμα της αναζήτησης μέσω του interface
               ResultSet rs= dbstate.executeQuery(sql);

               if(!rs.isBeforeFirst()){
//Αν δεν υπάρχουν εγγραφές ως αποτέλεσμα τότε ο πίνακας παίρνει τιμή NO WORDS FOUND
                    words.add("NO WORDS FOUND");

            }//end-if-rows

               while(rs.next()){//Αν υπάρχουν εγγραφές τότε προσθέτουμε στον πίνακα την λέξη από κάθε εγγραφή του αποτελέσματος

                     String word = rs.getString("word");

                    words.add(word);//Προσθήκη εγγραφής στον πίνακα

               }//end-while


               //Κλείσιμο σύνδεσης με την βάση
               conn.close();
               dbstate.close();

                }//end-try

                catch(SQLException e){//Εξαίρεση που μπορεί να προκύψει κατά την χρήση sql εντολών

                     e.getMessage();
                }//end-catch

          }//end-if-connect


          return words;//Επιστροφή αποτελέσματος
       }//end-get-result


       public static DBCheck getInstance(){
//Δημιουργία στιγμιοτύπου της κλάσης για χρήση στα Web Services
        if (inst == null)
            inst = new DBCheck ();
        return inst;//Επιστροφή στιγμιοτύπου

    }//end-getInstance


}//end-class
