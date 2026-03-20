package com.scheduler.scheduler_service.controller;

import com.scheduler.scheduler_service.entity.Job;
import com.scheduler.scheduler_service.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobs")
public class JobApiController {

    private final JobRepository jobRepository;

    @GetMapping
    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }
}