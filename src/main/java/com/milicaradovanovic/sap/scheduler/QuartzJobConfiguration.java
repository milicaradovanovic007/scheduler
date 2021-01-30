package com.milicaradovanovic.sap.scheduler;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class QuartzJobConfiguration {

    @Value("${sap.cron-scheduler.schedule-task}")
    String scheduleTaskCronSchedule;

    @Bean
    public JobDetail scheduleTaskJobDetails() {
        return JobBuilder.newJob(ScheduleTaskJob.class)
                .withIdentity("scheduleTaskJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger scheduleTaskJobTrigger(JobDetail scheduleTaskJobDetails) {
        return TriggerBuilder.newTrigger().forJob(scheduleTaskJobDetails)
                .withIdentity("scheduleTaskTrigger")
                .withSchedule(CronScheduleBuilder
                        .cronSchedule(this.scheduleTaskCronSchedule)
//                        .cronSchedule("0 0/1 * 1/1 * ? *") // every 1 minute - for testing purposes
                        .inTimeZone(TimeZone.getTimeZone("UTC")))
                .build();
    }
}
