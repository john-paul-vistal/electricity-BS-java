/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.*;
import Interface.*;
import Models.Admin;
import Models.Bill;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 1styrGroupC
 */
public class Transactions extends javax.swing.JFrame {

    BillInterface billControllers = new BillController();
    AdminInterface adminControllers = new AdminController();

    int billNumber;
  
    public int userID;
    public String userName;
    public int userLevel;
    public String fName;
    public String lName;
    public String mName;
    public boolean logged = false;

    /**
     * Creates new form Transaction_Form
     */
    public Transactions(int id) {
        initComponents();
        userID = id;
        loadBills();
        initialDisplay();
        
    }

    public void initialDisplay() {
         try {
            Admin admin = adminControllers.getAdmin(userID);
            fName = admin.getfName();
            lName = admin.getlName();
            mName = admin.getmName();
            userLevel = admin.getLevel();

            userNamelbl.setText(fName+" "+lName);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
         
        this.setExtendedState(Main.MAXIMIZED_BOTH);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
    }

    public void loadBills() {
        DefaultTableModel model = (DefaultTableModel) billsTable.getModel();
        model.setRowCount(0);
        DecimalFormat formatter = new DecimalFormat("#0.00");
        try {
            for (Bill bill : billControllers.getAllUnpaidBill()) {
                String status;
                if (bill.getStatus() == 2) {
                    status = "UNPAID";
                } else {
                    status = "OVERDUE";
                }
                model.addRow(new Object[]{bill.getId(), bill.getElectricityLineNo(), bill.getReading(), formatter.format(bill.getAmountDue()), status, bill.getRecordedDate(), bill.getDueDate(), adminControllers.getAdmin(bill.getRecordedBy()).getlName() + ", " + adminControllers.getAdmin(bill.getRecordedBy()).getfName()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();
        createTransactionBtn = new javax.swing.JButton();
        tfElectricityLineNo = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        refreshBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        billsTableContainer = new javax.swing.JScrollPane();
        billsTable = new javax.swing.JTable();
        userNamelbl = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        logOut = new javax.swing.JButton();
        close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The Flash Electricity Corp.(Transaction Page)");

        container.setBackground(new java.awt.Color(255, 255, 255));

        createTransactionBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        createTransactionBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pay.png"))); // NOI18N
        createTransactionBtn.setText("Create Transaction");
        createTransactionBtn.setEnabled(false);
        createTransactionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createTransactionBtnActionPerformed(evt);
            }
        });

        tfElectricityLineNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfElectricityLineNoActionPerformed(evt);
            }
        });

        Search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setText("The Flash Electricity Corporation");

        refreshBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh.png"))); // NOI18N
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N

        billsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ELECTRICITY LINE NO.", "METER READING(kwH)", "AMOUNT DUE", "STATUS", "RECORDED DATE", "DUE DATE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        billsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                billsTableMouseClicked(evt);
            }
        });
        billsTableContainer.setViewportView(billsTable);
        if (billsTable.getColumnModel().getColumnCount() > 0) {
            billsTable.getColumnModel().getColumn(0).setMinWidth(0);
            billsTable.getColumnModel().getColumn(0).setPreferredWidth(0);
            billsTable.getColumnModel().getColumn(0).setMaxWidth(0);
            billsTable.getColumnModel().getColumn(1).setResizable(false);
            billsTable.getColumnModel().getColumn(2).setResizable(false);
            billsTable.getColumnModel().getColumn(3).setResizable(false);
            billsTable.getColumnModel().getColumn(4).setResizable(false);
            billsTable.getColumnModel().getColumn(5).setResizable(false);
            billsTable.getColumnModel().getColumn(6).setResizable(false);
        }

        userNamelbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        userNamelbl.setText("..............................");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/profile2.png"))); // NOI18N

        logOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout.png"))); // NOI18N
        logOut.setToolTipText("Log Out");
        logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutActionPerformed(evt);
            }
        });

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/poweroff (1).png"))); // NOI18N
        close.setToolTipText("Close");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(billsTableContainer)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerLayout.createSequentialGroup()
                                .addComponent(tfElectricityLineNo, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Search)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 280, Short.MAX_VALUE)
                                .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(containerLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(createTransactionBtn))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(logOut)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(close)))
                        .addContainerGap())
                    .addGroup(containerLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(userNamelbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(userNamelbl)
                            .addComponent(jLabel4))
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerLayout.createSequentialGroup()
                        .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfElectricityLineNo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(billsTableContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                    .addGroup(containerLayout.createSequentialGroup()
                        .addComponent(createTransactionBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(close, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(logOut, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void billsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billsTableMouseClicked
        createTransactionBtn.setEnabled(true);
        DefaultTableModel model = (DefaultTableModel) billsTable.getModel();
        billNumber = Integer.parseInt(model.getValueAt(billsTable.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_billsTableMouseClicked

    private void createTransactionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createTransactionBtnActionPerformed
        Transaction_Form show = new Transaction_Form(this, true, billNumber,userID);
        show.setVisible(true);
        loadBills();
        createTransactionBtn.setEnabled(false);
    }//GEN-LAST:event_createTransactionBtnActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        tfElectricityLineNo.setText("");
        loadBills();
        createTransactionBtn.setEnabled(false);
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void tfElectricityLineNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfElectricityLineNoActionPerformed
        DefaultTableModel model = (DefaultTableModel) billsTable.getModel();
        model.setRowCount(0);
        DecimalFormat formatter = new DecimalFormat("#0.00");
        try {
            for (Bill bill : billControllers.searchUnpaidBill(tfElectricityLineNo.getText())) {
                String status;
                if (bill.getStatus() == 2) {
                    status = "UNPAID";
                } else {
                    status = "OVERDUE";
                }
                model.addRow(new Object[]{bill.getId(), bill.getElectricityLineNo(), bill.getReading(), formatter.format(bill.getAmountDue()), status, bill.getRecordedDate(), bill.getDueDate(), adminControllers.getAdmin(bill.getRecordedBy()).getlName() + ", " + adminControllers.getAdmin(bill.getRecordedBy()).getfName()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_tfElectricityLineNoActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        DefaultTableModel model = (DefaultTableModel) billsTable.getModel();
        model.setRowCount(0);
        DecimalFormat formatter = new DecimalFormat("#0.00");
        try {
            for (Bill bill : billControllers.searchUnpaidBill(tfElectricityLineNo.getText())) {
                String status;
                if (bill.getStatus() == 2) {
                    status = "UNPAID";
                } else {
                    status = "OVERDUE";
                }
                model.addRow(new Object[]{bill.getId(), bill.getElectricityLineNo(), bill.getReading(), formatter.format(bill.getAmountDue()), status, bill.getRecordedDate(), bill.getDueDate(), adminControllers.getAdmin(bill.getRecordedBy()).getlName() + ", " + adminControllers.getAdmin(bill.getRecordedBy()).getfName()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_SearchActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        int close = JOptionPane.showConfirmDialog(null, "Are you sure you want to continue?", "Close Application", JOptionPane.OK_CANCEL_OPTION);
        if (close == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_closeActionPerformed

    private void logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutActionPerformed
        int close = JOptionPane.showConfirmDialog(null, "Are you sure you want to continue?", "Sign Out", JOptionPane.OK_CANCEL_OPTION);
        if (close == 0) {
            this.dispose();
            Main_Run run = new Main_Run();
            run.main(null);
        }
    }//GEN-LAST:event_logOutActionPerformed

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
            java.util.logging.Logger.getLogger(Transactions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transactions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transactions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transactions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transactions(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Search;
    private javax.swing.JTable billsTable;
    private javax.swing.JScrollPane billsTableContainer;
    private javax.swing.JButton close;
    private javax.swing.JPanel container;
    private javax.swing.JButton createTransactionBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton logOut;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JTextField tfElectricityLineNo;
    private javax.swing.JLabel userNamelbl;
    // End of variables declaration//GEN-END:variables
}
