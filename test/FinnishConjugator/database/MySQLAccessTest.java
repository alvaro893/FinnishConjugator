/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FinnishConjugator.database;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alvaro
 */
public class MySQLAccessTest {
    
    public MySQLAccessTest() {
    }
    

    /**
     * Test of readVerb method, of class MySQLAccess.
     */
    @Test
    public void testReadVerb() throws Exception {
        System.out.println("readVerb");
        String verb = "juoda";
        MySQLAccess instance = new MySQLAccess();
        String expResult = "juoda";
        String result = instance.readVerb(verb)[2];
        assertEquals(expResult, result);
        instance.close();
    }

    /**
     * Test of insertVerb method, of class MySQLAccess.
     */
    @Test
    public void testInsertVerb() throws Exception {
        System.out.println("insertVerb");
        String verb = "falseverb";
        int type = 1;
        String description = "falseverb";
        MySQLAccess instance = new MySQLAccess();
        boolean expResult = true;
        boolean result = instance.insertVerb(verb, type, description);
        assertEquals(expResult, result);
        assertTrue(instance.deleteVerb(verb));
        instance.close();
    }    
}
