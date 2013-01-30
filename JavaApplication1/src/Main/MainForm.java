package Main;

import Connection.ConnectionToDB;
import javax.swing.JFrame;
import java.awt.*;
/**
 *
 * @author agavrilov
 */
public class MainForm extends javax.swing.JFrame{

    /**
     * Creates new form MainFor
     */
    public MainForm() {
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

        MainPanel_MainForm = new javax.swing.JPanel();
        ConnectPanel_Main_from = new javax.swing.JPanel();
        ConnectButton_mainForm = new javax.swing.JButton();
        StatusTxt_mainForm = new javax.swing.JTextField();
        StatusLbl_mainForm = new javax.swing.JLabel();
        urlLbl_mainForm = new javax.swing.JLabel();
        urlTxt_mainForm = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableList_MainFrom = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MainFrom");
        setName("MainFrame"); // NOI18N
        setResizable(false);

        ConnectPanel_Main_from.setBorder(javax.swing.BorderFactory.createTitledBorder("Настройки подключения к БД"));

        ConnectButton_mainForm.setText("Press for Connect");
        ConnectButton_mainForm.setToolTipText("Перейти к настройкам подключения");
        ConnectButton_mainForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectButton_mainFormActionPerformed(evt);
            }
        });

        StatusTxt_mainForm.setEditable(false);
        StatusTxt_mainForm.setBackground(new java.awt.Color(255, 51, 51));
        StatusTxt_mainForm.setText("Not connected");

        StatusLbl_mainForm.setText("Status:");

        urlLbl_mainForm.setText("URL:");

        urlTxt_mainForm.setEditable(false);

        javax.swing.GroupLayout ConnectPanel_Main_fromLayout = new javax.swing.GroupLayout(ConnectPanel_Main_from);
        ConnectPanel_Main_from.setLayout(ConnectPanel_Main_fromLayout);
        ConnectPanel_Main_fromLayout.setHorizontalGroup(
            ConnectPanel_Main_fromLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConnectPanel_Main_fromLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(urlLbl_mainForm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(urlTxt_mainForm))
            .addGroup(ConnectPanel_Main_fromLayout.createSequentialGroup()
                .addGroup(ConnectPanel_Main_fromLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConnectPanel_Main_fromLayout.createSequentialGroup()
                        .addComponent(StatusLbl_mainForm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StatusTxt_mainForm, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ConnectPanel_Main_fromLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(ConnectButton_mainForm)))
                .addGap(0, 106, Short.MAX_VALUE))
        );
        ConnectPanel_Main_fromLayout.setVerticalGroup(
            ConnectPanel_Main_fromLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ConnectPanel_Main_fromLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(ConnectPanel_Main_fromLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StatusTxt_mainForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StatusLbl_mainForm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ConnectPanel_Main_fromLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(urlLbl_mainForm)
                    .addComponent(urlTxt_mainForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(ConnectButton_mainForm)
                .addContainerGap())
        );

        TableList_MainFrom.setColumns(20);
        TableList_MainFrom.setRows(5);
        jScrollPane1.setViewportView(TableList_MainFrom);

        javax.swing.GroupLayout MainPanel_MainFormLayout = new javax.swing.GroupLayout(MainPanel_MainForm);
        MainPanel_MainForm.setLayout(MainPanel_MainFormLayout);
        MainPanel_MainFormLayout.setHorizontalGroup(
            MainPanel_MainFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanel_MainFormLayout.createSequentialGroup()
                .addGroup(MainPanel_MainFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ConnectPanel_Main_from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(MainPanel_MainFormLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(362, Short.MAX_VALUE))
        );
        MainPanel_MainFormLayout.setVerticalGroup(
            MainPanel_MainFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanel_MainFormLayout.createSequentialGroup()
                .addComponent(ConnectPanel_Main_from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 84, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel_MainForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel_MainForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ConnectButton_mainFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectButton_mainFormActionPerformed
        Connection.ConnectionToDB c = new ConnectionToDB();
        c.setVisible(true);
    }//GEN-LAST:event_ConnectButton_mainFormActionPerformed

    public static void Func()
    {
        String str;
        str = ConnectionToDB.url;
        urlTxt_mainForm.setText(str);
    }
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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
           //Func();
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ConnectButton_mainForm;
    private javax.swing.JPanel ConnectPanel_Main_from;
    private javax.swing.JPanel MainPanel_MainForm;
    private javax.swing.JLabel StatusLbl_mainForm;
    private static javax.swing.JTextField StatusTxt_mainForm;
    public static javax.swing.JTextArea TableList_MainFrom;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel urlLbl_mainForm;
    private static javax.swing.JTextField urlTxt_mainForm;
    // End of variables declaration//GEN-END:variables
}
