package com.scheduler.scheduler_service.Config;

import com.scheduler.scheduler_service.entity.Job;
import com.scheduler.scheduler_service.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class JobDataLoad implements CommandLineRunner {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public void run(String... args) {

        System.out.println("==||== Checking demo job data...");

        if (jobRepository.count() == 0) {

            System.out.println("==||== Inserting demo jobs...");

            for (int i = 1; i <= 20; i++) {

                Job job = new Job();
                job.setName("Demo Job " + i);
                job.setJobType("EMAIL");
                job.setStatus("SCHEDULED");
                job.setCronExpression("*/1 * * * *");
                job.setNextRunTime(LocalDateTime.now().plusSeconds(i * 10));
                job.setCreatedAt(LocalDateTime.now());
                job.setMaxRetries(3);
                job.setRetryCount(0);
                job.setPayload("{}");

                jobRepository.save(job);
            }

            System.out.println("||==|| Demo jobs inserted.");
        } else {
            System.out.println("||==|| Jobs already exist. Skipping insert.");
        }
    }
}