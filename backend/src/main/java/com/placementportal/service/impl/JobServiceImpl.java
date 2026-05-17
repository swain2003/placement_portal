package com.placementportal.service.impl;

import com.placementportal.dto.JobResponse;
import com.placementportal.entity.Job;
import com.placementportal.entity.JobStatus;
import com.placementportal.exception.ResourceNotFoundException;
import com.placementportal.repository.JobRepository;
import com.placementportal.service.JobService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Page<JobResponse> listOpenJobs(String search, int page, int size, String sortBy) {
        String safeSearch = search == null ? "" : search;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy == null || sortBy.isBlank() ? "createdAt" : sortBy));
        return jobRepository.findByStatusAndTitleContainingIgnoreCase(JobStatus.OPEN, safeSearch, pageRequest)
                .map(ServiceMapper::toJobResponse);
    }

    @Override
    public JobResponse getJob(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        return ServiceMapper.toJobResponse(job);
    }
}
