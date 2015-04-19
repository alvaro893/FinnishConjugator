/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinnishConjugator.speechParts;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alvaro
 */
public class WordTest {
    
    public WordTest() {
    }
    
    private void createWord(String s, String result1, int result2){
        Word w = new Word(s);
        assertEquals(result1, w.word);
        assertEquals(result2, w.syllableCount);
    }

    @Test
    public void testCount() {
        //createWord("uida", "VVCV", 2);
        createWord("ui", "VV", 1);
        createWord("ymmärtää", null, 3);
    }
    
}
