# Distributed Job Scheduler Service

A backend scheduling system built using **Spring Boot + PostgreSQL + Docker** that allows users to create and manage scheduled jobs which execute automatically at a specified time.

---

## Project Overview

Many real-world systems require tasks to run automatically such as:

* Nightly database backups
* Report generation
* Log cleanup
* Notification triggers

This project implements a **lightweight job scheduler engine** that stores jobs in a database and executes them using a background scheduler.

---


## ⚙️ Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Docker
* Maven
* Lombok
* REST APIs
* Git

---

## Running Locally
Prerequisites: Java 21, Maven, Docker Desktop

# 1. Clone
git clone https://github.com/Nishant-00/Job-scheduler.git
cd Job-scheduler

# 2. Build the JAR
mvn clean package -DskipTests

# 3. Build Docker image
docker build -t scheduler-app .

# 4. Start everything
docker-compose up

---

## Concepts Used

* REST API design
* Dependency Injection
* Inversion of Control (IoC)
* ORM using JPA
* Background Scheduling
* Layered Architecture
* Containerization using Docker
* Maven Build Lifecycle

---

## Future Enhancements

* Authentication & authorization
* Job priority queue
* UI dashboard
* Kafka / Redis integration
* AWS/GCP Deployment

---

