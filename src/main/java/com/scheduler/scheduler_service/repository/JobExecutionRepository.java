package com.scheduler.scheduler_service.repository;

import com.scheduler.scheduler_service.entity.JobExecution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobExecutionRepository extends JpaRepository<JobExecution, Long> {
    long count();
}