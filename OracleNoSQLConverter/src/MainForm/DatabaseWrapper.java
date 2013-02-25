package MainForm;

import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseWrapper {

    static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
    static Connection MyConnection;
    static final List<String> tables = new ArrayList<String>();
    static boolean _isConnected;
    
    /*
     * Function that provides a connection to DB
     */
    public static Connection createConnection(String username,
            String password,
            String url) throws ClassNotFoundException {
        Class.forName(DRIVER_NAME);
        try {
            MyConnection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            MainWindow.ConnectionConfigDialog.Connection_error_txt.setText("SQL Error: " + e.getErrorCode() + "; " + e.getMessage());
            _isConnected = false;
            MainWindow.ConnectionConfigDialog.connectionStatusLabel.setBackground(Color.RED);
            MainWindow.ConnectionConfigDialog.connectionStatusLabel.setText("Failed");
            MainWindow.ConnectionConfigDialog.connectionUrlLabel.setText("");
        }
        if (MyConnection == null) {
            _isConnected = false;
            MainWindow.ConnectionConfigDialog.connectionStatusLabel.setBackground(Color.RED);
            MainWindow.ConnectionConfigDialog.connectionStatusLabel.setText("Failed");
            MainWindow.ConnectionConfigDialog.connectionUrlLabel.setText("");
        } else {
              _isConnected = true;
            MainWindow.ConnectionConfigDialog.Connection_error_txt.setText("");
            MainWindow.ConnectionConfigDialog.connectionStatusLabel.setBackground(Color.GREEN);
            MainWindow.ConnectionConfigDialog.connectionStatusLabel.setText("Succeed");
            MainWindow.ConnectionConfigDialog.connectionUrlLabel.setText("Connected to: " + url);
        }
        return MyConnection;
    }
    
    /*
     * Returns 1 or 0, if connected of not
     */
    public static Boolean isConnected() {
        return _isConnected;
    }
    /*
     * Function for getting list of tables of current scheme in Database
     */
    public static List<String> getTableList() throws SQLException {
        try {
            PreparedStatement statementForTables =
                    MyConnection.prepareStatement("Select table_name from all_tables "
                            + "where not regexp_like(tablespace_name,'SYS.+') "
                            + "and owner=upper('andgavr')");
            ResultSet DatabaseResultSet = statementForTables.executeQuery();

            while (DatabaseResultSet.next()) {
                tables.add(DatabaseResultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
        }
        return tables;
    }
   
    /*
     * Delete values from ArrayList of tables
     */
    public static void clearArrayList(){
        tables.clear();
    }
}
