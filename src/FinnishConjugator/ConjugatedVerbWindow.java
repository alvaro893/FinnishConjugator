/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FinnishConjugator;

import javax.swing.*;
import FinnishConjugator.generateTable.*;
/**
 *
 * @author alvaro
 */
public class ConjugatedVerbWindow extends JFrame {
    GenerateTable verbTable;
    
    public ConjugatedVerbWindow(String verb) throws Exception{
        super(verb);
        /*String [ ] header = {"Present", "perfect"};
        Object [ ] [ ] data = {
                {"Finland", new Integer(1917),},
                {"Estonia", new Integer(1918),},
                {"U.S.A.", new Integer(1776), },
        };*/
        
        // create the table using finnish gramic rules
        verbTable = new GenerateTable(verb);
        Object [ ] [ ] data = verbTable.getData();
        String [] header = verbTable.getHeader();

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
