/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FinnishConjugator;

import FinnishConjugator.database.MySQLAccess;
import javax.swing.*;
import FinnishConjugator.generateTable.*;
/**
 *
 * @author alvaro
 */
public class ConjugatedVerbWindow extends JFrame {
    GenerateTable verbTable;
    
    public ConjugatedVerbWindow(String input) throws Exception{
        super(input);
        /*String [ ] header = {"Present", "perfect"};
        Object [ ] [ ] data = {
                {"Finland", new Integer(1917),},
                {"Estonia", new Integer(1918),},
                {"U.S.A.", new Integer(1776), },
        };*/
        // first read in the database
        MySQLAccess db = new MySQLAccess();
        String[] result = db.readVerb(input);
        System.out.println("retrieved verb:"+result[2]+" = "+result[3]);
        
        // Obtain the verb tenses
        Verb v = new Verb(result[2], result[3]);
        // create the table using finnish gramic rules
        Object [ ] [ ] data;
        String [] header;
        
        
        
        

        JTable t = new JTable(data, header);
        JScrollPane sp = new JScrollPane(t);
        this.add(sp);
        setSize(400, 200);
        if(true)
            setVisible(true);
        else
            JOptionPane.showMessageDialog(null, "Verb not found");
    }
}
