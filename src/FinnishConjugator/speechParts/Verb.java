/*
 *  This class stands for making all the necesary verb operations
 *  this document has fine infoemation  about finnish verbs
 *  http://people.uta.fi/~km56049/finnish/verbs.html
 */

package FinnishConjugator.speechParts;

import java.util.HashMap;
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
    // if vahva is true this method applies kpt from vahva to heikko
    public String kpt(String stem, boolean isVahva){
        
        StringBuilder sb = new StringBuilder(stem);        
        // vahva is strong, and heikko is weak
//        Verb-type 1.      Vahva --> Heikko  (minä, sinä, me and te ONLY)
//        Verb-type 2.      KPT almost never applies.... see the verbs nähdä and tehdä on the next page.
//        Verb-type 3.      Heikko --> Vahva
//        Verb-type 4.      Heikko --> Vahva
//        Verb-type 5.      KPT doesn't apply.
//        Verb-type 6.      Heikko --> Vahva
        // as you can see some kpt are doble letters, they must be prioriced
        // before the single kpt letters in order to avoid issues
        String[] vahva = {"kk", "pp", "tt", "k", "p", "t", "rt", "lt", "nt", "nk", "mp"};
        String[] heikko = {"k", "p", "t", "", "v", "d", "rr", "ll", "nn", "ng", "mm"};
        
        
        // vahva goes as a key and heikko as a value
        HashMap<String, String> rule = new HashMap<>();
        
        // if true vahva array will be keys in the hash map
        // otherwise heikko will be the keys
        if(isVahva == true){
            for (int i = 0; i < vahva.length; i++) {
                    rule.put(vahva[i], heikko[i]);
            }
        }else{
            for (int i = 0; i < vahva.length; i++) {
                    rule.put(heikko[i], vahva[i]);
            }       
        }
        
        String matched = null;
        for (String key: rule.keySet()) {
            /*System.out.println("key:"+key+"-"+
            stem.replace(key, rule.get(key)));//test*/            
            String result = stem.replace(key, rule.get(key));
            // if result dont macht stem we found something
            if(!result.equals(stem))
                // skip if the kpt was found in the firs letter of the stem
                if(!(key.length() == 1 && stem.charAt(0) == key.charAt(0)))
                    // we priorice the doble character kpt in order to avoid
                    // for instance change a 'k' inside of 'kk'
                    if(key.length() > 1){
                        return result;
                    }else{
                        matched = result;
                    }     
        }
        if(matched != null)
            return matched;
        else
            return stem;
        
     }
    
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
        boolean isVahva;
                
        if(type == 1 || type == 2 || type == 7 ){
            isVahva = true;
            for (int i = 0; i < length; i++) {
            // kpt in han(2) and he(5)
            if(i == 2 || i == 5)
                result[i][0] = pronoums[i] + " " + stem + ending[i];
            else
                result[i][0] = pronoums[i] + " " + kpt(stem, isVahva) + ending[i];
            }
        }else if(type == 3 || type == 4 || type == 6){
            isVahva = false;
            for (int i = 0; i < length; i++) {
                result[i][0] = pronoums[i] + " " + kpt(stem, isVahva) + ending[i];
            }
        }else{
            for (int i = 0; i < length; i++) {
                result[i][0] = pronoums[i] + " " + stem + ending[i];
            }
        }
        
        
        // fix for olla verb
        if(type == 0){
            result[2][0] = pronoums[2] + " " + "on";
            result[5][0] = pronoums[5] + " " + "ovät";
        }
        // negative column
        for(int i = 0; i < length; i++){
            result[i][1] = negative[i] + " " + stem;
        }

        return result;
    }
    
    public String[][] getImperfect() throws Exception{
        StringBuilder sb = new StringBuilder(stem);
        char lastCh = stem.charAt(stem.length()-1);
        switch(type){
            case 1:
            case 3:
            case 5:
                if(lastCh == 'o' || lastCh == 'ö' || lastCh == 'u' || lastCh == 'y')
                    sb.append('i');
                if(lastCh == 'a' || lastCh == 'ä' || lastCh == 'e' || lastCh == 'i'){
                    sb.deleteCharAt(stem.length()-1);
                    sb.append('i');
                }
                break;
            case 2:
            case 7:
                if(!verb.equals("uida") || !verb.equals("voida") || !verb.equals("imuroida")){
                    sb.deleteCharAt(stem.length()-1);
                    sb.append('i');
                }
                break;
            case 4:
                sb.deleteCharAt(stem.length()-1);
                sb.append("si");
                break;
            default:
                Exception e = null;
                throw e;
        }
        
        String iStem = sb.toString();
        int length = pronoums.length;
        String[][] result = new String[length][2];
        boolean isVahva;
                
        if(type == 1 || type == 2 || type == 7 ){
            isVahva = true;
            for (int i = 0; i < length; i++) {
            // kpt in han(2) and he(5)
            if(i == 2 || i == 5)
                result[i][0] = pronoums[i] + " " + iStem + ending[i];
            else
                result[i][0] = pronoums[i] + " " + kpt(iStem, isVahva) + ending[i];
            }
        }else if(type == 3 || type == 4 || type == 6){
            isVahva = false;
            for (int i = 0; i < length; i++) {
                result[i][0] = pronoums[i] + " " + kpt(iStem, isVahva) + ending[i];
            }
        }else{
            for (int i = 0; i < length; i++) {
                result[i][0] = pronoums[i] + " " + iStem + ending[i];
            }
    }
        // negative column
        for(int i = 0; i < length; i++){
            result[i][1] = negative[i] + " " + iStem;
        }

        return result;
    }  
}
