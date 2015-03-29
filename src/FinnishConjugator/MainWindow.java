/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinnishConjugator;

import FinnishConjugator.database.MySQLAccess;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author alvaro
 */
public class MainWindow extends JFrame {

    private JLabel label1;
    private JTextField verbField;
    private JButton btnConjugate, btnAddToDatabase;
    private final String nameButton[] = {"conjugate verb", "add verb"};
    MySQLAccess db;

    public MainWindow() throws SQLException, ClassNotFoundException {
        // title of the window
        super("Finnish conjugator");

        // create database object
        db = new MySQLAccess();

        // Create layouts
        JPanel textArea = new JPanel(new GridLayout(3, 2)); //(row, column)
        JPanel btnArea = new JPanel(new GridLayout(2, 1)); //(row, column)

        // Set up components
        label1 = new JLabel("Write verb");
        verbField = new JTextField("puhua");
        verbField.setEditable(true);
        btnConjugate = new JButton(nameButton[0]);
        btnAddToDatabase = new JButton(nameButton[1]);

        // add components to layouts
        textArea.add(label1);
        textArea.add(verbField);
        btnArea.add(btnAddToDatabase);
        btnArea.add(btnConjugate);

        // Organize the Panels inside the Frame
        super.add(textArea, BorderLayout.CENTER);
        super.add(btnArea, BorderLayout.LINE_END);

        // Some window operations
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 200);
        this.setVisible(true);
        setResizable(false);

        // event listeners
        // click in conjugate button
        btnConjugate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new ConjugatedVerbWindow(verbField.getText().trim(), db);
            }
        });

        btnAddToDatabase.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AddToDatabaseWindow(db);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
}
