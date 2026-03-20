package com.scheduler.scheduler_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableScheduling
public class SchedulerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerServiceApplication.class, args);
	}
    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
        scheduler.setThreadNamePrefix("job-worker-");
        return scheduler;
    }
}




//What happens here?
//
//Spring Boot starts
//
//Embedded Tomcat server starts
//
//All classes inside this package are scanned
//
//Beans are created (Controller, Service, Repository)
//
//This annotation is a mega annotation combining:
//
//@Configuration
//
//@EnableAutoConfiguration
//
//@ComponentScan
//
//Meaning: “Spring please bootstrap my backend system.”