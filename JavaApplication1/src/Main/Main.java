package Main;

import oracle.jdbc.driver.*;
import java.lang.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextField;
import Connection.ConnectionToDB;
/**
 * @author agavrilov
 */
public class Main extends MainForm{
    
    public static void test()
{
    if (Connection.ConnectionToDB.isConnected)
        {
            System.out.print("SUPER");
        }
    
}
    
    public static void main(String[] args) 
    {
        //Connection.ConnectionToDB.main(args);
        MainForm.main(args);
        
        //test();
        
        try{
        Statement stm = Connection.ConnectionToDB.connection.createStatement();
        ResultSet set = stm.executeQuery("Select * from employees");
                    int x = set.getMetaData().getColumnCount();
                    while (set.next()){
                        for (int i = 1; i<=x;i++){
                        TableList_MainFrom.setText(set.getMetaData().getColumnName(i) +": "+ set.getString(i));
                        }
                        //System.out.println();
                    }
                    stm.close();  
        }
        catch (SQLException e){
            
        }
            
        
       
        
        }
    }

    

