package com.placementportal.dto;

import com.placementportal.entity.ApplicationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApplicationResponse {
    private Long id;
    private String studentName;
    private String studentEmail;
    private String jobTitle;
    private String companyName;
    private ApplicationStatus status;
    private LocalDateTime createdAt;
    private String resumePath;
}
