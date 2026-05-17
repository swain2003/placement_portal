package com.placementportal.service;

import com.placementportal.dto.ApplicationResponse;
import com.placementportal.dto.StudentProfileRequest;
import com.placementportal.dto.StudentProfileResponse;

import java.util.List;

public interface StudentService {
    StudentProfileResponse getProfile(String email);

    StudentProfileResponse upsertProfile(String email, StudentProfileRequest request);

    StudentProfileResponse uploadResume(String email, String fileName);

    void applyToJob(String email, Long jobId);

    List<ApplicationResponse> getApplications(String email);
}
