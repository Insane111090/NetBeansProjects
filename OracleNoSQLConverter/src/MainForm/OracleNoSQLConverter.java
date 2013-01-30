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
    static final ConnectionToDBDialog connectionSetupDialoge = new ConnectionToDBDialog();
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConnectionSettings.setBorder(new TitledBorder("Настройка подключения к БД"));
        resultTables.setBorder(new TitledBorder("Список таблиц схемы"));
        
        final JList listOfTables = new JList();//List of result tables from Database
        final JButton connButton = new JButton("Press for connect");//Button pressed for connection
        final JLabel connStatus = new JLabel("Status: ");//Connection status Label in MainForm
        
        /*
         * Creation of "Status" textBox on MainForm in Panel - ConnectionSettings
         */
        final JTextField statusTxt = new JTextField("Not connected");
        statusTxt.setBackground(Color.red);
        statusTxt.setEditable(false);

        ConnectionSettings.add(connStatus, BorderLayout.BEFORE_LINE_BEGINS);
        ConnectionSettings.add(statusTxt, BorderLayout.AFTER_LINE_ENDS);
        ConnectionSettings.add(connButton, BorderLayout.AFTER_LAST_LINE);
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
        connectionSetupDialoge.setModal(true);
        
    }
    public static final class ConnectionToDBDialog extends JDialog{
        
        
    }
}
