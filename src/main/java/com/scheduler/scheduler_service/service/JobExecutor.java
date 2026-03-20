package com.scheduler.scheduler_service.service;

import com.scheduler.scheduler_service.entity.Job;
import com.scheduler.scheduler_service.entity.JobExecution;
import com.scheduler.scheduler_service.repository.JobExecutionRepository;
import com.scheduler.scheduler_service.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@EnableAsync
@RequiredArgsConstructor
public class JobExecutor {

    private final JobExecutionRepository jobExecutionRepository;
    private final JobRepository jobRepository;



    @Async("jobExecutorPool")
    public void execute(Job job, Long executionId){

        try{

            System.out.println(
                    Thread.currentThread().getName() +
                            " executing job -> " + job.getName()
            );

            System.out.println("Running job logic: " + job.getName());

            Thread.sleep(10000);

              //  Force failure for testing
//            if(job.getName().contains("fail")){
//                throw new RuntimeException("Intentional failure for retry testing");
//            }


            JobExecution exec = jobExecutionRepository.findById(executionId).get();
            exec.setStatus("SUCCESS");
            exec.setEndTime(LocalDateTime.now());

            // reschedule recurring job
            job.setNextRunTime(LocalDateTime.now().plusSeconds(30));
            job.setStatus("SCHEDULED");

            jobRepository.save(job);
            jobExecutionRepository.save(exec);

        }catch (InterruptedException e){

            Thread.currentThread().interrupt();

        }catch(Exception e){

            JobExecution exec = jobExecutionRepository.findById(executionId).get();
            exec.setEndTime(LocalDateTime.now());

            int retry = job.getRetryCount() == null ? 0 : job.getRetryCount();

            if(retry < job.getMaxRetries()){
                job.setRetryCount(retry + 1);
                job.setStatus("SCHEDULED");
                job.setNextRunTime(LocalDateTime.now().plusSeconds(20));

                exec.setStatus("RETRY");
                exec.setErrorMessage(e.getMessage());

            }else{
                job.setStatus("FAILED");

                exec.setStatus("FAILED");
                exec.setErrorMessage("Max retries exhausted");
            }

            jobRepository.save(job);
            jobExecutionRepository.save(exec);
        }
    }
}