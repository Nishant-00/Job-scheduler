package com.scheduler.scheduler_service.controller;

import com.scheduler.scheduler_service.entity.Job;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.scheduler.scheduler_service.service.JobService;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public Job create(@RequestBody Job job){
        return jobService.createJob(job);
    }

    @GetMapping
    public List<Job> getAll(){
        return jobService.getAllJobs();
    }
}

//
//What happens when request comes?
//
//DispatcherServlet receives HTTP request
//
//It matches URL /jobs
//
//Calls this controller method
//
//JSON body → converted to Job object
//
//Service called
//
//Result → converted back to JSON
//
//This is Spring MVC magic.