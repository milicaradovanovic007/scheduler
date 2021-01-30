package com.milicaradovanovic.sap.configuration;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorServiceConfig {

    @Bean
    @Qualifier("executorService")
    public ExecutorService threadExecutorService() {
        return Executors.newFixedThreadPool(10);
    }

}