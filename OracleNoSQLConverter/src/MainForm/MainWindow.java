package MainForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

/**
 * @author agavrilov
 */
public class MainWindow {

    static final JFrame mainForm = new JFrame();
    static final JPanel mainPanel = new JPanel(new MigLayout());
    static final JPanel ConnectionSettings = new JPanel(new MigLayout());
    static final JPanel resultTables = new JPanel(new MigLayout());
    static public JTextField statusTxt = new JTextField("Not connected");
    static public JTextField connectedUrlTxt = new JTextField();
    static final ConnectionToDBDialog connectionSetupDialog = new ConnectionToDBDialog();
    static final String[] tstr = {""};
    static JList listOfTables = new JList(tstr);//List of result tables from Database
    static JScrollPane scrollPane = new JScrollPane();
    static JLabel countTablesLbl = new JLabel("Count of tables: ");
    static final JTextField countTablesTxt = new JTextField();

    /*
     * Procedure for making the Interface
     */
    public static void createGUI(JLabel connStatus,
            JLabel urlconn,
            JButton openConnectionSetup,
            JButton exitApplic,
            JButton getDdlOfSelectedTable_btn) {
        /*
         * Adding elements on MainForm window
         */
        ConnectionSettings.add(connStatus, "split");
        ConnectionSettings.add(statusTxt, "wrap 10, w :100:300");//wrap to the next row
        ConnectionSettings.add(urlconn, "split");
        ConnectionSettings.add(connectedUrlTxt, "wrap 10, w :500:800, gapleft 20");
        ConnectionSettings.add(openConnectionSetup, "wrap");

        scrollPane.getViewport().setView(listOfTables);
        resultTables.add(scrollPane, "w 100:200:300, h 300,wrap");
        resultTables.add(countTablesLbl, "split");
        resultTables.add(countTablesTxt, "w 20");
        resultTables.add(getDdlOfSelectedTable_btn);

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

        connectionSetupDialog.setModal(true);//Makes window modal
    }

    public static JDialog createWarningDialog(String message) {
        final JDialog warningDialog = new JDialog();
        JPanel warningPanel = new JPanel(new MigLayout());
        JButton ok = new JButton("Ok");

        warningDialog.setTitle("WARNING");

        warningPanel.add(new JLabel(message), "wrap 20");
        warningPanel.add(ok, "align center");
        warningDialog.add(warningPanel);

        warningDialog.setSize(190, 130);
        warningDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        warningDialog.setLocationRelativeTo(null);//Appears on the screen center
        warningDialog.setModal(true);//Makes window modal

        ok.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                warningDialog.dispose();
            }
        });
        return warningDialog;
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

        //GetDdl button
        final JButton getDdlOfSelectedTable_btn = new JButton("Get DDL");
        getDdlOfSelectedTable_btn.setToolTipText("Press for view DDL of the selected table");

        /*
         * Labels on mainForm
         */
        final JLabel connStatus = new JLabel("Status: ");//Connection status Label in MainForm
        final JLabel urlconn = new JLabel("URL:");

        /*
         * Text fields on MainForm
         */
        countTablesTxt.setVisible(false);
        countTablesTxt.setBorder(null);
        countTablesTxt.setEditable(false);

        connectedUrlTxt.setEditable(false);

        statusTxt.setEditable(false);
        statusTxt.setBackground(Color.red);

        createGUI(connStatus, urlconn, openConnectionSetup, exitApplic, getDdlOfSelectedTable_btn);

        /*
         * On buttons click event
         */
        //Connect button event
        openConnectionSetup.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                connectionSetupDialog.setTitle("Connection settings");
                connectionSetupDialog.setSize(550, 350);
                connectionSetupDialog.setVisible(true);
            }
        });
        //Exit button event
        exitApplic.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        //GetDDL button event
        getDdlOfSelectedTable_btn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int selectedTableIndex = listOfTables.getSelectedIndex();
                boolean tableNotSelected = selectedTableIndex < 0;
                if (tableNotSelected) {
                    JDialog warningDialog = createWarningDialog("<html>You did not selected any table.<br>Please, select table first.</html>");
                    warningDialog.setVisible(true);
                } else {
                    String selectedTableName = listOfTables.getSelectedValue().toString();
                    System.out.println(selectedTableName);
                }
            }
        });
    }

    /*
     * Class for connection to Database, where user enters a server Name, port, username and password
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
        public static boolean isConnected;//boolean flag

        /*
         * Procedure for cleaning textFields on connectionto DB form
         */
        public static void clearFields() {
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
         * Function, that creates a connection to DB Form GUI
         */
        public void createFormGUI(JButton ConnectButton,
                JButton OkButton,
                JButton CancelButton) {
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


            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            /*
             * Adding atributes on connectionToDB form
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
             * Editable fields on Connection form
             */
            Status_connection_txt.setEditable(false);
            conn_res_txt.setEditable(false);
            Connection_error_txt.setEditable(false);

            /*
             * ToolTips for buttons and fields on connection form
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

        //main
        ConnectionToDBDialog() {
            super(mainForm);//calls mainForm constructor
            final JButton ConnectButton = new JButton("Connect");//Button for connection
            final JButton OkButton = new JButton("Ok");
            final JButton CancelButton = new JButton("Cancel");//Exit button

            createFormGUI(ConnectButton,
                    OkButton,
                    CancelButton);

            /*
             * Button click events
             */
            //Cancel button
            CancelButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    clearFields();
                    dispose();
                }
            });
            //Connect button(establish connection)
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

                        DatabaseWrapper.createConnection("andgavr",//usernameTxt.getText(),
                                "andgavr",//new String(passwordTxt.getPassword()),
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
            //Ok Button (Confirm)
            OkButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        if (DatabaseWrapper.isConnect()) {
                            MainWindow.statusTxt.setText("Connected");
                            MainWindow.statusTxt.setBackground(Color.green);
                            MainWindow.connectedUrlTxt.setText(conn_res_txt.getText());
                        } else {
                            MainWindow.statusTxt.setText("Not connected");
                            MainWindow.statusTxt.setBackground(Color.red);
                            MainWindow.connectedUrlTxt.setText(conn_res_txt.getText());
                        }
                        listOfTables.setListData(DatabaseWrapper.getTableList().toArray());
                        countTablesTxt.setVisible(true);
                        countTablesTxt.setText(String.valueOf(DatabaseWrapper.tables.size()));

                        DatabaseWrapper.clearArrayList();
                        dispose();
                    } catch (SQLException ex) {
                    }
                }
            });

        }
    }
}
