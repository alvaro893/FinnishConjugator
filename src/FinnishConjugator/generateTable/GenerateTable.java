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
    String[] result;
    
    public GenerateTable(String verb) throws Exception {
        // first read in the database
        MySQLAccess db = new MySQLAccess();
        result = db.readVerb(verb);
        System.out.println("retrieved verb:"+result[2]+" = "+result[3]);
    }

    public Object[][] getData() {
        return null;
        // test
//        Object[][] data = {
//            {pronoums[0]+" ", result[2]+"n"},
//            {pronoums[1]+" ", result[2]+"t"},
//            {pronoums[2]+" ", result[2]+""}
//        };
//        return data;
        
    }

    public String[] getHeader() {
        //test
        String[] header = {"p","verb"};
        return header;
    }
    
    
    
}
