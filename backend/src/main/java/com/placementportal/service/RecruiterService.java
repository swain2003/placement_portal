package com.placementportal.service;

import com.placementportal.dto.ApplicationResponse;
import com.placementportal.dto.JobRequest;
import com.placementportal.dto.JobResponse;
import com.placementportal.dto.RecruiterProfileRequest;
import com.placementportal.dto.RecruiterProfileResponse;
import com.placementportal.entity.ApplicationStatus;

import java.util.List;

public interface RecruiterService {
    RecruiterProfileResponse getProfile(String email);

    RecruiterProfileResponse upsertProfile(String email, RecruiterProfileRequest request);

    JobResponse createJob(String email, JobRequest request);

    JobResponse updateJob(String email, Long jobId, JobRequest request);

    void deleteJob(String email, Long jobId);

    List<JobResponse> myJobs(String email);

    List<ApplicationResponse> jobApplicants(String email, Long jobId);

    ApplicationResponse updateApplicationStatus(String email, Long applicationId, ApplicationStatus status);
}
