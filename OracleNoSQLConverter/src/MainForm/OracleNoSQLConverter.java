package MainForm;

import java.awt.*;
import java.awt.event.ActionEvent;
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
    static String server,sid,port,username,password,url;
    final static String driverName = "oracle.jdbc.driver.OracleDriver";
    static Connection connection;
    static boolean isConnected;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConnectionSettings.setBorder(new TitledBorder("Настройка подключения к БД"));
        resultTables.setBorder(new TitledBorder("Список таблиц схемы"));
        
        final JList listOfTables = new JList();//List of result tables from Database
        final JButton openConnectionSetup = new JButton("Configure connection");//Button pressed for configure connection
        openConnectionSetup.setToolTipText("Press for enter connection setup");//ToolTip for button
        final JLabel connStatus = new JLabel("Status: ");//Connection status Label in MainForm
        final JLabel urlconn = new JLabel("URL:");
        final JTextField connectedUrltxt = new JTextField();
        connectedUrltxt.setEditable(false);
        
        /*
         * Creation of "Status" textBox on MainForm in Panel - ConnectionSettings
         */
        final JTextField statusTxt = new JTextField("Not connected");
        statusTxt.setBackground(Color.red);
        statusTxt.setEditable(false);
        
        /*
         * On "Configure connection" button click event
         */
        openConnectionSetup.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                connectionSetupDialoge.setTitle("Connection settings");
                connectionSetupDialoge.setSize(200,200);
                connectionSetupDialoge.setVisible(true);  
            }
        });
        
        /*
         * Adding elements on MainForm window
         */
        ConnectionSettings.add(connStatus,BorderLayout.BEFORE_LINE_BEGINS );
        ConnectionSettings.add(statusTxt, BorderLayout.AFTER_LINE_ENDS);
        ConnectionSettings.add(openConnectionSetup, BorderLayout.AFTER_LAST_LINE);
        ConnectionSettings.add(urlconn, BorderLayout.NORTH);
        //ConnectionSettings.add(connectedUrltxt, BorderLayout);
        mainPanel.add(ConnectionSettings, BorderLayout.PAGE_START);
        resultTables.add(listOfTables);
        mainPanel.add(resultTables, BorderLayout.CENTER);
        mainForm.setTitle("MainForm");
        mainForm.setVisible(true);
       // mainForm.setResizable(false);
        mainForm.setSize(500, 500);
        mainForm.setContentPane(mainPanel);
        //mainForm.pack();
        mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainForm.setLocationRelativeTo(null);//Appears on the screen center
        connectionSetupDialoge.setModal(true);//Makes window modal
        
    }
    /*
     * Class for connection to Database, where user enter server, port, username and password
     */
    public static final class ConnectionToDBDialog extends JDialog{
        
        final JPanel ConnectionPanel = new JPanel(new BorderLayout());
        final JLabel serverLbl = new JLabel("Server: ");
        final JLabel portLbl = new JLabel("Port: ");
        final JLabel sidLbl = new JLabel("SID: ");
        final JLabel usernameLbl = new JLabel("Username: ");
        final JLabel passwordLbl = new JLabel("Password: ");
        final static JTextField serverTxt = new JTextField();//Field for server input
        final static JTextField portTxt = new JTextField();//Field for port input
        final static JTextField sidTxt = new JTextField();//Field for sid input
        final static JTextField usernameTxt = new JTextField();//Field for username input
        final static JPasswordField passwordTxt = new JPasswordField();//Field for password input
        final static JTextField Status_connection_txt = new JTextField();//Field for connection status(Failed or not)
        final static JTextField conn_res_txt = new JTextField();//Field for connection url
        final static JTextField Connection_error_txt = new JTextField();//Connection error
        static JButton ConnectButton = new JButton("Connect");
        static JButton OkButton = new JButton("Ok");
        static JButton CancelButton = new JButton("Cancel");
        
        /*
         * Procedure wich connects us to DB
         */
         public static void SetConnection()
            {
                try{
                    Class.forName(driverName);
                    server = serverTxt.getText().toString();//"oracle.avalon.ru"
                    sid = sidTxt.getText().toUpperCase().toString();//"ORCL";
                    port =portTxt.getText().toString();//"1521";
                    username = usernameTxt.getText().toString();//"andgavr";
                    password = new String(passwordTxt.getPassword());//"andgavr";
                    url = "jdbc:oracle:thin:@" + server + ":" + port + ":" + sid;
                    connection = DriverManager.getConnection(url, username, password);
                    
                    if(connection.equals(null)){
                    isConnected = false;
                    Status_connection_txt.setBackground(Color.RED);
                    Status_connection_txt.setText("Failed");
                    conn_res_txt.setText("");
                                                }
                    else{
                    Connection_error_txt.setText("");
                    Status_connection_txt.setBackground(Color.green);
                    Status_connection_txt.setText("Succeed");
                    conn_res_txt.setText("Connected to: " + url);
                    isConnected = true;
                        }
                }
                catch (ClassNotFoundException e){
                    Connection_error_txt.setText("ClassNotFoundException: " + e.getMessage().toString());
                    isConnected = false;
                    Status_connection_txt.setBackground(Color.RED);
                    Status_connection_txt.setText("Failed");
                    conn_res_txt.setText("");      
                }
                catch (SQLException e){
                    Connection_error_txt.setText("SQL Error: " + e.getErrorCode()+"; " + e.getMessage());
                    isConnected = false;
                    Status_connection_txt.setBackground(Color.RED);
                    Status_connection_txt.setText("Failed");
                    conn_res_txt.setText("");
                }
               
            }
        
        ConnectionToDBDialog(){
            super(mainForm);//calls mainForm constructor
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            /*
             * Adding atributes on connectionToDB panel
             */
            ConnectionPanel.add(serverLbl, BorderLayout.BEFORE_FIRST_LINE);
            ConnectionPanel.add(usernameLbl, BorderLayout.LINE_END);
            ConnectionPanel.add(portLbl, BorderLayout.LINE_START);
            ConnectionPanel.add(sidLbl, BorderLayout.AFTER_LAST_LINE);
            ConnectionPanel.add(passwordLbl, BorderLayout.WEST);
            ConnectionPanel.add(OkButton);
            ConnectionPanel.add(CancelButton);
           
            /*
             * Event for cancel button (dispose the form)
             */
            CancelButton.addActionListener(new AbstractAction() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    dispose();
                }
            });
            /*
             * Event on Connect button
             */
            ConnectButton.addActionListener(new AbstractAction() {

                @Override
                public void actionPerformed(ActionEvent ae) {
             /*
             * Calling a procedure for establish connection 
             */
            SetConnection();
                }
            });
            /*
            * Editable fields
            */
            Status_connection_txt.setEditable(false);
            conn_res_txt.setEditable(false);
            Connection_error_txt.setEditable(false);
            /*
             * ToolTips for buttons and fields
             */
            OkButton.setToolTipText("OK");
            CancelButton.setToolTipText("Cancel");
            ConnectButton.setToolTipText("Press for set connection to DB");
            usernameTxt.setToolTipText("Enter your username or login");
            passwordTxt.setToolTipText("Enter your password");
            serverTxt.setToolTipText("Address(link) of DB server");
            portTxt.setToolTipText("Port for DB connection");
            sidTxt.setToolTipText("SID of your DB");
            
            setContentPane(ConnectionPanel);
            setLocationRelativeTo(null);
            
            
            
            //pack();
            
        }
        
    }
}
