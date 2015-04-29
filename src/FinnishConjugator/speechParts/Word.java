/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//      Types of syllables in Finnish
//  1.  CV		ka-la, su-ku, ma-ta-la
//  2.	CVC		kas-tet-ta, ras-tas, mul-lat
//  3.	CVV		tuu-li, maa, sou-taa
//  4.	CVVC		kaar-re, liit-to, raus-ku, ry-tyyt-tää
//  5.	VC		us-ko, ok-sa, au-tu-as
//  6.	V		i-lo, o-sa, a-pe-a, yk-si-ö
//  7.	VV		aa-mu, uu-si, au-ke-aa, ai-to
//  8.	CVCC		kars-ki, vers-tas, lank-ku
//  9.	VVC		ais-ti, uit-taa, as-ti-aan
//10.	VCC		irs-tas, urk-ki-a
package FinnishConjugator.speechParts;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alvaro
 */
public class Word {

    String word;
    public boolean dottedWord;

    public Word(String s) {
        word = s.trim();
        word = word.toLowerCase();

        dottedWord = checkVocalHarmony();

//    word = word.replaceAll("[aeiouäöy]", "V");
//    word = word.replaceAll("[qwrtpsdfghjklzxcvbnm]", "C");
    }

    // Stands for declare if the word is an ö,ä,y word or not
    private boolean checkVocalHarmony() {
        // Remember that if a word has not a ö,ä,y letters but contains ONLY
        // i,e vocals it is also a ö,ä,y word
        Pattern pattern = Pattern.compile("y|ä|ö");
        Matcher matcher = pattern.matcher(word);
        boolean result = matcher.find();
        if (!result) {
            // vocal harmonia caused by i,e
            Pattern pattern2 = Pattern.compile("u|a|o");
            Matcher matcher2 = pattern2.matcher(word);
            result = !matcher2.find();
        }
        return result;
    }

    public static String kpt(String stem, boolean isVahva) {
        // isVahva: if true, this method applies kpt from vahva to heikko
        // stem: word to be "KPTed"

        // vahva is strong, and heikko is weak
//        Verb-type 1.      Vahva --> Heikko  (minä, sinä, me and te ONLY)
//        Verb-type 2.      KPT almost never applies.... see the verbs nähdä and tehdä on the next page.
//        Verb-type 3.      Heikko --> Vahva
//        Verb-type 4.      Heikko --> Vahva
//        Verb-type 5.      KPT doesn't apply.
//        Verb-type 6.      Heikko --> Vahva
        // as you can see some kpt are doble letters, they must be prioriced
        // before the single kpt letters in order to avoid issues
//        String[] vahva = {"kk", "pp", "tt", "k", "p", "t", "rt", "lt", "nt", "nk", "mp"};
//        String[] heikko = {"k", "p", "t", "", "v", "d", "rr", "ll", "nn", "ng", "mm"};
        // TODO
        final String rgxVahva = "(?!^.)(k|n)?k|(?!^.)(m|p)?p|(?!^.)(t|r|l|n)?t";
        final String rgxHeikko = "(?!^.)k|(?!^.)p|(?!^.)t|(?!^.)v|(?!^.)d|(?!^.)n(g|n)|rr|ll|mm";
        String rgx;

        if (isVahva) {
            rgx = rgxVahva;
        } else {
            rgx = rgxHeikko;
        }
        // the regular expresion will match the kpt form in the word
        Pattern pattern = Pattern.compile(rgx);
        Matcher matcher = pattern.matcher(stem);

        // if there is not any kpt it finish here and do nothing
        if (!matcher.find()) {
            return stem;
        } else {
            String[] vahva = {"kk", "pp", "tt", "k", "p", "t", "rt", "lt", "nt", "nk", "mp"};
            String[] heikko = {"k", "p", "t", "", "v", "d", "rr", "ll", "nn", "ng", "mm"};
            String found = matcher.group();

            // Using this hashmap, it will be able to substitute the current kpt 
            // form for other
            HashMap<String, String> rule = new HashMap<>();

            // if true vahva array values will be keys in the hash map
            // otherwise heikko will be the keys
            if (isVahva) {
                for (int i = 0; i < vahva.length; i++) {
                    rule.put(vahva[i], heikko[i]);
                }
            } else {
                for (int i = 0; i < heikko.length; i++) {
                    rule.put(heikko[i], vahva[i]);
                }
            }

            // this replaces the current kpt for the other one
            String resultString = matcher.replaceAll(rule.get(found));
            return resultString;
        }
    }

}
