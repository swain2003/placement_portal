package com.placementportal.controller;

import com.placementportal.dto.*;
import com.placementportal.entity.ApplicationStatus;
import com.placementportal.service.RecruiterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruiters")
@PreAuthorize("hasRole('RECRUITER')")
public class RecruiterController {

    private final RecruiterService recruiterService;

    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<RecruiterProfileResponse>> profile(Authentication authentication) {
        return ResponseEntity.ok(ApiResponse.<RecruiterProfileResponse>builder().success(true).message("Profile fetched").data(recruiterService.getProfile(authentication.getName())).build());
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<RecruiterProfileResponse>> updateProfile(Authentication authentication,
                                                                                @RequestBody RecruiterProfileRequest request) {
        return ResponseEntity.ok(ApiResponse.<RecruiterProfileResponse>builder().success(true).message("Profile updated").data(recruiterService.upsertProfile(authentication.getName(), request)).build());
    }

    @PostMapping("/jobs")
    public ResponseEntity<ApiResponse<JobResponse>> createJob(Authentication authentication, @Valid @RequestBody JobRequest request) {
        return ResponseEntity.ok(ApiResponse.<JobResponse>builder().success(true).message("Job posted").data(recruiterService.createJob(authentication.getName(), request)).build());
    }

    @PutMapping("/jobs/{jobId}")
    public ResponseEntity<ApiResponse<JobResponse>> updateJob(Authentication authentication,
                                                              @PathVariable Long jobId,
                                                              @Valid @RequestBody JobRequest request) {
        return ResponseEntity.ok(ApiResponse.<JobResponse>builder().success(true).message("Job updated").data(recruiterService.updateJob(authentication.getName(), jobId, request)).build());
    }

    @DeleteMapping("/jobs/{jobId}")
    public ResponseEntity<ApiResponse<Void>> deleteJob(Authentication authentication, @PathVariable Long jobId) {
        recruiterService.deleteJob(authentication.getName(), jobId);
        return ResponseEntity.ok(ApiResponse.<Void>builder().success(true).message("Job closed").build());
    }

    @GetMapping("/jobs")
    public ResponseEntity<ApiResponse<List<JobResponse>>> myJobs(Authentication authentication) {
        return ResponseEntity.ok(ApiResponse.<List<JobResponse>>builder().success(true).message("Jobs fetched").data(recruiterService.myJobs(authentication.getName())).build());
    }

    @GetMapping("/jobs/{jobId}/applicants")
    public ResponseEntity<ApiResponse<List<ApplicationResponse>>> applicants(Authentication authentication, @PathVariable Long jobId) {
        return ResponseEntity.ok(ApiResponse.<List<ApplicationResponse>>builder().success(true).message("Applicants fetched").data(recruiterService.jobApplicants(authentication.getName(), jobId)).build());
    }

    @PatchMapping("/applications/{applicationId}")
    public ResponseEntity<ApiResponse<ApplicationResponse>> updateApplication(Authentication authentication,
                                                                              @PathVariable Long applicationId,
                                                                              @RequestParam ApplicationStatus status) {
        return ResponseEntity.ok(ApiResponse.<ApplicationResponse>builder().success(true).message("Application status updated").data(recruiterService.updateApplicationStatus(authentication.getName(), applicationId, status)).build());
    }
}
