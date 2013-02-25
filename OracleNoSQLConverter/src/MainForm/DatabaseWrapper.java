package MainForm;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseWrapper {

    static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
    static Connection MyConnection;
    static final ArrayList<String> tables = new ArrayList<String>();
    static boolean isConnected;
    
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
            MainWindow.ConnectionToDBDialog.Connection_error_txt.setText("SQL Error: " + e.getErrorCode() + "; " + e.getMessage());
            isConnected = false;
            MainWindow.ConnectionToDBDialog.Status_connection_txt.setBackground(Color.RED);
            MainWindow.ConnectionToDBDialog.Status_connection_txt.setText("Failed");
            MainWindow.ConnectionToDBDialog.conn_res_txt.setText("");
        }
        if (MyConnection == null) {
            isConnected = false;
            MainWindow.ConnectionToDBDialog.Status_connection_txt.setBackground(Color.RED);
            MainWindow.ConnectionToDBDialog.Status_connection_txt.setText("Failed");
            MainWindow.ConnectionToDBDialog.conn_res_txt.setText("");
        } else {
              isConnected = true;
            MainWindow.ConnectionToDBDialog.Connection_error_txt.setText("");
            MainWindow.ConnectionToDBDialog. Status_connection_txt.setBackground(Color.GREEN);
            MainWindow.ConnectionToDBDialog.Status_connection_txt.setText("Succeed");
            MainWindow.ConnectionToDBDialog.conn_res_txt.setText("Connected to: " + url);
        }
        return MyConnection;
    }
    
    /*
     * Returns 1 or 0, if connected of not
     */
    public static Boolean isConnect(){     
        return isConnected;
    }
    /*
     * Function for getting list of tables of current scheme in Database
     */
    public static ArrayList<String> getTableList() throws SQLException
    {
        try {
            PreparedStatement statementForTables = MyConnection.prepareStatement("Select table_name from all_tables "
                         + "where not regexp_like(tablespace_name,'SYS.+') "
                         + "and owner=upper('andgavr')"); 
                ResultSet DatabaseResultSet = statementForTables.executeQuery();
                while (DatabaseResultSet.next())
                {
                    tables.add(DatabaseResultSet.getString(1));
                    //System.out.println(DatabaseResultSet.getString(1));
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
