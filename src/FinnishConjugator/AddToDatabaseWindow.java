/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinnishConjugator;

import FinnishConjugator.database.MySQLAccess;
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
public class AddToDatabaseWindow extends JFrame {

    JLabel verbLabel, typeLabel, DescLabel;
    JTextField verbField, typeField, DescField;
    JButton cancelButton, addButton;
    MySQLAccess db;

    public AddToDatabaseWindow(MySQLAccess db) throws SQLException {
        // title of the window
        super("Add verb");
        this.db = db;

        // Create layouts
        JPanel textArea = new JPanel(new GridLayout(3, 3)); //(row, column)
        JPanel btnArea = new JPanel(new GridLayout(1, 2)); //(row, column)

        // Initialize Components
        verbLabel = new JLabel("verb");
        typeLabel = new JLabel("type");
        DescLabel = new JLabel("Description");
        verbField = new JTextField();
        typeField = new JTextField();
        DescField = new JTextField();
        addButton = new JButton("add");
        cancelButton = new JButton("Cancel");

        // add components to the layout
        textArea.add(verbLabel);
        textArea.add(typeLabel);
        textArea.add(DescLabel);
        textArea.add(verbField);
        textArea.add(typeField);
        textArea.add(DescField);
        textArea.add(new JLabel());
        textArea.add(new JLabel());
        textArea.add(btnArea);
        btnArea.add(cancelButton);
        btnArea.add(addButton);
        super.add(textArea);

        // some window operations
        setVisible(true);
        this.setSize(500, 150);
        setResizable(false);

        // set the button listeners
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //Destroy the JFrame object
            }
        });

        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // this validate the form and perform a database query
                validateFormAndInsertData();
            }
        });
    }

    private void validateFormAndInsertData() {
        // this validate the form and perform a database query
        if (typeField.getText().isEmpty() | verbField.getText().isEmpty()
                | DescField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "there are empty fields");
        } else {
            try {
                int type = Integer.parseInt(typeField.getText());
                String v = verbField.getText().trim();
                String d = DescField.getText().trim();
                boolean sucess = db.insertVerb(v, type, d);
                if (sucess) {
                    JOptionPane.showMessageDialog(null, "verb added");
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "verb not added");
                }
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "type must be a number");
            } catch (SQLException ex) {
                Logger.getLogger(AddToDatabaseWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
