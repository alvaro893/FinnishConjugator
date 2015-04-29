/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinnishConjugator.speechParts;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alvaro
 */
public class WordTest {
    
    public WordTest() {
    }
    
//    @Test
//    private void createWord(String s, String result1, int result2){
//        Word w = new Word(s);
//        assertEquals(result1, w.word);
//    }
    
    @Test
    public void ktp(){
        Word w = new Word("heikko");
        String rgxVahva = "(?!^.)(k|n)?k|(?!^.)(m|p)?p|(?!^.)(t|r|l|n)?t";
        String rgxHeikko = "(?!^.)k|(?!^.)p|(?!^.)t|(?!^.)v|(?!^.)d|(?!^.)n(g|n)|rr|ll|mm";
        Pattern pattern = Pattern.compile(rgxVahva);
        Matcher matcher = pattern.matcher(w.word);
        if(!matcher.find())
            System.out.println("not found");
        else{
            System.out.println(matcher.group());
            String[] vahva = {"kk", "pp", "tt", "k", "p", "t", "rt", "lt", "nt", "nk", "mp"};
            String[] heikko = {"k", "p", "t", "", "v", "d", "rr", "ll", "nn", "ng", "mm"};
            String found = matcher.group();
            
            HashMap<String, String> rule = new HashMap<>();
        
            // if true vahva array values will be keys in the hash map
            // otherwise heikko will be the keys
            if(true){
                for (int i = 0; i < vahva.length; i++) {
                        rule.put(vahva[i], heikko[i]);
                }
            }else{
                for (int i = 0; i < heikko.length; i++) {
                        rule.put(heikko[i], vahva[i]);
                }
            }
            
             String resultString = matcher.replaceAll(rule.get(found));
             System.out.println(resultString+"  "+w.word);
        }
        
    }

    /**
     * Test of kpt method, of class Word.
     */
    @Test
    public void testKpt() {
        System.out.println("kpt");
        String stem = "heikko";
        boolean isVahva = true;
        String expResult = "heiko";
        String result;
        result = Word.kpt(stem, isVahva);
        assertEquals(expResult, result);
        
        stem = "heiko";
        expResult = "heikko";
        result = Word.kpt(stem, false);
        assertEquals(expResult, result);
        
    }
    
    
}
