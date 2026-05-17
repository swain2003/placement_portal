package com.placementportal.service;

import com.placementportal.dto.JobResponse;
import org.springframework.data.domain.Page;

public interface JobService {
    Page<JobResponse> listOpenJobs(String search, int page, int size, String sortBy);

    JobResponse getJob(Long id);
}
