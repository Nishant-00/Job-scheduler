package com.scheduler.scheduler_service.metrices;

import com.scheduler.scheduler_service.repository.JobExecutionRepository;
import com.scheduler.scheduler_service.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MetricsService {

    private final JobRepository jobRepository;
    private final JobExecutionRepository jobExecutionRepository;

    public Map<String, Object> getMetrics(){

        Map<String, Object> metrics = new HashMap<>();

        metrics.put("totalJobs", jobRepository.count());
        metrics.put("scheduledJobs", jobRepository.countByStatus("SCHEDULED"));
        metrics.put("runningJobs", jobRepository.countByStatus("RUNNING"));
        metrics.put("successJobs", jobRepository.countByStatus("SUCCESS"));
        metrics.put("failedJobs", jobRepository.countByStatus("FAILED"));

        metrics.put("totalExecutions", jobExecutionRepository.count());

        return metrics;
    }
}