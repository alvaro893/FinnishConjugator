/*
 * This class stands for add Finnish gramatic rules to the verbs from
 * database
 */

package FinnishConjugator.generateTable;

import FinnishConjugator.database.*;
/**
 *
 * @author alvaro
 */
public class GenerateTable {
    String[] pronoums = {"minä", "sinä", "hän", "se", "me", "te", "he"};
    MySQLAccess db;
    
    public GenerateTable(String verb) throws Exception {
        // first read in the database
        db = new MySQLAccess();
        String[] result = db.readVerb(verb);
        System.out.println(result.toString());
    }

    public Object[][] getData() {
        return null;
        
    }

    public String[] getHeader() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
