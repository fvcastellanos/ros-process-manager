/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cavitos.ros.pm.ui;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import net.cavitos.ros.pm.ProcessService;
import net.cavitos.ros.pm.dto.ProcessInfo;
import net.cavitos.ros.pm.quartz.ProcessScheduler;

/**
 *
 * @author fvcg
 */
public class ProcessManagerUI extends javax.swing.JFrame {
    private ProcessService processService;
    private ProcessScheduler processScheduler;
    private FillTableStrategy fillTableStrategy;

    public List<ProcessInfo> processList;
    
    private TableModel tableModel;
    
    /**
     * Creates new form ProcessManagerUI
     */
    public ProcessManagerUI() {
        processService = new ProcessService();
        processList = processService.getProcessList();
        initComponents();
        configureTableModel();
        initScheduler();
    }

    private void configureTableModel() {
        fillTableStrategy = new FillTableStrategy(processService, tbProcess);
        fillTableStrategy.buildTableModel();
    }
    
    private void initScheduler() {
        try {
            processScheduler = new ProcessScheduler();
            processScheduler.scheduleGetProcessListJob(fillTableStrategy);
            processScheduler.standBy();
        } catch(Exception ex) {
            ex.printStackTrace();
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

        spProcess = new javax.swing.JScrollPane();
        tbProcess = new javax.swing.JTable();
        pnOptions = new javax.swing.JPanel();
        btnKillProcess = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        chbRefresh = new javax.swing.JCheckBox();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ReactOS - Process Manager");

        tbProcess.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PId", "Process", "Memory (MB)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProcess.setColumnSelectionAllowed(true);
        tbProcess.getTableHeader().setReorderingAllowed(false);
        spProcess.setViewportView(tbProcess);
        tbProcess.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnKillProcess.setText("Kill process");
        btnKillProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKillProcessActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        chbRefresh.setText("Use automatic refresh");
        chbRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbRefreshActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnOptionsLayout = new javax.swing.GroupLayout(pnOptions);
        pnOptions.setLayout(pnOptionsLayout);
        pnOptionsLayout.setHorizontalGroup(
            pnOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKillProcess, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnOptionsLayout.createSequentialGroup()
                        .addComponent(chbRefresh)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnRefresh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnOptionsLayout.setVerticalGroup(
            pnOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnKillProcess)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRefresh)
                .addGap(9, 9, 9)
                .addComponent(chbRefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(spProcess, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spProcess, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
            .addComponent(pnOptions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private Long getSelectedProcessId() {
        int selected = tbProcess.getSelectedRow();
        Long id = (Long) tbProcess.getValueAt(selected, 0);
        return id;
    }
    
    private void btnKillProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKillProcessActionPerformed
        // TODO add your handling code here:
        try {
            processService.killProcess(getSelectedProcessId());
            
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Exception - " + ex.getMessage());
        }
    }//GEN-LAST:event_btnKillProcessActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        processScheduler.shutdown();
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void chbRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbRefreshActionPerformed
        // TODO add your handling code here:
        if(chbRefresh.isSelected()) {
            btnRefresh.setEnabled(false);
            processScheduler.start();
        } else {
            btnRefresh.setEnabled(true);
            processScheduler.standBy();
        }
    }//GEN-LAST:event_chbRefreshActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        fillTableStrategy.buildTableModel();
    }//GEN-LAST:event_btnRefreshActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//        System.load("C:\\Users\\fvcg2\\Desktop\\dll\\sigar-amd64-winnt.dll");
        System.load("C:\\sigar-dll\\sigar-winnt.dll");

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
            java.util.logging.Logger.getLogger(ProcessManagerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProcessManagerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProcessManagerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProcessManagerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProcessManagerUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnKillProcess;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JCheckBox chbRefresh;
    private javax.swing.JPanel pnOptions;
    private javax.swing.JScrollPane spProcess;
    private javax.swing.JTable tbProcess;
    // End of variables declaration//GEN-END:variables


    public List<ProcessInfo> getProcessList() {
        return processList;
    }
}
