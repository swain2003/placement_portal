package com.placementportal.controller;

import com.placementportal.dto.ApiResponse;
import com.placementportal.dto.JobResponse;
import com.placementportal.service.JobService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
@PreAuthorize("hasAnyRole('STUDENT','RECRUITER','ADMIN')")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<JobResponse>>> openJobs(@RequestParam(defaultValue = "") String search,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size,
                                                                   @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(ApiResponse.<Page<JobResponse>>builder().success(true).message("Jobs fetched").data(jobService.listOpenJobs(search, page, size, sortBy)).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<JobResponse>> getJob(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.<JobResponse>builder().success(true).message("Job fetched").data(jobService.getJob(id)).build());
    }
}
