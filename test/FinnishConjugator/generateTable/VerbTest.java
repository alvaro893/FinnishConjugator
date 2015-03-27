/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FinnishConjugator.generateTable;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alvaro
 */
public class VerbTest {
    Verb instance;     
    public VerbTest() {
         //instance = new Verb("puhua", "1");

    }
    
    void commonTasks(){
        System.out.println("verb stem: " + instance.stem);
        System.out.println("verb harmonia: " + instance.dottedVerb);

    }
    
    @Test
    public void testStem(){
        System.out.println("--testStem--");
         // test stem
         instance = new Verb("puhua", "1");
         commonTasks();
        assertEquals(instance.stem, "puhu");
        
        instance = new Verb("syödä", 2);
        assertEquals(instance.stem, "syö");
    }
    
    @Test
    public void testDotedVerb(){
        System.out.println("--testDotedVerb--");
         // test harmonia
        instance = new Verb("syödä", 2);
        commonTasks();
        assertTrue(instance.dottedVerb);
        
         // words with only i,e vocal
        instance = new Verb("pienni", 2);
        commonTasks();
        assertTrue(instance.dottedVerb);
        
    }
    
    @Test
    public void testPattern(){
        System.out.println("--testPattern--");
        Pattern pattern = Pattern.compile("y|ä|ö");
        Matcher matcher = pattern.matcher("syödä");
        assertTrue(matcher.find());
        
        // words with only i,e vocal
        Pattern patternb = Pattern.compile("u|a|o");
        Matcher matcherb = patternb.matcher("pienni");
        assertFalse(matcherb.find());
    }

    /**
     * Test of getPresent method, of class Verb.
     */
    @Test
    public void testGetPresent() {
        System.out.println("--testGetPresent--");
        instance = new Verb("puhua", 1);  
        
        Integer expResult = new Integer(6);
        Integer result = (Integer)instance.getPresent().length;
        System.out.println(Arrays.deepToString(instance.getPresent()));
        assertEquals(expResult, result);
        assertEquals("hän puhuu", instance.getPresent()[2]);
        
        instance = new Verb("syödä", 2);  
        System.out.println(Arrays.deepToString(instance.getPresent()));
        assertEquals("hän syö", instance.getPresent()[2]);
    }
    
//    @Test
//    public void testKpt(){
//        System.out.println("--testkpt--");
//        instance = new Verb("auttaa", 1);  
//        System.out.println(Arrays.deepToString(instance.getPresent()));
//        assertEquals("minä autan", instance.getPresent()[0]);
//        assertEquals("sinä autat", instance.getPresent()[1]);
//        assertEquals("hän auttaa", instance.getPresent()[2]);
//        
//    }
    
}
