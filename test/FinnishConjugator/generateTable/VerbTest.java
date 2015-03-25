/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FinnishConjugator.generateTable;

import junit.framework.TestCase;

/**
 *
 * @author alvaro
 */
public class VerbTest extends TestCase {
    
    public VerbTest(String testName) {
        super(testName);
    }

    /**
     * Test of getPresent method, of class Verb.
     */
    public void testGetPresent() {
        System.out.println("getPresent test");
        Verb instance = null;
        int expResult = 7;
        int result = instance.getPresent().length;
        System.out.println(result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
