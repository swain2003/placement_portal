package com.placementportal.repository;

import com.placementportal.entity.Job;
import com.placementportal.entity.JobStatus;
import com.placementportal.entity.RecruiterProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    Page<Job> findByStatusAndTitleContainingIgnoreCase(JobStatus status, String title, Pageable pageable);

    List<Job> findByRecruiter(RecruiterProfile recruiter);
}
