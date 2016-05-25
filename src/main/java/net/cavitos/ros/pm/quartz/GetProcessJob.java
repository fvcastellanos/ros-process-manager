/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cavitos.ros.pm.quartz;

import net.cavitos.ros.pm.ui.FillTableStrategy;
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
        FillTableStrategy fillTableStrategy = (FillTableStrategy) jobDataMap.get("fillTableStrategy");
        fillTableStrategy.buildTableModel();
        System.out.println("Process list acquired");
    }
    
}
