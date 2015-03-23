package FinnishConjugator;

import FinnishConjugator.database.MySQLAccess;
/**
 *
 * @author Alvaro Bolanos Rodriguez
 */
public class FinnishConjugator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        MySQLAccess dao = new MySQLAccess();
        dao.readDataBase();
    }
    
}
