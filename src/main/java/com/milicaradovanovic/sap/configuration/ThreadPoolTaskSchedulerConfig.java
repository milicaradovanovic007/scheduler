package com.milicaradovanovic.sap.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@ComponentScan(
        basePackages="com.milicaradovanovic.sap")
public class ThreadPoolTaskSchedulerConfig {

        @Bean
        public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
            ThreadPoolTaskScheduler threadPoolTaskScheduler
                    = new ThreadPoolTaskScheduler();
            threadPoolTaskScheduler.setPoolSize(5);
            threadPoolTaskScheduler.setThreadNamePrefix(
                    "ThreadPoolTaskScheduler");
            return threadPoolTaskScheduler;
        }
}
