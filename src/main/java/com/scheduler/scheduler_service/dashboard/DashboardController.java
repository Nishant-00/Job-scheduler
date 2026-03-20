package com.scheduler.scheduler_service.dashboard;

import com.scheduler.scheduler_service.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final JobRepository jobRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model){

        model.addAttribute("jobs", jobRepository.findAll());

        return "dashboard";
    }
}