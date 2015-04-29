/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FinnishConjugator.speechParts;

import FinnishConjugator.speechParts.Verb;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alvaro
 */
public class VerbTest {
    List<Verb> listOfVerbs = new LinkedList<>();
    Verb instance;
    
    public VerbTest() {
        // instance = new Verb("puhua", "1");
        listOfVerbs.add(new Verb("olla", "0"));
        listOfVerbs.add(new Verb("puhua", "1"));
        listOfVerbs.add(new Verb("syödä", 2));
        listOfVerbs.add(new Verb("tulla", "3"));
        listOfVerbs.add(new Verb("pestä", "3"));
        listOfVerbs.add(new Verb("tavata", "4"));
        listOfVerbs.add(new Verb("tarvita", "5"));
        listOfVerbs.add(new Verb("vaaleta", "6"));


    }
    
    void commonTasks(){
        System.out.println("verb stem: " + instance.stem);
        System.out.println("verb harmonia: " + instance.dottedWord);

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
        assertTrue(instance.dottedWord);
        
         // words with only i,e vocal
        instance = new Verb("pienni", 2);
        commonTasks();
        assertTrue(instance.dottedWord);
        
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
       // System.out.println(Arrays.deepToString(instance.getPresent()));
        assertEquals(expResult, result);
        assertEquals("hän puhuu", instance.getPresent()[2][0]);
        assertEquals("ei puhu", instance.getPresent()[2][1]);
        
        instance = new Verb("syödä", 2);  
       // System.out.println(Arrays.deepToString(instance.getPresent()));
        assertEquals("hän syö", instance.getPresent()[2][0]);
        assertEquals("ei syö", instance.getPresent()[2][1]);        
    }
    
    @Test
    public void testKpt(){
        System.out.println("--testkpt--");
        instance = new Verb("ymmärtää", 1);  
        assertEquals("minä ymmärrän", instance.getPresent()[0][0]);
        instance = new Verb("lukea", 1); 
        assertEquals("minä luen", instance.getPresent()[0][0]);
         instance = new Verb("tehdä", 7); 
        assertEquals("minä teen", instance.getPresent()[0][0]);
        
        instance = new Verb("auttaa", 1);  
        assertEquals("minä autan", instance.getPresent()[0][0]);
        assertEquals("sinä autat", instance.getPresent()[1][0]);
        assertEquals("hän auttaa", instance.getPresent()[2][0]);
        
        
        
    }
    
    @Test
    public void testGetImperfect() throws Exception{
        System.out.println("--testImperfect--");
        
        // try this particular verb
//        instance = new Verb("lentää", 1);  
//        assertEquals("minä lensin", instance.getImperfect()[0][0]);
        
        
        String[] expected = {"minä olin", "minä puhuin", "minä söin", "minä tulin",
            "minä pesin", "minä tapasin", "minä tarvitsin", "minä vaalenin" };
        
        for(Verb v : listOfVerbs){
            assertEquals(expected[listOfVerbs.indexOf(v)], v.getImperfect()[0][0]);
        }
        
    }
    
    @Test
    public void testActivePastParticipe() {
        System.out.println("--testActivePastParticipe--");
        String[] expected1 = { "ollut", "puhunut", "syönyt", "tullut", "pessyt", "tavannut", "tarvinnut", "vaalennut"};
        String[] expected2 = { "olleet", "puhuneet", "syöneet", "tulleet", "pesseet", "tavanneet", "tarvinneet", "vaalenneet"};
        
        for(Verb v : listOfVerbs){
            assertEquals(expected1[listOfVerbs.lastIndexOf(v)], v.activePastParticipe[0]);
        }
        
        for(Verb v : listOfVerbs){
            assertEquals(expected2[listOfVerbs.indexOf(v)], v.activePastParticipe[1]);
        }
    }
    
    @Test
    public void testGetPerfect(){
        System.out.println("--testActivePastParticipe--");
        instance = new Verb("tulla", 3);
        String expected = "me olemme tulleet";
        assertEquals(expected, instance.getPerfect()[3][0]);
    }
    
}
