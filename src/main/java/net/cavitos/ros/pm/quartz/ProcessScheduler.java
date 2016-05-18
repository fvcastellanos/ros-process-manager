/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cavitos.ros.pm.quartz;

import static org.quartz.JobBuilder.newJob;
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
    
    private JobDetail buildGetProcessJob() {
        JobDetail job = newJob(GetProcessJob.class)
                .withIdentity("getProcessList")
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
    
    public void scheduleGetProcessListJob() throws Exception {
        scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(buildGetProcessJob(), buildGetProcessTrigger());
        scheduler.start();
    }
}
