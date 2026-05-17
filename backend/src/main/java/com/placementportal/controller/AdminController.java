package com.placementportal.controller;

import com.placementportal.dto.ApiResponse;
import com.placementportal.dto.ApplicationResponse;
import com.placementportal.dto.JobResponse;
import com.placementportal.dto.RecruiterProfileResponse;
import com.placementportal.dto.StudentProfileResponse;
import com.placementportal.entity.Job;
import com.placementportal.entity.RecruiterProfile;
import com.placementportal.entity.StudentProfile;
import com.placementportal.repository.ApplicationRepository;
import com.placementportal.repository.JobRepository;
import com.placementportal.repository.RecruiterProfileRepository;
import com.placementportal.repository.StudentProfileRepository;
import com.placementportal.service.impl.ServiceMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final StudentProfileRepository studentProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;

    public AdminController(StudentProfileRepository studentProfileRepository,
                           RecruiterProfileRepository recruiterProfileRepository,
                           JobRepository jobRepository,
                           ApplicationRepository applicationRepository) {
        this.studentProfileRepository = studentProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
    }

    @GetMapping("/students")
    public ResponseEntity<ApiResponse<List<StudentProfileResponse>>> students() {
        List<StudentProfileResponse> data = studentProfileRepository.findAll().stream().map(ServiceMapper::toStudentResponse).toList();
        return ResponseEntity.ok(ApiResponse.<List<StudentProfileResponse>>builder().success(true).message("Students fetched").data(data).build());
    }

    @GetMapping("/recruiters")
    public ResponseEntity<ApiResponse<List<RecruiterProfileResponse>>> recruiters() {
        List<RecruiterProfileResponse> data = recruiterProfileRepository.findAll().stream().map(ServiceMapper::toRecruiterResponse).toList();
        return ResponseEntity.ok(ApiResponse.<List<RecruiterProfileResponse>>builder().success(true).message("Recruiters fetched").data(data).build());
    }

    @GetMapping("/jobs")
    public ResponseEntity<ApiResponse<List<JobResponse>>> jobs() {
        List<JobResponse> data = jobRepository.findAll().stream().map(ServiceMapper::toJobResponse).toList();
        return ResponseEntity.ok(ApiResponse.<List<JobResponse>>builder().success(true).message("Jobs fetched").data(data).build());
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<ApiResponse<Void>> closeJob(@PathVariable Long id) {
        Job job = jobRepository.findById(id).orElseThrow();
        job.setStatus(com.placementportal.entity.JobStatus.CLOSED);
        jobRepository.save(job);
        return ResponseEntity.ok(ApiResponse.<Void>builder().success(true).message("Job closed").build());
    }

    @GetMapping("/applications")
    public ResponseEntity<ApiResponse<List<ApplicationResponse>>> applications() {
        List<ApplicationResponse> data = applicationRepository.findAll().stream().map(ServiceMapper::toApplicationResponse).toList();
        return ResponseEntity.ok(ApiResponse.<List<ApplicationResponse>>builder().success(true).message("Applications fetched").data(data).build());
    }
}
