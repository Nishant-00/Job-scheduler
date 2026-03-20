package com.scheduler.scheduler_service.scheduler;

import com.scheduler.scheduler_service.entity.Job;
import com.scheduler.scheduler_service.entity.JobExecution;
import com.scheduler.scheduler_service.repository.JobExecutionRepository;
import com.scheduler.scheduler_service.repository.JobRepository;
import com.scheduler.scheduler_service.service.JobExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class JobScheduler {

    private final JobRepository jobRepository;
    private final JobExecutor jobExecutor;
    private final JobExecutionRepository jobExecutionRepository;

    @Scheduled(fixedRate = 10000)
    public void runJobs(){

        System.out.println("Scheduler running...");

        List<Job> jobs = jobRepository.findReadyJobs();

        for(Job job : jobs){



            if("SCHEDULED".equals(job.getStatus())){

               //test race condition
                try{
                    Thread.sleep(3000);
                }catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
                //test race condition

//                job.setStatus("RUNNING");
//                jobRepository.save(job);

                int updated = jobRepository.lockJob(job.getId());

                if(updated == 0){
                    System.out.println("Job already locked by another instance: " + job.getId());
                    continue;
                }

                JobExecution exec = new JobExecution();
                exec.setJobId(job.getId());
                exec.setStartTime(LocalDateTime.now());
                exec.setStatus("RUNNING");

                jobExecutionRepository.save(exec);

                jobExecutor.execute(job, exec.getId() );   //  pass executionId

            }
        }
    }
}