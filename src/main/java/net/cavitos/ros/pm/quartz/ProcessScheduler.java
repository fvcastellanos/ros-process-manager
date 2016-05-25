/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cavitos.ros.pm.quartz;

import java.util.List;
import net.cavitos.ros.pm.ProcessService;
import net.cavitos.ros.pm.dto.ProcessInfo;
import net.cavitos.ros.pm.ui.FillTableStrategy;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author fvcg
 */
public class ProcessScheduler {
    
    private Scheduler scheduler;
    
    private JobDataMap buildDataMap(FillTableStrategy fillTableStrategy) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("fillTableStrategy", fillTableStrategy);
        return jobDataMap;
    }
    
    private JobDetail buildGetProcessJob(FillTableStrategy fillTableStrategy) {
        JobDetail job = newJob(GetProcessJob.class)
                .withIdentity("getProcessList")
                .usingJobData(buildDataMap(fillTableStrategy))
                .build();
        return job;
    }
    
    private Trigger buildGetProcessTrigger() {
        Trigger trigger = newTrigger()
                .withIdentity("getProcessListTrigger")
                .startNow()
                .withSchedule(simpleSchedule()
                    .withIntervalInSeconds(5)
                    .repeatForever())
                .build();
        
        return trigger;
    }
    
    public void scheduleGetProcessListJob(FillTableStrategy fillTableStrategy) throws Exception {
        scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(buildGetProcessJob(fillTableStrategy), buildGetProcessTrigger());
        scheduler.start();
    }
    
    public void start() {
        try {
            scheduler.start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void standBy() {
        try {
            scheduler.standby();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void shutdown() {
        try {
            scheduler.shutdown(true);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
