package com.scheduler.scheduler_service.service;

import com.scheduler.scheduler_service.entity.Job;
import com.scheduler.scheduler_service.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public Job createJob(Job job){
        job.setStatus("SCHEDULED");
        job.setNextRunTime(LocalDateTime.now().plusSeconds(10));
        job.setCreatedAt(LocalDateTime.now());
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }
}