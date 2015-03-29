
/*
 * This class will display the conjugation from input user
 * using a Jtable
 */

package FinnishConjugator;

import FinnishConjugator.speechParts.Verb;
import FinnishConjugator.database.MySQLAccess;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author alvaro
 */
public class ConjugatedVerbWindow extends JFrame {
     Object [] [] data;
     String [] header;
      MySQLAccess db;
     
    public ConjugatedVerbWindow(String input){
        super(input);
        /*String [ ] header = {"Present", "perfect"};
        Object [ ] [ ] data = {
                {"Finland", new Integer(1917),},
                {"Estonia", new Integer(1918),},
                {"U.S.A.", new Integer(1776), },
        };*/
        // first read in the database
        try {
            db = new MySQLAccess();
            String[] result = db.readVerb(input);
            System.out.println("retrieved verb:" + result[2] + " = " + result[3]);

            // Obtain the verb object
            Verb v = new Verb(result[2], result[1]);

            // create the table using speechParts package
            data = null;
            header = new String[2];
            header[0] = "Present of " + result[2] + " " + "(" + result[3] + ")";
            header[1] = "Present (negative)";
            addToData(v);
            
            // creating table view
            JTable t = new JTable(data, header);
            JScrollPane sp = new JScrollPane(t);
            this.add(sp);
            
            // some window operations
            setSize(600, 200);
            setVisible(true);
            
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "SQL driver not installed");            
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Verb not found");
         } catch (Exception ex) {
             Logger.getLogger(ConjugatedVerbWindow.class.getName()).
                     log(Level.SEVERE, null, ex);
         } finally {
           db.close();
        }
    }
    
    private void addToData(Verb v){
        String[][] present = v.getPresent();
        data = new Object[present.length][2];
        // test
        
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < present.length; i++) {
                data[i][j] = present[i][j];  
            }
        }
        System.out.println("data:"+Arrays.deepToString(data));
    }
}
