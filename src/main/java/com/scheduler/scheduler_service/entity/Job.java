package com.scheduler.scheduler_service.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
@Entity
@Table(name="jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String jobType;

    private String cronExpression;

    @Column(columnDefinition = "TEXT")
    private String payload;

    private String status;

    private LocalDateTime createdAt;

    @Column(name = "next_run_time")
    private LocalDateTime nextRunTime;

    private Integer retryCount;
    private Integer maxRetries;
}

//What is this?
//
//This is Object Relational Mapping (ORM).
//
//Meaning:
//
//Java Object  ↔  Database Table Row
//
//Spring / Hibernate will:
//
//convert Job object → SQL insert
//
//convert SQL result → Job object
//
//You never write SQL manually.