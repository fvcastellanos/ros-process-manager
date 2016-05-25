/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cavitos.ros.pm.ui;

import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.cavitos.ros.pm.ProcessService;
import net.cavitos.ros.pm.dto.ProcessInfo;

/**
 *
 * @author fvcg
 */
public class FillTableStrategy {
    
    private static String [] COLUMNS_NAME = {"PId", "Process", "Memory (MB)"};
    private ProcessService processService;
    private JTable table;
    
    public FillTableStrategy(ProcessService processService, JTable table) {
        this.processService = processService;
        this.table = table;
    }
    
    private void configureTable(TableModel tableModel) {
        table.setModel(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(10);
        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
    }
    
    public TableModel buildTableModel() {
        List<ProcessInfo> processList = processService.getProcessList();
        TableModel tableModel = new DefaultTableModel(COLUMNS_NAME, processList.size());
       
        int i = 0;
        for(ProcessInfo info : processList) {
            tableModel.setValueAt(info.getpId(), i, 0);
            tableModel.setValueAt(info.getName(), i, 1);
            tableModel.setValueAt(info.getMemoryUssage(), i, 2);
            i++;
        }
        
        configureTable(tableModel);
        
        return tableModel;
    }
        
    
}
