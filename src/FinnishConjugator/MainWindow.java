/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FinnishConjugator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 *
 * @author alvaro
 */
public class MainWindow extends JFrame {
    private JLabel label1;
    private JTextField verbField;
    private JButton btnConjugate, btnAddToDatabase;
    private final String nameButton[] = {"conjugate verb", "add verb"};
    
    public MainWindow() {
        // title of the window
        super("Finnish conjugator");
        
        // Create layouts
        JPanel textArea = new JPanel(new GridLayout(3,2)); //(row, column)
        JPanel btnArea = new JPanel(new GridLayout(2,1)); //(row, column)
        
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
                try {
                    new ConjugatedVerbWindow(verbField.getText().trim());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Verb not found");
                }
            }
        });
        
        btnAddToDatabase.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AddToDatabaseWindow();
            }
        });
        
        
        
    }
}
