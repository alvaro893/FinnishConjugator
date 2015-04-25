/*
 *  This class stands for making all the necesary verb operations
 *  this document has fine infoemation  about finnish verbs
 *  http://people.uta.fi/~km56049/finnish/verbs.html
 */
package FinnishConjugator.speechParts;

/**
 *
 * @author alvaro
 */
public class Verb extends Word {

    public String verb; //verb in infinitive
    public String stem; //stem of the verb
    public int type; //the type of the verb
    public String[] activePastParticipe;
    private final String[] pronoums = {"minä", "sinä", "hän", "me", "te", "he"};
    private final String[] negative = {"en", "et", "ei", "emme", "ette", "eivät"};
    private String[] ending = {"n", "t", "", "mme", "tte", "vat"};

    /* constructor */
    public Verb(String verb, Object type) {
        // initialice variables
        super(verb);
        this.verb = verb;

        // if it is recived a type in String format it will converted into int
        if (type instanceof String) {
            this.type = Integer.parseInt((String) type);
        } else {
            this.type = ((Integer) type);
        }

        // this handles the stem
        applyVerbTypeRules();
        
        // this initialize activePastParticipe atributte
        setActivePastParticipe();

        // fix "he" pronoum
        if (dottedWord) {
            ending[5] = "vät";
        }
        // fix "han" pronoum
        if (this.type != 2) {
            ending[2] = stem.substring(stem.length() - 1, stem.length());
        }

        // apply kpt to the stem
        // kpt();
    }

    /* private methods */
    private void applyVerbTypeRules() {
        // to take advantage of StringBuilder methods
        StringBuilder sb = new StringBuilder(verb);

        switch (type) {
            // case 0 is the verb olla
            case 0:
                stem = "ole";
                break;
            // remove the last a/ä
            case 1:
                stem = verb.substring(0, verb.length() - 1);
                break;
            //remove the last 2 char
            case 2:
                stem = verb.substring(0, verb.length() - 2);
                break;
            case 3:
                stem = verb.substring(0, verb.length() - 2) + "e";
                break;
            // remove the 't'
            case 4:
                stem = sb.deleteCharAt(sb.length() - 1).toString();
                break;
            case 5:
                stem = verb.substring(0, verb.length() - 2) + "tse";
                break;
            case 6:
                stem = verb.substring(0, verb.length() - 2) + "ne";
                break;
            // only tehda and nahda
            case 7:
                stem = verb.substring(0, verb.length() - 3) + "ke";
        }

    }
    
    // this finds the active past
    private void setActivePastParticipe(){
        // set local variables
        StringBuilder sb = new StringBuilder(stem);
        String[] ending = new String[2];
        
        // the active past participe depends of the verb type
        switch(type){
            case 1:
            case 2:
                if(dottedWord)
                    ending[0] = "nyt";
                else
                    ending[0] = "nut";
                
                ending[1] = "neet";
                break;
            case 3:
                // the last 'e' of the type 3 must be deleted 
                sb.deleteCharAt(sb.length() - 1);
                // using the last consonant of the stem to get the forms:
                // nut, lut, rut, etc and neet, leet, etc
                char c = sb.charAt(sb.length() - 1);
                if(dottedWord)
                    ending[0] = c + "yt";
                else
                    ending[0] = c + "ut";
                
                ending[1] = c + "eet";
                break;
            case 4:
                // the last consonant of the type 4 must be deleted 
                sb.deleteCharAt(sb.length() - 1);
            case 5:
                if(dottedWord)
                    ending[0] = "nnyt";
                else
                    ending[0] = "nnut";
                
                ending[1] = "nneet";
                break;
        }
        //activePastParticipe = {sb + ending[0], sb + ending[1]};
        activePastParticipe = new String[2];
        activePastParticipe[0] = sb.toString() + ending[0];
        activePastParticipe[1] = sb.toString() + ending[1];
    }


    /* public methods */
    public String[][] getPresent() {

        // add to the string array
        int length = pronoums.length;
        String[][] result = new String[length][2];
        boolean isVahva;

        if (type == 1 || type == 2 || type == 7) {
            isVahva = true;
            for (int i = 0; i < length; i++) {
                // kpt in han(2) and he(5)
                if (i == 2 || i == 5) {
                    result[i][0] = pronoums[i] + " " + stem + ending[i];
                } else {
                    result[i][0] = pronoums[i] + " " + kpt(stem, isVahva) + ending[i];
                }
            }
        } else if (type == 3 || type == 4 || type == 6) {
            isVahva = false;
            for (int i = 0; i < length; i++) {
                result[i][0] = pronoums[i] + " " + kpt(stem, isVahva) + ending[i];
            }
        } else {
            for (int i = 0; i < length; i++) {
                result[i][0] = pronoums[i] + " " + stem + ending[i];
            }
        }

        // fix for olla verb
        if (type == 0) {
            result[2][0] = pronoums[2] + " " + "on";
            result[5][0] = pronoums[5] + " " + "ovät";
        }
        // negative column
        for (int i = 0; i < length; i++) {
            result[i][1] = negative[i] + " " + stem;
        }

        return result;
    }

    public String[][] getImperfect() throws Exception {
        
        StringBuilder sb = new StringBuilder(stem);
        char lastCh = stem.charAt(stem.length() - 1);
        switch (type) {
            case 1:
            case 3:
            case 5:
                if (lastCh == 'o' || lastCh == 'ö' || lastCh == 'u' || lastCh == 'y') {
                    sb.append('i');
                }
                if (lastCh == 'a' || lastCh == 'ä' || lastCh == 'e' || lastCh == 'i') {
                    sb.deleteCharAt(stem.length() - 1);
                    sb.append('i');
                }
                break;
            case 2:
            case 7:
                if (!verb.equals("käydä")) {
                    sb.deleteCharAt(stem.length() - 1);
                    sb.append('i');
                } else {
                    sb = new StringBuilder("käv");
                }
                break;
            case 4:
                sb.deleteCharAt(stem.length() - 1);
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

        if (type == 1 || type == 2 || type == 7) {
            isVahva = true;
            for (int i = 0; i < length; i++) {
                // kpt in han(2) and he(5)
                if (i == 2 || i == 5) {
                    result[i][0] = pronoums[i] + " " + iStem + ending[i];
                } else {
                    result[i][0] = pronoums[i] + " " + kpt(iStem, isVahva) + ending[i];
                }
            }
        } else if (type == 3 || type == 4 || type == 6) {
            isVahva = false;
            for (int i = 0; i < length; i++) {
                result[i][0] = pronoums[i] + " " + kpt(iStem, isVahva) + ending[i];
            }
        } else {
            for (int i = 0; i < length; i++) {
                result[i][0] = pronoums[i] + " " + iStem + ending[i];
            }
        }
        // negative column

        switch (type) {
            case 1:
            case 2:
                for (int i = 0; i < length; i++) {
                    String negativeEnding;
                    if(i < 3){
                        if(dottedWord)
                            negativeEnding = "nyt";
                        else
                            negativeEnding = "nut";
                    }else
                    result[i][1] = negative[i] + " " + stem;
                }
                break;
        }

        return result;
    }
}
