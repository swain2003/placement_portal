package com.placementportal.dto;

import com.placementportal.entity.JobStatus;
import com.placementportal.entity.JobType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private JobType type;
    private String location;
    private Double minimumCgpa;
    private JobStatus status;
    private LocalDateTime createdAt;
    private String companyName;
    private Set<String> requiredSkills;
}
