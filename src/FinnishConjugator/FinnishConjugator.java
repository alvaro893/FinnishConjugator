package FinnishConjugator;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alvaro Bolanos Rodriguez
 */
public class FinnishConjugator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            new MainWindow();
        } catch (SQLException ex) {
            Logger.getLogger(FinnishConjugator.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(FinnishConjugator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
