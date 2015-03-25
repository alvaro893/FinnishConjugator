/*
 *  This class stands for making all the necesary verb operations
 *  
 */

package FinnishConjugator.generateTable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author alvaro
 */
public class Verb {
    String verb; //verb in infinitive
    String stem; //stem of the verb
    int type; //the type of the verb
    boolean dottedVerb;
    private final String[] pronoums = {"minä", "sinä", "hän", "me", "te", "he"};
    
    /* constructor */ 
    public Verb(String verb, Object type) {
        this.verb = verb;
        if(type instanceof String)
            this.type = Integer.parseInt((String)type);
        else
            this.type = ((Integer) type).intValue();
        applyVerbTypeRules();
        dottedVerb = checkVocalHarmony();
    }   
    
    
    /* private methods */
    private void applyVerbTypeRules(){
        // to take advantage of StringBuilder methods
        StringBuilder sb = new StringBuilder(verb);
        
        switch(type){
            case 0: ;
                    break;
            // remove the last a/ä
            case 1: stem = verb.substring(0, verb.length()-2) ;
                    break;
            //remove the last 2 char
            case 2: stem = verb.substring(0, verb.length()-3);
                    break;
            case 3: stem = verb.substring(0, verb.length()-3) + "e";
                    break;
            // remove the 't'
            case 4: stem = sb.deleteCharAt(sb.length()-2).toString();
                    break;
            case 5: stem = verb.substring(0, verb.length()-3) + "tse";
                    break;
            case 6: stem = verb.substring(0,verb.length()-3) + "ne";
                    break;
        } 
            
    }
    
    // Apply the kpt rules to a stem of a verb
    private String kpt(String stem){
        /*not implement yet*/
        return null;
    }
    
    // Stands for declare if the current objet verb is an ö,ä,y word or not
    private boolean checkVocalHarmony(){
        // Remember that if a word has not a ö,ä,y letters but contains ONLY
        // i,e vocals it is also a ö,ä,y word
        Pattern pattern = Pattern.compile("öäy");
        Matcher matcher = pattern.matcher(verb);
        boolean result = matcher.find();
        if(!result){
            // this is going to fail
            // TODO: fix this!!
            pattern = Pattern.compile("ie");
            matcher = pattern.matcher(verb);
            result = matcher.find();
        }
            return result;
    }
    
    
    /* public methods */
    public String[] getPresent(){
        final String[] ending = {"n", "t", "", "mme", "tte",
            "vat", "vät"};
        String[] result = new String[pronoums.length];
        for (String person : result) {
            person = stem + ending;
        }
        return null;
    }
}
