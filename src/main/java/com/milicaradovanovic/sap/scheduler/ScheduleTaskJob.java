package com.milicaradovanovic.sap.scheduler;

import com.milicaradovanovic.sap.service.ScheduleTaskService;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.quartz.Job;


@EnableAsync
@Component
@Qualifier("scheduleTaskJob")
public class ScheduleTaskJob implements Job {

    private ScheduleTaskService scheduleTaskService;

    @Autowired
    public ScheduleTaskJob(ScheduleTaskService scheduleTaskService) {
        this.scheduleTaskService = scheduleTaskService;
    }

    @Override
    public void execute(JobExecutionContext context) {
        this.scheduleTaskService.executeTask();
    }

}
