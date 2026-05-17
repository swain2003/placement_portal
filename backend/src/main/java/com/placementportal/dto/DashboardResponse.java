package com.placementportal.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class DashboardResponse {
    private long totalStudents;
    private long totalRecruiters;
    private long totalJobs;
    private long totalApplications;
    private long selectedApplications;
    private long rejectedApplications;
    private Map<String, Long> topSkills;
}
