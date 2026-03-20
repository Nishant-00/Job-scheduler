package com.scheduler.scheduler_service.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {

    @Bean("jobExecutorPool")
    public Executor taskExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(5);      // always running workers
        executor.setMaxPoolSize(10);      // burst workers
        executor.setQueueCapacity(50);    // waiting jobs

        executor.setThreadNamePrefix("job-worker-");

        executor.initialize();
        return executor;
    }
}