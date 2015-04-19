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

/**
 *
 * @author Alvaro
 */
public class Word {
    String word;
    int syllableCount = 0;

    public Word(String s) {
        word = s.trim();
                
//        if(word.length() < 3){
//            syllableCount = 1;
//        }else{
            word = word.toLowerCase();
            word = word.replaceAll("[aeiouäöy]", "V");
            word = word.replaceAll("[qwrtpsdfghjklzxcvbnm]", "C");
            syllableCount = word.split("V").length;
            
//        }
        
    }
    
}
