package com.scheduler.scheduler_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_execution")
@Data
public class JobExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String status;

    private String errorMessage;
}