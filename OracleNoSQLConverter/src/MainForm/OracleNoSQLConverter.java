package MainForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

/**
 * @author agavrilov
 */
public class OracleNoSQLConverter {

    static final JFrame mainForm = new JFrame();
    static final JPanel mainPanel = new JPanel(new MigLayout());
    static final JPanel ConnectionSettings = new JPanel(new MigLayout());
    static final JPanel resultTables = new JPanel(new MigLayout());
    static public JTextField statusTxt = new JTextField("Not connected");
    static public JTextField connectedUrltxt = new JTextField();
    static final ConnectionToDBDialog connectionSetupDialoge = new ConnectionToDBDialog();
    static String driverName = "oracle.jdbc.driver.OracleDriver";
    static Connection connection;
    static final ArrayList<String> tablesArray = new ArrayList<>();
    static final String[] tstr = {""};
    static JList listOfTables = new JList(tstr);//List of result tables from Database
    static JScrollPane scrollPane = new JScrollPane();
    static final JTextField countTablesTxt = new JTextField();

    /*
     * Procedure for making the Interface
     */
    public static void createGUI(JLabel connStatus, JLabel urlconn, 
                                 JButton openConnectionSetup, JButton exitApplic) {
         /*
         * Adding elements on MainForm window
         */
        ConnectionSettings.add(connStatus, "split");
        ConnectionSettings.add(statusTxt, "wrap 10, w :100:300");//wrap to the next row
        ConnectionSettings.add(urlconn, "split");
        ConnectionSettings.add(connectedUrltxt, "wrap 10, w :500:800, gapleft 20");
        ConnectionSettings.add(openConnectionSetup, "wrap");

        scrollPane.getViewport().setView(listOfTables);
        resultTables.add(scrollPane, "w 100:200:300, h 300,wrap");
        resultTables.add(countTablesTxt, "w 100:200");

        mainPanel.add(ConnectionSettings, "wrap, dock north");
        mainPanel.add(resultTables, "wrap");
        mainPanel.add(exitApplic, "align right, gapright 20");

        mainForm.setTitle("MainForm");
        mainForm.setVisible(true);
        mainForm.setResizable(false);
        mainForm.setSize(500, 500);
        mainForm.setContentPane(mainPanel);
        mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainForm.setLocationRelativeTo(null);//Appears on the screen center

        connectionSetupDialoge.setModal(true);//Makes window modal  
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConnectionSettings.setBorder(new TitledBorder("Настройка подключения к БД"));
        resultTables.setBorder(new TitledBorder("Список таблиц"));

        /*
         * Creation of Buttons
         */
        //Connection button
        final JButton openConnectionSetup = new JButton("Configure connection");//Button pressed for configure connection
        openConnectionSetup.setToolTipText("Press for enter connection setup");//ToolTip for button

        //Exit button
        final JButton exitApplic = new JButton("Exit");
        exitApplic.setToolTipText("Exit application");

        /*
         * Labels on mainForm
         */
        final JLabel connStatus = new JLabel("Status: ");//Connection status Label in MainForm
        final JLabel urlconn = new JLabel("URL:");

        /*
         * Text fields on MainForm
         */
        countTablesTxt.setVisible(false);
        countTablesTxt.setEditable(false);

        connectedUrltxt.setEditable(false);

        statusTxt.setEditable(false);
        statusTxt.setBackground(Color.red);

        /*
         * On buttons click event
         */
        //Connect button event
        openConnectionSetup.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                connectionSetupDialoge.setTitle("Connection settings");
                connectionSetupDialoge.setSize(550, 350);
                connectionSetupDialoge.setVisible(true);
            }
        });
        //Exit button event
        exitApplic.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
            
        createGUI(connStatus, urlconn, openConnectionSetup, exitApplic);
    }

    /*
     * Class for connection to Database, where user enter server, port, username and password
     */
    public static final class ConnectionToDBDialog extends JDialog {

        final static JTextField serverTxt = new JTextField();//Field for server input
        final static JTextField portTxt = new JTextField();//Field for port input
        final static JTextField sidTxt = new JTextField();//Field for sid input
        final static JTextField usernameTxt = new JTextField();//Field for username input
        final static JPasswordField passwordTxt = new JPasswordField();//Field for password input
        final static JTextField Status_connection_txt = new JTextField();//Field for connection status(Failed or not)
        final static JTextField conn_res_txt = new JTextField();//Field for connection url
        final static JTextArea Connection_error_txt = new JTextArea();//Connection error
        public static boolean isConnected;

        /*
         * Procedure for cleaning textFields on JDialoge
         */
        public static void ClearFields() {
            serverTxt.setText("");
            portTxt.setText("");
            sidTxt.setText("");
            usernameTxt.setText("");
            passwordTxt.setText("");
            Status_connection_txt.setText("");
            conn_res_txt.setText("");
            Connection_error_txt.setText("");
            Status_connection_txt.setBackground(Color.WHITE);
        }

        /*
         * Procedure wich creates a connection to DB
         */
        public static Connection CreateConnection(String username,
                String password,
                String url) throws ClassNotFoundException {
            Class.forName(driverName);
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                Connection_error_txt.setText("SQL Error: " + e.getErrorCode() + "; " + e.getMessage());
                isConnected = false;
                Status_connection_txt.setBackground(Color.RED);
                Status_connection_txt.setText("Failed");
                conn_res_txt.setText("");
            }
            if (connection == null) {
                isConnected = false;
                Status_connection_txt.setBackground(Color.RED);
                Status_connection_txt.setText("Failed");
                conn_res_txt.setText("");
            } else {
                isConnected = true;
                Connection_error_txt.setText("");
                Status_connection_txt.setBackground(Color.green);
                Status_connection_txt.setText("Succeed");
                conn_res_txt.setText("Connected to: " + url);
            }
            return connection;
        }

        ConnectionToDBDialog() {
            super(mainForm);//calls mainForm constructor
            final JPanel ConnectionPanel = new JPanel(new MigLayout());
            final JPanel InputServerPanel = new JPanel(new MigLayout());
            final JPanel InputUserPanel = new JPanel(new MigLayout());
            final JLabel serverLbl = new JLabel("Server: ");
            final JLabel portLbl = new JLabel("Port: ");
            final JLabel sidLbl = new JLabel("SID: ");
            final JLabel usernameLbl = new JLabel("Username: ");
            final JLabel passwordLbl = new JLabel("Password: ");
            final JLabel Error = new JLabel("Error: ");
            final JLabel stat = new JLabel("Status: ");
            final JButton ConnectButton = new JButton("Connect");//Button for connection
            final JButton OkButton = new JButton("Ok");
            final JButton CancelButton = new JButton("Cancel");//Exit button

            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            /*
             * Adding atributes on connectionToDB panel
             */
            InputUserPanel.setBorder(new TitledBorder("Username and password settings"));
            InputUserPanel.add(usernameLbl, "split");
            InputUserPanel.add(usernameTxt, "wrap 10, w 100:150:200");
            InputUserPanel.add(passwordLbl, "split");
            InputUserPanel.add(passwordTxt, "wrap 28, w 100:150:200");

            InputServerPanel.setBorder(new TitledBorder("Server connection settings"));
            InputServerPanel.add(serverLbl, "split");
            InputServerPanel.add(serverTxt, "w 150:150:200, wrap");
            InputServerPanel.add(portLbl, "split");
            InputServerPanel.add(portTxt, "w 150:150:200, gapleft 18, wrap");
            InputServerPanel.add(sidLbl, "split");
            InputServerPanel.add(sidTxt, "w 150:150:200, gapleft 22, wrap 10");

            ConnectionPanel.add(InputServerPanel, "split");
            ConnectionPanel.add(InputUserPanel, "wrap 10, gapleft 20");
            ConnectionPanel.add(ConnectButton, "split");
            ConnectionPanel.add(conn_res_txt, "wrap 10,grow");
            ConnectionPanel.add(stat, "split");
            ConnectionPanel.add(Status_connection_txt, "wrap,w 50:70:");
            ConnectionPanel.add(Error, "split");
            ConnectionPanel.add(Connection_error_txt, "wrap 24, w :1200:,gapleft 12");
            ConnectionPanel.add(OkButton, "split,align right");
            ConnectionPanel.add(CancelButton);

            /*
             * Event for cancel button (dispose the form)
             */
            CancelButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    ClearFields();
                    dispose();
                }
            });

            /*
             * Event on Connect button
             */
            ConnectButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent ae) {

                    try {
                        String server = "oracle11.avalon.ru"; //serverTxt.getText().toString();//"oracle11.avalon.ru"
                        String sid = "ORCL";//sidTxt.getText().toUpperCase().toString();//"ORCL";
                        int port = Integer.decode("1521");//portTxt.getText().toString();//"1521";
                        String url = "jdbc:oracle:thin:@" + server + ":" + port + ":" + sid;

                        //username = "andgavr";//usernameTxt.getText().toString();//"andgavr";
                        //password = "andgavr";//new String(passwordTxt.getPassword());//"andgavr";

                        connection = CreateConnection(usernameTxt.getText(),
                                new String(passwordTxt.getPassword()),
                                url);
                    } catch (ClassNotFoundException e) {
                        Connection_error_txt.setText("ClassNotFoundException: " + e.getMessage());
                        isConnected = false;
                        Status_connection_txt.setBackground(Color.RED);
                        Status_connection_txt.setText("Failed");
                        conn_res_txt.setText("");
                    }
                }
            });

            OkButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (isConnected) {
                        OracleNoSQLConverter.statusTxt.setText("Connected");
                        OracleNoSQLConverter.statusTxt.setBackground(Color.green);
                        OracleNoSQLConverter.connectedUrltxt.setText(conn_res_txt.getText());
                    } else {
                        OracleNoSQLConverter.statusTxt.setText("Not connected");
                        OracleNoSQLConverter.statusTxt.setBackground(Color.red);
                        OracleNoSQLConverter.connectedUrltxt.setText(conn_res_txt.getText());
                    }
                    try {
                        PreparedStatement statementForTables = connection.prepareStatement("Select table_name from all_tables "
                                + "where not regexp_like(tablespace_name,'SYS.+') "
                                + "and owner=upper('andgavr')");

                        ResultSet DatabaseResultSet = statementForTables.executeQuery();

                        int colsCount = DatabaseResultSet.getMetaData().getColumnCount();

                        while (DatabaseResultSet.next()) {
                            for (int i = 1; i <= colsCount; i++) {
                                tablesArray.add(DatabaseResultSet.getString(i));
                                listOfTables.setListData(tablesArray.toArray());
                                System.out.println(DatabaseResultSet.getString(i));
                            }
                        }
                        statementForTables.close();

                        int countOfSelectedTables = tablesArray.size();

                        countTablesTxt.setVisible(true);
                        countTablesTxt.setText("Count of tables: " + countOfSelectedTables);

                    } catch (SQLException e) {
                        System.out.println(e.getErrorCode() + " " + e.getMessage());
                    }
                    dispose();
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
        }
    }
}
