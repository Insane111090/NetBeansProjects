package MainForm;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import javax.swing.border.TitledBorder;

/**
 * @author agavrilov
 */
public class OracleNoSQLConverter {

    static final JFrame mainForm = new JFrame();
    static final JPanel mainPanel = new JPanel(new BorderLayout());
    static final JPanel ConnectionSettings = new JPanel(new BorderLayout());
    static final JPanel resultTables = new JPanel(new BorderLayout());
    static final JList listOfTables = new JList();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConnectionSettings.setBorder(new TitledBorder("Настройка подключения к БД"));
        resultTables.setBorder(new TitledBorder("Список таблиц схемы"));
        
        
        
        mainPanel.add(ConnectionSettings,BorderLayout.NORTH);
        resultTables.add(listOfTables);
        mainPanel.add(resultTables);
        
        mainForm.setVisible(true);
       // mainForm.setResizable(false);
       mainForm.setSize(500, 500);
        mainForm.setContentPane(mainPanel);
        //mainForm.pack();
        mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainForm.setLocationRelativeTo(null);  
    }
    
}
