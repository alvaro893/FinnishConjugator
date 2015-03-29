/*
 *  This class stands for making all the necesary verb operations
 *  this document has fine infoemation  about finnish verbs
 *  http://people.uta.fi/~km56049/finnish/verbs.html
 */

package FinnishConjugator.speechParts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author alvaro
 */
public class Verb {
    public String verb; //verb in infinitive
    public String stem; //stem of the verb
    public int type; //the type of the verb
    public boolean dottedVerb;
    private final String[] pronoums = {"minä", "sinä", "hän", "me", "te", "he"};
    private final String[] negative = {"en", "et", "ei", "emme", "ette", "eivät"};
    private String[] ending;
    
    /* constructor */ 
    public Verb(String verb, Object type) {
        // initialice variables
        this.ending = new String[]{"n", "t", "", "mme", "tte", "vat"};
        this.verb = verb;
        
        // if it is recived a type in String format it will converted into int
        if(type instanceof String)
            this.type = Integer.parseInt((String)type);
        else
            this.type = ((Integer) type).intValue();
        applyVerbTypeRules();
        dottedVerb = checkVocalHarmony();
        
        // fix "he" pronoum
        if(dottedVerb)
            ending[5] = "vät";
        // fix "han" pronoum
        if(this.type != 2)
            ending[2] = stem.substring(stem.length()-1, stem.length());
        
        // apply kpt to the stem
       // kpt();
    }   
    
    
    /* private methods */
    private void applyVerbTypeRules(){
        // to take advantage of StringBuilder methods
        StringBuilder sb = new StringBuilder(verb);
        
        switch(type){
            // case 0 is the verb olla
            case 0: stem = "ole" ;
                    break;
            // remove the last a/ä
            case 1: stem = verb.substring(0, verb.length()-1) ;
                    break;
            //remove the last 2 char
            case 2: stem = verb.substring(0, verb.length()-2);
                    break;
            case 3: stem = verb.substring(0, verb.length()-2) + "e";
                    break;
            // remove the 't'
            case 4: stem = sb.deleteCharAt(sb.length()-1).toString();
                    break;
            case 5: stem = verb.substring(0, verb.length()-2) + "tse";
                    break;
            case 6: stem = verb.substring(0,verb.length()-2) + "ne";
                    break;
            // only tehda and nahda
            case 7: stem = verb.substring(0, verb.length()-3) + "ke" ;
        } 
            
    }
    
    // Apply the kpt rules to a stem of a verb
//    private void kpt(){
//        StringBuilder sb = new StringBuilder(stem);        
//        // vahva is strong, and heikko is weak
////        Verb-type 1.      Vahva --> Heikko  (minä, sinä, me and te ONLY)
////        Verb-type 2.      KPT almost never applies.... see the verbs nähdä and tehdä on the next page.
////        Verb-type 3.      Heikko --> Vahva
////        Verb-type 4.      Heikko --> Vahva
////        Verb-type 5.      KPT doesn't apply.
////        Verb-type 6.      Heikko --> Vahva
//        String[] vahva = {"kk", "pp", "tt", "k", "p", "t", "rt", "lt", "nt", "nk", "mp"};
//        String[] heikko = {"k", "p", "t", "", "v", "d", "rr", "ll", "nn", "ng", "mm"};
//        // position to start is the middle of the string
//        int startPosition = 0;//(int) Math.floor((double)stem.length()/2);
//        // position where the kpt was found
//        int indexKpt;
//        
//        for (int i = 0; i < heikko.length; i++) {
//            indexKpt = stem.indexOf(vahva[i], startPosition);
//            if(indexKpt != -1){
//                sb.delete(indexKpt, indexKpt+1);
//                stem = sb.insert(indexKpt, heikko[i]).toString();
//                break;
//            }
//        }
//     }
    
    // Stands for declare if the current objet verb is an ö,ä,y word or not
    private boolean checkVocalHarmony(){
        // Remember that if a word has not a ö,ä,y letters but contains ONLY
        // i,e vocals it is also a ö,ä,y word
        Pattern pattern = Pattern.compile("y|ä|ö");
        Matcher matcher = pattern.matcher(verb);
        boolean result = matcher.find();
        if(!result){
            // vocal harmonia caused by i,e
            Pattern pattern2 = Pattern.compile("u|a|o");
            Matcher matcher2 = pattern2.matcher(verb);
            result = !matcher2.find();
        }
            return result;
    }
    
    
    /* public methods */
    public String[][] getPresent(){
       
        // add to the string array
        int length = pronoums.length;
        String[][] result = new String[length][2];
        for (int i = 0; i < length; i++) {
            result[i][0] = pronoums[i] + " " + stem + ending[i];
        }
        for(int i = 0; i < length; i++){
            result[i][1] = negative[i] + " " + stem;
        }

        return result;
    }
}
