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
    public String participleStem; // sten of the participles form
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
                participleStem = "ol";
                break;
            // remove the last a/ä
            case 1:
                stem = verb.substring(0, verb.length() - 1);
                participleStem = stem;
                break;
            //remove the last 2 char
            case 2:
                stem = verb.substring(0, verb.length() - 2);
                participleStem = stem;
                break;
            case 3:
                participleStem = verb.substring(0, verb.length() - 2);
                stem = participleStem + "e";
                break;
            // remove the 't'
            case 4:
                participleStem = verb.substring(0, verb.length() - 2);
                stem = participleStem + "a";
                break;
            case 5:
                participleStem = verb.substring(0, verb.length() - 2);
                stem = participleStem + "tse";
                break;
            case 6:
                participleStem = verb.substring(0, verb.length() - 2);
                stem = participleStem + "ne";
                break;
            // only tehdä and nähda
            case 7:
                // participle stem is teh- and näh-
                participleStem = verb.substring(0, verb.length() - 2);
                stem = verb.substring(0, verb.length() - 3) + "ke";
        }

    }

    // this finds the active past
    private void setActivePastParticipe() {
        // set local variables
        StringBuilder sb = new StringBuilder(participleStem);
        String[] ending = new String[2];

        // the active past participe depends of the verb type
        switch (type) {
            case 1:
            case 2:
                if (dottedWord) {
                    ending[0] = "nyt";
                } else {
                    ending[0] = "nut";
                }

                ending[1] = "neet";
                break;
            case 0:
            case 3:
                // using the last consonant of the stem to get the forms:
                // nut, lut, rut, etc and neet, leet, etc
                char c = sb.charAt(sb.length() - 1);
                if (dottedWord) {
                    ending[0] = c + "yt";
                } else {
                    ending[0] = c + "ut";
                }

                ending[1] = c + "eet";
                break;
            case 4:
            case 5:
            case 6:
                if (dottedWord) {
                    ending[0] = "nnyt";
                } else {
                    ending[0] = "nnut";
                }

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
        int lastChIdx = stem.length() - 1;
        char lastCh = stem.charAt(lastChIdx);
        switch (type) {
            case 0:
            case 1:
                // TODO: page 213 of the book, implement silables

                if (stem.matches("nt(aa|ää)|lt(aa|ää)|rt(aa|ää)")) {
                    sb.substring(0, lastChIdx);
                    sb.append("si");
                }
            case 3:
            case 5:
            case 6:
                if (lastCh == 'o' || lastCh == 'ö' || lastCh == 'u' || lastCh == 'y') {
                    sb.append('i');
                }
                if (lastCh == 'a' || lastCh == 'ä' || lastCh == 'e' || lastCh == 'i') {
                    sb.deleteCharAt(lastChIdx);
                    sb.append('i');
                }
                break;
            case 2:
            case 7:
                switch (verb) {
                    case "käydä":
                        sb = new StringBuilder("käv");
                        break;
                    case "uidä":
                        sb = new StringBuilder("ui");
                        break;
                    default:
                        char ch = sb.charAt(lastChIdx - 1);
                        if (ch == 'u' || ch == 'y') {
                            if (dottedWord) {
                                sb.replace(lastChIdx - 1, lastChIdx + 1, "öi"); //syödä
                            } else {
                                sb.replace(ch, lastChIdx, "oi");
                            }
                        } else if (ch == 'i') {
                            sb.replace(lastChIdx - 1, lastChIdx + 1, "ei"); //viedä
                        } else {
                            sb.deleteCharAt(lastChIdx);
                            sb.append('i');
                        }
                        break;
                }
                break;
            case 4:
                // this removes the last 'a'
                sb.deleteCharAt(lastChIdx);
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
                    if (i < 3) {
                        if (dottedWord) {
                            negativeEnding = "nyt";
                        } else {
                            negativeEnding = "nut";
                        }
                    } else {
                        result[i][1] = negative[i] + " " + stem;
                    }
                }
                break;
        }

        return result;
    }

    public String[][] getPerfect() {
        String[][] result = new String[pronoums.length][2];
        String[] verbOllaPresent = {"olen", "olet", "on", "olemme", "olette", "olevat"};
        
        // positive part
        for(int i = 0; i < verbOllaPresent.length; i++){
            // j is for separete plural pronoums from singular ones
            int j;
            if(i > 2){
                j = 1;
            }else{
                j = 0;
            }
            
            result[i][0] = pronoums[i] + " " + verbOllaPresent[i] + " " + activePastParticipe[j];
        }
        
        // negaive part
        for(int i = 0; i < verbOllaPresent.length; i++){
            // j is for separete plural pronoums from singular ones
            int j;
            if(i > 2){
                j = 1;
            }else{
                j = 0;
            }
            
            result[i][1] = negative[i] + " " + "ole" + " " + activePastParticipe[j];
        }
        return result;
    }

    
}
