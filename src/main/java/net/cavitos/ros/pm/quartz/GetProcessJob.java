/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cavitos.ros.pm.quartz;

import javafx.collections.ObservableList;
import net.cavitos.ros.pm.ProcessService;
import net.cavitos.ros.pm.dto.ProcessInfo;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author fvcg
 */
public class GetProcessJob implements Job {

    public void execute(JobExecutionContext jec) throws JobExecutionException {
        JobDataMap jobDataMap = jec.getMergedJobDataMap();
        ObservableList<ProcessInfo> processList = (ObservableList<ProcessInfo>) jobDataMap.get("processList");
        ProcessService processService = (ProcessService) jobDataMap.get("processService");
        // processList.clear();
        processList.clear();
        for(ProcessInfo info : processService.getProcessList()) {
            processList.add(info);
        }
        System.out.println("Process list acquired");
    }
    
}
