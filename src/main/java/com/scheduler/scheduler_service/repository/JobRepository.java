package com.scheduler.scheduler_service.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.scheduler.scheduler_service.entity.Job;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    long countByStatus(String status);
    long count();
    List<Job> findByStatus(String status);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("""
UPDATE Job j
SET j.status = 'RUNNING'
WHERE j.id = :id AND j.status = 'SCHEDULED'
""")
    int lockJob(Long id);

    @Query("""
SELECT j FROM Job j
WHERE j.status = 'SCHEDULED'
AND j.nextRunTime IS NOT NULL
AND j.nextRunTime <= CURRENT_TIMESTAMP
ORDER BY j.nextRunTime ASC
""")
    List<Job> findReadyJobs();



}