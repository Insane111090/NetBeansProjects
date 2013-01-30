/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.*;
import javax.swing.JFrame;
import java.lang.*;
import java.util.*;
import java.io.*;
import java.awt.*;
/**
 *
 * @author agavrilov
 */

public class ConnectionToDB extends javax.swing.JFrame {
    
    final private static String driverName = "oracle.jdbc.driver.OracleDriver";
    public static String url;
    private static String server;
    private static String port;
    private static String sid;
    private static String username;
    private static String password;
    public static Connection connection; 
    public static boolean isConnected;
    /**
     * Creates new form ConnectionToDB
     */
    public ConnectionToDB() {
        super("Подключение к Базе Данных");   
        initComponents();
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Server_port_panel = new javax.swing.JPanel();
        Server_name_lbl = new javax.swing.JLabel();
        Port_name_lbl = new javax.swing.JLabel();
        SID_name_lbl = new javax.swing.JLabel();
        Server_input_txt = new javax.swing.JTextField();
        Port_input_txt = new javax.swing.JTextField();
        SID_input_txt = new javax.swing.JTextField();
        User_panel = new javax.swing.JPanel();
        User_name_lbl = new javax.swing.JLabel();
        Password_lbl = new javax.swing.JLabel();
        User_input_txt = new javax.swing.JTextField();
        Password_input_txt = new javax.swing.JPasswordField();
        ConnectButton = new javax.swing.JButton();
        conn_res_txt = new javax.swing.JTextField();
        Connection_error_txt = new javax.swing.JTextField();
        Error_lbl = new javax.swing.JLabel();
        Status_lbl = new javax.swing.JLabel();
        Status_connection_txt = new javax.swing.JTextField();
        OkButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        Server_port_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Настройка параметров сервера"));
        Server_port_panel.setToolTipText("");
        Server_port_panel.setName("Connection"); // NOI18N

        Server_name_lbl.setText("Server:");

        Port_name_lbl.setText("Port:");

        SID_name_lbl.setText("SID:");

        Server_input_txt.setToolTipText("Введите название сервера БД");

        Port_input_txt.setToolTipText("Порт соединения");

        SID_input_txt.setToolTipText("SID БД (ORCL)");

        javax.swing.GroupLayout Server_port_panelLayout = new javax.swing.GroupLayout(Server_port_panel);
        Server_port_panel.setLayout(Server_port_panelLayout);
        Server_port_panelLayout.setHorizontalGroup(
            Server_port_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Server_port_panelLayout.createSequentialGroup()
                .addGroup(Server_port_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Server_name_lbl)
                    .addComponent(Port_name_lbl)
                    .addComponent(SID_name_lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Server_port_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Server_input_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addComponent(Port_input_txt)
                    .addComponent(SID_input_txt))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        Server_port_panelLayout.setVerticalGroup(
            Server_port_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Server_port_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Server_port_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Server_name_lbl)
                    .addComponent(Server_input_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Server_port_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Port_name_lbl)
                    .addComponent(Port_input_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Server_port_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SID_name_lbl)
                    .addComponent(SID_input_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        User_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Настройка пользователя и пароля"));

        User_name_lbl.setText("User (Login):");

        Password_lbl.setText("Password:");

        User_input_txt.setToolTipText("Введите имя пользователя");

        Password_input_txt.setToolTipText("Введите пароль");

        javax.swing.GroupLayout User_panelLayout = new javax.swing.GroupLayout(User_panel);
        User_panel.setLayout(User_panelLayout);
        User_panelLayout.setHorizontalGroup(
            User_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(User_panelLayout.createSequentialGroup()
                .addGroup(User_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(User_name_lbl)
                    .addComponent(Password_lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(User_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(User_input_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(Password_input_txt))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        User_panelLayout.setVerticalGroup(
            User_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(User_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(User_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(User_name_lbl)
                    .addComponent(User_input_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(User_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Password_lbl)
                    .addComponent(Password_input_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        ConnectButton.setText("Connect");
        ConnectButton.setToolTipText("Соедениться с БД");
        ConnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectButtonActionPerformed(evt);
            }
        });

        conn_res_txt.setEditable(false);

        Connection_error_txt.setEditable(false);

        Error_lbl.setText("Error:");

        Status_lbl.setText("Status:");

        Status_connection_txt.setEditable(false);

        OkButton.setText("Ok");
        OkButton.setToolTipText("Ok");
        OkButton.setName("OkButton"); // NOI18N
        OkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkButtonActionPerformed(evt);
            }
        });

        CancelButton.setToolTipText("Выход");
        CancelButton.setText("Cancel");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Server_port_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(User_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ConnectButton)
                            .addComponent(Error_lbl, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Status_lbl, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Connection_error_txt)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Status_connection_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(OkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CancelButton))
                            .addComponent(conn_res_txt))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Server_port_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(User_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ConnectButton)
                    .addComponent(conn_res_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Connection_error_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Error_lbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Status_lbl)
                    .addComponent(Status_connection_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OkButton)
                    .addComponent(CancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void ConnectionSettings()
    {
        try{
            Class.forName(driverName);
            server ="oracle.avalon.ru";// Server_input_txt.getText().toString();
            sid ="ORCL";// SID_input_txt.getText().toUpperCase().toString();
            port ="1521";//Port_input_txt.getText().toString();
            username = "andgavr";//User_input_txt.getText().toString();
            password = "andgavr";//new String(Password_input_txt.getPassword());
            url = "jdbc:oracle:thin:@" + server + ":" + port + ":" + sid;
            connection = DriverManager.getConnection(url, username, password);

            if(connection.equals(null)){
                isConnected = false;
                Status_connection_txt.setBackground(Color.RED);
                Status_connection_txt.setText("Failed");
                conn_res_txt.setText("");
            }
            else
            {
                Connection_error_txt.setText("");
                Status_connection_txt.setBackground(Color.green);
                Status_connection_txt.setText("Succeed");
                conn_res_txt.setText("Connected to: " + url);
                isConnected = true;
            }
        }
        catch (ClassNotFoundException e){
            Connection_error_txt.setText("ClassNotFoundException: "+e.getMessage().toString());
            isConnected = false;
            Status_connection_txt.setBackground(Color.RED);
            Status_connection_txt.setText("Failed");
            conn_res_txt.setText("");

        }
        catch (SQLException e){
            Connection_error_txt.setText("SQL Error: "+e.getErrorCode()+"; " + e.getMessage());
            isConnected = false;
            Status_connection_txt.setBackground(Color.RED);
            Status_connection_txt.setText("Failed");
            conn_res_txt.setText("");
        }
        catch (NullPointerException e){
            Connection_error_txt.setText("NULL Exception: "+ e.getMessage());
            isConnected = false;
            Status_connection_txt.setBackground(Color.RED);
            Status_connection_txt.setText("Failed");
            conn_res_txt.setText("");
        }
    }
    private void ConnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectButtonActionPerformed
        ConnectionSettings();
    }//GEN-LAST:event_ConnectButtonActionPerformed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void OkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkButtonActionPerformed
        System.out.print("\nExiting from connection form\n");
        //setVisible(false);
        dispose();
        
    }//GEN-LAST:event_OkButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConnectionToDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConnectionToDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConnectionToDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConnectionToDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConnectionToDB().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JButton ConnectButton;
    private javax.swing.JTextField Connection_error_txt;
    private javax.swing.JLabel Error_lbl;
    private javax.swing.JButton OkButton;
    private javax.swing.JPasswordField Password_input_txt;
    private javax.swing.JLabel Password_lbl;
    private static javax.swing.JTextField Port_input_txt;
    private javax.swing.JLabel Port_name_lbl;
    private static javax.swing.JTextField SID_input_txt;
    private javax.swing.JLabel SID_name_lbl;
    private static javax.swing.JTextField Server_input_txt;
    private javax.swing.JLabel Server_name_lbl;
    private javax.swing.JPanel Server_port_panel;
    private javax.swing.JTextField Status_connection_txt;
    private javax.swing.JLabel Status_lbl;
    private static javax.swing.JTextField User_input_txt;
    private javax.swing.JLabel User_name_lbl;
    private javax.swing.JPanel User_panel;
    private static javax.swing.JTextField conn_res_txt;
    // End of variables declaration//GEN-END:variables
}