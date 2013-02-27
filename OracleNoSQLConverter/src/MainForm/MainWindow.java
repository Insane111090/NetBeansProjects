package MainForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import static MainForm.Util.MigPanel;

public class MainWindow {

    static final JFrame mainForm = new JFrame();
    static final JPanel mainPanel = new MigPanel();
    static final JPanel connectionSettings = new MigPanel();
    static final JPanel resultTables = new MigPanel();
    static public JTextField statusTxt = new JTextField("Not connected");
    static public JTextField connectedUrlTxt = new JTextField();
    static final ConnectionConfigDialog connectionSetupDialog = new ConnectionConfigDialog();
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
        connectionSettings.add(connStatus, "split");
        connectionSettings.add(statusTxt, "wrap 10, w :100:300");//wrap to the next row
        connectionSettings.add(urlconn, "split");
        connectionSettings.add(connectedUrlTxt, "wrap 10, w :500:800, gapleft 20");
        connectionSettings.add(openConnectionSetup, "wrap");

        scrollPane.getViewport().setView(listOfTables);
        resultTables.add(scrollPane, "w 100:200:300, h 300,wrap");
        resultTables.add(countTablesLbl, "split");
        resultTables.add(countTablesTxt, "w 20");
        resultTables.add(getDdlOfSelectedTable_btn);

        mainPanel.add(connectionSettings, "wrap, dock north");
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

    public static void main(String[] args) {
        connectionSettings.setBorder(new TitledBorder("Настройка подключения к БД"));
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
                    JOptionPane.showMessageDialog(
                            mainForm,
                            "You haven't selected any table.",
                            "Please, select table",
                            JOptionPane.ERROR_MESSAGE
                    );
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
    public static final class ConnectionConfigDialog extends JDialog {

        final static JTextField serverTxt = new JTextField();//Field for server input
        final static JTextField portTxt = new JTextField();//Field for port input
        final static JTextField sidTxt = new JTextField();//Field for sid input
        final static JTextField usernameTxt = new JTextField();//Field for username input
        final static JPasswordField passwordTxt = new JPasswordField();//Field for password input
        final static JTextField connectionStatusLabel = new JTextField();
        final static JTextField connectionUrlLabel = new JTextField();//Field for connection url
        final static JTextArea connectionErrorLabel = new JTextArea();//Connection error

        /*
         * Procedure for cleaning textFields on connection to DB form
         */
        public static void clearFields() {
            serverTxt.setText("");
            portTxt.setText("");
            sidTxt.setText("");
            usernameTxt.setText("");
            passwordTxt.setText("");
            connectionStatusLabel.setText("");
            connectionUrlLabel.setText("");
            connectionErrorLabel.setText("");
            connectionStatusLabel.setBackground(Color.WHITE);
        }

        /*
         * Function, that creates a connection to DB Form GUI
         */
        public void createFormGUI(JButton ConnectButton,
                JButton OkButton,
                JButton CancelButton) {
            final JPanel ConnectionPanel = new MigPanel();
            final JPanel InputServerPanel = new MigPanel();
            final JPanel InputUserPanel = new MigPanel();
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
            ConnectionPanel.add(connectionUrlLabel, "wrap 10,grow");
            ConnectionPanel.add(stat, "split");
            ConnectionPanel.add(connectionStatusLabel, "wrap,w 50:70:");
            ConnectionPanel.add(Error, "split");
            ConnectionPanel.add(connectionErrorLabel, "wrap 24, w :1200:,gapleft 12");
            ConnectionPanel.add(OkButton, "split,align right");
            ConnectionPanel.add(CancelButton);

            /*
             * Editable fields on Connection form
             */
            connectionStatusLabel.setEditable(false);
            connectionUrlLabel.setEditable(false);
            connectionErrorLabel.setEditable(false);

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
        ConnectionConfigDialog() {
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
                        connectionErrorLabel.setText("ClassNotFoundException: " + e.getMessage());
                        connectionStatusLabel.setBackground(Color.RED);
                        connectionStatusLabel.setText("Failed");
                        connectionUrlLabel.setText("");
                    }
                }
            });
            //Ok Button (Confirm)
            OkButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        if (DatabaseWrapper.isConnected()) {
                            MainWindow.statusTxt.setText("Connected");
                            MainWindow.statusTxt.setBackground(Color.green);
                            MainWindow.connectedUrlTxt.setText(connectionUrlLabel.getText());
                        } else {
                            MainWindow.statusTxt.setText("Not connected");
                            MainWindow.statusTxt.setBackground(Color.red);
                            MainWindow.connectedUrlTxt.setText(connectionUrlLabel.getText());
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
